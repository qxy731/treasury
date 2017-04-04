package com.soule.crm.sys.app.homepage;

import java.io.Serializable;

/**
 * 参数传递微件信息的类
 */
public class HomepageWidgetPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String wid;
    private String title;
    private String dataService;
    private String type;
    private String remark;
    private String parama;
    private String paramb;
    private String paramc;
    private String selected;

    /**
     * @return 微件ID
     */
    public String getWid() {
        return wid;
    }

    /**
     * @param wid 微件ID
     */
    public void setWid(String wid) {
        this.wid = wid;
    }
    /**
     * @return 微件标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title 微件标题
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return 微件数据服务
     */
    public String getDataService() {
        return dataService;
    }

    /**
     * @param dataService 微件数据服务
     */
    public void setDataService(String dataService) {
        this.dataService = dataService;
    }
    /**
     * @return 微件类型
     */
    public String getType() {
        return type;
    }

    /**
     * @param type 微件类型
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
    public String getParama() {
        return parama;
    }

    public void setParama(String parama) {
        this.parama = parama;
    }

    public String getParamb() {
        return paramb;
    }

    public void setParamb(String paramb) {
        this.paramb = paramb;
    }

    public String getParamc() {
        return paramc;
    }

    public void setParamc(String paramc) {
        this.paramc = paramc;
    }
}