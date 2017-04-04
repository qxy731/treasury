package com.soule.app.sys.funcauth;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数功能权限资源维护:查询功能权限资源
 */
public class FuncAuthQueryIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String jspPath;


    public String getJspPath() {
        return jspPath;
    }

    public void setJspPath(String jspPath) {
        this.jspPath= jspPath;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}