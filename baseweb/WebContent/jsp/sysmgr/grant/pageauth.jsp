<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>页面和功能点授权</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.syscommit_div {margin-left: 50px;margin-top: 10px}
</style>
</head>
<n:page action='com.soule.app.sys.pagegrant.PageGrantAction' />
<body style="height: 100%; overflow: hidden;">
<s:actionerror />
<form id='sysform'>
<input type="hidden" id='roleId' value='${roleId}'></input>
<div title="列表" id="accordion1">
	<div title="资源权限">
		<div id="jsplist"></div>
		<div class="syscommit_div">
			<input id='syscommit' type='button' class="l-button"  value='保存' />
		</div>
	</div>
</div>
</form>
</body>
<script type="text/javascript">
var path_data;
var pathmgr;
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
	path_data={rows:${initstr}};
	changes = [];

	//列表初始化
	var options1 = initOptions();
	options1.data=path_data;
	pathmgr = $("#jsplist").ligerGrid(options1);

	$('#syscommit').bind('click',doSave);
	$(".l-accordion-header").unbind();
	$(".l-accordion-toggle").hide();
});

function initOptions() {
	var options = {
		columns: [
			{ display: '路径名称', name: 'nodeName', width: 300, align: 'left'},
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
	var rowdata = pathmgr.getRow(rowid);
	var t = $(chk).attr('chtype');
	rowdata[t] = chk.checked;
	var nr = {};
	$.each( rowdata, function(i, n){//过滤掉children属性
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
	var nodepath = row.nodePath;
	var nodeid = row.nodeId;
	var mdata = {"roleId":roleid,"nodePath":nodepath,"nodeId":nodeid};
	mdata = Utils.convertObjectData('listChildIn',mdata);
	var url = "${_CONTEXT_PATH}/sys/page-grant!listChild.action";
	Utils.ajaxSubmit(url,mdata,function(result) {
		var selectRow = pathmgr.getSelectedRow();
		pathmgr.expand(selectRow);
		var ndata = result.rows;
		for (var i = 0 ; i < ndata.length ;i ++){
			var rowdata = ndata[i];
			pathmgr.appendRow(rowdata, selectRow);
		}
	});
 
}

function doSave() {
	var mdata = {};
	mdata.roleId = $('#roleId').val();
	mdata.modifyStr = JSON.stringify(changes);
	mdata = Utils.convertObjectData('saveAuthIn',mdata);
	var url= "${_CONTEXT_PATH}/sys/page-grant!saveAuth.action";
	Utils.ajaxSubmit(url,mdata,function(result) {
		changes = [];
		$.dialogBox.info(result.retMsg);
	});
}
function doRefresh() {
}
</script>
</html>