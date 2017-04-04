package com.soule.crm.table;

import java.io.Serializable;

/**
 * 对应表SYS_HOMEPAGE的类
 */
public class SysHomepagePo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 首页定制信息
     */
    private String layoutData;

    /**
     * @return 用户ID
     */
    public String getUserId () {
        return userId;
    }

    /**
     * @param userId 用户ID
     */
    public void setUserId (String userId) {
        this.userId = userId;
    }
    /**
     * @return 首页定制信息
     */
    public String getLayoutData () {
        return layoutData;
    }

    /**
     * @param layoutData 首页定制信息
     */
    public void setLayoutData (String layoutData) {
        this.layoutData = layoutData;
    }

}