<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审计日志</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.div_bottom {margin-top: 10px}
/* body {
	margin-top: 10px;margin-right: 10px
} */
</style>
</head>
<body>
<n:enums keys="log_biz_type,log_func_type,log_exec_result"/>
<table width="100%">
<tr><td>
<fieldset class="queryBox"><legend>日志明细</legend>
<table class='params'>
	<tr>
		<td>顺序号</td><td><input type='text' id='id' readonly="readonly"/></td>
		<td>执行人编码</td><td><input type='text' id='operStaffid' readonly="readonly"/></td>
	</tr>
	<tr>
		<td>执行人角色</td><td><input type='text' id='roleId' readonly="readonly"/></td>
		<td>执行人角色名</td><td><input type='text' id='roleName' readonly="readonly"/></td>
	</tr>
	<tr>
		<td>代理人编码</td><td><input type='text' id='devoStaffid' readonly="readonly"/></td>
		<td>代理人名称</td><td><input type='text' id='devoStaffid' readonly="readonly"/></td>
	</tr>
	<tr>
		<td>操作编码</td><td><input type='text' id='operCode' readonly="readonly"/></td>
		<td>操作名称</td><td><input type='text' id='operName' readonly="readonly"/></td>
	</tr>
	<tr>
		<td>客户端IP</td><td><input type='text' id='ipAddr' readonly="readonly"/></td>
		<td>业务类型</td><td><input type='text' id='bizType' readonly="readonly"/></td>
	</tr>
	<tr>
		<td>操作类型</td><td><input type='text' id='funcType' readonly="readonly"/></td>
		<td>执行结果</td><td><input type='text' id='execResult' readonly="readonly"/></td>
	</tr>
	<tr>
		<td>执行时间</td><td><input type='text' id='execTime' readonly="readonly"/></td>
		<td></td><td></td>
	</tr>
	<tr>
		<td>详细信息</td><td colspan="3"><textarea id="logDetail" name="logDetail" cols="100"
					rows="3" style="width: 420px;" readonly="readonly"></textarea></td>
	</tr>
</table>
</fieldset>
</td></tr>
</table>
<div align="center" class="div_bottom">
    <table>
        <tr>
           <td><input id='cancel' name='cancel' type="button" value="关&nbsp;闭" class="l-button"> </td>
        </tr>
    </table>
</div>
</body>
<script type='text/javascript'>
$(function () {
	$('#cancel').bind('click', doClear);
	var mdata = $.dialogBox.opener.getLogData();
	$('.params input').each(function(i,item){
		$(item).val(mdata[item.id]);
	});
	$('#logDetail').val(mdata.logDetail);
	$('#bizType').val(Utils.getCodeValue('log_biz_type',mdata.bizType));
	$('#funcType').val(Utils.getCodeValue('log_func_type',mdata.funcType));
	$('#execResult').val(Utils.getCodeValue('log_exec_result',mdata.execResult));
});

function doClear() {
	$.dialogBox.close();
}
</script>
</html>