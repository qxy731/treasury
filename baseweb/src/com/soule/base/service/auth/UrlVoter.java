package com.soule.base.service.auth;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import com.soule.comm.enu.ResourceType;

public class UrlVoter implements AccessDecisionVoter {

    private boolean enabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    public boolean supports(Class clazz) {
        return clazz.isAssignableFrom(FilterInvocation.class);
    }

    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        if (!enabled) {
            return ACCESS_GRANTED;
        }
        for (ConfigAttribute attr:attributes) {
            String url = attr.getAttribute();
            if (voteUrl(authentication, url)) {
                return ACCESS_GRANTED;
            } else {
                return ACCESS_ABSTAIN;
            }
        }

        return ACCESS_ABSTAIN;
    }

    protected boolean voteUrl(Authentication authentication, String url) {
        for (GrantedAuthority ga : authentication.getAuthorities()) {
            if (!(ga instanceof ResourceAuthority)) {
                continue;
            }
            ResourceAuthority resAuth = (ResourceAuthority) ga;
            if (resAuth.getResType() == ResourceType.MENU) {
                String menuUrl = (String) resAuth.getResource();
                menuUrl = "/" + menuUrl;
                if ( menuUrl.startsWith(url)) {
                    return true;
                }
            }
            if (resAuth.getResType() == ResourceType.URL) {
                String urlg = (String) resAuth.getResource();
                if (url != null && url.startsWith("/")) {
                    url = url.substring(1);
                }
                if (urlg!= null && url != null && url.startsWith(urlg)) {
                    return true;
                }
            }

        }
        return false;
    }

}
