package com.soule.base;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.security.core.session.SessionRegistry;

import com.soule.comm.tools.ContextUtil;

public class SessionListener implements HttpSessionListener {
    private SessionRegistry sessionRegistry;
    public void sessionCreated(HttpSessionEvent arg0) {
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        if (sessionRegistry == null) {
            sessionRegistry = (SessionRegistry)ContextUtil.getBean( "SessionRegistry");
        }
        if (sessionRegistry!=null) {
            sessionRegistry.removeSessionInformation(event.getSession().getId());
        }
    }

}
