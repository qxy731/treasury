package com.soule.app.sys.menugrant;

import com.soule.base.service.ServiceException;

/**
 * 菜单权限设置业务操作
 */
public interface IMenuGrantService {

    /**
     * 根据当前菜单节点，获得当前用户角色可分配子菜单和菜单目录及分配角色已获权限菜单标志信息
     */
    public MenuGrantListSubOut listSub(MenuGrantListSubIn in) throws ServiceException;
    /**
     * 保存当前用户角色菜单权限的信息。（新增和删除）
     */
    public MenuGrantSaveAuthOut saveAuth(MenuGrantSaveAuthIn in) throws ServiceException;


}
