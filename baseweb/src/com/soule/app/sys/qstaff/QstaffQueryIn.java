
package com.soule.app.sys.qstaff;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数选择客户公共页面:查询客户信息
 */
public class QstaffQueryIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();
    
    private String staffId;
    private String staffName;
    private Integer staffLevel;
    private String staffStatus;
    private String ownerUnitId;
    private String sex;

    public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Integer getStaffLevel() {
		return staffLevel;
	}

	public void setStaffLevel(Integer staffLevel) {
		this.staffLevel = staffLevel;
	}

	public String getStaffStatus() {
		return staffStatus;
	}

	public void setStaffStatus(String staffStatus) {
		this.staffStatus = staffStatus;
	}

	public String getOwnerUnitId() {
		return ownerUnitId;
	}

	public void setOwnerUnitId(String ownerUnitId) {
		this.ownerUnitId = ownerUnitId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}