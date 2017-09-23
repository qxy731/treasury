<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>国库会计分析其他数据统计表</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
body{
	width:810px;
	margin:0 auto;
}

.l-grid-row-cell div{
	text-align:left;
}
</style>
<script type="text/javascript">
	$(function () {
		Utils.validateInit();
		//$("#dataDate").ligerDateEditor({format: "yyyy-MM"});
		$("#query").bind('click', query);
		$("#reset").bind('click', doClear);
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
	
	function doClear() {
		if(validate()){
			/* var url = "${_CONTEXT_PATH}/report/bank-funds-flow-report!export4.action";
			var data = $('#myform').serialize();
			
			Utils.ajaxSubmit(url, data); */
			var dataDate =   $('#dataDate').val();
			var unitId = $('#unitId').val();
			var unitName = $("#unitName").val();
			location.href="${_CONTEXT_PATH}/report/bank-funds-flow-report!export4.action?dataDate="+dataDate+"&unitId="+unitId+"&unitName="+unitName;  
		}
	}
	function query() {
		if(validate()){
			var url = "${_CONTEXT_PATH}/report/bank-funds-flow-report!query4.action";
			var data = $('#myform').serialize();
			Utils.ajaxSubmit(url, data, function(result) {
				$.dialogBox.info(result.retMsg, function() {
					$("#selectDataDate").text("业务日期："+$("#dataDate").val());
					$("#selectUnitName").text("填报单位："+$("#unitName").val());
					//result.treasuryFundsNatureList
					$.each(result.accountingAnalysisOtherList,function(index,obj){
						$("#"+obj.tarCode).empty();
						$("#"+obj.tarCode).append(obj.tarValue);
					});
				});
			});
		}
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
			<td><input type='text' id='dataDate' name='queryIn.dataDate' validate="{required:true}" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM'})"/></td>
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
<fieldset class="outbox">
	<div id="qtydeflist" class="l-panel">
			<div class="l-panel-body">
				<div class="l-grid">
					<div class="l-grid-header" style="height:76px;">
						<div class="l-grid-header-inner" style="width: 100%;">
							<table class="l-grid-header-table" cellpadding="0" cellspacing="0">
								<tbody>
									<tr style="height:28px;">
										<td colspan="3"><div><span style="font-size:14pt;">国库会计分析其他数据统计表</span></div></td>
									</tr>
									<tr>
										<td colspan="2"><div style="text-align:left;"><span id="selectUnitName">填报单位：</span></div></td>
										<td><div style="text-align:left;"><span id="selectDataDate">业务期间：</span></div></td>
									</tr>
									<tr>
										<td colspan="2" style="width:500px;"><div><span>指   标</span></div></td>
										<td style="width: 300px;"><div><span>数据</span></div></td>
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
										<td colspan="2" style="width:500px"><div>国库库存余额（万元）</div></td>
										<td style="width:300px"><div id="TE_00069">0</div></td>
									</tr>
									<tr>
										<td colspan="2"><div>制度适应性指标（份）</div></td>
										<td><div id="TE_00070">0</div></td>
									</tr>
									<tr>
										<td colspan="2"><div>检查情况指标（次）</div></td>
										<td><div id="TE_00071">0</div></td>
									</tr>
									<tr>
										<td colspan="2"><div>业务差错总量</div></td>
										<td><div id="TE_00072">0</div></td>
									</tr>
									<tr>
										<td colspan="2"><div>其中：内部业务差错量</div></td>
										<td><div id="TE_00073">0</div></td>
									</tr>
									<tr>
										<td colspan="2"><div>外部业务差错量</div></td>
										<td><div id="TE_00074">0</div></td>
									</tr>
									<tr>
										<td rowspan="2" style="width:250px"><div>系统运行指标</div></td>
										<td style="width:250px"><div>业务系统故障次数（次）</div></td>
										<td><div id="TE_00075">0</div></td>
									</tr>
									<tr>
										<td><div>业务系统改造次数（次）</div></td>
										<td><div id="TE_00076">0</div></td>
									</tr>
									<tr>
										<td rowspan="5"><div>人员指标</div></td>
										<td><div>国库会计人员总数</div></td>
										<td><div id="TE_00077">0</div></td>
									</tr>
									<tr>
										<td><div>国库聘用人员数量</div></td>
										<td><div id="TE_00078">0</div></td>
									</tr>
									<tr>
										<td><div>国库会计人员培训人次</div></td>
										<td><div id="TE_00079">0</div></td>
									</tr>
									<tr>
										<td><div>国库会计人员轮岗人次</div></td>
										<td><div id="TE_00080">0</div></td>
									</tr>
									<tr>
										<td><div>国库重要岗位人员强制休假人次</div></td>
										<td><div id="TE_00081">0</div></td>
									</tr>
									<tr>
										<td colspan="2"><div>业务总量</div></td>
										<td><div id="TE_00089">0</div></td>
									</tr>
									<tr>
										<td colspan="2"><div>其中：预算收入业务量</div></td>
										<td><div id="TE_00090">0</div></td>
									</tr>
									
									<tr>
										<td colspan="2"><div>——预算收入电子化业务量</div></td>
										<td><div id="TE_00097">0</div></td>
									</tr>
									<tr>
										<td colspan="2"><div>商业银行代理的直接支付业务量</div></td>
										<td><div id="TE_00091">0</div></td>
									</tr>
									<tr>
										<td colspan="2"><div>——商业银行代理的直接支付电子化业务量</div></td>
										<td><div id="TE_00098">0</div></td>
									</tr>
									<tr>
										<td colspan="2"><div>商业银行代理的授权支付业务量</div></td>
										<td><div id="TE_00092">0</div></td>
									</tr>
									
									<tr>
										<td colspan="2"><div>——商业银行代理的授权支付电子化业务量</div></td>
										<td><div id="TE_00099">0</div></td>
									</tr>
									<tr>
										<td colspan="2"><div>人行直接办理的集中支付业务量</div></td>
										<td><div id="TE_00093">0</div></td>
									</tr>
									<tr>
										<td colspan="2"><div>——人行直接办理的集中支付电子化业务量</div></td>
										<td><div id="TE_00100">0</div></td>
									</tr>
									<tr>
										<td colspan="2"><div>实拨资金业务量</div></td>
										<td><div id="TE_00094">0</div></td>
									</tr>
									<tr>
										<td colspan="2"><div>——实拨资金电子化业务量</div></td>
										<td><div id="TE_00101">0</div></td>
									</tr>
									<tr>
										<td colspan="2"><div>其中：国库直接支付业务量</div></td>
										<td><div id="TE_00095">0</div></td>
									</tr>
									<tr>
										<td colspan="2"><div>——国库直接支付电子化业务量</div></td>
										<td><div id="TE_00102">0</div></td>
									</tr>
									
									<tr>
										<td colspan="2"><div>退库业务量</div></td>
										<td><div id="TE_00096">0</div></td>
									</tr>
									<tr>
										<td colspan="2"><div>——退库电子化业务量</div></td>
										<td><div id="TE_00103">0</div></td>
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