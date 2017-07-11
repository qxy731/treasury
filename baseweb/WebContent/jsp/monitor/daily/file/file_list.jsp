<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件系统监控</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.inbox ,.outbox {padding: 5px;margin: 2px;border: 1px solid #ccc;}
.params td {background-color: #E2EAFF; padding:2px 2px 2px 10px;}
#reset {width: 80px;}
</style>
</head>
<body>
<n:page action='com.soule.crm.monitor.daily.file.FileAction' />

<table class='content'>
<tr><td>
	<table class="s1-button">
		<tr>
			<td>
				<input id='list' name='list' type='button' value='刷&nbsp;新' class='l-button'/>
			</td>
		</tr>
	</table>
</td></tr>
<tr><td>
	<fieldset class="outbox"><legend>文件系统列表</legend>
		<div id='detaillist'></div>
</fieldset>
</td></tr>
</table>
</body>
<script type='text/javascript'>
$(function () {
	Utils.validateInit();
	$('#reset').bind('click', doClear);
	$('#list').bind('click', execute);

	//输出表格
	$("#detaillist").ligerGrid({
		columns: [
			{ display: '文件系统', name: 'fileId', align: 'center' ,width: '20%'},
			{ display: '总数', name: 'size', align: 'center' ,width: '15%'},
			{ display: '已使用', name: 'used', align: 'center' ,width: '15%'},
			{ display: '可使用', name: 'avail', align: 'center' ,width: '10%'},
			{ display: '使用百分比', name: 'usedRate', align: 'center' ,width: '10%'},
			{ display: '挂接点', name: 'mountOn', align: 'center' ,width: '15%'},
			{ display: '类型', name: 'type', align: 'center' ,width: '10%'}
		],
		usePager:false,
		pageSize:10,
		sortName: 'fileId',
		width: '100%',
		onError: function() {
			$.dialogBox.alert("查询数据失败");
		}
	});
	execute();
});
function doClear() {
	$(".inbox input[type='text']").each(function(i,item){
		item.value ='';
	});
}

function execute() {
	var params = {
		dataAction:'server',
		dataType:'server',
		url: "${_CONTEXT_PATH}/monitor/file!list.action",
		newPage:1,
		parms:''
	};
	var gridManager = $("#detaillist").ligerGetGridManager(); 
	gridManager.setOptions(params);
	gridManager.loadData();
}

</script>
</html>