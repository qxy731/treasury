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
.params {border-collapse: separate; border-spacing: 1px;}
.params tbody tr .tdl {background-color: #E2EAdF;}
.params textarea {width: 96%;}
</style>
</head>
<body>
<n:page action='com.soule.crm.monitor.realtime.sql.SqlAction' initMethod="initDetail"/>
<table class='content'>
<tr><td>
	<fieldset class="outbox"><legend>SQL语句详情</legend>
<table class='params'>
	<tr>
		<td class='tdl'>语句ID</td><td>${out.detail.sqlId}</td>
		<td class='tdl'>最长时间</td><td>${out.detail.useMaxTime}</td>
		<td class='tdl'>最短时间</td><td>${out.detail.useMinTime}</td>
	</tr>
	<tr>
		<td class='tdl'>执行次数</td><td>${out.detail.execTimes}</td>
		<td class='tdl'>执行总时间</td><td>${out.detail.execAllTime}</td>
		<td class='tdl'>平均时间</td><td>${out.detail.averageTime}</td>
	</tr>
	<tr>
		<td class='tdl'>最后一次开始时间</td><td><s:date name='out.detail.lastStartTime' format='yyyy-MM-dd hh:mm:ss'></s:date></td>
		<td class='tdl'>最后一次使用时间</td><td>${out.detail.lastUseTime}</td>
		<td class='tdl'></td><td></td>
	</tr>
	<tr>
		<td class='tdl'>最快一次开始时间</td><td><s:date name='out.detail.startTimeFast' format='yyyy-MM-dd hh:mm:ss'></s:date></td>
		<td class='tdl'>最快一次使用时间</td><td>${out.detail.useMinTime}</td>
		<td class='tdl'></td><td></td>
	</tr>
	<tr>
		<td class='tdl'>最快一次参数</td><td colspan='5'><textarea id='faseparams' style="height:25px;">${out.detail.sqlParamsFast}</textarea></td>
	</tr>
	<tr>
		<td class='tdl'>最慢一次开始时间</td><td><s:date name='out.detail.startTimeSlow' format='yyyy-MM-dd hh:mm:ss'></s:date></td>
		<td class='tdl'>最慢一次使用时间</td><td>${out.detail.useMaxTime}</td>
		<td class='tdl'></td><td></td>
	</tr>
	<tr>
		<td class='tdl'>最慢一次参数</td><td colspan='5'><textarea id='faseparams' style="height:25px;">${out.detail.sqlParamsSlow}</textarea></td>
	</tr>
	<tr>
		<td class='tdl'>SQL语句</td><td colspan='5'><textarea id='sqlContent' style="height:180px;" >${out.detail.sqlContent}</textarea></td>
	</tr>
</table>
</fieldset>
</td></tr>
</table>
</body>
<script type='text/javascript'>
$(function () {
	Utils.validateInit();
	$('#reset').bind('click', doClear);
	$('#detail').bind('click', execute);

});
function doClear() {
	$(".inbox input[type='text']").each(function(i,item){
		item.value ='';
	});
}

function execute() {
	if (!$('#myform').valid()){
		return;
	}
	//单记录数据
	var mdata = Utils.convertFormData('detailIn','myform');

	var url = "${_CONTEXT_PATH}/Sql!detail.action";
	Utils.ajaxSubmit(url,mdata);
}

</script>
</html>