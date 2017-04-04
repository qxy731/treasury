package com.soule.app.sys.roleass;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数角色分配:按角色查询人员
 */
public class RoleassQueryByRoleOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<RoleassRoleStaffPo> roleStaff;


    public List<RoleassRoleStaffPo> getRoleStaff() {
        return roleStaff;
    }

    public void setRoleStaff(List<RoleassRoleStaffPo> output) {
        this.roleStaff = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}