package com.soule.app.sys.orgchange;


import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;

/**
 * 员工机构变更维护模块表现层处理类
 */
@Namespace("/sys")
public class OrgchangeAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Autowired
    private IOrgchangeService service;

    /**
     * 查询员工机构变更 输入参数 
     */
    private OrgchangeQueryIn queryIn; //= new OrgchangeQueryIn();
    
    public void doInit() {
    }
    public String query() {
        OrgchangeQueryIn in = queryIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            OrgchangeQueryOut result = service.query(in);
            ServiceResult head = result.getResultHead();
            // TODO
            this.rows = result.getOrgChange();
            this.setTotal(head.getTotal());
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
            //test.insert update
            /*StaffStaffPo staff=new StaffStaffPo();
            staff.setOwnerUnitid("070667803");
            staff.setStaffId("06035");
            service.update(staff);
            
            staff.setStaffId("02033");
            service.insert(staff);*/
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }

    /**
     * 查询员工机构变更
     */
    @JSON(serialize=false)
    public OrgchangeQueryIn getQueryIn() {
        return this.queryIn;
    }
    /**
     * 查询员工机构变更
     */
    public void setQueryIn(OrgchangeQueryIn in) {
        this.queryIn = in;
    }
}
