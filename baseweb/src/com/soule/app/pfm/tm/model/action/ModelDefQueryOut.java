package com.soule.app.pfm.tm.model.action;

import java.io.Serializable;
import java.util.List;

import com.soule.app.pfm.tm.model.po.ModelDefPo;
import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

public class ModelDefQueryOut  implements Serializable, IServiceResult {
	  private static final long serialVersionUID = 1L;

	    private ServiceResult head = new ServiceResult();
	    private List<ModelDefPo> modelDef;

	    public ServiceResult getHead() {
			return head;
		}

		public void setHead(ServiceResult head) {
			this.head = head;
		}

		public List<ModelDefPo> getModelDef() {
			return modelDef;
		}

		public void setModelDef(List<ModelDefPo> modelDef) {
			this.modelDef = modelDef;
		}

		public ServiceResult getResultHead() {
	        return head;
	    }

	    public void setResultHead(ServiceResult head) {
	        this.head = head;
	    }
}
