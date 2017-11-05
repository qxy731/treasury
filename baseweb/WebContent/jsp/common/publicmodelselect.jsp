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
<body style="width:450;">
<n:page action='com.soule.app.pfm.tm.model.action.ModelDefAction' />
<n:enums keys='valid_type,sex,partime_job_type'/>
<table style="width:100%;">
	<tr>
		<td>
		<form id="myform">
		<table class='params' width="100%">
			<tr>
				<td>模型编号 </td><td><input type='text' id='modelCode' name='modelCode'/></td>
				<td>
					<input id='query' name='query' type='button' value='查&nbsp;询' class='l-button' style="float:left;margin-right:5px;"/>
				</td>
			</tr>
			<tr>
				<td>模型名称 </td><td><input type='text' id='modelName' name='modelName'/></td>
				<td>
					<input id='reset' name='reset' type='button' value='重&nbsp;置' class='l-button' style="float:left;margin-right:5px;"/>
				</td>
			</tr>
		</table>
		</form>
		</td>
	</tr>
	<tr>
		<td>
		  <fieldset class="detailBox"><legend>查询结果</legend>
		    <div id='modellist'></div>
		  </fieldset>
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
var gridManager;
var _checkbox = false;
	$(function () {
		_checkbox = <s:property value='#parameters.dup[0]' />;
		
		$("#modellist").ligerGrid({
			checkbox: _checkbox,
			enumlist: _enum_params,
			columns: [
						{ display: '模型编号', name: 'modelCode', width: 200, align: 'left' } ,
						{ display: '模型名称', name: 'modelName', width: 150,align: 'left' }
					],
			pageSize:20,
			sortName: 'modelCode',
			selectRowButtonOnly:true,
			height:'98%',
			width:435,
			onError: function(e) {
				Utils.toIndex(e);
			}
		});

		$("#query").bind('click', query);
		$("#reset").bind('click', doClear);

	});

	function query() {
		var modelCode = $("#modelCode").val();
		var modelName=$("#modelName").val();
		var params = {
			dataAction:'server',
			dataType:'server',
			url: '${_CONTEXT_PATH}/modelDef/model-def!query.action',
			newPage:1,
			parms:[{name:'queryIn.modelCode',value:modelCode}
			,{name:'queryIn.modelName',value:modelName}
			]
		};
		gridManager = $("#modellist").ligerGetGridManager(); 
		gridManager.setOptions(params);
		gridManager.loadData();
	}
	function doClear() {
		$(".queryBox input[type='text'],#unitId,#staffStatus").each(function(i,item){
			item.value ='';
			
		});
	}

	
	//选择国库
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
	   
	   function reloadData(){
		   var grid = $("#modellist").ligerGetGridManager();
		   grid.loadData();
	   }
	   
	   function select(){
			var dup = _checkbox;
			if(dup){
				var rows = gridManager.getCheckedRows();
				if(rows&&rows.length>0){
					return rows;
				}else{
					$.dialogBox.warn("请至少勾选一条指标。");
				}
			}else{
				var row = gridManager.getSelectedRow();
				if(row){
					return row;
				}else{
					$.dialogBox.warn("请选中一条指标。");
				}
			}
		}
</script>
</html>