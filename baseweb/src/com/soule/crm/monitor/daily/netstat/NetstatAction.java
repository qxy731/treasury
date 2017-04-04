package com.soule.crm.monitor.daily.netstat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;

/**
 * 网络状况I/O监控维护模块表现层处理类
 */
@Namespace("/monitor")
public class NetstatAction extends BaseAction {
    private final static Log logger = LogFactory.getLog(NetstatAction.class);
    @Autowired
    private INetstatService service;

    /**
     * 网卡信息 输入参数 
     */
    private NetstatHwinfoIn hwinfoIn; //= new NetstatHwinfoIn();
    /**
     * 流量信息 输入参数 
     */
    private NetstatFlowInfoIn flowInfoIn; //= new NetstatFlowInfoIn();

    private NetstatStatPo netPo;
    private String hwid;

    public void doInit() {
        hwid = request.getParameter("hwid");
    }
    public String hwinfo() {
        NetstatHwinfoIn in = hwinfoIn;
        try {
            NetstatHwinfoOut result = service.hwinfo(in);
            ServiceResult head = result.getResultHead();
            this.total = head.getTotal();
            this.rows = result.getNet();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String flowInfo() {
        NetstatFlowInfoIn in = new NetstatFlowInfoIn();
        in.setHwId(hwid);
        try {
            NetstatFlowInfoOut result = service.flowInfo(in);
            ServiceResult head = result.getResultHead();
            netPo = result.getStat();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }

    /**
     * 网卡信息
     */
    @JSON(serialize=false)
    public NetstatHwinfoIn getHwinfoIn() {
        return this.hwinfoIn;
    }
    /**
     * 网卡信息
     */
    public void setHwinfoIn(NetstatHwinfoIn in) {
        this.hwinfoIn = in;
    }
    /**
     * 流量信息
     */
    @JSON(serialize=false)
    public NetstatFlowInfoIn getFlowInfoIn() {
        return this.flowInfoIn;
    }
    /**
     * 流量信息
     */
    public void setFlowInfoIn(NetstatFlowInfoIn in) {
        this.flowInfoIn = in;
    }
    public NetstatStatPo getNetPo() {
        return netPo;
    }
    public void setNetPo(NetstatStatPo netPo) {
        this.netPo = netPo;
    }
    public String getHwid() {
        return hwid;
    }
    public void setHwid(String hwid) {
        this.hwid = hwid;
    }

}
