<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>进程监控</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.inbox ,.outbox {padding: 5px;margin: 2px;border: 1px solid #ccc;}
.params td {background-color: #E2EAFF; padding:2px 2px 2px 10px;}
#reset {width: 80px;}
</style>
</head>
<body>
<n:page action='com.soule.crm.monitor.daily.proc.ProcAction' />

<table class='content'>
<tr><td>
	<table class="s1-button">
			<tr>
				<td>
					<input id='list' name='list' type='button' value='刷&nbsp;&nbsp;新' class='l-button'/>
				</td>
			</tr>
	</table>
</td></tr>
<tr><td>
	<fieldset class="outbox"><legend>进程列表</legend>
		<div id='detaillist'></div>
</fieldset>
</td></tr>
</table>
</body>
<script type='text/javascript'>
$(function () {
	Utils.validateInit();
	$('#reset').bind('click', doClear);
	$('#list').bind('click', execute);

	//输出表格
	$("#detaillist").ligerGrid({
		columns: [
			{ display: '句柄', name: 'psId', align: 'center' ,width: '10%'},
			{ display: '执行名称', name: 'execName', align: 'center' ,width: '25%'},
			{ display: '启动时间', name: 'startTime', align: 'center' ,width: '20%'},
			{ display: '占用内存', name: 'memUsed', align: 'center' ,width: '10%', render: renderMem},
			{ display: '执行时间', name: 'timeUsed', align: 'center' ,width: '20%', render: renderTime},
			{ display: '用户', name: 'userId', align: 'center' ,width: '10%'}
		],
		pageSize:10,
		sortName: 'psId',
		usePager : false,
		width: '100%',
		onError: function() {
			$.dialogBox.alert("查询数据失败");
		}
	});
	execute();
});
function renderMem(row,t) {
	var x = row.memUsed;
	x = x /(1024*1024);
	var html = "";
	if (x >= 1) {
		html = x.toFixed(1) + "M";
	}
	else {
		var y = row.memUsed /1024;
		if (y >= 1) {
			html = y.toFixed(1) + "K";
		}
		else {
			html = row.memUsed;
		}
	}
	return html;
}
function renderTime(row,t) {
	var x = row.timeUsed/1000;
	x = x /(3600);
	var html = "";
	if (x >= 1) {
		html = x.toFixed(1) + "h";
	}
	else {
		var y = row.timeUsed /1000/60;
		if (y >= 1) {
			html = y.toFixed(1) + "m";
		}
		else {
			html = (row.timeUsed /1000).toFixed(1) + "s";
		}
	}
	return html;
}
function doClear() {
	$(".inbox input[type='text']").each(function(i,item){
		item.value ='';
	});
}

function execute() {
	var params = {
			dataAction:'server',
			dataType:'server',
			url: "${_CONTEXT_PATH}/monitor/proc!list.action",
			newPage:1,
			parms:''
		};
	var gridManager = $("#detaillist").ligerGetGridManager(); 
	gridManager.setOptions(params);
	gridManager.loadData();
}

</script>
</html>