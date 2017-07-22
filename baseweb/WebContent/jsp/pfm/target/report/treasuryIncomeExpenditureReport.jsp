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
			var url = "${_CONTEXT_PATH}/report/bank-funds-flow-report!query.action";
			var data = $('#myform').serialize();
			Utils.ajaxSubmit(url, data, function(result) {
				$.dialogBox.info(result.retMsg, function() {
					$("#selectDataDate").text("业务日期："+$("#dataDate").val());
					//$("#selectUnitName").text("填报单位："+$("#unitName").val());
					//result.treasuryFundsNatureList
					$.each(result.treasuryFundsNatureList,function(index,obj){
						$("#"+obj.tarCode).empty();
						$("#"+obj.tarCode).append(obj.tarValue);
					});
				});
			});
		}
	}
	
	function doExport(){
		var sqlKey="";
		var templateName="";
		var templateNameCN="";
		var orgCode =$("#unitId").val();
		var reportDate=$("#dataDate").val();
		var viewFlag ="";
		
		//var url = "${_CONTEXT_PATH}/report/report-base.action?sqlKey="+sqlKey+"&templateName="+templateName+"&templateNameCN="+encodeURI(encodeURI(templateNameCN))+"&params.orgCode="+orgCode+"&params.reportDate="+reportDate+"&viewFlag="+viewFlag;
		//window.open(url);
		
	}
	function doClear() {
		$(".queryBox input[type='text'],#unitId").each(function(i,item){
			item.value ='';
		});
	}
	//选择部门
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
			<%-- <td>所属部门</td>
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
										<td colspan="3" style="width: 375px;"><div><span>地主级</span></div></td>
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
										<td style="width:125px"><div id="">0</div></td>
										<td style="width:125px"><div id="">0</div></td>
										<td style="width:125px"><div id="">0</div></td>
										<td style="width:125px"><div id="">0</div></td>
										<td style="width:125px"><div id="">0</div></td>
										<td style="width:125px"><div id="">0</div></td>
										<td style="width:125px"><div id="">0</div></td>
										<td style="width:125px"><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>1.公共预算收入</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>2.公共预算调拨收入</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>其中：转移性收入</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>3.基金预算收入</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>4.债务预算收入</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>5.国资经营预算收入</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>二、税收收入</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>1.增值税</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>2.消费税</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>3.营业税</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>4.企业所得税</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>5.个人所得税</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>6.资源税</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>7.城市维护建设税</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>8.房产税</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>9.印花税</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>10.城镇土地使用税</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									
									<tr>
										<td><div>11.土地增值税</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>12.车船税</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>13.船舶吨税</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>14.车辆购置税</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>15.关税</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>16.耕地占用税</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>17.契税</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>三、非税收入</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									
									
									<tr>
										<td><div>1.专项收入</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>2.行政事业收费收入</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>3.罚没收入</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>4.国有资本经营收入</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>5.国有资产有偿收入</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>6.其他收入</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>四、政府性基金收入</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>其中：土地出让收入</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>五、国库支出</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>1.公共预算支出</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>2.公共预算调拨支出</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>3.基金预算支出</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>4.基金调拨支出</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>5.债务预算支出</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>6.国有经营预算支出</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>六、期末库存</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
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