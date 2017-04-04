package com.soule.app.common.selectunit;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

public class SelectUnitOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    
    private List<SelectUnitPo> unitVOList;


   

	public List<SelectUnitPo> getUnitVOList() {
		return unitVOList;
	}

	public void setUnitVOList(List<SelectUnitPo> unitVOList) {
		this.unitVOList = unitVOList;
	}

	public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}
