package com.soule.crm.pub.dataimport;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

public class DataImportErrorDetailIn implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private ServiceInput inputHead = new ServiceInput();

    private String uploadId;
    
    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

	public String getUploadId() {
		return uploadId;
	}

	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}

}
