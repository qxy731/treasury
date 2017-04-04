package com.soule.crm.monitor.daily.netstat;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数网络状况I/O监控:网卡信息
 */
public class NetstatHwinfoOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<NetstatNetPo> net;


    public List<NetstatNetPo> getNet() {
        return net;
    }

    public void setNet(List<NetstatNetPo> output) {
        this.net = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}