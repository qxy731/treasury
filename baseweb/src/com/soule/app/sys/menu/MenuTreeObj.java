package com.soule.app.sys.menu;

import com.soule.comm.po.ZtreeObj;

@SuppressWarnings("serial")
public class MenuTreeObj extends ZtreeObj {

    private String menuId;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}