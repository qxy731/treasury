package com.soule.app.pfm.tm.model.action;

import java.io.Serializable;
import java.util.List;

import com.soule.app.pfm.tm.model.po.ModelDefPo;
import com.soule.app.pfm.tm.qtydef.QtyDefPo;
import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

public class ModelDefUpdateQueryOut implements Serializable, IServiceResult{
	private static final long serialVersionUID = -7552671001583998079L;
	private ServiceResult head = new ServiceResult();
    //指标回显代码
	 private ModelDefPo modelDef;

	private List<QtyDefPo> tarList;
    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

	public ModelDefPo getModelDef() {
		return modelDef;
	}

	public void setModelDef(ModelDefPo modelDef) {
		this.modelDef = modelDef;
	}

	public List<QtyDefPo> getTarList() {
		return tarList;
	}

	public void setTarList(List<QtyDefPo> tarList) {
		this.tarList = tarList;
	}
    
    
}