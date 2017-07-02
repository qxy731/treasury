package com.soule.app.pfm.tm.model.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.app.pfm.tm.model.po.ModelDefPo;
import com.soule.app.pfm.tm.model.po.ModelTarPo;
import com.soule.app.pfm.tm.model.servcie.IModelDefService;
import com.soule.app.pfm.tm.qtydef.QtyDefPo;
import com.soule.app.sys.logon.ILogonInfoService;
import com.soule.app.sys.staff.StaffDeleteIn;
import com.soule.app.sys.staff.StaffInsertIn;
import com.soule.app.sys.staff.StaffUpdateIn;
import com.soule.base.action.BaseAction;
import com.soule.base.service.IServiceResult;
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
    private ModelDefUpdateIn updateIn;
    /**
     * 新增人员 输入参数 
     */
    private ModelDefInsertIn insertIn;
    /**
     * 删除人员 输入参数 
     */
    private ModelDefDeleteIn deleteIn;
    
    
    public ModelDefDeleteIn getDeleteIn() {
		return deleteIn;
	}
	public void setDeleteIn(ModelDefDeleteIn deleteIn) {
		this.deleteIn = deleteIn;
	}
	public void doInit() {
    }
    public String query() {
        try {
            queryIn.getInputHead().setPageNo(this.getPage());
            queryIn.getInputHead().setPageSize(this.getPagesize());
            
            
            ModelDefQueryOut result =this.modelDef.query(queryIn);
            List<ModelDefPo> modelDef =result.getModelDef();
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
  
    
    public String insert() {
    	ModelDefInsertIn in = insertIn;
        try {
            
        	ModelDefInsertOut result = modelDef.insertModel(in);
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
    	ModelDefDeleteIn in = deleteIn;
        try {
            // TODO
            IServiceResult result = modelDef.deleteModel(deleteIn);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    
    
   @Action(results={@Result(name="updateui", location="/jsp/pfm/target/targetModel/modelUpdate.jsp")})
    public String updateUI() {
        try {
        	ModelDefUpdateQueryOut result = modelDef.queryModelTar(queryIn);
            ServiceResult head = result.getResultHead();
            updateIn=new ModelDefUpdateIn();
            updateIn.setModelDef(result.getModelDef());
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return "updateui";
    }
   
   
   public String queryTarByCode() {
       try {
    	   queryIn.getInputHead().setPageNo(this.getPage());
           queryIn.getInputHead().setPageSize(this.getPagesize());
           
           
           ModelDefUpdateQueryOut result =this.modelDef.queryTarByCode(queryIn);
           List<QtyDefPo> modelDef =result.getTarList();
           ServiceResult head = result.getResultHead();
           rows = result.getTarList();
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
	   ModelDefUpdateIn in = updateIn;
       try {
           IServiceResult result = modelDef.updateModel(in);
           ServiceResult head = result.getResultHead();
           this.setRetCode(head.getRetCode());
           this.setRetMsg(head.getRetMsg());
       }
       catch(Exception e) {
           handleError(e);
       }
       return JSON;
   }
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
	public ModelDefInsertIn getInsertIn() {
		return insertIn;
	}
	public void setInsertIn(ModelDefInsertIn insertIn) {
		this.insertIn = insertIn;
	}
	public ModelDefUpdateIn getUpdateIn() {
		return updateIn;
	}
	public void setUpdateIn(ModelDefUpdateIn updateIn) {
		this.updateIn = updateIn;
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
