<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>大连市国库收支统计表</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.l-grid-row-cell div{
	text-align:left;
}

body{
	width:1200px;
	margin:0 auto;
}
</style>
<script type="text/javascript">
	$(function () {
		Utils.validateInit();
		//$("#dataDate").ligerDateEditor({format: "yyyy-MM"});
		$("#query").bind('click', query);
		$("#export").bind('click', doExport);
		$(".l-grid-header-table tr").addClass("l-grid-hd-row");
		$(".l-grid-header-table tr").addClass("l-grid-hd-mul");
		$(".l-grid-header-table td").addClass("l-grid-hd-cell");
		$(".l-grid-header-table td").addClass("l-grid-hd-cell-mul");
		$(".l-grid-header-table div").addClass("l-grid-hd-cell-inner");
		$(".l-grid-header-table span").addClass("l-grid-hd-cell-text");
		$(".l-grid-body-table tr").addClass("l-grid-row");
		$(".l-grid-body-table td").addClass("l-grid-row-cell");
		$(".l-grid-body-table div").addClass("l-grid-row-cell-inner");
		$(".l-grid-body-table div").addClass("l-grid-row-cell-inner-fixedheight");
	});
	
	function validate(){
		if($("#myform").valid()){
			return true;
		}
		return false;
	}
	
	function query() {
		if(validate()){
			var url = "${_CONTEXT_PATH}/report/bank-funds-flow-report!query2.action";
			var data = $('#myform').serialize();
			Utils.ajaxSubmit(url, data, function(result) {
				$.dialogBox.info(result.retMsg, function() {
					$("#selectDataDate").text("业务日期："+$("#dataDate").val());
					
					$.each(result.treasuryIncomList,function(index,obj){
						$("#"+obj.subCode+"_amc").empty();
						$("#"+obj.subCode+"_ayc").empty();
						$("#"+obj.subCode+"_agrc").empty();
						
						$("#"+obj.subCode+"_aml").empty();
						$("#"+obj.subCode+"_ayl").empty();
						$("#"+obj.subCode+"_agrl").empty();
						
						$("#"+obj.subCode+"_amt").empty();
						$("#"+obj.subCode+"_agrt").empty();
						
						$("#"+obj.subCode+"_amc").append(obj.amtMonthC);
						$("#"+obj.subCode+"_ayc").append(obj.amtYearC);
						$("#"+obj.subCode+"_agrc").append(obj.annualGrowthRateC);
						
						$("#"+obj.subCode+"_aml").append(obj.amtMonthL);
						$("#"+obj.subCode+"_ayl").append(obj.amtYearL);
						$("#"+obj.subCode+"_agrl").append(obj.annualGrowthRateL);
						
						$("#"+obj.subCode+"_amt").append(obj.amtMonthT);
						$("#"+obj.subCode+"_agrt").append(obj.annualGrowthRateT);
					});
					
					
				});
			});
		}
	}
	
	function doExport(){
		var sqlKey="";
		var templateName="";
		var templateNameCN="";
		if(validate()){
			var dataDate =   $('#dataDate').val();
			var unitId = $('#unitId').val();
			location.href="${_CONTEXT_PATH}/report/bank-funds-flow-report!export2.action?dataDate="+dataDate+"&unitId="+unitId;  
		}
		
	}
	function doClear() {
		$(".queryBox input[type='text'],#unitId").each(function(i,item){
			item.value ='';
		});
	}
	//选择国库
	function openSelectUnit(){
		Utils.openSelectUnit(null,'',setUnitIdName);
	}
	function setUnitIdName(){
	var selectNode=this.iframe.contentWindow.manager.getSelected();
		if(selectNode){
			var unitId=selectNode.data.unitId;
			var unitName=selectNode.data.unitName;
		  		$("#unitId").val(unitId);
		  		$("#unitName").val(unitName);
		}
	}
