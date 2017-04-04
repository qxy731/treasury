package com.soule.app.sys.funcauth;

import com.soule.base.media.DbAccessException;
import com.soule.base.service.ServiceException;

/**
 * 功能权限资源维护业务操作
 */
public interface IFuncAuthService {

    /**
     * 按页面路径模糊查询资源
     * @throws DbAccessException 
     */
    public FuncAuthQueryOut query(FuncAuthQueryIn in) throws ServiceException, DbAccessException;
    /**
     * 新增功能权限资源
     * @throws DbAccessException 
     */
    public FuncAuthInsertOut insert(FuncAuthInsertIn in) throws ServiceException, DbAccessException;
    /**
     * 某一页面中功能权限资源的批量删除
     */
    public FuncAuthDeleteOut delete(FuncAuthDeleteIn in) throws ServiceException;
    /**
     * 从指定页面搜索功能权限资源
     */
    public FuncAuthParseOut parse(FuncAuthParseIn in) throws ServiceException;


}
