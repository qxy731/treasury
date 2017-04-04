package com.soule.base.service.user;

import java.io.Serializable;

import com.soule.app.sys.userinfo.UserInfoStaffPo;

public class DefaultUser implements IUser ,Serializable {

    private static final long serialVersionUID = 1L;
    private UserInfoStaffPo staff;

    public DefaultUser(UserInfoStaffPo x) {
        this.staff = x;
    }

    public String getUserID() {
        return staff.getStaffId();
    }

    public String getUserName() {
        return staff.getStaffName();
    }

    public UserInfoStaffPo getStaff() {
        return staff;
    }

    public String getOwnerUnitId() {
        return staff.getOwnerUnitid();
    }

    public String getOwnerUnitName() {
        return staff.getUnitName();
    }

    public String getUserLevel() {
        return staff.getStaffLevel();
    }

}
