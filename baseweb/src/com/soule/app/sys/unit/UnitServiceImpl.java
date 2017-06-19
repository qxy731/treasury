package com.soule.app.sys.unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.comm.enu.UnitStatus;
import com.soule.comm.enu.YesNoFlag;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.ContextUtil;
import com.soule.comm.tools.ObjectUtil;
import com.soule.comm.tools.StringUtil;

/**
 * @author peter
 * @version Create On：2011-12-6
 */
@Service
public class UnitServiceImpl implements IUnitService {

	private static final Log logger = LogFactory.getLog(UnitServiceImpl.class);
    private String QUERY_ROOT_UNITS_IBATIS = "sysmgr_unit.getRootUnits";
    private String QUERY_ONE_UNIT_IBATIS = "sysmgr_unit.getUnitById";
    private String QUERY_ONE_UNITSAMPLE_IBATIS = "sysmgr_unit.getOneUnitById";
    private String DELETE_ONE_UNIT_IBATIS = "sysmgr_unit.deleteUnitById";
    private String QUERY_SUB_UNITS_IBATIS = "sysmgr_unit.querySubUnitsById";
    private String INSERT_ONE_UNIT_IBATIS = "sysmgr_unit.insertUnit";
    private String UPDATE_ONE_UNIT_ALL_IBATIS = "sysmgr_unit.updateUnitById";
    private String QUERY_CON_UNIT_DETAIL_IBATIS = "sysmgr_unit.queryUnitsByCondition";
    private String QUERY_CON_UNIT_COUNT_IBATIS = "sysmgr_unit.queryUnitsCountByCondition";

    @Autowired
    private IUnitMapService unitMapService;
    @Autowired
    private IDefaultService defService;
    public List<UnitPo> getUnitTreeModel() throws DbAccessException {
        List<UnitPo> orgList = defService.getIbatisMediator().find(QUERY_ROOT_UNITS_IBATIS, new HashMap());
        return orgList;
    }

    public UnitPo getUnitById(String unitId) throws DbAccessException {
        Map map = new HashMap();
        map.put("unitId", unitId);
        return (UnitPo) defService.getIbatisMediator().findById(QUERY_ONE_UNIT_IBATIS, map);
    }
    
    public UnitPo getOneUnitById(String unitId) throws DbAccessException {
        Map map = new HashMap();
        map.put("unitId", unitId);
        return (UnitPo) defService.getIbatisMediator().findById(QUERY_ONE_UNITSAMPLE_IBATIS, map);
    }

    /**
     * 根据unitId删除组织
     * 
     * @param unitId
     * @return
     * @throws DbAccessException
     */
    public int deleteUnit(String unitId) throws DbAccessException {
        Map map = new HashMap();
        map.put("unitId", unitId);
        UnitPo unit = getOneUnitById(unitId);
        int result = defService.getIbatisMediator().delete(DELETE_ONE_UNIT_IBATIS, map);
        if(result==1){
	    	unitMapService.deleteAllUnitsById(map);
	    	//删除之后查询父组织是否还有子节点，如果没有，要重置父节点的节点状态
	    	updateSuperLeafFlagForDelete(unit,YesNoFlag.YES.getValue());
        }
    	return result;
    }
    //删除之后查询父组织是否还有子节点，如果没有，要重置父节点的节点状态
    private void updateSuperLeafFlagForDelete(UnitPo unit,String leafFlag) throws DbAccessException{
    	if(unit!=null){
    		String unitStatus = unit.getUnitStatus();
    		if(UnitStatus.VALID.getValue().equals(unitStatus)){
	    		String superUnitId = unit.getSuperUnitId();
	    		if(!StringUtil.isEmpty(superUnitId)){
			    	List list = getSubUnitTreeModel(superUnitId);
			    	if(list==null||list.size()==0){
				        Map map=new HashMap();
				        map.put("unitId",superUnitId);
				        map.put("leafFlag",leafFlag);
				        defService.getIbatisMediator().update(UPDATE_ONE_UNIT_ALL_IBATIS, map);
			        }
	    		}
    		}
    	}
    }

    public List<UnitPo> getSubUnitTreeModel(String unitId) throws DbAccessException {
        Map map = new HashMap();
        map.put("unitId", unitId);
        List<UnitPo> orgList = defService.getIbatisMediator().find(QUERY_SUB_UNITS_IBATIS, map);
        return orgList;
    }

    /**
     * 添加新组织
     * @param Map map
     * @return
     * @throws DbAccessException
     */
    public int insertUnit(Map map) throws DbAccessException {
        int result = defService.getIbatisMediator().save(INSERT_ONE_UNIT_IBATIS, map);
        if(result==1){
        	String unitId = map.get("unitId").toString();
        	UnitPo unit = getOneUnitById(unitId);
        	List list =  getAllSuperUnitsBySuperUnit(unit);
        	unitMapService.insertSuperUnitMap(unitId,list);
        	//更新上级组织的叶子节点状态
        	updateSuperLeafFlag(unit,YesNoFlag.NO.getValue());
        }
        return result;
    }
    //更新上级组织的叶子节点状态
    private void updateSuperLeafFlag(UnitPo unit,String leafFlag) throws DbAccessException{
    	if(unit!=null){
	    	String superUnitId = unit.getSuperUnitId();
	    	String unitStatus = unit.getUnitStatus();
	    	if(!StringUtil.isEmpty(superUnitId)){
	        	if(UnitStatus.VALID.getValue().equals(unitStatus)){
		        	 Map map=new HashMap();
		        	 map.put("unitId",superUnitId);
		        	 map.put("leafFlag",leafFlag);
		        	 defService.getIbatisMediator().update(UPDATE_ONE_UNIT_ALL_IBATIS, map);
	        	}
	    	}
    	}
    }

