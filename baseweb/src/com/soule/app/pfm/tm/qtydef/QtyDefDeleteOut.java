package com.soule.app.pfm.tm.qtydef;

import java.io.Serializable;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

public class QtyDefDeleteOut implements Serializable, IServiceResult{
	private static final long serialVersionUID = 1136632199417523917L;
	private ServiceResult head = new ServiceResult();


	    public ServiceResult getResultHead() {
	        return head;
	    }

	    public void setResultHead(ServiceResult head) {
	        this.head = head;
	    }

}
