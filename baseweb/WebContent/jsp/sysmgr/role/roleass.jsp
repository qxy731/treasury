<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色分配</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.xcontent { width: 100%;height: 100%;display:inline;}
.left {margin:5px 0 0 5px;float:left;padding: 0;width:40%;}
.right {margin:5px 0 0 5px;float:left;padding: 0;width:50%;}
.params1 tbody tr td {background-color: #EAEFFF;padding:1px 1px 1px 10px;line-height: 28px;}
.params1 input ,.params select {width:200px; border:1px solid #b3bcFF;border-radius:2px 2px 2px 2px;vertical-align: bottom;}
.params1 input {padding-left: 4px; padding-bottom:4px;width: 141px;}
.params1 select {height:22px;}
.params1 input  {height:16px;}
.params1 { width: 100%;}
#operUnitname {width :198px;}
#lefttoptoolbar {wdith:100%}
#righttoptoolbar {wdith:100%}
.mytoolbar {height:24px;
            background: url("${_CONTEXT_PATH}/jwebui/skins/sys/images/panel/panel-toolbar.jpg") repeat-x scroll 0 0 #CEDFEF;
            border:1px solid #EFF7F7; 
            border-top:1px solid #9CBAE7; 
            border-left: 1px solid #9CBAE7;
            border-right: 1px solid #9CBAE7;
            margin-top: 10px
            }
.mytoolbar .l-icon {left: 2px; position: absolute; top: 1px; }
.mytoolbar .l-toolbar-item-hasicon {_margin-left: 4px;padding-left:20px;_margin-top:2px}
.mytoolbar .l-toolbar-item-disable{display: none}
#staffName,#staffId,#unitname,#roleId {width:120px;}
</style>
</head>
<n:page action='com.soule.app.sys.roleass.RoleassAction' />
<body>
<div class="xcontent">
	<div class="left">
		<fieldset class="queryBox"><legend>角色人员查询</legend>
			<table class='params'>
				<tr>
					<td>角色ID</td>
					<td><s:select list="roles" listKey="roleId" listValue="%{roleId + ': ' + roleName}" id="qRoleId" onchange="queryByRole()" emptyOption="true"/></td>
				</tr><tr>
					<td>执行机构</td>
					<td><input type="hidden" id="operUnitid" name="operUnitid"/><input id="operUnitname" name="operUnitname" readonly="readonly" type='text' class="unit_select"/></td>
				</tr>
				
			</table>
		</fieldset>
		<table width="100%">
		<tr><td><br /></td></tr>
		<tr>
			<td></td>
			<td align="center"><input id='reQuery' type='button' value='刷&nbsp;&nbsp;新' class='l-button'/></td>
		</tr>
		<tr><td><br/></td></tr>
        </table>
		<div id='lefttoptoolbar' class='mytoolbar'></div>
		<div id='roleStafflist'></div>
	</div>
	<div class="right">
		<fieldset class="queryBox"><legend>人员查询</legend>
			<table class='params1'>
				<tr>
					<td>员工姓名</td>
					<td><input type='text' id='staffName' name='staffName' width="25%" /></td>
					<td >员工编码</td>
					<td><input type='text' id='staffId' name='staffId' /></td>
					
				</tr>
				<tr>
					<td>所属机构</td>
					<td ><input type='hidden' id='unitId' name='unitId' />
					<input id="unitname" name="unitname" readonly="readonly" type='text' class="unit_select"/></td>
					<td>所属角色</td>
					<td><input type='text' id='roleId' name='roleId' /></td>
					
				</tr>
			</table>
		</fieldset>
		<table width="100%">
		<tr><td><br/></td></tr>
		<tr>
			<td></td>
			<td align="right"><input id='queryStaff' name='query' type='button' value='查&nbsp;&nbsp;询' class='l-button'/></td>
			<td width="5%"></td>
			<td width="5%"></td>
			<td align="left"><input id='reset' name='reset' type='button' value='重&nbsp;&nbsp;置' class='l-button'/></td>
			<td></td>
			
		</tr>
		<tr><td><br/></td></tr>
</table>
		<div id='righttoptoolbar' class='mytoolbar'></div>
		<div id='stafflist'></div>
	</div>
</div>
</body>
<script type='text/javascript'>
	$( function() {
		//输出表格
		$("#lefttoptoolbar").ligerToolBar({items:[
	      {text:'删除人员',name:'delete_btn',icon:'delete',click:deleteStaff}
	      ],
	      width:'99%'
	    });
		
		$("#righttoptoolbar").ligerToolBar({items:[
		  {text:'增加人员',name:'insert_btn',icon:'add',click:insertStaff}
		  ],
		  width:'99%'
		});
		
		$("#roleStafflist").ligerGrid( {
			columns : [ 
				{display : '员工编码',name : 'staffId',align : 'left',  width:'30%'},
				{display : '员工姓名',name : 'staffName',align : 'left',  width:'30%'},
				{display : '操作机构',name : 'operUnitName',align : 'left',  width:'30%'}
		    ],
			//buttons:[
				//{text:'删除人员',name:'delete_btn',clazz:'nbutton'}
			//],
			checkbox:true,
			pageSize : 20,
			sortName : 'staffId',
			height:300,
			onError : function() {
				$.dialogBox.error("查询数据失败");
			}
		});
		$("#stafflist").ligerGrid({
			columns: [
				{ display: '员工编码', name: 'staffId', align: 'left',  width:'20%' },
				{ display: '员工姓名', name: 'staffName', align: 'left' ,  width:'24%'},
				{ display: '所属机构编号', name: 'unitId', align: 'left' ,  width:'24%'},
				{ display: '所属机构名称', name: 'unitName', align: 'left',  width:'24%' }
			],
			checkbox:true,
			pageSize:20,
			//buttons:[
			//	{text:'增加人员',name:'insert_btn',clazz:'nbutton'}
			//],
			sortName: 'staffId',
			height:300,
			onError: function() {
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
		$(".queryBox input[type='text']").each(function(i,item){
			item.value ='';
		});
		$("#unitId").val('');
	}
	function queryByRole() {
		var url = "${_CONTEXT_PATH}/sys/roleass!queryByRole.action";
		var roleid = $("#qRoleId").val();
		var operUnitid = $("#operUnitid").val();
		var params = {
			dataAction : 'server',
			dataType : 'server',
			url : url,
			newPage : 1,
			parms : [ {name : 'queryByRoleIn.roleId',value : roleid},
					{name : 'queryByRoleIn.operUnitid',value : operUnitid} ]
		};
		var gridManager = $("#roleStafflist").ligerGetGridManager();
		gridManager.setOptions(params);
		gridManager.loadData();
	}

	function queryStaff() {
		var url = "${_CONTEXT_PATH}/sys/roleass!queryStaff.action";
		var roleid = $("#roleId").val();
		var staffname = $("#staffName").val();
		var staffid = $("#staffId").val();
		var unitid = $("#unitId").val();
		var params = {
				dataAction:'server',
				dataType:'server',
				url: url,
				newPage:1,
				parms:[
					{name:'queryStaffIn.roleId',value:roleid},
					{name:'queryStaffIn.staffName',value:staffname},
					{name:'queryStaffIn.staffId',value:staffid},
					{name:'queryStaffIn.unitId',value:unitid}
				]
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
			Utils.alert("请先选择一个机构");
			return;
		}
		var rows1 = grid1.getCheckedRows();
		if (rows1.length < 1) {
			Utils.alert("请先选择需要新增的人员");
			return ;
		}
		var mdata = {'roleId':roleid,'operUnitid':unitid};
		var rows1 = grid1.getCheckedRows();
		mdata['insertIn.insertsStr'] = JSON.stringify(rows1);

		var url = "${_CONTEXT_PATH}/sys/roleass!insert.action";
		Utils.ajaxSubmit(url,mdata);
	}
	function deleteStaff() {
		var grid1 = $('#roleStafflist').ligerGetGridManager();
		var rows1 = grid1.getCheckedRows();
		if (rows1.length < 1) {
			Utils.alert("请先选择需要删除的记录");
			return ;
		}
		$.dialogBox.alert('确定删除吗？', function () {
			doDeleteStaff(rows1);
		},true);
		
	}
	function doDeleteStaff(rows) {
		var mdata = {};
		mdata['deleteIn.deletesStr'] = JSON.stringify(rows);

		var url = "${_CONTEXT_PATH}/sys/roleass!delete.action";
		Utils.ajaxSubmit(url,mdata,function(result){
			Utils.alert(result.retMsg);
			queryByRole();
		});
	}

	function openSelectUnit(){
	    Utils.openSelectUnit(null,_CREATE_ORG,setUnitIdName);
	}
	function setUnitIdName(){
		var selectNode=this.iframe.contentWindow.manager.getSelected();
		if(selectNode){
			var unitId=selectNode.data.unitId;
			var unitName=selectNode.data.unitName;
		    $("#operUnitid").val(unitId);
		    $("#operUnitname").val(unitName);
		    queryByRole();
		}
	}
	function openSelectUnit1(){
	    Utils.openSelectUnit(null,_CREATE_ORG,setUnitIdName1);
	}
	function setUnitIdName1(){
		var selectNode=this.iframe.contentWindow.manager.getSelected();
		if(selectNode){
			var unitId=selectNode.data.unitId;
			var unitName=selectNode.data.unitName;
		    $("#unitId").val(unitId);
		    $("#unitname").val(unitName);
		}
	}

	
</script>
</html>