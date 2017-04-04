package com.soule.crm.monitor.realtime.sql;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceException;
import com.soule.base.service.ServiceResult;

/**
 * Sql监控维护模块表现层处理类
 */
@Namespace("/monitor")
public class SqlAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private final static Log logger = LogFactory.getLog(SqlAction.class);
    @Autowired
    private ISqlService service;

    /**
     * Sql监控结果查询 输入参数 
     */
    private SqlListIn listIn; //= new SqlListIn();
    /**
     * Sql监控明细 输入参数 
     */
    private SqlDetailIn detailIn; //= new SqlDetailIn();

    private String sortname;
    private String sortorder;
    private Date startTime;
    private SqlDetailOut out;

    public void doInit() {
        startTime = new Date();
    }

    public void initDetail() {
        try {
            String sqlId = this.request.getParameter("sqlId");
            detailIn = new SqlDetailIn();
            detailIn.setSqlId(sqlId);
            out = service.detail(detailIn);
        } catch (ServiceException e) {
            logger.error("PAGE",e);
        }
    }

    public String list() {
        SqlListIn in = listIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            in.setSortName(sortname);
            in.setSortOrder(sortorder);
            SqlListOut result = service.list(in);
            ServiceResult head = result.getResultHead();
            this.rows = result.getDetail();
            this.total = result.getResultHead().getTotal();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String detail() {
        SqlDetailIn in = detailIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            SqlDetailOut result = service.detail(in);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }

    public void resetStats() {
        try {
            service.reset();
        } catch (ServiceException e) {
            logger.error("PAGE",e);
        }
    }

    /**
     * Sql监控结果查询
     */
    @JSON(serialize=false)
    public SqlListIn getListIn() {
        return this.listIn;
    }
    /**
     * Sql监控结果查询
     */
    public void setListIn(SqlListIn in) {
        this.listIn = in;
    }
    /**
     * Sql监控明细
     */
    @JSON(serialize=false)
    public SqlDetailIn getDetailIn() {
        return this.detailIn;
    }
    /**
     * Sql监控明细
     */
    public void setDetailIn(SqlDetailIn in) {
        this.detailIn = in;
    }
    public String getSortname() {
        return sortname;
    }
    public void setSortname(String sortname) {
        this.sortname = sortname;
    }
    public String getSortorder() {
        return sortorder;
    }
    public void setSortorder(String sortorder) {
        this.sortorder = sortorder;
    }
    public SqlDetailOut getOut() {
        return out;
    }
    public void setOut(SqlDetailOut out) {
        this.out = out;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

}
