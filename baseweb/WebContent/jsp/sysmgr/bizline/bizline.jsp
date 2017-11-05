<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>业务线类型维护</title>

<%@include file="/comm.jsp" %>
<style type="text/css">
.xcontent { width: 100%;height: 100%;}
.left {padding:5px;float:left;width:45%; }
.right {padding:5px;float:left;width:45%; }
.detailBox {width:100%;}
.nbutton {width:50px;}
.mytoolbar {height:24px;background: url("${_CONTEXT_PATH}/jwebui/skins/sys/images/panel/panel-toolbar.jpg") repeat-x scroll 0 0 #CEDFEF;border:1px solid #EFF7F7;  border-top:1px solid #9CBAE7; border-left: 1px solid #9CBAE7;border-right: 1px solid #9CBAE7;}
.mytoolbar .l-icon {left: 2px; position: absolute; top: 1px; }
.mytoolbar .l-toolbar-item-hasicon {_margin-left: 4px;padding-left:20px;_margin-top:2px}
.mytoolbar .l-toolbar-item-disable{display: none}
#toptoolbar9 {width:100%;overflow: hidden;}
</style>
</head>
<n:page action='com.soule.app.sys.bizline.bltype.BltypeAction' />
<body>
<div class="xcontent">
	<div class="left">
		<fieldset class="outbox"><legend>业务线类型</legend>
		<div id='lefttoptoolbar'  class='mytoolbar'></div>
			<div id='bizTypelist'></div>
		</fieldset>
	</div>
	<div class="right">
		<fieldset class="outbox"><legend>业务线分类值明细</legend>
		<div id='righttoptoolbar' class='mytoolbar'></div>
			<div id='bizVallist'></div>
		</fieldset>
	</div>
</div>
</body>
<script type='text/javascript'>
$(function () {

	
	$("#lefttoptoolbar").ligerToolBar({items:[
        {text:'新增',name:'insert_bltype',icon:'add',click:insertBltype,disable:!'<n:auth key='insert_bltype'/>'},
        {text:'修改',name:'update_bltype',icon:'update',click:updateBltype,disable:!'<n:auth key='update_bltype'/>'},
        {text:'删除',name:'delete_bltype',icon:'delete',click:deleteBltype,disable:!'<n:auth key='delete_bltype'/>'}
        ],
        width:'99%'
    });

	$("#righttoptoolbar").ligerToolBar({items:[
        {text:'新增',name:'insert_blval',icon:'add',click:insertBlval,disable:!'<n:auth key='insert_blval'/>'},
        {text:'修改',name:'update_blval',icon:'update',click:updateBlval,disable:!'<n:auth key='update_blval'/>'},
        {text:'删除',name:'delete_blval',icon:'delete',click:deleteBlval,disable:!'<n:auth key='delete_blval'/>'},
        {text:'分配',name:'ass_blval',icon:'lock',click:staffAss,disable:!'<n:auth key='ass_blval'/>'}
        ],
        width:'99%'
        });
	//输出表格
	var grid = $("#bizTypelist").ligerGrid({
		columns: [
			{ display: '业务线类别', name: 'bizTypeId', align: 'left' ,width:'30%'},
			{ display: '业务线名称', name: 'bizTypeName', align: 'left' ,width:'30%'},
			//{ display: '备注', name: 'remark', align: 'center',width:250 }
			//{ display: '创建人', name: 'createUser', align: 'center' },
			{ display: '创建时间', name: 'createTime', align: 'left' ,width:'30%'}
			//{ display: '最后修改人', name: 'lastUpdUser', align: 'center' },
			//{ display: '最后修改时间', name: 'lastUpdTime', align: 'center' }
		],
//		buttons:[
//			{text:'新 增',name:'insert_bltype',clazz:'nbutton',enabled:'<n:auth key='insert_bltype'/>'},
	//		{text:'修 改',name:'update_bltype',clazz:'nbutton',enabled:'<n:auth key='update_bltype'/>'},
		//	{text:'删 除',name:'delete_bltype',clazz:'nbutton',enabled:'<n:auth key='delete_bltype'/>'}
		//],
		data:{rows:${bltypeStr}},
		pageSize:10,
		navbtn:false,
		sortName: 'bizTypeId',
		height: 400,
		onSelectRow:function(row,id,context) {
			queryBlVal(row);
		},
		onDblClickRow:function(row,id,context){
			updateBltype();
		},
		onError: function(e) {
			Utils.toIndex(e);
		}
	});
	grid.loadData();

	$("#bizVallist").ligerGrid({
		columns: [
			//{ display: '业务线类别', name: 'bizTypeId', align: 'center' },
			{ display: '业务线分类值', name: 'bizValue', align: 'left' ,width:'30%'},
			{ display: '业务线分类名', name: 'bizName', align: 'left' ,width:'30%'},
			//{ display: '备注', name: 'remark', align: 'center' ,width:250},
			//{ display: '创建人', name: 'createUser', align: 'center' ,width:100}
			{ display: '创建时间', name: 'createTime', align: 'left',width:'30%'}
			//{ display: '最后修改人', name: 'lastUpdUser', align: 'center' },
			//{ display: '最后修改时间', name: 'lastUpdTime', align: 'center' }
		],
	//	buttons:[
	//		{text:'新 增',name:'insert_blval',clazz:'nbutton',enabled:'<n:auth key='insert_blval'/>'},
		//	{text:'修 改',name:'update_blval',clazz:'nbutton',enabled:'<n:auth key='update_blval'/>'},
	//		{text:'删 除',name:'delete_blval',clazz:'nbutton',enabled:'<n:auth key='delete_blval'/>'},
	//		{text:'分配',name:'ass_blval',clazz:'nbutton',enabled:'<n:auth key='ass_blval'/>'}
	//	],
		pageSize:10,
		onDblClickRow:function(row,id,context){
			updateBlval();
		},
		sortName: 'bizTypeId',
		height: 400,
		onError: function(e) {
			Utils.toIndex(e);
		}
	});
	$('#insert_bltype').bind('click', insertBltype);
	$('#update_bltype').bind('click', updateBltype);
	$('#delete_bltype').bind('click', deleteBltype);
	$('#insert_blval').bind('click', insertBlval);
	$('#update_blval').bind('click', updateBlval);
	$('#delete_blval').bind('click', deleteBlval);
	$('#ass_blval').bind('click', staffAss);
});

function queryBlVal(row) {
	if (!row) {
		var grid = $("#bizTypelist").ligerGetGridManager();
		row = grid.getSelectedRow();
	}
	var grid = $("#bizVallist").ligerGetGridManager();
	var url = "${_CONTEXT_PATH}/sys/blval!query.action";
	var mdata = Utils.convertObjectData('queryIn',row);
	Utils.ajaxSubmit(url,mdata,function(result) {
		var grid = $("#bizVallist").ligerGetGridManager();
		grid.setOptions({data:result});
		grid.loadData();
	});
}
 

function queryBltype() {
	var validUrl = "${_CONTEXT_PATH}/sys/bltype!query.action";
	Utils.ajaxSubmit(validUrl,null,function(result) {
		var grid = $("#bizTypelist").ligerGetGridManager();
		grid.setOptions({data:result});
		grid.loadData();
	});
}
function insertBltype() {
	var url = "${_CONTEXT_PATH}/jsp/sysmgr/bizline/bltype_insert.jsp";
	$.dialogBox.openDialog(url,'新增业务线类型');
}
function updateBltype() {
	var grid = $("#bizTypelist").ligerGetGridManager();
	var selected = grid.getSelectedRow();
	if (selected) {
		var url = "${_CONTEXT_PATH}/jsp/sysmgr/bizline/bltype_modify.jsp?bltid=" + selected.bizTypeId;
		$.dialogBox.openDialog(url,'修改业务线类型');
	}
	else {
		$.dialogBox.alert("请先选择需要修改的业务线类型");
	}
}
function deleteBltype() {
	var grid = $("#bizTypelist").ligerGetGridManager();
	var sel = grid.getSelectedRow();
	if (!sel) {
		Utils.alert("请先选择需要删除的记录");
		return ;
	}
	var validUrl = "${_CONTEXT_PATH}/sys/bltype!validData.action";
	var mdata = Utils.convertObjectData('validDataIn',sel);
	Utils.ajaxSubmit(validUrl,mdata,function(result) {
		executeDel(sel,result);
	});
}
function executeDel(sel,result) {
	var msg = "存在相关记录数为 " + result.resultPo.bizValueCount + "<br/>";
	var url = "${_CONTEXT_PATH}/sys/bltype!delete.action";
	var mdata = Utils.convertObjectData('deleteIn',sel);
	$.dialogBox.alert(msg + '确认业务线类型吗?',function(){
		Utils.ajaxSubmit(url,mdata, function(result){
			$.dialogBox.alert(result.retMsg,function() {
				var grid = $("#bizTypelist").ligerGetGridManager();
				grid.deleteRow(sel);
				$.dialogBox.close();
			});
		});
	},true);
}
function insertBlval() {
	var grid = $("#bizTypelist").ligerGetGridManager();
	var sel = grid.getSelectedRow();
	if (!sel) {
		Utils.alert("请先选择需要新增业务线的记录");
		return ;
	}
	var url = "${_CONTEXT_PATH}/jsp/sysmgr/bizline/blval_insert.jsp?bltid=" + sel.bizTypeId;
	$.dialogBox.openDialog(url,'新增业务线分类');
}
function getSelectedBlval() {
	var grid = $("#bizVallist").ligerGetGridManager();
	var selected = grid.getSelectedRow();
	return selected;
}

function updateBlval() {
	var grid = $("#bizVallist").ligerGetGridManager();
	var selected = grid.getSelectedRow();
	if (selected) {
		var url = "${_CONTEXT_PATH}/jsp/sysmgr/bizline/blval_modify.jsp";
		$.dialogBox.openDialog(url,'修改业务线类型');
	}
	else {
		$.dialogBox.alert("请先选择需要修改的业务线类型");
	}
}
function deleteBlval() {
	var grid = $("#bizVallist").ligerGetGridManager();
	var sel = grid.getSelectedRow();
	if (!sel) {
		Utils.alert("请先选择需要删除的记录");
		return ;
	}
	var validUrl = "${_CONTEXT_PATH}/sys/blval!validData.action";
	var mdata = Utils.convertObjectData('validDataIn',sel);
	Utils.ajaxSubmit(validUrl,mdata,function(result) {
		executeDelVal(sel,result);
	});
}
function executeDelVal(sel,result) {
	var msg = "存在相关记录数为 " + result.resultPo.bizMapCount + "<br/>";
	var url = "${_CONTEXT_PATH}/sys/blval!delete.action";
	var mdata = Utils.convertObjectData('deleteIn',sel);
	$.dialogBox.alert(msg + '确认业务线分类吗?',function(){
		Utils.ajaxSubmit(url,mdata, function(result){
			$.dialogBox.alert(result.retMsg,function() {
				var grid = $("#bizVallist").ligerGetGridManager();
				grid.deleteRow(sel);
				$.dialogBox.close();
			});
		});
	},true);
}
function execute() {
	if (!$('#myform').valid()){
		return;
	}
	//单记录数据
	var mdata = Utils.convertFormData('queryIn','myform');

	var url = "${_CONTEXT_PATH}/sys/bltype!query.action";
	Utils.ajaxSubmit(url,mdata);
}
function staffAss() {
	var grid = $("#bizVallist").ligerGetGridManager();
	var selected = grid.getSelectedRow();
	if (selected) {
		var url = "${_CONTEXT_PATH}/jsp/sysmgr/bizline/bizass.jsp?bltid=" + selected.bizTypeId + "&blvid="+selected.bizValue;
		$.dialogBox.openDialog(url,{title:'业务线人员分配',width:'980px',height:'420px'});
	}
	else {
		$.dialogBox.alert("请先选择需要修改的业务线类型");
	}
}
</script>
</html>