<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>快捷菜单</title>
<link rel="shortcut icon" href="${_CONTEXT_PATH}/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="${_CONTEXT_PATH}/jwebui/zTree/css/zTreeStyle/zTreeStyle.css" />
<jsp:include page="/comm.jsp"></jsp:include>
<script type="text/javascript" src="${_CONTEXT_PATH}/jwebui/zTree/js/jquery.ztree.core-3.0.min.js"></script>
<n:page action='com.soule.crm.pub.quickmenu.QuickMenuAction'/>

<script type="text/javascript" >
var tab;
var g1;
var g2;
var treeTool;

$(document).ready(function() {
	var zNodes=${returnStr};

	var arr = [];
	
	treeTool = $.fn.zTree.init($("#tree1"), setting, zNodes);
	
    tab = $("#tabs").ligerTab();

	g1 = $("#mostUsedMenus").ligerGrid({
		columns: [
			{ display: '菜单名称', name: 'NODE_NAME', align: 'left' },
			{ display: '使用次数', name: 'USER_COUNT', align: 'left',width:80},
		],
		checkbox:true,
		pageSize:15,
		pageSizeOptions:[15,20,25],
		sortName: 'USER_COUNT',
		width:'100%',
		height: 413
	});
	
	g2 = $("#menubuilder").ligerGrid({
		columns: [
			{ display: '菜单名称', name: 'NODE_NAME', align: 'left' },
			{ display: '显示顺序', name: 'SEQ', align: 'left',editor:{type:'text'},width:80},
		],
		checkbox:true,
		usePager:false,
		sortName: 'NODE_NAME',
		enabledEdit:true,
		data:{rows:arr},
		width:'100%',
		height: 450
	});

	query();
});

var setting = {
		check: {enable: true},
		data: {simpleData: {enable: true}},
		view: {expandSpeed: ""}
};

function query() {
	//var url = "${_CONTEXT_PATH}/MenuCounter!query.action";
	//var mdata = {};
	//mdata.pagesize = 15;//g1.pageSize;
	//mdata.page = 1;//g1.pageNo;
	//Utils.ajaxSubmit(url, mdata, onSuccess);

	var params = {
		dataAction:'server',
		dataType:'server',
		url: '${_CONTEXT_PATH}/sys/menu-counter!query.action',
		newPage:1
	};
	g1.setOptions(params);
	g1.loadData();
	
	var url2 = "${_CONTEXT_PATH}/sys/quick-menu!query.action";
	Utils.ajaxSubmit(url2, null, onSuccess2);
}

function onSuccess(ret) {
	var g1rows = ret.rows;
	var tpage = ret.totalPage;
	g1.setOptions({data:{rows:g1rows}});
	g1.loadData();
}

function onSuccess2(ret) {
	var g1rows = ret.rows;
	g2.setOptions({data:{rows:g1rows}});
	g2.loadData();
}

	
function moveToRight() {
	var tabid = tab.getSelectedTabItemID();
	var g1rows;
	if(tabid == "tabid1") {
		g1rows = treeTool.getSelectedNodes();

		if(!checkQuickMenuCount(g1rows.length))return;
		
		for(var i=0;i<g1rows.length;i++) {
			var obj = {};
			var menuId = g1rows[i].id;
			var menuName = g1rows[i].name;
			if(!check(menuName))continue;
			var len = (g2.data.rows.length+1)+"";
			obj.NODE_NAME = menuName;
			obj.SEQ = len+"";
			obj.MENU_ID = menuId;
			obj.SAVED = "0";
			g2.addRow(obj);
		}
	}else if(tabid == "tabid2") {
		g1rows = g1.getCheckedRows();

		if(!checkQuickMenuCount(g1rows.length))return;
		
		for(var i=0;i<g1rows.length;i++) {
			var obj = {};
			var menuId = g1rows[i].MENU_ID;
			var menuName = g1rows[i].NODE_NAME;
			if(!check(menuName))continue;
			var len = g2.data.rows.length+1;
			obj.NODE_NAME = menuName;
			obj.SEQ = len+"";
			obj.MENU_ID = menuId;
			obj.SAVED = "0";
			g2.addRow(obj);
		}
	}
}
function check(menuId) {
	var llee = g2.getData();
	var len = g2.data.rows.length;
	if(len==0)return true;
	for(var i=0;i<len;i++) {
		if(g2.data.rows[i].__status=='delete')continue;
		if(menuId==g2.data.rows[i].NODE_NAME) {
			//$.dialogBox.warn("所选的快捷菜单与已选的出现重复！");
			return false;
		}
	}
	return true;
}

function deleterow() {
	g2.deleteSelectedRow();
	//g2.loadData();
}

function save() {
	if(!checkQuickMenuCount())return;
	var mdata = {};
	var rows2 = g2.getData();
	mdata['returnStr'] = JSON.stringify(rows2);
	var url = "${_CONTEXT_PATH}/sys/quick-menu!save.action";
	Utils.ajaxSubmit(url, mdata, function(result){
		$.dialogBox.info(result.retMsg);
		query();
	});
}


/**
 * 快捷菜单个数（有效行数）： 7
 * addedCount:将要新增的行数
 */
function checkQuickMenuCount(addedCount) {
	var rows2 = g2.getData();
	var len = rows2.length;
	if(len==0)return true;
	var deletedCount = 0;
	for(var i=0;i<len;i++) {
		if(!rows2[i].__status)continue;
		if(rows2[i].__status=='delete') {
			deletedCount ++;
		}
	}
	if(addedCount)len = len + addedCount;
	if(len - deletedCount > 7) {//如果快捷菜单个数（有效行数）超过 7,false
		$.dialogBox.warn("快捷菜单个数（行数）不能超过7个！");
		return false;
	}else {
		return true;
	}
}
</script>

</head>
<body style="padding:0;" >


<table class='content' width="900" height="500">
<tr>
<td width="45%"  height="100%">
<fieldset class="outbox"><legend>我的功能菜单</legend>
	<div id="tabs" style="width: 100%;height:100%;overflow:hidden; border:1px solid #A3C0E8;">
		<div title="我的菜单" id="myMenus" style='height:100%' tabid="tabid1">
			<ul id="tree1" class="ztree" style='height:410px;' ></ul>
		</div>
		<div title="常用菜单" style='height:100%' tabid="tabid2">
			<ul id="mostUsedMenus" style='height:100%;width:100%' ></ul>
		</div>
	</div>
</fieldset>
</td>
<td  width="5%">
<div align="center" style="width: 30">
	<input type="button" value="  >>  " onclick="moveToRight()" class="l-button"/>
	<br><br><br><br><br><br>
	<input type="button" value="  <<  " onclick="deleterow()"  class="l-button"/>
	<br><br><br><br><br><br>
	<input type="button" value=" 保存 " onclick="save()"  class="l-button"/>
</div>
</td>
<td width="45%" height="100%">
	<fieldset class="outbox"><legend>定制菜单</legend>
		<div id='menubuilder'></div>
	</fieldset>
</td>
</tr>
</table>

</body>
</html>