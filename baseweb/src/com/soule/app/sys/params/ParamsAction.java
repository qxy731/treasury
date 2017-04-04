package com.soule.app.sys.params;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;

/**
 * 参数配置维护模块表现层处理类
 */
@Namespace("/sys")
public class ParamsAction extends BaseAction {
	private static final long serialVersionUID = -6217127716890985133L;

	@Autowired
    private IParamsService service;

    /**
     * 查询参数配置 输入参数
     */
    private ParamsQueryIn queryIn; // = new ParamsQueryIn();
    /**
     * 新增参数配置 输入参数
     */
    private ParamsInsertIn insertIn; // = new ParamsInsertIn();
    /**
     * 删除参数配置 输入参数
     */
    private ParamsDeleteIn deleteIn; // = new ParamsDeleteIn();
    /**
     * 修改参数配置 输入参数
     */
    private ParamsUpdateIn updateIn; // = new ParamsUpdateIn();

    private ParamsParamsPo paramsPo;

    public void doInit() {
    }

    public void detailInit() {
        ParamsQueryIn in = new ParamsQueryIn();
        ParamsParamsPo params = new ParamsParamsPo();
        String paraId = request.getParameter("paraId");
        params.setParaId(paraId);
        in.setParams(params);
        try {
            ParamsQueryOut result = service.queryDetail(in);
            ServiceResult head = result.getResultHead();
            // TODO
            paramsPo = result.getParamsPo();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        } catch (Exception e) {
            handleError(e);
        }
    }

    public String query() {
        ParamsQueryIn in = queryIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            ParamsQueryOut result = service.query(in);
            ServiceResult head = result.getResultHead();
            // TODO
            this.rows = result.getParams();
            this.setTotal(head.getTotal());
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        } catch (Exception e) {
            handleError(e);
        }
        return JSON;
    }

    public String insert() {
        ParamsInsertIn in = insertIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            ParamsInsertOut result = service.insert(in);
            ServiceResult head = result.getResultHead();
            // TODO
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        } catch (Exception e) {
            handleError(e);
        }
        return JSON;
    }

    public String delete() {
        ParamsDeleteIn in = deleteIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            ParamsDeleteOut result = service.delete(in);
            ServiceResult head = result.getResultHead();
            // TODO
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        } catch (Exception e) {
            handleError(e);
        }
        return JSON;
    }

    public String update() {
        ParamsUpdateIn in = updateIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            ParamsUpdateOut result = service.update(in);
            ServiceResult head = result.getResultHead();
            // TODO
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        } catch (Exception e) {
            handleError(e);
        }
        return JSON;
    }

    /**
     * 查询参数配置
     */
    @JSON(serialize = false)
    public ParamsQueryIn getQueryIn() {
        return this.queryIn;
    }

    /**
     * 查询参数配置
     */
    public void setQueryIn(ParamsQueryIn in) {
        this.queryIn = in;
    }

    /**
     * 新增参数配置
     */
    @JSON(serialize = false)
    public ParamsInsertIn getInsertIn() {
        return this.insertIn;
    }

    /**
     * 新增参数配置
     */
    public void setInsertIn(ParamsInsertIn in) {
        this.insertIn = in;
    }

    /**
     * 删除参数配置
     */
    @JSON(serialize = false)
    public ParamsDeleteIn getDeleteIn() {
        return this.deleteIn;
    }

    /**
     * 删除参数配置
     */
    public void setDeleteIn(ParamsDeleteIn in) {
        this.deleteIn = in;
    }

    /**
     * 修改参数配置
     */
    @JSON(serialize = false)
    public ParamsUpdateIn getUpdateIn() {
        return this.updateIn;
    }

    /**
     * 修改参数配置
     */
    public void setUpdateIn(ParamsUpdateIn in) {
        this.updateIn = in;
    }

    public ParamsParamsPo getParamsPo() {
        return paramsPo;
    }

    public void setParamsPo(ParamsParamsPo paramsPo) {
        this.paramsPo = paramsPo;
    }

}
