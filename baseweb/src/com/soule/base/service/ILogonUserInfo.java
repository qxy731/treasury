package com.soule.base.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.soule.app.sys.userinfo.UserInfoPositionPo;
import com.soule.base.po.BizLinePo;
import com.soule.base.service.auth.RoleAssignPo;
import com.soule.base.service.logon.LogonInfoPo;
import com.soule.base.service.user.IUser;

public interface ILogonUserInfo extends UserDetails {

    public final static String BEAN_ID = "LOGON_USER_INFO";
    
    /**
     * 返回用户信息
     * @return
     */
    public abstract IUser getUser();

    public abstract void setUser(IUser user);

    /**
     * 返回登录相关信息
     * @return
     */
    public abstract LogonInfoPo getLogonInfo();

    public abstract void setLogonInfo(LogonInfoPo logonInfo);

    public abstract void setAuthorities(Collection<GrantedAuthority> granted);

    /**
     * 返回角色分配信息
     * 
     * @return
     */
    public abstract List<RoleAssignPo> getAssignRole();

    public abstract void setAssignRole(List<RoleAssignPo> roleAss);

    public abstract List<UserInfoPositionPo> getPositionPo();

    public abstract void setPositionPo(List<UserInfoPositionPo> pos);

    /**
     * 获取所有所属业务线
     * @return
     */
    public abstract List<BizLinePo> getBizLines();

    public abstract void setBizLines(List<BizLinePo> blps);
    /**
     * 获取业务线值
     * @param bizLineTypeId 业务线类型ID
     */
    public abstract List<BizLinePo> getBizLineValue(String bizLineTypeId);

    /**
     * 当前角色
     */
    public String getRoleId();
    /**
     * 当前角色名称
     */
    public String getRoleName();
    /**
     * 操作机构
     */
    public String getOperUnitId();
    /**
     * 操作机构名称
     */
    public String getOperUnitName();
    /**
     * 当前角色
     */
    public void setRoleId(String val);
    /**
     * 当前角色名称
     */
    public void setRoleName(String val);
    /**
     * 操作机构
     */
    public void setOperUnitId(String val);
    /**
     * 操作机构名称
     */
    public void setOperUnitName(String val);
    
    public String getIpAddress();
    public void setIpAddress(String ipAddress);
}