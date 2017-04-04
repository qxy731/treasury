package com.soule.app.sys.enums;

import java.io.Serializable;

/**
 * WebService参数传递SYS_ENUM_ITEM的类
 */
public class EnumItemPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 枚举编码
     */
    private String enumId;
    /**
     * 枚举项ID
     */
    private String itemId;
    /**
     * 枚举项显示值
     */
    private String itemValue;
    /**
     * 枚举项描述
     */
    private String itemDesc;
    /**
     * 顺序号
     */
    private Integer seqId;

    public String getEnumId() {
        return enumId;
    }

    public void setEnumId(String enumId) {
        this.enumId = enumId;
    }

    /**
     * @return 枚举项ID
     */
    public String getItemId () {
        return itemId;
    }

    /**
     * @param itemId 枚举项ID
     */
    public void setItemId (String itemId) {
        this.itemId = itemId;
    }
    /**
     * @return 枚举项显示值
     */
    public String getItemValue () {
        return itemValue;
    }

    /**
     * @param itemValue 枚举项显示值
     */
    public void setItemValue (String itemValue) {
        this.itemValue = itemValue;
    }
    /**
     * @return 枚举项描述
     */
    public String getItemDesc () {
        return itemDesc;
    }

    /**
     * @param itemDesc 枚举项描述
     */
    public void setItemDesc (String itemDesc) {
        this.itemDesc = itemDesc;
    }
    /**
     * @return 顺序号
     */
    public Integer getSeqId () {
        return seqId;
    }

    /**
     * @param seqId 顺序号
     */
    public void setSeqId (Integer seqId) {
        this.seqId = seqId;
    }

}