package com.soule.crm.monitor.daily.cpumem;

import java.io.Serializable;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数处理器内存监控:处理器信息
 */
public class CpumemCinfoOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private CpumemCpuPo cpu;


    public CpumemCpuPo getCpu() {
        return cpu;
    }

    public void setCpu(CpumemCpuPo output) {
        this.cpu = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}