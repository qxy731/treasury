package com.soule.app.sys.bizline.blass;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数业务线人员分配:查询业务线中人员
 */
public class BlassQueryOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<BlassBizassPo> bizass;


    public List<BlassBizassPo> getBizass() {
        return bizass;
    }

    public void setBizass(List<BlassBizassPo> output) {
        this.bizass = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}