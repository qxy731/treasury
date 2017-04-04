<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据库监控</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.inbox ,.outbox {padding: 5px;margin: 2px;border: 1px solid #ccc;}
.params td {background-color: #E2EAFF; padding:2px 2px 2px 10px;}
#dbstatus {height:24px;}
</style>
</head>
<body>
<n:page action='com.soule.crm.monitor.daily.db.DbAction' />

<table class='content'>
<tr><td>
	<table class="s1-button">
		<tr>
			<td>
				<input id='tablespace' name='tablespace' type='button' value='刷&nbsp;&nbsp;新' class='l-button'/>
			</td>
		</tr>
	</table>
</td></tr>
<tr><td>
	<fieldset class="outbox" ><legend>数据库状态</legend>
		<table class='params'>
			<tr>
				<td class='tdl' id='dbstatus'></td>
			</tr>
		</table>
	</fieldset>
	<fieldset class="outbox"><legend>数据库表空间信息</legend>
		<div id='tslist'></div>
	</fieldset>
</td></tr>
</table>
</body>
<script type='text/javascript'>
$(function () {
	Utils.validateInit();
	$('#tablespace').bind('click', execute);

	//输出表格
	$("#tslist").ligerGrid({
		columns: [
			{ display: '表空间ID', name: 'tsId', align: 'center' ,width:'20%'},
			{ display: '表空间大小', name: 'size', align: 'right' ,width:'20%',render:renderSize},
			{ display: '空闲空间', name: 'free', align: 'right' ,width:'20%',render:renderFree},
			{ display: '使用百分比', name: 'useRate', align: 'right' ,width:'30%',render:renderRate}
		],
		pageSize:10,
		sortName: 'tsId',
		width: '100%',
		usePager: false,
		onError: function() {
			$.dialogBox.alert("查询数据失败");
		},
		onDblClickRow:function(row,id,context){
			showDetail(row,id,context);
		}
	});
	execute();
	setInterval(function() {
		var url = "${_CONTEXT_PATH}/monitor/db!info.action";
        Utils.ajaxSubmit(url,{},function(result) {
        	var x = result.dbPo.runFlag;
        	if (x) {
        		$('#dbstatus').css('background-color','#99ee99');
        	}
        	else {
        		$('#dbstatus').css('background-color','#ee6666');
        	}
        });
    }, 3000);
	
});

function showDetail(row,id,context) {
	if (row) {
		var url = "${_CONTEXT_PATH}/jsp/monitor/daily/db/db_table.jsp?tsId=" + row.tsId;
		$.dialogBox.openDialog(url,{title:'表空间使用详情',width:800,height:450});
	}
	else {
		$.dialogBox.alert("请先选择需要查看的记录");
	}
}

function renderRate(row) {
	var rate = row.useRate* 100;
	rate = rate.toFixed(1);
	var color = '#99ee99';
	if (rate > 90) {
		color = '#ee9999';
	}
	var html = "<div style='margin-top:5px;width:98%;height:10px;border:2px solid #eeeeee'>"+
	"<div style='float:left;background-color:"+color+
	";height:10px;width:"+rate+"%'>" +rate+"%</div></div>";
	return html;
}

function renderSize(row,t) {
	var size = row.size;
	x = size /(1024*1024);
	var html = "";
	if (x >= 1) {
		html = x.toFixed(1) + "M";
	}
	else {
		var y = size /1024;
		if (y >= 1) {
			html = y.toFixed(1) + "K";
		}
		else {
			html = size;
		}
	}
	return html;
}
function renderFree(row) {
	var size = row.free;
	x = size /(1024*1024);
	var html = "";
	if (x >= 1) {
		html = x.toFixed(1) + "M";
	}
	else {
		var y = size /1024;
		if (y >= 1) {
			html = y.toFixed(1) + "K";
		}
		else {
			html = size;
		}
	}
	return html;
}
function execute() {
	var params = {
			dataAction:'server',
			dataType:'server',
			url: "${_CONTEXT_PATH}/monitor/db!tablespace.action",
			newPage:1,
			parms:''
		};
	var gridManager = $("#tslist").ligerGetGridManager(); 
	gridManager.setOptions(params);
	gridManager.loadData();
}

</script>
</html>