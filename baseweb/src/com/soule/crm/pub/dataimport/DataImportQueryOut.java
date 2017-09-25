package com.soule.crm.pub.dataimport;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

public class DataImportQueryOut implements Serializable, IServiceResult {
    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<DataImportPo> datalist;
    private List<HashMap> detailList;
    
    private String recordTotle;
    private String recordSuccess;
    private String recordError; 

    public List<HashMap> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<HashMap> detailList) {
        this.detailList = detailList;
    }

    public List<DataImportPo> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<DataImportPo> datalist) {
        this.datalist = datalist;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

	public String getRecordTotle() {
		return recordTotle;
	}

	public void setRecordTotle(String recordTotle) {
		this.recordTotle = recordTotle;
	}

	public String getRecordSuccess() {
		return recordSuccess;
	}

	public void setRecordSuccess(String recordSuccess) {
		this.recordSuccess = recordSuccess;
	}

	public String getRecordError() {
		return recordError;
	}

	public void setRecordError(String recordError) {
		this.recordError = recordError;
	}
}
