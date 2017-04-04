package com.soule.app.pfm.tm;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.opensymphony.xwork2.ActionContext;
import com.soule.base.action.BaseAction;
import com.soule.base.service.LogonInfoHolder;
import com.soule.comm.file.FileManager;

public class TarQtyUploadFileAction extends BaseAction{
    private static final long serialVersionUID = -7743675737675945499L;
    
    private File upload;
    private String uploadContentType;
    private String uploadFileName;
    @Override
    public String execute() throws Exception {
        try {
            String targetDirectory = application.getRealPath("/upload");
            String targetFileName = uploadFileName;
            File target = new File(targetDirectory, targetFileName);
            FileUtils.copyFile(upload, target);
            setUploadFileName(target.getPath());
            FileManager fm = FileManager.getInstance();
            String filepath = target.getPath();
            String template = "ImportTarQty";
            HashMap<String,Object>data = new HashMap<String,Object>();
            Map<String,Object> session=(Map<String,Object>)ActionContext.getContext().getSession();
            LogonInfoHolder logUserInfo=(LogonInfoHolder)session.get("logUserInfo");
            data.put("createOrg", logUserInfo.getOperUnitId());
            fm.importData(template, filepath, data);
            request.setAttribute("resultFlag", "success");
        } catch (Exception e) {
           addActionError(e.getMessage());
           request.setAttribute("resultFlag", "error");
           return INPUT;
        }
        return SUCCESS;
    }
    
    public File getUpload() {
        return upload;
    }
    public void setUpload(File upload) {
        this.upload = upload;
    }
    public String getUploadContentType() {
        return uploadContentType;
    }
    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }
    public String getUploadFileName() {
        return uploadFileName;
    }
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

}
