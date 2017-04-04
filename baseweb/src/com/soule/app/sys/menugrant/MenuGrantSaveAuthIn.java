package com.soule.app.sys.menugrant;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输出参数菜单权限设置:保存角色菜单权限
 */
public class MenuGrantSaveAuthIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private List<MenuGrantNodePo> modify;
    private String roleId;

    public List<MenuGrantNodePo> getModify() {
        return modify;
    }

    public void setModify(List<MenuGrantNodePo> input) {
        this.modify = input;
    }
    public String getModifyStr() {
        return "";
    }

    @SuppressWarnings("unchecked")
	public void setModifyStr(String input) {
        JSONArray jsonArray = JSONArray.fromObject(input);
        try {
            this.modify = (List<MenuGrantNodePo>) JSONArray.toList(jsonArray, MenuGrantNodePo.class);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}