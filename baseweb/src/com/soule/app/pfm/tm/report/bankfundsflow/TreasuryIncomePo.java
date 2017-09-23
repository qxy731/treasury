package com.soule.app.pfm.tm.report.bankfundsflow;

import java.math.BigDecimal;

public class TreasuryIncomePo {
	//数据日期
	private String tarDate;
	//科目代码
	private String subCode;
	//科目名称
	private String subName;
	//中央级发生金额
	private BigDecimal amtMonthC;
	//中央级累计金额
	private BigDecimal amtYearC;
	//中央级增长率
	private BigDecimal annualGrowthRateC;
	//地方级发生金额
	private BigDecimal amtMonthL;
	//地方级累计金额
	private BigDecimal amtYearL;
	//地方级增长率
	private BigDecimal annualGrowthRateL;
	//合计数累计金额
	private BigDecimal amtMonthT;
	//
	private BigDecimal amtYearT;
	//合计数增长率
	private BigDecimal annualGrowthRateT;
	//更新时间
	private java.util.Date uploadTime;
	//批次号
	private String batchId;
	//备用字段1
	private String ext1;
	//备用字段2
	private String ext2;
	//备用字段3
	private String ext3;
	//备用字段4
	private String ext4;
	public String getTarDate() {
		return tarDate;
	}
	public void setTarDate(String tarDate) {
		this.tarDate = tarDate;
	}
	public String getSubCode() {
		return subCode;
	}
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	
	public BigDecimal getAmtMonthC() {
		return amtMonthC;
	}
	public void setAmtMonthC(BigDecimal amtMonthC) {
		this.amtMonthC = amtMonthC;
	}
	public BigDecimal getAmtYearC() {
		return amtYearC;
	}
	public void setAmtYearC(BigDecimal amtYearC) {
		this.amtYearC = amtYearC;
	}
	public BigDecimal getAnnualGrowthRateC() {
		return annualGrowthRateC;
	}
	public void setAnnualGrowthRateC(BigDecimal annualGrowthRateC) {
		this.annualGrowthRateC = annualGrowthRateC;
	}
	public BigDecimal getAmtMonthL() {
		return amtMonthL;
	}
	public void setAmtMonthL(BigDecimal amtMonthL) {
		this.amtMonthL = amtMonthL;
	}
	public BigDecimal getAmtYearL() {
		return amtYearL;
	}
	public void setAmtYearL(BigDecimal amtYearL) {
		this.amtYearL = amtYearL;
	}
	public BigDecimal getAnnualGrowthRateL() {
		return annualGrowthRateL;
	}
	public void setAnnualGrowthRateL(BigDecimal annualGrowthRateL) {
		this.annualGrowthRateL = annualGrowthRateL;
	}
	public BigDecimal getAmtMonthT() {
		return amtMonthT;
	}
	public void setAmtMonthT(BigDecimal amtMonthT) {
		this.amtMonthT = amtMonthT;
	}
	public BigDecimal getAmtYearT() {
		return amtYearT;
	}
	public void setAmtYearT(BigDecimal amtYearT) {
		this.amtYearT = amtYearT;
	}
	public BigDecimal getAnnualGrowthRateT() {
		return annualGrowthRateT;
	}
	public void setAnnualGrowthRateT(BigDecimal annualGrowthRateT) {
		this.annualGrowthRateT = annualGrowthRateT;
	}
	public java.util.Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(java.util.Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	public String getExt3() {
		return ext3;
	}
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	public String getExt4() {
		return ext4;
	}
	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}
	
	

}
