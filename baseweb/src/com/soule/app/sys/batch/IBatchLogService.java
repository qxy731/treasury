package com.soule.app.sys.batch;

import com.soule.base.service.ServiceException;

public interface IBatchLogService {
    /**
     * 日志查询
     */
    public BatchLogOut querylog(BatchLogIn in) throws ServiceException;
}
