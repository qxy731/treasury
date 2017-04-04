package com.soule.app.sys.bizline.bltype;

import java.io.Serializable;

import org.apache.struts2.json.annotations.JSON;

/**
 * 参数传递查询所有业务线类型的类
 */
public class BltypeBizTypePo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String bizTypeId;
    private String bizTypeName;
    private String remark;
    private String createUser;
    private java.util.Date createTime;
    private String lastUpdUser;
    private java.util.Date lastUpdTime;

    /**
     * @return 业务线类别
     */
    public String getBizTypeId() {
        return bizTypeId;
    }

    /**
     * @param bizTypeId 业务线类别
     */
    public void setBizTypeId(String bizTypeId) {
        this.bizTypeId = bizTypeId;
    }
    /**
     * @return 业务线名称
     */
    public String getBizTypeName() {
        return bizTypeName;
    }

    /**
     * @param bizTypeName 业务线名称
     */
    public void setBizTypeName(String bizTypeName) {
        this.bizTypeName = bizTypeName;
    }
    /**
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
    /**
     * @return 创建时间
     */
    @JSON(format="yyyy-MM-dd HH:mm:ss")
    public java.util.Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 创建时间
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }
    /**
     * @return 最后修改人
     */
    public String getLastUpdUser() {
        return lastUpdUser;
    }

    /**
     * @param lastUpdUser 最后修改人
     */
    public void setLastUpdUser(String lastUpdUser) {
        this.lastUpdUser = lastUpdUser;
    }
    /**
     * @return 最后修改时间
     */
    public java.util.Date getLastUpdTime() {
        return lastUpdTime;
    }

    /**
     * @param lastUpdTime 最后修改时间
     */
    public void setLastUpdTime(java.util.Date lastUpdTime) {
        this.lastUpdTime = lastUpdTime;
    }
}