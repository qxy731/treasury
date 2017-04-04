<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="n" uri="/ncrm-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns:v="urn:schemas-microsoft-com:vml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>批量控制维护</title>
<jsp:include page="/comm.jsp"></jsp:include>
<link href="${_CONTEXT_PATH}/jwebui/structure/structure.css" rel="stylesheet" type="text/css" />
<style>
.node_type0 {background: url(../../../images/jiedian.png) no-repeat center ;}
.node_type1 {background: url(../../../images/bingfa.png) no-repeat center ;}
.node_type2 {background: url(../../../images/shunxu.png) no-repeat center ;}
</style>
<script type="text/javascript" src="${_CONTEXT_PATH}/jwebui/structure/structure.js"></script>
</head>

<body>
	<n:page action='com.soule.app.sys.batch.BatchAction' initMethod="doInitBatch"/>
	<div id='StructureView'></div>
</body>

<script type="text/javascript">
	var nodecolors = {
		"1" : [ 'node_type1', '并发节点' ],
		"2" : [ 'node_type2', '顺序节点' ],
		'3' : [ 'node_type0', '普通节点' ]
	};
//	var linecolors = {
//		"1" : [ "#ddddff", "依赖关系" ],
//		"2" : [ "#ffdddd", "所属关系" ],
//		'3' : [ '#dd6666', '客户关系' ]
//	};

	function queryData() {
		var bid = '${bid}';
		var result = {retCode:'${retCode}',retMsg:'${retMsg}'};
		if (Utils.isSuccess(result)) {
			var mdata = Utils.convertObjectData('queryMainIn', {"step.batchId" : "${bid}"});
			Utils.ajaxSubmit("${_CONTEXT_PATH}/sys/batch!queryMain.action", mdata,afterdone);
		}
		else {
			$.dialogBox.alert('无法展示.' +result.retMsg);
		}
	}

	var view;
	function afterdone(result) {
		var vconfigs = {
			data : result.rows,
			nodeIdField : 'stepId',
			nodeNameField : 'stepName',
			parentField : 'parentId',
			nodeHeight : 40,
			lineHeight : 20,
			nodeSpan : 4,
			nodeWidth : 50,
			//lineClassMap:linecolors,
			lineClassField : 'color',
			nodeClassMap : nodecolors,
			nodeClassField : 'nc',
			leftoffset : 0,
			menuitems : [ {
				name : '修改节点',
				click : toModifyNode,
				filter : 'all'
			}, {
				name : '增加子节点',
				click : toAddNode,
				filter : 'root,branch'
			}, {
				name : '删除节点',
				click : removeNode,
				filter : 'all'
			} ],
			renderUnit : function(i, item, data) {
				$(item).html(data.stepName);
				$(item).attr('class','st_nodecontent node_type'+ data.stepType);
			},
			isRootRecord : function(record) {
				return record.parentId == null || record.parentId == 0;
			},
			canExpand : function(data) {
				if (data.stepType == '1' || data.stepType == '2') {
					return true;
				}
				return false;
			},
			showTip : function(content, data) {
				content.height(100);
				content.html(data.stepId + "<br/>" + data.stepName);
			}
		};
		view = $('#StructureView').zStructure(vconfigs);
	}
	$(function() {
		queryData();
	});
	var modifyData = null;
	function toAddNode(event) {
		var data = event.data;
		modifyData = data._data;
		var url = '${_CONTEXT_PATH}/jsp/sysmgr/batch/stepdetail.jsp?type=insert&bid='
				+ modifyData.batchId + '&pid=' + data.nodeId;
		$.dialogBox.openDialog(url, {
			id : 'insertstep',height : '500px',width : '650px',lock : true,
			opacity : 0.07,title : '新增节点'}, addNode, true);
	}
	function toModifyNode(event) {
		var data = event.data;
		modifyData = data._data;
		var url = '${_CONTEXT_PATH}/jsp/sysmgr/batch/stepdetail.jsp?type=update&bid='
				+ modifyData.batchId + '&pid=' + data.nodeId;
		$.dialogBox.openDialog(url, {id : 'insertstep',
			height : '500px',width : '650px',lock : true,opacity : 0.07,title : '修改节点'
		}, addNode, true);
	}
	function addNode(event) {
		var nb = this.iframe.contentWindow.execute();
		return false;
	}
	function removeNode(event) {
		var p = event.data;
		var data = {
			"batchId" : p._data.batchId,
			"stepId" : p.nodeId
		};
		$.dialogBox.confirm("确认删除节点[" + p.nodeName + "]", function() {
			doDeleteNode(data);
		}, true);
	}
	function doDeleteNode(data) {
		var rows = [ data ];
		var url = "${_CONTEXT_PATH}/sys/batch!delete.action";
		var mdata = {
			"deleteIn.deletesStr" : JSON.stringify(rows)
		};
		Utils.ajaxSubmit(url, mdata, function(result) {
			$.dialogBox.info(result.retMsg, function() {
				view.removeNode(data);
			});
		});

	}
</script>
</html>
