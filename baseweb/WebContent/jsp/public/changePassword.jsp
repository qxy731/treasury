<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>密码修改</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">

.mparams { margin:20px auto; }
.mparams tr td { line-height: 28px;}
.mparams tr td input {  height: 20px;}
fieldset legend {
    background: url("${_CONTEXT_PATH}/images/password.gif") no-repeat scroll 0 0 transparent;
    height: 16px;
    padding-left: 20px;
}
#level1,#level2,#level3 {padding:3px 14px;width:40px;border:1px solid #aaa;background-color: #eee;}
</style>
</head>
<body>
<n:page action='com.soule.app.sys.changeposi.ChangePosiAction' />

<form id="myform" action="${_CONTEXT_PATH}/ChangePosi!change.action">
<input type='hidden' id='staffId' name='staffId' value='${logUserInfo.user.userID}'/>
<fieldset><legend>密码修改</legend>
<table class="mparams">
	<tr>
		<td align="right"><font style="color:red">*</font>原密码&nbsp;</td>
		<td><input id="oldPassword" name="oldPassword" type="password" validate='{required:true}'/></td>
	</tr>
	<tr>
		<td align="right"><font style="color:red">*</font>新密码&nbsp;</td>
		<td><input id="newPassword" name="newPassword" type="password" validate='{required:true}'  onkeyup="caculate(this)"/></td>
	</tr>
	<tr>
		<td align="right"><font style="color:red">*</font>重复新密码&nbsp;</td>
		<td><input id="newPassword_check" name="newPassword_check" type="password" validate='{required:true}'/></td></td>
	</tr>
	<tr>
		<td align="right">密码强度&nbsp;</td>
		<td><span id="level1">低</span><span id="level2">中</span><span id="level3">高</span></td>
	</tr>
</table>
<table style='width:100%;margin:20px auto;'>
	<tr>
		<td align="center"><input id='change' name='change' type='button' class='l-button' value='修&nbsp;改'/></td>
	</tr>
</table>
</fieldset>
</form>
</body>
<script type='text/javascript'>
$(function () {
	Utils.validateInit();
	$('#change').bind('click', execute);
	$('#oldPassword').val('');
});

function execute() {
	if ( $("#myform").valid() ) {
		if (checkPwd()) {
			$.dialogBox.alert('请牢记新密码，确定修改吗?',
			function () {doChange();},true);
		}
	}
}

function checkPwd()
{
	var oldpass = $('#oldPassword').val();
	var newpass = $('#newPassword').val();
	var newpass_check = $('#newPassword_check').val();
	if (oldpass == newpass) {
		$.dialogBox.alert("新密码不能与原密码一致！");
		return false;
	}
	if (newpass.length < 6) {
		$.dialogBox.alert("新密码长度必须大于六位！");
		return false;
	}
	if (newpass_check != newpass) {
		$.dialogBox.alert("您输入的两次新密码不一致！");
		return false;
	}
	return true;
}
function doChange() {
	var mdata = Utils.convertFormData('','myform');
	var url = "${_CONTEXT_PATH}/logonManager!updateLogonPwd.action";
	Utils.ajaxSubmit(url,mdata,function(result){
		$.dialogBox.info(result.retMsg,function(){
			$.dialogBox.close();
		});
	});
}
function caculate(self)
{
	var newpass = $('#newPassword').val();
	$('#level1').css("background-color","#eee");
	$('#level2').css("background-color","#eee");
	$('#level3').css("background-color","#eee");
	if (newpass.length < 6) {
		$('#level1').css("background-color","#FFA9A9");
	} else {
		var p1 = (newpass.search(/[a-zA-Z]/) != -1) ? 1 : 0;
		var p2 = (newpass.search(/[0-9]/) != -1) ? 1 : 0;
		var p3 = (newpass.search(/[^A-Za-z0-9_]/) != -1) ? 1 : 0;
		var pa = p1 + p2 + p3;
		if (pa == 1) {
			$('#level1').css("background-color","#FFA9A9");
		} else if (pa == 2) {
			$('#level2').css("background-color","#A9A9FF");
		} else if (pa == 3) {
			$('#level3').css("background-color","#A9FFA9");
		}
	}

}
</script>
</html>