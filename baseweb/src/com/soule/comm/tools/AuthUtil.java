package com.soule.comm.tools;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import com.soule.comm.CommConstants;

public class AuthUtil {
    
    @SuppressWarnings("unchecked")
    public static void addAuth(ServletRequest request,String authName) {
        Map auths = (Map) request.getAttribute(CommConstants.AUTH_MAP);
        if (auths == null) {
            auths = new HashMap();
            request.setAttribute(CommConstants.AUTH_MAP, auths);
        }
        auths.put(authName, Boolean.TRUE);
    }
    @SuppressWarnings("unchecked")
    public static void delAuth(ServletRequest request,String authName) {
        Map auths = (Map) request.getAttribute(CommConstants.AUTH_MAP);
        if (auths != null) {
            auths.remove(authName);
        }
    }
}
