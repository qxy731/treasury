
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.soule.app.pfm.tm.BaseTar"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>基础指标修改</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
</style>
<script type="text/javascript">
function add(){//修改方法
	if(validate()){
		try{
			$("#tarScope").val(getTarScope());
			var url = "${_CONTEXT_PATH}/qtydefManager/qty-def!update.action";
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


function validate(){
	if($("#insertForm").valid()){
		return true;
	}
	return false;
}

/* function openSelectUnit(){
	Utils.openSelectUnit('#createOrgBtn','',setUnitIdName);
}

function setUnitIdName(){
	var selectNode=this.iframe.contentWindow.manager.getSelected();
	if(selectNode){
		var unitId=selectNode.data.unitId;
		var unitName=selectNode.data.unitName;
		$("#createOrg").val(unitId);
		$("#createOrgName").val(unitName);
	}
} */

$(function() {
	Utils.validateInit();
	initTarscopeCheckBox();
	$('#saveBtn').bind('click', add);
	$('#cancelBtn').bind('click', cancelDialog);
}); 
function getTarScope(){
	var tarScope=null;
	var orgPersonCode="11000000";
	var checkedbox=$("input[name='tarScopeCheck']:checkbox:checked");
	if(checkedbox.length==2){
		tarScope = orgPersonCode;
	}else if(checkedbox.length==1){
		tarScope=checkedbox[0].value;
	}
	return tarScope;
}

//当为国库和金融机构时选中复选框
function initTarscopeCheckBox(){
	var tarScope = $("#tarScope").val();
	var checkObj=$("input[name='tarScopeCheck']:checkbox");
	if(tarScope=="<%=BaseTar.APPOBJ_ORGCODE%>"){
		$("#tarScopeCheck1").attr("checked","true");
	}else if(tarScope=="<%=BaseTar.APPOBJ_PERSONCODE%>"){
		$("#tarScopeCheck2").attr("checked","true");
	}else{
	   	$("#tarScopeCheck1").attr("checked","true");
		$("#tarScopeCheck2").attr("checked","true");
	}
}
</script>
</head>
<body>
<n:page action='com.soule.app.pfm.tm.qtydef.QtyDefAction' />
<n:enums keys='ind_unit,ind_accu,save_type,proc_type,tar_type,tar_sort,data_from,tar_property'/>
<form id="insertForm" name="insertForm">
<input id="tarCode" name="newQtyDef.tarCode" type="hidden" value="${newQtyDef.tarCode}"/>
<input id="lastUpdOrg" name="newQtyDef.lastUpdOrg" type="hidden" value="${logUserInfo.operUnitId}"/>
<input id="tarType" name="newQtyDef.tarType" type="hidden" value="<%=BaseTar.TAR_TYPE_BASE%>"/>
<input id="tarScope" name="newQtyDef.tarScope" type="hidden" value="${newQtyDef.tarScope}"/>
<table class="params" width="100%">
	<tr>
		<td width="15%" align="right">指标代码 :</td>
		<td width="35%"><input  id="tarCodeText" name="newQtyDef.tarCodeText" type="text" value="${newQtyDef.tarCode}" disabled="disabled"/></td>
		<td width="15%"><font color='red'>*</font>适用对象:</td>
		<td width="35%">
		  <input style="width:14px;height:14px;margin:4px;" id="tarScopeCheck1" name="tarScopeCheck" type="checkbox" value="<%=BaseTar.APPOBJ_ORGCODE%>"/><%=BaseTar.APPOBJ_ORGNAME %>&nbsp;
		  <input style="width:14px;height:14px;margin:4px;" id="tarScopeCheck2" name="tarScopeCheck" type="checkbox" value="<%=BaseTar.APPOBJ_PERSONCODE%>"/><%=BaseTar.APPOBJ_PERSONNAME %>
		</td>
	</tr>
	<tr>
		<td align="right"><font color='red'>*</font>指标名称: </td>
		<td colspan="3"><input id="tarName" name="newQtyDef.tarName" type="text" style="width:80%" value="${newQtyDef.tarName}" validate="{required:true,minlength:2,maxlength:50
		,remote:{url:'${_CONTEXT_PATH}/qtydefManager/qty-def!validTarName.action?opt=upd',type:'post',data:{'newQtyDef.tarName':function(){return $('#tarName').val();},'oldTarName':'${newQtyDef.tarName}'}},messages:{remote:'指标名称重复'}}"/></td>
	</tr>
	<tr>
		<td align="right">指标说明: </td>
		<td colspan="3"><input id="tarDesc" name="newQtyDef.tarDesc" style="width:80%" value="${newQtyDef.tarDesc}" size="30" type="text"/></td>
	</tr>
	<tr>
		<td align="right"><font color='red'>*</font>计量单位:</td>
		<td align="left">
			<n:select codetype="ind_unit" id="measUnitCode" name='newQtyDef.measUnitCode' emptyOption="true" validate="{required:true}" value="newQtyDef.measUnitCode"/>
		</td>
			<td align="right"><font color='red'>*</font>数据类型:</td>
		<td>
			<n:select codetype="ind_accu" id="measDefCode" name='newQtyDef.measDefCode' emptyOption="true" validate="{required:true}" value="newQtyDef.measDefCode"/>
		</td>
	</tr>
	<tr>
		<td align="right">指标模型分类</td>
		<td>
			<n:select codetype="tar_sort" id="tarSortCode" name='newQtyDef.tarSortCode' emptyOption="true" validate="{required:true}"  value="newQtyDef.tarSortCode"/>
		</td>
		<td align="right">数据来源</td>
		<td>
			<n:select codetype="data_from" id="dataSource" name='newQtyDef.dataSource' emptyOption="false" validate="{required:true}" value="newQtyDef.dataSource"/>
		</td>
	</tr>
	<tr>
		<td align="right"><font color='red'>*</font>保存日期: </td> 
		<td>
			<n:select codetype="save_type" id="storeDate" name='newQtyDef.storeDate' emptyOption="true" validate="{required:true}" value="newQtyDef.storeDate"/>
		</td>
		<td align="right" valign="top"><font color='red'>*</font>处理日期:</td>
		<td>
		  <n:select codetype="proc_type" id="procDateCode" name='newQtyDef.procDateCode' emptyOption="true" validate="{required:true}"  value="newQtyDef.procDateCode"/>
		</td>
	</tr>
	<tr>
		<td align="right"><font color='red'>*</font>指标属性</td>
		<td>
         <n:select codetype="tar_property" id="tarProperty" name='newQtyDef.tarProperty' emptyOption="true" validate="{required:true}"/>
		</td>
		<td align="right"></td><td></td>
	</tr>
	<tr>
		<td align="right">备注:</td>
		<td colspan="3">
		  <textarea rows="3" cols="40" style="width:80%" id="remark" name="newQtyDef.remark">${newQtyDef.remark}</textarea>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<div style="margin-left:295px;margin-top:5px;">
			 <input id="saveBtn" type="button" value="提&nbsp;交" class="l-button" style="float:left;">
			 <input id="cancelBtn" type="button" value="取&nbsp;消" class="l-button" style="float:left;margin-left:5px;">
			</div>
		</td>
	</tr>
</table>
</form>
</body>
</html>