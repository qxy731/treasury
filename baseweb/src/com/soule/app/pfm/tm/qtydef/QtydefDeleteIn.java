package com.soule.app.pfm.tm.qtydef;

import java.io.Serializable;
import java.util.List;
import net.sf.json.JSONArray;
import com.soule.base.service.ServiceInput;

public class QtydefDeleteIn implements Serializable {
	private static final long serialVersionUID = -7954066725479801672L;

	private ServiceInput inputHead = new ServiceInput();

	private List<QtyDefPo> deletes;
	private List<String> tarCodeList;

	public List<QtyDefPo> getDeletes() {
		return deletes;
	}

	public void setDeletes(List<QtyDefPo> input) {
		this.deletes = input;
	}

	public String getDeletesStr() {
		return "";
	}

	@SuppressWarnings("unchecked")
	public void setDeletesStr(String input) {
		JSONArray jsonArray = JSONArray.fromObject(input);
		this.deletes = (List<QtyDefPo>) JSONArray.toList(jsonArray,
				QtyDefPo.class);
	}

	public ServiceInput getInputHead() {
		return inputHead;
	}

	public void setInputHead(ServiceInput inputHead) {
		this.inputHead = inputHead;
	}

	public List<String> getTarCodeList() {
		return tarCodeList;
	}

	public void setTarCodeList(List<String> tarCodeList) {
		this.tarCodeList = tarCodeList;
	}

}
