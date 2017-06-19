package com.soule.app.pfm.tm.report.service;


import com.soule.app.pfm.tm.report.action.ModelAnalysisReportQueryIn;
import com.soule.app.pfm.tm.report.action.ModelAnalysisReportQueryOut;
import com.soule.base.service.ServiceException;

/**
 * 自定义报表操作
 */
public interface IModelAnalysisReportService {

    /**
     * 自定义报表的查询
     */
    public ModelAnalysisReportQueryOut query(ModelAnalysisReportQueryIn in) throws ServiceException;
   
}
