package com.soule.app.sys.changeposi;

import com.soule.base.service.ServiceException;

/**
 * 职位切换业务操作
 */
public interface IChangePosiService {

    /**
     * 
     */
    public ChangePosiChangeOut change(ChangePosiChangeIn in) throws ServiceException;


}
