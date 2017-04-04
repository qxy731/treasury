package com.soule.app.sys.bizline.bltype;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数业务线类型维护:查询所有业务线类型
 */
public class BltypeQueryOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<BltypeBizTypePo> bizType;


    public List<BltypeBizTypePo> getBizType() {
        return bizType;
    }

    public void setBizType(List<BltypeBizTypePo> output) {
        this.bizType = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}