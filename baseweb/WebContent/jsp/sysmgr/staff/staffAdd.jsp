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
	margin: 20px;
	width: 90%;
	height: 80%;
}
/*.s1-params {width:500px}*/
#insertForm table td {
	line-height: 28px;
	padding: 5px;
}

select, input[type=text] {
	width: 150px;
}

.div_bottom {
	margin-top: 10px
}

.table_btn td {
	padding: 1px 20px 1px 20px
}
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
				$.dialogBox.info(result.retMsg, function() {
					$.dialog.close();
				});
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
		<table class='s1-params'>
			<tr>
				<td><font color="red">*</font>&nbsp;员工编号</td>
				<td><input type='text' id='staffId' name='newStaff.staffId'
					validate="{required:true,maxlength:16}" /></td>
			</tr>
			<tr>
				<td><font color="red">*</font>&nbsp;登陆帐号</td>
				<td><input type='text' id='logonId' name='newStaff.logonId'
					validate="{required:true,maxlength:16}" /></td>
			</tr>
			<tr>
				<td><font color="red">*</font>&nbsp;组织
				</td>
				<td nowrap><input type='text' id='zzname' name='zzname'
					readonly="readonly" onclick="openSelectUnit()" class='unit_select' /><input
					type="hidden" id="ownerUnitid" name="newStaff.ownerUnitid"></td>
			</tr>
			<tr>
				<td><font color="red">*</font>&nbsp;登陆密码</td>
				<td><input type='text' id='password' name='newStaff.password'
					value="111111" / validate="{required:true,maxlength:16}"></td>
			</tr>
			<tr>
				<td><font color="red">*</font>&nbsp;员工姓名</td>
				<td><input type='text' id='staffName' name='newStaff.staffName'
					validate="{required:true}" /></td>
			</tr>
			<tr>
				<td>员工级别</td>
				<td><input type='text' id='staffLevel'
					name='newStaff.staffLevel' /></td>
			</tr>
			<tr>
				<td>性别</td>
				<td><n:select codetype="sex" id='sex' name='newStaff.sex'
						emptyOption="true" value="updateIn.modifyStaff.sex"></n:select></td>
			</tr>
			
			<tr>
				<td>身份证号</td>
				<td><input type='text' id='certNo' name='newStaff.certNo'/></td>
			</tr>
			
			<tr>
				<td>学历</td>
				 <td><n:select codetype="education_type" id='education' name='newStaff.education'
						emptyOption="true" value="updateIn.modifyStaff.education"></n:select></td>
						
				<!-- <td><input type='text' id='education' name='newStaff.education'/></td> -->
			</tr>
			
			<tr>
				<td>属性</td>
				<td><n:select codetype="partime_job_type" id='partTimeJob' name='newStaff.partTimeJob'
						emptyOption="true" value="updateIn.modifyStaff.partTimeJob"></n:select></td> 
				<!-- <td><input type='text' id='partTimeJob' name='newStaff.partTimeJob'/></td> -->
			</tr>
			
			<tr>
				<td>办公电话</td>
				<%-- <td><n:select codetype="officePhone" id='officePhone' name='newStaff.officePhone'
						emptyOption="true" value="updateIn.modifyStaff.officePhone"></n:select></td> --%>
				<td><input type='text' id='officePhone' name='newStaff.officePhone'/></td>
			</tr>
			
			<tr>
				<td>手机</td>
				<%-- <td><n:select codetype="mobilePhone" id='mobilePhone' name='newStaff.mobilePhone'
						emptyOption="true" value="updateIn.modifyStaff.mobilePhone"></n:select></td> --%>
				<td><input type='text' id='mobilePhone' name='newStaff.mobilePhone'/></td>
			</tr>
			
			<tr>
				<td>地址</td>
				<%-- <td><n:select codetype="address" id='address' name='newStaff.address'
						emptyOption="true" value="updateIn.modifyStaff.address"></n:select></td> --%>
				<td><input type='text' id='address' name='newStaff.address'/></td>
			</tr>
			<tr>
				<td><font color="red">*</font>&nbsp;员工状态</td>
				<td><n:select codetype="valid_type" id="staffStatus"
						name='newStaff.staffStatus' emptyOption="true" value="1"
						validate="{required:true}"></n:select></td>
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