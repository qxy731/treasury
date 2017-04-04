package com.soule.crm.monitor.daily.cpumem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarLoader;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;

/**
 * 处理器内存监控业务操作
 */
@Service
public class CpumemServiceImpl implements ICpumemService {

    private final static Log logger = LogFactory.getLog(CpumemServiceImpl.class);

    /**
     * CPU当前状态查询
     */
    public CpumemCinfoOut cinfo(CpumemCinfoIn in) throws ServiceException {
        CpumemCinfoOut out = new CpumemCinfoOut();
        Sigar sigar = new Sigar();
        SigarProxy proxy = SigarProxyCache.newInstance(sigar);
        CpumemCpuPo cpuPo = new CpumemCpuPo();
        out.setCpu(cpuPo);
        try {
            CpuPerc cpu = proxy.getCpuPerc();
            cpuPo.setCurrent(System.currentTimeMillis());
            cpuPo.setIdleTime(cpu.getIdle());
            cpuPo.setSysTime(cpu.getSys());
            cpuPo.setUserTime(cpu.getUser());
        } catch (SigarException e) {
            logger.error("SERVICE", e);
        }
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }

    /**
     * 物理内存当前使用状态查询
     */
    public CpumemMinfoOut minfo(CpumemMinfoIn in) throws ServiceException {
        CpumemMinfoOut out = new CpumemMinfoOut();
        Sigar sigar = new Sigar();
        SigarProxy proxy = SigarProxyCache.newInstance(sigar);
        CpumemMemPo memPo = new CpumemMemPo();
        out.setMem(memPo);
        try {
            Mem mem = proxy.getMem();
            memPo.setCurrent(System.currentTimeMillis());
            memPo.setFreeMem(mem.getFree());
            memPo.setTotalMem(mem.getTotal());
            memPo.setUsedMem(mem.getUsed());
        } catch (SigarException e) {
            logger.error("SERVICE", e);
        }
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }

}
