<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增角色</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
</style>
<script type="text/javascript">
	$(function() {
		//$("#dataDate").ligerDateEditor();
	});
	function select() {
		var val = $("#dataDate").val();
		return val;
	}
	
	
</script>
</head>
<body>
	<!-- <form > -->
		<table class='params' class='params' style='height:98%;width:100%;'>
			<tr>
				<td><font color="red">*</font>数据日期</td>
				<td><input type='text' id='dataDate' name='dataDate' class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM'})"/></td>
			</tr>
		</table>
	<!-- </form> -->
</body>
</html>