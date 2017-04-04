package com.soule.crm.monitor.daily.proc;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数进程监控:进程信息
 */
public class ProcListOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<ProcDetailPo> detail;


    public List<ProcDetailPo> getDetail() {
        return detail;
    }

    public void setDetail(List<ProcDetailPo> output) {
        this.detail = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}