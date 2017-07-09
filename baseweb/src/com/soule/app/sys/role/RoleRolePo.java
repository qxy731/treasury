package com.soule.app.sys.role;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 参数传递查询角色的类
 */
public class RoleRolePo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String roleId;
    private String roleName;
    private String roleStatus;
    private String remark;
    private String createUser;
    private Date createTime;
    private String lastUpdUser;
    private String lastUpdTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String createManName;
    private String parentRoleId;

    /**
     * @return 角色编码
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId 角色编码
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    /**
     * @return 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    /**
     * @return 角色状态
     */
    public String getRoleStatus() {
        return roleStatus;
    }

    /**
     * @param roleStatus 角色状态
     */
    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }
    /**
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**
     * @return 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    /**
     * @return 最后修改人
     */
    public String getLastUpdUser() {
        return lastUpdUser;
    }

    /**
     * @param lastUpdUser 最后修改人
     */
    public void setLastUpdUser(String lastUpdUser) {
        this.lastUpdUser = lastUpdUser;
    }
    /**
     * @return EXT1
     */
    public String getExt1() {
        return ext1;
    }

    /**
     * @param ext1 EXT1
     */
    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }
    /**
     * @return EXT2
     */
    public String getExt2() {
        return ext2;
    }

    /**
     * @param ext2 EXT2
     */
    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }
    /**
     * @return EXT3
     */
    public String getExt3() {
        return ext3;
    }

    /**
     * @param ext3 EXT3
     */
    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }
    /**
     * @return 创建人
     */
    public String getCreateManName() {
        return createManName;
    }

    /**
     * @param createManName 创建人
     */
    public void setCreateManName(String createManName) {
        this.createManName = createManName;
    }

    public String getParentRoleId() {
        return parentRoleId;
    }

    public void setParentRoleId(String parentRoleId) {
        this.parentRoleId = parentRoleId;
    }

    //@DateTimeFormat(pattern="YYYY-MM-DD HH:mm:ss")
    @JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdTime() {
		return lastUpdTime;
	}

	public void setLastUpdTime(String lastUpdTime) {
		this.lastUpdTime = lastUpdTime;
	}
    
    
    
}