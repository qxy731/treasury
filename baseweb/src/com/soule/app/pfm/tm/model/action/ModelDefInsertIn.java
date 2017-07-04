package com.soule.app.pfm.tm.model.action;

import java.io.Serializable;
import java.util.List;

import com.soule.app.pfm.tm.qtydef.QtyDefPo;
import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

public class ModelDefInsertIn implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	    private ServiceInput inputHead = new ServiceInput();

	    private String modelCode;
	    private String modelName;
	    private String modelDesc;
	    
	    public String getInsertsStr() {
	        return "";
	    }

	    public void setInsertsStr(String input) {
	        JSONArray jsonArray = JSONArray.fromObject(input);
	        this.tarList = (List<QtyDefPo>) JSONArray.toList(jsonArray, QtyDefPo.class); 
	    }
	    private List<QtyDefPo> tarList;
		public ServiceInput getInputHead() {
			return inputHead;
		}
		public void setInputHead(ServiceInput inputHead) {
			this.inputHead = inputHead;
		}

		public String getModelDesc() {
			return modelDesc;
		}
		public void setModelDesc(String modelDesc) {
			this.modelDesc = modelDesc;
		}
		public List<QtyDefPo> getTarList() {
			return tarList;
		}
		public void setTarList(List<QtyDefPo> tarList) {
			this.tarList = tarList;
		}
		public String getModelCode() {
			return modelCode;
		}
		public void setModelCode(String modelCode) {
			this.modelCode = modelCode;
		}
		public String getModelName() {
			return modelName;
		}
		public void setModelName(String modelName) {
			this.modelName = modelName;
		}
		
}