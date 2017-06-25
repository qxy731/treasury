
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.soule.app.pfm.tm.BaseTar"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>基础指标新增</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
。params{
	width:700px;
}
.div_bottom {
	margin-top: 10px
}

.table_btn td {
	padding: 1px 20px 1px 20px
}
</style>
<script type="text/javascript">
function add(){
	if(validate()){
		try{
			$("#saveBtn").attr("disabled",true);
			var url = "${_CONTEXT_PATH}/qtydefManager/qty-def!insert.action";
			var data = $('#insertForm').serialize();
			Utils.ajaxSubmit(url, data, function(result) {
				$.dialogBox.info(result.retMsg, function() {
					$.dialogBox.opener.query();
					$.dialogBox.close();
				});
			});
		}catch(e){}
	}
	return false;
}

function cancelDialog() {
	$.dialog.close();
}

function clearScreen() {
	$('input').each(function(i,item) {
		item.value= '';
	});
}
function validate(){
	if($("#insertForm").valid()){
		return true;
	}
	return false;
}

$(function() {
	Utils.validateInit();
	$('#saveBtn').bind('click', add);
	$('#cancelBtn').bind('click', cancelDialog);
});
</script>
</head>
<body>
<n:page action='com.soule.app.pfm.tm.qtydef.QtyDefAction' initMethod="doInitBusinessLine"/>
<n:enums keys='ind_unit,ind_accu,save_type,proc_type,tar_type,tar_sort,data_from,ind_type'/>
<form id="insertForm" name="insertForm">
<input id="createOrg" name="newQtyDef.createOrg" type="hidden" value="${logUserInfo.operUnitId}"/>
<input id="tarType" name="newQtyDef.tarType" type="hidden" value="<%=BaseTar.TAR_TYPE_BASE%>"/>
<input id="tarScope" name="newQtyDef.tarScope" type="hidden" value="<%=BaseTar.APPOBJ_ORGANDPERCODE%>"/>
<table class="params">
	<tr>
		<td width="15%" align="right">指标代码</td>
		<td width="35%"><input id="tarCodeText" name="newQtyDef.tarCodeText" type="text" disabled="disabled"/></td>
		<td width="15%" ></td><td width="35%" ></td>
	</tr>
	<tr>
		<td align="right"><font color='red'>*</font>指标名称</td>
		<td colspan="3"><input id="tarName" name="newQtyDef.tarName" type="text" style="width:80%;" validate="{required:true,minlength:2,maxlength:50
		,remote:{url:'${_CONTEXT_PATH}/qtydefManager/qty-def!validTarName.action',type:'post',data:{'newQtyDef.tarName':function(){return $('#tarName').val();}}},messages:{remote:'指标名称重复'}}"/></td>
	</tr>
	<tr>
		<td align="right">指标说明</td>
		<td colspan="3"><input id="tarDesc" name="newQtyDef.tarDesc" size="30"  style="width:80%;"  type="text"/></td>
	</tr>
	<tr>
		<td align="right"><font color='red'>*</font>计量单位</td>
		<td align="left">
         <n:select codetype="ind_unit" id="measUnitCode" name='newQtyDef.measUnitCode' emptyOption="true" validate="{required:true}"/>
        </td>
        <td align="right"><font color='red'>*</font>数据类型</td>
		<td>
          <n:select codetype="ind_accu" id="measDefCode" name='newQtyDef.measDefCode' emptyOption="true" validate="{required:true}"/>
		</td>
	</tr>
	<tr>
		<td align="right">指标模型分类</td>
		<td>
			<n:select codetype="tar_sort" id="tarSortCode" name='newQtyDef.tarSortCode' emptyOption="true"/>
		</td>
		<td align="right">数据来源</td>
		<td>
			<n:select codetype="data_from" id="dataSource" name='newQtyDef.dataSource' emptyOption="false" validate="{required:true}"/>
		</td>
	</tr>
	<tr>
		<td align="right"><font color='red'>*</font>保存日期</td>
		<td>
		<n:select codetype="save_type" id="storeDate" name='newQtyDef.storeDate' emptyOption="true" validate="{required:true}"/>
		</td>
		<td align="right"><font color='red'>*</font>处理日期</td>
		<td>
		<n:select codetype="proc_type" id="procDateCode" name='newQtyDef.procDateCode' emptyOption="true" validate="{required:true}"/>
		</td>
   </tr>
   <tr>
		<td align="right">备注</td>
		<td colspan="3">
		  <textarea rows="3" cols="40" style="width:80%" id="remark" name="newQtyDef.remark"></textarea>
		</td>
	</tr>
</table>
<div align="center" class="div_bottom">
	<table class="table_btn">
		<tr>
			<td><input id="saveBtn" type="button" value="提&nbsp;&nbsp;交" class="l-button"></td>
			<td><input id="cancelBtn" type="button" value="取&nbsp;&nbsp;消" class="l-button"></td>
		</tr>
	</table>
</div>
</form>
</body>
</html>