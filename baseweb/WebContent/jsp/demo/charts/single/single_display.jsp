<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>单列值基本图形</title>
<jsp:include page="/comm.jsp"></jsp:include>
<script type="text/javascript" src="${_CONTEXT_PATH}/jwebui/charts/FusionCharts.js"></script>
<style type="text/css">
.inbox ,.outbox {padding: 5px;margin: 2px;border: 1px solid #ccc;}
.params td {background-color: #E2EAFF; padding:2px 2px 2px 10px;}
</style>
</head>
<body>
<n:page action='com.soule.app.demo.charts.single.SingleAction' />
<table class='content'>
<tr><td>
<form id="myform" action="${_CONTEXT_PATH}/Single!display.action">
<fieldset class="queryBox"><legend>输入条件</legend>
<table class='s1-params'>
	<tr>
		<td>客户号</td><td><input type='text' id='custNo' name='custNo' validate='{required:true}' value="小王"/></td>
		<td>图形类型</td><td><n:select list="chartType.items" listKey="key" listValue="value" id="chartType" name='chartType' validate='{required:true}'></n:select></td>
		<td></td><td></td>
	</tr>
</table>
</fieldset>
</form>
</td></tr>
<tr><td>
	<table class="s1-button">
		<tr>
			<td>
				<input id='display' name='display' type='button' value='执&nbsp;&nbsp;行' class='nbutton'/>
			</td>
			<td>
				<input id='reset' name='reset' type='button' value='重&nbsp;&nbsp;置' class='nbutton'/>
			</td>
		</tr>
	</table>
</td></tr>
<tr><td>
	<fieldset class="outbox"><legend>输出结果</legend>
		<table>
			<tr>
				<td><div id='datalist'></div></td>
				<td><div id='datachart'></div></td>
			</tr>
		</table>
	</fieldset>
</td></tr>
</table>
</body>
<script type='text/javascript'>
$(function () {
	Utils.validateInit();
	$('#reset').bind('click', doClear);
	$('#display').bind('click', execute);

	//输出表格
	$("#datalist").ligerGrid({
		columns: [
			{ display: '客户号', name: 'custNo', align: 'center' },
			{ display: '月存款额', name: 'amt', align: 'center' },
			{ display: '月份', name: 'month', align: 'center' }
		],
		pageSize:10,
		sortName: 'custNo',
		width: 400,
		height:300,
		onError: function() {
			$.dialogBox.alert("查询数据失败");
		}
	});
});
function doClear() {
	$(".s1-params input[type='text'],.s1-params select").each(function(i,item){
		item.value ='';
	});
}
var chartKey = 0;
function execute() {
	if (!$('#myform').valid()){
		return;
	}
	var mdata = Utils.convertFormData('displayIn','myform');
	var url = "${_CONTEXT_PATH}/demo/single!display.action";
	Utils.ajaxSubmit(url,mdata,function(result) {
		//$.dialogBox.alert(result.retMsg);
		var grid = $("#datalist").ligerGetGridManager();
		grid.setOptions({data:result});
		grid.loadData();

		var custNo = $('#custNo').val();
		var chartFile = $('#chartType').val();
		var myChart = new FusionCharts( "${_CONTEXT_PATH}/jwebui/charts/swf/" + chartFile, "myChart" +(chartKey++), "500", "300", "0", "1");
		//myChart.setXMLUrl("Data.xml");
		//myChart.setJSONUrl ("Data.json");
		for (var u = 0 ; u < result.rows.length; u ++){
			result.rows[u]['mylink'] = "JavaScript:selectOne("+result.rows[u].month+")";
		}
		var jsonData = Utils.convertChartData(result.rows,'month','amt','mylink');
		jsonData['chart'] = {
		          "caption" : "用户("+custNo+")月存款额" ,
		          "xaxisname" : "月份",
		          "yaxisname" : "Money",
		          "numberprefix" : "$" };
		myChart.setJSONData(jsonData);
		myChart.render("datachart");
	});
}
function selectOne(x){
	$.dialogBox.alert("你选的是第"+x+"月份",'提示');
}
</script>
</html>