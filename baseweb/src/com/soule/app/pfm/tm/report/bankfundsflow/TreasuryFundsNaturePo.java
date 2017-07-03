package com.soule.app.pfm.tm.report.bankfundsflow;

public class TreasuryFundsNaturePo {
	
	private String businessType;//业务类型编码
	private String businessTypeName;//业务类型名称
	private String bankAllInflow;//从商业银行流入国库
	private String bankAllInflowName;//从商业银行流入国库
	private String bankAllOutflow;//从国库流向商业银行
	private String bankAllOutflowName;//从国库流向商业银行
	private String bankAllNetFlow;//净流入（流出）
	
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getBusinessTypeName() {
		return businessTypeName;
	}
	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}
	public String getBankAllInflow() {
		return bankAllInflow;
	}
	public void setBankAllInflow(String bankAllInflow) {
		this.bankAllInflow = bankAllInflow;
	}
	public String getBankAllInflowName() {
		return bankAllInflowName;
	}
	public void setBankAllInflowName(String bankAllInflowName) {
		this.bankAllInflowName = bankAllInflowName;
	}
	public String getBankAllOutflow() {
		return bankAllOutflow;
	}
	public void setBankAllOutflow(String bankAllOutflow) {
		this.bankAllOutflow = bankAllOutflow;
	}
	public String getBankAllOutflowName() {
		return bankAllOutflowName;
	}
	public void setBankAllOutflowName(String bankAllOutflowName) {
		this.bankAllOutflowName = bankAllOutflowName;
	}
	public String getBankAllNetFlow() {
		return bankAllNetFlow;
	}
	public void setBankAllNetFlow(String bankAllNetFlow) {
		this.bankAllNetFlow = bankAllNetFlow;
	}

}
