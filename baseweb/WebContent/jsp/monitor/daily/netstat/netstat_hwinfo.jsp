<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网络状况I/O监控</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.inbox ,.outbox {padding: 5px;margin: 2px;border: 1px solid #ccc;}
.params td {background-color: #E2EAFF; padding:2px 2px 2px 10px;}
#reset {width: 80px;}
</style>
</head>
<body>
<n:page action='com.soule.crm.monitor.daily.netstat.NetstatAction' />

<table class='content'>
<tr><td>
	<table class="s1-button">
		<tr>
			<td>
				<input id='hwinfo' name='hwinfo' type='button' value='刷&nbsp;新' class='l-button'/>
			</td>
		</tr>
	</table>
</td></tr>
<tr><td>
	<fieldset class="outbox"><legend>网卡列表</legend>
		<div id='netlist'></div>
	</fieldset>
</td></tr>
</table>
</body>
<script type='text/javascript'>
$(function () {
	Utils.validateInit();
	$('#hwinfo').bind('click', execute);

	//输出表格
	$("#netlist").ligerGrid({
		columns: [
			{ display: '网卡ID', name: 'hwId', align: 'center' },
			{ display: '网卡地址', name: 'address', align: 'center' },
			{ display: '网卡描述', name: 'desc', align: 'center' },
			{ display: '硬件地址', name: 'hwAddr', align: 'center' },
			{ display: '网关', name: 'netmask', align: 'center' },
			{ display: '类型', name: 'type', align: 'center' },
			{ display: '网卡速度', name: 'speed', align: 'center' },
			{ display: '接受字节数', name: 'rxBytes', align: 'center' }
			//{ display: '发送字节数', name: 'txBytes', align: 'center' },
			//{ display: '接受错误数', name: 'rxErrors', align: 'center' },
			//{ display: '发送错误数', name: 'txErrors', align: 'center' }
		],
		pageSize:10,
		sortName: 'txBytes',
		width: '100%',
		usePager:false,
		onError: function() {
			$.dialogBox.alert("查询数据失败");
		},
		onDblClickRow:function(row,id,context){
			showDetail(row,id,context);
		}
	});
	execute();
});

function showDetail(row,id,context) {
	if (row) {
		var url = "${_CONTEXT_PATH}/jsp/monitor/daily/netstat/netstat_flowinfo.jsp?hwid=" + row.hwId;
		Utils.openTab('netstat-'+row.hwId,'网络详情' + row.hwId,url);
	}
	else {
		$.dialogBox.alert("请先选择需要查看的记录");
	}
}


function execute() {

	var params = {
			dataAction:'server',
			dataType:'server',
			url: "${_CONTEXT_PATH}/monitor/netstat!hwinfo.action",
			newPage:1,
			parms:''
		};
	var gridManager = $("#netlist").ligerGetGridManager(); 
	gridManager.setOptions(params);
	gridManager.loadData();
}

</script>
</html>