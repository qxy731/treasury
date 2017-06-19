package com.soule.crm.pfm.engine.model;

public class ParameterModel {

	//参数类别代码
	private String paramGroupCode;
	//参数类别名称
	private String paramGroupName;
	//是否回算
	private String isBackTrace;
	//参数类型代码 (自定义参数/系统参数)
	private String paramTypeCode;
	//参数名称 (自定义参数/系统参数)
	private String paramName;
	//是否为自定义参数 
	private String isDefineFlag;
	//参数代码
	private String paramCode;
	
	public String getParamGroupCode() {
		return paramGroupCode;
	}
	public void setParamGroupCode(String paramGroupCode) {
		this.paramGroupCode = paramGroupCode;
	}
	public String getParamGroupName() {
		return paramGroupName;
	}
	public void setParamGroupName(String paramGroupName) {
		this.paramGroupName = paramGroupName;
	}
	public String getIsBackTrace() {
		return isBackTrace;
	}
	public void setIsBackTrace(String isBackTrace) {
		this.isBackTrace = isBackTrace;
	}
	public String getParamTypeCode() {
		return paramTypeCode;
	}
	public void setParamTypeCode(String paramTypeCode) {
		this.paramTypeCode = paramTypeCode;
	}
	
	public String getIsDefineFlag() {
		return isDefineFlag;
	}
	public void setIsDefineFlag(String isDefineFlag) {
		this.isDefineFlag = isDefineFlag;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamCode() {
		return paramCode;
	}
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	
}