package com.soule.base;

import static com.soule.comm.tools.ContextUtil.getBean;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soule.base.service.ServiceException;
import com.soule.base.service.logon.IUserLogonDaoService;
import com.soule.base.service.logon.LogonInfoPo;

/**
 * 只通过用户名登录
 * 
 * @author wuwei
 *
 */
public abstract class GetUserInfo {
    private static Log logger = LogFactory.getLog(GetUserInfo.class);
    protected HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * 获取用户登录ID
     * @return
     */
    public abstract String getLogonId();

    public String getUserPassword() {
        IUserLogonDaoService logDao = (IUserLogonDaoService) getBean("logonInfoDao");
        try {
            LogonInfoPo po = logDao.getLogInfo(getLogonId());
            if (po!=null) {
                return po.getPassword();
            }
        } catch (ServiceException e) {
            logger.error("用户[" +getLogonId()+ "]获取密码失败");
        }
        return "";
    }
}
