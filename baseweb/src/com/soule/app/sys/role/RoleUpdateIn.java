package com.soule.app.sys.role;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数角色:修改角色信息
 */
public class RoleUpdateIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead;

    private RoleRolePo modifyRole;

    public RoleRolePo getModifyRole()  {
        return modifyRole;
    }

    public void setModifyRole(RoleRolePo input) {
        this.modifyRole = input;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}