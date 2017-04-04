package com.soule.base.service.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * 允许设置授权信息
 *
 * @author wuwei
 *
 */
public interface IAllowSetAuthentication {
    public void setAuthorities(Collection<GrantedAuthority> authorities);
}
