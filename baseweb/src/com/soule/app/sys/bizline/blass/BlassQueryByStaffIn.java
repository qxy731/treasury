package com.soule.app.sys.bizline.blass;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输出参数业务线人员分配:查询人员所有业务线信息
 */
public class BlassQueryByStaffIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String staffId;


    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId= staffId;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}