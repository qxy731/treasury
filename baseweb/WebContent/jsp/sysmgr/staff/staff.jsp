<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工维护</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
body {
	margin-top: 5px
}
</style>
</head>
<body>
<n:page action='com.soule.app.sys.staff.StaffAction' />
<n:enums keys='valid_type,sex'/>
<table class="content" cellpadding="5">
	<tr>
		<td>
		<fieldset class="queryBox"><legend>查询条件</legend>
		<form id="myform">
		<table class='params'>
			<tr>
				<td>员工ID </td><td><input type='text' id='staffId' name='staffId'/></td>
				<td>员工姓名 </td><td><input type='text' id='staffName' name='staffName'/></td>
				<td>员工级别 </td><td><input type='text' id='staffLevel' name='staffLevel'/></td>
			</tr>
			<tr>
				<td>员工状态 </td>
				<td><n:select codetype="valid_type" id="staffStatus" name='queryIn.staffStatus' emptyOption="true" disabled="false"></n:select></td>
				<td>所属机构</td>
				<td><input type='hidden' id='unitId' name='unitId' /><input id="unitName" type='text' name="unitName" readonly="readonly" onclick="openSelectUnit()" class="unit_select"/></td>
				<td colspan="2">
<!-- 				<input id='query' name='query' type="button" value="查询" class="nbutton"/>&nbsp;<input id='reset' name='reset' type="button" value='重置' class="nbutton"/> -->
				</td>
			</tr>
		</table>
		
		</form>
		</fieldset>
		<table width="100%">
		<tr><td><br/></td></tr>
		<tr>
			<td></td>
			<td align="right"><input id='query' name='query' type='button' value='查&nbsp;&nbsp;询' class='l-button'/></td>
			<td width="5%"></td>
			<td width="5%"></td>
			<td align="left"><input id='reset' name='reset' type='button' value='重&nbsp;&nbsp;置' class='l-button'/></td>
			<td></td>
		</tr>
		<tr><td><br/></td></tr>
