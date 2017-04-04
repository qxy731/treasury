package com.soule.app.table;

import java.io.Serializable;

/**
 * 对应表SYS_RES_URL的类
 */
public class SysResUrlPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * URL_ID
     */
    private String urlId;
    /**
     * URL名称
     */
    private String urlName;
    /**
     * URL路径
     */
    private String urlPath;

    /**
     * @return URL_ID
     */
    public String getUrlId () {
        return urlId;
    }

    /**
     * @param urlId URL_ID
     */
    public void setUrlId (String urlId) {
        this.urlId = urlId;
    }
    /**
     * @return URL名称
     */
    public String getUrlName () {
        return urlName;
    }

    /**
     * @param urlName URL名称
     */
    public void setUrlName (String urlName) {
        this.urlName = urlName;
    }
    /**
     * @return URL路径
     */
    public String getUrlPath () {
        return urlPath;
    }

    /**
     * @param urlPath URL路径
     */
    public void setUrlPath (String urlPath) {
        this.urlPath = urlPath;
    }

}