package com.soule.app.sys.bizline.blass;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输出参数业务线人员分配:删除业务线中全部人员
 */
public class BlassDeleteAllIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String bizTypeId;
    private String bizValueId;


    public String getBizTypeId() {
        return bizTypeId;
    }

    public void setBizTypeId(String bizTypeId) {
        this.bizTypeId= bizTypeId;
    }

    public String getBizValueId() {
        return bizValueId;
    }

    public void setBizValueId(String bizValueId) {
        this.bizValueId= bizValueId;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}