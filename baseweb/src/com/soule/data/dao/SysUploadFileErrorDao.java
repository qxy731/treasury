package com.soule.data.dao;

import com.soule.comm.db.DatabaseHelper;

public class SysUploadFileErrorDao {
	
	
	public int insertSysUploadFileError(String errorId,String uploadId,long total,long success,long failure){
		DatabaseHelper helper = new DatabaseHelper();
		StringBuffer sql = new StringBuffer("insert into sys_upload_file_error(");
		sql.append("ERROR_ID,UPLOAD_ID,TOTAL_NUMBER,SUCCESS_NUMBER,FAILURE_NUMBER");
		sql.append(") values(");
		sql.append("'").append(errorId).append("',");
		sql.append("'").append(uploadId).append("',");
		sql.append(total).append(",");
		sql.append(success).append(",");
		sql.append(failure).append(")");
		System.out.println(sql.toString());
		return helper.executeUpdate(sql.toString());
	}

}
