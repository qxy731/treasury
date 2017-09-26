package com.soule.crm.pub.dataimport;

import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceException;
import com.soule.base.service.ServiceResult;
import com.soule.comm.enu.BizType;
import com.soule.comm.enu.ExecuteResult;
import com.soule.comm.enu.FunctionType;
import com.soule.comm.tools.AppUtils;
import com.soule.data.service.LoadFileDataManager;


@Namespace("/pub")
@Results( { 
	@Result(name = "download", type = "stream",params={"contentType","text/plain","contentDisposition","attachment;filename=\"${templateName}\"","inputName","downloadFile"})
	})
public class DataImportAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Log logger = LogFactory.getLog(DataImportAction.class);
    @Autowired
    private IDataImportService dataImportService;
 /*   @Autowired
    private IDefaultService sDefault;*/
    @Autowired
    private AppUtils appUtils;

    private DataImportQueryIn queryIn;
    private DataImportErrorDetailIn errorDetailIn; 
    private DataImportErrorDetailVo errorDetailVo;
    
    private String uploadId;
    private String fileId;
    private String uploadFileType;
    private String monthId;
    private String uoloadFiles;
    private String templateName;
    private String templateType;
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

    public String query() {
        DataImportQueryIn in = queryIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            DataImportQueryOut result = dataImportService.query(in);
            ServiceResult head = result.getResultHead();
            // TODO
            rows = result.getDatalist();
            total = head.getTotal();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
            appUtils.saveAuditLog("testrep", "数据补录查询", BizType.PUBM, FunctionType.QUERY, ExecuteResult.SUCCESS);
        } catch (Exception e) {
        	try {
				appUtils.saveAuditLog("testrep", "数据补录查询", BizType.PUBM, FunctionType.QUERY, ExecuteResult.FAIL);
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
            handleError(e);
        }
        return JSON;
    }

    public String deleteFile() {
        DataImportQueryIn in = queryIn;
        try {
            DataBatchUploadOut result = dataImportService.deleteFile(in);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
            appUtils.saveAuditLog("testrep", "源数据文件删除", BizType.PUBM, FunctionType.DELETE, ExecuteResult.SUCCESS);
        } catch (Exception e) {
        	try {
				appUtils.saveAuditLog("testrep", "源数据文件删除", BizType.PUBM, FunctionType.DELETE, ExecuteResult.FAIL);
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
            handleError(e);
        }
        return JSON;
    }


    public String queryFileDetail() { 
        try {
        	errorDetailIn.getInputHead().setPageNo(this.page);
        	errorDetailIn.getInputHead().setPageSize(this.pagesize);
        	DataImportErrorDetailOut errorDetailOut = dataImportService.queryFileDetail(errorDetailIn);
            ServiceResult head = errorDetailOut.getResultHead();
            this.rows = errorDetailOut.getErrorDetailList();
            this.errorDetailVo = errorDetailOut.getErrorObject();
            this.total = head.getTotal();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
            appUtils.saveAuditLog("testrep", "源数据文件错误信息查询", BizType.PUBM, FunctionType.QUERY, ExecuteResult.SUCCESS);
        } catch (Exception e) {
        	try {
				appUtils.saveAuditLog("testrep", "源数据文件错误信息查询", BizType.PUBM, FunctionType.QUERY, ExecuteResult.FAIL);
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
            handleError(e);
        }
        return JSON;
    }
    
    public String downTemplate(){
    	System.out.println(templateName);
    	System.out.println(templateType);
    	return "download";
    }
    
    public InputStream getDownloadFile() {
        try {
        	if(StringUtils.isNotBlank(templateName)){
        		//templateName = "大额来账清单.csv";
            	return ServletActionContext.getServletContext().getResourceAsStream("upload/template/"+templateName);
        	}
            //File f = new File(path,templateName);
            //return new FileInputStream(f);
        } catch (Exception e) {
            logger.error("ACTION",e);
        }
        return null;
    }
    
    public String loadFileData(){
    	try{
    		LoadFileDataManager.loadData();
    		//dataImportService.loadData();
    		this.retCode = "I0000";
    		this.retMsg = "加载数据文件需要一段时间，请耐心等待。加载完成后，每个文件的加载结果信息请查看详情！";
    	}catch (Exception e) {
    		this.retCode = "E0000";
    		this.retMsg = "加载数据文件失败，请查看文件是否正确！";
    		e.printStackTrace();
            //handleError(e);
        }
    	return JSON;
    }

    @JSON(serialize = false)
    public DataImportQueryIn getQueryIn() {
        return queryIn;
    }

    public void setQueryIn(DataImportQueryIn queryIn) {
        this.queryIn = queryIn;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getUploadFileType() {
        return uploadFileType;
    }

    public void setUploadFileType(String uploadFileType) {
        this.uploadFileType = uploadFileType;
    }

    public String getMonthId() {
        return monthId;
    }

    public void setMonthId(String monthId) {
        this.monthId = monthId;
    }

    public String getUoloadFiles() {
        return uoloadFiles;
    }

    public void setUoloadFiles(String uoloadFiles) {
        this.uoloadFiles = uoloadFiles;
    }

	public DataImportErrorDetailIn getErrorDetailIn() {
		return errorDetailIn;
	}

	public void setErrorDetailIn(DataImportErrorDetailIn errorDetailIn) {
		this.errorDetailIn = errorDetailIn;
	}

	public DataImportErrorDetailVo getErrorDetailVo() {
		return errorDetailVo;
	}

	public void setErrorDetailVo(DataImportErrorDetailVo errorDetailVo) {
		this.errorDetailVo = errorDetailVo;
	}

}
