package com.soule.base.service.auth;

import java.io.Serializable;

/**
 * 角色分配信息
 * @author wuwei
 *
 */
public class RoleAssignPo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String roleId;
    private String staffId;
    private String defFlag;
    private String remark;

    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getStaffId() {
        return staffId;
    }
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    public String getDefFlag() {
        return defFlag;
    }
    public void setDefFlag(String defFlag) {
        this.defFlag = defFlag;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
}
