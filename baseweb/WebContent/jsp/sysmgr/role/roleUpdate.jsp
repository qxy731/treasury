<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改角色</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
body {
	margin: 20px;
	width:90%;
	height:80%;
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
	function updateRole() {
		if (validate()) {
			try {
				var url = "${_CONTEXT_PATH}/sys/role!update.action";
				var data = Utils.convertFormData('updateIn', 'updateForm');
				Utils.ajaxSubmit(url, data, function(result) {
					$.dialogBox.alert(result.retMsg, function() {
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
		return $("#updateForm").valid();
	}
	function cancelDialog() {
		$.dialog.close();
	}
	$(function() {
		Utils.validateInit();
		$('#commit').bind('click', updateRole);
		$('#cancel').bind('click', cancelDialog);
	})
</script>
</head>

<body>
	<n:page action="com.soule.app.sys.role.RoleAction"></n:page>
	<n:enums keys='valid_type' />
	<form id="updateForm">
		<table class='s1-params'>
			<tr>
				<td><font color="red">*</font>&nbsp;角色编码</td>
				<td><input id="roleId" name="modifyRole.roleId"
					validate="{required:true}" readonly="readonly"
					value='${modifyRole.roleId}' /></td>
				<td></td>
			</tr>
			<tr>
				<td><font color="red">*</font>&nbsp;角色名称</td>
				<td><input id="roleName" name="modifyRole.roleName"
					validate="{required:true}" value='${modifyRole.roleName}'></input></td>
				<td></td>
			</tr>
			<tr>
				<td>角色描述</td>
				<td><input id="remark" name="modifyRole.remark"
					value='${modifyRole.remark}'></input></td>
				<td></td>
			</tr>
			<tr>
				<td><font color="red">*</font>&nbsp;角色状态</td>
				<td><n:select codetype="valid_type" id="roleStatus"
						name='modifyRole.roleStatus' emptyOption="true"
						validate="{required:true}" value='${modifyRole.roleStatus}'></n:select></td>
				<td></td>
			</tr>
			<tr>
				<td>上级角色</td>
				<td><input id="parentRole" name="modifyRole.parentRoleId"
					value='${modifyRole.parentRoleId}'></input></td>
				<td></td>
			</tr>
		</table>
		<div align="center" class="div_bottom">
			<table class="table_btn">
				<tr>
					<td><input id="commit" type="button" value="提&nbsp;交"
						class="l-button"></input></td>
					<td><input id="cancel" type="button" value="取&nbsp;消"
						class="l-button"></input></td>
				</tr>
			</table>
		</div>
		<!--<table class="s1-button">
	<tr>
		<td>
			<input id="commit" type="button" value="提交" class="l-button"></input>
		</td>
		<td>
			<input id="cancel" type="button" value="取消" class="l-button"></input>
		</td>
	</tr>
</table>
-->
	</form>
</body>
</html>