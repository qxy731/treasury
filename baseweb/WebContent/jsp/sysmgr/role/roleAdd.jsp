<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增角色</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
body {
	padding-top: 20px;
	width:90%;
	height:90%;
}

#remark {
	width: 250px;
}

.div_bottom {
	margin-top: 10px
}

.table_btn td {
	padding: 1px 20px 1px 20px
}
</style>
<script type="text/javascript">
	function addRole() {
		if (validate()) {
			try {
				var url = "${_CONTEXT_PATH}/sys/role!insert.action";
				var data = Utils.convertFormData('insertIn', 'insertForm');
				Utils.ajaxSubmit(url, data, function(result) {

					$.dialogBox.info(result.retMsg, function() {
						$.dialogBox.close();
					});
				});

			} catch (e) {
			}
		}
		return false;
	}
	function clearScreen() {
		$('input').each(function(i, item) {
			item.value = '';
		});
	}
	function validate() {
		return $("#insertForm").valid();
	}
	function cancelDialog() {
		$.dialog.close();
	}
	$(function() {
		Utils.validateInit();
		$('#commit').bind('click', addRole);
		$('#cancel').bind('click', cancelDialog);
	})
</script>
</head>

<body>
	<n:enums keys='valid_type' />
	<form id="insertForm">
		<table class='s1-params'>
			<tr>
				<td nowrap="nowrap"><font color="red">*</font>&nbsp;角色编码</td>
				<td><input id="roleId" name="newRole.roleId"
					validate="{required:true}" /></td>
				<td></td>
			</tr>
			<tr>
				<td><font color="red">*</font>&nbsp;角色名称</td>
				<td><input id="roleName" name="newRole.roleName"
					validate="{required:true}"></input></td>
				<td></td>
			</tr>
			<tr>
				<td>角色描述</td>
				<td><input id="remark" name="newRole.remark"></input></td>
				<td></td>
			</tr>
			<tr>
				<td><font color="red">*</font>&nbsp;角色状态</td>
				<td><n:select codetype="valid_type" id="roleStatus"
						name='newRole.roleStatus' emptyOption="true"
						validate="{required:true}"></n:select></td>
				<td></td>
			</tr>
			<tr>
				<td>上级角色</td>
				<td><input id="parentRole" name="newRole.parentRoleId"></input></td>
				<td></td>
			</tr>
		</table>
		<div align="center" class="div_bottom">
			<table class="table_btn">
				<tr>
					<td><input id="commit" type="button" value="提&nbsp;交"
						class="l-button"></td>
					<td><input id="cancel" type="button" value="取&nbsp;消"
						class="l-button"></td>
				</tr>
			</table>
		</div>
		<!--<table class="s1-button">
	<tr>
		<td nowrap>
			<input id="commit" type="button" value="提交" class="l-button"></input>
		</td>
		<td nowrap>
			<input id="cancel" type="button" value="取消" class="l-button"></input>
		</td>
	</tr>
</table>
-->
	</form>
</body>
</html>