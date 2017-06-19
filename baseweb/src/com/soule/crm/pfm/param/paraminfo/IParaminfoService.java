package com.soule.crm.pfm.param.paraminfo;

import com.soule.base.service.ServiceException;

/**
 * 指标信息 业务操作接口
 */
public interface IParaminfoService {


    /**
     * 指标信息查询
     */
    public ParaminfoQueryOut query(ParaminfoQueryIn in) throws ServiceException;

    /**
     * 指标信息查询
     */
    public ParaminfoQueryOut queryAll(ParaminfoQueryIn in)
            throws ServiceException;


}
