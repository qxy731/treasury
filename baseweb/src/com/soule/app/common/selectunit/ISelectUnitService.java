package com.soule.app.common.selectunit;

import com.soule.base.service.ServiceException;

/**
 * 机构查询业务操作
 */
public interface ISelectUnitService {

    /**
     * 机构查询
     * 
     * @param in
     * @return
     * @throws ServiceException
     */
    public SelectUnitOut queryUnit(SelectUnitIn in) throws ServiceException;

}
