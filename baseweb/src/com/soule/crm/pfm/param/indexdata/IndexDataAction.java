package com.soule.crm.pfm.param.indexdata;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceException;
import com.soule.base.service.ServiceResult;
import com.soule.comm.enu.BizType;
import com.soule.comm.enu.BizType1;
import com.soule.comm.enu.ExecuteResult;
import com.soule.comm.enu.FunctionType;
import com.soule.comm.tools.AppUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 指标数据补录 Action类
 */
@Namespace("/pfm/param") 
public class IndexDataAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private final static Log logger = LogFactory.getLog(IndexDataAction.class);
    @Autowired
    IIndexDataService service;
    @Autowired
	AppUtils appUtils;

    /**
     * 指标数据补录信息查询 输入参数 
     */
    private IndexDataQueryIn queryIn; //= new IndexDataQueryIn();
    /**
     * 解析上传文件 输入参数 
     */
    private IndexDataReadXlsFileIn readXlsFileIn; //= new IndexDataReadXlsFileIn();
    
    private String templateFile;

    /**
     * 方法 指标数据补录信息查询
     */
    public String query() {
        IndexDataQueryIn in = queryIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            IndexDataQueryOut result = service.query(in);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
            appUtils.saveAuditLog("indexdataimport", "指标数据补录信息查询", BizType.PFM, FunctionType.QUERY, ExecuteResult.SUCCESS);
        }
        catch(Exception e) {
        	try {
				appUtils.saveAuditLog("indexdataimport", "指标数据补录信息查询", BizType.PFM, FunctionType.QUERY, ExecuteResult.FAIL);
			} catch (ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            handleError(e);
        }
        return JSON;
    }
    /**
     * 方法 导出模板
     */
    public String exportTemplate() {
        IndexDataQueryIn in = queryIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            IndexDataQueryOut result = service.exportTemplate(in);
            templateFile = result.getTemplateFile();
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
            appUtils.saveAuditLog("indexdataimport", "导出模板", BizType.PFM, FunctionType.EXPORT, ExecuteResult.SUCCESS);
        }
        catch(Exception e) {
        	try {
				appUtils.saveAuditLog("indexdataimport", "导出模板", BizType.PFM, FunctionType.EXPORT, ExecuteResult.FAIL);
			} catch (ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            handleError(e);
        }
        return JSON;
    }
    
    /**
     * 方法 解析上传文件
     */
    public String readXlsFile() {
        IndexDataReadXlsFileIn in = readXlsFileIn;
        try {
        	in.setObjectType("2");
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            IndexDataReadXlsFileOut result = service.readXlsFile(in);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
            appUtils.saveAuditLog("indexdataimport", "解析上传文件", BizType.PFM, FunctionType.IMPORT, ExecuteResult.SUCCESS);
        }
        catch(Exception e) {
        	try {
				appUtils.saveAuditLog("indexdataimport", "解析上传文件", BizType.PFM, FunctionType.IMPORT, ExecuteResult.FAIL);
			} catch (ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            handleError(e);
        }
        return JSON;
    }

    @JSON(serialize=false)
    public IndexDataQueryIn getQueryIn() {
        return this.queryIn;
    }

    public void setQueryIn(IndexDataQueryIn in) {
        this.queryIn = in;
    }
    @JSON(serialize=false)
    public IndexDataReadXlsFileIn getReadXlsFileIn() {
        return this.readXlsFileIn;
    }

    public void setReadXlsFileIn(IndexDataReadXlsFileIn in) {
        this.readXlsFileIn = in;
    }
	public String getTemplateFile() {
		return templateFile;
	}
	public void setTemplateFile(String templateFile) {
		this.templateFile = templateFile;
	}

}
