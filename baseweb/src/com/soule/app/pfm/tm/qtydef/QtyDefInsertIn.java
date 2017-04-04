package com.soule.app.pfm.tm.qtydef;
import java.io.Serializable;
import java.util.List;

import com.soule.base.service.ServiceInput;
public class QtyDefInsertIn implements Serializable {
	private static final long serialVersionUID = 5518923337257092526L;
	private ServiceInput inputHead = new ServiceInput();
	private QtyDefPo newQtyDef;
    private List<String> dayScopeList;
	public QtyDefPo getNewQtyDef() {
		return newQtyDef;
	}
	public void setNewQtyDef(QtyDefPo newQtyDef) {
		this.newQtyDef = newQtyDef;
	}

	public ServiceInput getInputHead() {
		return inputHead;
	}

	public void setInputHead(ServiceInput inputHead) {
		this.inputHead = inputHead;
	}
	public List<String> getDayScopeList() {
		return dayScopeList;
	}
	public void setDayScopeList(List<String> dayScopeList) {
		this.dayScopeList = dayScopeList;
	}
}
