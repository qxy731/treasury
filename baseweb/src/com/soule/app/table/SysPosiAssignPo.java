package com.soule.app.table;

import java.io.Serializable;

/**
 * 对应表SYS_POSI_ASSIGN的类
 */
public class SysPosiAssignPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 职位ID
     */
    private String posiId;
    /**
     * 员工编号
     */
    private String staffId;

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

}