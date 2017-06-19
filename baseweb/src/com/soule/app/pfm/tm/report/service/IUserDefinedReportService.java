package com.soule.app.pfm.tm.report.service;


import com.soule.app.pfm.tm.report.action.UserDefinedReportQueryIn;
import com.soule.app.pfm.tm.report.action.UserDefinedReportQueryOut;
import com.soule.base.service.ServiceException;

/**
 * 自定义报表操作
 */
public interface IUserDefinedReportService {

    /**
     * 自定义报表的查询
     */
    public UserDefinedReportQueryOut query(UserDefinedReportQueryIn in) throws ServiceException;
   
}
