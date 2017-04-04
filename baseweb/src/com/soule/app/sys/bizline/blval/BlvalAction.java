package com.soule.app.sys.bizline.blval;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;

/**
 * 业务线分类值维护维护模块表现层处理类
 */
@Namespace("/sys")
public class BlvalAction extends BaseAction {
	private static final long serialVersionUID = 4156301104465721222L;
    @Autowired
    IBlvalService service;

    /**
     * 查询业务线分类 输入参数 
     */
    private BlvalQueryIn queryIn; //= new BlvalQueryIn();
    /**
     * 修改业务线分类 输入参数 
     */
    private BlvalModifyIn modifyIn; //= new BlvalModifyIn();
    /**
     * 判断业务线关系数据 输入参数 
     */
    private BlvalValidDataIn validDataIn; //= new BlvalValidDataIn();
    /**
     * 删除业务线类型 输入参数 
     */
    private BlvalDeleteIn deleteIn; //= new BlvalDeleteIn();
    /**
     * 新增业务线分类 输入参数 
     */
    private BlvalInsertIn insertIn; //= new BlvalInsertIn();

    //显示参数
    private BlvalResultPo resultPo;

    public void doInit() {
    }
    public String query() {
        BlvalQueryIn in = queryIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            BlvalQueryOut result = service.query(in);
            ServiceResult head = result.getResultHead();
            rows = result.getBizVal();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String modify() {
        BlvalModifyIn in = modifyIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            BlvalModifyOut result = service.modify(in);
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
    public String validData() {
        BlvalValidDataIn in = validDataIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            BlvalValidDataOut result = service.validData(in);
            ServiceResult head = result.getResultHead();
            resultPo = result.getResult();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String delete() {
        BlvalDeleteIn in = deleteIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            BlvalDeleteOut result = service.delete(in);
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
    public String insert() {
        BlvalInsertIn in = insertIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            BlvalInsertOut result = service.insert(in);
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
     * 查询业务线分类
     */
    @JSON(serialize=false)
    public BlvalQueryIn getQueryIn() {
        return this.queryIn;
    }
    /**
     * 查询业务线分类
     */
    public void setQueryIn(BlvalQueryIn in) {
        this.queryIn = in;
    }
    /**
     * 修改业务线分类
     */
    @JSON(serialize=false)
    public BlvalModifyIn getModifyIn() {
        return this.modifyIn;
    }
    /**
     * 修改业务线分类
     */
    public void setModifyIn(BlvalModifyIn in) {
        this.modifyIn = in;
    }
    /**
     * 判断业务线关系数据
     */
    @JSON(serialize=false)
    public BlvalValidDataIn getValidDataIn() {
        return this.validDataIn;
    }
    /**
     * 判断业务线关系数据
     */
    public void setValidDataIn(BlvalValidDataIn in) {
        this.validDataIn = in;
    }
    /**
     * 删除业务线类型
     */
    @JSON(serialize=false)
    public BlvalDeleteIn getDeleteIn() {
        return this.deleteIn;
    }
    /**
     * 删除业务线类型
     */
    public void setDeleteIn(BlvalDeleteIn in) {
        this.deleteIn = in;
    }
    /**
     * 新增业务线分类
     */
    @JSON(serialize=false)
    public BlvalInsertIn getInsertIn() {
        return this.insertIn;
    }
    /**
     * 新增业务线分类
     */
    public void setInsertIn(BlvalInsertIn in) {
        this.insertIn = in;
    }
    public BlvalResultPo getResultPo() {
        return resultPo;
    }
    public void setResultPo(BlvalResultPo resultPo) {
        this.resultPo = resultPo;
    }
}
