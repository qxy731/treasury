package com.soule.app.sys.userinfo;

import com.soule.base.service.ServiceException;

/**
 * 登陆用户信息装载业务操作
 */
public interface IUserInfoService {

    /**
     */
    public UserInfoStaffOut staff(UserInfoStaffIn in) throws ServiceException;
    /**
     */
    public UserInfoOperUnitOut operUnit(UserInfoOperUnitIn in) throws ServiceException;


}
