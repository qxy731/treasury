
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.soule.app.pfm.tm.BaseTar"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>衍生指标新增</title>
<jsp:include page="/comm.jsp"></jsp:include>
<script type="text/javascript" src="${_CONTEXT_PATH}/jsp/pfm/tarmgr/qtydef/mixQtydefAddUpd.js"></script>
<style type="text/css">
.params1 {background-color: #E2EAFF; padding:2px 2px 2px 10px;}
.params1 .l-button {width:40px;}
</style>
<script type="text/javascript">
$(function() {
	$('#saveBtn').bind('click', add);
	$('#cancelBtn').bind('click', cancelDialog);
});

</script>
</head>
<body style="overflow:hidden;">
<n:page action='com.soule.app.pfm.tm.qtydef.QtyDefAction' initMethod="doInitBusinessLine"/>
<n:enums keys='ind_unit,ind_accu,save_type,proc_type,tar_type,tar_sort,data_from,tar_property'/>
<form id="insertForm" name="insertForm">
<input id="createOrg" name="newQtyDef.createOrg" type="hidden" value="${logUserInfo.operUnitId}"/>
<input id="tarType" name="newQtyDef.tarType" type="hidden" value="<%=BaseTar.TAR_TYPE_MIX%>"/>
<input id="tarScope" name="newQtyDef.tarScope" type="hidden" value="<%=BaseTar.APPOBJ_ORGANDPERCODE%>"/>
<fieldset class="outbox"><legend>衍生指标新增</legend>
<table class="params" width="100%">
	<tr>
		<td width="15%" align="right">指标代码:</td>
		<td width="35%"><input  id="tarCodeText" name="newQtyDef.tarCodeText" type="text" disabled="disabled"/></td>
		<td width="15%"><font color='red'>*</font>适用对象:</td>
		<td width="35%">
		  <input style="width:14px;height:14px;margin:4px;" name="tarScopeCheck" type="checkbox" value="<%=BaseTar.APPOBJ_ORGCODE%>" checked/><%=BaseTar.APPOBJ_ORGNAME %>&nbsp;
		  <input style="width:14px;height:14px;margin:4px;" name="tarScopeCheck" type="checkbox" value="<%=BaseTar.APPOBJ_PERSONCODE%>" checked/><%=BaseTar.APPOBJ_PERSONNAME %>
		</td>
	</tr>
	<tr>
		<td align="right" ><font color='red'>*</font>指标名称:</td>
		<td colspan="3">
		<input id="tarName" name="newQtyDef.tarName" type="text" style="width:80%" validate="{required:true,minlength:2,maxlength:50
		,remote:{url:'${_CONTEXT_PATH}/qtydefManager/qty-def!validTarName.action',type:'post',data:{'newQtyDef.tarName':function(){return $('#tarName').val();}}},messages:{remote:'指标名称重复'}}"/></td>
	</tr>
	<tr>
		<td align="right">指标说明:</td>
		<td colspan="3"><input id="tarDesc" name="newQtyDef.tarDesc" style="width:80%" size="50" type="text"/></td>
	</tr>
	<tr>
        <td align="right"><font color='red'>*</font>计量单位:</td>
		 <td>
         <n:select codetype="ind_unit" id="measUnitCode" name='newQtyDef.measUnitCode' emptyOption="true" validate="{required:true}"/>
        </td>
		<td align="right"><font color='red'>*</font>计算精确度:</td>
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
		<td align="right"><font color='red'>*</font>保存日期:</td>
		<td>
		  <n:select codetype="save_type" id="storeDate" name='newQtyDef.storeDate' emptyOption="true" validate="{required:true}"/>
		</td>
		<td align="right"><font color='red'>*</font>处理日期:</td>
		<td >
			<n:select codetype="proc_type" id="procDateCode" name='newQtyDef.procDateCode' emptyOption="true" validate="{required:true}"/>
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
		  <textarea rows="3" cols="60" id="remark" name="newQtyDef.remark" style="width:80%"></textarea>
		</td>
   </tr>
