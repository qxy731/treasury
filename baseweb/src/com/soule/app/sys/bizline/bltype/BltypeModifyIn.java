package com.soule.app.sys.bizline.bltype;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输出参数业务线类型维护:修改业务线类型
 */
public class BltypeModifyIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private BltypeBizTypePo bizType;

    public BltypeBizTypePo getBizType()  {
        return bizType;
    }

    public void setBizType(BltypeBizTypePo input) {
        this.bizType = input;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}