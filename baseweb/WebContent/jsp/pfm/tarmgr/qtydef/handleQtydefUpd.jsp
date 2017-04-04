<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.soule.app.pfm.tm.propdef.PropDefPo,com.soule.app.pfm.tm.qtydef.QtyDefPo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>手工定量指标修改</title>
<jsp:include page="/comm.jsp"></jsp:include>
<link href="${_CONTEXT_PATH}/jwebui/artdialog/skins/blue.css" rel="stylesheet" type="text/css" />
<script src="${_CONTEXT_PATH}/jwebui/artdialog/jquery.artDialog.source.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/artdialog/plugins/iframeTools.source.js" type="text/javascript"></script>
<style type="text/css">
	body{margin:0 30px;width:850px;}
</style>
<script type="text/javascript">
$(function (){
	initTarscopeCheckBox();
	$("#cancelBtn").click(function(){
			Utils.removeTabById("updateHandleQtyTar");
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
});
function add(){
	if(validate()){//修改方法
		try{
			$("#tarScope").val(getTarScope());
			var url = "${_CONTEXT_PATH}/qtydefManager!update.action";
			//var data=getFormPara($("#insertForm"));
			var data=Utils.convertFormData('',"insertForm");
			$.ajax({
				url: url,
				type:'post',
				data: data,
				contentType:"application/x-www-form-urlencoded; charset=utf-8", 
				success: function(result){
					//$.dialogBox.info(result.retMsg);
					if(Utils.isSuccess(result)){
						$.dialogBox.info(result.retMsg);
					}else{
						$.dialogBox.error(result.retMsg);
					}
				},error:function(result){
					$.dialogBox.error('修改失败。');
				}
			});
		}catch(e){
			//alert(e);
		}
	}
	return false;
}
function getTarScope(){
	var tarScope=null;
	var orgPersonCode="<%=PropDefPo.APPOBJ_ORGANDPERCODE%>";
	var checkedbox=$("input[name='tarScopeCheck']:checkbox:checked");
	if(checkedbox.length==2){
		tarScope= orgPersonCode;
	}else if(checkedbox.length==1){
		tarScope=checkedbox[0].value;
	}
	return tarScope;
}

function validate(){
	return $("#insertForm").valid();
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
$(function() {
	Utils.validateInit();
});
</script>
</head>
<body>
<n:page action='com.soule.app.pfm.tm.qtydef.QtyDefAction' />
<n:enums keys='ind_unit,ind_accu,ind_type,save_type,biz_type' />
<form id="insertForm" name="insertForm">
<input id="tarCode" name="newQtyDef.tarCode" type="hidden" value="${newQtyDef.tarCode}" />
<input id="tarType" name="newQtyDef.tarType" type="hidden"	value="<%=QtyDefPo.TAR_TYPE_HANDLE%>" /><!-- 说明是手工指标 -->
<input id="tarScope" name="newQtyDef.tarScope" type="hidden" />
<input id="lastUpdOrg" name="newQtyDef.lastUpdOrg" type="hidden" value="${logUserInfo.operUnitId}" />
<fieldset class="detailList"><legend>修改手工定量指标</legend>
<table class="params">
	<tr>
		<td align="right" width="15%">指标代码 :</td>
		<td width="35%">
			<input id="tarCodeText" name="newQtyDef.tarCodeText" type="text" value="${newQtyDef.tarCode}" disabled="disabled" />
		</td>
		<td width="15%" align="right"></td>
		<td width="35%"></td>
	</tr>
	<tr>
	<td align="right"><font color='red'>*</font>指标名称:</td>
	<td colspan="3">
		<input id="tarName" name="newQtyDef.tarName" style="width: 80%" type="text" value="${newQtyDef.tarName}"  disabled="disabled"
		validate="{required:true,minlength:2,maxlength:50,remote:{url:'${_CONTEXT_PATH}/qtydefManager!validTarName.action?opt=upd',type:'post',data:{'newQtyDef.tarName':function(){return $('#tarName').val();},'oldTarName':'${newQtyDef.tarName}'}},messages:{remote:'指标名称重复'}}" />
	</td>
</tr>
<tr>
	<td align="right">指标说明:</td>
	<td colspan="3">
		<input id="tarDesc" name="newQtyDef.tarDesc" size="30" style="width: 80%" type="text" value="${newQtyDef.tarDesc}" />
	</td>
</tr>
<tr>
	<td align="right"><font color='red'>*</font>计量单位:</td>
	<td align="left">
	<n:select codetype="ind_unit" id="measUnitCode" name='newQtyDef.measUnitCode' emptyOption="true" validate="{required:true}" />
	</td>
	<td align="right"><font color='red'>*</font>数据类型:</td>
	<td>
	<n:select codetype="ind_accu" id="measDefCode" name='newQtyDef.measDefCode' emptyOption="true" validate="{required:true}" />
	</td>
	</tr>
	<tr>
	<td align="right"><font color='red'>*</font>指标属性 :</td>
	<td>
		<n:select codetype="ind_type" id="tarProperty" name='newQtyDef.tarProperty' emptyOption="true" validate="{required:true}" />
	</td>
	<td align="right">指标分类:</td>
	<td>
	<input id="tarSortCode" name="newQtyDef.tarSortCode" type="hidden" value="${newQtyDef.tarSortCode}" />
	<input id="tarSortName" name="tarSortName" type="text" value="${newQtyDef.tarSortName}" class="unit_select" onclick="selectTarSort('tarSortName','tarSortCode');" />
	</td>
</tr>
<tr>
	<td align="right"><font color='red'>*</font>保存日期:</td>
	<td>
	<n:select codetype="save_type" id="storeDate" name='newQtyDef.storeDate' emptyOption="true" validate="{required:true}" />
	</td>
	<td align="right"><font color='red'>*</font>适用对象:</td>
	<td>
		<input style="width: 18px;" name="tarScopeCheck" type="checkbox" <n:checked curVal='${newQtyDef.tarScope}' compare='<%=PropDefPo.APPOBJ_ORGCODE%>'/>
			value="<%=PropDefPo.APPOBJ_ORGCODE%>" validate="{required:true,minlength:1}" /><%=PropDefPo.APPOBJ_ORGNAME%>&nbsp;
		<input style="width: 18px;" name="tarScopeCheck" type="checkbox" <n:checked curVal='${newQtyDef.tarScope}' compare='<%=PropDefPo.APPOBJ_PERSONCODE%>'/>
			value="<%=PropDefPo.APPOBJ_PERSONCODE%>" /><%=PropDefPo.APPOBJ_PERSONNAME%>
	</td>
</tr>
<tr>
	<td align="right">产品类型:</td>
	<td>
	<input type=text id="prdtypeName" name="prdtypeName" value="${newQtyDef.prdtypeName}" class="unit_select" />
	<input type="hidden" id="prdtypeCode" name="newQtyDef.prdtypeCode" value="${newQtyDef.prdtypeCode}" />
	</td>
	<td align="right">科目号:</td>
	<td>
	<input type="text" id="subjName" name="subjName" value="${newQtyDef.subjName}" class="unit_select" />
	<input type="hidden" id="subjCode" name="newQtyDef.subjCode" value="${newQtyDef.subjCode}" />
	</td>
</tr>
<tr style="display:none;">
<td align="right"><font color='red'>*</font>业务条线:</td>
	<td>
	<n:select codetype="biz_type" id="tarBizType" name='newQtyDef.tarBizType' emptyOption="true" validate="{required:true}" disabled="true" />
	</td>
<td></td><td></td>
</tr>
<tr>
	<td align="right">备注:</td>
	<td colspan="3">
	<textarea rows="3" cols="40" id="remark" style="width: 80%" name="newQtyDef.remark">${newQtyDef.remark}</textarea>
	</td>
</tr>
<tr>
	<td colspan="4" align="center">
	<input type="button" value="确&nbsp;&nbsp;定" id="saveBtn" onclick="add()" class="l-button" />&nbsp;&nbsp;
	<input type="button" value="取&nbsp;&nbsp;消" id="cancelBtn" class="l-button"/>
	</td>
	</tr>
</table>
</fieldset>
</form>
</body>
</html>