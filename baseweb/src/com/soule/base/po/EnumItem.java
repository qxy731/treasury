package com.soule.base.po;

import com.soule.comm.po.IEnumItem;

public class EnumItem implements IEnumItem {
    private String key;
    private String value;
    private String desc;

    public EnumItem(String string, String string2, String string3) {
        key = string;
        value = string2;
        desc = string3;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String toJsString() {
        StringBuilder sb = new StringBuilder();
        sb.append("['").append(key).append("','").append(value).append("']");
        return sb.toString();
    }

}
