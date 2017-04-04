package com.soule.app.sys.pagegrant;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输出参数页面功能权限设置:保存角色页面权限
 */
public class PageGrantSaveAuthIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private List<PageGrantNodePo> modify;
    private String roleId;

    public List<PageGrantNodePo> getModify() {
        return modify;
    }

    public void setModify(List<PageGrantNodePo> input) {
        this.modify = input;
    }
    public String getModifyStr() {
        return "";
    }

    public void setModifyStr(String input) {
        JSONArray jsonArray = JSONArray.fromObject(input);
        this.modify = (List<PageGrantNodePo>) JSONArray.toList(jsonArray, PageGrantNodePo.class); 
    }


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId= roleId;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}