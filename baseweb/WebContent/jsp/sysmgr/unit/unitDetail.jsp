<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增部门</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
body{
	margin: 10px;
	width:90%;
	height:90%;
	
}

table td{
	line-height:22px;
	width:100px;
	border-bottom: 1px solid #bbb;
}
</style>
</head>
<body>
<form id="detailForm">
<table>
	<tr>
		<td>部门编码：</td>
		<td><s:property value="unit.unitId" /></td>
	</tr>
	<tr>
		<td>部门名称：</td>
		<td><s:property value="unit.unitName" /></td>
	</tr>
	<tr>
		<td>上级部门：</td>
		<td><s:property value="unit.superUnitId" /></td>
	</tr>
	<tr>
		<td>部门级别：</td>
		<td><s:property value="unit.unitLevel" /></td>
	</tr>
	<%-- <tr>
		<td>部门类型：</td>
		<td><s:if test="unit.unitKind==\"1\"">部门</s:if><s:elseif
			test="unit.unitKind==\"2\"">部门</s:elseif> <s:else></s:else></td>
	</tr> --%>
	<tr>
		<td>清算国库代码：</td>
		<td style="width:500px;"><s:property value="unit.settUnitId" /></td>
	</tr>
	<tr>
		<td>管理国库代码：</td>
		<td style="width:500px;"><s:property value="unit.mgrUnitId" /></td>
	</tr>
	<tr>
		<td>启用日期：</td>
		<td style="width:500px;"><s:property value="unit.startDate" /></td>
	</tr>
	<tr>
		<td>废止日期：</td>
		<td style="width:500px;"><s:property value="unit.endDate" /></td>
	</tr>
	<tr>
		<td>部门地址：</td>
		<td style="width:500px;"><s:property value="unit.unitAddress" /></td>
	</tr>
	<tr>
		<td>创建人：</td>
		<td><s:property value="unit.createUser" /></td>
	</tr>
	<tr>
		<td>创建时间：</td>
		<td><s:date name="unit.createTime" format="yyyy-MM-dd" /></td>
	</tr>
	<tr>
		<td>修改人：</td>
		<td><s:property value="unit.lastUpdUser" /></td>
	</tr>
	<tr>
		<td>修改时间：</td>
		<td><s:date name="unit.lastUpdTime" format="yyyy-MM-dd" /></td>
	</tr>
</table>
</form>
</body>
</html>