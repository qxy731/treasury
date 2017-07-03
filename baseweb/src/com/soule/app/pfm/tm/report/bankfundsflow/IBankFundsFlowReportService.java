package com.soule.app.pfm.tm.report.bankfundsflow;


import com.soule.base.service.ServiceException;

/**
 * 自定义报表操作
 */
public interface IBankFundsFlowReportService {

    /**
     * 自定义报表的查询
     */
    public BankFundsFlowReportQueryOut query(BankFundsFlowReportQueryIn in) throws ServiceException;
    
    public BankFundsFlowReportQueryOut query4(BankFundsFlowReportQueryIn in) throws ServiceException;
   
}
