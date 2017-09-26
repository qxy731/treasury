package com.soule.data.bean;

import java.io.Serializable;

public class SysEnumItem  implements Serializable {

	private static final long serialVersionUID = 1L;

	private String enumId;
	private String itemId;
	private String itemValue;
	private String itemDesc;
	private Integer seqId;
	public String getEnumId() {
		return enumId;
	}
	public void setEnumId(String enumId) {
		this.enumId = enumId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemValue() {
		return itemValue;
	}
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public Integer getSeqId() {
		return seqId;
	}
	public void setSeqId(Integer seqId) {
		this.seqId = seqId;
	}
}