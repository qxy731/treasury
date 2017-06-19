package com.soule.app.pfm.tm.report.action;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数人员:查询人员
 */
public class UserDefinedReportQueryIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String unitId;
    private String dataDate;
	public ServiceInput getInputHead() {
		return inputHead;
	}
	public void setInputHead(ServiceInput inputHead) {
		this.inputHead = inputHead;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getDataDate() {
		return dataDate;
	}
	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}


   

}