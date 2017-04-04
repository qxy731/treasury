package com.soule.app.pfm.tm.propdef;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.app.pfm.tm.BaseTar;
import com.soule.app.pfm.tm.tools.TarCodeUtils;
import com.soule.app.sys.staff.StaffServiceImpl;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.base.service.keygen.IKeyGenerator;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.ContextUtil;
import com.soule.comm.tools.ObjectUtil;
/**
 * 指标分类业务操作
 */
@Service("propDefService")
public class PropDefServiceImpl implements IPropDefService {
    // 数据库通用操作
    private static final Log logger = LogFactory.getLog(StaffServiceImpl.class);
    private static final String GET_PROPDEF="propdef.getPropDef";
    private static final String GET_PROPDEF_COUNT="propdef.getPropDefCount";
    private static final String INSERT_PROPDEF="propdef.savePropDef";
    private static final String UPD_PROPDEF="propdef.updPropDef";
    
    private static final String DEL_PROPDEF="propdef.delPropDefById";
    private static final String GET_ONE_BYKEY="propdef.getPropDefByKey";
    
    private static final String REMOVE_PROPDEF="propdef.delPropDef";
    
    private static final String GET_PROPDEF_BYSORT="propdef.getPropDefBySort";
    private static final String GET_PROPDEF_BYTARNAME="propdef.getPropDefByName";
    //实体表名
    public static final String TABLE_NAME="PFM_TM_PROP_DEF";
    
    @Autowired
    private IKeyGenerator keyg;
    /**
     * 指标分类的查询
     */
    @SuppressWarnings("unchecked")
    public PropDefQueryOut query(PropDefQueryIn in)
        throws ServiceException {
        PropDefQueryOut out = new PropDefQueryOut();
        try {
            IDefaultService defService = (IDefaultService) ContextUtil.getBean("defaultService");
            Map<String,Object> condition = new HashMap<String, Object>();
            condition.put("tarCode", in.getTarCode());
            condition.put("tarName", in.getTarName());
            condition.put("createOrg", in.getCreateOrg());
            condition.put("tarSortCode", in.getTarSortCode());
            if(BaseTar.APPOBJ_PERSONCODE.equals(in.getTarScope())){
                List<String> tarScopeList=Arrays.asList(BaseTar.APPOBJ_PERSONCODE,BaseTar.APPOBJ_ORGANDPERCODE);
                condition.put("tarScopeList", tarScopeList);
            }
            if(BaseTar.APPOBJ_ORGCODE.equals(in.getTarScope())){
                List<String> tarScopeList=Arrays.asList(BaseTar.APPOBJ_ORGCODE,BaseTar.APPOBJ_ORGANDPERCODE);
                condition.put("tarScopeList", tarScopeList);
            }
            if(BaseTar.APPOBJ_ORGANDPERCODE.equals(in.getTarScope())){
                List<String> tarScopeList=Arrays.asList(BaseTar.APPOBJ_ORGANDPERCODE);
                condition.put("tarScopeList", tarScopeList);
            }
            condition.put("tarStatus", PropDefPo.STATUS_INPUT);
            List totalnum = defService.getIbatisMediator().find(GET_PROPDEF_COUNT, condition);
            long total = 0L;
            if (!(ObjectUtil.isEmpty(totalnum))) {
                total = ((Long) totalnum.get(0)).longValue();
            }
            int pagesize = in.getInputHead().getPageSize();
            if (pagesize < 0) {
                pagesize = 10;
            }
            int pageno = in.getInputHead().getPageNo();
            int start = (pageno - 1) * pagesize;
            if (total > start) {
                List<PropDefPo> ret = (List<PropDefPo>)defService.getIbatisMediator().find(GET_PROPDEF, condition, start, pagesize);
                out.setPropDef(ret);
            }
            out.getResultHead().setTotal(total);
            AppUtils.setResult(out,MsgConstants.I0000);
        } catch (DbAccessException e) {
            logger.error("DB", e);
            AppUtils.setResult(out, MsgConstants.E0002);
        } catch (Exception e) {
            logger.error("SERVICE", e);
            AppUtils.setResult(out, MsgConstants.E0000);
        }
        return out;
    }
    /**
     * 修改指标信息
     */
    public PropDefUpdateOut update(PropDefUpdateIn in)
        throws ServiceException,DbAccessException {
        PropDefUpdateOut out = new PropDefUpdateOut();
        IDefaultService defService = (IDefaultService) ContextUtil.getBean("defaultService");
        PropDefPo po = in.getModifyPropDef();
        if (po != null) {
            po.setLastUpdUser("admin");
            po.setLastUpdTime(new Date());
            po.setLastUpdTime(new Date());
            int i = defService.getIbatisMediator().update(UPD_PROPDEF, po);
            if (i == 1) {
                AppUtils.setResult(out, MsgConstants.I0001);
            }
        }
        return out;
    }
    /**
     * 新增指标信息
     */
    public PropDefInsertOut insert(PropDefInsertIn in)
        throws ServiceException,DbAccessException {
        PropDefInsertOut out = new PropDefInsertOut();
        IDefaultService defService = (IDefaultService) ContextUtil.getBean("defaultService");
        TarCodeUtils tarUtils=new TarCodeUtils(keyg,TABLE_NAME);
        String primaryKey=tarUtils.gerneratedKey("X", null);
        PropDefPo po = in.getNewPropDef();
        po.setTarCode(primaryKey);
        if (po != null) {
            po.setCreateUser("admin");
            po.setCreateTime(new Date());
            int i = defService.getIbatisMediator().save(INSERT_PROPDEF, po);
            if (i == 1) {
            	out.setTarCode(po.getTarCode());
                AppUtils.setResult(out, MsgConstants.I0001);
            }
        }
        return out;
    }

