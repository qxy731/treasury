package com.soule.base.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.soule.app.sys.userinfo.UserInfoPositionPo;
import com.soule.base.po.BizLinePo;
import com.soule.base.service.auth.RoleAssignPo;
import com.soule.base.service.logon.LogonInfoPo;
import com.soule.base.service.user.IUser;
import com.soule.comm.enu.YesNoFlag;

/**
 * 登录信息汇总
 * 
 * @author wuwei
 */
public class LogonInfoHolder implements UserDetails, ILogonUserInfo {
    private static final long serialVersionUID = 8346694099114166479L;
    private IUser user;
    private LogonInfoPo logonInfo;
    private Collection<GrantedAuthority> grantedAuth;
    private List<RoleAssignPo> assignRole;
    private List<UserInfoPositionPo> posi;
    private List<BizLinePo> bizLines;
    private String roleId;
    private String roleName;
    private String operUnitId;
    private String operUnitName;
    private String ipAddress;

    /* (non-Javadoc)
     * @see com.soule.comm.service.ILogonUserInfo#getUser()
     */
    public IUser getUser() {
        return user;
    }

    /* (non-Javadoc)
     * @see com.soule.comm.service.ILogonUserInfo#setUser(com.soule.comm.service.user.IUser)
     */
    public void setUser(IUser user) {
        this.user = user;
    }

    /* (non-Javadoc)
     * @see com.soule.comm.service.ILogonUserInfo#getLogonInfo()
     */
    public LogonInfoPo getLogonInfo() {
        return logonInfo;
    }

    public void setLogonInfo(LogonInfoPo logonInfo) {
        this.logonInfo = logonInfo;
    }

    public GrantedAuthority[] getAuthoritiesArray() {
        return grantedAuth.toArray(new GrantedAuthority[0]);
    }

    public String getPassword() {
        return logonInfo.getPassword();
    }

    public String getUsername() {
        return logonInfo.getLogonId();
    }

    public boolean isAccountNonExpired() {
        if (logonInfo.getAccExpireTime() != null) {
            return logonInfo.getAccExpireTime().after(new Date());
        }
        return true;
    }

    public boolean isAccountNonLocked() {
        YesNoFlag vf = YesNoFlag.getInstance(logonInfo.getLockFlag());
        return (vf == YesNoFlag.NO);
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        YesNoFlag vf = YesNoFlag.getInstance(logonInfo.getValidFlag());
        return (vf == YesNoFlag.YES);
    }

    /* (non-Javadoc)
     * @see com.soule.comm.service.ILogonUserInfo#getAssignRole()
     */
    public List<RoleAssignPo> getAssignRole() {
        return assignRole;
    }

    /* (non-Javadoc)
     * @see com.soule.comm.service.ILogonUserInfo#setAssignRole(java.util.List)
     */
    public void setAssignRole(List<RoleAssignPo> roleAss) {
        this.assignRole = roleAss;
    }

    public List<UserInfoPositionPo> getPositionPo() {
        return posi;
    }

    public void setPositionPo(List<UserInfoPositionPo> pos) {
        this.posi = pos;
    }

    public String getOperUnitId() {
        return operUnitId;
    }

    public String getOperUnitName() {
        return operUnitName;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setOperUnitId(String operUnitId) {
        this.operUnitId = operUnitId;
    }

    public void setOperUnitName(String operUnitName) {
        this.operUnitName = operUnitName;
    }

    public List<BizLinePo> getBizLines() {
        return bizLines;
    }

    public void setBizLines(List<BizLinePo> blps) {
        bizLines = blps;
    }

    public List<BizLinePo> getBizLineValue(String bizLineTypeId) {
        ArrayList<BizLinePo> lines = new ArrayList<BizLinePo>();
        if (bizLines != null) {
            for (BizLinePo p : bizLines) {
                if (p.getBizTypeId().equals(bizLineTypeId)) {
                    lines.add(p);
                }
            }
        }
        return lines;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

	public Collection<GrantedAuthority> getAuthorities() {
		return grantedAuth;
	}

	public void setAuthorities(Collection<GrantedAuthority> granted) {
		grantedAuth = granted;
	}

}
