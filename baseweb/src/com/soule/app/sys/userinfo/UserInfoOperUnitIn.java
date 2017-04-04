package com.soule.app.sys.userinfo;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数登陆用户信息装载:取职位信息
 */
public class UserInfoOperUnitIn implements Serializable {

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