package com.soule.crm.pub.dataimport;

import java.io.InputStream;
import java.util.HashMap;

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
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
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
    @Autowired
    private IDefaultService sDefault;
    @Autowired
    private AppUtils appUtils;

    private DataImportQueryIn queryIn; // = new RateQueryIn();
    private String uploadId;
    private String fileId;
    private String uploadFileType;
    private String monthId;
    private String uoloadFiles;
    
    private String recordTotle;
    private String recordSuccess;
    private String recordError;
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

	public void doInit() {
        /*try {
            List<EnumItemPo> list = dataImportService.queryFileTypeList(AppUtils.getLogonUserInfo());
            ActionContext.getContext().put("fileTypelist", list);
        } catch (Exception e) {
            //e.printStackTrace();
            handleError(e);
        }*/
    }

    public void doInitDetail() {
        String uploadFileType = request.getParameter("uploadFileType");
        try {
            rows = sDefault.getIbatisMediator().find("dataimport.getUploadModel", uploadFileType);
            if (rows != null) {
                for (int i = 0 ; i < rows.size() ;i++){
                    HashMap one = (HashMap) rows.get(i);
                    String x = (String) one.get("TITLE_NAME");
                    if (x != null) {
                        one.put("TITLE_NAME", x.toUpperCase());
                    }
                }
            }
        } catch (DbAccessException e) {
            handleError(e);
        }
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

    public String uploadFile() {
        String uploadId = this.uploadId;
        String uploadFileType = this.uploadFileType;
        try {
            DataBatchUploadOut result = new DataBatchUploadOut();
            result = dataImportService.saveImpData(uploadId, monthId, uploadFileType);
            ServiceResult head = result.getResultHead();
            rows = result.getErrors();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
            appUtils.saveAuditLog("testrep", "数据补录上传", BizType.PUBM, FunctionType.IMPORT, ExecuteResult.SUCCESS);
        } catch (Exception ex) {
        	try {
				appUtils.saveAuditLog("testrep", "数据补录上传", BizType.PUBM, FunctionType.IMPORT, ExecuteResult.FAIL);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            handleError(ex);
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
            appUtils.saveAuditLog("testrep", "数据补录删除", BizType.PUBM, FunctionType.DELETE, ExecuteResult.SUCCESS);
        } catch (Exception e) {
        	try {
				appUtils.saveAuditLog("testrep", "数据补录删除", BizType.PUBM, FunctionType.DELETE, ExecuteResult.FAIL);
			} catch (ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            handleError(e);
        }
        return JSON;
    }

    public String callUploadData() {
        String uploadId = this.uploadId;
        String uploadFileType = this.uploadFileType;
        String monthId = this.monthId;
        try {
        	dataImportService.callUploadData(uploadId, uploadFileType, monthId);
            this.setRetCode("I0000");
            this.setRetMsg("执行成功");
        } catch (Exception e) {
            handleError(e);
        }
        return JSON;
    }

    public String queryFileDetail() { 
        DataImportQueryIn in = queryIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            in.getInputHead().setSortname(this.sortname);
            in.getInputHead().setSortorder(this.sortorder);
            DataImportQueryOut result = dataImportService.queryFileDetail(in);
            ServiceResult head = result.getResultHead();
            rows = result.getDetailList();
            total = head.getTotal();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
            recordTotle = result.getRecordTotle();
            recordSuccess = result.getRecordSuccess();
            recordError = result.getRecordError();
            appUtils.saveAuditLog("testrep", "数据补录详情", BizType.PUBM, FunctionType.QUERY, ExecuteResult.SUCCESS);
        } catch (Exception e) {
        	try {
				appUtils.saveAuditLog("testrep", "数据补录详情", BizType.PUBM, FunctionType.QUERY, ExecuteResult.FAIL);
			} catch (ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            handleError(e);
        }
        return JSON;
    }
    
    public String queryAjaxRecord() {
    	DataImportQueryIn in = queryIn;
        try {
            DataImportQueryOut result = dataImportService.queryAjaxRecord(in);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
            recordTotle = result.getRecordTotle();
            recordSuccess = result.getRecordSuccess();
            recordError = result.getRecordError();
        } catch (Exception e) {
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

	public String getRecordTotle() {
		return recordTotle;
	}

	public void setRecordTotle(String recordTotle) {
		this.recordTotle = recordTotle;
	}

	public String getRecordSuccess() {
		return recordSuccess;
	}

	public void setRecordSuccess(String recordSuccess) {
		this.recordSuccess = recordSuccess;
	}

	public String getRecordError() {
		return recordError;
	}

	public void setRecordError(String recordError) {
		this.recordError = recordError;
	}

}
