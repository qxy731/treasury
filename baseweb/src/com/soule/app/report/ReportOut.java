package com.soule.app.report;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;


public class ReportOut implements Serializable, IServiceResult {
    /**
	 * 
	 */
    private static final long serialVersionUID = -7169396495675991661L;
    private ServiceResult head = new ServiceResult();
    private List list;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}