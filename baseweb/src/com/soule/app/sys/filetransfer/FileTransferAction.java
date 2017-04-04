package com.soule.app.sys.filetransfer;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.app.table.SysUploadFilePo;
import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceException;
import com.soule.base.service.ServiceResult;
import com.soule.base.service.keygen.IKeyGenerator;
import com.soule.comm.tools.ContextUtil;
import com.soule.comm.tools.StringUtil;

/**
 * 上传下载维护模块表现层处理类
 */
@Namespace("/sys")
@Results( { 
	@Result(name = "success", location = "/jsp/common/upload/file_inner.jsp"),
	@Result(name = "download", type = "stream",params={"contentType","text/plain","contentDisposition","attachment;filename=\"${fileName}\"","inputName","downloadFile"})
	})
public class FileTransferAction extends BaseAction {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final static Log logger = LogFactory.getLog(FileTransferAction.class);
    @Autowired
    private IFileTransferService service ;
    @Autowired
    private IKeyGenerator keyg;
    /**
     * 上传文件 输入参数 
     */
    private FileTransferUploadIn uploadIn; //= new FileTransferUploadIn();
    /**
     * 上传文件列表 输入参数 
     */
    private FileTransferListIn listIn; //= new FileTransferListIn();
    /**
     * 上传文件删除 输入参数 
     */
    private FileTransferDeleteIn deleteIn; //= new FileTransferDeleteIn();
    /**
     * 文件下载 输入参数 
     */
    private FileTransferDownloadIn downloadIn; //= new FileTransferDownloadIn();

    private File onefile;
    private String onefileContentType;
    private String onefileFileName;
    private String uploadId;
    private String filesStr = "[]";
    private String fileName;
    private SysUploadFilePo downloadFile;
    /**
     * 允许上传文件类型
     */
    private String filter = "*";

    public void initUploadId() {
        uploadId = request.getParameter("uid");
        String ff = request.getParameter("filter");
        if (!StringUtil.isEmpty(ff)) {
            filter = ff;
        }
        if (StringUtil.isEmpty(uploadId)) {
            uploadId = keyg.getUUIDKey();
        }
        else {
            //查询当前已经上传的文件
            FileTransferListIn in = new FileTransferListIn();
            in.setUploadId(uploadId);
            FileTransferListOut result;
            try {
                result = service.list(in);
                if (result.getResultHead().isSuccess()) {
                    this.filesStr = JSONArray.fromObject(result.getFile()).toString();
                }
            } catch (ServiceException e) {
                logger.error("INIT",e);
            }
        }
        uploadIn = new FileTransferUploadIn();
        uploadIn.setFile(new SysUploadFilePo());
        uploadIn.getFile().setUploadId(uploadId);
    }

    public String upload() {
        FileTransferUploadIn in = uploadIn;
        try {
            if (in !=null) {
                in.getFile().setFileName(onefileFileName);
                in.getFile().setFilePath(onefile.getAbsolutePath());
                in.getFile().setFileSize((int)onefile.length());
                FileTransferUploadOut result = service.upload(in);
                ServiceResult head = result.getResultHead();
                uploadIn.setFile(result.getFile());
                this.setRetCode(head.getRetCode());
                this.setRetMsg(head.getRetMsg());
            }
        }
        catch(Exception e) {
            handleError(e);
        }
        return SUCCESS;
    }

    public String delete() {
        FileTransferDeleteIn in = deleteIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            FileTransferDeleteOut result = service.delete(in);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }

    public String download() {
        FileTransferDownloadIn in = downloadIn;
        try {
            FileTransferDownloadOut result = service.download(in);
            ServiceResult head = result.getResultHead();
            if (head.isSuccess()) {
                downloadFile = result.getFile();
                fileName = new String(downloadFile.getFileName().getBytes(),"ISO8859-1");
            }
        }
        catch(Exception e) {
            handleError(e);
        }
        return "download";
    }

    public InputStream getDownloadFile() {
        if (downloadFile != null) {
            File f = new File(downloadFile.getFilePath(),downloadFile.getFileId());
            try {
                return new FileInputStream(f);
            } catch (Exception e) {
                logger.error("ACTION",e);
            }
        }
        return null;
    }
    
    /**
     * 上传文件
     */
    public FileTransferUploadIn getUploadIn() {
        return this.uploadIn;
    }
    /**
     * 上传文件
     */
    public void setUploadIn(FileTransferUploadIn in) {
        this.uploadIn = in;
    }
    /**
     * 上传文件列表
     */
    @JSON(serialize=false)
    public FileTransferListIn getListIn() {
        return this.listIn;
    }
    /**
     * 上传文件列表
     */
    public void setListIn(FileTransferListIn in) {
        this.listIn = in;
    }
    /**
     * 上传文件删除
     */
    @JSON(serialize=false)
    public FileTransferDeleteIn getDeleteIn() {
        return this.deleteIn;
    }
    /**
     * 上传文件删除
     */
    public void setDeleteIn(FileTransferDeleteIn in) {
        this.deleteIn = in;
    }
    public File getOnefile() {
        return onefile;
    }
    public void setOnefile(File onefile) {
        this.onefile = onefile;
    }
    public String getOnefileContentType() {
        return onefileContentType;
    }
    public void setOnefileContentType(String onefileContentType) {
        this.onefileContentType = onefileContentType;
    }
    public String getOnefileFileName() {
        return onefileFileName;
    }
    public void setOnefileFileName(String onefileFileName) {
        this.onefileFileName = onefileFileName;
    }
    public String getUploadId() {
        return uploadId;
    }
    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public String getFilesStr() {
        return filesStr;
    }

    public void setFilesStr(String filesStr) {
        this.filesStr = filesStr;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**
     * 文件下载
     */
    @JSON(serialize=false)
    public FileTransferDownloadIn getDownloadIn() {
        return this.downloadIn;
    }
    /**
     * 文件下载
     */
    public void setDownloadIn(FileTransferDownloadIn in) {
        this.downloadIn = in;
    }
}
