package com.soule.app.sys.roleass;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 输出参数角色分配:新增角色人员
 */
public class RoleassInsertIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private List<RoleassRoleStaffPo> inserts;
    
    private String insertsStr;

    public List<RoleassRoleStaffPo> getInserts() {
        return inserts;
    }

    public void setInserts(List<RoleassRoleStaffPo> input) {
        this.inserts = input;
    }
    public String getInsertsStr() {
        return "";
    }

    public void setInsertsStr(String input) {
        JSONArray jsonArray = JSONArray.fromObject(input);
        this.inserts = (List<RoleassRoleStaffPo>) JSONArray.toList(jsonArray, RoleassRoleStaffPo.class); 
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}