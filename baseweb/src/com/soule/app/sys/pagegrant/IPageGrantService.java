package com.soule.app.sys.pagegrant;

import com.soule.base.media.DbAccessException;
import com.soule.base.service.ServiceException;

/**
 * 页面功能权限设置业务操作
 */
public interface IPageGrantService {

    /**
     * 当前角色可分配节点范围内，
     * 根据父节点，查询返回子节点信息。
     * 父节点为目录，列举子目录和jsp文件
     * 父节点为jsp，列举功能点
     */
    public PageGrantListChildOut listChild(PageGrantListChildIn in) throws ServiceException, DbAccessException;
    /**
     * 保存当前用户角色页面和功能点权限的信息。（新增和删除）
     */
    public PageGrantSaveAuthOut saveAuth(PageGrantSaveAuthIn in) throws ServiceException;


}
