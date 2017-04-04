package com.soule.app.sys.roleass;

import java.io.Serializable;

/**
 * 参数传递按角色查询人员的类
 */
public class RoleassRoleStaffPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String staffId;
    private String staffName;
    private String roleId;
    private String operUnitid;
    private String operUnitName;
    private String posiId;

    /**
     * @return 员工编码
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * @param staffId 员工编码
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
     * @return 机构ID
     */
    public String getOperUnitid() {
        return operUnitid;
    }

    /**
     * @param operUnitid 机构ID
     */
    public void setOperUnitid(String operUnitid) {
        this.operUnitid = operUnitid;
    }

    public String getOperUnitName() {
        return operUnitName;
    }

    public void setOperUnitName(String operUnitName) {
        this.operUnitName = operUnitName;
    }

    public String getPosiId() {
        return posiId;
    }

    public void setPosiId(String posiId) {
        this.posiId = posiId;
    }
}