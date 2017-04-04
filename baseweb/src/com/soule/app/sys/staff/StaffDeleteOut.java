package com.soule.app.sys.staff;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数人员:删除人员
 */
public class StaffDeleteOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();


    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}