<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sql监控</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.inbox ,.outbox {padding: 5px;margin: 2px;border: 1px solid #ccc;}
.params td {background-color: #E2EAFF; padding:2px 2px 2px 10px;}
.l-button {display:inline;}
#reset {width: 80px;}
</style>
</head>
<body>
<n:page action='com.soule.crm.monitor.realtime.sql.SqlAction' />

<table class='content'>
<tr><td>
<form id="myform" action="${_CONTEXT_PATH}/monitor/sql!list.action">
<fieldset class="queryBox"><legend>查询条件</legend>
<table class='params'>
	<tr>
		<td>本次统计开始时间</td><td><s:date name='startTime' format='yyyy-MM-dd hh:mm:ss'></s:date></td>
		<td></td><td></td>
		<td></td><td></td>
	</tr>
	<tr>
		<td>语句ID</td><td><input type='text' id='sqlId' name='sqlId' /></td>
		<td></td><td></td>
		<td></td><td></td>
	</tr>
</table>
</fieldset>
</form>
</td></tr>
<tr><td>
	<table class="s1-button">
			<tr>
				<td>
					<input id='list' name='list' type='button' value='查&nbsp;&nbsp;询' class='l-button'/>
				</td>
				<td>
					<input id='resetStats' name='resetStats' type='button' value='重新统计' class='l-button'/>
				</td>
			</tr>
	</table>
</td></tr>
<tr><td>
	<fieldset class="outbox"><legend>SQL语句列表</legend>
		<div id='detaillist'></div>
</fieldset>
</td></tr>
</table>
</body>
<script type='text/javascript'>
$(function () {
	Utils.validateInit();
	$('#resetStats').bind('click', doResetStats);
	$('#list').bind('click', execute);

	//输出表格
	$("#detaillist").ligerGrid({
		columns: [
			{ display: '语句ID', name: 'sqlId', align: 'left' },
			{ display: '最长时间(s)', name: 'useMaxTime', align: 'center' },
			{ display: '最短时间(s)', name: 'useMinTime', align: 'center' },
			{ display: '执行次数(s)', name: 'execTimes', align: 'center' },
			{ display: '执行总时间(s)', name: 'execAllTime', align: 'center' },
			//{ display: '最后一次使用时间', name: 'lastUseTime', align: 'center' },
			{ display: '最后一次开始时间', name: 'lastStartTime', align: 'center' },
			{ display: '平均时间(s)', name: 'averageTime', align: 'center' }
		],
		pageSize:10,
		sortName: 'sqlId',
		width: '100%',
		onError: function() {
			$.dialogBox.alert("查询数据失败");
		},
		onDblClickRow:function(row,id,context){
			showDetail(row,id,context);
		}
	});
});

function showDetail(row,id,context) {
	if (row) {
		var url = "${_CONTEXT_PATH}/jsp/monitor/realtime/sql/sql_detail.jsp?sqlId=" + row.sqlId;
		$.dialogBox.openDialog(url,{title:'SQL语句详情',width:1000,height:450});
	}
	else {
		$.dialogBox.alert("请先选择需要查看的记录");
	}
}
function doResetStats() {
	$.dialogBox.confirm("以往统计数据将丢失,确认重新开始统计?",function(){
		Utils.ajaxSubmit("${_CONTEXT_PATH}/monitor/sql!resetStats.action");
	});
}

function execute() {
	if (!$('#myform').valid()){
		return;
	}
	var pp = Utils.convertParam('listIn','myform');
	var params = {
		dataAction:'server',
		dataType:'server',
		url: "${_CONTEXT_PATH}/monitor/sql!list.action",
		newPage:1,
		parms:pp
	};
	var gridManager = $("#detaillist").ligerGetGridManager(); 
	gridManager.setOptions(params);
	gridManager.loadData();
}

</script>
</html>