package com.soule.crm.pfm.param.indexdata;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 参数传递指标数据补录信息查询的类
 */
public class IndexDataPo implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 指标代码
     */
    private String indexCode;

    /**
     * 指标名称
     */
    private String indexName;
    /**
     * 数据日期
     */
    private Date recoreDate;
    /**
     * 对象ID
     */
    private String objectId;
    /**
     * 对象名称
     */
    private String objectName;
    /**
     * 归属机构
     */
    private String orgCode;
    
    private String orgName;
    /**
     * 对象类型
     */
    private String objectType;
    /**
     * 指标值
     */
    private Double indexVal;
    /**
     * 加载日期
     */
    private Date loadDate;
    /**
     * 创建人
     */
    private String createUser;

    /**
     * @return 指标代码
     */
    public String getIndexCode() {
        return indexCode;
    }

    /**
     * @param indexCode 指标代码
     */
    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }
    /**
     * @return 数据日期
     */
    public java.util.Date getRecoreDate() {
        return recoreDate;
    }

    /**
     * @param recoreDate 数据日期
     */
    public void setRecoreDate(java.util.Date recoreDate) {
        this.recoreDate = recoreDate;
    }
    /**
     * @return 对象ID
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * @param objectId 对象ID
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
    /**
     * @return 归属机构
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * @param orgCode 归属机构
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
    /**
     * @return 对象类型
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * @param objectType 对象类型
     */
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }
    /**
     * @return 指标值
     */
    public Double getIndexVal() {
        return indexVal;
    }

    /**
     * @param indexVal 指标值
     */
    public void setIndexVal(Double indexVal) {
        this.indexVal = indexVal;
    }

	public Date getLoadDate() {
		return loadDate;
	}

	public void setLoadDate(Date loadDate) {
		this.loadDate = loadDate;
	}

	/**
     * @return 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
    
    
}