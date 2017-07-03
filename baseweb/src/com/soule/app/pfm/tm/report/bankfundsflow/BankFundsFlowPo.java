package com.soule.app.pfm.tm.report.bankfundsflow;

import java.math.BigDecimal;

public class BankFundsFlowPo {
	private String custOrgNo;//银行编号
	private String custOrgName;//银行名称
	private String unitId;//银行所属国库编号
	private String unitName;//银行所属国库名称
	private BigDecimal bankAllInflow;//从商业银行流入国库
	private BigDecimal bankSpecialInflow;//其中：来自专户
	private BigDecimal bankAllOutflow;//从国库流向商业银行
	private BigDecimal bankSpecialOutflow;//其中：流向专户
	private BigDecimal bankAllNetFlow;//净流入（流出）
	public String getCustOrgNo() {
		return custOrgNo;
	}
	public void setCustOrgNo(String custOrgNo) {
		this.custOrgNo = custOrgNo;
	}
	public String getCustOrgName() {
		return custOrgName;
	}
	public void setCustOrgName(String custOrgName) {
		this.custOrgName = custOrgName;
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
	public BigDecimal getBankAllInflow() {
		return bankAllInflow;
	}
	public void setBankAllInflow(BigDecimal bankAllInflow) {
		this.bankAllInflow = bankAllInflow;
	}
	public BigDecimal getBankSpecialInflow() {
		return bankSpecialInflow;
	}
	public void setBankSpecialInflow(BigDecimal bankSpecialInflow) {
		this.bankSpecialInflow = bankSpecialInflow;
	}
	public BigDecimal getBankAllOutflow() {
		return bankAllOutflow;
	}
	public void setBankAllOutflow(BigDecimal bankAllOutflow) {
		this.bankAllOutflow = bankAllOutflow;
	}
	public BigDecimal getBankSpecialOutflow() {
		return bankSpecialOutflow;
	}
	public void setBankSpecialOutflow(BigDecimal bankSpecialOutflow) {
		this.bankSpecialOutflow = bankSpecialOutflow;
	}
	public BigDecimal getBankAllNetFlow() {
		return bankAllNetFlow;
	}
	public void setBankAllNetFlow(BigDecimal bankAllNetFlow) {
		this.bankAllNetFlow = bankAllNetFlow;
	}

}