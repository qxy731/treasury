package com.soule.app.pfm.tm.report.po;

public class ModelAnalysisReportPo {
	private String modelName;
	
	private String modelCode;
	public String getModelName() {
		return modelName;
	}
	
	
	
	public String getModelCode() {
		return modelCode;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	private String unitId;
	private String unitName;
	private String dataDate;
	private String tarCode;
	private String tarName;
	private String tarValue;
	public String getTarCode() {
		return tarCode;
	}
	public void setTarCode(String tarCode) {
		this.tarCode = tarCode;
	}
	public String getTarName() {
		return tarName;
	}
	public void setTarName(String tarName) {
		this.tarName = tarName;
	}
	public String getTarValue() {
		return tarValue;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public void setTarValue(String tarValue) {
		this.tarValue = tarValue;
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
