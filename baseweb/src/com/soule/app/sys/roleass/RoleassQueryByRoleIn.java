
package com.soule.app.sys.roleass;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输出参数角色分配:按角色查询人员
 */
public class RoleassQueryByRoleIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String roleId;
    private String operUnitid;


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId= roleId;
    }

    public String getOperUnitid() {
        return operUnitid;
    }

    public void setOperUnitid(String operUnitid) {
        this.operUnitid= operUnitid;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}