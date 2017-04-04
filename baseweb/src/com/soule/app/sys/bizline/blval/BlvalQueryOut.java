package com.soule.app.sys.bizline.blval;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数业务线分类值维护:查询业务线分类
 */
public class BlvalQueryOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<BlvalBizValPo> bizVal;


    public List<BlvalBizValPo> getBizVal() {
        return bizVal;
    }

    public void setBizVal(List<BlvalBizValPo> output) {
        this.bizVal = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}