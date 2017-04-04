package com.soule.app.sys.roleass;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输出参数角色分配:查询人员
 */
public class RoleassQueryStaffIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String roleId;
    private String staffName;
    private String staffId;
    private String unitId;


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId= roleId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName= staffName;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId= staffId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId= unitId;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}