</table>
		</td>
	</tr>
	<tr>
		<td>
		  <fieldset class="detailBox"><legend>查询结果</legend>
			<div id='toptoolbar'></div>
		   <div id='stafflist'></div>
		   </fieldset>
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
	$(function () {
		
		$("#toptoolbar").ligerToolBar({items:[
		    {text:'新增',name:'insert_btn',icon:'add',click:insertStaff},
		    {text:'修改',name:'update_btn',icon:'update',click:updateStaff},
		    {text:'重置密码',name:'resetPwd_btn',icon:'updatePass',click:updateLogonPwd},
		    {text:'删除',name:'delete_btn',icon:'delete',click:deleteStaff},
		    {text:'所属机构变更历史',name:'lookHis_btn',icon:'lookHis',click:lookOrgChangeHis}
		    ],
		    width:'100%'
		});
		
		$("#stafflist").ligerGrid({
			enumlist: _enum_params,
			checkbox: true,
			//buttons:[
			//	{text:'新增',name:'insert_btn',clazz:'nbutton'},
			//	{text:'修改',name:'update_btn',clazz:'nbutton'},
			//	{text:'重置密码',name:'resetPwd_btn',clazz:'nbutton'},
			//	{text:'删除',name:'delete_btn',clazz:'nbutton'}
			//],
			columns: [
						{ display: '员工编号', name: 'staffId', width: '10%', align: 'left' },
						{ display: '员工姓名', name: 'staffName', width: '15%',align: 'left' },
						{ display: '所属机构名称', name: 'unitName',width: '20%', align: 'left' },
						{ display: '员工级别', name: 'staffLevel', width: '10%',align: 'right' },
						{ display: '员工状态', name: 'staffStatus', width: '10%' , codetype: 'valid_type'}, 
						{ display: '性别', name: 'sex', width: '10%',align: 'left' , codetype: 'sex'},
						{ display: '创建人', name: 'createUser', width: '15%',align: 'left' }
					],
			pageSize:20,
			sortName: 'staffId',
			selectRowButtonOnly:true,
			height:300,
			width:'100%',
			onError: function() {
				Utils.alert("查询数据失败");
			}
		});

		$("#stafflist #insert_btn").bind('click', insertStaff);
		$("#stafflist #update_btn").bind('click', updateStaff);
		$("#stafflist #delete_btn").bind('click ', deleteStaff);
		$("#stafflist #resetPwd_btn").bind('click ', updateLogonPwd);
		$("#query").bind('click', query);
		$("#reset").bind('click', doClear);

	});
	function insertStaff() {
		//var url = 'staffManager!insertUI.action';
		var url = '${_CONTEXT_PATH}/jsp/sysmgr/staff/staffAdd.jsp';
		//Utils.openTab("insertStaff","新增人员",url);
		$.dialogBox.openDialog(url,'新增人员');
	}

	function updateStaff() {
		var grid = $("#stafflist").ligerGetGridManager();
		var selected = grid.getSelectedRow();
		if (!selected) {
			Utils.alert("请先选择需要修改的记录");
			return ;
		}
		var staffId = selected.staffId;
		var url = '${_CONTEXT_PATH}/sys/staff!updateUI.action?queryIn.staffId='+staffId;
		//var url = '${_CONTEXT_PATH}/jsp/sysmgr/staff/staffAdd.jsp';
		$.dialogBox.openDialog(url,'修改人员');
	}
	function deleteStaff() {
		var grid = $("#stafflist").ligerGetGridManager();
		var rows = grid.getCheckedRows();
		if (rows.length < 1) {
			Utils.alert("请先选择需要删除的记录");
			return ;
		}
		var mdata = {"deleteIn.deletesStr":JSON.stringify(rows)};
		var url = "${_CONTEXT_PATH}/sys/staff!delete.action";
		Utils.ajaxSubmit(url,mdata, function(result){
			Utils.alert(result.retMsg,'提示',function() {
				query();
			});
		});
	}
	function query() {
		var staffId = $("#staffId").val();
		var staffName=$("#staffName").val();
		var staffLevel = $("#staffLevel").val();
		var staffStatus = $("#staffStatus").val();
		var unitId = $("#unitId").val();
		var params = {
			dataAction:'server',
			dataType:'server',
			url: '${_CONTEXT_PATH}/sys/staff!query.action',
			newPage:1,
			parms:[{name:'queryIn.staffId',value:staffId}
			,{name:'queryIn.staffName',value:staffName}
			,{name:'queryIn.staffLevel',value:staffLevel}
			,{name:'queryIn.staffStatus',value:staffStatus}
			,{name:'queryIn.unitId',value:unitId}
			]
		};
		var gridManager = $("#stafflist").ligerGetGridManager(); 
		gridManager.setOptions(params);
		gridManager.loadData();
	}
	function doClear() {
		$(".queryBox input[type='text'],#unitId").each(function(i,item){
			item.value ='';
		});
	}

	function updateLogonPwd() {
		var grid = $("#stafflist").ligerGetGridManager();
		var selected = grid.getSelectedRow();
		if (!selected) {
			Utils.alert("请先选择需要密码重置的记录");
			return ;
		}
		var staffId = selected.staffId;
		var url = '${_CONTEXT_PATH}/sys/longon!resetLogonPwd.action';
		var mdata={"logonInfoPo.staffId":staffId};
		Utils.ajaxSubmit(url,mdata);
	}
	//选择组织
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
		   var grid = $("#stafflist").ligerGetGridManager();
	        var selected = grid.getSelectedRow();
	        if (!selected) {
	            Utils.alert("请先选择需要查看机构变更历史的记录");
	            return ;
	        }
	        var staffId = selected.staffId;
	        var url = '${_CONTEXT_PATH}/jsp/sysmgr/orgchange/orgchange_detail.jsp?staffId='+staffId;
	        $.dialogBox.openDialog(url,{title:'所属机构变更历史',width:'730px',height:'330px'});
	   }
	   function reloadData(){
		   var grid = $("#stafflist").ligerGetGridManager();
		   grid.loadData();
	   }
</script>
</html>