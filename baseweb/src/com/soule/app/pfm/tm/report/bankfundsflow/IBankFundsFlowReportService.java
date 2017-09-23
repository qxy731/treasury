package com.soule.app.pfm.tm.report.bankfundsflow;


import java.util.Map;

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
    
    public Map export4(BankFundsFlowReportQueryIn in) throws ServiceException;
    /**
     *  国库会计分析其他数据统计表
     * @param in
     * @return
     * @throws ServiceException
     */
    public BankFundsFlowReportQueryOut query3(BankFundsFlowReportQueryIn in) throws ServiceException;
    
    
    
    /**
     * 与其他国库之间资金流动情况统计表
     * @param in
     * @return
     * @throws ServiceException
     */
    public BankFundsFlowReportQueryOut query2(BankFundsFlowReportQueryIn in) throws ServiceException;
    
    /**
     * 	与商业银行之间资金流动情况统计表
     * @param in
     * @return
     * @throws ServiceException
     */
    public Map export(BankFundsFlowReportQueryIn in) throws ServiceException;
   
}
