package com.soule.app.sys.enums;

import java.io.Serializable;
import java.util.Date;

/**
 * WebService参数传递SYS_ENUM的类
 */
public class EnumPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 枚举编号
     */
    private String enumId;
    /**
     * 枚举名称
     */
    private String enumName;
    /**
     * 枚举描述
     */
    private String enumDesc;
    /**
     * 代码关联标志
     */
    private String linkSrcFlag;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后修改人
     */
    private String lastUpdUser;
    /**
     * 最后修改时间
     */
    private Date lastUpdTime;

    /**
     * @return 枚举编号
     */
    public String getEnumId () {
        return enumId;
    }

    /**
     * @param enumId 枚举编号
     */
    public void setEnumId (String enumId) {
        this.enumId = enumId;
    }
    /**
     * @return 枚举名称
     */
    public String getEnumName () {
        return enumName;
    }

    /**
     * @param enumName 枚举名称
     */
    public void setEnumName (String enumName) {
        this.enumName = enumName;
    }
    /**
     * @return 枚举描述
     */
    public String getEnumDesc () {
        return enumDesc;
    }

    /**
     * @param enumDesc 枚举描述
     */
    public void setEnumDesc (String enumDesc) {
        this.enumDesc = enumDesc;
    }
    /**
     * @return 代码关联标志
     */
    public String getLinkSrcFlag () {
        return linkSrcFlag;
    }

    /**
     * @param linkSrcFlag 代码关联标志
     */
    public void setLinkSrcFlag (String linkSrcFlag) {
        this.linkSrcFlag = linkSrcFlag;
    }
    /**
     * @return 创建人
     */
    public String getCreateUser () {
        return createUser;
    }

    /**
     * @param createUser 创建人
     */
    public void setCreateUser (String createUser) {
        this.createUser = createUser;
    }
    /**
     * @return 创建时间
     */
    public Date getCreateTime () {
        return createTime;
    }

    /**
     * @param createTime 创建时间
     */
    public void setCreateTime (Date createTime) {
        this.createTime = createTime;
    }
    /**
     * @return 最后修改人
     */
    public String getLastUpdUser () {
        return lastUpdUser;
    }

    /**
     * @param lastUpdUser 最后修改人
     */
    public void setLastUpdUser (String lastUpdUser) {
        this.lastUpdUser = lastUpdUser;
    }
    /**
     * @return 最后修改时间
     */
    public Date getLastUpdTime () {
        return lastUpdTime;
    }

    /**
     * @param lastUpdTime 最后修改时间
     */
    public void setLastUpdTime (Date lastUpdTime) {
        this.lastUpdTime = lastUpdTime;
    }

}