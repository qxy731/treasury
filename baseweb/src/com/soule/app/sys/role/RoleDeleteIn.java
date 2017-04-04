package com.soule.app.sys.role;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输出参数角色:删除角色
 */
public class RoleDeleteIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead;

    private List<RoleRolePo> deletes;

    public List<RoleRolePo> getDeletes() {
        return deletes;
    }

    public void setDeletes(List<RoleRolePo> input) {
        this.deletes = input;
    }
    public String getDeletesStr() {
        return "";
    }

    public void setDeletesStr(String input) {
        JSONArray jsonArray = JSONArray.fromObject(input);
        this.deletes = (List<RoleRolePo>) JSONArray.toList(jsonArray, RoleRolePo.class); 
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}