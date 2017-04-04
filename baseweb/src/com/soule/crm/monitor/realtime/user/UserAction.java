package com.soule.crm.monitor.realtime.user;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;

/**
 * 在线用户监控维护模块表现层处理类
 */
@Namespace("/monitor")
public class UserAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
    private IUserService service;

    /**
     * 在线用户查询 输入参数 
     */
    private UserListIn listIn; //= new UserListIn();

    public void doInit() {
    }
    public String list() {
        UserListIn in = listIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            UserListOut result = service.list(in);
            ServiceResult head = result.getResultHead();
            this.rows = result.getDetail();
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
     * 在线用户查询
     */
    @JSON(serialize=false)
    public UserListIn getListIn() {
        return this.listIn;
    }
    /**
     * 在线用户查询
     */
    public void setListIn(UserListIn in) {
        this.listIn = in;
    }

}
