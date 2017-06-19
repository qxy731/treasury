package com.soule.crm.pfm.param.paraminfo;

import java.io.Serializable;
import java.util.Date;

/**
 * 参数传递指标信息查询的类
 */
public class ParaminfoQPo implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 是否存在基数指标
     */
    private String bsFlag ;
    /**
     * 计量单位
     */
    private String metUnit;
    /**
     * 计量精确度
     */
    private String metAccuracy;
    /**
     * 指标类型
     */
    private String tarType;
    /**
     * 指标属性
     */
    private String tarProperty;
    /**
     * 统计范围
     */
    private String startScopeCode;
    /**
     * 处理日期
     */
    private String disposeDate;
    /**
     * 指定处理日期
     */
    private String tarProcDate;
    /**
     * 存储日期
     */
    private String saveDate;
    /**
     * 产品类型
     */
    private String prdTypeCode;
    /**
     * 科目
     */
    private String subjCode;
    /**
     * 指标代码
     */
    private String indexCode;
    
	/**
     * 指标名称
     */
    private String indexName;
    /**
     * 适用对象
     */
    private String suitableObject;
    /**
     * 业务条线分类
     */
    private String busiLine;
    /**
     * 指标分类编码
     */
    private String tarSortCode;
    /**
     * 指标状态
     */
    private String tarStatus;
    /**
     * 指标说明
     */
    private String indexExplain;
    /**
     * 杂项
     */
    private String misc;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建机构
     */
    private String createOrg;
    /**
     * 修改人
     */
    private String lastUpdUser;
    /**
     * 修改机构
     */
    private String lastUpdOrg;
    /**
     * 修改时间
     */
    private Date lastUpdTime;
    /**
     * 日均范围
     */
    private String dayScope;
    
    private String dataSource;
    
    public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getBsFlag() {
		return bsFlag;
	}
	public void setBsFlag(String bsFlag) {
		this.bsFlag = bsFlag;
	}
	
	public String getTarType() {
		return tarType;
	}
	public void setTarType(String tarType) {
		this.tarType = tarType;
	}
	public String getTarProperty() {
		return tarProperty;
	}
	public void setTarProperty(String tarProperty) {
		this.tarProperty = tarProperty;
	}
	public String getStartScopeCode() {
		return startScopeCode;
	}
	public void setStartScopeCode(String startScopeCode) {
		this.startScopeCode = startScopeCode;
	}
	
	public String getTarProcDate() {
		return tarProcDate;
	}
	public void setTarProcDate(String tarProcDate) {
		this.tarProcDate = tarProcDate;
	}

	public String getPrdTypeCode() {
		return prdTypeCode;
	}
	public void setPrdTypeCode(String prdTypeCode) {
		this.prdTypeCode = prdTypeCode;
	}
	public String getSubjCode() {
		return subjCode;
	}
	public void setSubjCode(String subjCode) {
		this.subjCode = subjCode;
	}

	
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	
	public String getTarSortCode() {
		return tarSortCode;
	}
	public void setTarSortCode(String tarSortCode) {
		this.tarSortCode = tarSortCode;
	}
	public String getTarStatus() {
		return tarStatus;
	}
	public void setTarStatus(String tarStatus) {
		this.tarStatus = tarStatus;
	}
	
	public String getMetUnit() {
		return metUnit;
	}
	public void setMetUnit(String metUnit) {
		this.metUnit = metUnit;
	}
	public String getMetAccuracy() {
		return metAccuracy;
	}
	public void setMetAccuracy(String metAccuracy) {
		this.metAccuracy = metAccuracy;
	}
	public String getDisposeDate() {
		return disposeDate;
	}
	public void setDisposeDate(String disposeDate) {
		this.disposeDate = disposeDate;
	}
	public String getSuitableObject() {
		return suitableObject;
	}
	public void setSuitableObject(String suitableObject) {
		this.suitableObject = suitableObject;
	}
	public String getBusiLine() {
		return busiLine;
	}
	public void setBusiLine(String busiLine) {
		this.busiLine = busiLine;
	}
	public String getIndexExplain() {
		return indexExplain;
	}
	public void setIndexExplain(String indexExplain) {
		this.indexExplain = indexExplain;
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
	public String getDayScope() {
		return dayScope;
	}
	public void setDayScope(String dayScope) {
		this.dayScope = dayScope;
	}
	
	public String getSaveDate() {
		return saveDate;
	}
	public void setSaveDate(String saveDate) {
		this.saveDate = saveDate;
	}
	public String getIndexCode() {
		return indexCode;
	}
	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}
    
}