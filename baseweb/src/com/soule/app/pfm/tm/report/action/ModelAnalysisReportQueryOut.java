package com.soule.app.pfm.tm.report.action;

import java.io.Serializable;
import java.util.List;

import com.soule.app.pfm.tm.report.po.ModelAnalysisReportPo;
import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数人员:查询人员
 */
public class ModelAnalysisReportQueryOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<ModelAnalysisReportPo> modelAnalysisReport;
    
    public List<ModelAnalysisReportPo> getModelAnalysisReport() {
		return modelAnalysisReport;
	}

	public void setModelAnalysisReport(List<ModelAnalysisReportPo> modelAnalysisReport) {
		this.modelAnalysisReport = modelAnalysisReport;
	}

	public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}