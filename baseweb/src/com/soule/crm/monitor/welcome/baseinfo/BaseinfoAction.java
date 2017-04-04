package com.soule.crm.monitor.welcome.baseinfo;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;

/**
 * 基本信息展示维护模块表现层处理类
 */
@Namespace("/monitor")
public class BaseinfoAction extends BaseAction {
	private static final long serialVersionUID = 38678225875723177L;
    @Autowired
    private IBaseinfoService service;

    /**
     * 首页初始化 输入参数 
     */
    private BaseinfoInitDataIn initDataIn; //= new BaseinfoInitDataIn();
    private BaseinfoInitDataOut out;

    public void doInit() {
        initData();
    }
    public String initData() {
        BaseinfoInitDataIn in = initDataIn;
        try {
            BaseinfoInitDataOut result = service.initData(in);
            out = result;
            out.getApp().setServicePort(String.valueOf(request.getLocalPort()));
            out.getApp().setAppMachineType(request.getSession().getServletContext().getServerInfo());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }

    /**
     * 首页初始化
     */
    @JSON(serialize=false)
    public BaseinfoInitDataIn getInitDataIn() {
        return this.initDataIn;
    }
    /**
     * 首页初始化
     */
    public void setInitDataIn(BaseinfoInitDataIn in) {
        this.initDataIn = in;
    }
	public BaseinfoInitDataOut getOut() {
		return out;
	}
	public void setOut(BaseinfoInitDataOut out) {
		this.out = out;
	}

}
