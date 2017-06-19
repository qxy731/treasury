package com.soule.app.report.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 对应表的类
 */
public class SetdepPo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String custNo;
    private String custName;
    private String custType;
    private String induCode;
    private String busiKind;
    private String unitName2;
    private String unitId2;
    private String unitName;
    private String unitCode;
    private String grntNo;
    private String grntName;
    private Double grntBal1 ;
	private Double grntBal2 ;
	private Date reportDate ;
    private String orgNo;
    private String staffName;
    private String prdCode;
    private String prdName;
    private String flag;
    private Integer debitNum;
    private Double debitAmt;
    private Integer loanNum;
    private Double loanAmt;
    private Double stanCost;
    private Double stanProf;
    private Double numM;
    private String openCusCnt;
    private String totalCusCnt;
    private String cry;
    private Double rate;
    private String code6;
    private String code5;
    private String code4;
    private String code3;
    private String lever6;
    private String lever5;
    private String lever4;
    private String lever3;
    private Integer openPrdCnt;
//    private Integer totalCusCnt;
    private Integer cnt;
    private String rank;
    
    
    /**
     * 还款管理
     */
    private Date bizDate;
    private String custId;
    private String tel;
    private String proId;
    private String loanType;
    private String bizType;
    private String preCry;
    private String proStatus;
    private String praCry;
    private Double yearRate;
    private String questFlag;
    private String createUser;
    private String createOrg;
    private String lastUpUser;
    private String lastUpOrg;
    private String misc;
    private String ftLoanType;
    private String bankFlag;
    private Date preDate;
    private Date ratDate;
    private Date praDate;
    private Date createTime;
    private Date lastUpTime;
    private Double preAmt;
    private Double praAmt;
    private Integer loanLim;
    private String status;
    private String remark;
    private String orgCodes;
    private String orgNames;
    
    private Double rmbDpsAmt;
    private Double rmbFinAmt;
    private Double rmbFtuDpsAmt;
    private Double rmbFtuFinAmt;
    private Double rmbAddAmt;
    private Double usdDpsAmt;
    private Double usdFinAmt;
    private Double usdFtuDpsAmt;
    private Double usdFtuFinAmt;
    private Double usdAddAmt;
    private String fillUser;
    private String mobile;
    
    private Double rmbBot;
    private Double rmbTop;
    private String rmbAddAmtStr;
    private String rmbLastAmt;
    private Double rmbAddAmtNew;
    private Double usdBot;
    private Double usdTop;
    private String usdAddAmtStr;
    private String usdLastAmt;
    private Double usdAddAmtNew;
    
    public String getOrgNames() {
		return orgNames;
	}
	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}
	public String getOrgCodes() {
		return orgCodes;
	}
	public void setOrgCodes(String orgCodes) {
		this.orgCodes = orgCodes;
	}
	private String preAmtUsd;
    
    private String praAmtUsd;
    
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getGrntNo() {
		return grntNo;
	}
	public void setGrntNo(String grntNo) {
		this.grntNo = grntNo;
	}
	public String getGrntName() {
		return grntName;
	}
	public void setGrntName(String grntName) {
		this.grntName = grntName;
	}
	public Double getGrntBal1() {
		return grntBal1;
	}
	public void setGrntBal1(Double grntBal1) {
		this.grntBal1 = grntBal1;
	}
	public Double getGrntBal2() {
		return grntBal2;
	}
	public void setGrntBal2(Double grntBal2) {
		this.grntBal2 = grntBal2;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public Integer getOpenPrdCnt() {
		return openPrdCnt;
	}
	public void setOpenPrdCnt(Integer openPrdCnt) {
		this.openPrdCnt = openPrdCnt;
	}
	public String getRmbAddAmtStr() {
        return rmbAddAmtStr;
    }
    public void setRmbAddAmtStr(String rmbAddAmtStr) {
        this.rmbAddAmtStr = rmbAddAmtStr;
    }
    public String getUsdAddAmtStr() {
        return usdAddAmtStr;
    }
    public void setUsdAddAmtStr(String usdAddAmtStr) {
        this.usdAddAmtStr = usdAddAmtStr;
    }
    public Double getRmbBot() {
        return rmbBot;
    }
    public void setRmbBot(Double rmbBot) {
        this.rmbBot = rmbBot;
    }
    public Double getRmbTop() {
        return rmbTop;
    }
    public void setRmbTop(Double rmbTop) {
        this.rmbTop = rmbTop;
    }
    public String getRmbLastAmt() {
        return rmbLastAmt;
    }
    public void setRmbLastAmt(String rmbLastAmt) {
        this.rmbLastAmt = rmbLastAmt;
    }
    public Double getRmbAddAmtNew() {
        return rmbAddAmtNew;
    }
    public void setRmbAddAmtNew(Double rmbAddAmtNew) {
        this.rmbAddAmtNew = rmbAddAmtNew;
    }
    public Double getUsdBot() {
        return usdBot;
    }
    public void setUsdBot(Double usdBot) {
        this.usdBot = usdBot;
    }
    public Double getUsdTop() {
        return usdTop;
    }
    public void setUsdTop(Double usdTop) {
        this.usdTop = usdTop;
    }
    public String getUsdLastAmt() {
        return usdLastAmt;
    }
    public void setUsdLastAmt(String usdLastAmt) {
        this.usdLastAmt = usdLastAmt;
    }
    public Double getUsdAddAmtNew() {
        return usdAddAmtNew;
    }
    public void setUsdAddAmtNew(Double usdAddAmtNew) {
        this.usdAddAmtNew = usdAddAmtNew;
    }
    public Integer getCnt() {
		return cnt;
	}
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	public Date getBizDate() {
        return bizDate;
    }
    public void setBizDate(Date bizDate) {
        this.bizDate = bizDate;
    }
    public String getCustId() {
        return custId;
    }
    public void setCustId(String custId) {
        this.custId = custId;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
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
	public String getUnitName2() {
		return unitName2;
	}
	public void setUnitName2(String unitName2) {
		this.unitName2 = unitName2;
	}
	public String getUnitId2() {
		return unitId2;
	}
	public void setUnitId2(String unitId2) {
		this.unitId2 = unitId2;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getOrgNo() {
		return orgNo;
	}
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getProId() {
        return proId;
    }
    public void setProId(String proId) {
        this.proId = proId;
    }
    public String getLoanType() {
        return loanType;
    }
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }
    public String getBizType() {
        return bizType;
    }
    public void setBizType(String bizType) {
        this.bizType = bizType;
    }
    public String getPreCry() {
        return preCry;
    }
    public void setPreCry(String preCry) {
        this.preCry = preCry;
    }
    public String getProStatus() {
        return proStatus;
    }
    public void setProStatus(String proStatus) {
        this.proStatus = proStatus;
    }
    public String getPraCry() {
        return praCry;
    }
    public void setPraCry(String praCry) {
        this.praCry = praCry;
    }
    public Double getYearRate() {
        return yearRate;
    }
    public void setYearRate(Double yearRate) {
        this.yearRate = yearRate;
    }
    public String getQuestFlag() {
        return questFlag;
    }
    public void setQuestFlag(String questFlag) {
        this.questFlag = questFlag;
    }
    public String getCreateUser() {
        return createUser;
    }
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    public String getCreateOrg() {
        return createOrg;
    }
    public void setCreateOrg(String createOrg) {
        this.createOrg = createOrg;
    }
    public String getLastUpUser() {
        return lastUpUser;
    }
    public void setLastUpUser(String lastUpUser) {
        this.lastUpUser = lastUpUser;
    }
    public String getLastUpOrg() {
        return lastUpOrg;
    }
    public void setLastUpOrg(String lastUpOrg) {
        this.lastUpOrg = lastUpOrg;
    }
    public String getMisc() {
        return misc;
    }
    public void setMisc(String misc) {
        this.misc = misc;
    }
    public String getFtLoanType() {
        return ftLoanType;
    }
    public void setFtLoanType(String ftLoanType) {
        this.ftLoanType = ftLoanType;
    }
    public String getBankFlag() {
        return bankFlag;
    }
    public void setBankFlag(String bankFlag) {
        this.bankFlag = bankFlag;
    }
    public Date getPreDate() {
        return preDate;
    }
    public void setPreDate(Date preDate) {
        this.preDate = preDate;
    }
    public Date getRatDate() {
        return ratDate;
    }
    public String getPreAmtUsd() {
        return preAmtUsd;
    }
    public void setPreAmtUsd(String preAmtUsd) {
        this.preAmtUsd = preAmtUsd;
    }
    public String getPraAmtUsd() {
        return praAmtUsd;
    }
    public void setPraAmtUsd(String praAmtUsd) {
        this.praAmtUsd = praAmtUsd;
    }
    public void setRatDate(Date ratDate) {
        this.ratDate = ratDate;
    }
    public Date getPraDate() {
        return praDate;
    }
    public void setPraDate(Date praDate) {
        this.praDate = praDate;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getLastUpTime() {
        return lastUpTime;
    }
    public void setLastUpTime(Date lastUpTime) {
        this.lastUpTime = lastUpTime;
    }
    public Double getPreAmt() {
        return preAmt;
    }
    public void setPreAmt(Double preAmt) {
        this.preAmt = preAmt;
    }
    public Double getPraAmt() {
        return praAmt;
    }
    public void setPraAmt(Double praAmt) {
        this.praAmt = praAmt;
    }
    public Integer getLoanLim() {
        return loanLim;
    }
    public void setLoanLim(Integer loanLim) {
        this.loanLim = loanLim;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getPrdCode() {
		return prdCode;
	}
	public void setPrdCode(String prdCode) {
		this.prdCode = prdCode;
	}
	public String getPrdName() {
		return prdName;
	}
	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public Integer getDebitNum() {
        return debitNum;
    }
    public void setDebitNum(Integer debitNum) {
        this.debitNum = debitNum;
    }
    public void setLoanNum(Integer loanNum) {
        this.loanNum = loanNum;
    }
    public Double getDebitAmt() {
		return debitAmt;
	}
    
	public Integer getLoanNum() {
        return loanNum;
    }
    public void setDebitAmt(Double debitAmt) {
        this.debitAmt = debitAmt;
    }
    public Double getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(Double loanAmt) {
		this.loanAmt = loanAmt;
	}
	public Double getStanCost() {
		return stanCost;
	}
	public void setStanCost(Double stanCost) {
		this.stanCost = stanCost;
	}
	public Double getStanProf() {
		return stanProf;
	}
	public void setStanProf(Double stanProf) {
		this.stanProf = stanProf;
	}
	public String getOpenCusCnt() {
		return openCusCnt;
	}
	public void setOpenCusCnt(String openCusCnt) {
		this.openCusCnt = openCusCnt;
	}
	public String getTotalCusCnt() {
		return totalCusCnt;
	}
	public void setTotalCusCnt(String totalCusCnt) {
		this.totalCusCnt = totalCusCnt;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getInduCode() {
		return induCode;
	}
	public void setInduCode(String induCode) {
		this.induCode = induCode;
	}
	public String getBusiKind() {
		return busiKind;
	}
	public void setBusiKind(String busiKind) {
		this.busiKind = busiKind;
	}

	public Double getNumM() {
		return numM;
	}
	public void setNumM(Double numM) {
		this.numM = numM;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getCry() {
		return cry;
	}
	public void setCry(String cry) {
		this.cry = cry;
	}
    public String getCode6() {
        return code6;
    }
    public void setCode6(String code6) {
        this.code6 = code6;
    }
    public String getCode5() {
        return code5;
    }
    public void setCode5(String code5) {
        this.code5 = code5;
    }
    public String getCode4() {
        return code4;
    }
    public void setCode4(String code4) {
        this.code4 = code4;
    }
    public String getCode3() {
        return code3;
    }
    public void setCode3(String code3) {
        this.code3 = code3;
    }
    public String getLever6() {
        return lever6;
    }
    public void setLever6(String lever6) {
        this.lever6 = lever6;
    }
    public String getLever5() {
        return lever5;
    }
    public void setLever5(String lever5) {
        this.lever5 = lever5;
    }
    public String getLever4() {
        return lever4;
    }
    public void setLever4(String lever4) {
        this.lever4 = lever4;
    }
    public String getLever3() {
        return lever3;
    }
    public void setLever3(String lever3) {
        this.lever3 = lever3;
    }
    public Double getRmbDpsAmt() {
        return rmbDpsAmt;
    }
    public void setRmbDpsAmt(Double rmbDpsAmt) {
        this.rmbDpsAmt = rmbDpsAmt;
    }
    public Double getRmbFinAmt() {
        return rmbFinAmt;
    }
    public void setRmbFinAmt(Double rmbFinAmt) {
        this.rmbFinAmt = rmbFinAmt;
    }
    public Double getRmbFtuDpsAmt() {
        return rmbFtuDpsAmt;
    }
    public void setRmbFtuDpsAmt(Double rmbFtuDpsAmt) {
        this.rmbFtuDpsAmt = rmbFtuDpsAmt;
    }
    public Double getRmbFtuFinAmt() {
        return rmbFtuFinAmt;
    }
    public void setRmbFtuFinAmt(Double rmbFtuFinAmt) {
        this.rmbFtuFinAmt = rmbFtuFinAmt;
    }
    public Double getRmbAddAmt() {
        return rmbAddAmt;
    }
    public void setRmbAddAmt(Double rmbAddAmt) {
        this.rmbAddAmt = rmbAddAmt;
    }
    public Double getUsdDpsAmt() {
        return usdDpsAmt;
    }
    public void setUsdDpsAmt(Double usdDpsAmt) {
        this.usdDpsAmt = usdDpsAmt;
    }
    public Double getUsdFinAmt() {
        return usdFinAmt;
    }
    public void setUsdFinAmt(Double usdFinAmt) {
        this.usdFinAmt = usdFinAmt;
    }
    public Double getUsdFtuDpsAmt() {
        return usdFtuDpsAmt;
    }
    public void setUsdFtuDpsAmt(Double usdFtuDpsAmt) {
        this.usdFtuDpsAmt = usdFtuDpsAmt;
    }
    public Double getUsdFtuFinAmt() {
        return usdFtuFinAmt;
    }
    public void setUsdFtuFinAmt(Double usdFtuFinAmt) {
        this.usdFtuFinAmt = usdFtuFinAmt;
    }
    public Double getUsdAddAmt() {
        return usdAddAmt;
    }
    public void setUsdAddAmt(Double usdAddAmt) {
        this.usdAddAmt = usdAddAmt;
    }
    public String getFillUser() {
        return fillUser;
    }
    public void setFillUser(String fillUser) {
        this.fillUser = fillUser;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
   
}