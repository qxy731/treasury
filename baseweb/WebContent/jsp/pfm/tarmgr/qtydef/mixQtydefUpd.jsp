<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.soule.app.pfm.tm.propdef.PropDefPo,com.soule.app.pfm.tm.qtydef.QtyDefPo"%>
<%@ page import="com.soule.app.pfm.tm.BaseTar"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>复合定量指标修改</title>
<jsp:include page="/comm.jsp"></jsp:include>
<script type="text/javascript" src="${_CONTEXT_PATH}/jsp/pfm/tarmgr/qtydef/mixQtydefAddUpd.js"></script>
<style type="text/css">
	body{margin:0 30px;width:850px;}
	.params1 {background-color: #E2EAFF; padding:2px 2px 2px 10px;}
</style>
<script type="text/javascript">
	var orgPersonCode="<%=PropDefPo.APPOBJ_ORGANDPERCODE%>";
	$(function (){
		initTarscopeCheckBox();
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
	$(function (){
		$("#cancelBtn").click(function(){
			Utils.removeTabById("updateMixQtyTar");
		});
	});
</script>
</head>
<body>
<n:page action='com.soule.app.pfm.tm.qtydef.QtyDefAction'/>
<n:enums keys='ind_unit,ind_accu,ind_type,biz_type,save_type,proc_type'/>
<form id="insertForm" name="insertForm">
<input id="tarCode" name="newQtyDef.tarCode" type="hidden" value="${param.tarCode}"/>
<input id="tarScope" name="newQtyDef.tarScope" type="hidden" value="${newQtyDef.tarScope}"/>
<input id="tarType" name="newQtyDef.tarType" type="hidden" value="<%=QtyDefPo.TAR_TYPE_MIX%>"/>
<fieldset class="detailList"><legend>指标信息</legend>
<table class="params">
	<tr>
		<td width="10%" align="right">指标代码</td>
		<td width="40%"><input  id="tarCodeText" name="newQtyDef.tarCodeText" type="text" disabled="disabled" value="${param.tarCode}"/></td>
		<td align="right" width="10%"><font color='red'>*</font>适用对象</td>
		<td width="40%">
		  <input name="tarScopeCheck" type="checkbox" style="width: 18px;" <n:checked curVal='${newQtyDef.tarScope}' compare='<%=BaseTar.APPOBJ_ORGCODE%>'/> value="<%=BaseTar.APPOBJ_ORGCODE%>"/><%=BaseTar.APPOBJ_ORGNAME %>&nbsp;
		  <input name="tarScopeCheck" type="checkbox" style="width: 18px;" <n:checked curVal='${newQtyDef.tarScope}' compare='<%=BaseTar.APPOBJ_PERSONCODE%>'/> value="<%=BaseTar.APPOBJ_PERSONCODE%>"/><%=BaseTar.APPOBJ_PERSONNAME %>
		</td>
	</tr>
	<tr>
		<td align="right" ><font color='red'>*</font>指标名称</td>
		<td colspan="3">
		<input id="tarName" name="newQtyDef.tarName" type="text" style="width:80%" value="${newQtyDef.tarName}" disabled="disabled" validate="{required:true,minlength:2,maxlength:50
		,remote:{url:'${_CONTEXT_PATH}/qtydefManager!validTarName.action?opt=upd',type:'post',data:{'newQtyDef.tarName':function(){return $('#tarName').val();},'oldTarName':'${newQtyDef.tarName}'}},messages:{remote:'指标名称重复'}}" /></td>
	</tr>
	<tr>
		<td align="right">指标说明</td>
		<td colspan="3"><input id="tarDesc" name="newQtyDef.tarDesc" style="width:80%" size="50" type="text" value="${newQtyDef.tarDesc}"/></td>
	</tr>
	<tr>
        <td align="right"><font color='red'>*</font>计量单位</td>
		 <td>
         <n:select codetype="ind_unit" id="measUnitCode" name='newQtyDef.measUnitCode' emptyOption="true" validate="{required:true}"/>
        </td>
		<td align="right"><font color='red'>*</font>数据业型</td>
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
		<td><input id="tarSortName" name="tarSortName" type="text" value="${newQtyDef.tarSortName}" class="unit_select" onclick="selectTarSort('tarSortName','tarSortCode')"/>
		  <input id="tarSortCode" name="newQtyDef.tarSortCode" type="hidden" value="${newQtyDef.tarSortCode}"/>
		</td>
	</tr>
	<tr>
		<td align="right"><font color='red'>*</font>保存日期</td>
		<td>
		  <n:select codetype="save_type" id="storeDate" name='newQtyDef.storeDate' emptyOption="true" validate="{required:true}" />
		</td>
		<td align="right"><font color='red'>*</font>处理日期</td>
		<td>
			<n:select codetype="proc_type" id="procDateCode" name='newQtyDef.procDateCode' emptyOption="true" validate="{required:true}" />
			<input type="text" name="otherProcDate" id="otherProcDate" style="display:none;" value="${newQtyDef.tarProcDate}"/>
		</td>
   </tr>
   <tr>
		<td align="right">产品类型</td>
		<td>
		  <input type="text" id="prdtypeName" name="newQtyDef.prdtypeName" value="${newQtyDef.prdtypeName}" readonly="readonly" class="unit_select"/>
		  <input type="hidden" id="prdtypeCode" name="newQtyDef.prdtypeCode" value="${newQtyDef.prdtypeCode}"/>
		</td>
		<td align="right">科目号</td>
		<td>
		  <input type="text" id="subjName" name="newQtyDef.subjName" readonly="readonly" value="${newQtyDef.subjName}" class="unit_select">
		  <input type="hidden" id="subjCode" name="newQtyDef.subjCode" value="${newQtyDef.subjCode}" />
		</td>
   </tr>
   <tr style="display:none;">
		<td align="right"><font color='red'>*</font>业务条线</td>
		<td>
		<n:select codetype="biz_type" id="tarBizType" name='newQtyDef.tarBizType' emptyOption="true" validate="{required:true}" disabled="true" value="${newQtyDef.tarBizType}" />
	    </td>
		<td align="right"></td>
		<td></td>
   </tr>
	<tr>
		<td align="right">备注</td>
		<td colspan="3">
		  <textarea rows="3" cols="60" id="remark" name="newQtyDef.remark" style="width:80%">${newQtyDef.remark}</textarea>
		</td>
	</tr>
</table>
</fieldset>
<fieldset class="detailList"><legend>创建计算公式</legend>
<table class="params1">
  <tr>
  <td>
 <table cellpadding="0" cellspacing="0" border="0">
  <tr>
    <td  align="center" width="20%" >
      <input type="button" value="1" getvalue="1" class="m-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="2" getvalue="2" class="m-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="3" getvalue="3" class="m-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="4" getvalue="4" class="m-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="5" getvalue="5" class="m-button"/>
    </td>
  </tr>
  <tr>
    <td  align="center" width="20%" >
      <input type="button" value="6" getvalue="6" class="m-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="7" getvalue="7" class="m-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="8" getvalue="8" class="m-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="9" getvalue="9" class="m-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="0" getvalue="0" class="m-button"/>
    </td>
  </tr>
  <tr>
    <td  align="center" width="20%" >
      <input type="button" value="." getvalue="." class="m-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="+" getvalue="+" class="m-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="-" getvalue="-" class="m-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="*" getvalue="*" class="m-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="/" getvalue="/" class="m-button"/>
    </td>
  </tr>
  <tr>
    <td  align="center" width="20%" >
      <input type="button" value="(" getvalue="(" class="m-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value=")" getvalue=")" class="m-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="&lt;" getvalue="&lt;" class="m-button"/>
    </td>
    <td  align="center" width="20%" >
    <input type="button" value="&gt;" getvalue="&gt;" class="m-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="=" getvalue="=" class="m-button"/>
    </td>
  </tr>
  <tr>
    <td  align="center" width="20%" >
    <input type="button" value="不小于" getvalue="&gt;=" class="m-button"/>
    </td>
    <td  align="center" width="20%" >
    <input type="button" value="不大于" getvalue="&lt;=" class="m-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="或者" getvalue="或者" class="m-button"/>
    </td>
    <td  align="center" width="20%" >
     <input type="button" value="并且" getvalue="&&" class="m-button"/>
    </td>
    <td  align="center" width="20%" >
       <input type="button" value="非" getvalue="!" class="m-button"/>
    </td>
  </tr>
 <tr>
    <td  align="center" colspan="5">
		<table width="100%">
	    	<tr>
		    	<!-- <td>
		    		<input type="button" value="取&nbsp;余" getvalue="%" class="m-button" style="margin-top:2px;width:70px;"/>
		    	</td> -->
		    	<td>
		    		<input type="button" value="选择指标" class="m-button" id="selTarBtn" style="margin-top:2px;width:110px;"/>
		    	</td>
		    	<td>
		    		<input type="button" value="选择公式" class="m-button" id="selGsBtn" style="margin-top:2px;width:110px;"/>
		    	</td>
		    	<!-- <td>
		    		<input type="button" value="选择参数" class="m-button" style="margin-top:2px;width:106px;"/>
		    	</td> -->
	    	</tr>
    	</table>
	 </td>
  </tr>
  <!-- <tr>
    <td  align="center" colspan="5">
    	<table width="100%">
	    	<tr>
		    	<td>
		    	<input type="button" value="选择指标" class="m-button" id="selTarBtn" style="margin-top:2px;width:106px;"/>
		    	</td>
		    	<td>
		    	<input type="button" value="选择公式" class="m-button" id="selGsBtn" style="margin-top:2px;width:106px;"/>
		    	</td>
	    	</tr>
    	</table>
    </td>
  </tr> -->
</table>
 </td>
 <td width="100%">
        <textarea rows="4" cols="60" id="createGs" name="qtyExp.calcExp" validate="{required:true}">${qtyExp.calcExp}</textarea><input type="button"" name="resetBtn" id="resetBtn" value="重&nbsp;&nbsp;置" class="l-button"/><br/>
        <fieldset class="detailList"><legend><font color="red">复合指标公式说明</font></legend>
	        	<font color="red">
		             <p>月份贷款收息率:#月份贷款实收利息/#月份贷款应收利息 * 100</p> 
		             <p>月份黑金客户流失率:#月份黑金客户流失数/#取指标(#月末黑金客户数, 取周期(-1)) * 100</p> 
		             <p>新增对公存款季日均:环比增量(#对公存款季日均)</p>
	            </font>
        </fieldset>
 </td>
</tr>
</table>
</fieldset>
<table width="100%" border="0">
<tr><td align="center">
	<input type="button" value="确&nbsp;&nbsp;定" id="saveBtn" class="l-button" onclick="update();"/>&nbsp;
	<input type="button" value="取&nbsp;&nbsp;消" id="cancelBtn" class="l-button"/></td></tr>
</table>
</form>
</body>
</html>