<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门维护</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
</style>
</head>
<body>
<n:page action='com.soule.app.sys.unit.UnitAction' />
<n:enums keys='unit_kind,valid_type' />
<div class="content">
<fieldset class="queryBox"><legend>查询条件</legend>
<form id="queryForm" name="queryForm">
<table class="params">
	<tr>
		<td>部门编码</td>
		<td><input id="unitId" type="text" name="unitId" /></td>
		<td>部门名称</td>
		<td><input id="unitName" type="text" name="unitName" /></td>
		<td>上级部门</td>
		<td><input id="superUnitId" type="hidden"
			name="superUnitId" /> <input class='unit_select' type="text"
			id="superUnitName" name="superUnitName" readonly="readonly"
			onclick="openSelectUnit()" /></td>
</tr>
<tr>
	<td>部门级别</td>
	<td><input id="unitLevel" type="text" name="unitLevel" /></td>
	<td>部门状态</td>
	<td><n:select codetype="valid_type" id="unitStatus"
	name='unitStatus' emptyOption="true" disabled="false"></n:select>
</td>
<td colspan="2">
	<div style="float:right;">
		<input id='query' name='query' type="button" value="查&nbsp;询" class="l-button" style="float:left;margin:5px;"/>
		<input id='reset' name='reset' type='button' value='重&nbsp;置' class="l-button" style="float:left;margin:5px;"/>
	<div>
		</td>
	</tr>
</table>
</form>	
</fieldset>
<fieldset class="outbox">
	<legend>部门列表</legend>
	<div id='toptoolbar'></div>
	<div id='unitlist'></div>
