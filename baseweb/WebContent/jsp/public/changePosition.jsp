<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>职位切换</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">

.mparams { margin:30px auto; }
.mparams tr td { text-align: center;}
</style>
</head>
<body>
<n:page action='com.soule.app.sys.changeposi.ChangePosiAction' />

<form id="myform" action="${_CONTEXT_PATH}/ChangePosi!change.action">
<input type='hidden' id='staffId' name='staffId' value='${logUserInfo.user.userID}'/>
<fieldset><legend>可切换职位</legend>
<table class='mparams'>
	<tr>
		<td align="center"><font style="color: red;">*&nbsp;</font><n:select id='posiId' name='posiId' validate='{required:true}' list="positions" listKey="posiId" listValue="%{operUnitname + ': ' + roleName}" value="posiId" emptyOption="true"></n:select></td>
	</tr>
</table>
<table style='width:100%;margin:20px auto;'>
	<tr>
		<td align="center"><input id='change' name='change' type='button' class='l-button' value='切&nbsp;&nbsp;换'/></td>
	</tr>
</table>
</fieldset>
</form>
</body>
<script type='text/javascript'>
$(function () {
	Utils.validateInit();
	$('#change').bind('click', execute);
});

function execute() {
	if ( $("#myform").valid() ) {
		$.dialogBox.alert('页面将刷新，请保存当前工作内容，确定要切换吗?',
		function () {doChange();},true);
	}
}

function doChange() {
	//单记录数据
	var mdata = Utils.convertFormData('changeIn','myform');
	var url = "${_CONTEXT_PATH}/ChangePosi!change.action";
	Utils.ajaxSubmit(url,mdata,function(result) {
		parent.location = "${_CONTEXT_PATH}/jsp/public/autologin.jsp";
	});
}

</script>
</html>