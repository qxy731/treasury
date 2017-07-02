package com.soule.app.pfm.tm.model.action;

import java.io.Serializable;
import java.util.List;

import com.soule.app.pfm.tm.model.po.ModelDefPo;
import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

public class ModelDefDeleteIn implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	    private ServiceInput inputHead = new ServiceInput();
	    
	    public String getDeletesStr() {
			return "";
		}
		public void setDeletesStr(String deletesStr) {
			JSONArray jsonArray = JSONArray.fromObject(deletesStr);
	        this.tarList = (List<ModelDefPo>) JSONArray.toList(jsonArray, ModelDefPo.class); 
		}

	    private List<ModelDefPo> tarList;
		public ServiceInput getInputHead() {
			return inputHead;
		}
		public void setInputHead(ServiceInput inputHead) {
			this.inputHead = inputHead;
		}

		public List<ModelDefPo> getTarList() {
			return tarList;
		}

		public void setTarList(List<ModelDefPo> tarList) {
			this.tarList = tarList;
		}
		
		
}