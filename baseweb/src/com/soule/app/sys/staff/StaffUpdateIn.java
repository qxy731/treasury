
package com.soule.app.sys.staff;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输出参数人员:修改人员信息
 */
public class StaffUpdateIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private StaffStaffPo modifyStaff;

    public StaffStaffPo getModifyStaff()  {
        return modifyStaff;
    }

    public void setModifyStaff(StaffStaffPo input) {
        this.modifyStaff = input;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}