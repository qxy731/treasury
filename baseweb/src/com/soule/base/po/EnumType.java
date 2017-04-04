package com.soule.base.po;

import java.util.ArrayList;
import java.util.List;

import com.soule.comm.po.IEnumItem;
import com.soule.comm.po.IEnumType;

public class EnumType implements IEnumType {
    private String key;
    private List<IEnumItem> items = new ArrayList<IEnumItem>();

    public EnumType(String string) {
        key = string;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<IEnumItem> getItems() {
        return items;
    }

    public void setItems(List<IEnumItem> items) {
        this.items = items;
    }

    public void addItem(EnumItem enumItem) {
        items.add(enumItem);
    }

    public String toJsString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (IEnumItem item: items) {
            sb.append(item.toJsString());
            sb.append(',');
        }
        if (items.size() > 0) {
            sb.setLength(sb.length()-1);
        }
        sb.append("]");
        return sb.toString();
    }

}
