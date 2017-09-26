package com.soule.data.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.soule.comm.DefaultResultMsg;
import com.soule.comm.IResultMsg;
import com.soule.comm.db.BatchResult;
import com.soule.comm.db.DatabaseHelper;
import com.soule.comm.db.LineResultInfo;
import com.soule.comm.file.FileManager;
import com.soule.comm.id.UUIDGenerator;
import com.soule.comm.tools.DateFormatCalendar;
import com.soule.data.bean.SysUploadFile;
import com.soule.data.bean.SysUploadFileMapping;
import com.soule.data.bean.SysUploadFileQueue;
import com.soule.data.dao.BatTableDao;
import com.soule.data.dao.SysUploadFileDao;
import com.soule.data.dao.SysUploadFileErrorDao;
import com.soule.data.dao.SysUploadFileErrorDetailDao;
import com.soule.data.dao.SysUploadFileMappingDao;
import com.soule.data.dao.SysUploadFileQueueDao;

public class FileImportManager {
	
	private static Logger log = Logger.getLogger(FileImportManager.class);
	
	//private static FileQueueManager manager;
	
	/*public static FileQueueManager getInstance() {
        if (manager == null) {
            synchronized (FileQueueManager.class) {
                if (manager == null) {
                    manager = new FileQueueManager();
                }
            }
        }
        return manager;
    }*/
	
