package com.soule.app.pfm.tm.model.action;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

public class ModelDefQueryIn implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	    private ServiceInput inputHead = new ServiceInput();

	    private String modelCode;
	    private String modelName;
		public ServiceInput getInputHead() {
			return inputHead;
		}
		public void setInputHead(ServiceInput inputHead) {
			this.inputHead = inputHead;
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