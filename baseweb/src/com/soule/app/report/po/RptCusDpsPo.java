package com.soule.app.report.po;

import java.io.Serializable;
import java.util.Date;



/**
 * 对应表的类
 */
public class RptCusDpsPo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String orgNo;
    private String unitName;
    private Double sumBal;
    private Double sumThanDBal;
    private Double sumThanMBal;
    private Double sumThanYBal;
    private Double orgBal;
    private Double orgThanDBal;
    private Double orgThanMBal;
    private Double orgThanYBal;
    private Double corpBal;
    private Double corpThanDBal;
    private Double corpThanMBal;
    private Double corpThanYBal;
    private Double sumYBalAvg;
    private Double sumThanDAvgBal;
    private Double sumThanMAvgBal;
    private Double sumThanYAvgBal;
    private Double orgYBalAvg;
    private Double orgThanDAvgBal;
    private Double orgThanMAvgBal;
    private Double orgThanYAvgBal;
    private Double corpYBalAvg;
    private Double corpThanDAvgBal;
    private Double corpThanMAvgBal;
    private Double corpThanYAvgBal;
    
    private String rank;
    private String custNo;
    private String custName;
    private String custType;
    private Double bal1;
    private Double bal2;
    private Double balDVal;
    
    private String cry;
    private Integer midCusCnt;
    private Integer ymidCusCnt;
    private Integer midAwdCnt;
    private Integer ymidAwdCnt;
    private Double bal;
    private Double ybal;
    private Double rate;
    private Double yrate;
    
    private Integer cnt;
    private String examNum;
//    private String custName;
//    private String custNo;
    private String accNo;
    private String prdCode;
    private String depCode;
    private Double amt;
    private Double amt1;
    private Double amt2;
    private Double repRate;
    private Double offRate;
    private String offDate;
    private String offValidDate;
    private String valueDate;
    private String maturityDate;
    private Double carryRate;
    private Double upRate;
    private String examReq;
    private String reason1;
    private String nonePro;
    private String reason2;
    
    private String invmGlCode;
    private String invmTermRoll;
    private Double storeRate;
    private Double rmbBal;
    
    private String acctId;
    private String creditCust;
//    private String cry;
    private Double oriAmt;
    private Double dolBal;
    private Integer depDueDate;
    private String openDate;
    private String pointLvl;
    private String manualInt;
    private String thanBLimit;
    private String offNo;
