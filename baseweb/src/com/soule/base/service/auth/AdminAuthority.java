package com.soule.base.service.auth;

import org.springframework.security.core.GrantedAuthority;


public class AdminAuthority implements GrantedAuthority {
    private static final long serialVersionUID = 130368597169276996L;
    static final String ADMIN = "ADMIN";

    public String getAuthority() {
        return ADMIN;
    }

}
