package com.soule.app.pfm.tm.report.bankfundsflow;

import java.math.BigDecimal;

public class ReportTargetPo {
	
	private String tarCode;//业务类型编码
	private String tarName;//业务类型名称
	private BigDecimal tarValue;//从商业银行流入国库
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
	
	public BigDecimal getTarValue() {
		return tarValue;
	}
	public void setTarValue(BigDecimal tarValue) {
		this.tarValue = tarValue;
	}

}
