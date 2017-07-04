
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.soule.app.pfm.tm.BaseTar"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>定量指标详细信息</title>
<%@ include file="/comm.jsp" %>
<style type="text/css">

</style>
<script type="text/javascript">
	function cancelDialog(){
		$.dialog.close();
	}
	
	$(function() {
		$('#cancelBtn').bind('click', cancelDialog);
	}); 
</script>
</head>
<body>
<n:page action='com.soule.app.pfm.tm.qtydef.QtyDefAction' initMethod="doInit"/>
<n:enums keys='ind_unit,ind_accu,save_type,proc_type,tar_type,tar_sort,data_from'/>
<table class="params" width="100%">
	<tr>
		<td width="15%" align="right">指标代码:</td>
		<td width="35%"><input  id="tarCodeText" name="newQtyDef.tarCodeText" type="text" value="${newQtyDef.tarCode}" disabled="true"/></td>
		<td align="right" width="15%"></td><td width="35%"></td>
	</tr>
	<tr>
		<td align="right">指标名称: </td>
		<td colspan="3"><input id="tarName" name="newQtyDef.tarName" type="text" style="width:500px" value="${newQtyDef.tarName}" disabled="true"/></td>
	</tr>
	<tr>
		<td align="right">指标说明:</td>
		<td colspan="3"><input id="tarDesc" name="newQtyDef.tarDesc" type="text" size="30"  style="width:500px"  value="${newQtyDef.tarDesc}" disabled="true"/></td>
	</tr>
	<tr>
		<td align="right">计量单位:</td>
		<td align="left">
			<n:select codetype="ind_unit" id="measUnitCode" name='newQtyDef.measUnitCode' emptyOption="true" disabled="true" value="newQtyDef.measUnitCode" />
		</td>
		<td align="right">数据类型:</td>
		<td>
			<n:select codetype="ind_accu" id="measDefCode" name='newQtyDef.measDefCode' emptyOption="true" disabled="true" value="newQtyDef.measDefCode" />
		</td>
	</tr>
	<tr>
		<td align="right">指标模型分类</td>
		<td>
			<n:select codetype="tar_sort" id="tarSortCode" name='newQtyDef.tarSortCode' emptyOption="true" validate="{required:true}"  disabled="true" value="newQtyDef.tarSortCode"/>
		</td>
		<td align="right">数据来源</td>
		<td>
			<n:select codetype="data_from" id="dataSource" name='newQtyDef.dataSource' emptyOption="false" validate="{required:true}"  disabled="true" value="newQtyDef.dataSource"/>
		</td>
	</tr>
	<tr>
		<td align="right"><font color='red'>*</font>保存日期: </td> 
		<td>
		  <n:select codetype="save_type" id="storeDate" name='newQtyDef.storeDate' emptyOption="true" validate="{required:true}"  disabled="true" value="newQtyDef.storeDate"/>
		</td>
		<td align="right" valign="top"><font color='red'>*</font>处理日期:</td>
		<td>
		  <n:select codetype="proc_type" id="procDateCode" name='newQtyDef.procDateCode' emptyOption="true" validate="{required:true}"  disabled="true" value="newQtyDef.procDateCode"/>
		</td>
	</tr>
	<tr>
		<td align="right">备注:</td>
		<td colspan="3">
		  <textarea rows="3" cols="40" style="width:500px" id="remark" name="newQtyDef.remark"  disabled="true">${newQtyDef.remark}</textarea>
		</td>
	</tr>
	<tr id="calcExpShow">
		<td align="right">计算公式</td>
		<td colspan="3">
		  <textarea rows="3" cols="50" style="width:500px" id="calcExp" name="qtyExp.calcExp" disabled="true">${qtyExp.calcExp}</textarea>
		</td>
	</tr>
	<tr align="center">
		<td colspan="4" align="center" style="width:100%;"><input type="button" value="取&nbsp;消" id="cancelBtn" class="l-button" style="margin-top:10px;"/></td>
	</tr>
</table>
</body>
</html>
<script type="text/javascript">
var tarType = '${newQtyDef.tarType}';
if(tarType=='E'){
	$('#calcExpShow').hide();
}else if(tarType=='C'){
	$('#calcExpShow').show();
}else{
	$('#calcExpShow').hide();
}
</script>