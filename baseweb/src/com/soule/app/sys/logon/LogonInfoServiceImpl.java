package com.soule.app.sys.logon;
import static com.soule.comm.tools.ContextUtil.getBean;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.stereotype.Service;


import com.soule.MsgConstants;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.IServerTime;
import com.soule.base.service.ServiceException;
import com.soule.base.service.logon.LogonInfoPo;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.ContextUtil;

/**
 * 密码重置
 */
@Service
public class LogonInfoServiceImpl implements ILogonInfoService{
    private static final String SYS_LOGONINFO_INSERT="logonInfo.saveSysLongonInfo";
    private static final String SYS_LOGONINFO_UPDATE_PWD="logonInfo.updSysLogonPwd";
    private PasswordEncoder passwordEncoder = new PlaintextPasswordEncoder();
    @Autowired
    IServerTime serverTime;

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    public LongonInfoInsertOut insert(LogonInfoPo logonInfo) throws ServiceException, DbAccessException {
        LongonInfoInsertOut out = new LongonInfoInsertOut();
        IDefaultService defService = (IDefaultService) ContextUtil.getBean("defaultService");
        if (logonInfo != null) {
            String epwd = passwordEncoder.encodePassword(logonInfo.getPassword(), null);
            logonInfo.setPassword(epwd);
            int i = defService.getIbatisMediator().save(SYS_LOGONINFO_INSERT, logonInfo);
            if (i == 1) {
                AppUtils.setResult(out, MsgConstants.I0001);
            }
        }
        return out;
    }

    public LongonInfoInsertOut updateLogonPassword(LogonInfoPo logonInfo) throws ServiceException, DbAccessException {
        LongonInfoInsertOut out = new LongonInfoInsertOut();
        IDefaultService defService = (IDefaultService) ContextUtil.getBean("defaultService");
        if (logonInfo != null) {
            String epwd = passwordEncoder.encodePassword(logonInfo.getPassword(), null);
            logonInfo.setPassword(epwd);
            Date today = serverTime.getSysTime();
            Calendar c = Calendar.getInstance();
            c.setTime(today);
            c.add(Calendar.DAY_OF_MONTH, +10);
            logonInfo.setPwdExpireTime(c.getTime());
            int i = defService.getIbatisMediator().update(SYS_LOGONINFO_UPDATE_PWD, logonInfo);
            if (i == 1) {
                AppUtils.setResult(out, MsgConstants.I0003);
            }
        }
        return out;
    }

}
