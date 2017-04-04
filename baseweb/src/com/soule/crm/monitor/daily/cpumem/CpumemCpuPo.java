package com.soule.crm.monitor.daily.cpumem;

import java.io.Serializable;

/**
 * 参数传递处理器信息的类
 */
public class CpumemCpuPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long current;
    private Double idleTime;
    private Double userTime;
    private Double sysTime;

    /**
     * @return 状态时间
     */
    public Long getCurrent() {
        return current;
    }

    /**
     * @param current 状态时间
     */
    public void setCurrent(Long current) {
        this.current = current;
    }
    /**
     * @return 空闲比例
     */
    public Double getIdleTime() {
        return idleTime;
    }

    /**
     * @param idleTime 空闲比例
     */
    public void setIdleTime(Double idleTime) {
        this.idleTime = idleTime;
    }
    /**
     * @return 用户比例
     */
    public Double getUserTime() {
        return userTime;
    }

    /**
     * @param userTime 用户比例
     */
    public void setUserTime(Double userTime) {
        this.userTime = userTime;
    }
    /**
     * @return 系统比例
     */
    public Double getSysTime() {
        return sysTime;
    }

    /**
     * @param sysTime 系统比例
     */
    public void setSysTime(Double sysTime) {
        this.sysTime = sysTime;
    }
}