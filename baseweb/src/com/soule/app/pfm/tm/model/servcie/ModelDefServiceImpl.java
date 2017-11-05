package com.soule.app.pfm.tm.model.servcie;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.app.pfm.tm.model.action.ModelDefDeleteIn;
import com.soule.app.pfm.tm.model.action.ModelDefDeleteOut;
import com.soule.app.pfm.tm.model.action.ModelDefInsertIn;
import com.soule.app.pfm.tm.model.action.ModelDefInsertOut;
import com.soule.app.pfm.tm.model.action.ModelDefQueryIn;
import com.soule.app.pfm.tm.model.action.ModelDefQueryOut;
import com.soule.app.pfm.tm.model.action.ModelDefUpdateIn;
import com.soule.app.pfm.tm.model.action.ModelDefUpdateOut;
import com.soule.app.pfm.tm.model.action.ModelDefUpdateQueryOut;
import com.soule.app.pfm.tm.model.po.ModelDefPo;
import com.soule.app.pfm.tm.model.po.ModelTarPo;
import com.soule.app.pfm.tm.qtydef.QtyDefPo;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.base.service.keygen.IKeyGenerator;
import com.soule.base.service.logon.LogonInfoPo;
import com.soule.comm.tools.AppUtils;

/**
 * 自定义报表操作
 */
@Service
public class ModelDefServiceImpl implements IModelDefService {
	@Autowired
	private IKeyGenerator keyGenerator;
    // 数据库通用操作
	@Autowired
    private IDefaultService defService;
	
    private static final Log logger = LogFactory.getLog(ModelDefServiceImpl.class);
    private String PFM_MODEDRF_LIST;
    private String TABLE_NAME;
    public ModelDefServiceImpl() {
        this.PFM_MODEDRF_LIST = "modelDef.getModelDef";
    }

    /**
     * 自定义报表的查询
     */
    public ModelDefQueryOut query(ModelDefQueryIn in) throws ServiceException {
    	ModelDefQueryOut out = new ModelDefQueryOut();
        try {
            HashMap condition = new HashMap();
            condition.put("modelCode", in.getModelCode());
            condition.put("modelName", in.getModelName());
            long total = defService.getIbatisMediator().getRecordTotal(this.PFM_MODEDRF_LIST, condition);
            int pagesize = in.getInputHead().getPageSize();
            if (pagesize < 0) {
                pagesize = 10;
            }
            int pageno = in.getInputHead().getPageNo();
            int start = (pageno - 1) * pagesize;
            if (total > start) {
                List ret = defService.getIbatisMediator().find(this.PFM_MODEDRF_LIST, condition, start, pagesize);
                out.setModelDef(ret);;
            }
            out.getResultHead().setTotal(total);
            AppUtils.setResult(out, "I0000");
        } catch (DbAccessException e) {
            logger.error("DB", e);
            AppUtils.setResult(out, "E0002");
        } catch (Exception e) {
            logger.error("SERVICE", e);
            AppUtils.setResult(out, "E0000");
        }
        return out;
    }
    
    
    public ModelDefInsertOut  insertModel(ModelDefInsertIn in) throws ServiceException{
    	ModelDefInsertOut out = new ModelDefInsertOut();
    	String code=keyGenerator.getUUIDKey();
    	ModelDefPo modelDef = new ModelDefPo();
    	LogonInfoPo logonInfo = AppUtils.getLogonUserInfo().getLogonInfo();
    	modelDef.setCreateUser(logonInfo.getLogonId());
    	modelDef.setCreateOrg(AppUtils.getLogonUserInfo().getOperUnitId());
    	modelDef.setCreateTime(new Date());
    	modelDef.setModelCode(code);
    	modelDef.setModelDesc(in.getModelDesc());
    	modelDef.setModelName(in.getModelName());
    	modelDef.setModelStatus("1");
    	 try {
			defService.getIbatisMediator().save("modelDef.insertModelDef", modelDef);
			
			 List<QtyDefPo> list = in.getTarList();
	    	 
	    	 for(int i=0;i<list.size();i++){
	    		 ModelTarPo modelTar = new ModelTarPo();
	    		 if(!"0".equals(list.get(i).getTarStatus())){
	    			 modelTar.setModelCode(code);
		    		 modelTar.setTarCode(list.get(i).getTarCode());
		    		 defService.getIbatisMediator().save("modelDef.insertModelTar", modelTar);
	    		 }
	    	 }
	    	 AppUtils.setResult(out, "I0004");
		} catch (DbAccessException e) {
			e.printStackTrace();
			AppUtils.setResult(out, "E0000");
		}
    	return out;
    }
    
    
    public ModelDefUpdateOut updateModel(ModelDefUpdateIn in) throws ServiceException{
    	ModelDefUpdateOut out = new ModelDefUpdateOut();
    	ModelDefPo modelDef = new ModelDefPo();
    	LogonInfoPo logonInfo = AppUtils.getLogonUserInfo().getLogonInfo();
    	modelDef.setLastUpdUser(logonInfo.getLogonId());
    	modelDef.setLastUpdOrg(AppUtils.getLogonUserInfo().getOperUnitId());
    	modelDef.setLastUpdTime(new Date());
    	modelDef.setModelCode(in.getModelDef().getModelCode());
    	modelDef.setModelName(in.getModelDef().getModelName());
    	modelDef.setModelDesc(in.getModelDef().getModelDesc());
    	modelDef.setModelStatus("1");
    	 try {
			defService.getIbatisMediator().save("modelDef.updateModelDef", modelDef);
			
			defService.getIbatisMediator().save("modelDef.delMdoelTar", in.getModelDef().getModelCode());
			
			 List<QtyDefPo> list = in.getTarList();
	    	 
			 for(int i=0;i<list.size();i++){
	    		 ModelTarPo modelTar = new ModelTarPo();
	    		 if(!"0".equals(list.get(i).getTarStatus())){
	    			 modelTar.setModelCode(in.getModelDef().getModelCode());
		    		 modelTar.setTarCode(list.get(i).getTarCode());
		    		 defService.getIbatisMediator().save("modelDef.insertModelTar", modelTar);
	    		 }
	    	 }
	    	 
	    	 AppUtils.setResult(out, "I0004");
		} catch (DbAccessException e) {
			e.printStackTrace();
			 AppUtils.setResult(out, "E0000");
		}
    	return out;
    }
    
