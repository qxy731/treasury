package com.soule.app.pfm.tm;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.ServletActionContext;
import com.soule.base.action.BaseAction;
public class DownLoadTemplateFileAction extends BaseAction {
    private static final long serialVersionUID = -957262999927865300L;
    private final static String DOWNLOADFILEPATH = "/upload/";
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getDownloadFile() {
        InputStream in=ServletActionContext.getServletContext().getResourceAsStream(DOWNLOADFILEPATH + fileName);
        return in;
    }

    public String getDownloadChineseFileName() {
        String downloadChineseFileName = fileName;
        try {
            downloadChineseFileName = new String(downloadChineseFileName.getBytes(), "ISO8859-1");
            System.out.println(downloadChineseFileName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return downloadChineseFileName;
    }
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
}
