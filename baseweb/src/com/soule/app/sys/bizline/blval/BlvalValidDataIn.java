package com.soule.app.sys.bizline.blval;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输出参数业务线分类值维护:判断业务线关系数据
 */
public class BlvalValidDataIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String bizTypeId;
    private String bizValue;


    public String getBizTypeId() {
        return bizTypeId;
    }

    public void setBizTypeId(String bizTypeId) {
        this.bizTypeId= bizTypeId;
    }

    public String getBizValue() {
        return bizValue;
    }

    public void setBizValue(String bizValue) {
        this.bizValue= bizValue;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}