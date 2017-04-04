package com.soule.crm.monitor.daily.proc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hyperic.sigar.ProcCredName;
import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.ProcState;
import org.hyperic.sigar.ProcTime;
import org.hyperic.sigar.ProcUtil;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;
/**
 * 进程监控业务操作
 */
@Service
public class ProcServiceImpl implements IProcService {

    private final static Log logger = LogFactory.getLog(ProcServiceImpl.class);
    // 数据库通用操作

    /**
     * 进程信息查询
     */
    public ProcListOut list(ProcListIn in) throws ServiceException {
        ProcListOut out = new ProcListOut();
        Sigar sigar = new Sigar();
        SigarProxy proxy = SigarProxyCache.newInstance(sigar);
        long[] pids;
        ArrayList<ProcDetailPo> list = new ArrayList<ProcDetailPo>();
        try {
            pids = proxy.getProcList();
            for (int i=0; i<pids.length; i++) {
                long pid = pids[i];
                try {
                    ProcDetailPo pdp = buildProcDetailPo(pid,sigar,proxy);
                    list.add(pdp);
                } catch (SigarException e) {
                    logger.error("SERVICE",e);
                }
            }
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (SigarException e1) {
            logger.error("SERVICE",e1);
        }
        int sortIdx = 0;
        String[] names = {"psId","execName","startTime","memUsed","timeUsed","userId"};
        for (int i = 0 ; i < names.length ; i++) {
            if (names[i].equalsIgnoreCase(in.getSortname())) {
                sortIdx = i;
                break;
            }
        }
        sortBy(list,sortIdx,"asc".equalsIgnoreCase(in.getSortorder()));
        out.setDetail(list);
        return out;
    }

    private void sortBy(ArrayList<ProcDetailPo> list, final int sortIdx, final boolean isAsc) {
        Collections.sort(list, new Comparator<ProcDetailPo>() {
            public int compare(ProcDetailPo o1, ProcDetailPo o2) {
                int ret = 0;
                switch (sortIdx) {
                case 0: ret = o1.getPsId().compareTo(o2.getPsId());break;
                case 1: ret = o1.getExecName().compareTo(o2.getExecName());break;
                case 2: ret = o1.getStartTime().compareTo(o2.getStartTime());break;
                case 3: ret = o1.getMemUsed().compareTo(o2.getMemUsed());break;
                case 4: ret = o1.getTimeUsed().compareTo(o2.getTimeUsed());break;
                case 5: ret = o1.getUserId().compareTo(o2.getUserId());break;
                }
                ret = (isAsc?ret:-ret);
                return ret;
            }
        });
    }

    private ProcDetailPo buildProcDetailPo(long pid,Sigar sigar,SigarProxy proxy) throws SigarException {
        ProcDetailPo ret = new ProcDetailPo();
        ProcState state = sigar.getProcState(pid);
        ProcTime time = null;
        String unknown = "???";
        ret.setPsId(pid);

        try {
            ProcCredName cred = sigar.getProcCredName(pid);
            ret.setUserId(cred.getUser());
        } catch (SigarException e) {
            ret.setUserId(unknown);
        }

        try {
            time = sigar.getProcTime(pid);
            ret.setStartTime(new Date(time.getStartTime()));
            ret.setTimeUsed(time.getTotal());
        } catch (SigarException e) {
            ret.setTimeUsed(0l);
        }

        try {
            ProcMem mem = sigar.getProcMem(pid);
            ret.setMemUsed(mem.getResident());
        } catch (SigarException e) {
        }

        String name = ProcUtil.getDescription(sigar, pid);
        ret.setExecName(name);

        return ret;
    }

}
