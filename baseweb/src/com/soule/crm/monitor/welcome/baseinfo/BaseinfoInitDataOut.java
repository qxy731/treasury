package com.soule.crm.monitor.welcome.baseinfo;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数基本信息展示:首页初始化
 */
public class BaseinfoInitDataOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private BaseinfoAppPo app;
    private BaseinfoHardwarePo hardware;
    private BaseinfoJvmPo jvm;


    public BaseinfoAppPo getApp() {
        return app;
    }

    public void setApp(BaseinfoAppPo output) {
        this.app = output;
    }

    public BaseinfoHardwarePo getHardware() {
        return hardware;
    }

    public void setHardware(BaseinfoHardwarePo output) {
        this.hardware = output;
    }

    public BaseinfoJvmPo getJvm() {
        return jvm;
    }

    public void setJvm(BaseinfoJvmPo output) {
        this.jvm = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}