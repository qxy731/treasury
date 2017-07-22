<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模型维护</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
/* .params td {background-color: #E2EAFF; padding:2px 2px 2px 10px;}
body {
	margin-top: 5px
} */

</style>
</head>
<body>
<n:page action='com.soule.app.pfm.tm.model.action.ModelDefAction' />
<n:enums keys='valid_type,sex,partime_job_type'/>
<table class="content">
	<tr>
		<td>
		<fieldset class="queryBox"><legend>查询条件</legend>
		<form id="myform">
		<table class='params'>
			<tr>
				<td>模型编号 </td><td><input type='text' id='modelCode' name='modelCode'/></td>
				<td>模型名称 </td><td><input type='text' id='modelName' name='modelName'/></td>
				<td>模型状态 </td>
				<td><n:select codetype="valid_type" id="modelStatus" name='queryIn.modelStatus' emptyOption="true" disabled="false"></n:select></td>
				<td>
				<div style="float:right;">
					<input id='query' name='query' type='button' value='查&nbsp;询' class='l-button' style="float:left;margin-right:5px;"/>
					<input id='reset' name='reset' type='button' value='重&nbsp;置' class='l-button' style="float:left;margin-right:5px;"/>
				</div>				
				</td>
			</tr>
		</table>
		</form>
		</fieldset>
		</td>
	</tr>
	<tr>
		<td>
		  <fieldset class="detailBox"><legend>查询结果</legend>
			<div id='toptoolbar'></div>
		    <div id='modellist'></div>
		  </fieldset>
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
	$(function () {
		
		$("#toptoolbar").ligerToolBar({items:[
		    {text:'新增',name:'insert_btn',icon:'add',click:insertModel},
		    {text:'修改',name:'update_btn',icon:'update',click:updateModel},
		    {text:'删除',name:'delete_btn',icon:'delete',click:deleteModel}
		    ],
		    width:'100%'
		});
		
		$("#modellist").ligerGrid({
			enumlist: _enum_params,
			checkbox: true,
			//buttons:[
			//	{text:'新增',name:'insert_btn',clazz:'nbutton'},
			//	{text:'修改',name:'update_btn',clazz:'nbutton'},
			//	{text:'重置密码',name:'resetPwd_btn',clazz:'nbutton'},
			//	{text:'删除',name:'delete_btn',clazz:'nbutton'}
			//],
			columns: [
						{ display: '模型编号', name: 'modelCode', width: 100, align: 'left' } ,
						{ display: '模型名称', name: 'modelName', width: 150,align: 'left' },
						{ display: '模型状态', name: 'modelStatus',width: 100, align: 'left', codetype: 'valid_type'},
						{ display: '创建人', name: 'createUser', width: 150}, 
						{ display: '创建部门', name: 'createOrg', width: 150}, 
						{ display: '创建时间', name: 'createTime', width: 180,align: 'right' }
					],
			pageSize:20,
			sortName: 'modelCode',
			selectRowButtonOnly:true,
			height:'98%',
			width:'100%'/* ,
			onError: function() {
				Utils.alert("查询数据失败");
			} */
		});

		$("#modellist #insert_btn").bind('click', insertModel);
		$("#modellist #update_btn").bind('click', updateModel);
		$("#modellist #delete_btn").bind('click ', deleteModel);
		$("#query").bind('click', query);
		$("#reset").bind('click', doClear);

	});
	function insertModel() {
		//var url = 'staffManager!insertUI.action';
		var url = '${_CONTEXT_PATH}/jsp/pfm/target/targetModel/modelAdd.jsp';
		var p = {
				id : "insertModel",
				title : '新增模型',
				height:450,
				width:900,
				opacity : 0.07
			}; 
		//Utils.openTab("insertStaff","新增人员",url);
		$.dialogBox.openDialog(url,p);
	}

	function updateModel() {
		var grid = $("#modellist").ligerGetGridManager();
		var selected = grid.getSelectedRow();
		if (!selected) {
			Utils.alert("请先选择需要修改的记录");
			return ;
		}
		var modelCode = selected.modelCode;
		var url = '${_CONTEXT_PATH}/modelDef/model-def!updateUI.action?queryIn.modelCode='+modelCode;
		var p = {
				id : "updateModel",
				title : '修改人员',
				height:'80%',
				width:'80%',
				opacity : 0.07
			}; 
		//var url = '${_CONTEXT_PATH}/jsp/sysmgr/staff/staffAdd.jsp';
		$.dialogBox.openDialog(url,p);
	}
	function deleteModel() {
		var grid = $("#modellist").ligerGetGridManager();
		var rows = grid.getCheckedRows();
		if (rows.length < 1) {
			Utils.alert("请先选择需要删除的记录");
			return ;
		}
		var mdata = {"deleteIn.deletesStr":JSON.stringify(rows)};
		var url = "${_CONTEXT_PATH}/modelDef/model-def!delete.action";
		Utils.ajaxSubmit(url,mdata, function(result){
			Utils.alert(result.retMsg,'提示',function() {
				query();
			});
		});
	}
	function query() {
		var modelCode = $("#modelCode").val();
		var modelName=$("#modelName").val();
		var modelStatus = $("#modelStatus").val();
		var params = {
			dataAction:'server',
			dataType:'server',
			url: '${_CONTEXT_PATH}/modelDef/model-def!query.action',
			newPage:1,
			parms:[{name:'queryIn.modelCode',value:modelCode}
			,{name:'queryIn.modelName',value:modelName}
			,{name:'queryIn.modelStatus',value:modelStatus}
			]
		};
		var gridManager = $("#modellist").ligerGetGridManager(); 
		gridManager.setOptions(params);
		gridManager.loadData();
	}
	function doClear() {
		$(".queryBox input[type='text'],#unitId,#staffStatus").each(function(i,item){
			item.value ='';
			
		});
	}

	
	//选择部门
	   function openSelectUnit(){
	   		Utils.openSelectUnit(null,'',setUnitIdName);
	   }
	   function setUnitIdName(){
			var selectNode=this.iframe.contentWindow.manager.getSelected();
			if(selectNode){
				var unitId=selectNode.data.unitId;
				var unitName=selectNode.data.unitName;
		   		$("#unitId").val(unitId);
		   		$("#unitName").val(unitName);
			}
	   }
	   function lookOrgChangeHis(){
		   var grid = $("#modellist").ligerGetGridManager();
	        var selected = grid.getSelectedRow();
	        if (!selected) {
	            Utils.alert("请先选择需要查看部门变更历史的记录");
	            return ;
	        }
	        var staffId = selected.staffId;
	        var url = '${_CONTEXT_PATH}/jsp/sysmgr/orgchange/orgchange_detail.jsp?staffId='+staffId;
	        $.dialogBox.openDialog(url,{title:'所属部门变更历史',width:'730px',height:'330px'});
	   }
	   function reloadData(){
		   var grid = $("#modellist").ligerGetGridManager();
		   grid.loadData();
	   }
</script>
</html>