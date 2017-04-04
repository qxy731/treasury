package com.soule.crm.monitor.daily.cpumem;

import com.soule.base.service.ServiceException;

/**
 * 处理器内存监控业务操作
 */
public interface ICpumemService {

    /**
     * CPU当前状态查询
     */
    public CpumemCinfoOut cinfo(CpumemCinfoIn in) throws ServiceException;
    /**
     * 物理内存当前使用状态查询
     */
    public CpumemMinfoOut minfo(CpumemMinfoIn in) throws ServiceException;


}