    public ModelDefDeleteOut  deleteModel(ModelDefDeleteIn in) throws ServiceException{
    	ModelDefDeleteOut out = new ModelDefDeleteOut();
    	
    	try {
    		
    		for(int i=0;i<in.getTarList().size();i++){
    			String modelCode = in.getTarList().get(i).getModelCode();
    			//defService.getIbatisMediator().save("modelDef.delMdoelTar", modelCode);
    			
       		 	defService.getIbatisMediator().delete("modelDef.delMdoelDef", modelCode);
    		}
			 
	    	 AppUtils.setResult(out, "I0004");
		} catch (DbAccessException e) {
			e.printStackTrace();
			 AppUtils.setResult(out, "E0000");
		}
    	
    	return out;
    }
    
   
    
    public ModelDefUpdateQueryOut  queryModelTar(ModelDefQueryIn in) throws ServiceException{
    	ModelDefUpdateQueryOut out = new ModelDefUpdateQueryOut();
    	String modelCode= in.getModelCode();
    	HashMap<String,Object> condition = new HashMap<String,Object>();
    	condition.put("modelCode", modelCode);
    	try {
			List<ModelDefPo> modelList= defService.getIbatisMediator().find("modelDef.getModelDef", condition);
			//List<QtyDefPo> tarList = defService.getIbatisMediator().find("modelDef.getModelTarByModelCode", condition);
			
			if(null== modelList && modelList.size()<1){
				out.setModelDef(new ModelDefPo());
			}else{
				out.setModelDef(modelList.get(0));
			}
			out.getResultHead().setTotal(1);
            AppUtils.setResult(out, "I0000");
			//out.setTarList(tarList);
		} catch (DbAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return out;
    }
    
    public ModelDefUpdateQueryOut  queryTarByCode(ModelDefQueryIn in) throws ServiceException{
    	ModelDefUpdateQueryOut out = new ModelDefUpdateQueryOut();
    	String modelCode= in.getModelCode();
    	
    	try {
			 HashMap condition = new HashMap();
	            condition.put("modelCode", in.getModelCode());
	            condition.put("modelName", in.getModelName());
	            long total = defService.getIbatisMediator().getRecordTotal("modelDef.getModelTarByModelCode", condition);
	            int pagesize = in.getInputHead().getPageSize();
	            if (pagesize < 0) {
	                pagesize = 10;
	            }
	            int pageno = in.getInputHead().getPageNo();
	            int start = (pageno - 1) * pagesize;
	            if (total > start) {
	                List ret = defService.getIbatisMediator().find("modelDef.getModelTarByModelCode", condition, start, pagesize);
	                out.setTarList(ret);
	            }
	            out.getResultHead().setTotal(total);
	            AppUtils.setResult(out, "I0000");
		} catch (DbAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return out;
    }
}