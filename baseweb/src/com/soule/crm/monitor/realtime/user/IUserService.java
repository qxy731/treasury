package com.soule.crm.monitor.realtime.user;

import com.soule.base.service.ServiceException;

/**
 * 在线用户监控业务操作
 */
public interface IUserService {

    /**
     * 在线用户查询
     */
    public UserListOut list(UserListIn in) throws ServiceException;


}
