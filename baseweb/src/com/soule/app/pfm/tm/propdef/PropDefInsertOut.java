package com.soule.app.pfm.tm.propdef;

import java.io.Serializable;
import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 新增指标类（输出参数）
 */
public class PropDefInsertOut implements Serializable, IServiceResult {

	private static final long serialVersionUID = 1L;

	private ServiceResult head = new ServiceResult();
	// 添加成功后回显指标指标代码
	private String tarCode;

	public ServiceResult getResultHead() {
		return head;
	}

	public void setResultHead(ServiceResult head) {
		this.head = head;
	}

	public String getTarCode() {
		return tarCode;
	}

	public void setTarCode(String tarCode) {
		this.tarCode = tarCode;
	}
}