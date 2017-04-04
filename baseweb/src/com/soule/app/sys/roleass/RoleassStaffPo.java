package com.soule.app.sys.roleass;

import java.io.Serializable;

/**
 * 参数传递查询人员的类
 */
public class RoleassStaffPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String staffId;
    private String staffName;
    private String unitId;
    private String unitName;

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
     * @return 所属组织
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     * @param unitId 所属组织
     */
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

}