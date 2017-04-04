package com.soule.app.sys.unit;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

public class SelcUnitQueryOut implements Serializable, IServiceResult {
    private static final long serialVersionUID = -8375927674590605895L;
    private ServiceResult head = new ServiceResult();
    private List<UnitPo> unitPoList;
    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }
    public List<UnitPo> getUnitPoList() {
        return unitPoList;
    }
    public void setUnitPoList(List<UnitPo> unitPoList) {
        this.unitPoList = unitPoList;
    }

}