</script>
</head>
<body>
<n:page action='com.soule.app.pfm.tm.report.bankfundsflow.BankFundsFlowReportAction' />
<fieldset class="queryBox"><legend>查询条件</legend>
	<form id="myform">
	<table class='params'>
		<tr>
			<td>数据日期</td>
			<td><input type='text' id='dataDate' name='queryIn.dataDate' validate="{required:true}" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM'})"/>
			<input type='hidden' id='unitId' name='queryIn.unitId'  value="${logUserInfo.operUnitId}"/>
			</td>
			<td></td><td></td>
			<%-- <td>所属国库</td>
			<td>
				<input type='hidden' id='unitId' name='queryIn.unitId'  value="${logUserInfo.operUnitId}"/>
				<input id="unitName" type='text' name="queryIn.unitName" readonly="readonly" onclick="openSelectUnit()" class="unit_select" value="${logUserInfo.operUnitName}"/>
			</td> --%>
			<td align="right"><input id='query' name='query' type='button' value='查&nbsp;询' class='l-button'/></td>
			<td align="left"><input id='export' name='export' type='button' value='导&nbsp;出' class='l-button'/></td>
		</tr>
	</table>
	</form>
</fieldset>
<fieldset class="outbox">
	<div id="qtydeflist" class="l-panel">
			<div class="l-panel-body">
				<div class="l-grid">
					<div class="l-grid-header" style="height:98px;">
						<div class="l-grid-header-inner" style="width: 100%;">
							<table class="l-grid-header-table" cellpadding="0" cellspacing="0">
								<tbody>
									<tr style="height:28px;">
										<td colspan="9"><div><span style="font-size:14pt;">大连市国库收支统计表</span></div></td>
									</tr>
									<tr>
										<!-- <td colspan="2"><div style="text-align:left;"><span id="selectUnitName">填报单位：</span></div></td> -->
										<td colspan="2"><div style="text-align:left;"><span id="selectDataDate">业务期间：</span></div></td>
										<td colspan="7"><div style="text-align:right;padding-right:5px;"><span>单位：亿元</span></div></td>
									</tr>
									<tr>
										<td rowspan="2" style="width: 200px;"><div><span>项目</span></div></td>
										<td colspan="3" style="width: 375px;"><div><span>中央级</span></div></td>
										<td colspan="3" style="width: 375px;"><div><span>地方级</span></div></td>
										<td colspan="2" style="width: 250px;"><div><span>合计数</span></div></td>
									</tr>
									<tr>
										<td style="width: 125px;"><div><span>发生金额</span></div></td>
										<td style="width: 125px;"><div><span>累计金额</span></div></td>
										<td style="width: 125px;"><div><span>增长(%)</span></div></td>
										<td style="width: 125px;"><div><span>发生金额</span></div></td>
										<td style="width: 125px;"><div><span>累计金额</span></div></td>
										<td style="width: 125px;"><div><span>增长(%)</span></div></td>
										<td style="width: 125px;"><div><span>累计金额</span></div></td>
										<td style="width: 125px;"><div><span>增长(%)</span></div></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="l-grid-body">
						<div class="l-grid-body-inner" style="width: 100%;">
							<table class="l-grid-body-table" cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td style="width:200px"><div>一、国库收入</div></td>
										<td style="width:125px"><div id="T01_amc">0</div></td>
										<td style="width:125px"><div id="T01_ayc">0</div></td>
										<td style="width:125px"><div id="T01_agrc">0</div></td>
										<td style="width:125px"><div id="T01_aml">0</div></td>
										<td style="width:125px"><div id="T01_ayl">0</div></td>
										<td style="width:125px"><div id="T01_agrl">0</div></td>
										<td style="width:125px"><div id="T01_amt">0</div></td>
										<td style="width:125px"><div id="T01_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>1.公共预算收入</div></td>
										<td><div id="T010101_amc">0</div></td>
										<td><div id="T010101_ayc">0</div></td>
										<td><div id="T010101_agrc">0</div></td>
										<td><div id="T010101_aml">0</div></td>
										<td><div id="T010101_ayl">0</div></td>
										<td><div id="T010101_agrl">0</div></td>
										<td><div id="T010101_amt">0</div></td>
										<td><div id="T010101_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>2.公共预算调拨收入</div></td>
										<td><div id="T010102_amc">0</div></td>
										<td><div id="T010102_ayc">0</div></td>
										<td><div id="T010102_agrc">0</div></td>
										<td><div id="T010102_aml">0</div></td>
										<td><div id="T010102_ayl">0</div></td>
										<td><div id="T010102_agrl">0</div></td>
										<td><div id="T010102_amt">0</div></td>
										<td><div id="T010102_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>其中：转移性收入</div></td>
										<td><div id="110_amc">0</div></td>
										<td><div id="110_ayc">0</div></td>
										<td><div id="110_agrc">0</div></td>
										<td><div id="110_aml">0</div></td>
										<td><div id="110_ayl">0</div></td>
										<td><div id="110_agrl">0</div></td>
										<td><div id="110_amt">0</div></td>
										<td><div id="110_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>3.基金预算收入</div></td>
										<td><div id="T0102_amc">0</div></td>
										<td><div id="T0102_ayc">0</div></td>
										<td><div id="T0102_agrc">0</div></td>
										<td><div id="T0102_aml">0</div></td>
										<td><div id="T0102_ayl">0</div></td>
										<td><div id="T0102_agrl">0</div></td>
										<td><div id="T0102_amt">0</div></td>
										<td><div id="T0102_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>4.债务预算收入</div></td>
										<td><div id="T0103_amc">0</div></td>
										<td><div id="T0103_ayc">0</div></td>
										<td><div id="T0103_agrc">0</div></td>
										<td><div id="T0103_aml">0</div></td>
										<td><div id="T0103_ayl">0</div></td>
										<td><div id="T0103_agrl">0</div></td>
										<td><div id="T0103_amt">0</div></td>
										<td><div id="T0103_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>5.国资经营预算收入</div></td>
										<td><div id="T0106_amc">0</div></td>
										<td><div id="T0106_ayc">0</div></td>
										<td><div id="T0106_agrc">0</div></td>
										<td><div id="T0106_aml">0</div></td>
										<td><div id="T0106_ayl">0</div></td>
										<td><div id="T0106_agrl">0</div></td>
										<td><div id="T0106_amt">0</div></td>
										<td><div id="T0106_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>二、税收收入</div></td>
										<td><div id="101_amc">0</div></td>
										<td><div id="101_ayc">0</div></td>
										<td><div id="101_agrc">0</div></td>
										<td><div id="101_aml">0</div></td>
										<td><div id="101_ayl">0</div></td>
										<td><div id="101_agrl">0</div></td>
										<td><div id="101_amt">0</div></td>
										<td><div id="101_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>1.增值税</div></td>
										<td><div id="10101_amc">0</div></td>
										<td><div id="10101_ayc">0</div></td>
										<td><div id="10101_agrc">0</div></td>
										<td><div id="10101_aml">0</div></td>
										<td><div id="10101_ayl">0</div></td>
										<td><div id="10101_agrl">0</div></td>
										<td><div id="10101_amt">0</div></td>
										<td><div id="10101_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>2.消费税</div></td>
										<td><div id="10102_amc">0</div></td>
										<td><div id="10102_ayc">0</div></td>
										<td><div id="10102_agrc">0</div></td>
										<td><div id="10102_aml">0</div></td>
										<td><div id="10102_ayl">0</div></td>
										<td><div id="10102_agrl">0</div></td>
										<td><div id="10102_amt">0</div></td>
										<td><div id="10102_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>3.营业税</div></td>
										<td><div id="10103_amc">0</div></td>
										<td><div id="10103_ayc">0</div></td>
										<td><div id="10103_agrc">0</div></td>
										<td><div id="10103_aml">0</div></td>
										<td><div id="10103_ayl">0</div></td>
										<td><div id="10103_agrl">0</div></td>
										<td><div id="10103_amt">0</div></td>
										<td><div id="10103_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>4.企业所得税</div></td>
										<td><div id="10104_amc">0</div></td>
										<td><div id="10104_ayc">0</div></td>
										<td><div id="10104_agrc">0</div></td>
										<td><div id="10104_aml">0</div></td>
										<td><div id="10104_ayl">0</div></td>
										<td><div id="10104_agrl">0</div></td>
										<td><div id="10104_amt">0</div></td>
										<td><div id="10104_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>5.个人所得税</div></td>
										<td><div id="10106_amc">0</div></td>
										<td><div id="10106_ayc">0</div></td>
										<td><div id="10106_agrc">0</div></td>
										<td><div id="10106_aml">0</div></td>
										<td><div id="10106_ayl">0</div></td>
										<td><div id="10106_agrl">0</div></td>
										<td><div id="10106_amt">0</div></td>
										<td><div id="10106_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>6.资源税</div></td>
										<td><div id="10107_amc">0</div></td>
										<td><div id="10107_ayc">0</div></td>
										<td><div id="10107_agrc">0</div></td>
										<td><div id="10107_aml">0</div></td>
										<td><div id="10107_ayl">0</div></td>
										<td><div id="10107_agrl">0</div></td>
										<td><div id="10107_amt">0</div></td>
										<td><div id="10107_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>7.城市维护建设税</div></td>
										<td><div id="10109_amc">0</div></td>
										<td><div id="10109_ayc">0</div></td>
										<td><div id="10109_agrc">0</div></td>
										<td><div id="10109_aml">0</div></td>
										<td><div id="10109_ayl">0</div></td>
										<td><div id="10109_agrl">0</div></td>
										<td><div id="10109_amt">0</div></td>
										<td><div id="10109_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>8.房产税</div></td>
										<td><div id="10110_amc">0</div></td>
										<td><div id="10110_ayc">0</div></td>
										<td><div id="10110_agrc">0</div></td>
										<td><div id="10110_aml">0</div></td>
										<td><div id="10110_ayl">0</div></td>
										<td><div id="10110_agrl">0</div></td>
										<td><div id="10110_amt">0</div></td>
										<td><div id="10110_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>9.印花税</div></td>
										<td><div id="10111_amc">0</div></td>
										<td><div id="10111_ayc">0</div></td>
										<td><div id="10111_agrc">0</div></td>
										<td><div id="10111_aml">0</div></td>
										<td><div id="10111_ayl">0</div></td>
										<td><div id="10111_agrl">0</div></td>
										<td><div id="10111_amt">0</div></td>
										<td><div id="10111_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>10.城镇土地使用税</div></td>
										<td><div id="10112_amc">0</div></td>
										<td><div id="10112_ayc">0</div></td>
										<td><div id="10112_agrc">0</div></td>
										<td><div id="10112_aml">0</div></td>
										<td><div id="10112_ayl">0</div></td>
										<td><div id="10112_agrl">0</div></td>
										<td><div id="10112_amt">0</div></td>
										<td><div id="10112_agrt">0</div></td>
									</tr>
									
									<tr>
										<td><div>11.土地增值税</div></td>
										<td><div id="10113_amc">0</div></td>
										<td><div id="10113_ayc">0</div></td>
										<td><div id="10113_agrc">0</div></td>
										<td><div id="10113_aml">0</div></td>
										<td><div id="10113_ayl">0</div></td>
										<td><div id="10113_agrl">0</div></td>
										<td><div id="10113_amt">0</div></td>
										<td><div id="10113_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>12.车船税</div></td>
										<td><div id="10114_amc">0</div></td>
										<td><div id="10114_ayc">0</div></td>
										<td><div id="10114_agrc">0</div></td>
										<td><div id="10114_aml">0</div></td>
										<td><div id="10114_ayl">0</div></td>
										<td><div id="10114_agrl">0</div></td>
										<td><div id="10114_amt">0</div></td>
										<td><div id="10114_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>13.船舶吨税</div></td>
										<td><div id="10115_amc">0</div></td>
										<td><div id="10115_ayc">0</div></td>
										<td><div id="10115_agrc">0</div></td>
										<td><div id="10115_aml">0</div></td>
										<td><div id="10115_ayl">0</div></td>
										<td><div id="10115_agrl">0</div></td>
										<td><div id="10115_amt">0</div></td>
										<td><div id="10115_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>14.车辆购置税</div></td>
										<td><div id="10116_amc">0</div></td>
										<td><div id="10116_ayc">0</div></td>
										<td><div id="10116_agrc">0</div></td>
										<td><div id="10116_aml">0</div></td>
										<td><div id="10116_ayl">0</div></td>
										<td><div id="10116_agrl">0</div></td>
										<td><div id="10116_amt">0</div></td>
										<td><div id="10116_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>15.关税</div></td>
										<td><div id="10117_amc">0</div></td>
										<td><div id="10117_ayc">0</div></td>
										<td><div id="10117_agrc">0</div></td>
										<td><div id="10117_aml">0</div></td>
										<td><div id="10117_ayl">0</div></td>
										<td><div id="10117_agrl">0</div></td>
										<td><div id="10117_amt">0</div></td>
										<td><div id="10117_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>16.耕地占用税</div></td>
										<td><div id="10118_amc">0</div></td>
										<td><div id="10118_ayc">0</div></td>
										<td><div id="10118_agrc">0</div></td>
										<td><div id="10118_aml">0</div></td>
										<td><div id="10118_ayl">0</div></td>
										<td><div id="10118_agrl">0</div></td>
										<td><div id="10118_amt">0</div></td>
										<td><div id="10118_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>17.契税</div></td>
										<td><div id="10119_amc">0</div></td>
										<td><div id="10119_ayc">0</div></td>
										<td><div id="10119_agrc">0</div></td>
										<td><div id="10119_aml">0</div></td>
										<td><div id="10119_ayl">0</div></td>
										<td><div id="10119_agrl">0</div></td>
										<td><div id="10119_amt">0</div></td>
										<td><div id="10119_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>三、非税收入</div></td>
										<td><div id="103_amc">0</div></td>
										<td><div id="103_ayc">0</div></td>
										<td><div id="103_agrc">0</div></td>
										<td><div id="103_aml">0</div></td>
										<td><div id="103_ayl">0</div></td>
										<td><div id="103_agrl">0</div></td>
										<td><div id="103_amt">0</div></td>
										<td><div id="103_agrt">0</div></td>
									</tr>
									
									
									<tr>
										<td><div>1.专项收入</div></td>
										<td><div id="10302_amc">0</div></td>
										<td><div id="10302_ayc">0</div></td>
										<td><div id="10302_agrc">0</div></td>
										<td><div id="10302_aml">0</div></td>
										<td><div id="10302_ayl">0</div></td>
										<td><div id="10302_agrl">0</div></td>
										<td><div id="10302_amt">0</div></td>
										<td><div id="10302_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>2.行政事业收费收入</div></td>
										<td><div id="10304_amc">0</div></td>
										<td><div id="10304_ayc">0</div></td>
										<td><div id="10304_agrc">0</div></td>
										<td><div id="10304_aml">0</div></td>
										<td><div id="10304_ayl">0</div></td>
										<td><div id="10304_agrl">0</div></td>
										<td><div id="10304_amt">0</div></td>
										<td><div id="10304_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>3.罚没收入</div></td>
										<td><div id="10305_amc">0</div></td>
										<td><div id="10305_ayc">0</div></td>
										<td><div id="10305_agrc">0</div></td>
										<td><div id="10305_aml">0</div></td>
										<td><div id="10305_ayl">0</div></td>
										<td><div id="10305_agrl">0</div></td>
										<td><div id="10305_amt">0</div></td>
										<td><div id="10305_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>4.国有资本经营收入</div></td>
										<td><div id="10306_amc">0</div></td>
										<td><div id="10306_ayc">0</div></td>
										<td><div id="10306_agrc">0</div></td>
										<td><div id="10306_aml">0</div></td>
										<td><div id="10306_ayl">0</div></td>
										<td><div id="10306_agrl">0</div></td>
										<td><div id="10306_amt">0</div></td>
										<td><div id="10306_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>5.国有资产有偿收入</div></td>
										<td><div id="10307_amc">0</div></td>
										<td><div id="10307_ayc">0</div></td>
										<td><div id="10307_agrc">0</div></td>
										<td><div id="10307_aml">0</div></td>
										<td><div id="10307_ayl">0</div></td>
										<td><div id="10307_agrl">0</div></td>
										<td><div id="10307_amt">0</div></td>
										<td><div id="10307_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>6.其他收入</div></td>
										<td><div id="10399_amc">0</div></td>
										<td><div id="10399_ayc">0</div></td>
										<td><div id="10399_agrc">0</div></td>
										<td><div id="10399_aml">0</div></td>
										<td><div id="10399_ayl">0</div></td>
										<td><div id="10399_agrl">0</div></td>
										<td><div id="10399_amt">0</div></td>
										<td><div id="10399_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>四、政府性基金收入</div></td>
										<td><div id="10301_amc">0</div></td>
										<td><div id="10301_ayc">0</div></td>
										<td><div id="10301_agrc">0</div></td>
										<td><div id="10301_aml">0</div></td>
										<td><div id="10301_ayl">0</div></td>
										<td><div id="10301_agrl">0</div></td>
										<td><div id="10301_amt">0</div></td>
										<td><div id="10301_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>其中：土地出让收入</div></td>
										<td><div id="103014801_amc">0</div></td>
										<td><div id="103014801_ayc">0</div></td>
										<td><div id="103014801_agrc">0</div></td>
										<td><div id="103014801_aml">0</div></td>
										<td><div id="103014801_ayl">0</div></td>
										<td><div id="103014801_agrl">0</div></td>
										<td><div id="103014801_amt">0</div></td>
										<td><div id="103014801_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>五、国库支出</div></td>
										<td><div id="T02_amc">0</div></td>
										<td><div id="T02_ayc">0</div></td>
										<td><div id="T02_agrc">0</div></td>
										<td><div id="T02_aml">0</div></td>
										<td><div id="T02_ayl">0</div></td>
										<td><div id="T02_agrl">0</div></td>
										<td><div id="T02_amt">0</div></td>
										<td><div id="T02_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>1.公共预算支出</div></td>
										<td><div id="T020101_amc">0</div></td>
										<td><div id="T020101_ayc">0</div></td>
										<td><div id="T020101_agrc">0</div></td>
										<td><div id="T020101_aml">0</div></td>
										<td><div id="T020101_ayl">0</div></td>
										<td><div id="T020101_agrl">0</div></td>
										<td><div id="T020101_amt">0</div></td>
										<td><div id="T020101_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>2.公共预算调拨支出</div></td>
										<td><div id="T020102_amc">0</div></td>
										<td><div id="T020102_ayc">0</div></td>
										<td><div id="T020102_agrc">0</div></td>
										<td><div id="T020102_aml">0</div></td>
										<td><div id="T020102_ayl">0</div></td>
										<td><div id="T020102_agrl">0</div></td>
										<td><div id="T020102_amt">0</div></td>
										<td><div id="T020102_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>3.基金预算支出</div></td>
										<td><div id="T020201_amc">0</div></td>
										<td><div id="T020201_ayc">0</div></td>
										<td><div id="T020201_agrc">0</div></td>
										<td><div id="T020201_aml">0</div></td>
										<td><div id="T020201_ayl">0</div></td>
										<td><div id="T020201_agrl">0</div></td>
										<td><div id="T020201_amt">0</div></td>
										<td><div id="T020201_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>4.基金调拨支出</div></td>
										<td><div id="T020202_amc">0</div></td>
										<td><div id="T020202_ayc">0</div></td>
										<td><div id="T020202_agrc">0</div></td>
										<td><div id="T020202_aml">0</div></td>
										<td><div id="T020202_ayl">0</div></td>
										<td><div id="T020202_agrl">0</div></td>
										<td><div id="T020202_amt">0</div></td>
										<td><div id="T020202_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>5.债务预算支出</div></td>
										<td><div id="T0203_amc">0</div></td>
										<td><div id="T0203_ayc">0</div></td>
										<td><div id="T0203_agrc">0</div></td>
										<td><div id="T0203_aml">0</div></td>
										<td><div id="T0203_ayl">0</div></td>
										<td><div id="T0203_agrl">0</div></td>
										<td><div id="T0203_amt">0</div></td>
										<td><div id="T0203_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>6.国有经营预算支出</div></td>
										<td><div id="T0206_amc">0</div></td>
										<td><div id="T0206_ayc">0</div></td>
										<td><div id="T0206_agrc">0</div></td>
										<td><div id="T0206_aml">0</div></td>
										<td><div id="T0206_ayl">0</div></td>
										<td><div id="T0206_agrl">0</div></td>
										<td><div id="T0206_amt">0</div></td>
										<td><div id="T0206_agrt">0</div></td>
									</tr>
									<tr>
										<td><div>六、期末库存</div></td>
										<td><div id="T00_amc">0</div></td>
										<td><div id="T00_ayc">0</div></td>
										<td><div id="T00_agrc">0</div></td>
										<td><div id="T00_aml">0</div></td>
										<td><div id="T00_ayl">0</div></td>
										<td><div id="T00_agrl">0</div></td>
										<td><div id="T00_amt">0</div></td>
										<td><div id="T00_agrt">0</div></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					</div>
					
				</div>
	</div>
</fieldset>
</body>
</html>