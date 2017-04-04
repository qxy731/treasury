package com.soule.app.sys.bizline.blass;

import java.io.Serializable;

/**
 * 参数传递查询业务线中人员的类
 */
public class BlassBizassPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String bizTypeId;
    private String bizValue;
    private String staffId;
    private String createUser;
    private java.util.Date createTime;
    private String staffName;
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
     * @return 员工ID
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * @param staffId 员工ID
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
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
     * @return 员工姓名
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * @param staffName 员工姓名
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName;
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