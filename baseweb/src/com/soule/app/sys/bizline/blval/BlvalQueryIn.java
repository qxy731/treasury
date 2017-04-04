package com.soule.app.sys.bizline.blval;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数业务线分类值维护:查询业务线分类
 */
public class BlvalQueryIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String bizTypeId;


    public String getBizTypeId() {
        return bizTypeId;
    }

    public void setBizTypeId(String bizTypeId) {
        this.bizTypeId= bizTypeId;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}