	public PropDefDeleteOut delete(PropDefDeleteIn in) throws ServiceException,
			DbAccessException {
		PropDefDeleteOut out = new PropDefDeleteOut();
		int count = 0;
		IDefaultService defService = (IDefaultService) ContextUtil
				.getBean("defaultService");
		if (in != null && in.getDeletes() != null && in.getDeletes().size() > 0) {
			String[] tarCodeArray = new String[in.getDeletes().size()];
			for (int i = 0; i < in.getDeletes().size(); i++) {
				tarCodeArray[i] = in.getDeletes().get(i).getTarCode();
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tarCodeList", tarCodeArray);
			params.put("tarStatus", PropDefPo.STATUS_DEL);
			count = defService.getIbatisMediator().update(REMOVE_PROPDEF, params);
		}
		if (count == 0) {
			AppUtils.setResult(out, MsgConstants.W0000, new Object[] { "没有一条记录被删除" });
		} else {
			AppUtils.setResult(out, MsgConstants.I0000);
		}

		return out;
	}
	public PropDefPo getPropDefById(String tarCode) throws ServiceException {
        try {
            IDefaultService defService = (IDefaultService) ContextUtil.getBean("defaultService");
            PropDefPo condition=new PropDefPo();
            condition.setTarCode(tarCode);
            PropDefPo propdef = (PropDefPo)defService.getIbatisMediator().findById(GET_ONE_BYKEY, condition);
            return propdef;    
        } catch (DbAccessException e) {
            logger.error("DB", e);
            throw new ServiceException(e.getErrorCode(),"数据库错误");
        } 
       
	}
	@SuppressWarnings("unchecked")
    public PropDefPo getPropDefByTarName(String tarName){
        try {
            IDefaultService defService = (IDefaultService) ContextUtil.getBean("defaultService");
            List<PropDefPo> propdefs =(List<PropDefPo>) defService.getIbatisMediator().find(GET_PROPDEF_BYTARNAME, tarName);
            if(propdefs!=null&&propdefs.size()>0){
                return propdefs.get(0);
            }
            return null;    
        } catch (DbAccessException e) {
            logger.error("DB", e);
        } 
        return null;
    }
    @SuppressWarnings("unchecked")
	public List<PropDefPo> getPropDefListBySortCode(String tarSortCode)
        throws ServiceException {
        try {
            IDefaultService defService = (IDefaultService) ContextUtil.getBean("defaultService");
            List<PropDefPo> ret = (List<PropDefPo>)defService.getIbatisMediator().find(GET_PROPDEF_BYSORT, tarSortCode);
            return ret;
        } catch (DbAccessException e) {
            logger.error("DB", e);
            throw new ServiceException("E0002","DB Is Error");
        } catch (Exception e) {
            logger.error("SERVICE", e);
            throw new ServiceException("E0000","Server Is Error");
        }
       
    }

}