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
</style>
<script type="text/javascript">
	$(function() {
		Utils.validateInit();
		$('#commit').bind('click', addStaff);
		$('#cancel').bind('click', cancelDialog);
	});
	function addStaff(dialog) {
		if ($("#insertForm").valid()) {
			var url = "${_CONTEXT_PATH}/sys/staff!insert.action";
			var data = Utils.convertFormData('insertIn', 'insertForm');
			Utils.ajaxSubmit(url, data, function(result) {
				if(result.retCode=="C0001"){
					$.dialogBox.info(result.retMsg);
				}else{
					$.dialogBox.info(result.retMsg);
					$.dialogBox.opener.query();
					$.dialog.close();
				}
				
			});
		}
		return false;
	}
	function clearScreen() {
		$('input').each(function(i, item) {
			item.value = '';
		});
	}

	function openSelectUnit() {
		Utils.openSelectUnit(null, '', setUnitIdName);
	}

	function setUnitIdName() {
		var selectNode = this.iframe.contentWindow.manager.getSelected();
		if (selectNode) {
			var unitId = selectNode.data.unitId;
			var unitName = selectNode.data.unitName;
			$("#ownerUnitid").val(unitId);
			$("#zzname").val(unitName);
		}
	}
	function cancelDialog() {
		$.dialogBox.close();
	}
</script>
</head>
<body>
	<n:enums keys='valid_type,sex,education_type,partime_job_type' />
	<form id="insertForm">
		<table class='params' width="100%">
			<tr>
				<td><font color="red">*</font>员工编号</td>
				<td><input type='text' id='staffId' name='newStaff.staffId'
					validate="{required:true,maxlength:16}" /></td>
				<td><font color="red">*</font>登陆帐号</td>
				<td><input type='text' id='logonId' name='newStaff.logonId'
					validate="{required:true,maxlength:16}" /></td>
			</tr>
			<tr>
				<td><font color="red">*</font>组织 </td>
				<td nowrap><input type='text' id='zzname' name='zzname'
					readonly="readonly" onclick="openSelectUnit()" class='unit_select' /><input
					type="hidden" id="ownerUnitid" name="newStaff.ownerUnitid"></td>
					<td><font color="red">*</font>登陆密码</td>
				<td><input type='text' id='password' name='newStaff.password'
					value="111111" / validate="{required:true,maxlength:16}"></td>
			</tr>
			<tr>
				<td><font color="red">*</font>员工姓名</td>
				<td><input type='text' id='staffName' name='newStaff.staffName'
					validate="{required:true}" /></td>
				<td>员工级别</td>
				<td><input type='text' id='staffLevel'
					name='newStaff.staffLevel' /></td>
			</tr>
			<tr>
				<td>性别</td>
				<td><n:select codetype="sex" id='sex' name='newStaff.sex'
						emptyOption="true" value="updateIn.modifyStaff.sex"></n:select></td>
				<td>身份证号</td>
				<td><input type='text' id='certNo' name='newStaff.certNo'/></td>
			</tr>
			<tr>
				<td>学历</td>
				 <td><n:select codetype="education_type" id='education' name='newStaff.education'
						emptyOption="true" value="updateIn.modifyStaff.education"></n:select></td>
				<td>专兼职</td>
				<td><n:select codetype="partime_job_type" id='partTimeJob' name='newStaff.partTimeJob'
						emptyOption="true" value="updateIn.modifyStaff.partTimeJob"></n:select></td>
			</tr>
			<tr>
				<td>办公电话</td>
				<td><input type='text' id='officePhone' name='newStaff.officePhone'/></td>
				<td>手机</td>
				<td><input type='text' id='mobilePhone' name='newStaff.mobilePhone'/></td>
			</tr>
			<tr>
				<td>地址</td>
				<td  colspan="3"><input type='text' id='address' name='newStaff.address' style="width:479px;" /></td>
			</tr>
			<tr>
				<td><font color="red">*</font>员工状态</td>
				<td><n:select codetype="valid_type" id="staffStatus"
						name='newStaff.staffStatus' emptyOption="true" value="1"
						validate="{required:true}"></n:select></td>
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