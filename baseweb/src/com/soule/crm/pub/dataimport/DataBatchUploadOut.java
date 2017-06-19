package com.soule.crm.pub.dataimport;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;
import com.soule.crm.tools.ErrorInfoPo;


/**
 * 
 */
public class DataBatchUploadOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();

    private List<ErrorInfoPo> errors ;

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

    public List<ErrorInfoPo> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorInfoPo> errors) {
        this.errors = errors;
    }


}