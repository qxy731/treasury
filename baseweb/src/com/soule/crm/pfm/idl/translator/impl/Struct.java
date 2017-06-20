package com.soule.crm.pfm.idl.translator.impl;

public class Struct {
	//id 存放 指标代码或参数代码
	private String id;
	//指标
	private Integer period;
	//类型, 参数类型，指标类型
	private int type;
	//名称, 参数和指标别名
	private String alias;
	//参数查询
	private String dslSql;
	//是否为指标区间合计
	private boolean periodRangeFlag = false;
	
	public Struct() {
		
	}
	public boolean getPeriodRangeFlag() {
		return this.periodRangeFlag;
	}
	public void setPeriodRangeFlag(boolean b) {
		this.periodRangeFlag = b;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getDslSql() {
		return dslSql;
	}
	public void setDslSql(String dslSql) {
		this.dslSql = dslSql;
	}
	
	public static void main(String[] args) {

	}

}
