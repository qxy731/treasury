package com.soule.app.sys.bizline.blval;

import com.soule.base.service.ServiceException;

/**
 * 业务线分类值维护业务操作
 */
public interface IBlvalService {

    /**
     * 查询系统中定义的业务线分类
     */
    public BlvalQueryOut query(BlvalQueryIn in) throws ServiceException;
    /**
     * 修改业务线分类信息
     */
    public BlvalModifyOut modify(BlvalModifyIn in) throws ServiceException;
    /**
     * 删除业务线分类信息前，判断该业务线是否包业务关系表中的数据
     */
    public BlvalValidDataOut validData(BlvalValidDataIn in) throws ServiceException;
    /**
     * 删除业务线分类信息，包括业务关系表中的数据
     */
    public BlvalDeleteOut delete(BlvalDeleteIn in) throws ServiceException;
    /**
     * 新增业务线分类
     */
    public BlvalInsertOut insert(BlvalInsertIn in) throws ServiceException;


}
