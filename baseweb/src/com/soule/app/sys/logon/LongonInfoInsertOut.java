package com.soule.app.sys.logon;

import java.io.Serializable;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

public class LongonInfoInsertOut implements Serializable, IServiceResult {
    private static final long serialVersionUID = -2301415269076328346L;
    private ServiceResult head = new ServiceResult();


    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }
}
