package com.soule.crm.pub.dataimport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletOutputStream;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;

import com.soule.app.sys.enums.EnumItemPo;
import com.soule.base.action.BaseAction;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.base.service.ServiceResult;
import com.soule.comm.StringUtils;
import com.soule.comm.batch.PrimeEntrance;
import com.soule.comm.enu.BizType;
import com.soule.comm.enu.ExecuteResult;
import com.soule.comm.enu.FunctionType;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.ContextUtil;
import com.soule.comm.tools.DateFormatCalendar;
import com.soule.comm.tools.DateFormatDefine;
import com.soule.data.service.LoadFileDataManager;


@Namespace("/pub")
public class DataImportAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	//private final static Log logger = LogFactory.getLog(DataImportAction.class);
	private static final String STANDARD_USAGE = "Usage:java [-DRUNMODE=NEW/GOON] PrimeEntrance batchId[:instId] [yyyy-MM-dd]";
    @Autowired
    private IDataImportService dataImportService;
    @Autowired
    private AppUtils appUtils;
    @Autowired
    private IDefaultService sDefault;

    private DataImportQueryIn queryIn;
    private DataImportErrorDetailIn errorDetailIn; 
    private DataImportErrorDetailVo errorDetailVo;
    
    private String uploadId;
    private String fileId;
    private String uploadFileType;
    private String monthId;
    private String uoloadFiles;
    
    private String batchId;
    private String batchDate;
    private String instanceId;
    
    private String bizDateType;
    private String dataDate;
    
   

	public String getDataDate() {
		return dataDate;
	}

	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}

	public String query() {
        DataImportQueryIn in = queryIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            DataImportQueryOut result = dataImportService.query(in);
            ServiceResult head = result.getResultHead();
            rows = result.getDatalist();
            total = head.getTotal();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
            appUtils.saveAuditLog("testrep", "源数据文件查询", BizType.TARM, FunctionType.QUERY, ExecuteResult.SUCCESS);
        } catch (Exception e) {
        	try {
				appUtils.saveAuditLog("testrep", "源数据文件查询", BizType.TARM, FunctionType.QUERY, ExecuteResult.FAIL);
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
            appUtils.saveAuditLog("testrep", "源数据文件删除", BizType.TARM, FunctionType.DELETE, ExecuteResult.SUCCESS);
        } catch (Exception e) {
        	try {
				appUtils.saveAuditLog("testrep", "源数据文件删除", BizType.TARM, FunctionType.DELETE, ExecuteResult.FAIL);
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
            appUtils.saveAuditLog("testrep", "源数据文件错误信息查询", BizType.TARM, FunctionType.QUERY, ExecuteResult.SUCCESS);
        } catch (Exception e) {
        	try {
				appUtils.saveAuditLog("testrep", "源数据文件错误信息查询", BizType.TARM, FunctionType.QUERY, ExecuteResult.FAIL);
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
			String fileName="";
			EnumItemPo po = new EnumItemPo();
			try {
				po.setEnumId("uploadfile_type");
				po.setItemId(templateName);
				EnumItemPo po1 =  (EnumItemPo)sDefault.getIbatisMediator().findById("enum.getSysEnumItemByKey", po);
				fileName = po1.getItemValue();
				String[] str = fileName.split("\\.");
				templateName += "."+str[1];
			} catch (Exception e1) {
				e1.printStackTrace();
				return ;
			}
			
			
	        response.reset();
	        response.setContentType("text/plain;charset=UTF-8");
	        response.setHeader("Content-Disposition", "attachment;filename="
	            + new String(fileName.getBytes("GBK"),"ISO8859-1"));
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
    		this.retCode = "I0000";
    		this.retMsg = "加载数据文件需要一段时间，请耐心等待。加载完成后，每个文件的加载结果信息请查看详情！";
    	}catch (Exception e) {
    		this.retCode = "E0000";
    		this.retMsg = "加载数据文件失败，请查看文件是否正确！";
    		e.printStackTrace();
        }
    	return JSON;
    }
    
    public String batchTargetData(){
    	try{
    		String batchId = "bat_flow";
    		String dataDateTemp = dataDate;
    		dataDateTemp = dataDateTemp.replaceAll("-", "")+"01";
    		DateFormatCalendar.getInstance(dataDateTemp);
    		String batchDate = DateFormatCalendar.getMonthEndDate();
    		/*String batchDate = (String)sDefault.getIbatisMediator().findById("Common.getStrCurrDate", null);
    		if("2".equals(bizDateType)){
    			batchDate = getNextMonthEndDate(batchDate);
    		}*/
    		if (StringUtils.isEmpty(batchId) || StringUtils.isEmpty(batchDate)){
                System.out.println(STANDARD_USAGE);
                this.retCode = "E0000";
        		this.retMsg = "操作失败。";
                return JSON;
            }
	    	AbstractApplicationContext cxt = (AbstractApplicationContext)ContextUtil.getApplicationContext();
	        PrimeEntrance prime = new PrimeEntrance(cxt);
	        DateFormatCalendar.getInstance(batchDate,DateFormatDefine.FORMAT_YYYYMMDD_02);
	        String bDate = DateFormatCalendar.getLocalTime();
	        prime.doBatch(batchId, bDate, prime);
	        sDefault.getIbatisMediator().update("Common.updateCurrDate",batchDate);
	        this.retCode = "I0000";
    		this.retMsg = "操作成功。";
    	}catch(Exception e){
    		e.printStackTrace();
    		this.retCode = "E0000";
    		this.retMsg = "操作失败。";
    	}
    	return JSON;
    }
    
    
    
    public String deleteTargetData(){
    	try{
    		String batchId = "bat_flow";
    		String dataDateTemp = dataDate;
    		if(null==dataDate ||"".equals(dataDate)){
    			this.retCode = "I0000";
        		this.retMsg = "操作成功。";
        		return JSON;
    		}
    		dataDateTemp = dataDateTemp.replaceAll("-", "")+"01";
    		DateFormatCalendar.getInstance(dataDateTemp);
    		String batchDate = DateFormatCalendar.getMonthEndDate();
    		
    		this.dataImportService.deleteTargetData(batchDate);
	    	
	        this.retCode = "I0000";
    		this.retMsg = "操作成功。";
    	}catch(Exception e){
    		e.printStackTrace();
    		this.retCode = "E0000";
    		this.retMsg = "操作失败。";
    	}
    	return JSON;
    }
    
    /**
     * 获取任意时间下个月的最后一天
     * 描述:<描述函数实现的功能>.
     * @param bizDate
     * @return
     */
    private static String getNextMonthEndDate(String bizDate) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
            if(bizDate!=null && !"".equals(bizDate)){
                try {
					calendar.setTime(dft.parse(bizDate));
				} catch (ParseException e) {
					//e.printStackTrace();
				}
            }
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
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
	
	
	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getBatchDate() {
		return batchDate;
	}

	public void setBatchDate(String batchDate) {
		this.batchDate = batchDate;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	

	public String getBizDateType() {
		return bizDateType;
	}

	public void setBizDateType(String bizDateType) {
		this.bizDateType = bizDateType;
	}

	public static void main(String[] args){
		/*try{
    		PrimeEntrance prime = new PrimeEntrance();
            ConfigFactory.DEBUG = false;
            String rm = System.getProperty("RUNMODE");
            RunMode rmode = RunMode.NEWRUN;
            if (rm != null && rm.equalsIgnoreCase("GOON")) {
                rmode = RunMode.GOON;
            }
            String batchId = UUIDGenerator.generate("");
            String instanceId = null;
            prime.getContext().setRunMode(rmode);
            prime.getContext().setBatchId(batchId);
            prime.getContext().setInstId(instanceId);
            prime.clearHistoryData();
            prime.doAll();
    	}catch (Exception e) {
    		e.printStackTrace();
        }*/
		
		try {
			DateFormatCalendar.getInstance("20171031",DateFormatDefine.FORMAT_YYYYMMDD_02);
			String bDate = DateFormatCalendar.getLocalTime();
			System.out.println(bDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		
	}
	

}