</fieldset>
</div>
</body>
<script type="text/javascript">
	$(function() {
		$("#toptoolbar").ligerToolBar({
			items : [ {
				text : '查看详情',
				name : 'detail_btn',
				icon : 'detail',
				enabled : '<n:auth key='unit_detail'/>',
				click : detailUnit
			}, {
				text : '新&nbsp;增',
				name : 'insert_btn',
				icon : 'insert',
				enabled : '<n:auth key='unit_insert'/>',
				click : insertUnit
			}, {
				text : '修&nbsp;改',
				name : 'update_btn',
				icon : 'update',
				enabled : '<n:auth key='unit_modify'/>',
				click : updateUnit
			} ],
			width : '100%'
		});
		$("#unitlist").ligerGrid({
			enumlist : _enum_params,
			enabledSort : false,
			columns : [ {
				display : '部门编号',
				name : 'unitId',
				width : '7%',
				align : 'left'
			}, {
				display : '部门名称',
				name : 'unitName',
				width : '13%',
				align : 'left'
			}, {
				display : '上级部门编号',
				name : 'superUnitId',
				align : 'left'
			},
			//{ display: '上级部门名称', name: 'superUnitName', width:'11%',align:'left'},
			{
				display : '部门级别',
				name : 'unitLevel',
				width : '5%'
			}/* , {
				display : '部门类型',
				name : 'unitKind',
				width : '5%',
				codetype : 'unit_kind'
			} */, {
				display : '部门地址',
				name : 'unitAddress',
				width : '13%',
				align : 'left'
			}, {
				display : '清算国库代码',
				name : 'settUnitId',
				width : '8%',
				align : 'left'
			}, {
				display : '管理国库代码',
				name : 'mgrUnitId',
				width : '8%',
				align : 'left'
			}, {
				display : '启用日期',
				name : 'startDate',
				width : '10%',
				align : 'left'
			}, {
				display : '废止日期',
				name : 'endDate',
				width : '10%',
				align : 'left'
			}, {
				display : '创建人',
				name : 'createUser',
				width : '5%',
				align : 'left'
			}, {
				display : '状态',
				name : 'unitStatus',
				width : '5%',
				codetype : 'valid_type'
			} ],
			checkbox:true,
			enabledEdit : true,
			pageSize : 20,
			sortName : 'unitId',
			height : '98%',
			width : '100%',
			onError : function() {
				Utils.alert("查询数据失败。");
			}
		});
		$("#query").bind('click', query);
		$("#reset").bind('click', doClear);
	});
	//重置
	function doClear() {
		$("#unitId").val("");
		$("#unitName").val("");
		$("#superUnitId").val("");
		$("#superUnitName").val("");
		$("#unitLevel").val("");
		//$("#unitKind").val("");
		$("#unitStatus").val("");
	}
	//选择部门
	function openSelectUnit() {
		Utils.openSelectUnit('', _CREATE_ORG, setUnitIdName);
	}
	function setUnitIdName(unitId, unitName) {
		var selectNode = this.iframe.contentWindow.manager.getSelected();
		if (selectNode) {
			var unitId = selectNode.data.unitId;
			var unitName = selectNode.data.unitName;
			$("#superUnitId").val(unitId);
			$("#superUnitName").val(unitName);
		}
	}

	function query() {
		var unitId = $("#unitId").val();
		var unitName = $("#unitName").val();
		var superUnitId = $("#superUnitId").val();
		var unitLevel = $("#unitLevel").val();
		//var unitKind = $("#unitKind").val();
		var unitStatus = $("#unitStatus").val();
		var params = {
			dataAction : 'server',
			dataType : 'server',
			url : '${_CONTEXT_PATH}/sys/unit!query.action',
			newPage : 1,
			parms : [ {
				name : 'queryIn.unitId',
				value : unitId
			}, {
				name : 'queryIn.unitName',
				value : unitName
			}, {
				name : 'queryIn.superUnitId',
				value : superUnitId
			}, {
				name : 'queryIn.unitLevel',
				value : unitLevel
			}/* , {
				name : 'queryIn.unitKind',
				value : unitKind
			} */, {
				name : 'queryIn.unitStatus',
				value : unitStatus
			} ]
		};
		var gridManager = $("#unitlist").ligerGetGridManager();
		gridManager.setOptions(params);
		gridManager.loadData();
	}
	function insertUnit() {
		var p = {
			id : "insertUnitDetail",
			title : '编辑部门信息',
			width : 500,
			height : 450,
			opacity : 0.07
		};
		var url = '${_CONTEXT_PATH}/jsp/sysmgr/unit/unitAdd.jsp';
		$.dialogBox.openDialog(url, p);
	}
	//编辑部门信息
	function updateUnit() {
		var grid = $("#unitlist").ligerGetGridManager();
		var selected = grid.getSelectedRow();
		if (!selected) {
			$.dialogBox.alert("请先选择需要修改的记录");
			return;
		}
		var unitId = selected.unitId;
		var p = {
			id : "updateUnitDetail",
			title : '编辑部门信息',
			width : 500,
			height : 450,
			opacity : 0.07
		};
		var url = '${_CONTEXT_PATH}/sys/unit!openUnitById.action?opType=1&queryIn.unitId='
				+ unitId;
		$.dialogBox.openDialog(url, p);
	}
	//删除部门信息
	function deleteUnit() {
		var grid = $("#unitlist").ligerGetGridManager();
		var selected = grid.getSelectedRow();
		if (!selected) {
			$.dialogBox.alert("请先选择需要修改的记录");
			return;
		}
		Utils
				.alert(
						"你确定要删除当前部门，如果继续，请点击“确定”按钮?",
						'请确认',
						function() {
							var unitId = selected.unitId;

							var url = "${_CONTEXT_PATH}/sys/unit!deleteUnitModel.action?queryIn.unitId="
									+ unitId;
							Utils.ajaxSubmit(url, '', function(result) {
								$.dialogBox.info(result.retMsg, function() {
									query();
								});
							});
						}, true);
	}
	function detailUnit() {
		var grid = $("#unitlist").ligerGetGridManager();
		var selected = grid.getSelectedRow();
		if (!selected) {
			Utils.alert("请先选择需要查看的记录");
			return;
		}
		var unitId = selected.unitId;
		var p = {
			id : "openUnitDetail",
			title : '查看部门信息',
			width : 500,
			height : 450,
			opacity : 0.07
		};
		var url = '${_CONTEXT_PATH}/sys/unit!openUnitById.action?queryIn.unitId='
				+ unitId;
		$.dialogBox.openDialog(url, p, null, true);
		//$.dialog.open(url,{height:450,width:700,title:,cancel:true});  
	}
</script>
</html>