package com.soule.crm.demo.comp.dropdown;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;
import com.soule.comm.tools.ContextUtil;

/**
 * 机构下拉框维护模块表现层处理类
 */
@Namespace("/demo")
public class DropdownAction extends BaseAction {
    private final static Log logger = LogFactory.getLog(DropdownAction.class);
    @Autowired
    private IDropdownService service ;

    /**
     * 获取匹配数据 输入参数 
     */
    private DropdownListIn listIn; //= new DropdownListIn();

    public void doInit() {
    }
    public String list() {
        DropdownListIn in = listIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            DropdownListOut result = service.list(in);
            ServiceResult head = result.getResultHead();
            this.rows = result.getData();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }

    /**
     * 获取匹配数据
     */
    @org.apache.struts2.json.annotations.JSON(serialize=false)
    public DropdownListIn getListIn() {
        return this.listIn;
    }
    /**
     * 获取匹配数据
     */
    public void setListIn(DropdownListIn in) {
        this.listIn = in;
    }

}
