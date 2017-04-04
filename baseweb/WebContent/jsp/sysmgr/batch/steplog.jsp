<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="n" uri="/ncrm-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns:v="urn:schemas-microsoft-com:vml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>批处理日志</title>
<style>
</style>
<jsp:include page="/comm_debug.jsp"></jsp:include>
<link href="${_CONTEXT_PATH}/jwebui/structure/structure.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${_CONTEXT_PATH}/jwebui/structure/structure.js"></script>
</head>

<body>
	<n:page action='com.soule.app.sys.batch.BatchLogAction'/>
	<n:enums keys='bat_proc_status'/>

	<div id='loglist'></div>
	<form id="myform">
		<input type='hidden' name='batchId' value='${param.batchId}' />
		<input type='hidden' name='instId' value='${param.instId}' />
		<input type='hidden' name='stepId' value='${param.stepId}' />
	</form>
	
</body>

<script type="text/javascript">

	$(function() {
		$("#loglist").ligerGrid({
			enabledSort:false,
			enumlist: _enum_params ,
			columns: [
			//{ display: '业务日期', name: 'txDate'  ,align: 'left',width:100},
			//{ display: '批处理号', name: 'batchId' ,align:'left',width:100},
			{ display: '步骤ID', name: 'stepId',width:50},
			{ display: '执行时间', name: 'exeTime',width:160},
			{ display: '执行结果', name: 'retCode',width:100 ,codetype:'bat_proc_status'},
			{ display: '执行信息', name: 'retMsg',width:280}
			],
			pageSize:20,
			height:'100%',
			heightDiff:-18,
			onError: function(e) {
				Utils.toIndex(e);
			}
		});
		queryData();
	});
	function queryData() {
		var url = "${_CONTEXT_PATH}/sys/batch-log!querylog.action";
		var pp = Utils.convertParam('logIn','myform');
		var params = {
			dataAction:'server',
			dataType:'server',
			url: url,
			newPage:1,
			parms:pp
		};
		var gridManager = $("#loglist").ligerGetGridManager(); 
		gridManager.setOptions(params);
		gridManager.loadData();
	}
</script>
</html>
