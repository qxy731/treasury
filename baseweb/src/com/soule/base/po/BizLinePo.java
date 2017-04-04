package com.soule.base.po;

import java.io.Serializable;

/**
 * 业务线关联关系
 */
public class BizLinePo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String bizTypeId;
    private String bizValue;
    private String createUser;
    private String bizTypeName;
    private String bizName;

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
     * @return 业务线分类值
     */
    public String getBizValue() {
        return bizValue;
    }

    /**
     * @param bizValue 业务线分类值
     */
    public void setBizValue(String bizValue) {
        this.bizValue = bizValue;
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
     * @return 业务线类型名
     */
    public String getBizTypeName() {
        return bizTypeName;
    }

    /**
     * @param bizTypeName 业务线类型名
     */
    public void setBizTypeName(String bizTypeName) {
        this.bizTypeName = bizTypeName;
    }
    /**
     * @return 业务线分类名
     */
    public String getBizName() {
        return bizName;
    }

    /**
     * @param bizName 业务线分类名
     */
    public void setBizName(String bizName) {
        this.bizName = bizName;
    }
}