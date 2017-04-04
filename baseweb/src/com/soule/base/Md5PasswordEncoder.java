package com.soule.base;

/**
 * 
 * @author wuwei
 *
 */
public class Md5PasswordEncoder extends org.springframework.security.authentication.encoding.Md5PasswordEncoder {
    
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
        boolean result = false;
        if ( encPass.equals(rawPass)){
            result = true;
        }else{
            result = super.isPasswordValid(encPass, rawPass, salt);
        }
        return result;
    }
}
