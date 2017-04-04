package com.soule.app.sys.staff;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数人员:查询人员
 */
public class StaffQueryOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<StaffStaffPo> staff;


    public List<StaffStaffPo> getStaff() {
        return staff;
    }

    public void setStaff(List<StaffStaffPo> output) {
        this.staff = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}