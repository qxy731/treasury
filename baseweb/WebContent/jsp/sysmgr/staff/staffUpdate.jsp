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
body {
	margin: 20px;
	width: 90%;
	height: 80%;
}

.div_bottom {
	margin-top: 10px
}

.table_btn td {
	padding: 1px 20px 1px 20px
}
</style>
</head>

<body>
	<n:enums keys='valid_type,sex,education_type,partime_job_type' />
	<form id="insertForm">
		<table class='s1-params'>
			<tr>
				<td align="right"><font color="red">*</font>&nbsp;员工编号</td>
				<td><input type='text' id='staffId' name='modifyStaff.staffId'
					value="${updateIn.modifyStaff.staffId}" readonly="readonly" /></td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>&nbsp;员工姓名</td>
				<td><input type='text' id='staffName'
					name='modifyStaff.staffName'
					value="${updateIn.modifyStaff.staffName}" /></td>
			</tr>
			<tr>
				<td align="right">员工级别</td>
				<td><input type='text' id='staffLevel'
					name='modifyStaff.staffLevel'
					value="${updateIn.modifyStaff.staffLevel}" /></td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>&nbsp;所属部门</td>
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
			</tr>
			
			<tr>
				<td>身份证号</td>
				<td><input type='text' id='certNo' name='modifyStaff.certNo' value="${updateIn.modifyStaff.certNo}"/></td>
			</tr>
			
			<tr>
				<td>学&nbsp;&nbsp;历</td>
				 <td><n:select codetype="education_type" id='education' name='modifyStaff.education' value="${updateIn.modifyStaff.education}"
						emptyOption="true" ></n:select></td>
						
				<!-- <td><input type='text' id='education' name='newStaff.education'/></td> -->
			</tr>
			
			<tr>
				<td>属&nbsp;&nbsp;性</td>
				<td><n:select codetype="partime_job_type" id='partTimeJob' name='modifyStaff.partTimeJob'
						emptyOption="true" value="${updateIn.modifyStaff.partTimeJob}"></n:select></td> 
				<!-- <td><input type='text' id='partTimeJob' name='newStaff.partTimeJob'/></td> -->
			</tr>
			
			<tr>
				<td>办公电话</td>
				<%-- <td><n:select codetype="officePhone" id='officePhone' name='newStaff.officePhone'
						emptyOption="true" value="updateIn.modifyStaff.officePhone"></n:select></td> --%>
				<td><input type='text' id='officePhone' name='modifyStaff.officePhone' value="${updateIn.modifyStaff.officePhone}"/></td>
			</tr>
			
			<tr>
				<td>手&nbsp;&nbsp;机</td>
				<%-- <td><n:select codetype="mobilePhone" id='mobilePhone' name='newStaff.mobilePhone'
						emptyOption="true" value="updateIn.modifyStaff.mobilePhone"></n:select></td> --%>
				<td><input type='text' id='mobilePhone' name='modifyStaff.mobilePhone'   value="${updateIn.modifyStaff.mobilePhone}"/></td>
			</tr>
			
			<tr>
				<td>地&nbsp;&nbsp;址</td>
				<%-- <td><n:select codetype="address" id='address' name='newStaff.address'
						emptyOption="true" value="updateIn.modifyStaff.address"></n:select></td> --%>
				<td><input type='text' id='address' name='modifyStaff.address'  value="${updateIn.modifyStaff.address}"/></td>
			</tr>
			
			<tr>
				<td align="right"><font color="red">*</font>&nbsp;员工状态</td>
				<td><n:select codetype="valid_type" id="staffStatus"
						name='modifyStaff.staffStatus' emptyOption="true"
						value="updateIn.modifyStaff.staffStatus"></n:select></td>
			</tr>
		</table>
		<div align="center" class="div_bottom">
			<table class="table_btn">
				<tr>
					<td><input id="commit" type="button" value="提&nbsp;交"
						class="l-button"></input></td>
					<td><input id="cancel" type="button" value="取&nbsp;消"
						class="l-button"></td>
				</tr>
			</table>
		</div>
		<!--<table class="s1-button">
	<tr>
		<td nowrap align="right">
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