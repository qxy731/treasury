package com.soule.crm.pfm.param.paraminfo;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;


/**
 * 输出参数指标信息:指标信息查询
 */
public class ParaminfoQueryOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<ParaminfoQPo> q;


    public List<ParaminfoQPo> getQ() {
        return q;
    }

    public void setQ(List<ParaminfoQPo> output) {
        this.q = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}