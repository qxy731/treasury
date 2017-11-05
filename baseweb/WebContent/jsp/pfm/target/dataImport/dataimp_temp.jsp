<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>源数据文件导入错误信息查看</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
</style>
</head>
<body style="overflow: hidden;">
<n:page action='com.soule.crm.pub.dataimport.DataImportAction'/>
<n:enums keys='err_code'/>
<table width="100%">
<tr><td>
<fieldset class="outbox"><legend>错误记录列表</legend>
	<table class='params'>
		<tr>
			<td align="left" nowrap="nowrap" width="10%">文件编号:</td>
			<td width="25%"  align="left" id="uploadId">${errorDetailVo.uploadId}</td>
			<td align="left" nowrap="nowrap" width="10%">文件名称:</td>
			<td width="55%" colspan="3" align="left" id="fileName">${errorDetailVo.fileName}</td>
		</tr>
		<tr>
			<td align="left" nowrap="nowrap" width="10%">总记录条数:</td>
			<td width="25%" align="left" id="totalNumber">${errorDetailVo.totalNumber}</td>
			<td align="left" nowrap="nowrap" width="10%">合格条数:</td>
			<td  width="25%" align="left" id="successNumber">${errorDetailVo.successNumber}</td>
			<td align="left" nowrap="nowrap" width="10%">不合格条数:</td>
			<td  width="20%" align="left" id="failureNumber">${errorDetailVo.failureNumber}</td>
		</tr>
	</table>
	<div id='custInfolist' style="overflow:auto;"></div>
</fieldset>
</td></tr>
</table>
</body>
<script type='text/javascript'>
$(function () {
	var uploadId =  $.dialogBox.opener.getUploadId();
	var mdata = [];
	mdata.push({"name":'errorDetailIn.uploadId','value':uploadId});
	//输出表格
	$("#custInfolist").ligerGrid({
		dataAction:'server',
		dataType:'server',
		columns: [
		    { display: '行数', name: 'errorRow', align: 'center' ,width:80},
		    { display: '列数', name: 'errorColumn',align: 'center',width:80},
		    { display: '错误描述', name: 'errorMessage', align: 'left',width:700}
		],
		url:'${_CONTEXT_PATH}/pub/data-import!queryFileDetail.action',
		urlParms:mdata,
		pageSize:20,
		height:'450',
		width:'100%',
		onSuccess:function(result){
			var errorDetailVo = result.errorDetailVo;
			$("#uploadId").html(errorDetailVo.uploadId);
			$("#fileName").html(errorDetailVo.fileName);
			$("#totalNumber").html(errorDetailVo.totalNumber);
			$("#successNumber").html(errorDetailVo.successNumber);
			$("#failureNumber").html(errorDetailVo.failureNumber);
		},
		onError: function(e) {
			Utils.toIndex(e);
		}
	});
	execute();
});

function execute() {
	var uploadId =  $.dialogBox.opener.getUploadId();
	var mdata = [];
	mdata.push({"name":'errorDetailIn.uploadId','value':uploadId});
	var params = {
			newPage:1,
			parms:mdata
	};
	var gridManager = $("#custInfolist").ligerGetGridManager();
	gridManager.setOptions(params);
	gridManager.loadData();
}
</script>
</html>