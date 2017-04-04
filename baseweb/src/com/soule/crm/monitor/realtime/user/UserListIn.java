package com.soule.crm.monitor.realtime.user;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输出参数在线用户监控:在线用户查询
 */
public class UserListIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String userId;
    private Timestamp minTime;
    private Timestamp maxTime;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId= userId;
    }

    public java.sql.Timestamp getMinTime() {
        return minTime;
    }

    public void setMinTime(Timestamp minTime) {
        this.minTime= minTime;
    }

    public java.util.Date getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Timestamp maxTime) {
        this.maxTime= maxTime;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}