package com.soule.app.sys.roleass;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceResult;
import com.soule.comm.tools.ObjectUtil;

/**
 * 角色分配维护模块表现层处理类
 */
@Namespace("/sys")
public class RoleassAction extends BaseAction {
	private static final long serialVersionUID = 8882277662702243730L;
	private final static Log logger = LogFactory.getLog(RoleassAction.class);
    @Autowired
    private IRoleassService service;
    @Autowired
    private IDefaultService defService;

    /**
     * 按角色查询人员 输入参数 
     */
    private RoleassQueryByRoleIn queryByRoleIn; //= new RoleassQueryByRoleIn();
    /**
     * 查询人员 输入参数 
     */
    private RoleassQueryStaffIn queryStaffIn; //= new RoleassQueryStaffIn();
    /**
     * 删除角色人员 输入参数 
     */
    private RoleassDeleteIn deleteIn; //= new RoleassDeleteIn();
    /**
     * 新增角色人员 输入参数 
     */
    private RoleassInsertIn insertIn; //= new RoleassInsertIn();

    private List roles;
    private String roleId;
    private String operUnitid;

    public void doInit() {
        HashMap<String,String> condition = new HashMap<String,String>(1);
        try {
            roles =defService.getIbatisMediator().find("role.getSysRole",condition);
        } catch (DbAccessException e) {
            logger.error("init",e);
        }
    }

    public String queryByRole() {
        RoleassQueryByRoleIn in = queryByRoleIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            RoleassQueryByRoleOut result = service.queryByRole(in);
            ServiceResult head = result.getResultHead();
            this.rows = result.getRoleStaff();
            this.total = head.getTotal();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String queryStaff() {
        RoleassQueryStaffIn in = queryStaffIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            RoleassQueryStaffOut result = service.queryStaff(in);
            ServiceResult head = result.getResultHead();
            this.rows = result.getStaff();
            this.total = head.getTotal();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String delete() {
        RoleassDeleteIn in = deleteIn;
        try {
            RoleassDeleteOut result = service.delete(in);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String insert() {
        RoleassInsertIn in = insertIn;
        try {
            List<RoleassRoleStaffPo> pos = in.getInserts();
            if (!ObjectUtil.isEmpty(pos)) {
                for (RoleassRoleStaffPo po:pos) {
                    po.setRoleId(roleId);
                    po.setOperUnitid(operUnitid);
                }
            }
            RoleassInsertOut result = service.insert(in);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }

    /**
     * 按角色查询人员
     */
    @JSON(serialize=false)
    public RoleassQueryByRoleIn getQueryByRoleIn() {
        return this.queryByRoleIn;
    }
    /**
     * 按角色查询人员
     */
    public void setQueryByRoleIn(RoleassQueryByRoleIn in) {
        this.queryByRoleIn = in;
    }
    /**
     * 查询人员
     */
    @JSON(serialize=false)
    public RoleassQueryStaffIn getQueryStaffIn() {
        return this.queryStaffIn;
    }
    /**
     * 查询人员
     */
    public void setQueryStaffIn(RoleassQueryStaffIn in) {
        this.queryStaffIn = in;
    }
    /**
     * 删除角色人员
     */
    @JSON(serialize=false)
    public RoleassDeleteIn getDeleteIn() {
        return this.deleteIn;
    }
    /**
     * 删除角色人员
     */
    public void setDeleteIn(RoleassDeleteIn in) {
        this.deleteIn = in;
    }
    /**
     * 新增角色人员
     */
    @JSON(serialize=false)
    public RoleassInsertIn getInsertIn() {
        return this.insertIn;
    }
    /**
     * 新增角色人员
     */
    public void setInsertIn(RoleassInsertIn in) {
        this.insertIn = in;
    }

    public List getRoles() {
        return roles;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getOperUnitid() {
        return operUnitid;
    }

    public void setOperUnitid(String operUnitid) {
        this.operUnitid = operUnitid;
    }

}
