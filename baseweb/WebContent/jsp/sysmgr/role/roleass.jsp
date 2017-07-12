<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色分配</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.left {
	float: left;
	width: 50%;
}

.right {
	float: left;
	width: 50%;
}
</style>
</head>
<n:page action='com.soule.app.sys.roleass.RoleassAction' />
<body>
	<div class="content">
		<div class="left">
			<fieldset class="queryBox">
				<legend>角色人员查询</legend>
				<table class='params'>
					<tr>
						<td>角色ID</td>
						<td><s:select list="roles" listKey="roleId"
								listValue="%{roleId + ': ' + roleName}" id="qRoleId"
								onchange="queryByRole()" emptyOption="true" /></td>
					</tr>
					<tr>
						<td>执行部门</td>
						<td><input type="hidden" id="operUnitid" name="operUnitid" /><input
							id="operUnitname" name="operUnitname" readonly="readonly"
							type='text' class="unit_select" /></td>
					</tr>
					<tr>
					<td colspan="2">
						<div style="float:right;">
						<input id='reQuery' type='button' value='刷&nbsp;新' class='l-button' style="float:left;margin-right:5px;"/>
						</div>
					</td>
					</tr>
				</table>
			</fieldset>
			<fieldset class="outbox">
				<div id='lefttoptoolbar'></div>
				<div id='roleStafflist'></div>
			</fieldset>
		</div>
		<div class="right">
			<fieldset class="queryBox">
				<legend>人员查询</legend>
				<table class='params'>
					<tr>
						<td>员工姓名</td>
						<td><input type='text' id='staffName' name='staffName'
							width="25%" /></td>
						<td>员工编码</td>
						<td><input type='text' id='staffId' name='staffId' /></td>

					</tr>
					<tr>
						<td>所属部门</td>
						<td><input type='hidden' id='unitId' name='unitId' /> <input
							id="unitname" name="unitname" readonly="readonly" type='text'
							class="unit_select" /></td>
						<td>所属角色</td>
						<td><input type='text' id='roleId' name='roleId' /></td>
					</tr>
					<tr>
					<td colspan="4">
						<div style="float:right;">
							<input id='queryStaff' name='query' type='button' value='查&nbsp;询' class='l-button' style="float:left;margin-right:5px;"/>
							<input id='reset' name='reset' type='button' value='重&nbsp;置' class='l-button' style="float:left;margin-right:5px;"/>
						</div>
					</td>
				</tr>
				</table>
			</fieldset>
			<fieldset class="outbox">
				<div id='righttoptoolbar'></div>
				<div id='stafflist'></div>
			</fieldset>
		</div>
	</div>
