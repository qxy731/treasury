package com.soule.app.sys.qstaff;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;

/**
 * 选择客户公共页面维护模块表现层处理类
 */
@Namespace("/sys")
public class QstaffAction extends BaseAction {
	private static final long serialVersionUID = -3833113690509144611L;

	@Autowired
    private IQstaffService service;

    /**
     * 查询客户信息 输入参数 
     */
    private QstaffQueryIn queryIn; //= new QstaffQueryIn();

    public void doInit() {
    }
    public String query() {
        QstaffQueryIn in = queryIn;
        try {
        	in.getInputHead().setPageNo(page);
        	in.getInputHead().setPageSize(pagesize);
        	QstaffQueryOut result = service.query(in);
            rows = result.getBase();
            total=(int)result.getResultHead().getTotal();
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
     * 查询客户信息
     */
    @JSON(serialize=false)
    public QstaffQueryIn getQueryIn() {
        return this.queryIn;
    }
    /**
     * 查询客户信息
     */
    public void setQueryIn(QstaffQueryIn in) {
        this.queryIn = in;
    }

}
