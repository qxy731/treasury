package com.soule.app.sys.params;

import com.soule.base.service.ServiceException;

/**
 * 参数配置业务操作
 */
public interface IParamsService {

    /**
     * 查询获得系统中定义的所有系统参数值。
     */
    public ParamsQueryOut query(ParamsQueryIn in) throws ServiceException;

    /**
     * 新增系统参数。
     */
    public ParamsInsertOut insert(ParamsInsertIn in) throws ServiceException;

    /**
     * 删除系统参数。
     */
    public ParamsDeleteOut delete(ParamsDeleteIn in) throws ServiceException;

    /**
     * 修改系统参数。
     */
    public ParamsUpdateOut update(ParamsUpdateIn in) throws ServiceException;

    /**
     * 查询系统参数详细。
     */
    public ParamsQueryOut queryDetail(ParamsQueryIn in) throws ServiceException;

    public ParamsParamsPo queryParamByParamId(String paraId) throws ServiceException;

    public ParamsParamsPo queryParams(String paraId, String paraRank) throws ServiceException;
}
