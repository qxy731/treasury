package com.soule.app.pfm.tm.model.action;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

public class ModelDefDeleteIn implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	    private ServiceInput inputHead = new ServiceInput();

	    private String mdoelCode;
	    private String mdoelName;
		public ServiceInput getInputHead() {
			return inputHead;
		}
		public void setInputHead(ServiceInput inputHead) {
			this.inputHead = inputHead;
		}

		public String getMdoelCode() {
			return mdoelCode;
		}
		public void setMdoelCode(String mdoelCode) {
			this.mdoelCode = mdoelCode;
		}
		public String getMdoelName() {
			return mdoelName;
		}
		public void setMdoelName(String mdoelName) {
			this.mdoelName = mdoelName;
		}
}