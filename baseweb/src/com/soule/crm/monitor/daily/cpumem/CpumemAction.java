package com.soule.crm.monitor.daily.cpumem;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;

/**
 * 处理器内存监控维护模块表现层处理类
 */
@Namespace("/monitor")
public class CpumemAction extends BaseAction {
	private static final long serialVersionUID = 1L;
    @Autowired
    private ICpumemService service;

    /**
     * 处理器信息 输入参数 
     */
    private CpumemCinfoIn cinfoIn; //= new CpumemCinfoIn();
    /**
     * 内存信息 输入参数 
     */
    private CpumemMinfoIn minfoIn; //= new CpumemMinfoIn();

    private CpumemCpuPo cpuPo;
    private CpumemMemPo memPo;

    public void doInit() {
    }
    public String cinfo() {
        CpumemCinfoIn in = cinfoIn;
        try {
            CpumemCinfoOut result = service.cinfo(in);
            ServiceResult head = result.getResultHead();
            cpuPo = result.getCpu();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String minfo() {
        CpumemMinfoIn in = minfoIn;
        try {
            CpumemMinfoOut result = service.minfo(in);
            ServiceResult head = result.getResultHead();
            memPo = result.getMem();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }

    /**
     * 处理器信息
     */
    @JSON(serialize=false)
    public CpumemCinfoIn getCinfoIn() {
        return this.cinfoIn;
    }
    /**
     * 处理器信息
     */
    public void setCinfoIn(CpumemCinfoIn in) {
        this.cinfoIn = in;
    }
    /**
     * 内存信息
     */
    @JSON(serialize=false)
    public CpumemMinfoIn getMinfoIn() {
        return this.minfoIn;
    }
    /**
     * 内存信息
     */
    public void setMinfoIn(CpumemMinfoIn in) {
        this.minfoIn = in;
    }
    public CpumemCpuPo getCpuPo() {
        return cpuPo;
    }
    public void setCpuPo(CpumemCpuPo cpuPo) {
        this.cpuPo = cpuPo;
    }
    public CpumemMemPo getMemPo() {
        return memPo;
    }
    public void setMemPo(CpumemMemPo memPo) {
        this.memPo = memPo;
    }

}
