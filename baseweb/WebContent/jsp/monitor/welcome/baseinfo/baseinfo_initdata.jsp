<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>基本信息展示</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.inbox ,.outbox {padding: 5px;margin: 2px;border: 1px solid #ccc;}
.params td {background-color: #E2EAFF; padding:2px 2px 2px 10px; width:120px;}
.params {border-collapse: separate; border-spacing: 1px;}
.content {width: 800px;margin:30px;}
.params tbody tr .tdl {background-color: #E2EAdF;}
#reset {width: 80px;}
</style>
</head>
<body>
<n:page action='com.soule.crm.monitor.welcome.baseinfo.BaseinfoAction' />

<table class='content'>
<tr><td>
	<fieldset class="outbox">
		<table class='params'>
			<tr>
				<td class='tdl'>机器名称</td><td>${out.app.machineName}</td>
				<td class='tdl'>IP地址</td><td>${out.app.machineIp}</td>
			</tr>
			<tr>
				<td class='tdl'>应用服务器类型</td><td>${out.app.appMachineType}</td>
				<td class='tdl'>服务监听端口</td><td>${out.app.servicePort}</td>
			</tr>
		</table>
	</fieldset>
	<fieldset class="outbox">
		<table class='params'>
			<tr>
				<td class='tdl'>硬件架构</td><td>${out.hardware.hardwareAgre}</td>
				<td class='tdl'>CPU个数</td><td>${out.hardware.cpuCount}</td>
			</tr>
			<tr>
				<td class='tdl'>操作系统名称</td><td>${out.hardware.osName}</td>
				<td class='tdl'>操作系统版本</td><td>${out.hardware.osVersion}</td>
			</tr>
			<tr>
				<td class='tdl'>语言环境</td><td>${out.hardware.osLanguage}</td>
				<td class='tdl'>系统字符集</td><td>${out.hardware.osCharset}</td>
			</tr>
			<tr>
				<td class='tdl'>物理内存</td><td>${out.hardware.memory}</td>
				<td class='tdl'>可用内存</td><td>${out.hardware.freeMemory}</td>
			</tr>
		</table>
	</fieldset>
	<fieldset class="outbox">
	<table class='params'>
		<tr>
			<td class='tdl'>JVM名称</td><td>${out.jvm.jvmName}</td>
			<td class='tdl'>JVM版本</td><td>${out.jvm.jvmVersio}</td>
		</tr>
		<tr>
			<td class='tdl'>JVM厂商</td><td>${out.jvm.jvmVendor}</td>
			<td class='tdl'></td><td></td>
		</tr>
		<tr>
			<td class='tdl'>物理内存</td><td>${out.jvm.jvmMemory}</td>
			<td class='tdl'>可用内存</td><td>${out.jvm.jvmAvlMe}</td>
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
	$('#initData').bind('click', execute);

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
	var mdata = Utils.convertFormData('initDataIn','myform');

	var url = "${_CONTEXT_PATH}/monitor/baseinfo!initData.action";
	Utils.ajaxSubmit(url,mdata);
}

</script>
</html>