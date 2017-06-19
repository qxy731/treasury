package com.soule.app.report.po;

import java.io.Serializable;
import java.util.Date;



/**
 * 对应表的类
 */
public class RptMidPo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String orgNo;
    private String unitName;
    private String unitId2;
    private String unitName2;
    private Double bal1;
    private Double thanDBal1;
    private Double thanMBal1;
    private Double thanYBal1;
    private Double account1;
    private Double rate1;
    private Double bal2;
    private Double thanMBal2;
    private Double thanYBal2;
    private Integer cusCnt;
    private Integer cnt;
    private Integer cntM;
    private Integer cntY;
    private Double rate2;
    private Double rate3;
    private Double rate4;
    private String staffName;
    private Double bal;
    private Double thanDBal;
    private Double thanMBal;
    private Double thanYBal;
    private Double cntD1;
    private Double cntM1;
    private Double cntY1;
    private Double cntD2;
    private Double cntM2;
    private Double cntY2;
    private String misc;
    private String staffId;
    private Double targetY;
    private String targetY1;
    private String targetY2;
    private String targetY3;
    private String targetY4;
    private Double rate;
    private Double thanRate;
    private Double thanRate1;
    private Double thanRate2;
    private Double thanRate3;
    private Double thanRate4;
    private Double ybalAvg;
    private Double thanMAvgBal;
    private Double thanYAvgBal;
    private Double sumBal;
    private Double sumMBal;
    private Double sumYBal;
    private Integer plainCnt;
    private Integer engageCnt;
    private Double engageRate;
    private Integer initiatorCnt;
    private Double iceBreakRate;
    private Double loanRate1;
    private Double loanRate2;
    private Double loanLosses;
    private Integer cnt1;
    private Integer cnt2;
    private Double avgBal1;
    private Double avgBal2;
    private Double thanAvgBal1;
    private Double thanAvgBal2;
    private Integer replyCnt;
    private Double replyAmt;
    private Double loanAmt;
    private Integer awdCnt;
    private Double awdLimit;
    private Double loan;
    private Double loanReplyRate;
    private Integer storeCnt;
    private Double surLimit;
    private Double surLoan;
    private Double prfM1;
    private Double prfY1;
//    private Double targetY;
//    private Double rate;
//    private Double thanRate;
    private Double incomeLev;
    private Double prfM2;
    private Double prfY2;
    private Double priceLev;
    private Double prfM3;
    private Double prfY3;
    private Double profitLev;
    
//    private Integer cusCnt;
    private Double salarySign;
//    private Double rate1;
    private Double salaryRate;
    private Double netbankSign;
//    private Double rate2;
    private Double annuSign;
