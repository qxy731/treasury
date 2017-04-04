package com.soule.app.common.selectunit;

import java.io.Serializable;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceInput;
import com.soule.base.service.ServiceResult;

public class SelectUnitIn implements Serializable,IServiceResult{
	
    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();
    private ServiceResult head = new ServiceResult();
	
	private String unitId;
	private String superUnitId;
	private String unitName;
	private String orgId;
	private String type;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getSuperUnitId() {
		return superUnitId;
	}
	public void setSuperUnitId(String superUnitId) {
		this.superUnitId = superUnitId;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public ServiceInput getInputHead() {
		return inputHead;
	}
	public void setInputHead(ServiceInput inputHead) {
		this.inputHead = inputHead;
	}
	public ServiceResult getHead() {
		return head;
	}
	public void setHead(ServiceResult head) {
		this.head = head;
	}
	public ServiceResult getResultHead() {
		// TODO Auto-generated method stub
		return head;
	}

	
	
}
