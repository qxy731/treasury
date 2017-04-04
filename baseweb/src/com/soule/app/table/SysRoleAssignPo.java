package com.soule.app.table;

import java.io.Serializable;

/**
 * 对应表SYS_ROLE_ASSIGN的类
 */
public class SysRoleAssignPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色编码
     */
    private String roleId;
    /**
     * 员工编号
     */
    private String staffId;
    /**
     * 缺省标志
     */
    private String defFlag;
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
    private java.util.Date createTime;
    /**
     * 最后修改人
     */
    private String lastUpdUser;
    /**
     * 最后修改时间
     */
    private java.util.Date lastUpdTime;

    /**
     * @return 角色编码
     */
    public String getRoleId () {
        return roleId;
    }

    /**
     * @param roleId 角色编码
     */
    public void setRoleId (String roleId) {
        this.roleId = roleId;
    }
    /**
     * @return 员工编号
     */
    public String getStaffId () {
        return staffId;
    }

    /**
     * @param staffId 员工编号
     */
    public void setStaffId (String staffId) {
        this.staffId = staffId;
    }
    /**
     * @return 缺省标志
     */
    public String getDefFlag () {
        return defFlag;
    }

    /**
     * @param defFlag 缺省标志
     */
    public void setDefFlag (String defFlag) {
        this.defFlag = defFlag;
    }
    /**
     * @return 备注
     */
    public String getRemark () {
        return remark;
    }

    /**
     * @param remark 备注
     */
    public void setRemark (String remark) {
        this.remark = remark;
    }
    /**
     * @return 创建人
     */
    public String getCreateUser () {
        return createUser;
    }

    /**
     * @param createUser 创建人
     */
    public void setCreateUser (String createUser) {
        this.createUser = createUser;
    }
    /**
     * @return 创建时间
     */
    public java.util.Date getCreateTime () {
        return createTime;
    }

    /**
     * @param createTime 创建时间
     */
    public void setCreateTime (java.util.Date createTime) {
        this.createTime = createTime;
    }
    /**
     * @return 最后修改人
     */
    public String getLastUpdUser () {
        return lastUpdUser;
    }

    /**
     * @param lastUpdUser 最后修改人
     */
    public void setLastUpdUser (String lastUpdUser) {
        this.lastUpdUser = lastUpdUser;
    }
    /**
     * @return 最后修改时间
     */
    public java.util.Date getLastUpdTime () {
        return lastUpdTime;
    }

    /**
     * @param lastUpdTime 最后修改时间
     */
    public void setLastUpdTime (java.util.Date lastUpdTime) {
        this.lastUpdTime = lastUpdTime;
    }

}