package com.soule.data.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.soule.data.bean.SysUploadFile;

public class LoadFileDataManager {
	
	private static Logger log = Logger.getLogger(LoadFileDataManager.class);
	
	public static void loadData(){
		try{
			SysUploadFile sysUploadFilePo = null;
			while(true){
				//每次从队列去取一个文件
		        FileImportManager fqm = new FileImportManager();
		        sysUploadFilePo = fqm.getUploadFile();
		        if(sysUploadFilePo==null){
		        	break;
		        }
		        try{
			        Map<String,Object> params = new HashMap<String,Object>();
			        params.put("sysUploadFilePo", sysUploadFilePo);
			        fqm.importFileData(params);
			        log.info("Import File Data is success.");
		        }catch(Exception e){
		        	e.printStackTrace();
		        	log.info("Import File Data"+e.getMessage());
		        }
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	public static void main(String[] args) {
		
		LoadFileDataManager.loadData();
	}

}
