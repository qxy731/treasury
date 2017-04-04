package com.soule.app.pfm.tm.qtydef;
import java.util.List;

import com.soule.base.media.DbAccessException;
import com.soule.base.service.ServiceException;
public interface IQtyDefService {

	/**
	 * 定量指标新增
	 * 
	 * @param in
	 * @return
	 * @throws ServiceException
	 * @throws DbAccessException
	 */
	public QtyDefInsertOut insert(QtyDefPo newQtyDef, List<String> dayScopeList,QtyExpDefPo qtyExp)
			throws ServiceException, DbAccessException;

	/**
	 * 查询定量指标
	 */
	public QtyDefQueryOut query(QtyDefQueryIn in) throws ServiceException;

	/**
	 * 根据Id查询定量指标
	 * @throws DbAccessException 
	 */
	public QtyDefQueryOut getQtyDefById(String tarCode) throws ServiceException, DbAccessException;

	/**
	 * 修改定量指标
	 * 
	 * @param qtydef
	 * @return
	 * @throws ServiceException
	 * @throws DbAccessException
	 */
	public QtyDefUpdateOut update(QtyDefPo qtydef,List<String>dayScopeList,QtyExpDefPo qtyExp) throws ServiceException,
			DbAccessException;

	public QtyDefDeleteOut delete(QtydefDeleteIn in) throws ServiceException,
			DbAccessException;
	/**
	 * 根据指标分类代码查询定量指标
	 * @param tarSortCode 指标分类代码
	 * @return
	 * @throws ServiceException
	 */
	public List<QtyDefPo> getQtyDefListBySortCode(String tarSortCode) throws ServiceException;
	 public QtyDefPo getQtyDefByTarName(String tarName);
	
}
