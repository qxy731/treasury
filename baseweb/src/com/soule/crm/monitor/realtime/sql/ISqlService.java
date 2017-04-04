package com.soule.crm.monitor.realtime.sql;

import com.soule.base.service.ServiceException;

/**
 * Sql监控业务操作
 */
public interface ISqlService {

    /**
     * Sql执行情况查询
     */
    public SqlListOut list(SqlListIn in) throws ServiceException;
    /**
     * Sql执行明细情况查询
     */
    public SqlDetailOut detail(SqlDetailIn in) throws ServiceException;
    
    /**
     * 重新开始统计
     * @param in
     * @return
     * @throws ServiceException
     */
    public void reset() throws ServiceException;


}
