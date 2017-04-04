package com.soule.base.service.logon;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;

import com.soule.base.media.DbAccessException;
import com.soule.base.service.DefaultService;
import com.soule.base.service.IServerTime;
import com.soule.base.service.ServiceException;
import com.soule.comm.enu.YesNoFlag;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.ParamsUtil;
import com.soule.comm.tools.StringUtil;

/**
 * 处理登录有关的数据库操作
 * 
 * @author wuwei
 */
public class UserLogonDaoServiceImpl implements IUserLogonDaoService {

    private static Log logger = LogFactory.getLog(UserLogonDaoServiceImpl.class);
    private PasswordEncoder passwordEncoder = new PlaintextPasswordEncoder();
    @Autowired
    private DefaultService service;
    @Autowired
    private IServerTime serverTime;
    @Autowired
    private ParamsUtil paramsUtil;

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public LogonInfoPo getLogInfo(String logonId) throws ServiceException {
        LogonInfoPo user = null;
        try {
            user = (LogonInfoPo) service.getIbatisMediator().findById("auth.getLogonInfo", logonId);
        } catch (DbAccessException e) {
            if (e.getCause() == null) {
                logger.debug("",e);
            } else {
                logger.debug("", e.getCause());
            }
        }
        return user;
    }

    public LogonInfoPo updateLogonFailed(String logonId) throws ServiceException {
        LogonInfoPo po = getLogInfo(logonId);
        try {
            if (po!= null) {
                po.setFailLogonCount(po.getFailLogonCount()+1);
                if (po.getMaxFailCount() !=0 && po.getFailLogonCount() >= po.getMaxFailCount()) {
                    po.setLockFlag(YesNoFlag.YES.getValue());
                }
                service.getIbatisMediator().update("logonInfo.updateFailedLogon", po);
            }
        } catch (DbAccessException e) {
            logger.error("",e);
        }
        return po;
    }

    public void updateLogonStatus(LogonInfoPo po) throws ServiceException {
        if (po != null) {
            try {
            	service.getIbatisMediator().update("logonInfo.updateLogonStatus", po);
            } catch (DbAccessException e) {
                logger.error("",e);
            }
        }
    }

    public LogonInfoPo save(LogonInfoPo logInfo) throws ServiceException {
        return null;
    }
    
    public String savePassword(String loginId, String oldPassword, String newPassword) throws ServiceException {
        if (StringUtil.isEmpty(oldPassword)|| StringUtil.isEmpty(newPassword)) {
            return "密码为空";
        }
        if (loginId == null) {
            loginId = (String) AppUtils.getLogonUserInfo().getLogonInfo().getLogonId();
        }

        LogonInfoPo logonPo = getLogInfo(loginId);
        if (!passwordEncoder.isPasswordValid(logonPo.getPassword(), oldPassword, null)) {
            return "原密码不正确";
        }
        logonPo.setPassword(passwordEncoder.encodePassword(newPassword, ""));
        int expDays;
        if (logonPo.getPwdExpireDays()!= null) {
            expDays = logonPo.getPwdExpireDays().intValue();
        }
        else {
            String val = paramsUtil.getParamValueByParamIdAndRank("PWD_EXPIRE_DAYS", "SYS");
            expDays = StringUtil.parseInt(val, -1);
        }
        Date today = serverTime.getSysTime();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, expDays);
        logonPo.setPwdExpireTime(c.getTime());
        try {
        	service.getIbatisMediator().update("logonInfo.updateLogonPasswd", logonPo);
        } catch (Exception e) {
            logger.error("",e);
            return "错误原因:" +e.getMessage();
        }
        return null;
    }

}
