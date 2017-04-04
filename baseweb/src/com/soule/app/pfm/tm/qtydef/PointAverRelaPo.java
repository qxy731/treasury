package com.soule.app.pfm.tm.qtydef;

import java.io.Serializable;
/**
 * 时点指标关联日均指标
 * @author Administrator
 *
 */
public class PointAverRelaPo implements Serializable{
	private static final long serialVersionUID = -6104426122169199798L;
	//时点指标代码
	private String ptarCode;
	//日均指标代码
	private String atarCode;
	//日均范围
	private String dayScope;
	public String getPtarCode() {
		return ptarCode;
	}
	public void setPtarCode(String ptarCode) {
		this.ptarCode = ptarCode;
	}
	public String getAtarCode() {
		return atarCode;
	}
	public void setAtarCode(String atarCode) {
		this.atarCode = atarCode;
	}
	public String getDayScope() {
		return dayScope;
	}
	public void setDayScope(String dayScope) {
		this.dayScope = dayScope;
	}
}
