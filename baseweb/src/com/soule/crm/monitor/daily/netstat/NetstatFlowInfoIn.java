package com.soule.crm.monitor.daily.netstat;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输出参数网络状况I/O监控:流量信息
 */
public class NetstatFlowInfoIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String hwId;


    public String getHwId() {
        return hwId;
    }

    public void setHwId(String hwId) {
        this.hwId= hwId;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}