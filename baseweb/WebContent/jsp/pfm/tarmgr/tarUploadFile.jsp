<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.soule.app.pfm.tm.propdef.PropDefPo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>定性指标批量导入</title>
<%@ include file="/comm.jsp" %> 
<style type="text/css">
	body {margin: 5px;}
</style>
<script type="text/javascript">
var resultFlag='${resultFlag}';
$(function (){
	$("#cancelBtn").click(function(){
		Utils.removeTabById("tarUploadFile");
	});
	if('success'==resultFlag){
		$.dialogBox.info("批量导入指标成功","信息",function(){
			Utils.removeTabById("tarUploadFile");
			});
	}else if('error'==resultFlag){
		$.dialogBox.error("批量导入指标失败","信息",true);
	}
});
</script>


</head>

<body>
<fieldset style="padding: 5px;margin:15; border: 1px solid #ddd;margin-bottom:5px;border-radius:4px 4px 4px 4px;height: 350px;"><legend>定性指标导入</legend>
<form id="insertForm" action="${_CONTEXT_PATH}/tarImport.action" method="post" enctype="multipart/form-data">
<table style="margin: 15px;" width="96%">
	<tr>
		<td align="right">类型 :</td>
		<td>
		  <select name="aa" disabled="disabled">
		    <option value="2">定性</option>
		    <option value="1">定量</option>
		  </select>
		</td>
	</tr>
	<tr>
		<td align="right"><font color="red">*</font>导入文件 :</td>
		<td><input name="upload" type="file" /></td>
	</tr>
	<tr>
	  <td>&nbsp;</td>
	  <td>
	    <input type="submit" value="确&nbsp;&nbsp;定" id="saveBtn" class="l-button" style="margin: 0;padding: 0;"/>&nbsp;&nbsp;<input type="button" value="取&nbsp;&nbsp;消 " id="cancelBtn" class="l-button" style="margin: 0;padding: 0;"/>
	  </td>
	</tr>
</table>
</form>
</fieldset>
</body>
</html>