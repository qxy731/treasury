package com.soule.app.sys.filetransfer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.app.table.SysUploadFilePo;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.base.service.keygen.IKeyGenerator;
import com.soule.comm.ParamConstants;
import com.soule.comm.enu.YesNoFlag;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.StringUtil;
/**
 * 上传下载业务操作
 */
@Service
public class FileTransferServiceImpl implements IFileTransferService {

    private final static Log logger = LogFactory.getLog(FileTransferServiceImpl.class);
    @Autowired
    private IDefaultService sDefault;
    @Autowired
    private IKeyGenerator keyg;
    /**
     * 上传文件
     */
    public FileTransferUploadOut upload(FileTransferUploadIn in)
        throws ServiceException {
        FileTransferUploadOut out = new FileTransferUploadOut();
        if (in == null || in.getFile() == null) {
            AppUtils.setResult(out, MsgConstants.E0001);
        }
        SysUploadFilePo po = in.getFile();
        File file = new File(po.getFilePath());
        if (!file.exists()) {
            AppUtils.setResult(out, MsgConstants.E0009,po.getFilePath());
        }
        AppUtils.initRecordStatus(po);
        po.setDelFlag(YesNoFlag.NO.getValue());
        po.setFileId(keyg.getUUIDKey());
        String newFilePath = getFileRootPath(po.getCreateTime());
        try {
            FileUtils.copyFile(file, new File(newFilePath,po.getFileId()));
        } catch (IOException e) {
            logger.error("SERVICE",e);
            AppUtils.setResult(out, MsgConstants.E0010);
        }
        po.setFilePath(newFilePath);
        po.setFileType(in.getFile().getFileType());
       // po.setCreateOrg(createOrg);
        try {
            sDefault.getIbatisMediator().save("upload.saveSysUploadFile", po);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002);
        }
        out.setFile(po);
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }
    /**
     * 取得上传文件保存根目录
     * @param createTime
     * @return
     */
    private String getFileRootPath(Date createTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String subDir = sdf.format(createTime);
        return ParamConstants.UPLOAD_ROOT + "/" + subDir;
    }
    /**
     * 上传文件列表
     */
    public FileTransferListOut list(FileTransferListIn in)
        throws ServiceException {
        FileTransferListOut out = new FileTransferListOut();
        if (in == null || in.getUploadId() == null) {
            AppUtils.setResult(out, MsgConstants.E0001);
        }
        HashMap<String,Object> param = new HashMap<String,Object>();
        param.put("uploadId", in.getUploadId());
        param.put("delFlag", YesNoFlag.NO.getValue());
        try {
            List ret = sDefault.getIbatisMediator().find("upload.getSysUploadFile", param);
            out.setFile(ret);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002,e.getMessage());
        }
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }
    /**
     * 删除上传文件
     */
    public FileTransferDeleteOut delete(FileTransferDeleteIn in)
        throws ServiceException {
        FileTransferDeleteOut out = new FileTransferDeleteOut();
        if (in == null || in.getDelete() == null) {
            AppUtils.setResult(out, MsgConstants.E0001);
        }
        if (StringUtil.isEmpty(in.getDelete().getUploadId()) || StringUtil.isEmpty(in.getDelete().getFileId())) {
            AppUtils.setResult(out, MsgConstants.E0001);
        }
        try {
            sDefault.getIbatisMediator().update("upload.delSysUploadFile", in.getDelete());
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002);
        }
        AppUtils.setResult(out, MsgConstants.I0002);
        return out;
    }

    /**
     * 下载文件
     */
    public FileTransferDownloadOut download(FileTransferDownloadIn in) throws ServiceException {
        FileTransferDownloadOut out = new FileTransferDownloadOut();
        if (in == null || in.getFile() == null) {
            AppUtils.setResult(out, MsgConstants.E0001);
        }
        if (StringUtil.isEmpty(in.getFile().getUploadId()) || StringUtil.isEmpty(in.getFile().getFileId())) {
            AppUtils.setResult(out, MsgConstants.E0001);
        }
        try {
            SysUploadFilePo fpo = (SysUploadFilePo) sDefault.getIbatisMediator().findById("upload.getSysUploadFileByKey", in.getFile());
            sDefault.getIbatisMediator().update("upload.updateDownloadTime", in.getFile());
            out.setFile(fpo);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002);
        }
        AppUtils.setResult(out, MsgConstants.I0002);
        return out;
    }

}
