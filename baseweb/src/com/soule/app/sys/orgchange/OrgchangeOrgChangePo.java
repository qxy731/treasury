package com.soule.app.sys.orgchange;

import java.io.Serializable;

import org.apache.struts2.json.annotations.JSON;

/**
 * 参数传递查询员工机构变更的类
 */
public class OrgchangeOrgChangePo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String staffId;    
    private String updUser;
    private String ownerUnitid;
    private java.util.Date startTime;
    private java.util.Date endTime;

    private String staffName;
    private String updUserName;
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
     * @return 修改人
     */
    public String getUpdUser() {
        return updUser;
    }

    /**
     * @param updUser 修改人
     */
    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }
    /**
     * @return 开始时间
     */
    @JSON(format="yyyy-MM-dd HH:mm:ss")
    public java.util.Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime 开始时间
     */
    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }
    /**
     * @return 结束时间
     */
    @JSON(format="yyyy-MM-dd HH:mm:ss")
    public java.util.Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime 结束时间
     */
    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    public String getOwnerUnitid() {
        return ownerUnitid;
    }

    public void setOwnerUnitid(String ownerUnitid) {
        this.ownerUnitid = ownerUnitid;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getUpdUserName() {
        return updUserName;
    }

    public void setUpdUserName(String updUserName) {
        this.updUserName = updUserName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    
}