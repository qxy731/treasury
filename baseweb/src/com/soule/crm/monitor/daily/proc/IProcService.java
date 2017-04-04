package com.soule.crm.monitor.daily.proc;

import com.soule.base.service.ServiceException;

/**
 * 进程监控业务操作
 */
public interface IProcService {

    /**
     * 进程信息查询
     */
    public ProcListOut list(ProcListIn in) throws ServiceException;


}
