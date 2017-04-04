package com.soule.app.sys.logon;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.MsgConstants;
import com.soule.base.action.BaseAction;
import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;
import com.soule.base.service.logon.IUserLogonDaoService;
import com.soule.base.service.logon.LogonInfoPo;
import com.soule.comm.tools.ParamsUtil;

@Namespace("/sys")
public class LongonAction extends BaseAction{
    private static final long serialVersionUID = 2306616135866576613L;
    private LogonInfoPo logonInfoPo;

    private String oldPassword;
    private String newPassword;
    private String loginId;
    @Autowired
    private IUserLogonDaoService logDao;
    @Autowired
    private ILogonInfoService logonInfoService;
    @Autowired
    private ParamsUtil paramsUtil;

    public String updateLogonPwd() {
        try {
            String result = logDao.savePassword(loginId, oldPassword, newPassword);
            if (result == null) {
                this.setRetCode(MsgConstants.I0003);
                this.setRetMsg("修改密码成功!");
            }
            else {
                this.setRetCode(MsgConstants.E0015);
                this.setRetMsg(result);
            }
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String resetLogonPwd() {
        if(null!=logonInfoPo){
            String val = paramsUtil.getParamValueByParamIdAndRank("RESET_PASSWORD", "SYS");
            logonInfoPo.setPassword(val);
        }
        try {
            IServiceResult result = logonInfoService.updateLogonPassword(logonInfoPo);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public LogonInfoPo getLogonInfoPo() {
        return logonInfoPo;
    }
    public void setLogonInfoPo(LogonInfoPo logonInfoPo) {
        this.logonInfoPo = logonInfoPo;
    }
    public String getOldPassword() {
        return oldPassword;
    }
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public String getLoginId() {
        return loginId;
    }
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

}
