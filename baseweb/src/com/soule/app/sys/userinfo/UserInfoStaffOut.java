package com.soule.app.sys.userinfo;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数登陆用户信息装载:取用户信息
 */
public class UserInfoStaffOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private UserInfoStaffPo staff;


    public UserInfoStaffPo getStaff() {
        return staff;
    }

    public void setStaff(UserInfoStaffPo output) {
        this.staff = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}