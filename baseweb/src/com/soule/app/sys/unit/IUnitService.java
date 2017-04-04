package com.soule.app.sys.unit;

import java.util.List;
import java.util.Map;

import com.soule.base.media.DbAccessException;
import com.soule.base.service.ServiceException;

/** 
 * @author peter731
 * @version Create On：2011-12-6 
 * 
 */
public interface IUnitService {	
	/**
	 * 总行下第一层机构
	 * @return
	 * @throws DbAccessException
	 */
	public List<UnitPo> getUnitTreeModel() throws DbAccessException;
	/**
	 * 根据unitId获取组织信息
	 * @param unitId
	 * @return
	 * @throws DbAccessException
	 */
	public UnitPo getUnitById(String unitId) throws DbAccessException;
	/**
	 * 根据unitId获取组织信息
	 * 不查询上级组织名称
	 * @param unitId
	 * @return
	 * @throws DbAccessException
	 */
	public UnitPo getOneUnitById(String unitId) throws DbAccessException;
	/**
	 * 根据unitId删除组织
	 * @param unitId
	 * @return
	 * @throws DbAccessException
	 */
	public int deleteUnit(String unitId) throws DbAccessException;
	/**
	 * 根据unitId获取组织下面的子组织
	 * @param unitId
	 * @return
	 * @throws DbAccessException
	 */
	public List<UnitPo> getSubUnitTreeModel(String unitId) throws DbAccessException;
	/**
	 * 添加新组织
	 * @param unitId
	 * @return
	 * @throws DbAccessException
	 */
	public int insertUnit(Map map) throws DbAccessException;
	/**
	 * 根据unitId更改组织
	 * @param unitId
	 * @param unitName
	 * @return
	 * @throws DbAccessException
	 */
	public int updateUnit(Map map) throws DbAccessException;
	
	/**
     * 查询
	 * @throws ServiceException 
     */
    public UnitQueryOut query(UnitQueryIn in) throws ServiceException;
	
}
