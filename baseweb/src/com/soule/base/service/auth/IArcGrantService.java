package com.soule.base.service.auth;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;


import com.soule.base.media.DbAccessException;
import com.soule.base.service.ServiceException;
import com.soule.base.service.logon.LogonInfoPo;

public interface IArcGrantService {

	boolean isAdministrator(String logonId);

    List getAllGrantsByUser(LogonInfoPo logonInfo);

    /**
     * 获得用户角色列表
     * @throws ServiceException 
     * @throws DbAccessException 
     */
    List<RoleAssignPo> getUserRoleAssign(String userId) throws ServiceException, DbAccessException;

    /**
     * 根据角色ID获得对应角色的所有权限
     * @throws DbAccessException 
     */
    List<GrantedAuthority> getGrantOfRole(String roleId) throws DbAccessException;
}
