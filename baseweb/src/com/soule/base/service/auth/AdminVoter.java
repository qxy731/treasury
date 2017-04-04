package com.soule.base.service.auth;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class AdminVoter implements AccessDecisionVoter {

    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    public boolean supports(Class clazz) {
        return true;
    }

    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        return isAdmin(authentication) ? ACCESS_GRANTED : ACCESS_ABSTAIN;
    }

    public static boolean isAdmin(Authentication authentication) {
        if (authentication == null) {
            return false;
        }
        Collection<GrantedAuthority> auths = authentication.getAuthorities();
        if (auths == null) {
            return false;
        }
        for (GrantedAuthority ga:auths) {
            if (ga instanceof AdminAuthority) {
                return true;
            }
        }
        return false;
    }
}
