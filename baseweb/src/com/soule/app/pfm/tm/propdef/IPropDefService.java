package com.soule.app.pfm.tm.propdef;

import java.util.List;

import com.soule.base.media.DbAccessException;
import com.soule.base.service.ServiceException;

/**
 * 指标分类业务操作
 */

public interface IPropDefService {

    /**
     * 定性指标类的查询
     */
    public PropDefQueryOut query(PropDefQueryIn in) throws ServiceException;

    /**
     * 修改定性指标信息
     */
    public PropDefUpdateOut update(PropDefUpdateIn in) throws ServiceException, DbAccessException;

    /**
     * 定性指标新增
     */
    public PropDefInsertOut insert(PropDefInsertIn in) throws ServiceException, DbAccessException;

    /**
     * 批量删除定性指标
     */
    public PropDefDeleteOut delete(PropDefDeleteIn in) throws ServiceException, DbAccessException;
    
    public PropDefPo getPropDefById(String tarCode) throws ServiceException;
    /**
     * 根据指标分类代码查询定性指标
     * @param tarSortCode 指标分类代码
     * @return
     * @throws ServiceException
     */
    public List<PropDefPo> getPropDefListBySortCode(String tarSortCode)throws ServiceException;

    public PropDefPo getPropDefByTarName(String tarName);
}
