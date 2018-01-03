package com.soule.app.pfm.tm.report.bankfundsflow;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

public class BankFundsFlowReportQueryIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String dataDate;//报表日期 
    private String unitId;//填报单位
    private String unitName;//填报单位
    private String subCode;//预算科目
    private String cntType;//汇表范围
    private String dataType;//数据范围
	
	public String getDataDate() {
		return dataDate;
	}

	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public ServiceInput getInputHead() {
	    return inputHead;
	}

	public void setInputHead(ServiceInput inputHead) {
	    this.inputHead = inputHead;
	}

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getCntType() {
		return cntType;
	}

	public void setCntType(String cntType) {
		this.cntType = cntType;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
}