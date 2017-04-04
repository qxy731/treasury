package com.soule.app.pfm.tm.propdef;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;
/**
 * 输出参数指标分类:修改指标分类信息
 */
public class PropDefUpdateIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private PropDefPo modifyPropDef;
    public PropDefPo getModifyPropDef() {
		return modifyPropDef;
	}

	public void setModifyPropDef(PropDefPo modifyPropDef) {
		this.modifyPropDef = modifyPropDef;
	}

	public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}