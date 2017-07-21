<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据导入返回错误信息查看</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
#errorlist {width:650px;}
</style>
</head>
<body>
<table class='content2'>
<tr><td>
<form id="myform" action="">
<fieldset class="outbox"><legend>错误列表</legend>
		<div id='errorlist' style="overflow:auto;"></div>
</fieldset>
</form>
</td></tr>
</table>
</body>
<script type='text/javascript'>
$(function () {
	var data = $.dialogBox.opener.errordata;
	var tabledata = {};
	tabledata.rows = data;
	//输出表格
	$("#errorlist").ligerGrid({
		enumlist: _enum_params ,
		columns: [
		    { display: '行数', name: 'rowid', align: 'left' ,width:'10%'},
		    { display: '列名', name: 'columnName', align: 'left',width:'20%'},
		    { display: '错误描述', name: 'errorInfo', align: 'left',width:'68%' }
		],
		pageSize:10,
		height:'100%',
		heightDiff:-20,
		data:tabledata
	});
});

</script>
</html>