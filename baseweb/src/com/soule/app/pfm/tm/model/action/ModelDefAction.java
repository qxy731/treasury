package com.soule.app.pfm.tm.model.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.app.pfm.tm.model.po.ModelDefPo;
import com.soule.app.pfm.tm.model.servcie.IModelDefService;
import com.soule.app.sys.logon.ILogonInfoService;
import com.soule.app.sys.staff.StaffDeleteIn;
import com.soule.app.sys.staff.StaffInsertIn;
import com.soule.app.sys.staff.StaffUpdateIn;
import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;

@Namespace("/modelDef")
public class ModelDefAction extends BaseAction {
    private static final long serialVersionUID = 1295775504760653068L;
    @Autowired
    private IModelDefService modelDef;
    @Autowired
    private ILogonInfoService logonInfoService;
    /**
     * 查询人员 输入参数 
     */
    private ModelDefQueryIn queryIn;
    /**
     * 修改人员信息 输入参数 
     */
    //private StaffUpdateIn updateIn;
    /**
     * 新增人员 输入参数 
     */
    //private StaffInsertIn insertIn;
    /**
     * 删除人员 输入参数 
     */
    //private StaffDeleteIn deleteIn;
    
    
    public void doInit() {
    }
    public String query() {
        try {
           // queryIn.getInputHead().setPageNo(this.getPage());
           // queryIn.getInputHead().setPageSize(this.getPagesize());
            List<ModelDefPo> modelDef = new ArrayList<ModelDefPo>();
            for (int i=0;i<2;i++){
            	ModelDefPo modelDefPo = new ModelDefPo();
            	modelDefPo.setModelCode("001"+i);
            	modelDefPo.setModelName("name"+i);
            	modelDef.add(modelDefPo);
            }
            
            ModelDefQueryOut result = new ModelDefQueryOut();
            result.setModelDef(modelDef);
            ServiceResult head = result.getResultHead();
            rows = result.getModelDef();
            total=(int)result.getResultHead().getTotal();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
   /* public String update() {
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
        //StaffInsertIn in = insertIn;
        try {
            
            IServiceResult result = staffService.insert(insertIn);
            if(null!=insertIn&&insertIn.getNewStaff()!=null){
              LogonInfoPo logonInfo=new LogonInfoPo();
              logonInfo.setStaffId(insertIn.getNewStaff().getStaffId());
              logonInfo.setLogonId(insertIn.getNewStaff().getLogonId());
              logonInfo.setPassword(insertIn.getNewStaff().getPassword());
              logonInfo.setLockFlag(YesNoFlag.NO.getValue());
              logonInfo.setValidFlag(YesNoFlag.YES.getValue());
              logonInfoService.insert(logonInfo);
            }
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String delete() {
        StaffDeleteIn in = deleteIn;
        try {
            // TODO
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
    }*/
  
    /**
     * 查询人员
     */
    @JSON(serialize=false)
    public ModelDefQueryIn getQueryIn() {
		return queryIn;
	}
	public void setQueryIn(ModelDefQueryIn queryIn) {
		this.queryIn = queryIn;
	}
    
    /**
     * 修改人员信息
     
    @JSON(serialize=false)
    public StaffUpdateIn getUpdateIn() {
        return this.updateIn;
    }*/
	/**
     * 修改人员信息
     
    public void setUpdateIn(StaffUpdateIn in) {
        this.updateIn = in;
    }*/
    /**
     * 新增人员
     
    @JSON(serialize=false)
    public StaffInsertIn getInsertIn() {
        return this.insertIn;
    }*/
    /**
     * 新增人员
     
    public void setInsertIn(StaffInsertIn in) {
        this.insertIn = in;
    }*/
    /**
     * 删除人员
     
    @JSON(serialize=false)
    public StaffDeleteIn getDeleteIn() {
        return this.deleteIn;
    }*/
    /**
     * 删除人员
     
    public void setDeleteIn(StaffDeleteIn in) {
        this.deleteIn = in;
    }*/
}
