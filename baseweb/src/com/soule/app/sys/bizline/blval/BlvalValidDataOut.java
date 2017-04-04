package com.soule.app.sys.bizline.blval;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数业务线分类值维护:判断业务线关系数据
 */
public class BlvalValidDataOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private BlvalResultPo result;


    public BlvalResultPo getResult() {
        return result;
    }

    public void setResult(BlvalResultPo output) {
        this.result = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}