<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门下拉框</title>
<jsp:include page="/comm.jsp"></jsp:include>
<script src="${_CONTEXT_PATH}/jwebui/ftext/filtertext.js"></script>
<style type="text/css">
.inbox ,.outbox {padding: 5px;margin: 2px;border: 1px solid #ccc;}
.l-box-select {width: 149px;}
#reset {width: 80px;}
</style>
</head>
<body>
<n:page action='com.soule.crm.demo.comp.dropdown.DropdownAction' />

<table class='content'>
<tr><td>
<form id="myform" action="${_CONTEXT_PATH}/Dropdown!list.action">
<fieldset class="queryBox"><legend>输入条件</legend>
<table class='params'>
	<tr>
		<td>简写字母或中文</td><td><input id="unitId" /></td>
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
					<input id='list' name='list' type='button' value='执&nbsp;&nbsp;行' class='nbutton'/>
				</td>
				<td>
					<input id='reset' name='reset' type='button' value='重&nbsp;&nbsp;置' class='nbutton'/>
				</td>
			</tr>
	</table>
</td></tr>
<tr><td>
	<fieldset class="outbox"><legend>输出结果</legend>
		<div id='datalist'></div>
</fieldset>
</td></tr>
</table>
</body>
<script type='text/javascript'>
$(function () {
	Utils.validateInit();
	$('#reset').bind('click', doClear);
	$('#list').bind('click', execute);

	var url = "${_CONTEXT_PATH}/demo/dropdown!list.action";
	$('#unitId').filterText({url:url,valueField:'unitId',textField:'unitName'});
	
	//输出表格
	$("#datalist").ligerGrid({
		columns: [
			{ display: '部门编号', name: 'unitId', align: 'center' },
			{ display: '部门名称', name: 'unitName', align: 'center' }
		],
		pageSize:10,
		sortName: 'unitId',
		width: '100%',
		onError: function() {
			$.dialogBox.alert("查询数据失败",'提示');
		}
	});
});
function inputchar(a) {
	alert(a);
}
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
	var mdata = Utils.convertFormData('listIn','myform');

	var url = "${_CONTEXT_PATH}/demo/dropdown!list.action";
	Utils.ajaxSubmit(url,mdata);
}

</script>
</html>