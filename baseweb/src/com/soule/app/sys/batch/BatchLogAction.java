package com.soule.app.sys.batch;


import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;

/**
 * 批处理日志模块表现层处理类
 */
@Namespace("/sys")
public class BatchLogAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Autowired
    protected IBatchLogService service;
    /**
     * 查询参数 
     */
    private BatchLogIn logIn; //= new BatchLogIn();
    public String querylog() {
        BatchLogIn in = logIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            BatchLogOut result = service.querylog(in);
            ServiceResult head = result.getResultHead();
            this.total = result.getResultHead().getTotal();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
            rows = result.getBatchLogs();
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }

    @JSON(serialize=false)
    public BatchLogIn getLogIn() {
        return logIn;
    }
    public void setLogIn(BatchLogIn logIn) {
        this.logIn = logIn;
    }
    
}
