
package com.soule.app.sys.staff;

import java.io.Serializable;

/**
 * 参数传递查询人员的类
 */
public class StaffStaffPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String staffId;
    private String staffName;
    private String ownerUnitid;
    private Integer staffLevel;
    private String staffStatus;
    private String sex;
    private String createUser;
    private java.util.Date createTime;
    private String lastUpdUser;
    private java.util.Date lastUpdTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String staffLevelName;
    private String staffStatusName;
    private String sexName;
    private String createManName;
    
    private String logonId;
    private String password;
    
    
    private String unitName;
    /**
     * @return 员工编号
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * @param staffId 员工编号
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
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
     * @return 所属组织
     */
    public String getOwnerUnitid() {
        return ownerUnitid;
    }

    /**
     * @param ownerUnitid 所属组织
     */
    public void setOwnerUnitid(String ownerUnitid) {
        this.ownerUnitid = ownerUnitid;
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
    /**
     * @return EXT1
     */
    public String getExt1() {
        return ext1;
    }

    /**
     * @param ext1 EXT1
     */
    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }
    /**
     * @return EXT2
     */
    public String getExt2() {
        return ext2;
    }

    /**
     * @param ext2 EXT2
     */
    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }
    /**
     * @return EXT3
     */
    public String getExt3() {
        return ext3;
    }

    /**
     * @param ext3 EXT3
     */
    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }
    /**
     * @return 员工级别名称
     */
    public String getStaffLevelName() {
        return staffLevelName;
    }

    /**
     * @param staffLevelName 员工级别名称
     */
    public void setStaffLevelName(String staffLevelName) {
        this.staffLevelName = staffLevelName;
    }
    /**
     * @return 员工状态名称
     */
    public String getStaffStatusName() {
        return staffStatusName;
    }

    /**
     * @param staffStatusName 员工状态名称
     */
    public void setStaffStatusName(String staffStatusName) {
        this.staffStatusName = staffStatusName;
    }
    /**
     * @return 员工性别名称
     */
    public String getSexName() {
        return sexName;
    }

    /**
     * @param sexName 员工性别名称
     */
    public void setSexName(String sexName) {
        this.sexName = sexName;
    }
    /**
     * @return 创建人
     */
    public String getCreateManName() {
        return createManName;
    }

    /**
     * @param createManName 创建人
     */
    public void setCreateManName(String createManName) {
        this.createManName = createManName;
    }

    public String getLogonId() {
        return logonId;
    }

    public void setLogonId(String logonId) {
        this.logonId = logonId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUnitName() {
        return unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}