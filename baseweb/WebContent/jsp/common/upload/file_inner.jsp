<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
body {background: url(${_CONTEXT_PATH}/images/${SkinType}/bg_${SkinType}.gif) repeat;}
.fileframe {margin:0;padding:0; overflow: hidden;border:none;}
</style>
</head>
<body class='fileframe' onload="initload()">
<form id='fileform' action='${_CONTEXT_PATH}/sys/file-transfer!upload.action' method='post' enctype='multipart/form-data' >
	<input type='file' id='onefile' name='onefile' onchange="uploadFile()" ></input>
	<input type='hidden' id='uploadId' name='uploadIn.file.uploadId'></input>
	<input type='hidden' id='filter' name='filter'></input>
</form>
</body>
<script>
function initload() {
	var uid = '${param.uid}';
	if (!uid) {
		uid = '${uploadIn.file.uploadId}';
	}
	document.getElementById('uploadId').value = uid;
	var filter = '${param.filter}';
	if (!filter) {
		filter = '${filter}';
	}
	document.getElementById('filter').value = filter;
	addUploadedFileToParent();
}
function uploadFile() {
	var filter = document.getElementById('filter').value;
	var dosubmit = false;
	if (filter != '*') {
		var fileName = document.getElementById('onefile').value;
		var i = fileName.lastIndexOf('.');
		if (i >= 0){
			var suffix = fileName.substring(i+1);
			var x = filter.indexOf(suffix);
			if (x >= 0) {
				dosubmit = true;
			}
			else {
				alert("本操作允许上传文件类型["+filter+"]");
			}
		}
	}
	else {
		dosubmit = true;
	}
	if (dosubmit) {
		try {
			document.getElementById('onefile').enabled = 'false';
			document.getElementById('fileform').submit();
		}
		finally {
			
		}
	}
}

function addUploadedFileToParent() {
	var result ={};
	result.retCode = '${retCode}';
	result.retMsg = '${retMsg}';
	if (result.retCode && result.retCode.substr(0,1) == 'I') {
		var file = {};
		file.uploadId = '${uploadIn.file.uploadId}';
		file.fileId = '${uploadIn.file.fileId}';
		file.fileName = '${uploadIn.file.fileName}';
		file.fileSize = '${uploadIn.file.fileSize}';
		if (file.fileId) {
			parent.addFile(file);
		}
	}
	else if (result.retCode && result.retCode.substr(0,1) == 'E'){
		alert(result.retMsg);
	}
}
</script>
</html>