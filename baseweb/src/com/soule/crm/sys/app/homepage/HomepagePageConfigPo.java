package com.soule.crm.sys.app.homepage;

import java.io.Serializable;

/**
 * 参数传递首页初始化的类
 */
public class HomepagePageConfigPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String wid;
    private String title;
    private String type;
    private String parama;
    private String paramb;
    private String paramc;
    /**
     * 微件初始化数据
     */
    private Serializable model;

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

    public Serializable getModel() {
        return model;
    }

    public void setModel(Serializable model) {
        this.model = model;
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