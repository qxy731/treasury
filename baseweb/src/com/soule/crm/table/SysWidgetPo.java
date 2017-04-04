package com.soule.crm.table;

import java.io.Serializable;

/**
 * 对应表SYS_WIDGET的类
 */
public class SysWidgetPo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Override
	public boolean equals(Object obj) {
		if(((SysWidgetPo)obj).getWid().equals(this.getWid())){
			return true;
		}
		return false;
	}
	/**
     * 微件ID
     */
    private String wid;
    /**
     * 微件标题
     */
    private String title;
    /**
     * 微件数据服务
     */
    private String dataService;
    /**
     * 微件类型
     */
    private String type;
    /**
     * 配置参数A
     */
    private String parama;
    /**
     * 配置参数B
     */
    private String paramb;
    /**
     * 配置参数C
     */
    private String paramc;
    /**
     * 备注
     */
    private String remark;

    /**
     * @return 微件ID
     */
    public String getWid () {
        return wid;
    }

    /**
     * @param wid 微件ID
     */
    public void setWid (String wid) {
        this.wid = wid;
    }
    /**
     * @return 微件标题
     */
    public String getTitle () {
        return title;
    }

    /**
     * @param title 微件标题
     */
    public void setTitle (String title) {
        this.title = title;
    }
    /**
     * @return 微件数据服务
     */
    public String getDataService () {
        return dataService;
    }

    /**
     * @param dataService 微件数据服务
     */
    public void setDataService (String dataService) {
        this.dataService = dataService;
    }
    /**
     * @return 微件类型
     */
    public String getType () {
        return type;
    }

    /**
     * @param type 微件类型
     */
    public void setType (String type) {
        this.type = type;
    }
    /**
     * @return 配置参数A
     */
    public String getParama () {
        return parama;
    }

    /**
     * @param parama 配置参数A
     */
    public void setParama (String parama) {
        this.parama = parama;
    }
    /**
     * @return 配置参数B
     */
    public String getParamb () {
        return paramb;
    }

    /**
     * @param paramb 配置参数B
     */
    public void setParamb (String paramb) {
        this.paramb = paramb;
    }
    /**
     * @return 配置参数C
     */
    public String getParamc () {
        return paramc;
    }

    /**
     * @param paramc 配置参数C
     */
    public void setParamc (String paramc) {
        this.paramc = paramc;
    }
    /**
     * @return 备注
     */
    public String getRemark () {
        return remark;
    }

    /**
     * @param remark 备注
     */
    public void setRemark (String remark) {
        this.remark = remark;
    }

}