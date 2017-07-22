package com.soule.app.pfm.tm.report.action;


import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.app.pfm.tm.report.service.IModelAnalysisReportService;
import com.soule.app.pfm.tm.report.service.IUserDefinedReportService;
import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;

/**
 * 人员维护模块表现层处理类
 */
@Namespace("/report")
public class ModelAnalysisAction extends BaseAction {
    private static final long serialVersionUID = 1295775504760653068L;
    
    @Autowired
    private IModelAnalysisReportService modelAnalysisReportService;
    /**
     * 查询人员 输入参数 
     */
    private ModelAnalysisReportQueryIn queryIn;
    
    public void doInit() {
    }
    public String query() {
        try {
            queryIn.getInputHead().setPageNo(this.getPage());
            queryIn.getInputHead().setPageSize(this.getPagesize());
            
            ModelAnalysisReportQueryOut result = modelAnalysisReportService.query(queryIn);
            ServiceResult head = result.getResultHead();
            rows = result.getModelAnalysisReport();
            total=(int)result.getResultHead().getTotal();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
	public ModelAnalysisReportQueryIn getQueryIn() {
		return queryIn;
	}
	public void setQueryIn(ModelAnalysisReportQueryIn queryIn) {
		this.queryIn = queryIn;
	}
   

  
   
}
