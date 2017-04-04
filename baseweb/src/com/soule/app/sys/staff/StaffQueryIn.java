package com.soule.app.sys.staff;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数人员:查询人员
 */
public class StaffQueryIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String staffId;
    private String staffName;
    private Integer staffLevel;
    private String staffStatus;
    private String unitId;
    private String unitName;


    public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId= staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName= staffName;
    }

    public Integer getStaffLevel() {
        return staffLevel;
    }

    public void setStaffLevel(Integer staffLevel) {
        this.staffLevel= staffLevel;
    }

    public String getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(String staffStatus) {
        this.staffStatus= staffStatus;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId= unitId;
    }

    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}