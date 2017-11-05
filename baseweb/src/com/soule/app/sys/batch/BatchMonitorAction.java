package com.soule.app.sys.batch;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.MsgConstants;
import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;

@Namespace("/sys")
public class BatchMonitorAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Autowired
    protected IBatchMonitorService service;
    @Autowired
    protected IBatchService bservice;

    private String bid;
    private BatchQueryMonitorIn queryMonitorIn;

    public void doInit() {
        String param = request.getParameter("bid");
        Integer x = bservice.queryMaxStepId(param);
        if ( x != null && x.intValue() > 0) {
            retCode = MsgConstants.I0000;
            this.setBid(param);
        }
        else {
            retCode = MsgConstants.E0000;
            retMsg = "批处理流程不存在";
            this.setBid(null);
        }
    }
    public void doInitInst() {
        String param = request.getParameter("bid");
        this.rows = service.queryInstIds(param);
    }
    public String queryMonitor() {
        BatchQueryMonitorIn in =  queryMonitorIn;
        try {
            BatchQueryMonitorOut result = service.queryMonitor(in);
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
    public String queryStatus() {
        BatchQueryMonitorIn in = queryMonitorIn;
        try {
            BatchQueryMonitorOut result = service.queryStatus(in);
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
    public String getBid() {
        return bid;
    }
    public void setBid(String bid) {
        this.bid = bid;
    }
    public BatchQueryMonitorIn getQueryMonitorIn() {
        return queryMonitorIn;
    }
    public void setQueryMonitorIn(BatchQueryMonitorIn queryMonitorIn) {
        this.queryMonitorIn = queryMonitorIn;
    }
}
