<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传下载</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.file{display:inline; border:1px solid #999; margin:5px 0 0 5px;padding:2px;float:left;overflow:hidden;}
#fileframe{width:100%;height:30px;margin:0 10px 10px 10px;padding:0;border:none;}
#files {margin:10px; border:1px solid #888;height: 100px;overflow:auto;}
.delarea,.downarea{width:18px;height:18px;float:right;cursor:pointer;}
.delarea { background:#fff url('../../../images/delete.gif') no-repeat right center;}
.downarea { background:#fff url('../../../images/down.gif') no-repeat right center;}

</style>
</head>
<body style='overflow: hidden; border:none;'>
<n:page action='com.soule.app.sys.filetransfer.FileTransferAction' initMethod="initUploadId"/>
<div>已上传的文件</div>
<div id="files" ></div>
<iframe id='fileframe' src='file_inner.jsp?uid=${uploadId}&filter=${filter}' frameborder="0"></iframe>
<div id='msg'></div>

</body>
<script type='text/javascript'>
var params = {
		'canDelete':false,
		'canDownload':true,
		'fileNum':1
	};

/**
 * 
 */
function initMethod(filenum,canDelete,canDownload) {
	params.fileNum = filenum;
	if (canDelete) {
		params.canDelete = (canDelete == 'true');
	}
	if (canDelete) {
		params.canDownload = (canDownload == 'true');
	}
	try {
		params.msg = '文件大小<5M,允许文件类型[${filter}],可上传文件数' + filenum,
		$('#msg').html(params.msg);
		var fileMax = params.fileNum;
		if (fileMax == 0) {
			$('#fileframe').hide();
		}
		var files = ${filesStr};
		for (var i = 0 ; i < files.length; i++) {
			addFile(files[i]);
		}
	}catch(e){
	}
}
function getUploadId(){
	return '${uploadId}';
}
function getUploadFiles() {
	var fileareas = $('.file').toArray();
	var files = [];
	for (var x = 0 ; x < fileareas.length; x ++) {
		var file = {};
		file.fileId = $(fileareas[x]).attr('fid');
		file.fileName = $(fileareas[x]).attr('fn');
		files.push(file);
	}
	return files;
}
function addFile(fileObj) {
	var filestr = "<span class='file'" +
		" fid='" + fileObj.fileId +"'" +
		" uid='" + fileObj.uploadId +"'" +
		" fn='" + fileObj.fileName +"'";
	filestr = filestr + ">" + fileObj.fileName;
	if (params.canDelete) {
		filestr += "<span class='delarea' onclick='deleteFile(this)'/>";
	}
	if (params.canDownload) {
		filestr += "<span class='downarea' onclick='downloadFile(this)'/>";
	}
	filestr += "</span>";

	$('#files').append(filestr);
	var len = $('.file').toArray().length;
	var fileMax = params.fileNum;
	if (len >= fileMax) {
		$('#fileframe').hide();
		$('#msg').html("已达文件最大数"+fileMax+"!");
	}
	else{
		$('#msg').html(params.msg);
	}
}
function deleteFile(obj) {
	var data = {};
	data.uploadId = $(obj).parent().attr('uid');
	data.fileId = $(obj).parent().attr('fid');
	var url = "${_CONTEXT_PATH}/FileTransfer!delete.action";
	var mdata = Utils.convertObjectData('deleteIn.delete',data);
	Utils.ajaxSubmit(url,mdata,function(result){
		$(obj).parent().remove();
		var len = $('.file').toArray().length;
		var fileMax = params.fileNum;
		if (len < fileMax) {
			$('#fileframe').show();
			$('#msg').html(params.msg);
		}
	});
}
function downloadFile(obj) {
	var data = {};
	data.uploadId = $(obj).parent().attr('uid');
	data.fileId = $(obj).parent().attr('fid');
	var mdata = Utils.convertObjectData('downloadIn.file',data);
	var url = "${_CONTEXT_PATH}/FileTransfer!download.action?" + $.param(mdata);
	//alert (url);
	document.location = url;
}
</script>
</html>