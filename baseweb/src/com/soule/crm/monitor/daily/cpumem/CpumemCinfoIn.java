package com.soule.crm.monitor.daily.cpumem;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数处理器内存监控:处理器信息
 */
public class CpumemCinfoIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String userId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId= userId;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}