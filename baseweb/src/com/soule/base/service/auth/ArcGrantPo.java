package com.soule.base.service.auth;

import java.util.Date;

/**
 * 权限分配关系
 * 
 * @author wuwei
 *
 */
public class ArcGrantPo {
    private String principalCode;
    private String principalType;
    private String resCode;
    private String resType;
    private String channelCode;
    private String createUser;
    private Date createTime;
    private String lasgUpdUser;
    private Date lastUpdTime;
    public String getPrincipalCode() {
        return principalCode;
    }
    public void setPrincipalCode(String principalCode) {
        this.principalCode = principalCode;
    }
    public String getPrincipalType() {
        return principalType;
    }
    public void setPrincipalType(String principalType) {
        this.principalType = principalType;
    }
    public String getResCode() {
        return resCode;
    }
    public void setResCode(String resCode) {
        this.resCode = resCode;
    }
    public String getResType() {
        return resType;
    }
    public void setResType(String resType) {
        this.resType = resType;
    }
    public String getChannelCode() {
        return channelCode;
    }
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }
    public String getCreateUser() {
        return createUser;
    }
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getLasgUpdUser() {
        return lasgUpdUser;
    }
    public void setLasgUpdUser(String lasgUpdUser) {
        this.lasgUpdUser = lasgUpdUser;
    }
    public Date getLastUpdTime() {
        return lastUpdTime;
    }
    public void setLastUpdTime(Date lastUpdTime) {
        this.lastUpdTime = lastUpdTime;
    }
    
}
