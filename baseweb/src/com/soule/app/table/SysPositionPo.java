package com.soule.app.table;

import java.io.Serializable;

/**
 * 对应表SYS_POSITION的类
 */
public class SysPositionPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 职位ID
     */
    private String posiId;
    /**
     * 组织编号
     */
    private String unitId;
    /**
     * 角色编码
     */
    private String roleId;

    /**
     * @return 职位ID
     */
    public String getPosiId () {
        return posiId;
    }

    /**
     * @param posiId 职位ID
     */
    public void setPosiId (String posiId) {
        this.posiId = posiId;
    }
    /**
     * @return 组织编号
     */
    public String getUnitId () {
        return unitId;
    }

    /**
     * @param unitId 组织编号
     */
    public void setUnitId (String unitId) {
        this.unitId = unitId;
    }
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

}