	/**
	 * 获取未处理的上传文件
	 * @return
	 */
	public SysUploadFile getUploadFile(){
		//从队列表获取最新的一条
		SysUploadFileQueueDao queueDao = new SysUploadFileQueueDao();
		SysUploadFileQueue fileQueue = queueDao.getSysUploadFileQueue();
		if(fileQueue==null){
			log.info("the object of fileQueue is null.");
			return null;
		}
		String uploadId = fileQueue.getUploadId();
		String queueId = fileQueue.getQueueId();
		if(StringUtils.isBlank(uploadId)||StringUtils.isBlank(queueId)){
			log.info("the object of uploadId or queueId is null.");
			return null;
		}
		//删除队列中
		queueDao.deleteSysUploadFileQueue(queueId);
		//从文件表获取文件对象
		SysUploadFileDao fileDao = new SysUploadFileDao();
		return fileDao.getOneUploadFile(uploadId);
	}
	
	
	public int queryUploadFileQueueCount(){
		SysUploadFileQueueDao queueDao = new SysUploadFileQueueDao();
		return queueDao.queryUploadFileQueueCount();
	}
	
	
	public void importFileData(Map<String,Object> params){
		SysUploadFile sysUploadFilePo = (SysUploadFile)params.get("sysUploadFilePo");
		if(sysUploadFilePo==null){
        	log.info("the object of sysUploadFilePo is null.");
        	return;
        }
		String uploadId = sysUploadFilePo.getUploadId();
		String filepath = sysUploadFilePo.getFilePath()+"/"+uploadId;
        String fileType = sysUploadFilePo.getFileType();
        String businessDate = sysUploadFilePo.getBusinessDate();
        String batchId = sysUploadFilePo.getBatchId();
        if(StringUtils.isBlank(uploadId)){
        	log.info("the object of uploadId is null.");
        	return;
        }
        if(StringUtils.isBlank(filepath)){
        	log.info("the object of filepath is null.");
        	return;
        }
        if(StringUtils.isBlank(fileType)){
    		log.info("the object of fileType is null.");
        	return;
    	}
        if(StringUtils.isBlank(businessDate)){
        	log.info("the object of businessDate is null.");
        	return;
        }
        if(StringUtils.isBlank(batchId)){
        	log.info("the object of batchId is null.");
        	return;
        }
        SysUploadFileMappingDao mappingDao = new SysUploadFileMappingDao();
        SysUploadFileMapping sysUploadFileMapping = mappingDao.getOneSysUploadFileMapping(fileType);
        if(sysUploadFileMapping==null){
        	log.info("the object of SysUploadFileMapping is null.");
        	return;
        }
        String fileFreq = sysUploadFileMapping.getFileFreq();
        String template = sysUploadFileMapping.getFileTemplate();
	    String table = sysUploadFileMapping.getFileTable();
        if(StringUtils.isBlank(fileFreq)){
        	log.info("the object of fileFreq is null.");
        	return;
        }
        if(StringUtils.isBlank(template)){
        	log.info("the object of fileTemplate is null.");
        	return;
        }
        if(StringUtils.isBlank(table)){
        	log.info("the object of fileTable is null.");
        	return;
        }
        String date = null;
        try {
			DateFormatCalendar.getInstance(businessDate);
			date = DateFormatCalendar.getBusinessDate(fileFreq);
			//删除数据对应表对应当天的数据
	    	if(StringUtils.isBlank(date)){
	    		log.info("the object of businessDate is null.");
	        	return;
	    	}
		} catch (ParseException e1) {
			log.info("the object of businessDate ParseException.");
			return;
		}
		
	    FileManager fm = FileManager.getInstance();
	    //删除当前业务日期当前批次的数据
    	BatTableDao batDao = new BatTableDao();
    	batDao.deleteCurrentBatData(table, date, batchId);
	    //导入文件新数据
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("date",date);
    	map.put("batchId", batchId);
    	IResultMsg ret  = fm.importData(template, filepath, map);
    	long total = 0;
    	long  successCnt = 0;
    	long errorCnt = 0;
    	String errorId = UUIDGenerator.generate("");
    	String detailId = UUIDGenerator.generate("");
    	 //保存导入信息主表
	    SysUploadFileErrorDao errorDao = new SysUploadFileErrorDao();
	    //保存导入信息明细表
	    SysUploadFileErrorDetailDao errorDetailDao = new SysUploadFileErrorDetailDao();
	    if(ret instanceof BatchResult){
	    	BatchResult bret = (BatchResult)ret;			
        	//System.out.println(ret.getErrorMessage());
    	    System.out.println("total:"+bret.getLineCount()+"&successCount:"+bret.getSuccessCount());
    	    total = bret.getLineCount();
    	    successCnt = bret.getSuccessCount();
    	    errorCnt = total-successCnt;
    	    errorDao.insertSysUploadFileError(errorId, uploadId, total, successCnt, errorCnt);
    	    LinkedList<LineResultInfo> list = bret.getErrorMessageList();
		    LineResultInfo error = (LineResultInfo)list.get(0);
		    int column = error.getColumn();
		    long row = error.getIndex();
		    String msg = error.getMsg();
		    if(StringUtils.isNotBlank(msg)){
		    	int length = msg.length();
		    	if(length>512){
		    		length=512;
		    	}
		    	msg = msg.substring(0,length-1);
		    }else{
		    	msg = "";
		    }
		    errorDetailDao.insertSysUploadFileErrorDetail(detailId, errorId,row, column, msg);
	    }else if(ret instanceof DefaultResultMsg){
	    	DefaultResultMsg dret = (DefaultResultMsg)ret;
    		String msg = dret.getErrorMessage();
    		if(StringUtils.isNotBlank(msg)){
		    	int length = msg.length();
		    	if(length>512){
		    		length=512;
		    	}
		    	msg = msg.substring(0,length-1);
		    }else{
		    	msg = "";
		    }
    		errorDao.insertSysUploadFileError(errorId, uploadId, total, successCnt, errorCnt);
    		errorDetailDao.insertSysUploadFileErrorDetail(detailId, errorId,0, 0, msg);
	    }
	   
	    //更新sys_upload_file_mapping的导入次数,导入状态，当result_type='1'
	    SysUploadFileDao fileDao = new SysUploadFileDao();
	    if(ret.isSuccessful()){
	    	 fileDao.updateFileResultType(uploadId,"1");
	    	 mappingDao.updateSysUploadFileMapping(fileType);
	    }else{
	    	/*处理结果:0-未处理,1-全部成功,2-部分失败,3-全部失败*/
	    	if(errorCnt==total){
    			//全部失败
    			fileDao.updateFileResultType(uploadId,"3");
    		}else{
    			//部分成功
    			fileDao.updateFileResultType(uploadId,"2");
    		}
	    	
	    }
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
