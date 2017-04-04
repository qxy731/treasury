package com.soule.app.demo.charts.single;

import com.soule.base.service.ServiceException;

/**
 * 基本图形业务操作
 */
public interface ISingleService {

    /**
     */
    public SingleDisplayOut display(SingleDisplayIn in) throws ServiceException;


}
