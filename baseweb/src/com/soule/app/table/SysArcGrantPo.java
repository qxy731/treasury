package com.soule.app.table;

import java.io.Serializable;
import java.util.Date;

/**
 * 对应表SYS_ARC_GRANT的类
 */
public class SysArcGrantPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 授权对象编码
     */
    private String principalCode;
    /**
     * 授权对象类型
     */
    private String principalType;
    /**
     * 资源编码
     */
    private String resCode;
    /**
     * 资源类型
     */
    private String resType;
    /**
     * 渠道编码
     */
    private String channelCode;
    /**
     * 可执行
     */
    private String runFlag;
    /**
     * 可分配
     */
    private String assFlag;
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
     * EXT1
     */
    private String ext1;
    /**
     * EXT2
     */
    private String ext2;
    /**
     * EXT3
     */
    private String ext3;

    /**
     * @return 授权对象编码
     */
    public String getPrincipalCode () {
        return principalCode;
    }

    /**
     * @param principalCode 授权对象编码
     */
    public void setPrincipalCode (String principalCode) {
        this.principalCode = principalCode;
    }
    /**
     * @return 授权对象类型
     */
    public String getPrincipalType () {
        return principalType;
    }

    /**
     * @param principalType 授权对象类型
     */
    public void setPrincipalType (String principalType) {
        this.principalType = principalType;
    }
    /**
     * @return 资源编码
     */
    public String getResCode () {
        return resCode;
    }

    /**
     * @param resCode 资源编码
     */
    public void setResCode (String resCode) {
        this.resCode = resCode;
    }
    /**
     * @return 资源类型
     */
    public String getResType () {
        return resType;
    }

    /**
     * @param resType 资源类型
     */
    public void setResType (String resType) {
        this.resType = resType;
    }
    /**
     * @return 渠道编码
     */
    public String getChannelCode () {
        return channelCode;
    }

    /**
     * @param channelCode 渠道编码
     */
    public void setChannelCode (String channelCode) {
        this.channelCode = channelCode;
    }
    /**
     * @return 可执行
     */
    public String getRunFlag () {
        return runFlag;
    }

    /**
     * @param runFlag 可执行
     */
    public void setRunFlag (String runFlag) {
        this.runFlag = runFlag;
    }
    /**
     * @return 可分配
     */
    public String getAssFlag () {
        return assFlag;
    }

    /**
     * @param assFlag 可分配
     */
    public void setAssFlag (String assFlag) {
        this.assFlag = assFlag;
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
    /**
     * @return EXT1
     */
    public String getExt1 () {
        return ext1;
    }

    /**
     * @param ext1 EXT1
     */
    public void setExt1 (String ext1) {
        this.ext1 = ext1;
    }
    /**
     * @return EXT2
     */
    public String getExt2 () {
        return ext2;
    }

    /**
     * @param ext2 EXT2
     */
    public void setExt2 (String ext2) {
        this.ext2 = ext2;
    }
    /**
     * @return EXT3
     */
    public String getExt3 () {
        return ext3;
    }

    /**
     * @param ext3 EXT3
     */
    public void setExt3 (String ext3) {
        this.ext3 = ext3;
    }

}