package com.soule.app.report.po;

import java.io.Serializable;
import java.util.Date;

public class CusStatOrgUp implements Serializable{

	private static final long serialVersionUID = -4919041570736573451L;

	private String unitId;
	
	private String unitName;
	
	private Date bizDate;
	
	private Integer tranCusCnt;
	
	private Integer tranActCnt;
	
	private Integer tranOpCnt;
	
	private Integer qyCusCnt;
	
	private Integer qyActCnt;
	
	private Integer qyOpCnt;
	
	private Integer exitCnt1;
	
	private Integer exitCnt2;
	
	private Integer tranCnt;

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

	public Date getBizDate() {
    	return bizDate;
    }

	public void setBizDate(Date bizDate) {
    	this.bizDate = bizDate;
    }

	public Integer getTranCusCnt() {
    	return tranCusCnt;
    }

	public void setTranCusCnt(Integer tranCusCnt) {
    	this.tranCusCnt = tranCusCnt;
    }

	public Integer getTranActCnt() {
    	return tranActCnt;
    }

	public void setTranActCnt(Integer tranActCnt) {
    	this.tranActCnt = tranActCnt;
    }

	public Integer getTranOpCnt() {
    	return tranOpCnt;
    }

	public void setTranOpCnt(Integer tranOpCnt) {
    	this.tranOpCnt = tranOpCnt;
    }

	public Integer getQyCusCnt() {
    	return qyCusCnt;
    }

	public void setQyCusCnt(Integer qyCusCnt) {
    	this.qyCusCnt = qyCusCnt;
    }

	public Integer getQyActCnt() {
    	return qyActCnt;
    }

	public void setQyActCnt(Integer qyActCnt) {
    	this.qyActCnt = qyActCnt;
    }

	public Integer getQyOpCnt() {
    	return qyOpCnt;
    }

	public void setQyOpCnt(Integer qyOpCnt) {
    	this.qyOpCnt = qyOpCnt;
    }

	public Integer getExitCnt1() {
    	return exitCnt1;
    }

	public void setExitCnt1(Integer exitCnt1) {
    	this.exitCnt1 = exitCnt1;
    }

	public Integer getExitCnt2() {
    	return exitCnt2;
    }

	public void setExitCnt2(Integer exitCnt2) {
    	this.exitCnt2 = exitCnt2;
    }

	public Integer getTranCnt() {
    	return tranCnt;
    }

	public void setTranCnt(Integer tranCnt) {
    	this.tranCnt = tranCnt;
    }
}
