package com.soule.crm.pub.dataimport;

import java.io.Serializable;
import java.util.List;

import com.soule.app.table.SysUploadFileErrorDetailPo;
import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

public class DataImportErrorDetailOut implements Serializable, IServiceResult {
    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private DataImportErrorDetailVo errorObject; 
    private List<SysUploadFileErrorDetailPo> errorDetailList;
	
    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

	public DataImportErrorDetailVo getErrorObject() {
		return errorObject;
	}

	public void setErrorObject(DataImportErrorDetailVo errorObject) {
		this.errorObject = errorObject;
	}

	public List<SysUploadFileErrorDetailPo> getErrorDetailList() {
		return errorDetailList;
	}

	public void setErrorDetailList(List<SysUploadFileErrorDetailPo> errorDetailList) {
		this.errorDetailList = errorDetailList;
	}
}
