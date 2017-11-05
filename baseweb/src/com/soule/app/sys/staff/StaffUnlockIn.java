package com.soule.app.sys.staff;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输出参数人员:解锁人员
 */
public class StaffUnlockIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private List<StaffStaffPo> unlocks;

    public List<StaffStaffPo> getUnlocks() {
        return unlocks;
    }

    public void setUnlocks(List<StaffStaffPo> unlocks) {
        this.unlocks = unlocks;
    }
    public String getDeletesStr() {
        return "";
    }

    public void setDeletesStr(String unlocks) {
        JSONArray jsonArray = JSONArray.fromObject(unlocks);
        this.unlocks = (List<StaffStaffPo>) JSONArray.toList(jsonArray, StaffStaffPo.class); 
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}