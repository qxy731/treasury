
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.soule.app.pfm.tm.BaseTar"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>定性指标维护</title>
<%@ include file="/comm.jsp" %>
<script type="text/javascript" src="${_CONTEXT_PATH}/jsp/pfm/tarmgr/qtydef/qtydef.js"></script> 
<style type="text/css">
</style>
</head>
<body>
<n:page action="com.soule.app.pfm.tm.qtydef.QtyDefAction" initMethod="initialization"></n:page>
<n:enums keys='tar_type,app_object,tar_sort,data_from'/>
<table class="content">
	<tr>
		<td>
		<fieldset class="queryBox"><legend>查询条件</legend>
		<form id="myform">
		<input id='createOrg' name='createOrg' type='hidden'  value="${logUserInfo.operUnitId}"/>
				<input id='creatOrgName' name='creatOrgName' type="hidden" readonly="readonly"  class='unit_select' value="${logUserInfo.operUnitName}"/>
		<table class="params">
			<tr>
				<td align="right">指标代码</td>
				<td><input id='tarCode' name='tarCode' type="text" /></td>
				<td align="right">指标名称</td>
				<td><input id='tarName' name='tarName' type="text" /></td>
				<td align="right">指标规模分类</td>
				<td>
					<n:select codetype="tar_sort" id="tarSortCode" name='tarSortCode' emptyOption="true"/>
				</td>
			</tr>
			<tr>
			   <td align="right">指标层级属性</td>
				<td>
		         <n:select codetype="tar_type" id="tarType" name='tarType' emptyOption="true"/>
		        </td>
		        <%-- <td align="right">建立部门</td>
				<td>
				<input id='createOrg' name='createOrg' type='hidden'  value="${logUserInfo.operUnitId}"/>
				<input id='creatOrgName' name='creatOrgName' type="text" readonly="readonly"  class='unit_select' value="${logUserInfo.operUnitName}"/>
				</td> --%>
				<td colspan="4">
					<div style="float:right;">
						<input id='query' name='query' type='button' value='查&nbsp;询' class='l-button' style="float:left;margin-right:5px;" />
						<input id='reset' name='reset' type='button' value='重&nbsp;置' class='l-button' style="float:left;margin-right:5px;" />
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
				<div id="toptoolbar"></div>
				<div id="qtydeflist"></div>
			</fieldset>
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
	var grid;
	$(function () {
		$("#toptoolbar").ligerToolBar({items:[
			{text:'基础指标新增',name:'addBaseQtyBtn',icon: 'add',click:insertBaseQty},
			{text:'衍生指标新增',name:'addMixQtyBtn',icon: 'add',click:insertMixQty},
   			{text:'指标修改',name:'updQtyBtn',icon: 'update',click:updateQtyDef},
   			{text:'查看详情',name:'detailInfoBtn',icon: 'look',click:detailInfo},
   			{text:'指标删除',name:'delQtyBtn',icon: 'delete',click:deleteQtyDef}
           ],width:'100%'
   	 });
		//表格初始化 
		$("#qtydeflist").ligerGrid({
			checkbox:true,
			isSingleCheck :true,
			rownumbers:true,
			enumlist: _enum_params , 
			columns: [
						{ display: '指标代码', name: 'tarCode',width:130,align:'left'},
						{ display: '指标名称', name: 'tarName', width:340,align:'left'},
						{ display: '指标规模分类', name: 'tarSortCode', codetype: 'tar_sort',width:140,align:'left'},
						{ display: '指标层级属性', name: 'tarType', codetype: 'tar_type',width:80,align:'left'},
						{ display: '数据来源', name: 'dataSource', codetype: 'data_from',width:80,align:'left'},
						{ display: '建立部门', name: 'unitName', width:230,align:'left'}
						],
			pageSize:20,
			selectRowButtonOnly:true,
			width:'100%',
			height:'100%',
			heightDiff:-20 ,
			isSingleCheck:true,
			onBeforeSelectRow:function(checked, data, rowid, rowdata){
				$(rowid).addClass("l-selected");
				return true;
			},
			onError: function() {
				$.dialogBox.error("查询数据失败");
			} 
		});
		$("#query").bind('click', query);
		$("#reset").bind('click', doClear);
		$("#creatOrgName").bind('click', openSelectUnit);
	});
	
	function updateQtyDef(){
		if(!grid){return;}
		var checkedObjRow=grid.getCheckedRows(); 
		if(checkedObjRow&&checkedObjRow.length>1){
			$.dialogBox.warn("修改数据时只能勾选一项",'提示内容');
			return;
		}
		if(checkedObjRow&&checkedObjRow.length<1){
			$.dialogBox.warn("请勾选1条数据进行修改",'提示内容');
			return;
		}
		var tarCode = checkedObjRow[0].tarCode;
		var tarType = checkedObjRow[0].tarType;
		if("<%=BaseTar.TAR_TYPE_BASE%>"==tarType){
			//修改基础定量指标
			updateBaseQty(tarCode,tarType);
		}else if("<%=BaseTar.TAR_TYPE_MIX%>"==tarType){
			updateMixQty(tarCode,tarType);
		}
	}
	
	
	function query() {
		var tarCode = $("#tarCode").val();
		var tarName = $("#tarName").val();
		var tarType = $("#tarType").val();
		var tarScope = "11000000";
		var createOrg = $("#createOrg").val();
		var tarSortCode = $("#tarSortCode").val();
		var params = {
			dataAction:'server',
			dataType:'server',
			url: '${_CONTEXT_PATH}/qtydefManager/qty-def!query.action',
			newPage:1,
			parms:[{name:'queryIn.tarCode',value:tarCode}
			,{name:'queryIn.tarName',value:tarName}
			,{name:'queryIn.tarType',value:tarType}
			,{name:'queryIn.tarScope',value:tarScope}
			,{name:'queryIn.createOrg',value:createOrg}
			,{name:'queryIn.tarSortCode',value:tarSortCode}]
		};
		grid = $("#qtydeflist").ligerGetGridManager();
		grid.setOptions(params);
		grid.loadData();
		
	}
	
	
	function doClear() {
		/* $(".queryText input[type='text'],.queryText input[type='hidden']").each(function(i,item){
			item.value ='';
		}); */
		$(".queryBox input[type='text']").each(function(i,item){
			item.value ='';
		});
		$("#createOrg").val(_CREATE_ORG);
		$("#creatOrgName").val(_CREATE_ORGNAME);
	}
	
	function deleteQtyDef() {
		var rows = grid.getCheckedRows();
		if (rows.length < 1) {
			$.dialogBox.warn("请先选择需要删除的记录",'提示内容');
			return ;
		}
		var mdata = {"deleteIn.deletesStr":JSON.stringify(rows)};
		var url = _CONTEXT_PATH+'/qtydefManager/qty-def!delete.action';
		$.dialogBox.choice('确认删除吗?',function(){
			Utils.ajaxSubmit(url,mdata, function(result){
				$.dialogBox.info(result.retMsg,query);
			});
		});
	}

	function detailInfo(){
		if(!grid){return;}
		var rows = grid.getCheckedRows();
		if (rows.length < 1) {
			$.dialogBox.warn("请勾选一条数据进行查看");
			return ;
		}
		if (rows.length > 1) {
			$.dialogBox.warn("查看详细信息时只能勾选一项");
			return ;
		}
		var checkedObjRow=grid.getCheckedRows();
		var tarCode = checkedObjRow[0].tarCode;
		var url =_CONTEXT_PATH+'/jsp/pfm/tarmgr/qtydef/qtyDetail.jsp?tarCode='+tarCode;
		$.dialog.open(url,
				{
			      width:740,
			      height:420,
			      title:'定量指标详细信息'
				});
		//this.disabled="disabled";
	}
	
	function openSelectUnit(){
		Utils.openSelectUnit(null,_CREATE_ORG,setUnitIdName);
	}

	function setUnitIdName(){
		var selectNode=this.iframe.contentWindow.manager.getSelected();
		if(selectNode){
			var unitId=selectNode.data.unitId;
			var unitName=selectNode.data.unitName;
			$("#createOrg").val(unitId);
			$("#creatOrgName").val(unitName);
		}
	}
</script>
</html>