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
<style type="text/css">
</style>
</head>
<body>
<n:page action="com.soule.app.pfm.tm.qtydef.QtyDefAction" initMethod="initialization"></n:page>
<n:enums keys='tar_type,app_object,tar_sort,data_from'/>
<table width="100%">
	<tr>
		<td>
		<fieldset class="queryBox"><legend>查询条件</legend>
		<form id="myform">
		<table class="params">
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
			<%-- <tr>
				<td align="right">指标业务分类</td>
				<td>
					<n:select codetype="tar_sort" id="tarSortCode" name='tarSortCode' emptyOption="true"/>
				</td>
			</tr>
			<tr>
			   <td align="right">指标层级属性</td>
				<td>
		         <n:select codetype="tar_type" id="tarType" name='tarType' emptyOption="true"/>
		        </td>
		    </tr> --%>
		</table>
		</form>
		</fieldset>
		</td>
	</tr>
	<tr><td>
		<table width="100%">
			<tr>
				<td align="right" with="50%"><input id='query' name='query' type='button' value='查&nbsp;询' class='l-button' style="margin-right:20px"/></td>
				<td align="left"  with="50%"><input id='reset' name='reset' type='button' value='重&nbsp;置' class='l-button' style="margin-left:20px"/></td>
			</tr>
		</table>
	</td></tr>
	<tr>
		<td>
		<fieldset class="outbox"><legend>查询结果</legend>
			<div id="qtydeflist" style="overflow:auto;"></div>
		</fieldset>
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
var grid;
var _checkbox = false;
$(function () {
		_checkbox = <s:property value='#parameters.dup[0]' />;
		//表格初始化 
		$("#qtydeflist").ligerGrid({
			checkbox: _checkbox,
			enumlist: _enum_params ,
			columns: [
						{ display: '指标代码', name: 'tarCode',width:130,align:'left'},
						{ display: '指标名称', name: 'tarName', width:340,align:'left'},
						{ display: '指标业务分类', name: 'tarSortCode', codetype: 'tar_sort',width:140,align:'left'},
						{ display: '指标层级属性', name: 'tarType', codetype: 'tar_type',width:80,align:'left'},
						{ display: '数据来源', name: 'dataSource', codetype: 'data_from',width:80,align:'left'},
						{ display: '建立国库', name: 'unitName', width:230,align:'left'}
						],
			pageSize:20,
			selectRowButtonOnly:true,
			width:438,
			height:'100%',
			heightDiff:-20,
			onError: function(e) {
				Utils.toIndex(e);
			}
		});
		$("#query").bind('click', query);
		$("#reset").bind('click', doClear);
		$("#creatOrgName").bind('click', openSelectUnit);
	});
	
	
	function query() {
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
	
	//选择指标
	function select(){
		var dup = _checkbox;
		if(dup){
			var rows = grid.getCheckedRows();
			if(rows&&rows.length>0){
				return rows;
			}else{
				$.dialogBox.warn("请至少勾选一条指标。");
			}
		}else{
			var row = grid.getSelectedRow();
			if(row){
				return row;
			}else{
				$.dialogBox.warn("请选中一条指标。");
			}
		}
	}
</script>
</html>