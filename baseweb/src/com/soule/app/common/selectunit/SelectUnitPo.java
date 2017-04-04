package com.soule.app.common.selectunit;

import java.io.Serializable;

public class SelectUnitPo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String unitId;
    private String unitName;
    private String superUnitId;
    private String leafFlag;
    private String isleaf;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getSuperUnitId() {
        return superUnitId;
    }

    public void setSuperUnitId(String superUnitId) {
        this.superUnitId = superUnitId;
    }

    public String getLeafFlag() {
        return leafFlag;
    }

    public void setLeafFlag(String leafFlag) {
        this.leafFlag = leafFlag;
    }

    public String getIsleaf() {
        if (leafFlag.equals("1")) {
            return "true";
        } else {
            return "false";
        }
    }

    public void setIsleaf(String isleaf) {
        this.isleaf = isleaf;
    }

}
