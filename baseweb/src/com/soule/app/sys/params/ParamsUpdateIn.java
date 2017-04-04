package com.soule.app.sys.params;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数参数配置:修改参数配置
 */
public class ParamsUpdateIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private ParamsParamsPo params;

    public ParamsParamsPo getParams() {
        return params;
    }

    public void setParams(ParamsParamsPo input) {
        this.params = input;
    }

    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}