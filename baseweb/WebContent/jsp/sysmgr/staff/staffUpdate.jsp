<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工修改</title>
<jsp:include page="/comm.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		Utils.validateInit();
		$('#commit').bind('click', update);
		$('#cancel').bind('click', cancelDialog);
	});
	function cancelDialog() {
		$.dialog.close();
	}
	function update() {
		if (validate()) {
			try {
				var url = "${_CONTEXT_PATH}/sys/staff!update.action";
				var data = Utils.convertFormData('updateIn', 'insertForm');
				Utils.ajaxSubmit(url, data, function(result) {
					$.dialogBox.info(result.retMsg, function() {
						$.dialogBox.opener.reloadData();
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
		return $('#insertForm').valid();
	}
	//选择部门
	function openSelectUnit() {
		Utils.openSelectUnit(null, '', setUnitIdName);
	}
	function setUnitIdName() {
		var selectNode = this.iframe.contentWindow.manager.getSelected();
		if (selectNode) {
			var unitId = selectNode.data.unitId;
			var unitName = selectNode.data.unitName;
			$("#unitId").val(unitId);
			$("#unitName").val(unitName);
		}
	}
</script>
<style type="text/css">

</style>
</head>
<body>
	<n:enums keys='valid_type,sex,education_type,partime_job_type' />
	<form id="insertForm">
		<table class='params' width="100%">
			<tr>
				<td align="right"><font color="red">*</font>员工编号</td>
				<td><input type='text' id='staffId' name='modifyStaff.staffId'
					value="${updateIn.modifyStaff.staffId}" readonly="readonly" /></td>
				<td align="right"><font color="red">*</font>员工姓名</td>
				<td><input type='text' id='staffName'
					name='modifyStaff.staffName'
					value="${updateIn.modifyStaff.staffName}" /></td>
			</tr>
			<tr>
				<td align="right">员工级别</td>
				<td><input type='text' id='staffLevel'
					name='modifyStaff.staffLevel'
					value="${updateIn.modifyStaff.staffLevel}" /></td>
				<td align="right"><font color="red">*</font>所属部门</td>
				<td><input type='hidden' id='ownerUnitid'
					name='modifyStaff.ownerUnitid' value="${updateIn.modifyStaff.ownerUnitid}" /><input id="unitName"
					name="unitName" readonly="readonly"
					value="${updateIn.modifyStaff.unitName}" onclick="openSelectUnit()"
					class='unit_select' /></td>
			</tr>
			<tr>
				<td align="right">性别</td>
				<td><n:select codetype="sex" id='sex' name='modifyStaff.sex'
						emptyOption="true" value="updateIn.modifyStaff.sex"></n:select></td>
				<td>身份证号</td>
				<td><input type='text' id='certNo' name='modifyStaff.certNo' value="${updateIn.modifyStaff.certNo}"/></td>
			</tr>
			<tr>
				<td>学历</td>
				<td><n:select codetype="education_type" id='education' name='modifyStaff.education' value="${updateIn.modifyStaff.education}"
						emptyOption="true" ></n:select></td>
				<td>属性</td>
				<td><n:select codetype="partime_job_type" id='partTimeJob' name='modifyStaff.partTimeJob'
						emptyOption="true" value="${updateIn.modifyStaff.partTimeJob}"></n:select></td> 
			</tr>
			<tr>
				<td>办公电话</td>
				<td><input type='text' id='officePhone' name='modifyStaff.officePhone' value="${updateIn.modifyStaff.officePhone}"/></td>
				<td>手机</td>
				<td><input type='text' id='mobilePhone' name='modifyStaff.mobilePhone'   value="${updateIn.modifyStaff.mobilePhone}"/></td>
			</tr>
			<tr>
				<td>地址</td>
				<td colspan="3"><input type='text' id='address' name='modifyStaff.address'  value="${updateIn.modifyStaff.address}"  style="width:479px;" /></td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>员工状态</td>
				<td><n:select codetype="valid_type" id="staffStatus"
						name='modifyStaff.staffStatus' emptyOption="true"
						value="updateIn.modifyStaff.staffStatus"></n:select></td>
				<td colspan="2"></td>	
			</tr>
			<tr>
				<td colspan="4">
					<input id="commit" type="button" value="提&nbsp;交" class="l-button" style="float:left;margin-left:200px;"></input>
					<input id="cancel" type="button" value="取&nbsp;消" class="l-button" style="float:left;margin-left:5px;"></input>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>