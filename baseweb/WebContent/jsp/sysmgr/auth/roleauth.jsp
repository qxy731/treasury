<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%-- <s:head theme="ajax" /> --%>
<link rel="stylesheet" type="text/css" href="${_CONTEXT_PATH}/jwebui/skins/${SkinType}/css/ligerui-all.css" />
<script type="text/javascript" src="${_CONTEXT_PATH}/jwebui/jquery/jquery-1.4-dev.js"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerAccordion.js" type="text/javascript"></script>
<script type="text/javascript" src="${_CONTEXT_PATH}/jwebui/jquery/jquery.validate.min.js"></script>
<script type="text/javascript" src="${_CONTEXT_PATH}/jwebui/jquery/jquery.metadata.js"></script>
<script type="text/javascript" src="${_CONTEXT_PATH}/jwebui/jquery/messages_cn.js"></script>
<script type="text/javascript" src="${_CONTEXT_PATH}/TreeDeptData.js"></script>
<script type="text/javascript" src="${_CONTEXT_PATH}/jsp/sysmgr/auth/roleauth.js"></script>

<style type="text/css">
div#rMenu {
	position: absolute;
	visibility: hidden;
	background-color: #555;
	text-align: center;
	padding: 1px;
}

div#rMenu ul li {
	min-width:100px;
	margin: 1px 0;
	padding: 0 5px;
	cursor: pointer;
	list-style: none outside none;
	background-color: #EEEEEE;
}
div#rMenu ul li:hover{
	background-color: #EEEE99;
}
.l-table-edit {
	font-size: 12px;
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test,.l-button-reset {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}


</style>

</head>
<!-- 
<e:page id="" init="menuManager"/>
<e:comboxdata id="" init="menuManager"/>
 -->

<body style="height: 100%; overflow: hidden;">
<form id='sysform' name='sysform' action=''>
<input type="hidden" name='roleId' id="roleId" value="s"></input>
<table cellpadding="5" width="100%">
	<tr>
		<td>
			<div title="菜单列表" id="accordion1">
				<div title="系统菜单">
					<div id="sysmenulist"></div>
					<div id="sysmenuctl">
						<input id='syscommit' type='button' value='保存'/>
						<input id='sysrefresh' type='button' value='刷新'/>
					</div>
				</div>
				<div title="自定义菜单">
					<div id="cusmenulist"></div>
					<div id="sysmenuctl">
						<input id='cuscommit' type='button' value='保存'/>
						<input id='cusrefresh' type='button' value='刷新'/>
					</div>
				</div>
			</div>
		</td>
	</tr>
</table>
</form>
</body>
<script type="text/javascript">

$( function() {
	var height = $(window).height();
	$("#accordion1").ligerAccordion( {height : height,speed : null});
	$(window).resize( function() {
		var accordion = $("#accordion1").ligerGetAccordionManager();
		var nheight = $(window).height();
		accordion.setHeight(nheight);
	});

	sysmenu_data={rows:${sysZNodes}};
	cusmenu_data={rows:${cusZNodes}};
});
var sysmenu_data;
var cusmenu_data;
var sysmenu_mgr;
var cusmenu_mgr;
var changes = {};
$(function (){
	var options1 = initOptions();
	options1.data=sysmenu_data;
	sysmenu_mgr = $("#sysmenulist").ligerGrid(options1);
	var options2 = initOptions();
	options2.data=cusmenu_data;
	cusmenu_mgr = $("#cusmenulist").ligerGrid(options2);

	$('#syscommit').bind('click',doSubmit);
	$('#cuscommit').bind('click',doSubmit);

});

function initOptions() {
	var options = {
	columns: [
			{ display: '菜单名称', name: 'name', width: 250, align: 'left'},
			{ display: '可执行', name: 'id', width: 100, type: 'boolean', align: 'center', render: renderRunChk},
			{ display: '可分配', name: 'id', width: 100, type: 'boolean', align: 'center', render: renderAssChk}
		],
		width: '99%',height:'80%',
		tree: {
			columnName: 'name',
			isParent:isParentNode,
			onBeforeToggle:queryData
		},
		enabledEdit:false,
		enabledSort:false,
		alternatingRow: false,
		usePager:false
	}
	return options;
}
function isParentNode(rowData) {
	return rowData.hasChild;
}
function renderChk(row,t) {
	var html = "<input type ='checkbox'" +" name='" + row.id +"' id='" + row.id + "' onclick=";
	if (t == 'run'){
		html +="recordChange1";
	}
	else{
		html += "recordChange2";
	}
	html += "(this,'"+ row.id + "','" + row.menuId + "')>";
	return html;
}
function renderRunChk(row) {
	return renderChk(row,'run');
}
function renderAssChk(row) {
	return renderChk(row,'ass');
}
function recordChange1(chk,rowid,menuid) {
	var curr = changes['"'+ menuid + "." + rowid + '"'];
	if (!curr) curr = "--";
	curr = (chk.checked?"1":"0") + curr.charAt(1);
	changes['"'+ menuid + "." + rowid + '"'] = curr;
}
function recordChange2(chk,rowid,menuid) {
	var curr = changes['"'+ menuid + "." + rowid + '"'];
	if (!curr) curr = "--";
	curr = curr.charAt(0) + (chk.checked?"1":"0") ;
	changes['"'+ menuid + "." + rowid + '"'] = curr;
}
function queryData(row,idx,element) {
	if (row.children.length > 0){
		return;
	}
	var roleid = $("#roleId").val();
	var menuid = row.menuId;
	var nodeid = row.id;
	var mdata = {"roleId":roleid,"menuId":menuid,"nodeId":nodeid};
	$.ajax({
		type: "POST",
		dataType: "json",
		url: "${_CONTEXT_PATH}/roleAuth!findSubMenu.action",
		data:mdata,
		success: updateTable
	});
}
function updateTable(data) {
	var mid = data.menuId;
	var menumgr;
	if (mid == 'sys'){
		menumgr = sysmenu_mgr;
	}
	else{
		menumgr = cusmenu_mgr;
	}
	var selectRow = menumgr.getSelectedRow();
	menumgr.expand(selectRow);
	var ndata = eval(data.subZNodes);
	for (var i = 0 ; i < ndata.length ;i ++){
		var rowdata = ndata[i];
		menumgr.appendRow(rowdata, selectRow);
	}
}

function doSubmit() {
	var mdata = {};
	mdata.roleId = $('#roleId').val();
	mdata.modifies = changes;
	$.ajax({
		type: "POST",
		url: "${_CONTEXT_PATH}/roleAuth!saveAuth.action",
		data:mdata,
		success: function(retData) {
			var ret = eval(retData);
			if(ret.retCode == "I0000") {
				changes = {};
				$.ligerDialog.alert('保存成功', '提示', 'success');
			}
			else{
				$.ligerDialog.alert('保存失败', '提示', 'error');
			}
		},
		error: function(retData) {
			$.ligerDialog.alert('请检查网络链接', '提示', 'error');
		}
	});
}
function doReset() {
	$('#node_name').val("");
}
 
</script>
</html>