package com.soule.app.sys.fileupdown;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.soule.MsgConstants;
import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;
import com.soule.comm.po.IEnumType;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.EnumTypeUtil;


@Namespace("/sys")
public class FileUploadDownAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	//private final static Log logger = LogFactory.getLog(FileUploadDownAction.class);
	
    @Autowired
    private IFileUploadDownService service ;
    
    private List<File> myFile;
    private List<String> myFileName;
    private List<String> myFileType;
    private List<String> myImportType;
	private String businessDate;
	
	
	public void doInit() {
        try {
            IEnumType uf = EnumTypeUtil.getEnumType("uploadfile_type");
            ActionContext.getContext().put("fileTypelist", uf.getItems());
        } catch (Exception e) {
            //e.printStackTrace();
            handleError(e);
        }
    }
	
	public String upload(){
		 FileUploadDownOut result = new FileUploadDownOut();
		 try {
			 if(myFile==null||myFile.isEmpty()){
				 AppUtils.setResult(result, MsgConstants.E0009);
			 }
			 if(StringUtils.isBlank(businessDate)){
				 AppUtils.setResult(result, MsgConstants.E0001);
			 }
			 Map<String,Object> params = new HashMap<String,Object>();
			 params.put("businessDate", businessDate);
			 result = service.uploadFile(myFile,myFileName,myFileType,myImportType,params);
             ServiceResult head = result.getResultHead();
             this.setRetCode(head.getRetCode());
             this.setRetMsg(head.getRetMsg());
		 }catch(Exception e) {
	         handleError(e);
	     }
	     return JSON;
    }

	public List<File> getMyFile() {
		return myFile;
	}

	public void setMyFile(List<File> myFile) {
		this.myFile = myFile;
	}

	public List<String> getMyFileName() {
		return myFileName;
	}

	public void setMyFileName(List<String> myFileName) {
		this.myFileName = myFileName;
	}

	public List<String> getMyFileType() {
		return myFileType;
	}

	public void setMyFileType(List<String> myFileType) {
		this.myFileType = myFileType;
	}

	public List<String> getMyImportType() {
		return myImportType;
	}

	public void setMyImportType(List<String> myImportType) {
		this.myImportType = myImportType;
	}

	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}
	
}