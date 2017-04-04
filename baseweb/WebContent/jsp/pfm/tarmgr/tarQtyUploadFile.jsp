<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.soule.app.pfm.tm.propdef.PropDefPo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>定量指标批量导入</title>
<%@ include file="/comm.jsp" %> 
<style type="text/css">
 /*  body{
    margin-left: 15px;
  } */
</style>
<script type="text/javascript">
var resultFlag='${resultFlag}';
$(function (){
	$("#cancelBtn").click(function(){
		Utils.removeTabById("tarQtyUploadFile");
	});
	if('success'==resultFlag){
		$.dialogBox.info("批量导入指标成功","信息",function(){
			Utils.removeTabById("tarQtyUploadFile");
			});
	}else if('error'==resultFlag){
		$.dialogBox.error("批量导入指标失败","信息",true);
	}
});
</script>


</head>

<body>
<table class="content">
<tr>
<td>
<fieldset class="detailList"><legend>定量指标导入</legend>
<form id="insertForm" action="${_CONTEXT_PATH}/tarQtyImport.action" method="post" enctype="multipart/form-data">
<table class="params">
	<tr>
		<td align="right">类型</td>
		<td>
		  <select name="aa" disabled="disabled">
		    <option value="1">定量</option>
		    <option value="2">定性</option>
		  </select>
		</td>
	</tr>
	<tr>
		<td align="right"><font color="red">*</font>导入文件</td>
		<td><input name="upload" type="file" style="height:22px;width:500px;"/></td>
	</tr>
	<tr>
	  <td colspan="2" align="center">
	    <input type="submit" value="确&nbsp;&nbsp;定" id="saveBtn" class="l-button"/>
	    <input type="button" value="取&nbsp;&nbsp;消 " id="cancelBtn" class="l-button"/>
	  </td>
	</tr>
</table>
</form>
</fieldset>
</td>
</tr>
</table>
</body>
</html>