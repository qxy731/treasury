package com.soule.app.sys.batch;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.MsgConstants;
import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceException;
import com.soule.base.service.ServiceResult;

/**
 * 批处理配置维护模块表现层处理类
 */
@Namespace("/sys")
public class BatchAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Autowired
    protected IBatchService service ;

    /**
     * 查询步骤信息列表 输入参数 
     */
    private BatchQueryMainIn queryMainIn; //= new BatchQueryMainIn();
    /**
     * 查询单个步骤信息 输入参数 
     */
    private BatchQueryStepIn queryStepIn; //= new BatchQueryStepIn();
    /**
     * 查询所有批处理类型ID 输入参数 
     */
    private BatchQueryIdIn queryIdIn; //= new BatchQueryIdIn();
    /**
     * 修改步骤信息 输入参数 
     */
    private BatchUpdateIn updateIn; //= new BatchUpdateIn();
    /**
     * 新增步骤信息 输入参数 
     */
    private BatchInsertIn insertIn; //= new BatchInsertIn();
    /**
     * 删除步骤 输入参数 
     */
    private BatchDeleteIn deleteIn; //= new BatchDeleteIn();

    private List<BatchStepIdPo> ids;
    private String bid;
    public void doInit() {
        BatchQueryIdIn in = new BatchQueryIdIn();
        try {
            BatchQueryIdOut out = service.queryId(in);
            ids = out.getStepId();
        } catch (ServiceException e) {
        }
        if (ids == null) {
            ids = new ArrayList<BatchStepIdPo>();
        }
    }

    public void doInitBatch() {
        String type = request.getParameter("type");
        String param = request.getParameter("bid");
        if ("new".equals(type)) {
            BatchInsertIn in = new BatchInsertIn();
            try {
                BatchStepPo input = new BatchStepPo();
                input.setBatchId(param);
                input.setCompCount(0);
                input.setParentId(0);
                input.setStepId(1);
                input.setStepNo(0);
                input.setStepType("2");
                input.setStepName("根节点");
                in.setStep(input);
                service.insert(in);
                bid = param;
                retCode = MsgConstants.I0000;
            } catch (ServiceException e) {
                e.printStackTrace();
                retMsg = e.getErrorMsg();
                retCode = MsgConstants.E0000;
                bid = null;
            }
        }
        else if ("old".equals(type)) {
            Integer x = service.queryMaxStepId(param);
            if ( x != null && x.intValue() > 0) {
                retCode = MsgConstants.I0000;
                bid = param;
            }
            else {
                retCode = MsgConstants.E0000;
                retMsg = "批处理流程不存在";
                bid = null;
            }
        }
    }
    public String queryMain() {
        BatchQueryMainIn in = queryMainIn;
        try {
            BatchQueryMainOut result = service.queryMain(in);
            ServiceResult head = result.getResultHead();
            this.total = result.getResultHead().getTotal();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
            rows = result.getSteps();
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String queryId() {
        BatchQueryIdIn in = queryIdIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            BatchQueryIdOut result = service.queryId(in);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String update() {
        BatchUpdateIn in = updateIn;
        try {
            BatchUpdateOut result = service.update(in);
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
        BatchInsertIn in = insertIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            BatchInsertOut result = service.insert(in);
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
        BatchDeleteIn in = deleteIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            BatchDeleteOut result = service.delete(in);
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
     * 查询步骤信息列表
     */
    @JSON(serialize=false)
    public BatchQueryMainIn getQueryMainIn() {
        return this.queryMainIn;
    }
    /**
     * 查询步骤信息列表
     */
    public void setQueryMainIn(BatchQueryMainIn in) {
        this.queryMainIn = in;
    }
    /**
     * 查询单个步骤信息
     */
    @JSON(serialize=false)
    public BatchQueryStepIn getQueryStepIn() {
        return this.queryStepIn;
    }
    /**
     * 查询单个步骤信息
     */
    public void setQueryStepIn(BatchQueryStepIn in) {
        this.queryStepIn = in;
    }
    /**
     * 查询所有批处理类型ID
     */
    @JSON(serialize=false)
    public BatchQueryIdIn getQueryIdIn() {
        return this.queryIdIn;
    }
    /**
     * 查询所有批处理类型ID
     */
    public void setQueryIdIn(BatchQueryIdIn in) {
        this.queryIdIn = in;
    }
    /**
     * 修改步骤信息
     */
    @JSON(serialize=false)
    public BatchUpdateIn getUpdateIn() {
        return this.updateIn;
    }
    /**
     * 修改步骤信息
     */
    public void setUpdateIn(BatchUpdateIn in) {
        this.updateIn = in;
    }
    /**
     * 新增步骤信息
     */
    @JSON(serialize=false)
    public BatchInsertIn getInsertIn() {
        return this.insertIn;
    }
    /**
     * 新增步骤信息
     */
    public void setInsertIn(BatchInsertIn in) {
        this.insertIn = in;
    }
    /**
     * 删除步骤
     */
    @JSON(serialize=false)
    public BatchDeleteIn getDeleteIn() {
        return this.deleteIn;
    }
    /**
     * 删除步骤
     */
    public void setDeleteIn(BatchDeleteIn in) {
        this.deleteIn = in;
    }
    public List<BatchStepIdPo> getIds() {
        return ids;
    }
    public void setIds(List<BatchStepIdPo> ids) {
        this.ids = ids;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

}
