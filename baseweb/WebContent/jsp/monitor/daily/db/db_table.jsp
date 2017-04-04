<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据库监控</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.inbox ,.outbox {padding: 5px;margin: 2px;border: 1px solid #ccc;}
.params td {background-color: #E2EAFF; padding:2px 2px 2px 10px;}
#reset {width: 80px;}
</style>
</head>
<body>
<n:page action='com.soule.crm.monitor.daily.db.DbAction' />
<form id="myform" action="${_CONTEXT_PATH}/Db!table.action">
	<input type='hidden' id='tsId' name='tsId' value='${request.parameters.tsId[0]}'/></td>
</form>
<table class='content'>
<tr><td>
	<fieldset class="outbox"><legend>表空间</legend>
		<div id='tablelist'></div>
</fieldset>
</td></tr>
</table>
</body>
<script type='text/javascript'>
$(function () {
	Utils.validateInit();

	//输出表格
	$("#tablelist").ligerGrid({
		columns: [
			{ display: '表名', name: 'tableName',width:300 },
			{ display: '占用空间', name: 'used',render:renderSize },
			{ display: '表空间ID', name: 'tsId' },
			{ display: '对象类型', name: 'ttype' }
		],
		pageSize:10,
		sortName: 'tableName',
		width: '100%',
		usePager:false,
		onError: function() {
			$.dialogBox.alert("查询数据失败");
		}
	});
	execute();
});
function renderSize(row) {
	var size = row.used;
	x = size /(1024*1024);
	var html = "";
	if (x >= 1) {
		html = x.toFixed(1) + "M";
	}
	else {
		var y = size /1024;
		if (y >= 1) {
			html = y.toFixed(1) + "K";
		}
		else {
			html = size;
		}
	}
	return html;
}
function execute() {
	//单记录数据
	var mdata = Utils.convertParam('tableIn','myform');
	var params = {
			dataAction:'server',
			dataType:'server',
			url: "${_CONTEXT_PATH}/monitor/db!table.action",
			newPage:1,
			parms:mdata
		};
	var gridManager = $("#tablelist").ligerGetGridManager(); 
	gridManager.setOptions(params);
	gridManager.loadData();
}

</script>
</html>