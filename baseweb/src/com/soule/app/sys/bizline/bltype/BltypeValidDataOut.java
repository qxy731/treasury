package com.soule.app.sys.bizline.bltype;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数业务线类型维护:判断业务线类型数据
 */
public class BltypeValidDataOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private BltypeResultPo result;


    public BltypeResultPo getResult() {
        return result;
    }

    public void setResult(BltypeResultPo output) {
        this.result = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}