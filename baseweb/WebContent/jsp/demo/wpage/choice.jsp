<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<script type="text/javascript">

//TODO 本页面完成功能组件ID选择
function getSelectFunction() {
	//此处修改为具体的选择功能ID
	return document.getElementById("pg").value;
}
</script>
<style type="text/css">

</style>
</head>

<body>
      <select id="pg">
      	<option value="/jsp/demo/wpage/mywidget0.jsp">模板1</option>
      	<option value="/jsp/demo/wpage/mywidget1.jsp">模板2</option>
      	<option value="/jsp/demo/wpage/mywidget2.jsp">模板3</option>
      	<option value="/jsp/demo/wpage/mywidget3.jsp">模板4</option>
      	<option value="/jsp/demo/wpage/mywidget4.jsp">模板5</option>
      	<option value="/jsp/demo/wpage/mywidget5.jsp">模板6</option>
      	<option value="/jsp/demo/wpage/mywidget6.jsp">模板7</option>
      	<option value="/jsp/demo/wpage/mywidget7.jsp">模板8</option>
      	<option value="/jsp/demo/wpage/mywidget8.jsp">模板9</option>
      </select>
</body>
</html>
