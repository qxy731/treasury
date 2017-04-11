<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审计日志</title>
<jsp:include page="/comm_debug.jsp"></jsp:include>
<style type="text/css">
.params td {
	background-color: #E2EAFF;
	padding: 2px 2px 2px 10px;
}

.queryBox .params input[type=text], .queryBox .params .l-text {
	
}

.s1-button {
	margin-top: 20px;
}

#query {
	width: 80px
}

#reset {
	width: 80px
} /**/
body {
	margin-top: 10px
}
</style>
</head>
<body>
	<n:page action='com.soule.app.sys.auditlog.AuditLogAction' />
	<n:enums keys="log_biz_type,log_func_type,log_exec_result" />
	<table class='content'>
		<tr>
			<td>
				<form id="myform" action="${_CONTEXT_PATH}/AuditLog!query.action">
					<fieldset class="queryBox">
						<legend>查询条件</legend>
						<table class='params'>
							<tr>
								<td nowrap="nowrap" align="center">执行人编码</td>
								<td><input type='text' id='operStaffid'
									name='log.operStaffid' class='user_select' /></td>
								<td nowrap="nowrap" align="center">执行人姓名</td>
								<td><input type='text' id='operStaffName'
									name='log.operStaffName' /></td>
								<td align="center">执行人角色</td>
								<td><input type='text' id='roleId' name='log.roleId' /></td>
							</tr>
							<tr>
								<td align="center">操作编码</td>
								<td><input type='text' id='operCode' name='log.operCode' /></td>
								<td align="center">操作名称</td>
								<td><input type='text' id='operName' name='log.operName' /></td>
								<td align="center">客户端IP</td>
								<td><input type='text' id='ipAddr' name='log.ipAddr' /></td>
							</tr>
							<tr>
								<td align="center">业务类型</td>
								<td><n:select codetype="log_biz_type" id="bizType"
										name='log.bizType' emptyOption="true"></n:select></td>
								<td align="center">操作类型</td>
								<td><n:select codetype="log_func_type" id="funcType"
										name='log.funcType' emptyOption="true"></n:select></td>
								<td align="center">执行结果</td>
								<td><n:select codetype="log_exec_result" id="execResult"
										name='log.execResult' emptyOption="true"></n:select></td>
							</tr>
							<tr>
								<td align="center">开始时间</td>
								<td><input type='text' id='startTime' name='log.startTime' /></td>
								<td align="center">结束时间</td>
								<td><input type='text' id='endTime' name='log.endTime' /></td>
								<td align="right"><input id='query' name='query'
									type='button' value='执&nbsp;&nbsp;行' class='l-button' /></td>
								<td align="left"><input id='reset' name='reset'
									type='button' value='重&nbsp;&nbsp;置' class='l-button' /></td>
							</tr>
						</table>
					</fieldset>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<fieldset class="detailBox">
					<legend>查询结果</legend>
					<div id='loglist'></div>
				</fieldset>
			</td>
		</tr>
	</table>
</body>
<script type='text/javascript'>
	$(function() {
		Utils.validateInit();
		$('#reset').bind('click', doClear);
		$('#query').bind('click', execute);
		$('#operStaffid').bind('click', doSelectStaff);
		$('#startTime').ligerDateEditor();
		$('#endTime').ligerDateEditor();
		//输出表格
		$("#loglist")
				.ligerGrid(
						{
							enumlist : _enum_params,
							columns : [
							//{ display: '顺序号', name: 'id', align: 'center' },
							{
								display : '执行人',
								name : 'operStaffName',
								align : 'left'
							},
							//{ display: '执行人编码', name: 'operStaffid', align: 'center' },
							//{ display: '执行角色', name: 'roleName', align: 'center' },
							//{ display: '执行人角色', name: 'roleId', align: 'center' },
							//{ display: '代理人编码', name: 'devoStaffid', align: 'center' },
							//{ display: '操作编码', name: 'operCode', align: 'center' },
							{
								display : '操作名称',
								name : 'operName',
								align : 'left'
							}, {
								display : '客户端IP',
								name : 'ipAddr',
								align : 'left'
							}, {
								display : '业务类型',
								name : 'bizType',
								align : 'left',
								codetype : 'log_biz_type'
							}, {
								display : '操作类型',
								name : 'funcType',
								align : 'left',
								codetype : 'log_func_type'
							}, {
								display : '执行结果',
								name : 'execResult',
								align : 'left',
								codetype : 'log_exec_result'
							}, {
								display : '执行时间',
								name : 'execTime',
								align : 'left'
							}
							//{ display: '详细信息', name: 'logDetail', align: 'center' },
							//
							],
							pageSize : 20,
							enabledSort : false,
							allowAdjustColWidth : true,
							width : '100%',
							height : '98%',
							onError : function() {
								$.dialogBox.error("查询数据失败", '提示');
							},
							onDblClickRow : function(row, id, x) {
								var url = '${_CONTEXT_PATH}/jsp/sysmgr/auditlog/auditlog_detail.jsp'
								$.dialogBox.openDialog(url, {
									title : '日志详细信息',
									width : '640px',
									height : '380px'
								});
							}
						});
	});

	function doSelectStaff() {
		Utils.openSelectAStaff(function() {
			var single = this.iframe.contentWindow.select();
			if (!single) {
				return false;
			}
			$('#operStaffName').val(single.staffName);
			$('#operStaffid').val(single.staffId);
		}, true);
	}
	function getLogData() {
		var grid = $("#loglist").ligerGetGridManager();
		return grid.getSelectedRow();
	}

	function doClear() {
		$(".params input[type='text']").each(function(i, item) {
			item.value = '';
		});
	}

	function execute() {
		var mdata = Utils.convertParam('queryIn', 'myform');
		var params = {
			dataAction : 'server',
			dataType : 'server',
			url : '${_CONTEXT_PATH}/sys/audit-log!query.action',
			newPage : 1,
			parms : mdata,
			onError : function() {
				$.dialogBox.error("查询数据失败");
			}
		};
		var gridManager = $("#loglist").ligerGetGridManager();
		gridManager.setOptions(params);
		gridManager.loadData();
	}
</script>
</html>