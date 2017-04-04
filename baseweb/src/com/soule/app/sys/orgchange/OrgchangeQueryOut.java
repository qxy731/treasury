package com.soule.app.sys.orgchange;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数员工机构变更:查询员工机构变更
 */
public class OrgchangeQueryOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<OrgchangeOrgChangePo> orgChange;


    public List<OrgchangeOrgChangePo> getOrgChange() {
        return orgChange;
    }

    public void setOrgChange(List<OrgchangeOrgChangePo> output) {
        this.orgChange = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}