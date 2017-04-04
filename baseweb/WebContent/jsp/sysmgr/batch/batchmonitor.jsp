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
.node_status0 {background: url(../../../images/question.png) no-repeat center;}
.node_status1 {background: url(../../../images/yunxing.png) no-repeat center -5px;}
.node_status2 {background: url(../../../images/succeed.png) no-repeat center;}
.node_status3 {background: url(../../../images/error.png) no-repeat center;}
.node_status4 {background: url(../../../images/halfsucces.png) no-repeat center;}
.node_status5 {background: url(../../../images/warning.png) no-repeat center;}
</style>
<script type="text/javascript" src="${_CONTEXT_PATH}/jwebui/structure/structure.js"></script>
</head>

<body>
	<n:page action='com.soule.app.sys.batch.BatchMonitorAction'/>
	<n:enums keys='bat_proc_status' />
	<div id='StructureView'></div>
</body>

<script type="text/javascript">
	var nodecolors = {
		'0' : [ 'node_status0', '未知' ],
		'1' : [ 'node_status1', '运行中' ],
		'2' : [ 'node_status2', '成功' ],
		'3' : [ 'node_status3', '失败' ],
		'4' : [ 'node_status4', '忽略' ],
		'5' : [ 'node_status5', '部分成功' ]
	};
	function queryData() {
		var bid = '${bid}';
		var result = {retCode:'${retCode}',retMsg:'${retMsg}'};
		if (Utils.isSuccess(result)) {
			var mdata = Utils.convertObjectData('queryMonitorIn', {"step.batchId" : "${bid}","step.instId" : "${param.iid}"});
			Utils.ajaxSubmit("${_CONTEXT_PATH}/sys/batch-monitor!queryMonitor.action", mdata,afterdone);
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
			//lineColorMap:linecolors,
			lineClassField : 'color',
			nodeClassMap : nodecolors,
			nodeClassField : 'nc',
			menuitems : [ {
				name : '详细日志',
				click : toShowDetailLog,
				filter : 'all'
			}],
			renderUnit : function(i, item, data) {
				$(item).html(data.stepName);
				$(item).attr('class','st_nodecontent node_status'+ data.procStatus);
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
				content.width(200);
				var sb = data.stepName+ '<br/>';
				var st = Utils.getCodeValue('bat_proc_status',data.procStatus);
				sb += '状态:' + st + '<br/>';
				sb += '开始时间:' + data.startTime + '<br/>';
				sb += '结束时间:' + data.endTime+ '<br/>';
				content.html(sb);
			}
		};
		view = $('#StructureView').zStructure(vconfigs);
	}
	$(function() {
		queryData();
	});
	function toShowDetailLog(event) {
		var data = event.data._data;;
		var url = '${_CONTEXT_PATH}/jsp/sysmgr/batch/steplog.jsp?instId=' + data.instId + '&batchId=' + data.batchId + '&stepId=' + data.stepId;
		$.dialogBox.openDialog(url, {id : 'detaillog',height : '450px',width : '650px',lock : true,opacity : 0.07,title : '详细日志'}, true);
	}
 
	 
	 
</script>
</html>
