<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.soule.app.pfm.tm.BaseTar,com.soule.app.pfm.tm.qtydef.QtyDefPo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>定量指标详细信息</title>
<script type="text/javascript">
</script>
<%@ include file="/comm.jsp" %>
<style type="text/css">
	body {margin: 5px;}
</style>
<script type="text/javascript">
$(function (){
	initShowOtherProcDate();
	initTarscopeCheckBox();
	initDayScope();
	$("#procDateCode").change(function(){
		if($(this).val()=='OT'){
			$("#otherProcDateDiv").css("display","block");
		}else{
			$("#otherProcDateDiv").css("display","none");
		}
	});
});
//当为机构和个人时选中复选框
function initTarscopeCheckBox(){
	var checkObj=$("input[name='tarScopeCheck']:checkbox");
	var checkedObj=$("input[name='tarScopeCheck']:checkbox:checked");
	if(checkedObj&&checkedObj.length<1){
		checkObj.each(function (){
			this.checked=true;
		});
   }
}
//如果处理日期为'其他'时显示,否则不显示
function initShowOtherProcDate(){
	var procDateCode=$("#procDateCode").val();
	if("OT"==procDateCode){
		$("#otherProcDateDiv").css("display","block");
	}else{
		$("#otherProcDateDiv").css("display","none");
	}
}
function initDayScope(){
	var dayScopeStatus="${dayScopeStatus}";
	dayScopeStatus=eval(dayScopeStatus);
	$("input[name='dayScopeList']:checkbox").each(function(){
		var checkValue=this.value;
		if(containScope(dayScopeStatus,checkValue)){
			this.checked=true;
		}else{
			this.checked=false;
		}
	});	
}
function containScope(dayScopeStatus,checkvalue){
	if(dayScopeStatus){
		for(var i=0;i<dayScopeStatus.length;i++){
			var dayScope=dayScopeStatus[i].dayScope;
			var tarStatus=dayScopeStatus[i].tarStatus;
			if(checkvalue==dayScope&&tarStatus=='1'){
				return true;
			}
		}
	}
	return false;
}
</script>
</head>
<body>
<n:page action='com.soule.app.pfm.tm.qtydef.QtyDefAction' initMethod="doInit"/>
<n:enums keys='ind_unit,ind_accu,ind_type,save_type,proc_type,biz_type'/>
<table class="params" width="100%">
	<tr>
		<td width="15%" align="right">指标代码:</td>
		<td width="35%"><input  id="tarCodeText" name="newQtyDef.tarCodeText" type="text" value="${newQtyDef.tarCode}" disabled="disabled"/></td>
		<td align="right" width="15%" >适用对象:</td>
		<td width="35%" >
		  <input name="tarScopeCheck" type="checkbox" <n:checked curVal='${newQtyDef.tarScope}' compare='<%=BaseTar.APPOBJ_ORGCODE%>'/> value="<%=BaseTar.APPOBJ_ORGCODE%>" style="width: 20px;" disabled='disabled'/><%=BaseTar.APPOBJ_ORGNAME %>&nbsp;
		  <input name="tarScopeCheck" type="checkbox" <n:checked curVal='${newQtyDef.tarScope}' compare='<%=BaseTar.APPOBJ_PERSONCODE%>'/> value="<%=BaseTar.APPOBJ_PERSONCODE%>" style="width: 20px;" disabled='disabled'/><%=BaseTar.APPOBJ_PERSONNAME %>
		</td>
	</tr>
	<tr>
		<td align="right">指标名称: </td>
		<td colspan="3"><input id="tarName" name="newQtyDef.tarName" type="text" style="width:500px" value="${newQtyDef.tarName}" disabled='disabled'/></td>
	</tr>
	<tr>
		<td align="right">指标说明:</td>
		<td colspan="3"><input id="tarDesc" name="newQtyDef.tarDesc" type="text" size="30"  style="width:500px"  value="${newQtyDef.tarDesc}" disabled='disabled'/></td>
	</tr>
	<tr>
		<td align="right">计量单位:</td>
		 <td align="left">
         <n:select codetype="ind_unit" id="measUnitCode" name='newQtyDef.measUnitCode' emptyOption="true" disabled="true"/>
        </td>
        <td align="right">数据类型:</td>
		<td>
          <n:select codetype="ind_accu" id="measDefCode" name='newQtyDef.measDefCode' emptyOption="true" disabled="true"/>
		</td>
	</tr>
	<tr>
		<td align="right">指标属性:</td>
		<td>
         <n:select codetype="ind_type" id="tarProperty" name='newQtyDef.tarProperty' emptyOption="true" disabled="true"/>
		</td>
		<td align="right">指标分类:</td>
		<td>
		 <input type="text" id="tarSortName" name="newQtyDef.tarSortName" disabled='disabled' value="${newQtyDef.tarSortName}" class="unit_select"/>
		 <input type="hidden" id="tarSortCode" name="newQtyDef.tarSortCode" value="${newQtyDef.tarSortCode}">
		</td>
	</tr>
	<tr id='dayScopeListShow' style="display:none;">
		<td align="right">日均范围:</td>
		<td>
		 <input type="checkbox" name="dayScopeList" value='<%=BaseTar.AVER_SCOPE_Y%>' style="width: 20px;" disabled='disabled' />年
		 <input type="checkbox" name="dayScopeList" value="<%=BaseTar.AVER_SCOPE_Q%>" style="width: 20px;" disabled='disabled' />季
		 <input type="checkbox" name="dayScopeList" value="<%=BaseTar.AVER_SCOPE_M%>" style="width: 20px;" disabled='disabled' />月
		</td>
		<td></td>
		<td></td>		
	</tr>
	<tr>
		<td align="right">保存日期:</td>
		<td>
		  <n:select codetype="save_type" id="storeDate" name='newQtyDef.storeDate' emptyOption="true" disabled="true"/>
		</td>
		<td align="right">处理日期: </td>
		<td>
		  <n:select codetype="proc_type" id="procDateCode" name='newQtyDef.procDateCode' emptyOption="true" disabled="true"/><div id="otherProcDateDiv" style="display: none;"><input type="text" name="newQtyDef.tarProcDate" id="otherProcDate" disabled='disabled'/>(处理日期为其他时必填)</div>
		</td>
   </tr>
	<tr>
	<%-- <td align="right">业务条线: </td>
		<td>
		<n:select codetype="biz_type" id="tarBizType" name='newQtyDef.tarBizType' emptyOption="true"  disabled="true" value="#attr.newQtyDef.tarBizType"/>
	   </td> --%>
	   <td align="right">产品类型:</td>
		<td>
		  <input type="text" id="prdtypeName" name="newQtyDef.prdtypeName" disabled='disabled' class="unit_select"/>
		  <input type="hidden" id="prdtypeCode" name="newQtyDef.prdtypeCode"/>
		</td>
		<td align="right">科目号:</td>
		<td>
		 <input type="text" id="subjName" name="newQtyDef.subjName" disabled='disabled' class="unit_select" >
		 <input type="hidden" id="subjCode" name="newQtyDef.subjCode">
		</td>
	</tr>
 <!-- 	<tr>
		<td align="right">科目号:</td>
		<td colspan="3">
		 <input type="text" id="subjName" name="newQtyDef.subjName" disabled='disabled' class="unit_select" >
		 <input type="hidden" id="subjCode" name="newQtyDef.subjCode">
		</td>
   </tr> -->
    <tr>
		<td align="right">备注:</td>
		<td colspan="3">
		  <textarea rows="3" cols="40" style="width:500px" id="remark" name="newQtyDef.remark" disabled='disabled'  >${newQtyDef.remark}</textarea>
		</td>
	</tr>
	<tr id="calcExpShow">
		<td align="right">计算公式</td>
		<td colspan="3">
		  <textarea rows="3" cols="40" style="width:500px" id="calcExp" name="qtyExp.calcExp" disabled='disabled'  >${qtyExp.calcExp}</textarea>
		</td>
	</tr>
</table>
</body>
</html>
<script type="text/javascript">
//alert("${newQtyDef.dayScope}");
var tarType = '${newQtyDef.tarType}';
if(tarType=='1'){
	//$('#dayScopeListShow').show();
	$('#calcExpShow').hide();
}else if(tarType=='2'){
	//$('#dayScopeListShow').hide();
	$('#calcExpShow').show();
}else{
	//$('#dayScopeListShow').hide();
	$('#calcExpShow').hide();
}
</script>