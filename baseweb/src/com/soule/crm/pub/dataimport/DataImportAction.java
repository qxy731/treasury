package com.soule.crm.pub.dataimport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
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
import com.soule.data.service.LoadFileDataManager;


@Namespace("/pub")
public class DataImportAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	//private final static Log logger = LogFactory.getLog(DataImportAction.class);
    @Autowired
    private IDataImportService dataImportService;
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
    
    public void downTemplate(){
		try {
			String templateName = request.getParameter("templateName");
			templateName = java.net.URLDecoder.decode(templateName, "utf-8");
	        response.reset();
	        response.setContentType("text/plain;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename="
	            + new String(templateName.getBytes(),"ISO8859-1"));
	        ServletOutputStream out = null;
	        InputStream is = null;
	        BufferedInputStream bis = null;
	        BufferedOutputStream bos = null;
	        try {
	          out = response.getOutputStream();
	          is = ServletActionContext.getServletContext().getResourceAsStream("upload/template/"+templateName);
	          bis = new BufferedInputStream(is);
	          bos = new BufferedOutputStream(out);
	          byte[] buff = new byte[1024];
	          int bytesRead;
	          while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	            bos.write(buff, 0, bytesRead);
	          }
	        } catch (Exception e) {
	          e.printStackTrace();
	        } finally {
	          if (bis != null)
	            bis.close();
	          if (bos != null)
	            bos.close();
	          if(is != null)
	        	 is.close();
	          if(out != null){
	        	  out.flush();
	  	          out.close();
	          }
	        }
	        
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return;
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
	
	public static void main(String[] args){
		
		try {
			///baseweb/pub/data-import!downTemplate.action?templateName=%E5%B0%8F%E9%A2%9D%E6%9D%A5%E8%B4%A6%E6%B8%85%E5%8D%95.csv
			String mytext = java.net.URLEncoder.encode("小额来账清单.csv","utf-8");
			System.out.println(mytext);
			String value = "%E5%B0%8F%E9%A2%9D%E6%9D%A5%E8%B4%A6%E6%B8%85%E5%8D%95.csv";
			System.out.println(value);
			String mytext2  = java.net.URLDecoder.decode(value,"utf-8"); 
			
			if(mytext.equals(value)){
				System.out.println(true);
			}
			System.out.println(mytext2);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		
	}
	

}
