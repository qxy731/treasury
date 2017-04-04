package com.soule.crm.monitor.daily.proc;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;

/**
 * 进程监控维护模块表现层处理类
 */
@Namespace("/monitor")
public class ProcAction extends BaseAction {
	private static final long serialVersionUID = 1L;
    @Autowired
    private IProcService service;

    /**
     * 进程信息 输入参数 
     */
    private ProcListIn listIn; //= new ProcListIn();
    private String sortname;
    private String sortorder;

    public void doInit() {
    }
    public String list() {
        try {
            listIn = new ProcListIn();
            listIn.setSortname(sortname);
            listIn.setSortorder(sortorder);
            ProcListOut result = service.list(listIn);
            ServiceResult head = result.getResultHead();
            this.rows = result.getDetail();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }

    /**
     * 进程信息
     */
    @JSON(serialize=false)
    public ProcListIn getListIn() {
        return this.listIn;
    }
    /**
     * 进程信息
     */
    public void setListIn(ProcListIn in) {
        this.listIn = in;
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

}
