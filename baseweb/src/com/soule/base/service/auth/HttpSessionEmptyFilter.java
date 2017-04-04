package com.soule.base.service.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;

import com.soule.MsgConstants;
import com.soule.comm.tools.AppUtils;

public class HttpSessionEmptyFilter implements Filter {

    private SessionRegistry sessionRegistry;

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        if (!(request instanceof HttpServletRequest)) {
            throw new ServletException("Can only process HttpServletRequest");
        }
 
        if (!(response instanceof HttpServletResponse)) {
            throw new ServletException("Can only process HttpServletResponse");
        }
 
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String q1 = httpRequest.getRequestURI();
        // String q2 = httpRequest.getContextPath();
        if (q1.endsWith(".action")) {
            HttpSession session = httpRequest.getSession(false);
            SessionInformation info = null;
            if(session!=null){
                info=this.sessionRegistry.getSessionInformation(session.getId());
                if (info != null) {
                    if (info.isExpired()) {
                        httpResponse.setContentType("text/html;charset=UTF-8");
                        String msg = AppUtils.getMessage(MsgConstants.E0008);
                        httpResponse.getWriter().write(msg);
                        return;
                    }
                }
            }else{
                httpResponse.setContentType("text/html;charset=UTF-8");
                String msg = AppUtils.getMessage(MsgConstants.E0008);
                httpResponse.getWriter().write(msg);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig arg0) throws ServletException {
    }

    public SessionRegistry getSessionRegistry() {
        return sessionRegistry;
    }

    public void setSessionRegistry(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }
}