//    private Double rate3;
    private Double taxSign;
    private Double highCustCnt;
    private Double rate5;
    private Double guestCnt;
    private Double rate6;
    private Double tripDep;
    private Double rate7;
    private Double creditOpen;
    private Double rate8;
    
    
    
    
	public Double getSalarySign() {
		return salarySign;
	}
	public void setSalarySign(Double salarySign) {
		this.salarySign = salarySign;
	}
	public Double getSalaryRate() {
		return salaryRate;
	}
	public void setSalaryRate(Double salaryRate) {
		this.salaryRate = salaryRate;
	}
	public Double getNetbankSign() {
		return netbankSign;
	}
	public void setNetbankSign(Double netbankSign) {
		this.netbankSign = netbankSign;
	}
	public Double getAnnuSign() {
		return annuSign;
	}
	public void setAnnuSign(Double annuSign) {
		this.annuSign = annuSign;
	}
	public Double getTaxSign() {
		return taxSign;
	}
	public void setTaxSign(Double taxSign) {
		this.taxSign = taxSign;
	}
	public Double getRate4() {
		return rate4;
	}
	public void setRate4(Double rate4) {
		this.rate4 = rate4;
	}
	public Double getHighCustCnt() {
		return highCustCnt;
	}
	public void setHighCustCnt(Double highCustCnt) {
		this.highCustCnt = highCustCnt;
	}
	public Double getRate5() {
		return rate5;
	}
	public void setRate5(Double rate5) {
		this.rate5 = rate5;
	}
	public Double getGuestCnt() {
		return guestCnt;
	}
	public void setGuestCnt(Double guestCnt) {
		this.guestCnt = guestCnt;
	}
	public Double getRate6() {
		return rate6;
	}
	public void setRate6(Double rate6) {
		this.rate6 = rate6;
	}
	public Double getTripDep() {
		return tripDep;
	}
	public void setTripDep(Double tripDep) {
		this.tripDep = tripDep;
	}
	public Double getRate7() {
		return rate7;
	}
	public void setRate7(Double rate7) {
		this.rate7 = rate7;
	}
	public Double getCreditOpen() {
		return creditOpen;
	}
	public void setCreditOpen(Double creditOpen) {
		this.creditOpen = creditOpen;
	}
	public Double getRate8() {
		return rate8;
	}
	public void setRate8(Double rate8) {
		this.rate8 = rate8;
	}
	public Double getPrfM1() {
		return prfM1;
	}
	public void setPrfM1(Double prfM1) {
		this.prfM1 = prfM1;
	}
	public Double getPrfY1() {
		return prfY1;
	}
	public void setPrfY1(Double prfY1) {
		this.prfY1 = prfY1;
	}
	public Double getIncomeLev() {
		return incomeLev;
	}
	public void setIncomeLev(Double incomeLev) {
		this.incomeLev = incomeLev;
	}
	public Double getPrfM2() {
		return prfM2;
	}
	public void setPrfM2(Double prfM2) {
		this.prfM2 = prfM2;
	}
	public Double getPrfY2() {
		return prfY2;
	}
	public void setPrfY2(Double prfY2) {
		this.prfY2 = prfY2;
	}
	public Double getPriceLev() {
		return priceLev;
	}
	public void setPriceLev(Double priceLev) {
		this.priceLev = priceLev;
	}
	public Double getPrfM3() {
		return prfM3;
	}
	public void setPrfM3(Double prfM3) {
		this.prfM3 = prfM3;
	}
	public Double getPrfY3() {
		return prfY3;
	}
	public void setPrfY3(Double prfY3) {
		this.prfY3 = prfY3;
	}
	public Double getProfitLev() {
		return profitLev;
	}
	public void setProfitLev(Double profitLev) {
		this.profitLev = profitLev;
	}
	public Integer getReplyCnt() {
		return replyCnt;
	}
	public void setReplyCnt(Integer replyCnt) {
		this.replyCnt = replyCnt;
	}
	public Double getReplyAmt() {
		return replyAmt;
	}
	public void setReplyAmt(Double replyAmt) {
		this.replyAmt = replyAmt;
	}
	public Double getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(Double loanAmt) {
		this.loanAmt = loanAmt;
	}
	public Integer getAwdCnt() {
		return awdCnt;
	}
	public void setAwdCnt(Integer awdCnt) {
		this.awdCnt = awdCnt;
	}
	public Double getAwdLimit() {
		return awdLimit;
	}
	public void setAwdLimit(Double awdLimit) {
		this.awdLimit = awdLimit;
	}
	public Double getLoan() {
		return loan;
	}
	public void setLoan(Double loan) {
		this.loan = loan;
	}
	public Double getLoanReplyRate() {
		return loanReplyRate;
	}
	public void setLoanReplyRate(Double loanReplyRate) {
		this.loanReplyRate = loanReplyRate;
	}
	public Integer getStoreCnt() {
		return storeCnt;
	}
	public void setStoreCnt(Integer storeCnt) {
		this.storeCnt = storeCnt;
	}
	public Double getSurLimit() {
		return surLimit;
	}
	public void setSurLimit(Double surLimit) {
		this.surLimit = surLimit;
	}
	public Double getSurLoan() {
		return surLoan;
	}
	public void setSurLoan(Double surLoan) {
		this.surLoan = surLoan;
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
	public String getUnitId2() {
		return unitId2;
	}
	public void setUnitId2(String unitId2) {
		this.unitId2 = unitId2;
	}
	public String getUnitName2() {
		return unitName2;
	}
	public void setUnitName2(String unitName2) {
		this.unitName2 = unitName2;
	}
	public Double getBal1() {
		return bal1;
	}
	public void setBal1(Double bal1) {
		this.bal1 = bal1;
	}
	public Double getThanMBal1() {
		return thanMBal1;
	}
	public void setThanMBal1(Double thanMBal1) {
		this.thanMBal1 = thanMBal1;
	}
	public Double getThanYBal1() {
		return thanYBal1;
	}
	public void setThanYBal1(Double thanYBal1) {
		this.thanYBal1 = thanYBal1;
	}
	
	public Double getAccount1() {
		return account1;
	}
	public void setAccount1(Double account1) {
		this.account1 = account1;
	}
	public Double getRate1() {
		return rate1;
	}
	public void setRate1(Double rate1) {
		this.rate1 = rate1;
	}
	public Double getBal2() {
		return bal2;
	}
	public void setBal2(Double bal2) {
		this.bal2 = bal2;
	}
	public Double getThanMBal2() {
		return thanMBal2;
	}
	public void setThanMBal2(Double thanMBal2) {
		this.thanMBal2 = thanMBal2;
	}
	public Double getThanYBal2() {
		return thanYBal2;
	}
	public void setThanYBal2(Double thanYBal2) {
		this.thanYBal2 = thanYBal2;
	}
	
	public Integer getCusCnt() {
		return cusCnt;
	}
	public void setCusCnt(Integer cusCnt) {
		this.cusCnt = cusCnt;
	}
	public Double getRate2() {
		return rate2;
	}
	public void setRate2(Double rate2) {
		this.rate2 = rate2;
	}
	public Double getThanDBal1() {
		return thanDBal1;
	}
	public void setThanDBal1(Double thanDBal1) {
		this.thanDBal1 = thanDBal1;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public Double getCntD1() {
		return cntD1;
	}
	public void setCntD1(Double cntD1) {
		this.cntD1 = cntD1;
	}
	public Double getCntM1() {
		return cntM1;
	}
	public void setCntM1(Double cntM1) {
		this.cntM1 = cntM1;
	}
	public Double getCntY1() {
		return cntY1;
	}
	public void setCntY1(Double cntY1) {
		this.cntY1 = cntY1;
	}
	public Double getCntD2() {
		return cntD2;
	}
	public void setCntD2(Double cntD2) {
		this.cntD2 = cntD2;
	}
	public Double getCntM2() {
		return cntM2;
	}
	public void setCntM2(Double cntM2) {
		this.cntM2 = cntM2;
	}
	public Double getCntY2() {
		return cntY2;
	}
	public void setCntY2(Double cntY2) {
		this.cntY2 = cntY2;
	}
	public String getMisc() {
		return misc;
	}
	public void setMisc(String misc) {
		this.misc = misc;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public Double getBal() {
		return bal;
	}
	public void setBal(Double bal) {
		this.bal = bal;
	}
	public Double getThanDBal() {
		return thanDBal;
	}
	public void setThanDBal(Double thanDBal) {
		this.thanDBal = thanDBal;
	}
	public Double getThanMBal() {
		return thanMBal;
	}
	public void setThanMBal(Double thanMBal) {
		this.thanMBal = thanMBal;
	}
	public Double getThanYBal() {
		return thanYBal;
	}
	public void setThanYBal(Double thanYBal) {
		this.thanYBal = thanYBal;
	}

	public Double getTargetY() {
		return targetY;
	}
	public void setTargetY(Double targetY) {
		this.targetY = targetY;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Double getThanRate() {
		return thanRate;
	}
	public void setThanRate(Double thanRate) {
		this.thanRate = thanRate;
	}
	public Double getRate3() {
		return rate3;
	}
	public void setRate3(Double rate3) {
		this.rate3 = rate3;
	}
	public String getTargetY1() {
		return targetY1;
	}
	public void setTargetY1(String targetY1) {
		this.targetY1 = targetY1;
	}
	public String getTargetY2() {
		return targetY2;
	}
	public void setTargetY2(String targetY2) {
		this.targetY2 = targetY2;
	}
	public String getTargetY3() {
		return targetY3;
	}
	public void setTargetY3(String targetY3) {
		this.targetY3 = targetY3;
	}
	public Double getThanRate1() {
		return thanRate1;
	}
	public void setThanRate1(Double thanRate1) {
		this.thanRate1 = thanRate1;
	}
	public Double getThanRate2() {
		return thanRate2;
	}
	public void setThanRate2(Double thanRate2) {
		this.thanRate2 = thanRate2;
	}
	public Double getThanRate3() {
		return thanRate3;
	}
	public void setThanRate3(Double thanRate3) {
		this.thanRate3 = thanRate3;
	}
	
	public Double getYbalAvg() {
		return ybalAvg;
	}
	public void setYbalAvg(Double ybalAvg) {
		this.ybalAvg = ybalAvg;
	}
	public Double getThanMAvgBal() {
		return thanMAvgBal;
	}
	public void setThanMAvgBal(Double thanMAvgBal) {
		this.thanMAvgBal = thanMAvgBal;
	}
	public Double getThanYAvgBal() {
		return thanYAvgBal;
	}
	public void setThanYAvgBal(Double thanYAvgBal) {
		this.thanYAvgBal = thanYAvgBal;
	}
	public Double getSumBal() {
		return sumBal;
	}
	public void setSumBal(Double sumBal) {
		this.sumBal = sumBal;
	}
	public Double getSumMBal() {
		return sumMBal;
	}
	public void setSumMBal(Double sumMBal) {
		this.sumMBal = sumMBal;
	}
	public Double getSumYBal() {
		return sumYBal;
	}
	public void setSumYBal(Double sumYBal) {
		this.sumYBal = sumYBal;
	}
	public Integer getPlainCnt() {
		return plainCnt;
	}
	public void setPlainCnt(Integer plainCnt) {
		this.plainCnt = plainCnt;
	}
	public Integer getEngageCnt() {
		return engageCnt;
	}
	public void setEngageCnt(Integer engageCnt) {
		this.engageCnt = engageCnt;
	}
	public Double getEngageRate() {
		return engageRate;
	}
	public void setEngageRate(Double engageRate) {
		this.engageRate = engageRate;
	}
	public Integer getInitiatorCnt() {
		return initiatorCnt;
	}
	public void setInitiatorCnt(Integer initiatorCnt) {
		this.initiatorCnt = initiatorCnt;
	}
	public Double getIceBreakRate() {
		return iceBreakRate;
	}
	public void setIceBreakRate(Double iceBreakRate) {
		this.iceBreakRate = iceBreakRate;
	}
	public Double getLoanRate1() {
		return loanRate1;
	}
	public void setLoanRate1(Double loanRate1) {
		this.loanRate1 = loanRate1;
	}

	public Double getLoanRate2() {
		return loanRate2;
	}
	public void setLoanRate2(Double loanRate2) {
		this.loanRate2 = loanRate2;
	}
	public Double getLoanLosses() {
		return loanLosses;
	}
	public void setLoanLosses(Double loanLosses) {
		this.loanLosses = loanLosses;
	}
	public Integer getCnt1() {
		return cnt1;
	}
	public void setCnt1(Integer cnt1) {
		this.cnt1 = cnt1;
	}
	public Integer getCnt2() {
		return cnt2;
	}
	public void setCnt2(Integer cnt2) {
		this.cnt2 = cnt2;
	}
	public Double getAvgBal1() {
		return avgBal1;
	}
	public void setAvgBal1(Double avgBal1) {
		this.avgBal1 = avgBal1;
	}
	public Double getAvgBal2() {
		return avgBal2;
	}
	public void setAvgBal2(Double avgBal2) {
		this.avgBal2 = avgBal2;
	}
	public Double getThanAvgBal1() {
		return thanAvgBal1;
	}
	public void setThanAvgBal1(Double thanAvgBal1) {
		this.thanAvgBal1 = thanAvgBal1;
	}
	public Double getThanAvgBal2() {
		return thanAvgBal2;
	}
	public void setThanAvgBal2(Double thanAvgBal2) {
		this.thanAvgBal2 = thanAvgBal2;
	}
    public Integer getCnt() {
        return cnt;
    }
    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }
    public Integer getCntM() {
        return cntM;
    }
    public void setCntM(Integer cntM) {
        this.cntM = cntM;
    }
    public Integer getCntY() {
        return cntY;
    }
    public void setCntY(Integer cntY) {
        this.cntY = cntY;
    }
    public String getTargetY4() {
        return targetY4;
    }
    public void setTargetY4(String targetY4) {
        this.targetY4 = targetY4;
    }
    public Double getThanRate4() {
        return thanRate4;
    }
    public void setThanRate4(Double thanRate4) {
        this.thanRate4 = thanRate4;
    }
    
}