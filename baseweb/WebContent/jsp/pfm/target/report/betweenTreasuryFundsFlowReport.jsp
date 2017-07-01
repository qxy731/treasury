<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>与其他国库之间资金流动情况统计表</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
body{
	width:1210px;
	margin:0 auto;
}
.params td {
	background-color: #E2EAFF;
	padding: 2px 2px 2px 10px;
}

.l-grid-row-cell div{
	text-align:left;
}
.l-grid-body-table td{
	border:1px solid #bed5f3;
}
</style>
<script type="text/javascript">
	$(function () {
		Utils.validateInit();
		$("#dataDate").ligerDateEditor({format: "yyyy-MM"});
		$("#query").bind('click', query);
		//$("#reset").bind('click', doClear);
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
					$("#selectUnitName").text("填报单位："+$("#unitName").val());
					//result.treasuryFundsNatureList
					$.each(result.treasuryFundsNatureList,function(index,obj){
						$("#"+obj.tarCode).empty();
						$("#"+obj.tarCode).append(obj.tarValue);
					});
					//result.treasuryFundsSourceList
					$.each(result.treasuryFundsSourceList,function(index,obj){
						$("#"+obj.tarCode).empty();
						$("#"+obj.tarCode).append(obj.tarValue);
					});
				});
			});
		}
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
			<td><input type='text' id='dataDate' name='queryIn.dataDate' validate="{required:true}" /></td>
			<td>所属部门</td>
			<td>
				<input type='hidden' id='unitId' name='queryIn.unitId'  value="${logUserInfo.operUnitId}"/>
				<input id="unitName" type='text' name="queryIn.unitName" readonly="readonly" onclick="openSelectUnit()" class="unit_select" value="${logUserInfo.operUnitName}"/>
			</td>
			<td align="right"><input id='query' name='query' type='button' value='查&nbsp;询' class='l-button'/></td>
			<td align="left"><input id='reset' name='reset' type='button' value='导&nbsp;出' class='l-button'/></td>
		</tr>
	</table>
	</form>
