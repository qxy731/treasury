package com.soule.base.service.auth;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * 数据库验证
 * @author wuwei
 *
 */
public class MyDaoAuthenticationProvider extends DaoAuthenticationProvider {
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication,
            UserDetails user) {
        UsernamePasswordAuthenticationTokenNs result = new UsernamePasswordAuthenticationTokenNs(principal, authentication
                .getCredentials(), user.getAuthorities());
        result.setDetails(authentication.getDetails());

        return result;
    }
}
