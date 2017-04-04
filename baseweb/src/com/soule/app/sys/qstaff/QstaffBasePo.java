
package com.soule.app.sys.qstaff;

import java.io.Serializable;

/**
 * 参数传递查询客户信息的类
 */
public class QstaffBasePo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String staffId;
    private String staffName;
    private String ownerUnitId;
    private String ownerUnitName;
    private Integer staffLevel;
    private String staffStatus;
    private String sex;
    private String createUser;
    private java.util.Date createTime;
    private String lastUpdUser;
    private java.util.Date lastUpdTime;
    

    /**
     * @return 客户编号
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * @param staffId 客户编号
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    /**
     * @return 客户名称
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * @param staffName 客户名称
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    /**
     * @return 所属机构
     */
    public String getOwnerUnitId() {
        return ownerUnitId;
    }

    /**
     * @param owerUnitId 所属机构
     */
    public void setOwnerUnitId(String ownerUnitId) {
        this.ownerUnitId = ownerUnitId;
    }
    public String getOwnerUnitName() {
		return ownerUnitName;
	}

	public void setOwnerUnitName(String ownerUnitName) {
		this.ownerUnitName = ownerUnitName;
	}

	/**
     * @return 员工级别
     */
    public Integer getStaffLevel() {
        return staffLevel;
    }

    /**
     * @param staffLevel 员工级别
     */
    public void setStaffLevel(Integer staffLevel) {
        this.staffLevel = staffLevel;
    }
    /**
     * @return 员工状态
     */
    public String getStaffStatus() {
        return staffStatus;
    }

    /**
     * @param staffStatus 员工状态
     */
    public void setStaffStatus(String staffStatus) {
        this.staffStatus = staffStatus;
    }
    /**
     * @return 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
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