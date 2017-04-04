
package com.soule.app.sys.roleass;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输出参数角色分配:删除角色人员
 */
public class RoleassDeleteIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private List<RoleassRoleStaffPo> deletes;

    public List<RoleassRoleStaffPo> getDeletes() {
        return deletes;
    }

    public void setDeletes(List<RoleassRoleStaffPo> input) {
        this.deletes = input;
    }
    public String getDeletesStr() {
        return "";
    }

    public void setDeletesStr(String input) {
        JSONArray jsonArray = JSONArray.fromObject(input);
        this.deletes = (List<RoleassRoleStaffPo>) JSONArray.toList(jsonArray, RoleassRoleStaffPo.class); 
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}