//    private String offDate;
    private String offDueDate;
    
    
    
    
	public String getAcctId() {
		return acctId;
	}
	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}
	public String getCreditCust() {
		return creditCust;
	}
	public void setCreditCust(String creditCust) {
		this.creditCust = creditCust;
	}
	public Double getOriAmt() {
		return oriAmt;
	}
	public void setOriAmt(Double oriAmt) {
		this.oriAmt = oriAmt;
	}
	public Double getDolBal() {
		return dolBal;
	}
	public void setDolBal(Double dolBal) {
		this.dolBal = dolBal;
	}
	public Integer getDepDueDate() {
		return depDueDate;
	}
	public void setDepDueDate(Integer depDueDate) {
		this.depDueDate = depDueDate;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getPointLvl() {
		return pointLvl;
	}
	public void setPointLvl(String pointLvl) {
		this.pointLvl = pointLvl;
	}
	public String getManualInt() {
		return manualInt;
	}
	public void setManualInt(String manualInt) {
		this.manualInt = manualInt;
	}
	public String getThanBLimit() {
		return thanBLimit;
	}
	public void setThanBLimit(String thanBLimit) {
		this.thanBLimit = thanBLimit;
	}
	public String getOffNo() {
		return offNo;
	}
	public void setOffNo(String offNo) {
		this.offNo = offNo;
	}
	public String getOffDueDate() {
		return offDueDate;
	}
	public void setOffDueDate(String offDueDate) {
		this.offDueDate = offDueDate;
	}
	public String getInvmGlCode() {
		return invmGlCode;
	}
	public void setInvmGlCode(String invmGlCode) {
		this.invmGlCode = invmGlCode;
	}
	public String getInvmTermRoll() {
		return invmTermRoll;
	}
	public void setInvmTermRoll(String invmTermRoll) {
		this.invmTermRoll = invmTermRoll;
	}
	public Double getStoreRate() {
		return storeRate;
	}
	public void setStoreRate(Double storeRate) {
		this.storeRate = storeRate;
	}
	public Double getRmbBal() {
		return rmbBal;
	}
	public void setRmbBal(Double rmbBal) {
		this.rmbBal = rmbBal;
	}
	public Integer getCnt() {
		return cnt;
	}
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	public String getExamNum() {
		return examNum;
	}
	public void setExamNum(String examNum) {
		this.examNum = examNum;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getPrdCode() {
		return prdCode;
	}
	public void setPrdCode(String prdCode) {
		this.prdCode = prdCode;
	}
	public String getDepCode() {
		return depCode;
	}
	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}
	public Double getAmt() {
		return amt;
	}
	public void setAmt(Double amt) {
		this.amt = amt;
	}
	public Double getRepRate() {
		return repRate;
	}
	public void setRepRate(Double repRate) {
		this.repRate = repRate;
	}
	public Double getOffRate() {
		return offRate;
	}
	public void setOffRate(Double offRate) {
		this.offRate = offRate;
	}
	public String getOffDate() {
		return offDate;
	}
	public void setOffDate(String offDate) {
		this.offDate = offDate;
	}
	public String getOffValidDate() {
		return offValidDate;
	}
	public void setOffValidDate(String offValidDate) {
		this.offValidDate = offValidDate;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public String getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}
	public Double getCarryRate() {
		return carryRate;
	}
	public void setCarryRate(Double carryRate) {
		this.carryRate = carryRate;
	}
	public Double getUpRate() {
		return upRate;
	}
	public void setUpRate(Double upRate) {
		this.upRate = upRate;
	}
	public String getExamReq() {
		return examReq;
	}
	public void setExamReq(String examReq) {
		this.examReq = examReq;
	}
	public String getReason1() {
		return reason1;
	}
	public void setReason1(String reason1) {
		this.reason1 = reason1;
	}
	public String getNonePro() {
		return nonePro;
	}
	public void setNonePro(String nonePro) {
		this.nonePro = nonePro;
	}
	public String getReason2() {
		return reason2;
	}
	public void setReason2(String reason2) {
		this.reason2 = reason2;
	}

	public String getCry() {
		return cry;
	}
	public void setCry(String cry) {
		this.cry = cry;
	}
	public Integer getMidCusCnt() {
		return midCusCnt;
	}
	public void setMidCusCnt(Integer midCusCnt) {
		this.midCusCnt = midCusCnt;
	}
	
	public Integer getMidAwdCnt() {
		return midAwdCnt;
	}
	public void setMidAwdCnt(Integer midAwdCnt) {
		this.midAwdCnt = midAwdCnt;
	}
	
	public Double getBal() {
		return bal;
	}
	public void setBal(Double bal) {
		this.bal = bal;
	}

	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getOrgNo() {
		return orgNo;
	}
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Double getSumBal() {
		return sumBal;
	}
	public void setSumBal(Double sumBal) {
		this.sumBal = sumBal;
	}
	public Double getSumThanDBal() {
		return sumThanDBal;
	}
	public void setSumThanDBal(Double sumThanDBal) {
		this.sumThanDBal = sumThanDBal;
	}
	public Double getSumThanMBal() {
		return sumThanMBal;
	}
	public void setSumThanMBal(Double sumThanMBal) {
		this.sumThanMBal = sumThanMBal;
	}
	public Double getSumThanYBal() {
		return sumThanYBal;
	}
	public void setSumThanYBal(Double sumThanYBal) {
		this.sumThanYBal = sumThanYBal;
	}
	public Double getOrgBal() {
		return orgBal;
	}
	public void setOrgBal(Double orgBal) {
		this.orgBal = orgBal;
	}
	public Double getOrgThanDBal() {
		return orgThanDBal;
	}
	public void setOrgThanDBal(Double orgThanDBal) {
		this.orgThanDBal = orgThanDBal;
	}
	public Double getOrgThanMBal() {
		return orgThanMBal;
	}
	public void setOrgThanMBal(Double orgThanMBal) {
		this.orgThanMBal = orgThanMBal;
	}
	public Double getOrgThanYBal() {
		return orgThanYBal;
	}
	public void setOrgThanYBal(Double orgThanYBal) {
		this.orgThanYBal = orgThanYBal;
	}
	public Double getCorpBal() {
		return corpBal;
	}
	public void setCorpBal(Double corpBal) {
		this.corpBal = corpBal;
	}
	public Double getCorpThanDBal() {
		return corpThanDBal;
	}
	public void setCorpThanDBal(Double corpThanDBal) {
		this.corpThanDBal = corpThanDBal;
	}
	public Double getCorpThanMBal() {
		return corpThanMBal;
	}
	public void setCorpThanMBal(Double corpThanMBal) {
		this.corpThanMBal = corpThanMBal;
	}
	public Double getCorpThanYBal() {
		return corpThanYBal;
	}
	public void setCorpThanYBal(Double corpThanYBal) {
		this.corpThanYBal = corpThanYBal;
	}
	public Double getSumYBalAvg() {
		return sumYBalAvg;
	}
	public void setSumYBalAvg(Double sumYBalAvg) {
		this.sumYBalAvg = sumYBalAvg;
	}
	public Double getSumThanDAvgBal() {
		return sumThanDAvgBal;
	}
	public void setSumThanDAvgBal(Double sumThanDAvgBal) {
		this.sumThanDAvgBal = sumThanDAvgBal;
	}
	public Double getSumThanMAvgBal() {
		return sumThanMAvgBal;
	}
	public void setSumThanMAvgBal(Double sumThanMAvgBal) {
		this.sumThanMAvgBal = sumThanMAvgBal;
	}
	public Double getSumThanYAvgBal() {
		return sumThanYAvgBal;
	}
	public void setSumThanYAvgBal(Double sumThanYAvgBal) {
		this.sumThanYAvgBal = sumThanYAvgBal;
	}
	public Double getOrgYBalAvg() {
		return orgYBalAvg;
	}
	public void setOrgYBalAvg(Double orgYBalAvg) {
		this.orgYBalAvg = orgYBalAvg;
	}
	public Double getOrgThanDAvgBal() {
		return orgThanDAvgBal;
	}
	public void setOrgThanDAvgBal(Double orgThanDAvgBal) {
		this.orgThanDAvgBal = orgThanDAvgBal;
	}
	public Double getOrgThanMAvgBal() {
		return orgThanMAvgBal;
	}
	public void setOrgThanMAvgBal(Double orgThanMAvgBal) {
		this.orgThanMAvgBal = orgThanMAvgBal;
	}
	public Double getOrgThanYAvgBal() {
		return orgThanYAvgBal;
	}
	public void setOrgThanYAvgBal(Double orgThanYAvgBal) {
		this.orgThanYAvgBal = orgThanYAvgBal;
	}
	public Double getCorpYBalAvg() {
		return corpYBalAvg;
	}
	public void setCorpYBalAvg(Double corpYBalAvg) {
		this.corpYBalAvg = corpYBalAvg;
	}
	public Double getCorpThanDAvgBal() {
		return corpThanDAvgBal;
	}
	public void setCorpThanDAvgBal(Double corpThanDAvgBal) {
		this.corpThanDAvgBal = corpThanDAvgBal;
	}
	public Double getCorpThanMAvgBal() {
		return corpThanMAvgBal;
	}
	public void setCorpThanMAvgBal(Double corpThanMAvgBal) {
		this.corpThanMAvgBal = corpThanMAvgBal;
	}
	public Double getCorpThanYAvgBal() {
		return corpThanYAvgBal;
	}
	public void setCorpThanYAvgBal(Double corpThanYAvgBal) {
		this.corpThanYAvgBal = corpThanYAvgBal;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public Double getBal1() {
		return bal1;
	}
	public void setBal1(Double bal1) {
		this.bal1 = bal1;
	}
	public Double getBal2() {
		return bal2;
	}
	public void setBal2(Double bal2) {
		this.bal2 = bal2;
	}
	public Double getBalDVal() {
		return balDVal;
	}
	public void setBalDVal(Double balDVal) {
		this.balDVal = balDVal;
	}
	
	public Integer getYmidCusCnt() {
		return ymidCusCnt;
	}
	public void setYmidCusCnt(Integer ymidCusCnt) {
		this.ymidCusCnt = ymidCusCnt;
	}
	public Integer getYmidAwdCnt() {
		return ymidAwdCnt;
	}
	public void setYmidAwdCnt(Integer ymidAwdCnt) {
		this.ymidAwdCnt = ymidAwdCnt;
	}
	public Double getYbal() {
		return ybal;
	}
	public void setYbal(Double ybal) {
		this.ybal = ybal;
	}
	public Double getYrate() {
		return yrate;
	}
	public void setYrate(Double yrate) {
		this.yrate = yrate;
	}
	public Double getAmt1() {
		return amt1;
	}
	public void setAmt1(Double amt1) {
		this.amt1 = amt1;
	}
	public Double getAmt2() {
		return amt2;
	}
	public void setAmt2(Double amt2) {
		this.amt2 = amt2;
	}
    
}