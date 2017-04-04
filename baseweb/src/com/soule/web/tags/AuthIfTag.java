package com.soule.web.tags;

import org.apache.struts2.components.If;
import org.apache.struts2.views.jsp.IfTag;

import com.soule.comm.CommConstants;

public class AuthIfTag extends IfTag {

    private static final long serialVersionUID = 1L;

    String key ;

    protected void populateParams() {
        If prop = ((If) component);
        prop.setTest("#request." + CommConstants.AUTH_MAP + "." + key);
    }
    public void setKey(String key) {
        this.key = key;
    }

}
