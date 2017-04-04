<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.soule.app.pfm.tm.BaseTar,com.soule.app.pfm.tm.qtydef.QtyDefPo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>基础定量指标修改</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
	body{margin:0 30px;width:850px;}
</style>
<script type="text/javascript">
$(function (){
	initShowOtherProcDate();
	initTarscopeCheckBox();
	initDayScope();
	$("#createOrgBtn").click(openSelectUnit);
	$("#cancelBtn").click(function(){
		Utils.removeTabById("updateBaseQtyTar");
	});
	$("#prdtypeName").click(function(){
		 var url="${_CONTEXT_PATH}/jsp/common/pub_productTree.jsp";
		　$.dialog.open(url,
				{
			      lock:true,
			      opacity:0.07,
			      title:'产品类型',
			      ok: function(){
	                    var products=$.dialog.data('products');
	                    if(products){
		                    var prdname="";
			                var prdcode="";    
	                        for(var i=0;i<products.length;i++){
	                        	var product=products[i];
	                        	prdcode=product[0];
	                        	prdname=product[1];
		                    }
		                    $("#prdtypeCode").val(prdcode);
	                        $("#prdtypeName").val(prdname);
	                    }
	                    
	                  }
				});
	});
	$("#subjName").click(function (){
		  var url="${_CONTEXT_PATH}/jsp/common/subjectTree.jsp";
		　$.dialog.open(url,
				{
			      lock:true,
			      opacity:0.07,
			      title:'科目号',
			      ok: function(){
	                    var subjects=$.dialog.data('subjects');
	                    if(subjects){
		                    var subjName="";
			                var subjCode="";    
	                        for(var i=0;i<subjects.length;i++){
	                        	var subject=subjects[i];
	                        	subjCode=subject[0];
	                        	subjName=subject[1];
		                    }
		                    $("#subjCode").val(subjCode);
	                        $("#subjName").val(subjName);
	                    }
	                    
	                  }
			      
				});
	});

	$("#procDateCode").change(function(){
		if($(this).val()=='OT'){
			$("#otherProcDateDiv").css("display","block");
		}else{
			$("#otherProcDateDiv").css("display","none");
		}
	});
});
function add(){//修改方法
	if(validate()){
		try{
			$("#tarScope").val(getTarScope());
			var url = "${_CONTEXT_PATH}/qtydefManager!update.action";
			var data=Utils.convertFormData('','insertForm');
			$.ajax({
				url: url,
				type:'post',
				data: data,
				contentType:"application/x-www-form-urlencoded; charset=utf-8", 
				success: function(result){
					if(Utils.isSuccess(result)){
						$.dialogBox.info(result.retMsg);
					}else{
						$.dialogBox.error(result.retMsg);
					}
				},
				error:function(result){
					$.dialogBox.error('修改失败。');
				}
			});
		}catch(e){}
	}
	return false;
}
function getTarScope(){
	var tarScope=null;
	var orgPersonCode="<%=BaseTar.APPOBJ_ORGANDPERCODE%>";
	var checkedbox=$("input[name='tarScopeCheck']:checkbox:checked");
	if(checkedbox.length==2){
		tarScope= orgPersonCode;
	}else if(checkedbox.length==1){
		tarScope=checkedbox[0].value;
	}
	return tarScope;
}
function clearScreen() {
	$('input').each(function(i,item) {
		item.value= '';
	});
}
function validate(){
	if($("#insertForm").valid()){
		var procDateCode=$("#procDateCode").val();
		if("OT"==procDateCode){
			var otherProcDate=$("#otherProcDate").val();
			if(!otherProcDate||otherProcDate.trim()==""){
				$.dialogBox.warn("请输入指定处理日期","消息",function(){
					$("#otherProcDate").focus();
				});
				return false;
			}
		}else{
			$("#otherProcDate").val("");
		}
		return true;
	}
	return false;
}

function openSelectUnit(){
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
}
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
$(function() {
	Utils.validateInit();
}); 

</script>
</head>

