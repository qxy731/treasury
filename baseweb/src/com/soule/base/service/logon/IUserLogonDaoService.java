package com.soule.base.service.logon;

import com.soule.base.service.ServiceException;

public interface IUserLogonDaoService {
    /**
     * 根据登录名获取登录信息
     */
    public LogonInfoPo getLogInfo(String logonId) throws ServiceException;
    /**
     * 修改登录状态
     */
    public void updateLogonStatus(LogonInfoPo po) throws ServiceException;
    /**
     * 失败次数修改
     */
    public LogonInfoPo updateLogonFailed(String logonId) throws ServiceException;
    /**
     * 更新密码
     */
    public String savePassword(String loginId, String oldPassword, String newPassword) throws ServiceException ;
    /**
     * 新增保存
     * @param user
     */
    public LogonInfoPo save(LogonInfoPo logInfo) throws ServiceException ;
}
