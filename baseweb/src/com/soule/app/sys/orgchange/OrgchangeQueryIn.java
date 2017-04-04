package com.soule.app.sys.orgchange;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数员工机构变更:查询员工机构变更
 */
public class OrgchangeQueryIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private OrgchangeOrgChangePo orgChange;

    public OrgchangeOrgChangePo getOrgChange()  {
        return orgChange;
    }

    public void setOrgChange(OrgchangeOrgChangePo input) {
        this.orgChange = input;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}