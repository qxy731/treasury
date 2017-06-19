package com.soule.app.pfm.tm.report.action;


import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.app.pfm.tm.report.service.IUserDefinedReportService;
import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;

/**
 * 人员维护模块表现层处理类
 */
@Namespace("/report")
public class UserReportAction extends BaseAction {
    private static final long serialVersionUID = 1295775504760653068L;
    
    @Autowired
    private IUserDefinedReportService userDefinedReportService;
    /**
     * 查询人员 输入参数 
     */
    private UserDefinedReportQueryIn queryIn;
    
    public void doInit() {
    }
    public String query() {
        try {
            queryIn.getInputHead().setPageNo(this.getPage());
            queryIn.getInputHead().setPageSize(this.getPagesize());
            
            UserDefinedReportQueryOut result = userDefinedReportService.query(queryIn);
            ServiceResult head = result.getResultHead();
            rows = result.getUserDefinedReport();
            total=(int)result.getResultHead().getTotal();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
   

    /**
     * 查询人员
     */
    @JSON(serialize=false)
    public UserDefinedReportQueryIn getQueryIn() {
        return this.queryIn;
    }
    /**
     * 查询人员
     */
    public void setQueryIn(UserDefinedReportQueryIn in) {
        this.queryIn = in;
    }
   
}
