package com.soule.app.sys.unit;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数人员:查询人员
 */
public class UnitQueryOut implements Serializable, IServiceResult {
    private static final long serialVersionUID = 1L;
    private ServiceResult head = new ServiceResult();
    private List<UnitPo> unit; 
    public List<UnitPo> getUnit() {
		return unit;
	}
	public void setUnit(List<UnitPo> unit) {
		this.unit = unit;
	}
	public ServiceResult getResultHead() {
        return head;
    }
    public void setResultHead(ServiceResult head) {
        this.head = head;
    }
}