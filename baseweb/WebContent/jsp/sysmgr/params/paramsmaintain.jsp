<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>参数配置维护</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.inbox ,.outbox ,.queryBox{padding: 5px;margin: 2px;border: 1px solid #ccc;}
.params td {background-color: #E2EAFF; padding:2px 2px 2px 10px;}
.btn_div {margin-top: 20px;margin-bottom: 15px;}
.btn_table td{padding:1px 65px 1px 65px;}
#reset {width: 80px;}
body {
	margin-top: 5px
}
</style>
</head>
<body>
<n:page action='com.soule.app.sys.params.ParamsAction' />
<n:enums keys='para_rank'/>

<table class='content'>
<tr><td>

<fieldset class="queryBox"><legend>查询条件</legend>
<form id="myform" action="${_CONTEXT_PATH}/Params!query.action">
<table class='params'>
	<tr>
<!--		<td align="right">ID</td>-->
        <td>ID</td>
		<td><input type='text' id='paraId' name='params.paraId' value='${paramsPo.paraId}' /></td>
<!--		<td align="right">分级</td>-->
        <td>分级</td>
		<td><n:select codetype="para_rank" id="paraRank" name='params.paraRank' emptyOption="true" ></n:select></td>
	</tr>
	<tr>
<!--		<td align="right">值</td>-->
        <td>值</td>
		<td><input type='text' id='paraValue' name='params.paraValue' value='${paramsPo.paraValue}' validate='{maxlength:255}' /></td>
<!--		<td align="right">说明</td>-->
        <td>说明</td>
		<td><input type='text' id='remark' name='params.remark' value='${paramsPo.remark}' validate='{maxlength:512}' /></td>
	</tr>
</table>
</form>
</fieldset>
<div class="btn_div" align="center">
     <table class="btn_table">
         <tr >
           <td ><input id='query' name='query' type='button' value='查&nbsp;&nbsp;询' class='l-button'/></td>
           <td ><input id='reset' name='reset' type='button' value='重&nbsp;&nbsp;置' class='l-button'/></td>
         </tr>
     </table>
</div>

</td></tr>
<tr><td>
	<fieldset class="detailBox"><legend>查询结果</legend>
		<div id='toptoolbar'></div>
		<div id='paramslist'></div>
</fieldset>
</td></tr>
</table>
</body>
<script type='text/javascript'>
var paraId = null;
$(function () {
	Utils.validateInit();
	$('#reset').bind('click', doClear);
	$('#query').bind('click', execute);

	$("#toptoolbar").ligerToolBar({items:[
	      {text:'新增',name:'insert_btn',icon: 'add',click:insert,enabled:'<n:auth key='params_insert'/>' } ,
	      {text:'删除',name:'delete_btn',icon: 'delete',click:deleteRow,enabled:'<n:auth key='params_delete'/>' } ,
	      {text:'修改',name:'update_btn',icon: 'update',click:updateRow, enabled:'<n:auth key='params_update'/>'}                       	
	      ],
	      width:'100%'
	});
	//输出表格
	$("#paramslist").ligerGrid({
		enumlist: _enum_params ,
		checkbox: true,
		columns: [
			{ display: 'ID', name: 'paraId', align: 'center' },
			{ display: '分级', name: 'paraRank', align: 'center',codetype: 'para_rank' },
			{ display: '值', name: 'paraValue', align: 'center' },
			{ display: '说明', name: 'remark', align: 'center',width:320 }
		],
		pageSize:10,
		selectRowButtonOnly:true,
		sortName: 'paraId',
		height:300,
        data:{rows:[]},
		width: '100%',
		onError: function() {
			$.dialogBox.error("查询数据失败",'提示');
		}
	});
});
function doClear() {
	$(".inbox input[type='text']").each(function(i,item){
		item.value ='';
	});
	$('#paraId').val("");
	$('#paraRank').val("");
	$('#paraValue').val("");
	$('#remark').val("");
}

function updateRow(){
	var grid = $('#paramslist').ligerGetGridManager();
	var rows = grid.getSelectedRow();
	
	if(rows==null){
		$.dialogBox.warn("请选择一条信息",true);
		return;
	}
	
	var paraId = rows.paraId;
	var url = '${_CONTEXT_PATH}/jsp/sysmgr/params/params_update.jsp?paraId='+paraId;

	$.dialogBox.openDialog(url,{title:'修改系统配置参数',width:'640px',height:'150px'});
}

function insert(){
	var url = '${_CONTEXT_PATH}/jsp/sysmgr/params/params_insert.jsp';
	$.dialogBox.openDialog(url,{title:'新增系统配置参数',width:'640px',height:'150px'});
}

function deleteRow(){
	var grid = $('#paramslist').ligerGetGridManager();
	var rows = grid.getCheckedRows();
	if (rows.length < 1) {
			$.dialogBox.warn("请先选择需要删除的记录");
			return ;
		}
	var mdata = {"deleteIn.deletesStr":JSON.stringify(rows)};
    var url = "${_CONTEXT_PATH}/sys/params!delete.action";
    $.dialogBox.confirm('你确定删除吗？',function () {
		Utils.ajaxSubmit(url, mdata, function(result) {
			if (Utils.isSuccess(result)) {
				if (result.retMsg) {
					$.dialogBox.info('删除成功',function() {execute();} ); 
				}
			}
		});
		},true);
	
}

function execute() {
	if (!$('#myform').valid()){
		return;
	}
	//多记录数据
	var mdata = Utils.convertParam('queryIn','myform');

	//var url = "${_CONTEXT_PATH}/Params!query.action";
	//Utils.ajaxSubmit(url,mdata);
	var params = {
			dataAction:'server',
			dataType:'server',
			url: '${_CONTEXT_PATH}/sys/params!query.action',
			newPage:1,
			parms:mdata,
			onError: function() {
				$.dialogBox.error("查询数据失败");
			}
		};
		var gridManager = $("#paramslist").ligerGetGridManager(); 
		gridManager.setOptions(params);
		gridManager.loadData();
}

</script>
</html>