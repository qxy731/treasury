package com.soule.app.sys.batch;

import java.util.List;

import com.soule.base.service.ServiceException;

public interface IBatchMonitorService {

    BatchQueryMonitorOut queryMonitor(BatchQueryMonitorIn in) throws ServiceException;

    BatchQueryMonitorOut queryStatus(BatchQueryMonitorIn in) throws ServiceException;
    /**
     * 查询实例号
     */
    public List queryInstIds(String param);
}
