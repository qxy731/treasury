package com.soule.app.sys.batch;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

/**
 * 参数传递步骤监控信息类
 */
public class BatchMonitorPo extends BatchStepPo {

    private static final long serialVersionUID = 1L;

    private Integer instId;
    private Date startTime;
    private Date endTime;
    private String procStatus;
    private String procInfo;

    public Integer getInstId() {
        return instId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    @JSON(format="yyyy-MM-dd HH:mm:ss")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @JSON(format="yyyy-MM-dd HH:mm:ss")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getProcStatus() {
        return procStatus;
    }

    public void setProcStatus(String procStatus) {
        this.procStatus = procStatus;
    }

    public String getProcInfo() {
        return procInfo;
    }

    public void setProcInfo(String procInfo) {
        this.procInfo = procInfo;
    }

}