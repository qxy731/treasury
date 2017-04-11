<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色维护</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.inbox ,.outbox {padding: 5px;margin: 2px;border: 1px solid #ccc;}
.params td {background-color: #E2EAFF; padding:2px 2px 2px 10px;}
#reset {width: 80px;}
#query {width: 80px;}
body {
	margin-top: 5px
}
</style>
</head>
<body>
<n:page action='com.soule.app.sys.role.RoleAction' />
<n:enums keys='valid_type'/>
<table class="content">
	<tr>
		<td>
		<fieldset class="queryBox"><legend>查询条件</legend>
		<form id="myform">
		<table class="params">
			<tr>
				<td>角&nbsp;&nbsp;色ID</td>
				<td><input id='roleId' name='roleId' type="text" /></td>
				<td nowrap="nowrap">角色名称</td>
				<td><input id='roleName' name='roleName'type="text" /></td>
				
			</tr>
			<tr>
				<td>状&nbsp;&nbsp;&nbsp;&nbsp;态</td>
				<td><n:select codetype="valid_type" id="roleStatus" name='roleStatus' emptyOption="true"></n:select>
				</td> 
				<td>创&nbsp;建&nbsp;人</td>
				<td><input id='createUser' type="text" ></td>
				
			</tr>
		</table>
		</form>
		</fieldset>
		</td>
	</tr>
	
		 <tr><td>
<br>
<table width="100%">
		<tr>
			<td></td>
			<td align="right"><input id='query' name='query' type="button" value="查&nbsp;&nbsp;询" class="l-button"/></td>
			<td width="5%"></td>
			<td width="5%"></td>
			<td  align="left"><input id='reset' name='reset' type='button' value='重&nbsp;&nbsp;置' class='l-button'/></td>
			<td></td>
		</tr>
</table>
<br>
</td></tr>
	
	 <tr>			
			<td style="width:100%">
				<fieldset class="detailBox"><legend>角色列表</legend>
					<div id='toptoolbar'></div>
					<div id='rolelist'></div>
				</fieldset>
			</td>
		</tr>
</table>
</body>
<script type="text/javascript">
        
	$(function () {
		 $("#toptoolbar").ligerToolBar({items:[
    	 	{text:'新增角色',name:'insert_btn',icon:'insert', click:insertRole},
			{text:'修改角色',name:'update_btn',icon:'update', click:updateRole},
			{text:'删除角色',name:'delete_btn',icon:'delete', click:deleteRole},
			{text:'菜单授权',name:'menuauth_btn',icon:'lock', click:menuAuth},
			{text:'页面和功能授权',name:'page_auth_btn',icon:'lock', click:pageAuth}
           ],
          width:'100%'
		});
		$("#rolelist").ligerGrid({
			checkbox: true,
			enumlist: _enum_params ,
			
			columns: [
			{ display: '角色主键', name: 'roleId', align: 'left', width: '16%' },
			{ display: '角色名称', name: 'roleName',align: 'left',  width: '16%' },
			{ display: '角色描述', name: 'remark', width: '16%',align:'left' }, 
			{ display: '角色状态', name: 'roleStatus', width:  '15%' , codetype: 'valid_type'}, 
			{ display: '创建人', name: 'createUser', width:  '15%'}, 
			{ display: '创建时间', name: 'createTime', width:  '15%'}
			],
			pageSize:20,
			selectRowButtonOnly:true,
			sortName: 'roleId', 
			height:'98%',
			width:'100%',
			onError: function(xrequest,textStatus,error) {
				$.dialogBox.error(xrequest.responseText);
			}
		});

		
		$("#query").bind('click', query);
		$("#reset").bind('click', doClear);

	});
	function insertRole() {
		var url = '${_CONTEXT_PATH}/jsp/sysmgr/role/roleAdd.jsp';
		$.dialogBox.openDialog(url,{title:'新增角色',width:450,height:350});
	}
	function deleteRole() {
		var grid = $("#rolelist").ligerGetGridManager();
		var rows = grid.getCheckedRows();
		if (rows.length < 1) {
			$.dialogBox.warn("请先选择需要删除的记录");
			return ;
		}
		var mdata = {"deleteIn.deletesStr":JSON.stringify(rows)};
		var url = "${_CONTEXT_PATH}/sys/role!delete.action";
		$.dialogBox.choice('确认删除角色吗?',function(){
			Utils.ajaxSubmit(url,mdata, function(result){
				$.dialogBox.info(result.retMsg,query);
			});
		});
	}
	function updateRole() {
		var grid = $("#rolelist").ligerGetGridManager();
		var selected = grid.getSelectedRow();
		if (selected) {
			var url = '${_CONTEXT_PATH}/jsp/sysmgr/role/roleUpdate.jsp?roleid=' + selected.roleId;
			$.dialogBox.openDialog(url,{title:'修改角色',width:450,height:350});
		}
		else {
			$.dialogBox.alert("请先选择需要修改的角色");
		}
	}
	function menuAuth() {
		var grid = $("#rolelist").ligerGetGridManager();
		var selected = grid.getSelectedRow();
		if (selected) {
			var url = '${_CONTEXT_PATH}/jsp/sysmgr/grant/menuauth.jsp?roleId=' + selected.roleId;
			Utils.openTab('menuauth' + selected.roleId,'菜单授权',url);
		}
		else {
			Utils.alert("请先选择一个角色");
		}
	}
	function pageAuth() {
		var grid = $("#rolelist").ligerGetGridManager();
		var selected = grid.getSelectedRow();
		if (selected) {
			var url = '${_CONTEXT_PATH}/jsp/sysmgr/grant/pageauth.jsp?roleId=' + selected.roleId;
			Utils.openTab('pageauth' + selected.roleId,'页面授权',url);
		}
		else {
			Utils.alert("请先选择一个角色");
		}
	}
	function query() {
		var pp = Utils.convertParam('queryIn','myform');
		var params = {
			dataAction:'server',
			dataType:'server',
			url: '${_CONTEXT_PATH}/sys/role!query.action',
			newPage:1,
			parms:pp
		};
		var gridManager = $("#rolelist").ligerGetGridManager(); 
		gridManager.setOptions(params);
		gridManager.loadData();
	}
	function doClear() {
		$(".queryBox input[type='text']").each(function(i,item){
			item.value ='';
		});
	}
</script>
</html>