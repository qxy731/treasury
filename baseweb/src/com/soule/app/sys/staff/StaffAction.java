package com.soule.app.sys.staff;


import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.app.sys.logon.ILogonInfoService;
import com.soule.base.action.BaseAction;
import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;
import com.soule.base.service.logon.LogonInfoPo;
import com.soule.comm.enu.YesNoFlag;

/**
 * 人员维护模块表现层处理类
 */
@Namespace("/sys")
public class StaffAction extends BaseAction {
    private static final long serialVersionUID = 1295775504760653068L;
    @Autowired
    private IStaffService staffService;
    @Autowired
    private ILogonInfoService logonInfoService;
    /**
     * 查询人员 输入参数 
     */
    private StaffQueryIn queryIn;
    /**
     * 修改人员信息 输入参数 
     */
    private StaffUpdateIn updateIn;
    /**
     * 新增人员 输入参数 
     */
    private StaffInsertIn insertIn;
    /**
     * 删除人员 输入参数 
     */
    private StaffDeleteIn deleteIn;
    
    /**
     * 解锁人员 输入参数 
     */
    private StaffUnlockIn unlockIn;
    
    
    public void doInit() {
    }
    public String query() {
        try {
            queryIn.getInputHead().setPageNo(this.getPage());
            queryIn.getInputHead().setPageSize(this.getPagesize());
            StaffQueryOut result = staffService.query(queryIn);
            ServiceResult head = result.getResultHead();
            rows = result.getStaff();
            total=(int)result.getResultHead().getTotal();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String update() {
        StaffUpdateIn in = updateIn;
        try {
            IServiceResult result = staffService.update(in);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String insert() {
        try {
        	StaffQueryIn qIn =new  StaffQueryIn();
            qIn.setStaffId(insertIn.getNewStaff().getStaffId());
            StaffQueryOut qr = staffService.query(qIn);
            List list = qr.getStaff();
            if(null==list || list.size()==0){
            	 IServiceResult result = staffService.insert(insertIn);
                 if(null!=insertIn&&insertIn.getNewStaff()!=null){
                   LogonInfoPo logonInfo=new LogonInfoPo();
                   logonInfo.setStaffId(insertIn.getNewStaff().getStaffId());
                   logonInfo.setLogonId(insertIn.getNewStaff().getLogonId());
                   logonInfo.setPassword(insertIn.getNewStaff().getPassword());
                   logonInfoService.insert(logonInfo);
                 }
                 ServiceResult head = result.getResultHead();
                 this.setRetCode(head.getRetCode());
                 this.setRetMsg(head.getRetMsg());
            }else{
            	 this.setRetCode("C0001");
                 this.setRetMsg("员工号已重复");
            }
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String delete() {
        StaffDeleteIn in = deleteIn;
        try {
            IServiceResult result = staffService.delete(in);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    
    public String unlock() {
        StaffUnlockIn in = unlockIn;
        try {
            IServiceResult result = staffService.unlock(in);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }

    @Action(results={@Result(name="updateui", location="/jsp/sysmgr/staff/staffUpdate.jsp")})
    public String updateUI() {
        try {
            StaffQueryOut result = staffService.query(queryIn);
            ServiceResult head = result.getResultHead();
            if(null!=result.getStaff()&&result.getStaff().size()>0){
                updateIn=new StaffUpdateIn();
                updateIn.setModifyStaff(result.getStaff().get(0));
            }
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return "updateui";
    }
    /**
     * 查询人员
     */
    @JSON(serialize=false)
    public StaffQueryIn getQueryIn() {
        return this.queryIn;
    }
    /**
     * 查询人员
     */
    public void setQueryIn(StaffQueryIn in) {
        this.queryIn = in;
    }
    /**
     * 修改人员信息
     */
    @JSON(serialize=false)
    public StaffUpdateIn getUpdateIn() {
        return this.updateIn;
    }
    /**
     * 修改人员信息
     */
    public void setUpdateIn(StaffUpdateIn in) {
        this.updateIn = in;
    }
    /**
     * 新增人员
     */
    @JSON(serialize=false)
    public StaffInsertIn getInsertIn() {
        return this.insertIn;
    }
    /**
     * 新增人员
     */
    public void setInsertIn(StaffInsertIn in) {
        this.insertIn = in;
    }
    /**
     * 删除人员
     */
    @JSON(serialize=false)
    public StaffDeleteIn getDeleteIn() {
        return this.deleteIn;
    }
    /**
     * 删除人员
     */
    public void setDeleteIn(StaffDeleteIn in) {
        this.deleteIn = in;
    }
    
    @JSON(serialize=false)
	public StaffUnlockIn getUnlockIn() {
		return unlockIn;
	}
	public void setUnlockIn(StaffUnlockIn unlockIn) {
		this.unlockIn = unlockIn;
	}
    
}