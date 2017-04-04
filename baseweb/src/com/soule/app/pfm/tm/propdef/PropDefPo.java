package com.soule.app.pfm.tm.propdef;

import java.io.Serializable;
import java.util.Date;

import com.soule.app.pfm.tm.BaseTar;

/**
 * 定性考评指标
 */
public class PropDefPo extends BaseTar implements Serializable {
	private static final long serialVersionUID = 8045071340214481085L;
	
	//指标状态（1：已启用;2：已停用）
	public static String STATUS_DEL = "2";
	public static String STATUS_INPUT = "1";
	// 指标代码
	private String tarCode;
	// 指标名称
	private String tarName;
	// 适用对象
	private String tarScope;
	// 业务线条分类
	private String tarBizType;
	// 指标分类编码
	private String tarSortCode;
	// 指标分类名称
	private String tarSortName;
	// 指标状态
	private String tarStatus=STATUS_INPUT;
	
	private String statusName;
	// 指标说明
	private String tarDesc;
	// 杂项
	private String misc;
	// 备注
	private String remark;
	// 创建人
	private String createUser;
	// 创建时间
	private Date createTime;
	// 创建机构
	private String createOrg;
	// 修改人
	private String lastUpdUser;
	// 修改机构
	private String lastUpdOrg;
	// 修改时间
	private Date lastUpdTime;

	private String unitName;
	 public PropDefPo() {

	}

	public String getTarCode() {
		return tarCode;
	}

	public void setTarCode(String tarCode) {
		this.tarCode = tarCode;
	}

	public String getTarName() {
		return tarName;
	}

	public void setTarName(String tarName) {
		this.tarName = tarName;
	}

	public String getTarScope() {
		return tarScope;
	}

	public void setTarScope(String tarScope) {
		this.tarScope = tarScope;
	}

	public String getTarBizType() {
		return tarBizType;
	}

	public void setTarBizType(String tarBizType) {
		this.tarBizType = tarBizType;
	}

	public String getTarSortCode() {
		return tarSortCode;
	}

	public void setTarSortCode(String tarSortCode) {
		this.tarSortCode = tarSortCode;
	}

	public String getTarSortName() {
		return tarSortName;
	}

	public void setTarSortName(String tarSortName) {
		this.tarSortName = tarSortName;
	}

	public String getTarStatus() {
		return tarStatus;
	}

	public void setTarStatus(String tarStatus) {
		if (STATUS_INPUT.equals(tarStatus)) {
			statusName = "已录入";
		} else if (STATUS_DEL.equals(tarStatus)) {
			statusName = "已删除";
		}
		this.tarStatus = tarStatus;
	}

	public String getTarDesc() {
		return tarDesc;
	}

	public void setTarDesc(String tarDesc) {
		this.tarDesc = tarDesc;
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

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}