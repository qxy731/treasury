<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>输入</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
body {
	width:300px;
	height:80px;
}
</style>
</head>
<body>
<table class='s1-params'>
	<tr>
		<td>批处理编号</td>
		<td><input type='text' id='stepId' /></td>
	</tr>
</table>
</body>
<script type='text/javascript'>
	function getInput(){
		return $('#stepId').val();
	}
</script>
</html>