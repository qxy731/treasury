package com.soule.crm.demo.comp.dropdown;

import java.io.Serializable;

/**
 * 参数传递获取匹配数据的类
 */
public class DropdownDataPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String unitId;
    private String unitName;
    private String pinyin;

    /**
     * @return 机构号
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     * @param unitId 机构号
     */
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }
    /**
     * @return 机构名
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * @param unitName 机构名
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

}