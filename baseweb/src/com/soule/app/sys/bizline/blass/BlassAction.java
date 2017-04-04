package com.soule.app.sys.bizline.blass;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;

/**
 * 业务线人员分配维护模块表现层处理类
 */
@Namespace("/sys")
public class BlassAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private final static Log logger = LogFactory.getLog(BlassAction.class);
    @Autowired
    private IBlassService service;

    /**
     * 查询业务线中人员 输入参数 
     */
    private BlassQueryIn queryIn; //= new BlassQueryIn();
    /**
     * 新增业务线中人员 输入参数 
     */
    private BlassInsertIn insertIn; //= new BlassInsertIn();
    /**
     * 删除业务线中人员 输入参数 
     */
    private BlassDeleteIn deleteIn; //= new BlassDeleteIn();
    /**
     * 删除业务线中全部人员 输入参数 
     */
    private BlassDeleteAllIn deleteAllIn; //= new BlassDeleteAllIn();
    /**
     * 业务线中添加查询结果中全部人员 输入参数 
     */
    private BlassInsertAllIn insertAllIn; //= new BlassInsertAllIn();

    public void doInit() {
    }
    public String query() {
        BlassQueryIn in = queryIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            BlassQueryOut result = service.query(in);
            ServiceResult head = result.getResultHead();
            rows = result.getBizass();
            this.setTotal(head.getTotal());
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String insert() {
        BlassInsertIn in = insertIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            BlassInsertOut result = service.insert(in);
            ServiceResult head = result.getResultHead();
            // TODO
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String delete() {
        BlassDeleteIn in = deleteIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            BlassDeleteOut result = service.delete(in);
            ServiceResult head = result.getResultHead();
            // TODO
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String deleteAll() {
        BlassDeleteAllIn in = deleteAllIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            BlassDeleteAllOut result = service.deleteAll(in);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String insertAll() {
        BlassInsertAllIn in = insertAllIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            BlassInsertAllOut result = service.insertAll(in);
            ServiceResult head = result.getResultHead();
            // TODO
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    /**
     * 查询业务线中人员
     */
    @JSON(serialize=false)
    public BlassQueryIn getQueryIn() {
        return this.queryIn;
    }
    /**
     * 查询业务线中人员
     */
    public void setQueryIn(BlassQueryIn in) {
        this.queryIn = in;
    }
    /**
     * 新增业务线中人员
     */
    @JSON(serialize=false)
    public BlassInsertIn getInsertIn() {
        return this.insertIn;
    }
    /**
     * 新增业务线中人员
     */
    public void setInsertIn(BlassInsertIn in) {
        this.insertIn = in;
    }
    /**
     * 删除业务线中人员
     */
    @JSON(serialize=false)
    public BlassDeleteIn getDeleteIn() {
        return this.deleteIn;
    }
    /**
     * 删除业务线中人员
     */
    public void setDeleteIn(BlassDeleteIn in) {
        this.deleteIn = in;
    }
    /**
     * 删除业务线中全部人员
     */
    @JSON(serialize=false)
    public BlassDeleteAllIn getDeleteAllIn() {
        return this.deleteAllIn;
    }
    /**
     * 删除业务线中全部人员
     */
    public void setDeleteAllIn(BlassDeleteAllIn in) {
        this.deleteAllIn = in;
    }
    /**
     * 业务线中添加查询结果中全部人员
     */
    @JSON(serialize=false)
    public BlassInsertAllIn getInsertAllIn() {
        return this.insertAllIn;
    }
    /**
     * 业务线中添加查询结果中全部人员
     */
    public void setInsertAllIn(BlassInsertAllIn in) {
        this.insertAllIn = in;
    }
}
