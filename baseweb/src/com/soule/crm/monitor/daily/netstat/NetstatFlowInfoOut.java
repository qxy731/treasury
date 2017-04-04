package com.soule.crm.monitor.daily.netstat;

import java.io.Serializable;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数网络状况I/O监控:流量信息
 */
public class NetstatFlowInfoOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private NetstatStatPo stat;


    public NetstatStatPo getStat() {
        return stat;
    }

    public void setStat(NetstatStatPo output) {
        this.stat = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}