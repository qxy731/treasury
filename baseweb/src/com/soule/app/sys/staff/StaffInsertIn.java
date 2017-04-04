package com.soule.app.sys.staff;
import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数人员:新增人员
 */
public class StaffInsertIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private StaffStaffPo newStaff;

    public StaffStaffPo getNewStaff()  {
        return newStaff;
    }

    public void setNewStaff(StaffStaffPo input) {
        this.newStaff = input;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}