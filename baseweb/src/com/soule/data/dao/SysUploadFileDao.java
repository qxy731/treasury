package com.soule.data.dao;

import java.util.List;

import com.soule.comm.db.DatabaseHelper;
import com.soule.data.bean.SysUploadFile;

public class SysUploadFileDao {
	
	public List<SysUploadFile> queryUploadFile(){
		DatabaseHelper helper = new DatabaseHelper();
		StringBuffer sql= new StringBuffer("select ");
		sql.append("A.UPLOAD_ID as uploadId,");
		sql.append("A.FILE_ID as fileId,");
		sql.append("A.FILE_NAME as fileName,");
		sql.append("A.FILE_TYPE as fileType,");
		sql.append("ROUND(A.FILE_SIZE/1024,1) as fileSize,");
		sql.append("A.FILE_PATH as filePath,");
		sql.append("A.DEL_FLAG as delFlag,");
		sql.append("A.DOWN_NUM as downNum,");
		sql.append("A.BUSINESS_DATE as businessDate,");
		//处理结果:0-未处理,1-全部成功,2-部分失败,3-全部失败
		sql.append("A.RESULT_TYPE as resultType,");
		sql.append("A.CREATE_USER as createUser,");
		sql.append("A.CREATE_TIME as createTime,");
		sql.append("A.CREATE_ORG as createOrg,");
		sql.append("A.UPLOAD_NO as uploadNo,");
		sql.append("A.BATCH_ID as batchId,");
		sql.append("A.IMPORT_TYPE as importType");
		sql.append(" from sys_upload_file A ");
		sql.append(" where RESULT_TYPE='0' ");
		sql.append(" order by A.CREATE_TIME");
		System.out.println(sql.toString());
		return helper.getRecords(sql.toString(),SysUploadFile.class);
	}
	
	public SysUploadFile getOneUploadFile(String uploadId){
		DatabaseHelper helper = new DatabaseHelper();
		StringBuffer sql= new StringBuffer("select ");
		sql.append("A.UPLOAD_ID as uploadId,");
		sql.append("A.FILE_ID as fileId,");
		sql.append("A.FILE_NAME as fileName,");
		sql.append("A.FILE_TYPE as fileType,");
		sql.append("ROUND(A.FILE_SIZE/1024,1) as fileSize,");
		sql.append("A.FILE_PATH as filePath,");
		sql.append("A.DEL_FLAG as delFlag,");
		sql.append("A.DOWN_NUM as downNum,");
		sql.append("A.BUSINESS_DATE as businessDate,");
		/*处理结果:0-未处理,1-全部成功,2-部分失败,3-全部失败*/
		sql.append("A.RESULT_TYPE as resultType,");
		sql.append("A.CREATE_USER as createUser,");
		sql.append("A.CREATE_TIME as createTime,");
		sql.append("A.CREATE_ORG as createOrg,");
		sql.append("A.UPLOAD_NO as uploadNo,");
		sql.append("A.BATCH_ID as batchId,");
		sql.append("A.IMPORT_TYPE as importType");
		sql.append(" from sys_upload_file A ");
		sql.append(" where A.UPLOAD_ID ='").append(uploadId).append("'");
		System.out.println(sql.toString());
		return helper.getOneRecord(sql.toString(),SysUploadFile.class);
	}
	
	public int updateFileResultType(String uploadId,String resultType){
		DatabaseHelper helper = new DatabaseHelper();
		StringBuffer sql= new StringBuffer("update sys_upload_file set");
		/*处理结果:0-未处理,1-全部成功,2-部分失败,3-全部失败*/
		sql.append(" RESULT_TYPE ='").append(resultType).append("'");
		sql.append(" where UPLOAD_ID ='").append(uploadId).append("'");
		System.out.println(sql.toString());
		return helper.executeUpdate(sql.toString());
	}

}
