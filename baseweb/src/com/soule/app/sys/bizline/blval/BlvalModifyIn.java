package com.soule.app.sys.bizline.blval;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输出参数业务线分类值维护:修改业务线分类
 */
public class BlvalModifyIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private BlvalBizValPo bizVal;

    public BlvalBizValPo getBizVal()  {
        return bizVal;
    }

    public void setBizVal(BlvalBizValPo input) {
        this.bizVal = input;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}