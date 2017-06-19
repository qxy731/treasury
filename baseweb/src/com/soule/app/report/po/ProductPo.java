package com.soule.app.report.po;

import java.io.Serializable;
import java.util.Date;



/**
 * 对应表的类
 */
public class ProductPo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String id;
    private String orgName;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
    

 
}