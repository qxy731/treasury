package com.soule.base.service.auth;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;


public class UsernamePasswordAuthenticationTokenNs extends UsernamePasswordAuthenticationToken implements IAllowSetAuthentication{

    private static final long serialVersionUID = -2152952219599923036L;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean first = true;

    public UsernamePasswordAuthenticationTokenNs(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public UsernamePasswordAuthenticationTokenNs(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.authorities = authorities;
    }

    /**
     * 取代父类的(因为构造完成后无法修改)
     */
    public Collection<GrantedAuthority> getAuthorities() {
        if (authorities == null) {
            return null;
        }
        //GrantedAuthority[] copy = new GrantedAuthority[authorities.length];
        //System.arraycopy(authorities, 0, copy, 0, authorities.length);
        //FIXME 
        return (Collection<GrantedAuthority>) authorities;
    }

    /**
     * 只允许设置一次
     */
    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        if (first) {
            this.authorities = authorities;
            first = false;
        } else {
            throw new RuntimeException("GrantedAuthority be set only one time");
        }
    }
}
