package com.soule.app.sys.role;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数角色:新增角色
 */
public class RoleInsertIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead;

    private RoleRolePo newRole;

    public RoleRolePo getNewRole()  {
        return newRole;
    }

    public void setNewRole(RoleRolePo input) {
        this.newRole = input;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}