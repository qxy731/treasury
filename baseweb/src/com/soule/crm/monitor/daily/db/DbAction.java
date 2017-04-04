package com.soule.crm.monitor.daily.db;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;

/**
 * 数据库监控维护模块表现层处理类
 */
@Namespace("/monitor")
public class DbAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
    private IDbService service;

    /**
     * 数据库状态 输入参数 
     */
    private DbInfoIn infoIn; //= new DbInfoIn();
    /**
     * 表空间信息 输入参数 
     */
    private DbTablespaceIn tablespaceIn; //= new DbTablespaceIn();
    /**
     * 表信息 输入参数 
     */
    private DbTableIn tableIn; //= new DbTableIn();

    private DbDbPo dbPo;
    private String tsId;

    public void doInit() {
        tsId = request.getParameter("tsId");
    }
    public String info() {
        try {
            dbPo = new DbDbPo();
            DbInfoOut result = service.info(new DbInfoIn());
            if (result.getResultHead().isSuccess()) {
                if ( result.getResultHead().getTotal() > 0) {
                    dbPo = result.getDb().get(0);
                }
            }
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String tablespace() {
        DbTablespaceIn in = new DbTablespaceIn();
        try {
            DbTablespaceOut result = service.tablespace(in);
            ServiceResult head = result.getResultHead();
            this.rows = result.getTs();
            this.total = result.getResultHead().getTotal();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String table() {
        DbTableIn in = tableIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            in.getInputHead().setSortname(this.sortname);
            in.getInputHead().setSortorder(this.sortorder);
            DbTableOut result = service.table(in);
            ServiceResult head = result.getResultHead();
            this.rows = result.getTable();
            this.total = head.getTotal();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }

    /**
     * 数据库状态
     */
    @JSON(serialize=false)
    public DbInfoIn getInfoIn() {
        return this.infoIn;
    }
    /**
     * 数据库状态
     */
    public void setInfoIn(DbInfoIn in) {
        this.infoIn = in;
    }
    /**
     * 表空间信息
     */
    @JSON(serialize=false)
    public DbTablespaceIn getTablespaceIn() {
        return this.tablespaceIn;
    }
    /**
     * 表空间信息
     */
    public void setTablespaceIn(DbTablespaceIn in) {
        this.tablespaceIn = in;
    }
    /**
     * 表信息
     */
    @JSON(serialize=false)
    public DbTableIn getTableIn() {
        return this.tableIn;
    }
    /**
     * 表信息
     */
    public void setTableIn(DbTableIn in) {
        this.tableIn = in;
    }
    public DbDbPo getDbPo() {
        return dbPo;
    }
    public void setDbPo(DbDbPo dbPo) {
        this.dbPo = dbPo;
    }
    public String getTsId() {
        return tsId;
    }
    public void setTsId(String tsId) {
        this.tsId = tsId;
    }

}
