package com.soule.app.sys.params;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数参数配置:查询参数配置
 */
public class ParamsQueryIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private ParamsParamsPo params;

    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

    public ParamsParamsPo getParams() {
        return params;
    }

    public void setParams(ParamsParamsPo params) {
        this.params = params;
    }

}