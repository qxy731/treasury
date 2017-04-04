package com.soule.base.service.logon;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.soule.base.service.ILogonUserInfo;
import com.soule.comm.tools.ContextUtil;

/**
 * 处理有关登录的事情
 * 
 * @author wuwei
 */
public class UserDetailsServiceImpl  implements UserDetailsService {

    private static Log logger = LogFactory.getLog(UserDetailsServiceImpl.class);
    @Autowired
    private IUserLogonDaoService logDao;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        LogonInfoPo user = null;
        try {
            user = logDao.getLogInfo(username);
        } catch (Exception e) {
            logger.error("",e);
            throw new UsernameNotFoundException("获取" + username + "有关信息失败", e);
        }
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails userDetails = (UserDetails)(ContextUtil.getBean(ILogonUserInfo.BEAN_ID));
        ((ILogonUserInfo)userDetails).setLogonInfo(user);
        return userDetails;
    }


}
