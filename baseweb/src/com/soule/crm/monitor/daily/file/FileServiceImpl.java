package com.soule.crm.monitor.daily.file;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.NfsFileSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;
/**
 * 文件系统监控业务操作
 */
@Service
public class FileServiceImpl implements IFileService {

    private final static Log logger = LogFactory.getLog(FileServiceImpl.class);

    /**
     * 文件系统查询
     */
    public FileListOut list(FileListIn in)
        throws ServiceException {
        FileListOut out = new FileListOut();
        ArrayList<FileDetailPo> detail = new ArrayList<FileDetailPo>();
        out.setDetail(detail);

        Sigar sigar = new Sigar();
        SigarProxy proxy = SigarProxyCache.newInstance(sigar);
        FileSystem[] fslist;
        try {
            fslist = proxy.getFileSystemList();
            for (int i=0; i<fslist.length; i++) {
                FileDetailPo fdp = buildFileDetailPo(fslist[i],sigar,proxy);
                detail.add(fdp);
            }
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (SigarException e) {
            logger.error("SERVICE", e);
        }
        return out;
    }

    private FileDetailPo buildFileDetailPo(FileSystem fs,Sigar sigar,SigarProxy proxy) {
        long used, avail, total, pct;
        FileDetailPo ret = new FileDetailPo();
        try {
            FileSystemUsage usage;
            if (fs instanceof NfsFileSystem) {
                NfsFileSystem nfs = (NfsFileSystem)fs;
                if (!nfs.ping()) {
                    System.out.println(nfs.getUnreachableMessage());
                    return ret;
                }
            }
            usage = sigar.getFileSystemUsage(fs.getDirName());
            {
                used = usage.getTotal() - usage.getFree();
                avail = usage.getAvail();
                total = usage.getTotal();

                pct = (long)(usage.getUsePercent() * 100);
            }
        } catch (SigarException e) {
            //e.g. on win32 D:\ fails with "Device not ready"
            //if there is no cd in the drive.
            used = avail = total = pct = 0;
        }

        String usePct;
        if (pct == 0) {
            usePct = "-";
        }
        else {
            usePct = pct + "%";
        }
        ret.setFileId(fs.getDevName());
        ret.setSize(formatSize(total));
        ret.setUsed(formatSize(used));
        ret.setAvail(formatSize(avail));
        ret.setMountOn(fs.getDirName());
        ret.setUsedRate(usePct);
        ret.setType(fs.getSysTypeName() + "/" + fs.getTypeName());
        return ret;
    }
    private String formatSize(long size) {
        return Sigar.formatSize(size * 1024);
    }
}
