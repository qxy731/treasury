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
</style>
</head>
<body>
<n:page action='com.soule.app.sys.staff.StaffAction' />
<n:enums keys='valid_type,sex,partime_job_type,education_type'/>
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
				<td>所属国库</td>
				<td><input type='hidden' id='unitId' name='unitId' /><input id="unitName" type='text' name="unitName" readonly="readonly" onclick="openSelectUnit()" class="unit_select"/></td>
				<td colspan="2">
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
		  <fieldset class="outbox"><legend>查询结果</legend>
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
		    {text:'删除',name:'delete_btn',icon:'delete',click:deleteStaff}/* ,
		    {text:'所属国库变更历史',name:'lookHis_btn',icon:'lookHis',click:lookOrgChangeHis} */
		    ],
		    width:'100%'
		});
		
		$("#stafflist").ligerGrid({
			enumlist: _enum_params,
			checkbox: true,
			isSingleCheck :true,
			//buttons:[
			//	{text:'新增',name:'insert_btn',clazz:'nbutton'},
			//	{text:'修改',name:'update_btn',clazz:'nbutton'},
			//	{text:'重置密码',name:'resetPwd_btn',clazz:'nbutton'},
			//	{text:'删除',name:'delete_btn',clazz:'nbutton'}
			//],
			columns: [
						{ display: '员工编号', name: 'staffId', width: 80, align: 'left' },
						{ display: '员工姓名', name: 'staffName', width:80,align: 'left' },
						{ display: '所属国库名称', name: 'unitName',width: 200, align: 'left' },
						{ display: '员工级别', name: 'staffLevel', width: 80,align: 'right' },
						{ display: '员工状态', name: 'staffStatus', width: 80 , codetype: 'valid_type'}, 
						{ display: '性别', name: 'sex', width: 40,align: 'left' , codetype: 'sex'},
						{ display: '身份证号', name: 'certNo', width: 120,align: 'left' },
						{ display: '学历', name: 'education', width: 80,align: 'left',codetype: 'education_type'},
						{ display: '专兼职', name: 'partTimeJob', width:60,align: 'left',codetype: 'partime_job_type'},
						{ display: '办公电话', name: 'officePhone', width:60,align: 'left'},
						{ display: '手机', name: 'mobilePhone', width:60,align: 'left'},
						{ display: '地址', name: 'address', width: 200,align: 'left'},
						{ display: '创建人', name: 'createUser', width:80,align: 'left' }
					],
			pageSize:20,
			sortName: 'staffId',
			selectRowButtonOnly:true,
			height:'98%',
			width:'100%',
			onError: function(e) {
				Utils.toIndex(e);
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
		var url = '${_CONTEXT_PATH}/jsp/sysmgr/staff/staffAdd.jsp';
		var p = {
				id : "insertStaff",
				title : '新增人员',
				width : 620,
				height : 230,
				opacity : 0.07
			}; 
		$.dialogBox.openDialog(url,p);
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
		var p = {
				id : "updateStaff",
				title : '修改人员',
				width : 620,
				height : 204,
				opacity : 0.07
			}; 
		//var url = '${_CONTEXT_PATH}/jsp/sysmgr/staff/staffAdd.jsp';
		$.dialogBox.openDialog(url,p);
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
		$.dialogBox.confirm("您确定要删除当前记录吗？",function(){
			Utils.ajaxSubmit(url,mdata, function(result){
				$.dialogBox.info(result.retMsg);
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
		$(".queryBox input[type='text'],#unitId,#staffStatus").each(function(i,item){
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
		$.dialogBox.confirm("您确定为当前记录重置密码吗？",function(){
			Utils.ajaxSubmit(url,mdata);
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
	   function lookOrgChangeHis(){
		   var grid = $("#stafflist").ligerGetGridManager();
	        var selected = grid.getSelectedRow();
	        if (!selected) {
	            Utils.alert("请先选择需要查看国库变更历史的记录");
	            return ;
	        }
	        var staffId = selected.staffId;
	        var url = '${_CONTEXT_PATH}/jsp/sysmgr/orgchange/orgchange_detail.jsp?staffId='+staffId;
	        $.dialogBox.openDialog(url,{title:'所属国库变更历史',width:'730px',height:'330px'});
	   }
	   function reloadData(){
		   var grid = $("#stafflist").ligerGetGridManager();
		   grid.loadData();
	   }
</script>
</html>