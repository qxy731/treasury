package com.soule.app.pfm.tm.qtydef;

import java.io.Serializable;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

public class QtyDefInsertOut implements Serializable, IServiceResult{
	private static final long serialVersionUID = -7552671001583998079L;
	private ServiceResult head = new ServiceResult();
    //指标回显代码
    private String tarCode;
    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }
    public String getTarCode() {
		return tarCode;
	}
    public void setTarCode(String tarCode) {
		this.tarCode = tarCode;
	}
}