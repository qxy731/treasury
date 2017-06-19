package com.soule.app.report.po;

import java.io.Serializable;
import java.util.Date;



/**
 * 对应表的类
 */
public class RptMktPo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String rank;
    private String bankId;
    private String unitName;
    private String staffName;
    private String priJob;
    private String mktCusCnt;
    private String mktCnt;
    private String mktActCnt;
    private String mktPasCnt;
    private Double mktAvgCnt;
    private String remark1;
    private String dim;
    private String project;
    private String target;
    private Double actualValue;
    private String rankong1;
    private String rankong2;
    private Double total;
    
    private String orgCnt;
    private String orgViewCnt;
    private String viewCnt;
    private Double viewAvgCnt;
    private Double viewCov;
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getPriJob() {
		return priJob;
	}
	public void setPriJob(String priJob) {
		this.priJob = priJob;
	}
	public String getMktCusCnt() {
		return mktCusCnt;
	}
	public void setMktCusCnt(String mktCusCnt) {
		this.mktCusCnt = mktCusCnt;
	}
	public String getMktCnt() {
		return mktCnt;
	}
	public void setMktCnt(String mktCnt) {
		this.mktCnt = mktCnt;
	}
	public String getMktActCnt() {
		return mktActCnt;
	}
	public void setMktActCnt(String mktActCnt) {
		this.mktActCnt = mktActCnt;
	}
	public String getMktPasCnt() {
		return mktPasCnt;
	}
	public void setMktPasCnt(String mktPasCnt) {
		this.mktPasCnt = mktPasCnt;
	}
	public Double getMktAvgCnt() {
		return mktAvgCnt;
	}
	public void setMktAvgCnt(Double mktAvgCnt) {
		this.mktAvgCnt = mktAvgCnt;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getOrgCnt() {
		return orgCnt;
	}
	public void setOrgCnt(String orgCnt) {
		this.orgCnt = orgCnt;
	}
	public String getOrgViewCnt() {
		return orgViewCnt;
	}
	public void setOrgViewCnt(String orgViewCnt) {
		this.orgViewCnt = orgViewCnt;
	}
	public String getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(String viewCnt) {
		this.viewCnt = viewCnt;
	}
	public Double getViewAvgCnt() {
		return viewAvgCnt;
	}
	public void setViewAvgCnt(Double viewAvgCnt) {
		this.viewAvgCnt = viewAvgCnt;
	}
	public Double getViewCov() {
		return viewCov;
	}
	public void setViewCov(Double viewCov) {
		this.viewCov = viewCov;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getDim() {
		return dim;
	}
	public void setDim(String dim) {
		this.dim = dim;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Double getActualValue() {
		return actualValue;
	}
	public void setActualValue(Double actualValue) {
		this.actualValue = actualValue;
	}
	public String getRankong1() {
		return rankong1;
	}
	public void setRankong1(String rankong1) {
		this.rankong1 = rankong1;
	}
	public String getRankong2() {
		return rankong2;
	}
	public void setRankong2(String rankong2) {
		this.rankong2 = rankong2;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
    
}