</fieldset>
<fieldset class="detailList">
	<div id="qtydeflist" class="l-panel">
			<div class="l-panel-body">
				<div class="l-grid">
					<div class="l-grid-header" style="height:76px;">
						<div class="l-grid-header-inner" style="width: 100%;">
							<table class="l-grid-header-table" cellpadding="0" cellspacing="0">
								<tbody>
									<tr style="height:28px;">
										<td colspan="8"><div><span style="font-size:14pt;">与其他国库之间资金流动情况统计表</span></div></td>
									</tr>
									<tr>
										<td colspan="2"><div style="text-align:left;"><span id="selectUnitName">填报单位：</span></div></td>
										<td colspan="2"><div style="text-align:left;"><span id="selectDataDate">业务期间：</span></div></td>
										<td colspan="4"><div style="text-align:right;padding-right:5px;"><span>单位：万元</span></div></td>
									</tr>
									<tr>
										<td style="width: 150px;"><div><span>从其他国库流入</span></div></td>
										<td style="width: 150px;"><div><span>小计</span></div></td>
										<td style="width: 150px;"><div><span>从总库流入</span></div></td>
										<td style="width: 150px;"><div><span>从省分库流入</span></div></td>
										<td style="width: 150px;"><div><span>从中心支库流入</span></div></td>
										<td style="width: 150px;"><div><span>从区（县）支库流入</span></div></td>
										<td style="width: 150px;"><div><span>从乡镇金库流入</span></div></td>
										<td style="width: 150px;"><div><span>从同级国库流入</span></div></td>
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
										<td style="width:150px"><div>划入（或下级国库上划）预算收入</div></td>
										<td style="width:150px"><div id="">0</div></td>
										<td style="width:150px"><div id="">0</div></td>
										<td style="width:150px"><div id="">0</div></td>
										<td style="width:150px"><div id="">0</div></td>
										<td style="width:150px"><div id="">0</div></td>
										<td style="width:150px"><div id="">0</div></td>
										<td style="width:150px"><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>税收返还流入</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>体制上解流入</div></td>
										<td><div id="">0</div></td>
										<td><div id="">-</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">-</div></td>
									</tr>
									<tr>
										<td><div>总额分成流入</div></td>
										<td><div id="">0</div></td>
										<td><div id="">-</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">-</div></td>
									</tr>
									<tr>
										<td><div>兑付国家债券流入</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">-</div></td>
										<td><div id="">-</div></td>
										<td><div id="">-</div></td>
										<td><div id="">-</div></td>
										<td><div id="">-</div></td>
									</tr>
									<tr>
										<td><div>转贷（或下级国库上划）地方政府债券</div></td>
										<td><div id="">-</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">-</div></td>
									</tr>
									<tr>
										<td><div>系统内调拨（转移）收入</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>划入其他资金</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>从同级国库流入</div></td>
										<td><div id="">0</div></td>
										<td><div id="">-</div></td>
										<td><div id="">-</div></td>
										<td><div id="">-</div></td>
										<td><div id="">-</div></td>
										<td><div id="">-</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>流入合计</div></td>
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
					
					<div class="l-grid">
					<div class="l-grid-header" style="height:26px;">
						<div class="l-grid-header-inner" style="width: 100%;">
							<table class="l-grid-header-table" cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td style="width: 150px;"><div><span>流向其他国库</span></div></td>
										<td style="width: 150px;"><div><span>小计</span></div></td>
										<td style="width: 150px;"><div><span>流向总库</span></div></td>
										<td style="width: 150px;"><div><span>流向省分库</span></div></td>
										<td style="width: 150px;"><div><span>流向中心支库</span></div></td>
										<td style="width: 150px;"><div><span>流向区（县)支库</span></div></td>
										<td style="width: 150px;"><div><span>流向乡镇金库</span></div></td>
										<td style="width: 150px;"><div><span>流向同级国库</span></div></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="l-grid-body">
						<div class="l-grid-body-inner" style="width: 100%;">
							<table class="l-grid-body-table" cellpadding="0" cellspacing="0">
								<tbody  id="treasuryFundsSourceList">
									<tr>
										<td style="width:150px"><div>上划（或划出）预算收入</div></td>
										<td style="width:150px"><div id="">0</div></td>
										<td style="width:150px"><div id="">0</div></td>
										<td style="width:150px"><div id="">0</div></td>
										<td style="width:150px"><div id="">0</div></td>
										<td style="width:150px"><div id="">0</div></td>
										<td style="width:150px"><div id="">0</div></td>
										<td style="width:150px"><div id="">-</div></td>
									</tr>
									<tr>
										<td><div>税收返还流出</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">-</div></td>
									</tr>
									<tr>
										<td><div>体制上解流出</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">-</div></td>
										<td><div id="">-</div></td>
									</tr>
									<tr>
										<td><div>总额分成流出</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">-</div></td>
										<td><div id="">-</div></td>
									</tr>
									<tr>
										<td><div>兑付国家债券流出</div></td>
										<td><div id="">0</div></td>
										<td><div id="">-</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">-</div></td>
									</tr>
									<tr>
										<td><div>上划（或转贷）地方政府债券流出</div></td>
										<td><div id="">0</div></td>
										<td><div id="">-</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">-</div></td>
									</tr>
									<tr>
										<td><div>系统内调拨（转移）支出</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>划出其他资金</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">-</div></td>
									</tr>
									<tr>
										<td><div>流向同级国库</div></td>
										<td><div id="">0</div></td>
										<td><div id="">-</div></td>
										<td><div id="">-</div></td>
										<td><div id="">-</div></td>
										<td><div id="">-</div></td>
										<td><div id="">-</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>流出合计</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
										<td><div id="">0</div></td>
									</tr>
									<tr>
										<td><div>净流入（净流出）</div></td>
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