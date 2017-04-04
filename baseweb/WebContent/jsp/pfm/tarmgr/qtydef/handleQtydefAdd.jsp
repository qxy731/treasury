<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.soule.app.pfm.tm.propdef.PropDefPo,com.soule.app.pfm.tm.qtydef.QtyDefPo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>手工定量指标新增</title>
<script type="text/javascript">
</script>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
	body{margin:0 30px;width:850px;}
</style>
<script type="text/javascript">
function add(){
	if(validate()){
		try{
			$("#tarScope").val(getTarScope());
			$("#saveBtn").attr("disabled",true);
			var url = "${_CONTEXT_PATH}/qtydefManager!insert.action";
			var data=Utils.convertFormData('',"insertForm");
			$.ajax({
				url: url,
				type:'post',
				data: data,
				dataType:'json',
				contentType:"application/x-www-form-urlencoded; charset=utf-8", 
				success: function(result){
					if(Utils.isSuccess(result)){
						$("#tarCodeText").val(result.tarCode);//添加成功后显示指标代码
						$.dialogBox.info(result.retMsg);
					}else{
						$("#saveBtn").attr("disabled",false);
						$.dialogBox.error(result.retMsg);
					}
				},error:function(result){
					$.dialogBox.error('添加失败。');
				}
			});
		}catch(e){}
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
$(function() {
	Utils.validateInit();
	
    $("#cancelBtn").click(function(){
		Utils.removeTabById("insertHandleQtyTar");
	});

    $("#prdtypeName").click(function(){
		 var url="${_CONTEXT_PATH}/jsp/common/pub_productTree.jsp";
		　$.dialog.open(url,
				{
			      id:"prdDailog",
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
</script>
</head>
<body>
<n:page action='com.soule.app.pfm.tm.qtydef.QtyDefAction' initMethod="doInitBusinessLine"/>
<n:enums keys='ind_unit,ind_accu,ind_type,save_type,biz_type'/>
<fieldset class="detailList"><legend>新增手工定量指标</legend>
<form id="insertForm" name="insertForm">
<input id="tarType" name="newQtyDef.tarType" type="hidden" value="<%=QtyDefPo.TAR_TYPE_HANDLE%>"/>
<input id="tarScope" name="newQtyDef.tarScope" type="hidden" />
<input id="tarSortCode" name="newQtyDef.tarSortCode" type="hidden"/>
<input id="createOrg" name="newQtyDef.createOrg" type="hidden" value="${logUserInfo.operUnitId}"/>
<table class="params" width="100%">
	<tr>
		<td align="right" width="15%">指标代码: </td>
		<td width="35%"><input  id="tarCodeText" name="newQtyDef.tarCodeText" type="text" disabled="disabled"/></td>
		<td width="15%" align="right"></td>
		<td width="35%"></td>
	</tr>
	<tr>
		<td align="right"><font color='red'>*</font>指标名称:</td>
		<td colspan="3"><input id="tarName" name="newQtyDef.tarName" type="text" style="width:80%;" validate="{required:true,minlength:2,maxlength:50
		,remote:{url:'${_CONTEXT_PATH}/qtydefManager!validTarName.action',type:'post',data:{'newQtyDef.tarName':function(){return $('#tarName').val();}}},messages:{remote:'指标名称重复'}}"/></td>
	</tr>
	<tr>
		<td align="right">指标说明:</td>
		<td colspan="3"><input id="tarDesc" name="newQtyDef.tarDesc" style="width:80%;" size="30" type="text"/></td>
	</tr>
	<tr>
		<td align="right"><font color='red'>*</font>计量单位:</td>
		 <td align="left">
         <n:select codetype="ind_unit" id="measUnitCode" name='newQtyDef.measUnitCode' emptyOption="true" validate="{required:true}"/>
        </td>
		<td align="right"><font color='red'>*</font>数据类型:</td>
		<td>
          <n:select codetype="ind_accu" id="measDefCode" name='newQtyDef.measDefCode' emptyOption="true" validate="{required:true}"/>
		</td>
	</tr>
	<tr>
		<td align="right"><font color='red'>*</font>指标属性: </td>
		<td>
         <n:select codetype="ind_type" id="tarProperty" name='newQtyDef.tarProperty' emptyOption="true" validate="{required:true}"/>
		</td>
		<td align="right">指标分类: </td>
		<td><input id="tarSortName" name="tarSortName" type="text" class="unit_select" onclick="selectTarSort('tarSortName','tarSortCode');"/>
		</td>
	</tr>
   <tr>
		<td align="right"><font color='red'>*</font>保存日期:</td>
		<td>
		  <n:select codetype="save_type" id="storeDate" name='newQtyDef.storeDate' emptyOption="true" validate="{required:true}"/>
		</td>
		<td align="right"><font color='red'>*</font>适用对象:</td>
		<td>
		  <input name="tarScopeCheck" type="checkbox" value="<%=PropDefPo.APPOBJ_ORGCODE%>" style="width: 18px;" validate="{required:true,minlength:1}"/><%=PropDefPo.APPOBJ_ORGNAME %>&nbsp;
		  <input name="tarScopeCheck" type="checkbox" value="<%=PropDefPo.APPOBJ_PERSONCODE%>" style="width: 18px;"/><%=PropDefPo.APPOBJ_PERSONNAME %><span></span>
		</td>
	</tr>
   <tr>
		<td align="right">产品类型:</td>
		<td>
		 <input type="text" id="prdtypeName" name="newQtyDef.prdtypeName" readonly="readonly" class="unit_select"/>
		 <input type="hidden" id="prdtypeCode" name="newQtyDef.prdtypeCode"/>
		</td>
		<td align="right">科目号:</td>
		<td>
		 <input type="text" id="subjName" name="newQtyDef.subjName" readonly="readonly" class="unit_select">
		 <input type="hidden" id="subjCode" name="newQtyDef.subjCode">
		</td>
   </tr>
   <tr style="display:none;">
   	   <td align="right"><font color='red'>*</font>业务条线: </td>
	   <td>
		<n:select codetype="biz_type" id="tarBizType" name='newQtyDef.tarBizType' emptyOption="true" validate="{required:true}" disabled="true" value="#attr.newQtyDef.tarBizType"/>
	   </td>
	   <td></td><td></td>
   </tr>
   <tr>
		<td align="right">备注:</td>
		<td colspan="3">
		  <textarea rows="3" cols="40" style="width:80%;" id="remark" name="newQtyDef.remark"></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="4" align="center">
		  <input type="button" value="确&nbsp;&nbsp;定" id="saveBtn" onclick="add();" class="l-button" />&nbsp;&nbsp;
		  <input type="button" value="取&nbsp;&nbsp;消" id="cancelBtn" class="l-button" />
		</td>
	</tr>
</table>
</form>
</fieldset>
</body>
</html>