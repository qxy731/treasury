package com.soule.app.sys.role;

import com.soule.base.media.DbAccessException;
import com.soule.base.service.ServiceException;

/**
 * 角色业务操作
 */
public interface IRoleService {

    /**
     * 角色的查询
     */
    public RoleQueryOut query(RoleQueryIn in) throws ServiceException;
    /**
     * 修改角色信息
     */
    public RoleUpdateOut update(RoleUpdateIn in) throws ServiceException;
    /**
     * 角色的新增
     */
    public RoleInsertOut insert(RoleInsertIn in) throws ServiceException, DbAccessException;
    /**
     * 批量删除角色
     */
    public RoleDeleteOut delete(RoleDeleteIn in) throws ServiceException;


}
