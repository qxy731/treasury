package com.soule.app.sys.fileupdown;

import java.io.Serializable;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

public class FileUploadDownOut implements Serializable, IServiceResult {

	private static final long serialVersionUID = 1L;
	
	private ServiceResult head = new ServiceResult();

	@Override
	public ServiceResult getResultHead() {
		return head;
	}
	
	public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}
