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
<n:enums keys='valid_type,sex,partime_job_type'/>
<table class="content" width="100%">
	<tr>
		<td>
		<fieldset class="queryBox"><legend>查询条件</legend>
		<form id="myform">
		<table class='params'>
			<tr>
				<td>数据日期</td>
				<td><input type='text' id='dataDate' name='dataDate' /></td>
				<td>所属国库</td>
				<td><input type='hidden' id='unitId' name='unitId' /><input id="unitName" type='text' name="unitName" readonly="readonly" onclick="openSelectUnit()" class="unit_select"/></td>
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
		<tr><td>
		  <fieldset class="outbox"><legend>查询结果</legend>
			<div id='toptoolbar'></div>
		   <div id='reportlist'></div>
		   </fieldset>
		</td>
	   </tr>
	   </table>
</body>
<script type="text/javascript">
	$(function () {
		$("#dataDate").ligerDateEditor();
		/* $("#toptoolbar").ligerToolBar({items:[
		    {text:'新增',name:'insert_btn',icon:'add',click:insertStaff},
		    {text:'修改',name:'update_btn',icon:'update',click:updateStaff},
		    {text:'重置密码',name:'resetPwd_btn',icon:'updatePass',click:updateLogonPwd},
		    {text:'删除',name:'delete_btn',icon:'delete',click:deleteStaff},
		    {text:'所属国库变更历史',name:'lookHis_btn',icon:'lookHis',click:lookOrgChangeHis}
		    ],
		    width:'100%'
		});
		 */
		$("#reportlist").ligerGrid({
			enumlist: _enum_params,
			checkbox: true,
			//buttons:[
			//	{text:'新增',name:'insert_btn',clazz:'nbutton'},
			//	{text:'修改',name:'update_btn',clazz:'nbutton'},
			//	{text:'重置密码',name:'resetPwd_btn',clazz:'nbutton'},
			//	{text:'删除',name:'delete_btn',clazz:'nbutton'}
			//],
			columns: [
						{ display: '所属国库', name: 'unitName', width: '25%', align: 'left' },
						{ display: '指标编号', name: 'tarCode', width: '25%', align: 'left' },
						{ display: '指标名称', name: 'tarName', width: '25%', align: 'left' },
						{ display: '指标值', name: 'tarValue', width: '25%',align: 'left' }
					],
			pageSize:20,
			sortName: 'tarCode',
			selectRowButtonOnly:true,
			height:'98%',
			width:'100%',
			onError: function(e) {
				Utils.toIndex(e);
			}
		});

		$("#query").bind('click', query);
		$("#reset").bind('click', doClear);

	});
	
	function query() {
		var unitId = $("#unitId").val();
		var dataDate =$("#dataDate").val();
		var params = {
			dataAction:'server',
			dataType:'server',
			url: '${_CONTEXT_PATH}/report/user-report!query.action',
			newPage:1,
			parms:[{name:'queryIn.dataDate',value:dataDate}
			,{name:'queryIn.unitId',value:unitId}
			]
		};
		var gridManager = $("#reportlist").ligerGetGridManager(); 
		gridManager.setOptions(params);
		gridManager.loadData();
	}
	function doClear() {
		$(".queryBox input[type='text'],#unitId").each(function(i,item){
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
	  /*  function lookOrgChangeHis(){
		   var grid = $("#stafflist").ligerGetGridManager();
	        var selected = grid.getSelectedRow();
	        if (!selected) {
	            Utils.alert("请先选择需要查看国库变更历史的记录");
	            return ;
	        }
	        var staffId = selected.staffId;
	        var url = '${_CONTEXT_PATH}/jsp/sysmgr/orgchange/orgchange_detail.jsp?staffId='+staffId;
	        $.dialogBox.openDialog(url,{title:'所属国库变更历史',width:'730px',height:'330px'});
	   } */
	   function reloadData(){
		   var grid = $("#reportlist").ligerGetGridManager();
		   grid.loadData();
	   }
</script>
</html>