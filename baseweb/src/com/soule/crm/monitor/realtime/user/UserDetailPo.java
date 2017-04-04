package com.soule.crm.monitor.realtime.user;

import java.io.Serializable;

/**
 * 参数传递在线用户查询的类
 */
public class UserDetailPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String userId;
    private String userName;
    private java.util.Date logonTime;
    private String onlineTime;
    private String ipAddress;

    /**
     * @return 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * @return 用户姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName 用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * @return 登陆时间
     */
    public java.util.Date getLogonTime() {
        return logonTime;
    }

    /**
     * @param logonTime 登陆时间
     */
    public void setLogonTime(java.util.Date logonTime) {
        this.logonTime = logonTime;
    }
    /**
     * @return 在线时间
     */
    public String getOnlineTime() {
        return onlineTime;
    }

    /**
     * @param onlineTime 在线时间
     */
    public void setOnlineTime(String onlineTime) {
        this.onlineTime = onlineTime;
    }
    /**
     * @return 登陆IP地址
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @param ipAddress 登陆IP地址
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}