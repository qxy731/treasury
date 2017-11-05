package com.soule.app.pfm.tm.model.po;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

public class ModelDefPo {
	
	private String modelCode;
	private String modelName;
	private String modelStatus;
	private String modelDesc;
	private String createUser;
	private String createOrg;
	private Date createTime;
	private String lastUpdUser;
	private String  lastUpdOrg;
	private Date lastUpdTime;
	private String createOrgName;
	public String getModelCode() {
		return modelCode;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelStatus() {
		return modelStatus;
	}
	public void setModelStatus(String modelStatus) {
		this.modelStatus = modelStatus;
	}
	public String getModelDesc() {
		return modelDesc;
	}
	public void setModelDesc(String modelDesc) {
		this.modelDesc = modelDesc;
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
	
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getLastUpdUser() {
		return lastUpdUser;
	}
	public void setLastUpdUser(String lastUpdUser) {
		this.lastUpdUser = lastUpdUser;
	}
	public String getLastUpdOrg() {
		return lastUpdOrg;
	}
	public void setLastUpdOrg(String lastUpdOrg) {
		this.lastUpdOrg = lastUpdOrg;
	}
	public Date getLastUpdTime() {
		return lastUpdTime;
	}
	public void setLastUpdTime(Date lastUpdTime) {
		this.lastUpdTime = lastUpdTime;
	}
	public String getCreateOrgName() {
		return createOrgName;
	}
	public void setCreateOrgName(String createOrgName) {
		this.createOrgName = createOrgName;
	}

}
