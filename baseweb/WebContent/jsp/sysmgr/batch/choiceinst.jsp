<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="n" uri="/ncrm-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批处理实例选择</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
html,body {background: url(${_CONTEXT_PATH}/images/${SkinType}/bg_${SkinType}.gif) repeat;}
</style>
</head>
<body>
<n:page action='com.soule.app.sys.batch.BatchMonitorAction' initMethod="doInitInst"/>
<table class='params' style='height:100px;'>
	<tr>
		<td>批处理运行实例号</td>
		<td><n:select name="instId" id="instId" list='rows' listKey="instId" listValue="instId" validate='{required:true}' ></n:select></td>
	</tr>
</table>
</body>
<script type='text/javascript'>
function getInstId() {
	var x = $('#instId').val();
	if (!x) {
		$.dialogBox.alert('请选择实例ID');
	}
	return x;
}
</script>
</html>