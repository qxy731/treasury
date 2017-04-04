package com.soule.base.service.logon;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录信息
 * 
 * @author wuwei
 */
public class LogonInfoPo implements Serializable {

    private static final long serialVersionUID = 514905257326186993L;
    /**
     * 登录号
     */
    private String logonId;
    /**
     * 员工编号
     */
    private String staffId;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 有效标志
     */
    private String validFlag;
    /**
     * 锁定标志
     */
    private String lockFlag;
    /**
     * 失败的登录次数
     */
    private Integer failLogonCount;
    /**
     * 允许登录失败次数
     */
    private Integer maxFailCount;
    /**
     * 密码过期天数
     */
    private Integer pwdExpireDays;
    /**
     * 密码过期的时间
     */
    private Date pwdExpireTime;
    /**
     * 登录帐户过期的时间
     */
    private Date accExpireTime;
    /**
     * 上一次登录IP
     */
    private String lastLogonIp;
    /**
     * 上一次登录的时间
     */
    private Date lastLogonTime;

    public String getLogonId() {
        return logonId;
    }

    public void setLogonId(String logonId) {
        this.logonId = logonId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getFailLogonCount() {
        return failLogonCount;
    }

    public void setFailLogonCount(Integer failLogonCount) {
        this.failLogonCount = failLogonCount;
        if (this.failLogonCount == null) {
            this.failLogonCount = new Integer(0);
        }
    }

    public Integer getMaxFailCount() {
        return maxFailCount;
    }

    public void setMaxFailCount(Integer maxFailCount) {
        this.maxFailCount = maxFailCount;
        if (this.maxFailCount == null) {
            this.maxFailCount = new Integer(0);
        }
    }

    public Integer getPwdExpireDays() {
        return pwdExpireDays;
    }

    public void setPwdExpireDays(Integer pwdExpireDays) {
        this.pwdExpireDays = pwdExpireDays;
        if (this.pwdExpireDays == null) {
            this.pwdExpireDays = new Integer(0);
        }
    }

    public Date getPwdExpireTime() {
        return pwdExpireTime;
    }

    public void setPwdExpireTime(Date pwdExpireTime) {
        this.pwdExpireTime = pwdExpireTime;
    }

    public Date getAccExpireTime() {
        return accExpireTime;
    }

    public void setAccExpireTime(Date accExpireTime) {
        this.accExpireTime = accExpireTime;
    }

    public String getLastLogonIp() {
        return lastLogonIp;
    }

    public void setLastLogonIp(String lastLogonIp) {
        this.lastLogonIp = lastLogonIp;
    }

    public Date getLastLogonTime() {
        return lastLogonTime;
    }

    public void setLastLogonTime(Date lastLogonTime) {
        this.lastLogonTime = lastLogonTime;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(String lockFlag) {
        this.lockFlag = lockFlag;
    }
    
}
