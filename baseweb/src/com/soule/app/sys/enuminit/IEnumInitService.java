package com.soule.app.sys.enuminit;

import com.soule.base.media.DbAccessException;
import com.soule.base.service.ServiceException;

/**
 * 枚举参数初始化业务操作
 */
public interface IEnumInitService {

    /**
     * 把所有枚举参数导入到内存中
     * @throws DbAccessException 
     */
    public void load() throws ServiceException, DbAccessException;


}
