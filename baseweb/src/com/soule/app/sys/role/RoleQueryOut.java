package com.soule.app.sys.role;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数角色:查询角色
 */
public class RoleQueryOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<RoleRolePo> role;


    public List<RoleRolePo> getRole() {
        return role;
    }

    public void setRole(List<RoleRolePo> output) {
        this.role = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}