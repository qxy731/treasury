package com.soule.app.sys.unit;

import java.util.List;
import java.util.Map;

import com.soule.base.media.DbAccessException;


/** 
 * @author peter731
 * @version Create On：2011-12-6 
 * 
 */
public interface IUnitMapService {	
	
	//删除上级组织
	public int deleteSuperUnits(Map map) throws DbAccessException;
	//删除下级组织
	public int deleteSubUnits(Map map) throws DbAccessException;
	//删除上下级组织关系
	public int deleteAllUnitsById(Map map) throws DbAccessException;
	//新增下属组织关联关系
	public int insertSubUnitMap(String unitId,List list) throws DbAccessException;
	//新增上属组织关联关系
	public int insertSuperUnitMap(String unitId,List list) throws DbAccessException;
	
}
