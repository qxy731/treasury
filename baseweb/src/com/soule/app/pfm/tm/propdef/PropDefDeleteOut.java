package com.soule.app.pfm.tm.propdef;

import java.io.Serializable;
import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 删除指标类（输出结果类）
 */
public class PropDefDeleteOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();


    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}