package com.soule.app.pfm.tm.model.action;

import java.io.Serializable;
import java.util.List;

import com.soule.app.pfm.tm.model.po.ModelDefPo;
import com.soule.app.pfm.tm.qtydef.QtyDefPo;
import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

public class ModelDefUpdateIn implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	    private ServiceInput inputHead = new ServiceInput();
	    
	    private ModelDefPo modelDef;
	    
	    public String getUpdatesStr() {
	        return "";
	    }

	    public void setUpdatesStr(String input) {
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

		public List<QtyDefPo> getTarList() {
			return tarList;
		}
		public void setTarList(List<QtyDefPo> tarList) {
			this.tarList = tarList;
		}
		public ModelDefPo getModelDef() {
			return modelDef;
		}
		public void setModelDef(ModelDefPo modelDef) {
			this.modelDef = modelDef;
		}
		
		
}