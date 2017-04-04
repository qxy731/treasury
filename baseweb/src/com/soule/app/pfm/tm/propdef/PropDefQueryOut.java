package com.soule.app.pfm.tm.propdef;

import java.io.Serializable;
import java.util.List;
import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数指标分类:查询指标类
 */
public class PropDefQueryOut implements Serializable, IServiceResult {

	private static final long serialVersionUID = 1L;

	private ServiceResult head = new ServiceResult();
	private List<PropDefPo> propDef;

	public ServiceResult getResultHead() {
		return head;
	}

	public void setResultHead(ServiceResult head) {
		this.head = head;
	}

	public List<PropDefPo> getPropDef() {
		return propDef;
	}

	public void setPropDef(List<PropDefPo> propDef) {
		this.propDef = propDef;
	}

}