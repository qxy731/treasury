package com.soule.app.sys.role;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 角色维护模块表现层处理类
 */
@Namespace("/sys")
public class RoleAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Autowired
    private IRoleService service;

    /**
     * 查询角色 输入参数 
     */
    private RoleQueryIn queryIn; //= new RoleQueryIn();
    /**
     * 修改角色信息 输入参数 
     */
    private RoleUpdateIn updateIn; //= new RoleUpdateIn();
    /**
     * 新增角色 输入参数 
     */
    private RoleInsertIn insertIn; //= new RoleInsertIn();
    /**
     * 删除角色 输入参数 
     */
    private RoleDeleteIn deleteIn; //= new RoleDeleteIn();

    //回显对象
    private String data ;
    private RoleRolePo modifyRole;
    public String query() {
        RoleQueryIn in = queryIn;
        try {
            in.getInputHead().setPageNo(page);
            in.getInputHead().setPageSize(pagesize);
            RoleQueryOut result = service.query(in);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
            rows = result.getRole();
            total = head.getTotal();
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String update() {
        RoleUpdateIn in = updateIn;
        try {
            IServiceResult result = service.update(in);
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
        RoleInsertIn in = insertIn;
        try {
            IServiceResult result = service.insert(in);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String delete() {
        RoleDeleteIn in = deleteIn;
        try {
            IServiceResult result = service.delete(in);
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
     * 查询角色
     */
    @JSON(serialize=false)
    public RoleQueryIn getQueryIn() {
        return this.queryIn;
    }
    /**
     * 查询角色
     */
    public void setQueryIn(RoleQueryIn in) {
        this.queryIn = in;
    }
    /**
     * 修改角色信息
     */
    @JSON(serialize=false)
    public RoleUpdateIn getUpdateIn() {
        return this.updateIn;
    }
    /**
     * 修改角色信息
     */
    public void setUpdateIn(RoleUpdateIn in) {
        this.updateIn = in;
    }
    /**
     * 新增角色
     */
    @JSON(serialize=false)
    public RoleInsertIn getInsertIn() {
        return this.insertIn;
    }
    /**
     * 新增角色
     */
    public void setInsertIn(RoleInsertIn in) {
        this.insertIn = in;
    }
    /**
     * 删除角色
     */
    @JSON(serialize=false)
    public RoleDeleteIn getDeleteIn() {
        return this.deleteIn;
    }
    /**
     * 删除角色
     */
    public void setDeleteIn(RoleDeleteIn in) {
        this.deleteIn = in;
    }
    public RoleRolePo getModifyRole() {
        return modifyRole;
    }
    public void setModifyRole(RoleRolePo modifyRole) {
        this.modifyRole = modifyRole;
    }
    
    public void doInit() {
        RoleQueryIn qin = new RoleQueryIn();
        qin.setRoleId(request.getParameter("roleid"));
        try {
            RoleQueryOut out = service.query(qin);
            if (out.getResultHead().isSuccess()) {
                setModifyRole(out.getRole().get(0));
            }
        } catch (Exception e) {
            handleError(e);
        }
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    
}