<body>
<n:page action='com.soule.app.pfm.tm.qtydef.QtyDefAction' />
<n:enums keys='ind_unit,ind_accu,ind_type,save_type,proc_type,biz_type'/>
<form id="insertForm" name="insertForm">
<input id="tarCode" name="newQtyDef.tarCode" type="hidden" value="${newQtyDef.tarCode}"/>
<input id="tarScope" name="newQtyDef.tarScope" type="hidden"/>
<input id="lastUpdOrg" name="newQtyDef.lastUpdOrg" type="hidden" value="${logUserInfo.operUnitId}"/>
<input id="tarType" name="newQtyDef.tarType" type="hidden" value="<%=BaseTar.TAR_TYPE_BASE%>"/>
<fieldset class="detailList"><legend>修改基础定量指标</legend>
<table class="params">
	<tr>
		<td width="15%" align="right">指标代码 :</td>
		<td width="35%"><input  id="tarCodeText" name="newQtyDef.tarCodeText" type="text" value="${newQtyDef.tarCode}" disabled="disabled"/></td>
		<td width="15%"></td><td width="35%"></td>
	</tr>
	<tr>
		<td align="right"><font color='red'>*</font>指标名称: </td>
		<td colspan="3"><input id="tarName" name="newQtyDef.tarName" type="text" style="width:80%" value="${newQtyDef.tarName}"  disabled="disabled" validate="{required:true,minlength:2,maxlength:50
		,remote:{url:'${_CONTEXT_PATH}/qtydefManager!validTarName.action?opt=upd',type:'post',data:{'newQtyDef.tarName':function(){return $('#tarName').val();},'oldTarName':'${newQtyDef.tarName}'}},messages:{remote:'指标名称重复'}}"/></td>
	</tr>
	<tr>
		<td align="right">指标说明: </td>
		<td colspan="3"><input id="tarDesc" name="newQtyDef.tarDesc" style="width:80%" value="${newQtyDef.tarDesc}" size="30" type="text"/></td>
	</tr>
	<tr>
		<td align="right"><font color='red'>*</font>计量单位: </td>
		 <td align="left">
         <n:select codetype="ind_unit" id="measUnitCode" name='newQtyDef.measUnitCode' emptyOption="true" validate="{required:true}"/>
        </td>
        <td align="right"><font color='red'>*</font>数据类型:</td>
		<td>
          <n:select codetype="ind_accu" id="measDefCode" name='newQtyDef.measDefCode' emptyOption="true" validate="{required:true}"/>
		</td>
	</tr>
	<tr>
		<td align="right"><font color='red'>*</font>指标属性:</td>
		<td>
         <n:select codetype="ind_type" id="tarProperty" name='newQtyDef.tarProperty' emptyOption="true" validate="{required:true}" disabled="true"/>
		</td>
		<td align="right">指标分类:</td>
		<td><input id="tarSortName" name="newQtyDef.tarSortName" type="text" value="${newQtyDef.tarSortName}" readonly="readonly" class="unit_select" onclick="selectTarSort('tarSortName','tarSortCode');"/>
		  <input id="tarSortCode" name="newQtyDef.tarSortCode" type="hidden" value="${newQtyDef.tarSortCode}"/>
		</td>
	</tr>
	<%-- <tr style="display: <s:if test='newQtyDef.tarProperty==1'>block;</s:if><s:else>none;</s:else>"> --%>
   <tr>
		<td align="right">日均范围:</td>
		<td>
		  <input <n:checked curVal='<%=BaseTar.AVER_SCOPE_Y%>' list='${dayScopeList}'/> type="checkbox" name="dayScopeList" value="<%=BaseTar.AVER_SCOPE_Y%>" style="width: 20px;"/>年
		  <input <n:checked curVal='<%=BaseTar.AVER_SCOPE_Q%>' list='${dayScopeList}'/> type="checkbox" name="dayScopeList" value="<%=BaseTar.AVER_SCOPE_Q%>" style="width: 20px;"/>季
		  <input <n:checked curVal='<%=BaseTar.AVER_SCOPE_M%>' list='${dayScopeList}'/> type="checkbox" name="dayScopeList" value="<%=BaseTar.AVER_SCOPE_M%>" style="width: 20px;"/>月
		</td>
		<td align="right">适用对象:</td>
		<td>
		  <input name="tarScopeCheck" type="checkbox" <n:checked curVal='${newQtyDef.tarScope}' compare='<%=BaseTar.APPOBJ_ORGCODE%>'/> value="<%=BaseTar.APPOBJ_ORGCODE%>" style="width: 20px;"/><%=BaseTar.APPOBJ_ORGNAME %>&nbsp;
		  <input name="tarScopeCheck" type="checkbox" <n:checked curVal='${newQtyDef.tarScope}' compare='<%=BaseTar.APPOBJ_PERSONCODE%>'/> value="<%=BaseTar.APPOBJ_PERSONCODE%>" style="width: 20px;"/><%=BaseTar.APPOBJ_PERSONNAME %>
		</td>
	</tr>
	<tr>
		<td align="right"><font color='red'>*</font>保存日期: </td> 
		<td>
		  <n:select codetype="save_type" id="storeDate" name='newQtyDef.storeDate' emptyOption="true" validate="{required:true}"/>
		</td>
		<td align="right" valign="top"><font color='red'>*</font>处理日期:</td>
		<td>
		  <n:select codetype="proc_type" id="procDateCode" name='newQtyDef.procDateCode' emptyOption="true" validate="{required:true}"/>
		  <div id="otherProcDateDiv"><input type="text" name="newQtyDef.tarProcDate" id="otherProcDate" value="${newQtyDef.tarProcDate}"/>(处理日期为其他时必填)</div>
		</td>
   </tr>
   <tr>
	   <td align="right">产品类型:</td>
		<td>
		  <input type="text" id="prdtypeName" name="prdtypeName" value="${newQtyDef.prdtypeName}" readonly="readonly" class="unit_select"/>
		  <input type="hidden" id="prdtypeCode" name="newQtyDef.prdtypeCode" value="${newQtyDef.prdtypeCode}"/>
		</td>
		<td align="right">科目号:</td>
		<td colspan="3">
		  <input type="text" id="subjName" name="subjName" value="${newQtyDef.subjName}" readonly="readonly" class="unit_select"/>
		 <input type="hidden" id="subjCode" name="newQtyDef.subjCode" value="${newQtyDef.subjCode}"/>
		</td>
   </tr>
   <tr>
		<td align="right">备注:</td>
		<td colspan="3">
		  <textarea rows="3" cols="40" style="width:80%" id="remark" name="newQtyDef.remark">${newQtyDef.remark}</textarea>
		</td>
	</tr>
   <tr style="display:none;">
		<td align="right"><font color='red'>*</font>业务条线:</td>
		<td>
		<n:select codetype="biz_type" id="tarBizType" name='newQtyDef.tarBizType' emptyOption="true" validate="{required:true}" disabled="true"/>
	   </td>
		<td align="right">是否存在基数指标:</td>
		<td>
		  <input type="radio" name="newQtyDef.bsFlag" value="<%=QtyDefPo.BSFLAG_YES%>" <n:checked curVal='${newQtyDef.bsFlag}' compare='<%=QtyDefPo.BSFLAG_YES%>'/> style="width: 20px;"/>是<input type="radio" name="newQtyDef.bsFlag" value="<%=QtyDefPo.BSFLAG_NO%>" <n:checked curVal='${newQtyDef.bsFlag}' compare='<%=QtyDefPo.BSFLAG_NO%>'/> style="width: 20px;"/>否
		</td>
   </tr>
	<tr>
	  <td colspan="4" align="center">
	    <input type="button" value="确&nbsp;&nbsp;定" id="saveBtn" onclick="add();" class="l-button" style="margin: 0;padding: 0;"/>&nbsp;&nbsp;
	    <input type="button" value="取&nbsp;&nbsp;消 " id="cancelBtn" class="l-button" style="margin: 0;padding: 0;"/>
	  </td>
	</tr>
</table>
</fieldset>
</form>
</body>
<script type="text/javascript">
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
</html>