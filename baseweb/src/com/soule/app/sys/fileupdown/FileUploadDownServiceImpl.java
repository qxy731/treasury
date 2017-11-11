package com.soule.app.sys.fileupdown;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.app.table.SysUploadFilePo;
import com.soule.app.table.SysUploadFileQueuePo;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.base.service.keygen.IKeyGenerator;
import com.soule.comm.ParamConstants;
import com.soule.comm.enu.YesNoFlag;
import com.soule.comm.tools.AppUtils;

@Service
public class FileUploadDownServiceImpl implements IFileUploadDownService {
	
	private final static Log logger = LogFactory.getLog(FileUploadDownServiceImpl.class);
	
    @Autowired
    private IDefaultService sDefault;
    @Autowired
    private IKeyGenerator keyg;
    /**
     * 上传文件
     */
    public FileUploadDownOut uploadFile(List<File> files,List<String> fileName,List<String> fileType,List<String> importType,Map<String,Object> params) throws ServiceException {
    	FileUploadDownOut out = new FileUploadDownOut();
        if(files==null||files.isEmpty()){
        	AppUtils.setResult(out, MsgConstants.E0009);
        }
        String uploadNo = String.valueOf(keyg.getSeqence("UPLOAD_NO"));
        params.put("uploadNo", uploadNo);
        if(files!=null){
			 for(int i=0;i<files.size();i++){
				 File file = files.get(i);
				 params.put("fileName", fileName.get(i));
				 params.put("fileType", fileType.get(i));
				 params.put("importType", importType.get(i));
				 uploadOneFile(file,params);
			 }
		 }
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }
    
    private FileUploadDownOut uploadOneFile(File file,Map<String,Object> params) throws ServiceException{
    	FileUploadDownOut out = new FileUploadDownOut();
    	if (file!=null&&!file.exists()) {
            AppUtils.setResult(out, MsgConstants.E0009);
        }
        String uploadId = keyg.getUUIDKey();
        Date date = new Date();
    	String newFilePath = getFileRootPath(date);
    	SysUploadFilePo po = new SysUploadFilePo();
		po.setUploadId(uploadId);
		po.setFileId(getFileIdForBatchIdByFileType(params));//处理同一个fileId是否多个batchId
		po.setFileName(params.get("fileName").toString());
		po.setFileType(params.get("fileType").toString());
		po.setFileSize((int)file.length()); 
		po.setFilePath(newFilePath);
		po.setDelFlag(YesNoFlag.NO.getValue());
		po.setDownNum(0);
		po.setBusinessDate(params.get("businessDate").toString());
		po.setResultType("0");
		AppUtils.initRecordStatus(po);
		po.setCreateTime(date);
		po.setUploadNo(params.get("uploadNo").toString());
		po.setBatchId(String.valueOf(keyg.getSeqence("BATCH_ID")));
		po.setImportType(params.get("importType").toString());
        SysUploadFileQueuePo fileQueuePo = new SysUploadFileQueuePo();
        fileQueuePo.setQueueId(keyg.getUUIDKey());
        fileQueuePo.setUploadId(uploadId);
        try {
            sDefault.getIbatisMediator().save("upload.saveSysUploadFile", po);
            sDefault.getIbatisMediator().save("uploadQueue.saveSysUploadFileQueue", fileQueuePo);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002);
        }
        try {
            FileUtils.copyFile(file,new File(newFilePath,uploadId));
        } catch (IOException e) {
            logger.error("SERVICE",e);
            AppUtils.setResult(out, MsgConstants.E0010);
        }
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }
    
    /**
     * 取得上传文件保存根目录
     * @param createTime
     * @return
     */
    private String getFileRootPath(Date createTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String subDir = sdf.format(createTime);
        return ParamConstants.UPLOAD_ROOT + "/" + subDir;
    }
    
    private String getFileIdForBatchIdByFileType(Map<String,Object> params) {
    	String fileId = null;
    	try {
        	Map<String,Object> map = new HashMap<String,Object>();
        	map.put("fileType",params.get("fileType").toString());
        	map.put("delFlag","0");
        	map.put("businessDate",params.get("businessDate").toString());
        	String importType = params.get("importType").toString();
        	if("2".equals(importType)){
        		@SuppressWarnings("unchecked")
				List<SysUploadFilePo> list = sDefault.getIbatisMediator().find("upload.getSysUploadFile", map);
        		if(list!=null&&!list.isEmpty()){
        			SysUploadFilePo po = list.get(0);
        			fileId = po.getFileId();
        		}
        	}
    		if(StringUtils.isBlank(fileId)){
        		fileId = keyg.getUUIDKey();
        	}
		} catch (DbAccessException e) {
			logger.error("DB",e);
		} catch (Exception e1) {
			logger.info(e1);
		}
    	return fileId;
    }
}