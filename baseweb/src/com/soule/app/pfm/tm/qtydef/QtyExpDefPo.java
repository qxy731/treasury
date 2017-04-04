package com.soule.app.pfm.tm.qtydef;

import java.io.Serializable;
import java.util.Date;
//复合指标定义表
public class QtyExpDefPo implements Serializable{
	private static final long serialVersionUID = -3886613942207149703L;
	//指标代码
	private String tarCode;
	//适用对象
	private String tarScope;
	//计算公式
	private String calcExp;
	//sql内部表示	
	private String sqlExp;
	//杂项	
	private String misc;
	//备注	
	private String remark;
	private String createUser;
	//创建时间	
	private Date createTime;
	//创建机构	
	private String createOrg;
	//修改人	
	private String lastUpdUser;
	//修改时间	
	private Date lastUpdTime;
	//修改机构	
	private String lastUpdOrg;
	public String getTarCode() {
		return tarCode;
	}
	public void setTarCode(String tarCode) {
		this.tarCode = tarCode;
	}
	public String getTarScope() {
		return tarScope;
	}
	public void setTarScope(String tarScope) {
		this.tarScope = tarScope;
	}
	public String getCalcExp() {
		return calcExp;
	}
	public void setCalcExp(String calcExp) {
		this.calcExp = calcExp;
	}
	public String getSqlExp() {
		return sqlExp;
	}
	public void setSqlExp(String sqlExp) {
		this.sqlExp = sqlExp;
	}
	public String getMisc() {
		return misc;
	}
	public void setMisc(String misc) {
		this.misc = misc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateOrg() {
		return createOrg;
	}
	public void setCreateOrg(String createOrg) {
		this.createOrg = createOrg;
	}
	public String getLastUpdUser() {
		return lastUpdUser;
	}
	public void setLastUpdUser(String lastUpdUser) {
		this.lastUpdUser = lastUpdUser;
	}
	public Date getLastUpdTime() {
		return lastUpdTime;
	}
	public void setLastUpdTime(Date lastUpdTime) {
		this.lastUpdTime = lastUpdTime;
	}
	public String getLastUpdOrg() {
		return lastUpdOrg;
	}
	public void setLastUpdOrg(String lastUpdOrg) {
		this.lastUpdOrg = lastUpdOrg;
	}

}