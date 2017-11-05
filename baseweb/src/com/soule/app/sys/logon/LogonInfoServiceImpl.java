package com.soule.app.sys.logon;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.base.Md5PasswordEncoder;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.base.service.logon.LogonInfoPo;
import com.soule.comm.enu.YesNoFlag;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.ContextUtil;
import com.soule.comm.tools.ParamsUtil;

/**
 * 密码重置
 */
@Service
public class LogonInfoServiceImpl implements ILogonInfoService{
    private static final String SYS_LOGONINFO_INSERT="logonInfo.saveSysLongonInfo";
    private static final String SYS_LOGONINFO_UPDATE_PWD="logonInfo.updSysLogonPwd";
    
    @Autowired
    private ParamsUtil paramsUtil;
    
    private PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

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
            logonInfo.setLockFlag(YesNoFlag.NO.getValue());
            logonInfo.setValidFlag(YesNoFlag.YES.getValue());
            int pwdExpireDays = Integer.parseInt(paramsUtil.getParamValueByParamIdAndRank("PWD_EXPIRE_DAYS", "SYS"));
            int maxFailCount = Integer.parseInt(paramsUtil.getParamValueByParamIdAndRank("MAX_FAIL_COUNT", "SYS"));
            logonInfo.setMaxFailCount(maxFailCount);
            logonInfo.setFailLogonCount(0);
            logonInfo.setPwdExpireDays(pwdExpireDays);
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, pwdExpireDays);
            logonInfo.setPwdExpireTime(c.getTime());
            defService.getIbatisMediator().save(SYS_LOGONINFO_INSERT, logonInfo);
            AppUtils.setResult(out, MsgConstants.I0001);
        }
        return out;
    }

    public LongonInfoInsertOut updateLogonPassword(LogonInfoPo logonInfo) throws ServiceException, DbAccessException {
        LongonInfoInsertOut out = new LongonInfoInsertOut();
        IDefaultService defService = (IDefaultService) ContextUtil.getBean("defaultService");
        if (logonInfo != null) {
            String epwd = passwordEncoder.encodePassword(logonInfo.getPassword(), null);
            logonInfo.setPassword(epwd);
            Calendar c = Calendar.getInstance();
            int pwdExpireDays = Integer.parseInt(paramsUtil.getParamValueByParamIdAndRank("PWD_EXPIRE_DAYS", "SYS"));
            c.add(Calendar.DAY_OF_MONTH, pwdExpireDays);
            logonInfo.setPwdExpireTime(c.getTime());
            defService.getIbatisMediator().update(SYS_LOGONINFO_UPDATE_PWD, logonInfo);
            AppUtils.setResult(out, MsgConstants.I0003);
        }
        return out;
    }
    
}
