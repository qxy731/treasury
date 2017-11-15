<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增国库</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
/* body {
	margin: 20px;
	width: 90%;
	height: 80%;
} */

/* .div_btn {
	margin-top: 10px
} */
</style>
</head>
<body>
	<n:enums keys='unit_kind,valid_type' />
	<form id="insertForm">
		<table class='s1-params'>
			<tr>
				<td nowrap="nowrap"><font color="red">*</font>&nbsp;国库编码:</td>
				<td nowrap="nowrap"><input id="unitId" type="text"
					name="unitId" value="" validate="{required:true}"/></td>
			</tr>
			<tr>
				<td nowrap="nowrap"><font color="red">*</font>&nbsp;国库名称:</td>
				<td nowrap="nowrap"><input id="unitName" type="text"
					name="unitName" value="" validate="{required:true}"/></td>
			</tr>
			<tr>
				<td nowrap="nowrap"><font color="red">*</font>&nbsp;上级国库:</td>
				<td nowrap="nowrap"><input type="text" id="superUnitName"
					name="superUnitName" readonly="readonly" onclick="openSelectUnit()"
					class='unit_select' validate="{required:true}"/></td>
			</tr>
			<tr>
				<td nowrap="nowrap"><font color="red">*</font>&nbsp;国库级别:</td>
				<td nowrap="nowrap"><input id="unitLevel" type="text"
					name="unitLevel" value="" readonly="readonly" validate="{required:true}"/></td>
			</tr>
			<!-- 开始 -->
			<tr>
				<td nowrap="nowrap"><font color="red">*</font>&nbsp;清算国库代码:</td>
				<td nowrap="nowrap"><input id="settUnitId" type="text"
					name="settUnitId" value="" validate="{required:true}"/></td>
			</tr>
			
			<tr>
				<td nowrap="nowrap"><font color="red">*</font>&nbsp;管理国库代码:</td>
				<td nowrap="nowrap"><input id="mgrUnitId" type="text"
					name="mgrUnitId" value="" validate="{required:true}"/></td>
			</tr>
			
			<tr>
				<td nowrap="nowrap"><font color="red">*</font>&nbsp;启用日期:</td>
				<td nowrap="nowrap"><input id="startDate" type="text"
					name="startDate" value="" validate="{required:true}"/></td>
			</tr>
			
		<!--  结束 -->
			<tr>
				<td nowrap="nowrap">国库地址:</td>
				<td nowrap="nowrap"><input id="unitAddress" type="text"
					name="unitAddress" value="" style="width: 300px;" /></td>
			</tr>
			<tr>
				<td nowrap="nowrap"><font color="red">*</font>&nbsp;国库状态:</td>
				<td nowrap="nowrap"><n:select codetype="valid_type"
						id="unitStatus" name='unitStatus' emptyOption="false"
						disabled="false" cssStyle="width:152px;" ></n:select></td>
			</tr>
		</table>
		<input id="superUnitId" type="hidden" name="superUnitId" />
		<div align="center" class="div_btn">
			<table width="100%">
				<tr>
					<td align="right"><input id="commit" type="button"
						value="提&nbsp;交" class="l-button"></input></td>
					<td width="5%"></td>
					<td width="5%"></td>
					<td><input id="cancel" type="button" value="取&nbsp;消"
						class="l-button"></input></td>
				</tr>
			</table>
		</div>
		<!--<table class="s1-button">
	<tr>
		<td nowrap="nowrap" align="right">
			<input id="commit" type="button" value="提交" class="l-button"></input>
		</td>
		<td nowrap="nowrap">
			<input id="cancel" type="button" value="取消" class="l-button"></input>
		</td>
	</tr>
</table>
-->
	</form>
	<script type="text/javascript">
	$("#startDate").ligerDateEditor();
	$("#endDate").ligerDateEditor();
		function addUnit() {
			if (validate()) {
				try {
					var paramObj = {
						'queryIn.unitId' : $('#unitId').val(),
						'queryIn.unitName' : $('#unitName').val(),
						'queryIn.unitAddress' : $('#unitAddress').val(),
						'queryIn.superUnitId' : $('#superUnitId').val(),
						'queryIn.unitLevel' : $('#unitLevel').val(),
						'queryIn.settUnitId' : $('#settUnitId').val(),
						'queryIn.mgrUnitId' : $('#mgrUnitId').val(),
						'queryIn.startDate' : $('#startDate').val(),
						'queryIn.unitStatus' : $('#unitStatus').val()
					};
					//var params = $.param(paramObj,true);
					var params = Utils.convertFormData('queryIn', 'insertForm');
					var url = "${_CONTEXT_PATH}/sys/unit!addUnitModel.action";
					Utils.ajaxSubmit(url, params, function(resData) {
						$.dialogBox.info(resData.retMsg, function() {
							$.dialogBox.opener.query();
							$.dialogBox.close();
						});
					});
				} catch (e) {
				}
			}
		}
		function validate() {
			return $("#insertForm").valid();
		}
		//选择国库
		function openSelectUnit() {
			Utils.openSelectUnit(null, '', setUnitIdAndName);
		}
		function setUnitIdAndName(unitId, unitName) {
			var selectNode = this.iframe.contentWindow.manager.getSelected();
			if (selectNode) {
				var unitId = selectNode.data.unitId;
				var unitName = selectNode.data.unitName;
				$("#superUnitId").val(unitId);
				$("#superUnitName").val(unitName);
				setUnitLevel();
			}
		}
		function setUnitLevel() {
			var paramObj = {
				'queryIn.unitId' : $('#superUnitId').val()
			};
			var params = $.param(paramObj, true);
			var url = '${_CONTEXT_PATH}/sys/unit!queryByUnitId.action?'
					+ params;
			Utils.ajaxSubmit(url, '', function(result) {
				$("#unitLevel").val(eval(result.unitLevel) + 1);
			});
		}
		function cancelDialog() {
			$.dialog.close();
		}
		$(function() {
			Utils.validateInit();
			$('#commit').bind('click', addUnit);
			$('#cancel').bind('click', cancelDialog);
		});
	</script>
</body>
</html>