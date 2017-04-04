package com.soule.app.sys.gname;

import com.soule.base.service.ServiceException;

public interface IGetNameService {
    /**
     * @param finalValue
     *            根据value在值栈中查找过后 产生的值
     */
    public String queryResult(String finalValue) throws ServiceException;
}
