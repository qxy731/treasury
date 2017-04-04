package com.soule.app.sys.qstaff;

import com.soule.base.service.ServiceException;

/**
 * 选择客户公共页面业务操作
 */
public interface IQstaffService {

    /**
     * 查询
     */
    public QstaffQueryOut query(QstaffQueryIn in) throws ServiceException;


}
