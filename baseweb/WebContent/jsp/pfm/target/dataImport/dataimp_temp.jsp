<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据导入详细信息查看</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.content2 {width:100%;}
body {background: url(${_CONTEXT_PATH}/images/${SkinType}/bg_${SkinType}.gif) repeat;}
</style>
</head>
<body style="overflow: hidden;">
<n:page action='com.soule.crm.pub.dataimport.DataImportAction' initMethod="doInitDetail" />
<n:enums keys='err_code'/>
<table class='content2'>
<tr><td style="padding-left: 15px; padding-right: 5px; padding-top: 15px;">
<form id="myform" action="">
<fieldset class="detailBox" style="width: 700px;"><legend>错误记录列表</legend>
	<table class='params'>
		<tr>
			<td align="left" nowrap="nowrap" width="10%">总记录条数:</td>
			<td width="20%" align="left" id="totle"></td>
			<td align="left" nowrap="nowrap" width="10%">合格条数:</td>
			<td  width="20%" align="left" id="success"></td>
			<td align="left" nowrap="nowrap" width="10%">不合格条数:</td>
			<td  width="20%" align="left" id="error"></td>
		</tr>
	</table>
	<div id='custInfolist' style="overflow:auto;"></div>
</fieldset>
</form>
</td></tr>
</table>
</body>
<script type='text/javascript'>
var fileId = '${param.fileId}';
var uploadType = '${param.uploadFileType}';

$(function () {
	Utils.validateInit();
	
	//输出表格
	$("#custInfolist").ligerGrid({
		enumlist: _enum_params ,
		columns: [
		    { display: '处理结果', name: 'ERR_CODE', align: 'left',codetype:'err_code',isSort:true}
		    <s:iterator value="rows">
		    ,{ display: '<s:property value="TITLE_CNNAME"/>',isSort:true, width: caculateWidth('<s:property value="TITLE_CNNAME"/>'),name: '<s:property value="TITLE_NAME"/>',codetype:'', align: 'left' }
		    </s:iterator>
		],
		pageSize:10,
		sortName:'ERR_CODE',
		height:'350',
		heightDiff:-20,
		width:'700',
		onError: function(e) {
			Utils.toIndex(e);
		}
	});
	execute();
	ajaxRecord();
	
	addShadow();
});

function caculateWidth(val) {
	if (val) {
		return val.length * 20;
	}
	return 120;
}

function execute() {
	if (!$('#myform').valid()){
		return;
	}
	//单记录数据
	var mdata = [];
	mdata.push({"name":'queryIn.fileId','value':fileId});
	mdata.push({"name":'queryIn.fileType','value':uploadType});
	var params = {
			dataAction:'server',
			dataType:'server', 
			url: '${_CONTEXT_PATH}/pub/data-import!queryFileDetail.action',
			newPage:1,
			parms:mdata,
			onError: function(e) {
				Utils.toIndex(e);
			}
	}; 
	var gridManager = $("#custInfolist").ligerGetGridManager();
	gridManager.setOptions(params);
	gridManager.loadData();
}

function ajaxRecord() {
	var url = '${_CONTEXT_PATH}/pub/data-import!queryAjaxRecord.action';
	$.ajax({
		url: url,
		type: 'POST',
		data: {'queryIn.fileId':fileId,'queryIn.fileType':uploadType},
		dataType: 'json',
		async: false,
		success: function(result) {
			$('#totle').append(result.recordTotle);
			$('#success').append(result.recordSuccess);
			$('#error').append(result.recordError);
		},
		error: function(result) {
			Utils.toIndex(result);
		}
	});
}


</script>
</html>