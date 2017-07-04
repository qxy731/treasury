<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线用户监控</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.inbox ,.outbox {padding: 5px;margin: 2px;border: 1px solid #ccc;}
.params td {background-color: #E2EAFF; padding:2px 2px 2px 10px;}
#list {display:inline;}
#reset {width: 80px;}
</style>
</head>
<body>
<n:page action='com.soule.crm.monitor.realtime.user.UserAction' />

<table class='content'>
<tr><td>
<form id="myform" action="${_CONTEXT_PATH}/User!list.action">
<fieldset class="queryBox"><legend>精确查询</legend>
<table class='params'>
	<tr>
		<td>用户ID</td><td><input type='text' id='userId' name='userId' /></td>
		<!--
		<td>登陆时间前</td><td><input type='text' id='minTime' name='minTime' /></td>
		<td>登陆时间后</td><td><input type='text' id='maxTime' name='maxTime' /></td>
		-->
	</tr>
</table>
</fieldset>
</form>
</td></tr>
<tr><td>
	<table class="s1-button">
			<tr>
				<td align="right">
					<input id='list' name='list' type='button' value='执&nbsp;&nbsp;行' class='l-button'/>
				</td>
				<td>
					<input id='reset' name='reset' type='button' value='重&nbsp;置' class='l-button'/>
				</td>
			</tr>
	</table>
</td></tr>
<tr><td>
	<fieldset class="outbox"><legend>在线用户</legend>
		<div id='detaillist'></div>
</fieldset>
</td></tr>
</table>
</body>
<script type='text/javascript'>
$(function () {
	Utils.validateInit();
	$('#minTime').ligerDateEditor({showTime:true,format:"yyyy-MM-dd hh:mm:ss"});
	$('#maxTime').ligerDateEditor({showTime:true,format:"yyyy-MM-dd hh:mm:ss"});
	$('#reset').bind('click', doClear);
	$('#list').bind('click', execute);

	//输出表格
	$("#detaillist").ligerGrid({
		columns: [
			{ display: '用户ID', name: 'userId', align: 'center' ,width:'18%'},
			{ display: '用户姓名', name: 'userName', align: 'center' ,width:'18%'},
			{ display: '登陆时间', name: 'logonTime', align: 'center' ,width:'18%'},
			{ display: '在线时间', name: 'onlineTime', align: 'center' ,width:'18%'},
			{ display: '登陆IP地址', name: 'ipAddress', align: 'center' ,width:'27%'}
		],
		pageSize:10,
		sortName: 'userId',
		width: '100%',
		onError: function() {
			$.dialogBox.alert("查询数据失败");
		}
	});
});
function doClear() {
	$("#minTime").val('');
	$("#maxTime").val('');
	$("#userId").val('');
}

function execute() {
	if (!$('#myform').valid()){
		return;
	}
	var pp = Utils.convertParam('listIn','myform');
	var params = {
		dataAction:'server',
		dataType:'server',
		url: "${_CONTEXT_PATH}/monitor/user!list.action",
		newPage:1,
		parms:pp
	};
	var gridManager = $("#detaillist").ligerGetGridManager(); 
	gridManager.setOptions(params);
	gridManager.loadData();
}

</script>
</html>