<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增模型</title>
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
<n:page action='com.soule.app.pfm.tm.model.action.ModelDefAction' />
<body>
	<div width="100%">
		<div class="left">
			<fieldset class="queryBox">
				<legend>模型基础信息</legend>
				<table class='params'>
					<tr>
						<td>模型名称</td>
						<td><input id="modelName" name="modelName" type='text' /></td>
					</tr>
					<tr>
						<td>模型描述</td>
						<td><input id="modelDesc" name="modelDesc" type='text' /></td>
					</tr>
					<tr>
					<td colspan="2"><input id='commit' type='button' value='提&nbsp;交' class='l-button' style="float:right;" /></td>
				</tr>
				</table>
			</fieldset>
			<fieldset class="detailBox">
				<div id='lefttoptoolbar'></div>
				<div id='modelTarlist'></div>
			</fieldset>
		</div>
		<div class="right">
			<fieldset class="queryBox">
				<legend>指标查询</legend>
				<table class='params'>
					<tr>
						<td align="right">指标代码</td>
						<td><input id='tarCode' name='tarCode' type="text" /></td>
					</tr>
					<tr>
						<td align="right">指标名称</td>
						<td><input id='tarName' name='tarName' type="text" /></td>
					</tr>
					<tr>
						<td align="right">建立国库</td>
						<td>
						<input id='createOrg' name='createOrg' type='hidden' value="${logUserInfo.operUnitId}"/>
						<input id='creatOrgName' name='creatOrgName' type="text" readonly="readonly"  class='unit_select' value="${logUserInfo.operUnitName}"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<input id='reset' name='reset' type='button' value='重&nbsp;置' class='l-button' style="float:right;"  />
							<input id='queryTar' name='query' type='button' value='查&nbsp;询' class='l-button' style="float:right;" />
						</td>
					</tr>
				</table>
			</fieldset>			
			<fieldset class="detailBox">
				<div id='righttoptoolbar'></div>
				<div id='tarlist'></div>
			</fieldset>
		</div>
	</div>
</body>
<script type='text/javascript'>
	$(function() {
		//输出表格
		$("#lefttoptoolbar").ligerToolBar({
			items : [ {
				text : '删除指标',
				name : 'delete_btn',
				icon : 'delete',
				click : deleteTar
			} ],
			width : '99%'
		});

		$("#righttoptoolbar").ligerToolBar({
			items : [ {
				text : '增加指标',
				name : 'insert_btn',
				icon : 'add',
				click : insertTar
			} ],
			width : '99%'
		});

		$("#modelTarlist").ligerGrid({
			columns : [{
				display : '指标编号',
				name : 'tarCode',
				align : 'left',
				width : 100
			}, {
				display : '指标名称',
				name : 'tarName',
				align : 'left',
				width : 200
			} ],
			//buttons:[
			//{text:'删除人员',name:'delete_btn',clazz:'nbutton'}
			//],
			checkbox : true,
			pageSize : 20,
			sortName : 'tarCode',
			height : '98%',
			width : '100%',
			onError: function(e) {
				Utils.toIndex(e);
			}
		});
		$("#tarlist").ligerGrid({
			columns : [ {
				display : '指标编号',
				name : 'tarCode',
				align : 'left',
				width : 100
			}, {
				display : '指标名称',
				name : 'tarName',
				align : 'left',
				width : 200
			}  ],
			checkbox : true,
			pageSize : 20,
			sortName : 'tarCode',
			height : '98%',
			width : '100%',
			onError: function(e) {
				Utils.toIndex(e);
			}
		});
		$("#queryTar").bind('click', queryTar);
		$("#reQuery").bind('click', queryByRole);
		$("#reset").bind('click', doClear);
		$("#delete_btn").bind('click', deleteTar);
		$("#insert_btn").bind('click ', insertTar);
		$("#operUnitname").bind('click ', openSelectUnit);
		$("#unitname").bind('click ', openSelectUnit1);
		
		$("#commit").bind('click ', commit);
	})
	function doClear() {
		$(".queryBox input[type='text']").each(function(i, item) {
			item.value = '';
		});
		$("#unitId").val('');
	}

	function queryTar() {
		var tarCode = $("#tarCode").val();
		var tarName = $("#tarName").val();
		var tarScope = "11000000";
		var createOrg = $("#createOrg").val();
		var params = {
			dataAction:'server',
			dataType:'server',
			url: '${_CONTEXT_PATH}/qtydefManager/qty-def!query.action',
			newPage:1,
			parms:[{name:'queryIn.tarCode',value:tarCode}
			,{name:'queryIn.tarName',value:tarName}
			,{name:'queryIn.tarScope',value:tarScope}
			,{name:'queryIn.createOrg',value:createOrg}
			]
		};
		grid = $("#tarlist").ligerGetGridManager(); 
		grid.setOptions(params);
		grid.loadData();
	}
	
	
	function commit() {
		var grid = $('#modelTarlist').ligerGetGridManager();
		var modelName = $("#modelName").val();
		var modelDesc = $("#modelDesc").val();
		
		var rows = grid.getData();
		if (rows.length<1) {
			Utils.alert("请先选择一个指标");
			return;
		}
		var mdata = {
			'insertIn.modelName' : modelName,
			'insertIn.modelDesc' : modelDesc,
			'insertIn.insertsStr' : JSON.stringify(rows)
		};
		var url = "${_CONTEXT_PATH}/modelDef/model-def!insert.action";
		Utils.ajaxSubmit(url, mdata, onSuccess);
	}
	//将指标信息写入模型指标列表中的页面缓存中
	function insertTar() {
		var grid = $('#modelTarlist').ligerGetGridManager();
		var grid1 = $('#tarlist').ligerGetGridManager();
		var rows1 = grid1.getCheckedRows();
		var datas = grid.getData();
		
		if(datas.length>0){
			var i =0;			
			while(i<rows1.length){
				var j = 0 ;
				var flag = true;
				while(j<datas.length){
					flag = true;
					if(rows1[i].tarCode == datas[j].tarCode && datas[j].tarStatus!="0"){
						flag = false;
						break;
					}
					j++;
				}
				if(flag){
					rows1[i].tarStatus="1";
					grid.addRow(rows1[i]);
				}
				i++;
			}
			
		}else{
			var i =0;
			while(i<rows1.length){
				grid.addRow(rows1[i]);
				i++;
			}
		}
	}
	function deleteTar() {
		var grid = $('#modelTarlist').ligerGetGridManager();
		var rows = grid.getCheckedRows();
		if (rows.length < 1) {
			Utils.alert("请先选择需要删除的记录");
			return;
		}
		$.dialogBox.alert('确定删除吗？', function() {
			grid.deleteSelectedRow();
			for(var i=0;i<rows.length;i++){
				rows[i].tarStatus='0';
			}
		}, true);

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
		$.dialogBox.close();
		$.dialogBox.opener.query();
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