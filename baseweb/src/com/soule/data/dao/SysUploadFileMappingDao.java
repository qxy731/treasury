package com.soule.data.dao;

import com.soule.comm.db.DatabaseHelper;
import com.soule.data.bean.SysUploadFileMapping;

public class SysUploadFileMappingDao {
	
	public int updateSysUploadFileMapping(String fileType){
		DatabaseHelper helper = new DatabaseHelper();
		StringBuffer sql= new StringBuffer(" update sys_upload_file_mapping");
		/*所有文件到达状态：1-未完成,2-已完成*/
		sql.append(" set IMPORT_NUM = IMPORT_NUM+1,IMPORT_STATUS='2',");
		sql.append(" LAST_UPD_TIME = CURRENT_TIMESTAMP");
		sql.append(" where FILE_TYPE = '").append(fileType).append("'");
		System.out.println(sql.toString());
		return helper.executeUpdate(sql.toString());
	}
	
	public SysUploadFileMapping getOneSysUploadFileMapping(String fileType){
		DatabaseHelper helper = new DatabaseHelper();
		StringBuffer sql= new StringBuffer(" select ");
		sql.append(" FILE_PK as filePk,");
		sql.append(" FILE_SOURCE as fileSource,");
		sql.append(" FILE_TYPE as fileType,");
		sql.append(" FILE_NAME as fileName,");
		sql.append(" FILE_FREQ as fileFreq,");
		sql.append(" FILE_TEMPLATE as fileTemplate,");
		sql.append(" FILE_TABLE as fileTable,");
		sql.append(" FILE_NUM as fileNum,");
		sql.append(" IMPORT_NUM as importNum,");
		sql.append(" IMPORT_STATUS as importStatus");
		sql.append(" from sys_upload_file_mapping");
		sql.append(" where FILE_TYPE = '").append(fileType).append("'");
		System.out.println(sql.toString());
		return helper.getOneRecord(sql.toString(), SysUploadFileMapping.class);
	}

}
