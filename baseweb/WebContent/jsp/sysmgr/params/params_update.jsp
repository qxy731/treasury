<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>参数配置</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.inbox ,.outbox {padding: 5px;margin: 2px;border: 1px solid #ccc;}
.params td {background-color: #E2EAFF; padding:2px 2px 2px 10px;}
.fclass {color:#FF0000}
.div_bottom {margin-top: 10px}
#reset {width: 80px;}
.table_btn td{padding: 1px 20px 1px 20px}
body {
	margin-right: 10px
}
</style>
</head>
<body>
<n:page action='com.soule.app.sys.params.ParamsAction' initMethod="detailInit"/>
<n:enums keys='para_rank'/>
<table class='content'>
<tr><td>
<form id="myform" action="${_CONTEXT_PATH}/Params!update.action">
<fieldset class="queryBox"><legend>输入条件</legend>
<table class='params'>
	<tr>
		<td align="right"><font class='fclass'>*&nbsp;</font>ID</td><td><input type='text' id='paraId' name='params.paraId' value='${paramsPo.paraId}' readonly="readonly" disabled="disabled"/></td>
		<td align="right"><font class='fclass'>*&nbsp;</font>分级</td><td><n:select codetype="para_rank" id="paraRank" name='params.paraRank' emptyOption="true" validate="{required:true}" value='paramsPo.paraRank'></n:select></td>
	</tr>
	<tr>
		<td align="right"><font class='fclass'>*&nbsp;</font>值</td><td><input type='text' id='paraValue' name='params.paraValue' value='${paramsPo.paraValue}' validate='{required:true,maxlength:255}' /></td>
		<td align="right"><font class='fclass'>*&nbsp;</font>说明</td><td><input type='text' id='remark' name='params.remark' value='${paramsPo.remark}' validate='{required:true,maxlength:512}' /></td>
	</tr>
</table>
</fieldset>
</form>
</td></tr>
</table>
<div align="center" class="div_bottom">
   <table class="table_btn">
      <tr>
         <td><input id="update" name="update" type="button" value="确&nbsp;定" class="l-button"></td>
         <td><input id="reset" name="reset" type="button" value="重&nbsp;置" class="l-button"></td>
      </tr>
   </table>
</div>
<!--<div align="center" class="div_bottom">
    <input id="update" name="update" type="button" value="确&nbsp;定" class="l-button">
    <input id="reset" name="reset" type="button" value="重&nbsp;置" class="l-button"> 
</div>
-->
</body>
<script type='text/javascript'>
$(function () {
	Utils.validateInit();
	$('#reset').bind('click', doClear);
	$('#update').bind('click', execute);

});
function doClear() {
	$(".inbox input[type='text']").each(function(i,item){
		item.value ='';
	});
	$('#paraRank').val("");
	$('#paraValue').val("");
	$('#remark').val("");
}

function execute() {
	if (!$('#myform').valid()){
		return;
	}
	//单记录数据
	var mdata = Utils.convertFormData('updateIn','myform');

	var url = "${_CONTEXT_PATH}/sys/params!update.action";
	Utils.ajaxSubmit(url, mdata, function(result) {
		if (result.retMsg) {
			$.dialogBox.info('修改成功',function() {$.dialogBox.opener.execute();//子页面调用父页面方法
			$.dialog.close();} ); 
		}
	});
}

</script>
</html>