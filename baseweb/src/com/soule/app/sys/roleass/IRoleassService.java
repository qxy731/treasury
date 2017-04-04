package com.soule.app.sys.roleass;

import com.soule.base.service.ServiceException;

/**
 * 角色分配业务操作
 */
public interface IRoleassService {

    /**
     * 根据角色查询当前机构下的人,有翻页
     */
    public RoleassQueryByRoleOut queryByRole(RoleassQueryByRoleIn in) throws ServiceException;
    /**
     * 根据基本条件查询当前机构下的人，有翻页
     */
    public RoleassQueryStaffOut queryStaff(RoleassQueryStaffIn in) throws ServiceException;
    /**
     * 删除当前角色下的人
     */
    public RoleassDeleteOut delete(RoleassDeleteIn in) throws ServiceException;
    /**
     * 当前角色下新增人员
     */
    public RoleassInsertOut insert(RoleassInsertIn in) throws ServiceException;


}