</body>
<script type='text/javascript'>
	$(function() {
		//输出表格
		$("#lefttoptoolbar").ligerToolBar({
			items : [ {
				text : '删除人员',
				name : 'delete_btn',
				icon : 'delete',
				click : deleteStaff
			} ],
			width : '99%'
		});

		$("#righttoptoolbar").ligerToolBar({
			items : [ {
				text : '增加人员',
				name : 'insert_btn',
				icon : 'add',
				click : insertStaff
			} ],
			width : '99%'
		});

		$("#roleStafflist").ligerGrid({
			columns : [ {
				display : '员工编码',
				name : 'staffId',
				align : 'left',
				width : '33%'
			}, {
				display : '员工姓名',
				name : 'staffName',
				align : 'left',
				width : '33%'
			}, {
				display : '操作部门',
				name : 'operUnitName',
				align : 'left',
				width : '34%'
			} ],
			//buttons:[
			//{text:'删除人员',name:'delete_btn',clazz:'nbutton'}
			//],
			checkbox : true,
			pageSize : 20,
			sortName : 'staffId',
			height : '90%',
			onError : function() {
				$.dialogBox.error("查询数据失败");
			}
		});
		$("#stafflist").ligerGrid({
			columns : [ {
				display : '员工编码',
				name : 'staffId',
				align : 'left',
				width : '22%'
			}, {
				display : '员工姓名',
				name : 'staffName',
				align : 'left',
				width : '26%'
			}, {
				display : '所属部门编号',
				name : 'unitId',
				align : 'left',
				width : '26%'
			}, {
				display : '所属部门名称',
				name : 'unitName',
				align : 'left',
				width : '26%'
			} ],
			checkbox : true,
			pageSize : 20,
			//buttons:[
			//	{text:'增加人员',name:'insert_btn',clazz:'nbutton'}
			//],
			sortName : 'staffId',
			height : '90%',
			onError : function() {
				$.dialogBox.error("查询数据失败");
			}
		});
		$("#queryStaff").bind('click', queryStaff);
		$("#reQuery").bind('click', queryByRole);
		$("#reset").bind('click', doClear);
		$("#delete_btn").bind('click', deleteStaff);
		$("#insert_btn").bind('click ', insertStaff);
		$("#operUnitname").bind('click ', openSelectUnit);
		$("#unitname").bind('click ', openSelectUnit1);
	})
	function doClear() {
		$(".queryBox input[type='text']").each(function(i, item) {
			item.value = '';
		});
		$("#unitId").val('');
	}
	/* function queryByRole() {
		var url = "${_CONTEXT_PATH}/sys/roleass!queryByRole.action";
		var roleid = $("#qRoleId").val();
		var operUnitid = $("#operUnitid").val();
		var params = {
			dataAction : 'server',
			dataType : 'server',
			url : url,
			newPage : 1,
			parms : [ {
				name : 'queryByRoleIn.roleId',
				value : roleid
			}, {
				name : 'queryByRoleIn.operUnitid',
				value : operUnitid
			} ]
		};
		var gridManager = $("#roleStafflist").ligerGetGridManager();
		gridManager.setOptions(params);
		gridManager.loadData();
	} */

	function queryStaff() {
		var url = "${_CONTEXT_PATH}/sys/roleass!queryStaff.action";
		var roleid = $("#roleId").val();
		var staffname = $("#staffName").val();
		var staffid = $("#staffId").val();
		var unitid = $("#unitId").val();
		var params = {
			dataAction : 'server',
			dataType : 'server',
			url : url,
			newPage : 1,
			parms : [ {
				name : 'queryStaffIn.roleId',
				value : roleid
			}, {
				name : 'queryStaffIn.staffName',
				value : staffname
			}, {
				name : 'queryStaffIn.staffId',
				value : staffid
			}, {
				name : 'queryStaffIn.unitId',
				value : unitid
			} ]
		};
		var gridManager = $("#stafflist").ligerGetGridManager();
		gridManager.setOptions(params);
		gridManager.loadData();
	}
	function insertStaff() {
		var grid1 = $('#stafflist').ligerGetGridManager();
		var roleid = $("#qRoleId").val();
		var unitid = $("#operUnitid").val();
		if (!roleid) {
			Utils.alert("请先选择一个角色");
			return;
		}
		if (!unitid) {
			Utils.alert("请先选择一个部门");
			return;
		}
		var rows1 = grid1.getCheckedRows();
		if (rows1.length < 1) {
			Utils.alert("请先选择需要新增的人员");
			return;
		}
		var mdata = {
			'roleId' : roleid,
			'operUnitid' : unitid
		};
		var rows1 = grid1.getCheckedRows();
		mdata['insertIn.insertsStr'] = JSON.stringify(rows1);

		var url = "${_CONTEXT_PATH}/sys/roleass!insert.action";
		Utils.ajaxSubmit(url, mdata, onSuccess);
	}
	function deleteStaff() {
		var grid1 = $('#roleStafflist').ligerGetGridManager();
		var rows1 = grid1.getCheckedRows();
		if (rows1.length < 1) {
			Utils.alert("请先选择需要删除的记录");
			return;
		}
		$.dialogBox.alert('确定删除吗？', function() {
			doDeleteStaff(rows1);
		}, true);

	}
	function doDeleteStaff(rows) {
		var mdata = {};
		mdata['deleteIn.deletesStr'] = JSON.stringify(rows);

		var url = "${_CONTEXT_PATH}/sys/roleass!delete.action";
		Utils.ajaxSubmit(url, mdata, function(result) {
			Utils.alert(result.retMsg);
			queryByRole();
		});
	}

	function openSelectUnit() {
		Utils.openSelectUnit(null, _CREATE_ORG, setUnitIdName);
	}
	function setUnitIdName() {
		var selectNode = this.iframe.contentWindow.manager.getSelected();
		if (selectNode) {
			var unitId = selectNode.data.unitId;
			var unitName = selectNode.data.unitName;
			$("#operUnitid").val(unitId);
			$("#operUnitname").val(unitName);
			queryByRole();
		}
	}
	function openSelectUnit1() {
		Utils.openSelectUnit(null, _CREATE_ORG, setUnitIdName1);
	}
	function setUnitIdName1() {
		var selectNode = this.iframe.contentWindow.manager.getSelected();
		if (selectNode) {
			var unitId = selectNode.data.unitId;
			var unitName = selectNode.data.unitName;
			$("#unitId").val(unitId);
			$("#unitname").val(unitName);
		}
	}
	
	var onSuccess = function() {
		queryByRole();
	}
	function queryByRole() {
		var url = "${_CONTEXT_PATH}/sys/roleass!queryByRole.action";
		var roleid = $("#qRoleId").val();
		var operUnitid = $("#operUnitid").val();
		var staffId = $("#staffId").val();
		var staffName = $("#staffName").val();
		var params = {
			dataAction : 'server',
			dataType : 'server',
			url : url,
			newPage : 1,
			parms : [ {
				name : 'queryByRoleIn.roleId',
				value : roleid
			}, {
				name : 'queryByRoleIn.operUnitid',
				value : operUnitid
			}, {
				name : 'queryByRoleIn.staffId',
				value : staffId
			}, {
				name : 'queryByRoleIn.staffName',
				value : staffName
			} ]
		};
		var gridManager = $("#roleStafflist").ligerGetGridManager();
		gridManager.setOptions(params);
		gridManager.loadData();
	} 

</script>
</html>