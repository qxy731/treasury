
package com.soule.app.sys.staff;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输出参数人员:删除人员
 */
public class StaffDeleteIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private List<StaffStaffPo> deletes;

    public List<StaffStaffPo> getDeletes() {
        return deletes;
    }

    public void setDeletes(List<StaffStaffPo> input) {
        this.deletes = input;
    }
    public String getDeletesStr() {
        return "";
    }

    public void setDeletesStr(String input) {
        JSONArray jsonArray = JSONArray.fromObject(input);
        this.deletes = (List<StaffStaffPo>) JSONArray.toList(jsonArray, StaffStaffPo.class); 
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}