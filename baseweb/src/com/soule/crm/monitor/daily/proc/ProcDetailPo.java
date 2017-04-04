package com.soule.crm.monitor.daily.proc;

import java.io.Serializable;

/**
 * 参数传递进程信息的类
 */
public class ProcDetailPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long psId;
    private String execName;
    private java.util.Date startTime;
    private Long memUsed;
    private Long timeUsed;
    private String userId;

    /**
     * @return 句柄
     */
    public Long getPsId() {
        return psId;
    }

    /**
     * @param psId 句柄
     */
    public void setPsId(Long psId) {
        this.psId = psId;
    }
    /**
     * @return 执行名称
     */
    public String getExecName() {
        return execName;
    }

    /**
     * @param execName 执行名称
     */
    public void setExecName(String execName) {
        this.execName = execName;
    }
    /**
     * @return 启动时间
     */
    public java.util.Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime 启动时间
     */
    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }
    /**
     * @return 占用内存
     */
    public Long getMemUsed() {
        return memUsed;
    }

    /**
     * @param memUsed 占用内存
     */
    public void setMemUsed(Long memUsed) {
        this.memUsed = memUsed;
    }
    /**
     * @return 执行时间
     */
    public Long getTimeUsed() {
        return timeUsed;
    }

    /**
     * @param timeUsed 执行时间
     */
    public void setTimeUsed(Long timeUsed) {
        this.timeUsed = timeUsed;
    }
    /**
     * @return 用户
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId 用户
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}