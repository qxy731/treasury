package com.soule.app.report.po;

import java.io.Serializable;
import java.util.Date;



/**
 * 对应表的类(电子银行)
 */
public class SignCustInfoPo implements Serializable {

    private static final long serialVersionUID = 1L;
    


    	
	/**
     * 网银机构号
     */
    private String unitId;
    private String bancsNo;
    /**
     * 受理机构名称
     */
    private String unitName;
    /**
     * 网银客户号
     */
    private String custId;
    /**
     * 客户名称
     */
    private String custName;
    /**
     * 市场细分
     */
    private String mksgId;
    /**
     * 签约时间
     */
    private Date signDate;
   
    
    private String acctNo;
    private String acctName;
    private String opName;
    private String userId;
    private String userNo;
    private String bankIdM;
    private String bankNameM;
    private Date loginDate;
    private Integer netCnt;
    private Integer ynetCnt;
    private Integer mnetCnt;
    private Integer dnetCnt;
    private Date bizDate;
    private String orgLvl1;
    private String orgLvl2;
    private String orgLvl3;
    private Integer ynewcusCnt;
    private Integer ynetbankCnt;
    private Double cusCnt;
    private String unitName2;
    private String serType;
    private String id;
    private String name;
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
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getMksgId() {
		return mksgId;
	}
	public void setMksgId(String mksgId) {
		this.mksgId = mksgId;
	}
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	public String getAcctName() {
		return acctName;
	}
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserNo() {
        return userNo;
    }
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
    public String getBankIdM() {
		return bankIdM;
	}
	public void setBankIdM(String bankIdM) {
		this.bankIdM = bankIdM;
	}
	public String getBankNameM() {
		return bankNameM;
	}
	public void setBankNameM(String bankNameM) {
		this.bankNameM = bankNameM;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
	
	public Integer getNetCnt() {
		return netCnt;
	}
	public void setNetCnt(Integer netCnt) {
		this.netCnt = netCnt;
	}
	public Date getBizDate() {
		return bizDate;
	}
	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}
	public String getOrgLvl1() {
		return orgLvl1;
	}
	public void setOrgLvl1(String orgLvl1) {
		this.orgLvl1 = orgLvl1;
	}
	public String getOrgLvl2() {
		return orgLvl2;
	}
	public void setOrgLvl2(String orgLvl2) {
		this.orgLvl2 = orgLvl2;
	}
	public String getOrgLvl3() {
		return orgLvl3;
	}
	public void setOrgLvl3(String orgLvl3) {
		this.orgLvl3 = orgLvl3;
	}
	
	public Integer getYnetCnt() {
		return ynetCnt;
	}
	public void setYnetCnt(Integer ynetCnt) {
		this.ynetCnt = ynetCnt;
	}
	public Integer getMnetCnt() {
		return mnetCnt;
	}
	public void setMnetCnt(Integer mnetCnt) {
		this.mnetCnt = mnetCnt;
	}
	public Integer getDnetCnt() {
		return dnetCnt;
	}
	public void setDnetCnt(Integer dnetCnt) {
		this.dnetCnt = dnetCnt;
	}
	public Integer getYnewcusCnt() {
		return ynewcusCnt;
	}
	public void setYnewcusCnt(Integer ynewcusCnt) {
		this.ynewcusCnt = ynewcusCnt;
	}
	public Integer getYnetbankCnt() {
		return ynetbankCnt;
	}
	public void setYnetbankCnt(Integer ynetbankCnt) {
		this.ynetbankCnt = ynetbankCnt;
	}
	public Double getCusCnt() {
		return cusCnt;
	}
	public void setCusCnt(Double cusCnt) {
		this.cusCnt = cusCnt;
	}
	public String getUnitName2() {
		return unitName2;
	}
	public void setUnitName2(String unitName2) {
		this.unitName2 = unitName2;
	}
	public String getSerType() {
		return serType;
	}
	public void setSerType(String serType) {
		this.serType = serType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBancsNo() {
		return bancsNo;
	}
	public void setBancsNo(String bancsNo) {
		this.bancsNo = bancsNo;
	}
 
}