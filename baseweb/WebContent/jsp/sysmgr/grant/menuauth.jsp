<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单授权</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
#syscommit,#cuscommit {width:80px;}
.syscommit_div {margin-left: 50px;margin-top: 10px}
</style>
</head>
<n:page action='com.soule.app.sys.menugrant.MenuGrantAction' />
<body style="height: 100%; overflow: hidden;">
<form id='sysform'>
<input type="hidden" id='roleId' value='${roleId}'></input>
<table cellpadding="5" width="100%">
	<tr>
		<td>
			<div title="菜单列表" id="accordion1">
				<div title="系统菜单">
					<div id="sysmenulist"></div>
					<div id="sysmenuctl" class="syscommit_div">
						<input id='syscommit' type='button' value='保存' class="l-button"/>
					</div>
				</div>
				<div title="自定义菜单">
					<div id="cusmenulist"></div>
					<div id="sysmenuctl" class="syscommit_div">
						<input id='cuscommit' type='button' value='保存' class="l-button"/>
					</div>
				</div>
			</div>
		</td>
	</tr>
</table>
</form>
</body>
<script type="text/javascript">
var sysmenu_data;
var cusmenu_data;
var sysmenu_mgr;
var cusmenu_mgr;
var menumgr;
var changes = [];

$( function() {
	//布局初始化
	var height = $(window).height();
	$("#accordion1").ligerAccordion( {height : height,speed : null});
	$(window).resize( function() {
		var accordion = $("#accordion1").ligerGetAccordionManager();
		var nheight = $(window).height();
		accordion.setHeight(nheight);
	});

	//数据初始化
	sysmenu_data={rows:${sysZNodes}};
	cusmenu_data={rows:${cusZNodes}};
	changes = [];

	//列表初始化
	var options1 = initOptions();
	options1.data=sysmenu_data;
	sysmenu_mgr = $("#sysmenulist").ligerGrid(options1);
	var options2 = initOptions();
	options2.data=cusmenu_data;
	cusmenu_mgr = $("#cusmenulist").ligerGrid(options2);
	menumgr = sysmenu_mgr;

	$('#syscommit').bind('click',doSave);
	$('#sysrefresh').bind('click',doRefresh);
	$('#cuscommit').bind('click',doSave);
});

function initOptions() {
	var options = {
		columns: [
			{ display: '菜单名称', name: 'nodeName', width: 300, align: 'left'},
			{ display: '可执行', name: 'runFlag', width: 100, type: 'boolean', align: 'center', render: renderRunChk}
			<n:authif key='assauth'>
			,{ display: '可分配', name: 'assFlag', width: 100, type: 'boolean', align: 'center', render: renderAssChk}
			</n:authif> 
		],
		width: '99%',height:'80%',
		tree: {
			columnName: 'nodeName',
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
	var html = "<input style='width:30px' type ='checkbox' id='" + row.nodeId + "' onclick=";
	html += "recordChange(this,'"+ row.nodeId  + "','" + row.menuId + "') ";
	html += "chtype='"+ t +"' " ;
	if (t == 'runFlag') {
		if (row.runFlag == true){
			html += " checked ";
		}
	}
	else{
		if (row.assFlag == true){
			html += " checked ";
		}
	}
	html += '>';
	return html;
}
function renderRunChk(row) {
	return renderChk(row,'runFlag');
}
function renderAssChk(row) {
	return renderChk(row,'assFlag');
}
function recordChange(chk,nodeid,menuid) {
	var xx = $(chk).parent().parent().parent();
	var rowid = xx.attr('rowid');
	var rowdata = menumgr.getRow(rowid);
	var t = $(chk).attr('chtype');
	rowdata[t] = chk.checked;
	var nr = {};
	$.each( rowdata, function(i, n){
		if (i != 'children')
			nr[i] = n;
	});
	changes.push(nr);
}

function queryData(row,idx,element) {
	if (row.children.length > 0){
		return;
	}
	var roleid = $("#roleId").val();
	var menuid = row.menuId;
	var nodeid = row.nodeId;
	var mdata = {"roleId":roleid,"menuId":menuid,"nodeId":nodeid};
	mdata = Utils.convertObjectData('listSubIn',mdata);
	var url = "${_CONTEXT_PATH}/sys/menu-grant!listSub.action";
	Utils.ajaxSubmit(url,mdata,updateTable);
}
function updateTable(data) {
	var mid = data.menuId;
	if (mid == 'sys'){
		menumgr = sysmenu_mgr;
	}
	else{
		menumgr = cusmenu_mgr;
	}
	var selectRow = menumgr.getSelectedRow();
	menumgr.expand(selectRow);
	var ndata = data.rows;
	for (var i = 0 ; i < ndata.length ;i ++){
		var rowdata = ndata[i];
		menumgr.appendRow(rowdata, selectRow);
	}
}

function doSave() {
	var mdata = {};
	mdata.roleId = $('#roleId').val();
	mdata.modifyStr = JSON.stringify(changes);
	mdata = Utils.convertObjectData('saveAuthIn',mdata);
	var url = "${_CONTEXT_PATH}/sys/menu-grant!saveAuth.action";
	Utils.ajaxSubmit(url,mdata, function(result) {
		changes = [];
		$.dialogBox.info(result.retMsg);
	});
}
function doRefresh() {
}
</script>
</html>