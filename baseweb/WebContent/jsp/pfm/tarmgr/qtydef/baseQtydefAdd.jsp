<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.soule.app.pfm.tm.propdef.PropDefPo,com.soule.app.pfm.tm.qtydef.QtyDefPo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>基础定量指标新增</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
	body{margin:0 30px;width:850px;}
</style>
<script type="text/javascript">
$(function (){	
	$("input[name='dayScopeList']").click(ischeckedDayScope);
	$("#tarProperty").change(cancelDayScopeChecked);

	$("#cancelBtn").click(function(){
		Utils.removeTabById("insertBaseQtyTar");
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
			      title:'科目号',
			      lock:true,
			      opacity:0.07,
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
					//if(result.retCode.substr(0,1)=='I'){
						//$.dialogBox.info('添加成功', '提示',function(){
					if(Utils.isSuccess(result)){
						$("#tarCodeText").val(result.tarCode);//添加成功后显示指标代码
						$.dialogBox.info(result.retMsg);
					}else{
						/* $.dialogBox.error('添加失败,数据库发生错误', '提示',function(){
							Utils.removeTabById("insertBaseQtyTar");
						}); */
						$("#saveBtn").attr("disabled",false);
						$.dialogBox.error(result.retMsg);
					}
				},error:function(result){
					/* $.dialogBox.error('添加失败,网络发生错误', '提示',function(){
						Utils.removeTabById("insertBaseQtyTar");
					}); */
					$("#saveBtn").attr("disabled",false);
					$.dialogBox.error("添加失败。");
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
function ischeckedDayScope(){
	var tarProperty=$("#tarProperty").val();
	if("<%=QtyDefPo.IND_TYPE_HOURS_MONEY%>"!=tarProperty){
	   $("input[name='dayScopeList']").each(function(){
		   if(this.checked){
			   this.checked=false;
			}
	   });
	}
}
function cancelDayScopeChecked(){
	var tarProperty=$(this).val();
	if("<%=QtyDefPo.IND_TYPE_HOURS_MONEY%>"!=tarProperty){
		$("input[name='dayScopeList']:checked").attr("checked",false);
	}
}
function validateProcOther(){
	var procDateCode=$("#procDateCode").val().trim();
    //"OT"表示处理日期
	if("OT"==procDateCode){
		var otherValue=$("#otherProcDate").val().trim();
		if(otherValue.length<1){
			$.dialogBox.warn('处理日期为其他时请输入处理日期','提示',function (){
				$("#otherProcDate").focus();
			});
			return false;
		}else{
			var first=otherValue.charAt(0);
			if("YQMyqm".indexOf(first)==-1){
				$.dialogBox.warn('处理日期必须以"y、q、m"三个字母中的任意一个开头','提示',function (){
					$("#otherProcDate").focus();
				});
				return false;
			}else{
				if("y"==first||"Y"==first){
					var year=otherValue.substring(1,3);alert(year);
					if(!isNaN(year)){
						$.dialogBox.warn('输入格式不正确','提示',function (){
							$("#otherProcDate").focus();
						});
						return false;
					}
					var mon=otherValue.substring(1,3);alert(mon);
				}
			}
	    }
    }
}

$(function() {
	Utils.validateInit();
});
</script>
</head>
<body>
<n:page action='com.soule.app.pfm.tm.qtydef.QtyDefAction' initMethod="doInitBusinessLine"/>
<n:enums keys='ind_unit,ind_accu,ind_type,save_type,proc_type,biz_type'/>
<form id="insertForm" name="insertForm">
<input id="tarScope" name="newQtyDef.tarScope" type="hidden"/>
<input id="createOrg" name="newQtyDef.createOrg" type="hidden" value="${logUserInfo.operUnitId}"/>
<input id="tarType" name="newQtyDef.tarType" type="hidden" value="<%=QtyDefPo.TAR_TYPE_BASE%>"/>
<fieldset class="detailList"><legend>新增基础定量指标</legend>
<table class="params">
	<tr>
		<td width="15%" align="right">指标代码</td>
		<td width="35%"><input  id="tarCodeText" name="newQtyDef.tarCodeText" type="text" disabled="disabled"/></td>
		<td width="15%" ></td>
		<td width="35%" ></td>
	</tr>
	<tr>
		<td align="right"><font color='red'>*</font>指标名称</td>
		<td colspan="3"><input id="tarName" name="newQtyDef.tarName" type="text" style="width:80%;" validate="{required:true,minlength:2,maxlength:50
		,remote:{url:'${_CONTEXT_PATH}/qtydefManager!validTarName.action',type:'post',data:{'newQtyDef.tarName':function(){return $('#tarName').val();}}},messages:{remote:'指标名称重复'}}"/></td>
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
		<td align="right"><font color='red'>*</font>指标属性</td>
		<td>
         <n:select codetype="ind_type" id="tarProperty" name='newQtyDef.tarProperty' emptyOption="true" validate="{required:true}"/>
		</td>
		<td align="right">指标分类</td>
		<td>
		 <input type="text" id="tarSortName" name="newQtyDef.tarSortName" readonly="readonly" onclick="selectTarSort('tarSortName','tarSortCode')" class="unit_select"/>
		 <input type="hidden" id="tarSortCode" name="newQtyDef.tarSortCode">
		</td>
	</tr>
	<tr>
		<td align="right">日均范围</td>
		<td>
		 <input type="checkbox" name="dayScopeList" value='<%=QtyDefPo.AVER_SCOPE_Y%>' style="width: 20px;" />年
		 <input type="checkbox" name="dayScopeList" value="<%=QtyDefPo.AVER_SCOPE_Q%>" style="width: 20px;"/>季
		 <input type="checkbox" name="dayScopeList" value="<%=QtyDefPo.AVER_SCOPE_M%>" style="width: 20px;"/>月
		</td>
		<td align="right"><font color='red'>*</font>适用对象</td>
		<td>
		  <input name="tarScopeCheck" validate="{required:true}" type="checkbox" value="<%=PropDefPo.APPOBJ_ORGCODE%>" style="width: 20px;"/><%=PropDefPo.APPOBJ_ORGNAME %>&nbsp;
		  <input name="tarScopeCheck" type="checkbox" value="<%=PropDefPo.APPOBJ_PERSONCODE%>" style="width: 20px;"/><%=PropDefPo.APPOBJ_PERSONNAME %>
		</td>
	</tr>
	<tr>
		<td align="right"><font color='red'>*</font>保存日期</td>
		<td>
		  <n:select codetype="save_type" id="storeDate" name='newQtyDef.storeDate' emptyOption="true" validate="{required:true}"/>
		</td>
		<td align="right"><font color='red'>*</font>处理日期</td>
		<td>
		  <n:select codetype="proc_type" id="procDateCode" name='newQtyDef.procDateCode' emptyOption="true" validate="{required:true}"/><div id="otherProcDateDiv" style="display: none;"><input type="text" name="newQtyDef.tarProcDate" id="otherProcDate"/>(处理日期为其他时必填)</div>
		</td>
   </tr>
	<tr>
	   <td align="right">产品类型</td>
		<td>
		  <input type="text" id="prdtypeName" name="newQtyDef.prdtypeName" readonly="readonly" class="unit_select"/>
		  <input type="hidden" id="prdtypeCode" name="newQtyDef.prdtypeCode"/>
		</td>
		<td align="right">科目号</td>
		<td colspan="3">
		 <input type="text" id="subjName" name="newQtyDef.subjName" readonly="readonly" class="unit_select" >
		 <input type="hidden" id="subjCode" name="newQtyDef.subjCode">
		</td>
	</tr>
	 <tr>
		<td align="right">备注</td>
		<td colspan="3">
		  <textarea rows="3" cols="40" style="width:80%" id="remark" name="newQtyDef.remark"></textarea>
		</td>
	</tr>
 	<tr  style="display:none;">
		 <td align="right"><font color='red'>*</font>业务条线</td>
		<td>
		<n:select codetype="biz_type" id="tarBizType" name='newQtyDef.tarBizType' emptyOption="true" validate="{required:true}" disabled="true" value="#attr.newQtyDef.tarBizType" />
	   </td>
		<td align="right"> 
	                是否存在基数指标
	    </td>
	   <td>
	    <input type="radio" name="newQtyDef.bsFlag" value="<%=QtyDefPo.BSFLAG_YES%>" style="width: 20px;"/>是<input type="radio" name="newQtyDef.bsFlag" value="<%=QtyDefPo.BSFLAG_NO%>" style="width: 20px;"/>否
	   </td>
    </tr>
	<tr>	 
	  <td colspan="4" align="center">
	  	<input type="button" value="确&nbsp;&nbsp;定" id="saveBtn" onclick="add()" class="l-button" />&nbsp;&nbsp;
	   	<input type="button" value="取&nbsp;&nbsp;消 " id="cancelBtn" class="l-button"/>
	  </td>
	</tr>
</table>
</fieldset>
</form>
</body>
</html>