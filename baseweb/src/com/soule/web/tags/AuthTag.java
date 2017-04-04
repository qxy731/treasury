package com.soule.web.tags;

import org.apache.struts2.components.Property;
import org.apache.struts2.views.jsp.PropertyTag;

import com.soule.comm.CommConstants;

public class AuthTag extends PropertyTag {

    private static final long serialVersionUID = 1L;

    String key ;

    protected void populateParams() {
        Property prop = ((Property) component);
        prop.setDefault("false");
        prop.setValue("#request." + CommConstants.AUTH_MAP + "." + key);
    }
    public void setKey(String key) {
        this.key = key;
    }

}
