package com.soule.app.sys.logon;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.ServiceException;
import com.soule.base.service.logon.LogonInfoPo;

public interface ILogonInfoService {
    public LongonInfoInsertOut insert(LogonInfoPo logonInfo) throws ServiceException,DbAccessException;
    /**
     * 密码重置
     */
    public LongonInfoInsertOut updateLogonPassword(LogonInfoPo logonInfo) throws ServiceException,DbAccessException;

}