    public int updateUnit(Map map) throws DbAccessException {
        int result = defService.getIbatisMediator().update(UPDATE_ONE_UNIT_ALL_IBATIS, map);
        if(result==1){
        	boolean flag = false;
        	String superUnitId = map.get("superUnitId")==null?"":map.get("superUnitId").toString();
        	String unitStatus = map.get("unitStatus").toString();
        	map=new HashMap();
        	if(!StringUtil.isEmpty(superUnitId)){
	        	if(UnitStatus.VALID.getValue().equals(unitStatus)){      		 
			        	 flag = true;//状态为有效时
			        	 map.put("leafFlag",YesNoFlag.NO.getValue());
	        	}else{       		
	        		List ret = getSubUnitTreeModel(superUnitId);
	        		if(ret==null||ret.size()==0){
	        			 flag=true;//状态为无效时并且父组织没有有效的子节点
	        			 map.put("leafFlag",YesNoFlag.YES.getValue());
	        		}
	        	}
        	}
        	if(flag){//更新上级组织的叶子节点状态
	        	 map.put("unitId",superUnitId);
	        	 defService.getIbatisMediator().update(UPDATE_ONE_UNIT_ALL_IBATIS, map);
        	}
        }
        return result;
    }
    
    
    //获取下级组织
    public List getAllSubUnitsBySuperUnit(UnitPo unit)throws DbAccessException {
		List all = new ArrayList();
		Stack stack = new Stack();
		stack.push(unit);
		all.add(unit);
		while (!stack.isEmpty()) {
			UnitPo superUnitVO = (UnitPo) stack.pop();
			List ret = getSubUnitTreeModel(superUnitVO.getUnitId());
			if (ret != null && !ret.isEmpty()){
				for (int i = 0; i < ret.size(); i++) {
					UnitPo vo = (UnitPo) ret.get(i);
					stack.push(vo);
					all.add(vo);
				}
			}
		}
		return all;
	}
    
   //获取上级组织
    public List getAllSuperUnitsBySuperUnit(UnitPo unit)throws DbAccessException {
		List all = new ArrayList();
		Stack stack = new Stack();
		stack.push(unit);
		all.add(unit);
		while (!stack.isEmpty()) {
			UnitPo superUnitVO = (UnitPo) stack.pop();
			String superUnitId = superUnitVO.getSuperUnitId();
			if(StringUtil.isEmpty(superUnitId)){
				continue;
			}else{
				UnitPo vo = getOneUnitById(superUnitId);
				if(vo!=null){
					stack.push(vo);
					all.add(vo);
				}else{
					continue;
				}
			}
		}
		return all;
	}
    //获取上下组织
    public List getAllUnitsBySuperUnit(UnitPo unit)throws DbAccessException {
    	List all = new ArrayList();
		Stack stack = new Stack();
		stack.push(unit);
		all.add(unit);
		while (!stack.isEmpty()) {
			UnitPo superUnitVO = (UnitPo) stack.pop();
			String superUnitId = superUnitVO.getSuperUnitId();
			if(StringUtil.isEmpty(superUnitId)){
				continue;
			}else{
				UnitPo vo = getOneUnitById(superUnitId);
				if(vo!=null){
					stack.push(vo);
					all.add(vo);
				}else{
					continue;
				}
			}
		}
		stack.push(unit);
		while (!stack.isEmpty()) {
			UnitPo superUnitVO = (UnitPo) stack.pop();
			List ret = getSubUnitTreeModel(superUnitVO.getUnitId());
			if (ret != null && !ret.isEmpty()){
				for (int i = 0; i < ret.size(); i++) {
					UnitPo vo = (UnitPo) ret.get(i);
					stack.push(vo);
					all.add(vo);
				}
			}
		}
    	return all;
    }
    
    /**
     * 查询
     */
    public UnitQueryOut query(UnitQueryIn in) throws ServiceException {
    	UnitQueryOut out = new UnitQueryOut();
        try {
            IDefaultService defService = (IDefaultService) ContextUtil.getBean("defaultService");
            Map condition = new HashMap();
            UnitPo unit=new UnitPo();
            unit.setUnitId(in.getUnitId());
            unit.setUnitName(in.getUnitName());
           // unit.setUnitKind(in.getUnitKind());
            unit.setUnitLevel(in.getUnitLevel());
            unit.setUnitStatus(in.getUnitStatus());
            unit.setSuperUnitId(in.getSuperUnitId());
            condition.put("unit",unit);
            List totalnum = defService.getIbatisMediator().find(this.QUERY_CON_UNIT_COUNT_IBATIS, condition);
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
                List ret = defService.getIbatisMediator().find(this.QUERY_CON_UNIT_DETAIL_IBATIS, condition, start, pagesize);
                out.setUnit(ret);
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
}