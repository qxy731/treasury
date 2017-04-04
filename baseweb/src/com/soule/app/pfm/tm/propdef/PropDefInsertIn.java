package com.soule.app.pfm.tm.propdef;

import java.io.Serializable;
import com.soule.base.service.ServiceInput;
/**
 * 新增指标分类（输入参数）
 */
public class PropDefInsertIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();
    private PropDefPo newPropDef;
    public PropDefPo getNewPropDef() {
		return newPropDef;
	}
	public void setNewPropDef(PropDefPo newPropDef) {
		this.newPropDef = newPropDef;
	}

	public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}