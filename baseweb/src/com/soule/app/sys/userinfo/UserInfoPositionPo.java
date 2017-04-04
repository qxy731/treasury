package com.soule.app.sys.userinfo;

import java.io.Serializable;

/**
 * 参数传递取职位信息的类
 */
public class UserInfoPositionPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String posiId;
    private String staffId;
    private String defFlag;
    private String roleId;
    private String roleName;
    private String operUnitid;
    private String operUnitname;

    /**
     * @return 职位ID
     */
    public String getPosiId() {
        return posiId;
    }

    /**
     * @param posiId 职位ID
     */
    public void setPosiId(String posiId) {
        this.posiId = posiId;
    }
    /**
     * @return 员工号
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * @param staffId 员工号
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    /**
     * @return 缺省标志
     */
    public String getDefFlag() {
        return defFlag;
    }

    /**
     * @param defFlag 缺省标志
     */
    public void setDefFlag(String defFlag) {
        this.defFlag = defFlag;
    }
    /**
     * @return 角色ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId 角色ID
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    /**
     * @return 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    /**
     * @return 操作机构
     */
    public String getOperUnitid() {
        return operUnitid;
    }

    /**
     * @param operUnitid 操作机构
     */
    public void setOperUnitid(String operUnitid) {
        this.operUnitid = operUnitid;
    }
    /**
     * @return 操作机构名称
     */
    public String getOperUnitname() {
        return operUnitname;
    }

    /**
     * @param operUnitname 操作机构名称
     */
    public void setOperUnitname(String operUnitname) {
        this.operUnitname = operUnitname;
    }
}