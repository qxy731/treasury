package com.soule.crm.monitor.realtime.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.LogonInfoHolder;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.ContextUtil;

/**
 * 在线用户监控业务操作
 */
@Service
public class UserServiceImpl implements IUserService {

    private final static Log logger = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private SessionRegistry sessionRegistry;
    @Autowired
    private IDefaultService sDefault;
    /**
     * 在线用户查询
     */
    public UserListOut list(UserListIn in) throws ServiceException {
        UserListOut out = new UserListOut();

        List<Object> principals = sessionRegistry.getAllPrincipals();
        if ( in.getUserId() != null ){
            ArrayList<Object> sel = new ArrayList<Object>();
            for (Object principal: principals) {
                LogonInfoHolder holder = (LogonInfoHolder)principal;
                String p = holder.getUser().getUserID();
                if (p.startsWith(in.getUserId())) {
                    sel.add(holder);
                }
            }
            principals = sel;
        }
        out.getResultHead().setTotal(principals.size());
        List<UserDetailPo> output = new ArrayList<UserDetailPo>();
        out.setDetail(output);
        int startIdx = (in.getInputHead().getPageNo() -1 ) * in.getInputHead().getPageSize();
        int endIdx = startIdx + in.getInputHead().getPageSize();
        Date currDate = new Date();
        for (int i = startIdx ; i < principals.size() && i < endIdx ; i++) {
            List<SessionInformation> sessionInfo = sessionRegistry.getAllSessions(principals.get(i),false);
            UserDetailPo udp;
            try {
                if (sessionInfo!=null && sessionInfo.size() > 0 && sessionInfo.get(0).isExpired()) {
                    logger.debug("Expired " + sessionInfo.get(0).getPrincipal());
                    continue;
                }
                LogonInfoHolder hd = (LogonInfoHolder)sessionInfo.get(0).getPrincipal();
                in.setUserId(hd.getUser().getUserID());
                udp = (UserDetailPo) sDefault.getIbatisMediator().findById("monitor.getLogonInfo", in);
                if (udp != null ){
                    long t = currDate.getTime() - udp.getLogonTime().getTime();
                    udp.setOnlineTime(t/1000/60 + "m" + (t/1000)%60 + "s");
                    output.add(udp);
                }
            } catch (DbAccessException e) {
                logger.error("DB",e);
            }
        }
        AppUtils.setResult(out, MsgConstants.I0000, this.getClass().getName());
        return out;
    }

    public SessionRegistry getSessionRegistry() {
        return sessionRegistry;
    }

    public void setSessionRegistry(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

}
