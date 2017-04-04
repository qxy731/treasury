package com.soule.app.sys.unit;

import com.soule.comm.po.ZtreeObj;

@SuppressWarnings("serial")
public class UnitTreeObj extends ZtreeObj {

    // private String menuId;

    private String unitLevel;

    public String getUnitLevel() {
        return unitLevel;
    }

    public void setUnitLevel(String unitLevel) {
        this.unitLevel = unitLevel;
    }

}