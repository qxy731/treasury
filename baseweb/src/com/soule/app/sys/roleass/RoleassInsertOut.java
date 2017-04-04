package com.soule.app.sys.roleass;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数角色分配:新增角色人员
 */
public class RoleassInsertOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();


    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}