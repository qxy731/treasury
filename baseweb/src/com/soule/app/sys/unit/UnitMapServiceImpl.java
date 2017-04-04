package com.soule.app.sys.unit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;

/**
 * @author peter
 * @version Create On：2011-12-6
 */
@Service
public class UnitMapServiceImpl implements IUnitMapService {
	@Autowired
    private IDefaultService defService;
	/**
	 * Map map存放一个键值对	
	 * relaUnitId：下级组织号
	 */
	public int deleteSuperUnits(Map map) throws DbAccessException {
		return defService.getIbatisMediator().delete("sysmgr_unitmap.deleteSuperUnits", map);
	}
	
	/**
	 * Map map存放一个键值对
	 * unitId：上级组织号	 
	 */
	public int deleteSubUnits(Map map) throws DbAccessException {
		return defService.getIbatisMediator().delete("sysmgr_unitmap.deleteSubUnits", map);
	}
	
	/**
	 * 删除与组织相关所有的映射关系
	 * Map map存放一个键值对
	 * unitId：组织号
	 */
	public int deleteAllUnitsById(Map map) throws DbAccessException {
		return defService.getIbatisMediator().delete("sysmgr_unitmap.deleteAllUnitsById", map);
	}
	
	/**
	 * unitId为新增或修改组织的组织编码
	 * list中存放的是与该组织有关联关系的组织对象
	 */
	public int insertSubUnitMap(String unitId,List list) throws DbAccessException {
		int count = 0;
		for(int i=0;i<list.size();i++){
			UnitPo vo = (UnitPo)list.get(i);
			Map map = new HashMap();
			map.put("unitId", unitId);
			map.put("relaUnitId",vo.getUnitId());
			int result = defService.getIbatisMediator().save("sysmgr_unitmap.insertUnit", map);
			count+=result;
		}
		return count;
	}
	
	public int insertSuperUnitMap(String unitId,List list) throws DbAccessException {
		int count = 0;
		for(int i=0;i<list.size();i++){
			UnitPo vo = (UnitPo)list.get(i);
			Map map = new HashMap();
			map.put("unitId",vo.getUnitId());
			map.put("relaUnitId",unitId);
			int result = defService.getIbatisMediator().save("sysmgr_unitmap.insertUnit", map);
			count+=result;
		}
		return count;
	}
	
}