</table>
</fieldset>
<fieldset class="outbox"><legend>创建计算公式</legend>
<table class="params1">
  <tr>
  <td>
 <table cellpadding="0" cellspacing="0" border="0">
  <tr>
    <td  align="center" width="20%" >
      <input type="button" value="1" getvalue="1" class="l-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="2" getvalue="2" class="l-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="3" getvalue="3" class="l-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="4" getvalue="4" class="l-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="5" getvalue="5" class="l-button"/>
    </td>
  </tr>
  <tr>
    <td  align="center" width="20%" >
      <input type="button" value="6" getvalue="6" class="l-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="7" getvalue="7" class="l-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="8" getvalue="8" class="l-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="9" getvalue="9" class="l-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="0" getvalue="0" class="l-button"/>
    </td>
  </tr>
  <tr>
    <td  align="center" width="20%" >
      <input type="button" value="." getvalue="." class="l-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="+" getvalue="+" class="l-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="-" getvalue="-" class="l-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="*" getvalue="*" class="l-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="/" getvalue="/" class="l-button"/>
    </td>
  </tr>
  <tr>
    <td  align="center" width="20%" >
      <input type="button" value="(" getvalue="(" class="l-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value=")" getvalue=")" class="l-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="&lt;" getvalue="&lt;" class="l-button"/>
    </td>
    <td  align="center" width="20%" >
    <input type="button" value="&gt;" getvalue="&gt;" class="l-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="=" getvalue="=" class="l-button"/>
    </td>
  </tr>
  <tr>
    <td  align="center" width="20%" >
    <input type="button" value="不小于" getvalue="&gt;=" class="l-button"/>
    </td>
    <td  align="center" width="20%" >
    <input type="button" value="不大于" getvalue="&lt;=" class="l-button"/>
    </td>
    <td  align="center" width="20%" >
      <input type="button" value="或者" getvalue="||" class="l-button"/>
    </td>
    <td  align="center" width="20%" >
     <input type="button" value="并且" getvalue="&&" class="l-button"/>
    </td>
    <td  align="center" width="20%" >
       <input type="button" value="非" getvalue="!" class="l-button"/>
    </td>
  </tr>
   <tr>
    <td  align="center" colspan="5">
		<table width="100%">
	    	<tr>
		    	<td>
		    		<input type="button" value="选择指标" class="l-button" id="selTarBtn" style="margin-top:2px;width:110px;"/>
		    	</td>
		    	<td>
		    		<input type="button" value="选择公式" class="l-button" id="selGsBtn" style="margin-top:2px;width:110px;"/>
		    	</td>
		    	<!-- <td>
		    		<input type="button" value="选择参数" class="l-button" style="margin-top:2px;width:106px;"/>
		    	</td> -->
	    	</tr>
    	</table>
	 </td>
  </tr>
</table>
 </td>
 <td width="100%">
		<textarea rows="4" cols="60" id="createGs" name="qtyExp.calcExp" validate="{required:true}">${qtyExp.calcExp}</textarea>
		<input type="button"" name="resetBtn" id="resetBtn" value="重&nbsp;置" class="l-button" style="width:80px;"/><br/>
		<fieldset class="outbox"><legend><font color="red">复合指标公式说明</font></legend>
				<font color="red">
					<p>月份贷款收息率:#月份贷款实收利息/#月份贷款应收利息 * 100</p> 
					<p>月份黑金客户流失率:#月份黑金客户流失数/#取指标(#月末黑金客户数, 取周期(-1)) * 100</p> 
					<p>新增对公存款季日均:环比增量(#对公存款季日均)</p>
				</font>
		</fieldset>
 </td>
  </tr>
  <tr>
		<td colspan="2">
			<input id="saveBtn" type="button" value="提&nbsp;交" class="l-button" style="float:left;margin-left:230px;margin-top:5px;width:80px;" />
			<input id="cancelBtn" type="button" value="取&nbsp;消" class="l-button" style="float:left;margin-left:5px;margin-top:5px;width:80px;" />
		</td>
	</tr>
</table>
</fieldset>
<!-- <table width="100%">
	<tr>
		<td align="right">
			<input type="button" value="确&nbsp;定" id="saveBtn" class="l-button" style="margin-right:10px;"/>
		</td>
		<td align="left">
			<input type="button" value="取&nbsp;消" id="cancelBtn" class="l-button" style="margin-left:10px;"/>
		</td>
	</tr>
</table> -->
</form>
</body>
</html>