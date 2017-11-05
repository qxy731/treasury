package com.soule.crm.pfm.param.paraminfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceException;
import com.soule.base.service.ServiceResult;
import com.soule.comm.enu.BizType;
import com.soule.comm.enu.ExecuteResult;
import com.soule.comm.enu.FunctionType;
import com.soule.comm.tools.AppUtils;


/**
 * 指标信息 Action类
 */
@Namespace("/pfm/param") 
public class ParaminfoAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    //private final static Log logger = LogFactory.getLog(ParaminfoAction.class);
    @Autowired
    IParaminfoService service;
    @Autowired
	AppUtils appUtils;

    /**
     * 指标信息查询 输入参数 
     */
    private ParaminfoQueryIn queryIn; //= new ParaminfoQueryIn();
    
    private ParaminfoQPo paraminfo;
    
    public void initParam(){
    	String indexCode = request.getParameter("indexCode");
    	if (indexCode.equals("null") || indexCode.trim().equals("")) {
    	}else{
    		ParaminfoQueryIn in = new ParaminfoQueryIn();
            try {
                in.getInputHead().setPageNo(this.page);
                in.getInputHead().setPageSize(this.pagesize);
                in.setIndexCode(indexCode);
                ParaminfoQueryOut result = service.query(in);
                ServiceResult head = result.getResultHead();
                this.rows=result.getQ();
                this.total=head.getTotal();
                this.setRetCode(head.getRetCode());
                this.setRetMsg(head.getRetMsg());
                if(rows.size() > 0){
                	paraminfo = (ParaminfoQPo)rows.get(0);
                }
                appUtils.saveAuditLog("paraminfo", "指标库详情", BizType.TARM, FunctionType.QUERY, ExecuteResult.SUCCESS);
            }
            catch(Exception e) {
            	try {
					appUtils.saveAuditLog("paraminfo", "指标库详情", BizType.TARM, FunctionType.QUERY, ExecuteResult.FAIL);
				} catch (ServiceException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                handleError(e);
            }
    	}
    }

    /**
     * 方法 指标信息查询
     */
    public String query() {
        ParaminfoQueryIn in = queryIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            ParaminfoQueryOut result = service.query(in);
            ServiceResult head = result.getResultHead();
            this.rows=result.getQ();
            this.total=head.getTotal();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
            appUtils.saveAuditLog("paraminfo", "指标库查询", BizType.TARM, FunctionType.QUERY, ExecuteResult.SUCCESS);
        }
        catch(Exception e) {
        	try {
				appUtils.saveAuditLog("paraminfo", "指标库查询", BizType.TARM, FunctionType.QUERY, ExecuteResult.FAIL);
			} catch (ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            handleError(e);
        }
        return JSON;
    }
    
    /**
     * 方法 指标信息查询
     */
    public String queryAll() {
        ParaminfoQueryIn in = queryIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            ParaminfoQueryOut result = service.queryAll(in);
            ServiceResult head = result.getResultHead();
            this.rows=result.getQ();
            this.total=head.getTotal();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
            appUtils.saveAuditLog("paraminfo", "指标库查询", BizType.TARM, FunctionType.QUERY, ExecuteResult.SUCCESS);
        }catch(Exception e) {
        	try {
				 appUtils.saveAuditLog("paraminfo", "指标库查询", BizType.TARM, FunctionType.QUERY, ExecuteResult.FAIL);
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
        	e.printStackTrace();
            handleError(e);
        }
        return JSON;
    }

    @JSON(serialize=false)
    public ParaminfoQueryIn getQueryIn() {
        return this.queryIn;
    }

    public void setQueryIn(ParaminfoQueryIn in) {
        this.queryIn = in;
    }

	public ParaminfoQPo getParaminfo() {
		return paraminfo;
	}

	public void setParaminfo(ParaminfoQPo paraminfo) {
		this.paraminfo = paraminfo;
	}

}
