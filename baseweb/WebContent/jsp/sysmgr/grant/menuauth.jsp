<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单授权</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
/* .syscommit_div {margin-left: 50px;margin-top: 10px} */
</style>
</head>
<n:page action='com.soule.app.sys.menugrant.MenuGrantAction' />
<body>
<form id='sysform'>
<input type="hidden" id='roleId' value='${roleId}'></input>
<fieldset class="content outbox"><legend>菜单授权</legend>
	<div id="toptoolbar"></div>
	<div id="sysmenulist"></div>
</fieldset>
</form>
</body>
<script type="text/javascript">
var sysmenu_data;
var sysmenu_mgr;
var changes = [];

$( function() {
	
	$("#toptoolbar").ligerToolBar({items:[
			{text:'保存',name:'syscommit',icon: 'save',click:doSave}
           ],width:'100%'
   	 });
	//数据初始化
	sysmenu_data={rows:${sysZNodes}};
	//cusmenu_data={rows:${cusZNodes}};
	changes = [];

	//列表初始化
	var options1 = initOptions();
	options1.data=sysmenu_data;
	sysmenu_mgr = $("#sysmenulist").ligerGrid(options1);
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
		width: '100%',height:'80%',
		tree: {
			columnName: 'nodeName',
			isParent:isParentNode,
			idField: 'nodeId'
	        //,parentIDField: 'parentNode'
		},
		onTreeExpand:queryData,
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
	var rowid = row['__id'];
	var html = "<input style='margin:4px auto;' type ='checkbox' id='" + row.nodeId + "' onclick=";
	html += "recordChange(this,'"+ row.nodeId  + "','" + row.menuId + "','"+rowid+"') ";
	html += "chtype='"+ t +"' " ;
	if (t == 'runFlag') {
		if (row.runFlag == true){ html += " checked "; }
	}
	else{
		if (row.assFlag == true){ html += " checked "; }
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
function recordChange(chk,nodeid,menuid,rowid) {
	var rowdata = sysmenu_mgr.getRow(rowid);
	var t = $(chk).attr('chtype');
	rowdata[t] = chk.checked;
	var nr = {};
	$.each(rowdata, function(i, n){
		if (i != 'children')
			nr[i] = n;
	});
	changes.push(nr);
}

function queryData(data,e) {
	if (data.children.length > 0){
		return;
	}
	var roleid = $("#roleId").val();
	var menuid = data.menuId;
	var nodeid = data.nodeId;
	var mdata = {"roleId":roleid,"menuId":menuid,"nodeId":nodeid};
	mdata = Utils.convertObjectData('listSubIn',mdata);
	var url = "${_CONTEXT_PATH}/sys/menu-grant!listSub.action";
	Utils.ajaxSubmit(url,mdata,function (result){
		sysmenu_mgr.append(result.rows, data);
		e.update();
	});
	return false;
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