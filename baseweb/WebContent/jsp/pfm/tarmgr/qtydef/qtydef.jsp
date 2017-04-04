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
.content{width:95%;}
</style>
</head>
<body>
<n:page action="com.soule.app.pfm.tm.qtydef.QtyDefAction" initMethod="initialization"></n:page>
<n:enums keys='tar_type,app_object,tar_sort'/>
<input name="ctx" type="hidden" value="${_CONTEXT_PATH}"/>
<table class="content">
	<tr>
		<td>
		<fieldset class="queryText"><legend>查询条件</legend>
		<form id="myform">
		<table class="params">
			<tr>
				<td align="right">指标代码</td>
				<td><input id='tarCode' name='tarCode' type="text" /></td>
				<td align="right">指标名称</td>
				<td><input id='tarName' name='tarName' type="text" /></td>
				<td align="right">建立机构</td>
				<td>
				<input id='createOrg' name='createOrg' type='hidden'  value="${logUserInfo.operUnitId}"/>
				<input id='creatOrgName' name='creatOrgName' type="text" readonly="readonly"  class='unit_select' value="${logUserInfo.operUnitName}"/>
				</td>
			</tr>
			<tr>
				<td align="right">指标分类</td>
				<td>
					<n:select codetype="tar_sort" id="tarSortCode" name='tarSortCode' emptyOption="true"/>
				</td>			  
			   <td align="right">指标类型</td>
				<td>
		         <n:select codetype="tar_type" id="tarType" name='tarType' emptyOption="true"/>
		        </td>
		        <td></td><td></td>
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
		<form>
			<fieldset class="detailList"><legend>查询结果</legend>
				<div id="toptoolbar"></div>
				<div id="qtydeflist" style="overflow:auto;"></div>
			</fieldset>
		</form>
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
var grid;
	$(function () {
		$("#toptoolbar").ligerToolBar({items:[		  
   			{text:'修改定量指标',name:'updQtyBtn',icon: 'update',click:updateQtyDef},
   			//{text:'删除定量指标',name:'delQtyBtn',icon: 'delete',click:deleteQtyDef},
   			/* {text:'模板下载',name:'importModelBtn',icon:'export',click:downloadTemplateFile},
   			{text:'批量导入',name:'muchImportBtn',icon: 'import',click:importFile}, */
   			{text:'详细信息',name:'detailInfoBtn',icon: 'look',click:detailInfo}
           ],width:'100%'
   	 });
		//表格初始化 
		$("#qtydeflist").ligerGrid({
			checkbox: true,
			enumlist: _enum_params ,
			columns: [
						{ display: '指标代码', name: 'tarCode',width:130,align:'left'},
						{ display: '指标名称', name: 'tarName', width:340,align:'left'},
						{ display: '指标分类', name: 'tarSortName', width:140,align:'left'}, 						
						{ display: '指标类型', name: 'tarType', width:80,codetype: 'tar_type',align:'left'},
						{ display: '建立机构', name: 'unitName', width:230,align:'left'}
						],
			pageSize:20,
			selectRowButtonOnly:true,
			width:'100%',
			height:'100%',
			heightDiff:-20,
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
		}else if("<%=BaseTar.TAR_TYPE_HANDLE%>"==tarType){
			updateHandleQty(tarCode,tarType);
		}
	}
	//修改基础定量指标
	function updateBaseQty(tarCode,tarType){
		var url = '${_CONTEXT_PATH}/jsp/pfm/tarmgr/qtydef/baseQtydefUpd.jsp?tarCode='+tarCode+"&ts=" + new Date().getTime();
		Utils.removeTabById("updateBaseQtyTar");
		Utils.openTab("updateBaseQtyTar","修改基础定量指标",url);
	}
	//修改复合定量指标
	function updateMixQty(tarCode,tarType){
	  var url = '${_CONTEXT_PATH}/jsp/pfm/tarmgr/qtydef/mixQtydefUpd.jsp?tarCode='+tarCode+"&ts=" + new Date().getTime();
	  Utils.removeTabById("updateMixQtyTar");
	  Utils.openTab("updateMixQtyTar","修改复合定量指标",url);
	}
	//修改手工定量指标
	function updateHandleQty(tarCode,tarType){
		var url = '${_CONTEXT_PATH}/jsp/pfm/tarmgr/qtydef/handleQtydefUpd.jsp?tarCode='+tarCode+"&ts=" + new Date().getTime();
		Utils.removeTabById("updateHandleQtyTar");
		Utils.openTab("updateHandleQtyTar","修改手工定量指标",url);
	}
	function deleteQtyDef() {
		var rows = grid.getCheckedRows();
		if (rows.length < 1) {
			$.dialogBox.warn("请先选择需要删除的记录",'提示内容');
			return ;
		}
		var mdata = {"deleteIn.deletesStr":JSON.stringify(rows)};
		var url = '${_CONTEXT_PATH}/qtydefManager!delete.action';
		$.ligerDefaults.DialogString.titleMessage = '删除定量指标';
		$.ligerDialog.confirm('确认删除吗?', function (yes) {
            if(yes){
            	$.ajax({
        			url: url,
        			type:'POST',
        			data: mdata,
        			success: function(result){
            		var obj=result;
        				if("I0000"==obj.retCode){
        					$.ligerDialog.success('删除成功');
        					query();
        				}else if("W0000"==obj.retCode){
        					$.ligerDialog.warn('没有一条记录被删除')
        				}
        			},
        			error:function(result){
        				$.dialogBox.error("出错了，请检查网络链接。",'提示内容');
        			}
        		});
            }
		});
	}
	
	function query() {
		var tarCode = $("#tarCode").val();
		var tarName = $("#tarName").val();
		var tarType = $("#tarType").val();
		var tarScope = getTarScope();
		var createOrg = $("#createOrg").val();
		var tarSortCode = $("#tarSortCode").val();
		var params = {
			dataAction:'server',
			dataType:'server',
			url: '${_CONTEXT_PATH}/qtydefManager!query.action',
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
	function detailInfo(){
		if(!grid){return;}
		var rows = grid.getCheckedRows();
		if (rows.length < 1) {
			$.dialogBox.warn("请勾选1条数据进行查看");
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
			      width:700,
			      height:335,
			      title:'定量指标详细信息',
			      close:function(){
			          $("#detailInfoBtn").attr("disabled",false);
		          }
				});
		this.disabled="disabled";
	}
	function importFile(){
		var url = '${_CONTEXT_PATH}/jsp/pfm/tarmgr/tarQtyUploadFile.jsp';
		Utils.removeTabById("tarQtyUploadFile");
		Utils.openTab("tarQtyUploadFile","批量导入定量指标",url);
	}
	function downloadTemplateFile(){
		location='${_CONTEXT_PATH}/download.action?fileName=QtydefTemplateFile.xls';
	}
	function doClear() {
		$(".queryText input[type='text'],.queryText input[type='hidden']").each(function(i,item){
			item.value ='';
		});
		//$("#createOrg")[0].value='';
		$("input[name=tarScope]").each(function (){
            this.checked=false;
		});
		$("#createOrg").val(_CREATE_ORG);
		$("#creatOrgName").val(_CREATE_ORGNAME);
	}
	function getTarScope(){
		var tarScope=null;
		var orgPersonCode="<%=BaseTar.APPOBJ_ORGANDPERCODE%>";
		var checkedbox=$("form input[name='tarScope']:checkbox:checked");
		if(checkedbox.length==2){
			tarScope= orgPersonCode;
		}else if(checkedbox.length==1){
			tarScope=checkedbox[0].value;
		}else{
			tarScope="";
		}
		return tarScope;
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