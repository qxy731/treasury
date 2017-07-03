package com.soule.app.pfm.tm.report.bankfundsflow;

public class TreasuryFundsSourcePo {
	
	private String systemType;//系统类型编码
	private String systemTypeName;//系统类型名称
	private String bankAllInflow;//从商业银行流入国库
	private String bankAllOutflow;//从国库流向商业银行
	private String bankAllNetFlow;//净流入（流出）
	
	public String getSystemType() {
		return systemType;
	}
	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}
	public String getSystemTypeName() {
		return systemTypeName;
	}
	public void setSystemTypeName(String systemTypeName) {
		this.systemTypeName = systemTypeName;
	}
	public String getBankAllInflow() {
		return bankAllInflow;
	}
	public void setBankAllInflow(String bankAllInflow) {
		this.bankAllInflow = bankAllInflow;
	}
	public String getBankAllOutflow() {
		return bankAllOutflow;
	}
	public void setBankAllOutflow(String bankAllOutflow) {
		this.bankAllOutflow = bankAllOutflow;
	}
	public String getBankAllNetFlow() {
		return bankAllNetFlow;
	}
	public void setBankAllNetFlow(String bankAllNetFlow) {
		this.bankAllNetFlow = bankAllNetFlow;
	}

}
