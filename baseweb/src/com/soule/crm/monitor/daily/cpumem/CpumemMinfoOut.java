package com.soule.crm.monitor.daily.cpumem;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数处理器内存监控:内存信息
 */
public class CpumemMinfoOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private CpumemMemPo mem;


    public CpumemMemPo getMem() {
        return mem;
    }

    public void setMem(CpumemMemPo output) {
        this.mem = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}