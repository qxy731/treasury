package com.soule.data.dao;

import com.soule.comm.db.DatabaseHelper;

public class SysUploadFileErrorDetailDao{
	
	public int insertSysUploadFileErrorDetail(String detailId,String errorId,long errorRow,int errorColumn,String errorMessage){
		DatabaseHelper helper = new DatabaseHelper();
		StringBuffer sql = new StringBuffer("insert into sys_upload_file_error_detail(");
		sql.append("DETAIL_ID,ERROR_ID,ERROR_ROW,ERROR_COLUMN,ERROR_MESSAGE");
		sql.append(") values(");
		sql.append("'").append(detailId).append("',");
		sql.append("'").append(errorId).append("',");
		sql.append(errorRow).append(",");
		sql.append(errorColumn).append(",");
		sql.append("'").append(errorMessage).append("')");
		System.out.println(sql.toString());
		return helper.executeUpdate(sql.toString());
	}

}
