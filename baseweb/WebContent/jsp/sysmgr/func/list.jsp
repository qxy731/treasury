<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>树状显示JSP</title>
<jsp:include page="/comm_debug.jsp"></jsp:include>
<style type="text/css">
#leftbox {width:45%;float:left;}
#detailbox {width:50%;float:left;}
.detailtable {width:80%;margin: 10px;}
.outbox {padding: 5px;margin: 2px;border: 1px solid #dddddd;}
input[type=button] {width:80px;}
#jspPath {width:98%;border:1px solid #b3bcFF;border-radius:2px 2px 2px 2px;height:20px;}
.mytoolbar {height:24px;background: url("${_CONTEXT_PATH}/jwebui/skins/sys/images/panel/panel-toolbar.jpg") repeat-x scroll 0 0 #CEDFEF;border:1px solid #EFF7F7;  border-top:1px solid #9CBAE7; border-left: 1px solid #9CBAE7;border-right: 1px solid #9CBAE7;}
#toptoolbar9 {width:100%;overflow: hidden;}

.detailBox {margin-top: 20px}
</style>
</head>
<n:page action='com.soule.app.sys.jsptree.JspTreeAction' />
<body>
<div id="leftbox" >
	<div title="目录树" style="overflow-x:hidden;width:100%;">
		<ul id="jsptree"></ul>
	</div>
</div>
<div id="detailbox" >
	<div title="详细">
		<form id="myform" action="${_CONTEXT_PATH}/FuncAuth.action">
			<table class='detailtable'>
			<tr>
				<td>
					<fieldset class="outbox"><legend>页面路径 </legend>
					<input type='text' id='jspPath' name='jspPath' readonly="readonly"/><input type='button' value='解析' id='parse_btn' class="l-button"/>
				</td>
			</tr>
				<tr>
					<td>
					<fieldset class="detailBox"><legend>可控制功能点</legend>
					<div id='toptoolbar9' class='mytoolbar'></div>
					<div id='recordlist'></div>
					</fieldset>
					</td>
				</tr>
				<tr>
					<td>
<!-- 						<input type='button' value='增加' id='insert_btn' class="nbutton"/> -->
<!-- 						<input type='button' value='删除' id='delete_btn' class="nbutton"/> -->
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
</body>
<script type='text/javascript'>
	var manager = null;
	var url = "${_CONTEXT_PATH}/sys/jsp-tree!listFile.action";
	$(function () {
		//布局初始化
		var height = $(window).height();
		var accordion1 = $("#leftbox").ligerAccordion( {height : height-2,speed:null});
		var accordion2 = $("#detailbox").ligerAccordion( {height : height-2,speed:null});
		$(window).resize( function() {
				var height = $(window).height();
				accordion1.setHeight(height);
				accordion2.setHeight(height);
			});

		//目录树初始化
		manager = $("#jsptree").ligerTree({
			data: ${initstr},
			checkbox:false,
			textFieldName:'name',
			nodeWidth : '1%',
			onBeforeExpand: onBeforeExpand,
			isLeaf:function(nodedata) {
				return nodedata.pathType == '1';
			},
			onExpand: onExpand,
			slide:false,
			onClick :function(node) {
				if (node.data.pathType == '0') {
					showFuncDetail(node.data.path);
				}
			}
		});
		
		
		$("#toptoolbar9").ligerToolBar({items:[
		 {text:'新增',name:'insert_btn',icon:'add',click:doInsert},
		 {text:'删除',name:'delete_btn',icon:'delete',click:toDelete}
		 	],
		  width:'99%'
		});
		//功能点列表初始化
		$("#recordlist").ligerGrid({
			usePager:false,
			checkbox:true,
			allowAdjustColWidth:false,
			columns: [
				{ display: '功能编码', name: 'funcCode', width:'30%',align: 'left' },
				{ display: '功能名称', name: 'funcName', width:'30%', align: 'left' },
				{ display: '功能描述', name: 'funcDesc', width:'40%', align: 'left' }
				],
			pageSize:15,
			sortName: 'funcId',
			width: '100%',
			height: '250px',
			onError: function() {
				$.dialogBox.error("查询数据失败");
			}
		});
		//按钮初始化
		$("#insert_btn").bind('click', doInsert);
		$("#delete_btn").bind('click', toDelete);
		$("#parse_btn").bind('click', doParse);
	});

	function doInsert() {
		var url = '${_CONTEXT_PATH}/jsp/sysmgr/func/insert.jsp?jsppath=' + $('#jspPath').val();
		$.dialogBox.openDialog(url,'新增功能点');
	}

	function doParse() {
        var url = '${_CONTEXT_PATH}/sys/func-auth!parse.action';
        var mdata = Utils.convertFormData('parseIn','myform');
        if ($('#jspPath').val() == '') {
            return;
        }
        Utils.ajaxSubmit(url,mdata,function(result) {
            $.dialogBox.alert(result.retMsg, function() {
                var grid = $("#recordlist").ligerGetGridManager();
                grid.setOptions({data:result});
                grid.loadData();
            });
        });
    }
	function toDelete() {
		var grid = $("#recordlist").ligerGetGridManager();
		var rows = grid.getCheckedRows();
		if (rows.length < 1) {
			$.dialogBox.alert("请先选择需要删除的记录");
		}
		else {
			$.dialogBox.alert('确认删除?', function(){
				doDelete(rows);
			},true);
		}
	}
	function doDelete(rows) {
		var mdata = {"deleteIn.deletesStr":JSON.stringify(rows)};
		var url = "${_CONTEXT_PATH}/sys/func-auth!delete.action";
		Utils.ajaxSubmit(url,mdata, function(result){
			$.dialogBox.alert(result.retMsg,function() {
				showFuncDetail($('#jspPath').val());
			});
		});
	}
	
	function showFuncDetail(jsppath) {
		$('#jspPath').val(jsppath);
		var url = '${_CONTEXT_PATH}/sys/func-auth!query.action';
		var mdata = Utils.convertFormData('queryIn','myform');
		Utils.ajaxSubmit(url,mdata, function(result){
			var grid = $("#recordlist").ligerGetGridManager();
			grid.setOptions({data:result});
			grid.loadData();
		});
	}
	function onBeforeExpand(node) {
		if (node.data.children && node.data.children.length == 0){
			var params = {'listFileIn.currPath':node.data.path};
			Utils.ajaxSubmit(url,params, function(result) {
				var ndata = result.rows;
				for (var x = 0 ; x< ndata.length ; x++) {
					if (ndata[x].pathType == '1') {
						ndata[x].children =[];
						ndata[x].isexpand ='false';
					}
				}
				manager.append(node.target, ndata);
			});
		}
	}
	function onExpand(note)
	{ 
	}

</script>
</html>