<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>与商业银行之间资金流动情况统计表</title>
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
	
	function query() {
		if(validate()){
			var url = "${_CONTEXT_PATH}/report/bank-funds-flow-report!query.action";
			var data = $('#myform').serialize();
			Utils.ajaxSubmit(url, data, function(result) {
				$.dialogBox.info(result.retMsg, function() {
					$("#selectDataDate").text("业务日期："+$("#dataDate").val());
					$("#selectUnitName").text("填报单位："+$("#unitName").val());
					//result.bankFundsFlowList //http://caibaojian.com/jquery-each-json.html
					var newCustOrgData = '';
					$.each(result.bankFundsFlowList,function(index,obj){
						/*var innerHTML = '<tr class="l-grid-row"><td class="l-grid-row-cell" style="width:200px"><div>'+obj.custOrgName+'</div></td>';
						innerHTML += '<td class="l-grid-row-cell" style="width:200px;" ><div class="l-grid-row-cell-inner l-grid-row-cell-inner-fixedheight">'+obj.bankAllInflow+'</div></td>';
						innerHTML += '<td class="l-grid-row-cell" style="width:200px"><div class="l-grid-row-cell-inner l-grid-row-cell-inner-fixedheight" >'+obj.bankSpecialInflow+'</div></td>';
						innerHTML += '<td class="l-grid-row-cell" style="width:200px"><div class="l-grid-row-cell-inner l-grid-row-cell-inner-fixedheight">'+obj.bankAllOutflow+'</div></td>';
						innerHTML += '<td class="l-grid-row-cell" style="width:200px"><div class="l-grid-row-cell-inner l-grid-row-cell-inner-fixedheight">'+obj.bankSpecialOutflow+'</div></td>';
						innerHTML += '<td class="l-grid-row-cell" style="width:200px"><div class="l-grid-row-cell-inner l-grid-row-cell-inner-fixedheight">'+obj.bankAllNetFlow+'</div></td></tr>';
						newCustOrgData += innerHTML;*/
						
						$("#"+obj.custOrgNo+"_BANK_ALL_INFLOW").empty();
						$("#"+obj.custOrgNo+"_BANK_SPECIAL_INFLOW").empty();
						$("#"+obj.custOrgNo+"_BANK_ALL_OUTFLOW").empty();
						$("#"+obj.custOrgNo+"_BANK_SPECIAL_OUTFLOW").empty();
						$("#"+obj.custOrgNo+"_BANK_ALL_NETFLOW").empty();
						$("#"+obj.custOrgNo+"_BANK_ALL_INFLOW").append(obj.bankAllInflow);
						$("#"+obj.custOrgNo+"_BANK_SPECIAL_INFLOW").append(obj.bankSpecialInflow);
						$("#"+obj.custOrgNo+"_BANK_ALL_OUTFLOW").append(obj.bankAllOutflow);
						$("#"+obj.custOrgNo+"_BANK_SPECIAL_OUTFLOW").append(obj.bankSpecialOutflow);
						$("#"+obj.custOrgNo+"_BANK_ALL_NETFLOW").append(obj.bankAllNetFlow);
						
					});
					$("#custOrgData").empty();
					$("#custOrgData").append(newCustOrgData);
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
		if(validate()){
			var dataDate =   $('#dataDate').val();
			var unitId = $('#unitId').val();
			var unitName = $('#unitName').val();
			location.href="${_CONTEXT_PATH}/report/bank-funds-flow-report!export.action?dataDate="+dataDate+"&unitId="+unitId+"&unitName="+unitName;  
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
					<div class="l-grid-header" style="height:98px;">
						<div class="l-grid-header-inner" style="width: 100%;">
							<table class="l-grid-header-table" cellpadding="0" cellspacing="0">
								<tbody>
									<tr style="height:28px;">
										<td colspan="6"><div><span style="font-size:14pt;">与商业银行之间资金流动情况统计表</span></div></td>
									</tr>
									<tr>
										<td colspan="2"><div style="text-align:left;"><span id="selectUnitName">填报单位：</span></div></td>
										<td colspan="2"><div style="text-align:left;"><span id="selectDataDate">业务期间：</span></div></td>
										<td colspan="2"><div style="text-align:right;padding-right:5px;"><span>单位：万元</span></div></td>
									</tr>
									<tr>
										<td colspan="6"><div style="text-align:left;"><span>一、按资金流向分类</span></div></td>
									</tr>
									<tr>
										<td style="width: 200px;"><div><span>机构类别</span></div></td>
										<td style="width: 200px;"><div><span>从商业银行流入国库</span></div></td>
										<td style="width: 200px;"><div><span>其中：来自专户</span></div></td>
										<td style="width: 200px;"><div><span>从国库流向商业银行</span></div></td>
										<td style="width: 200px;"><div><span>其中：流向专户</span></div></td>
										<td style="width: 200px;"><div><span>净流入（流出）</span></div></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="l-grid-body">
						<div class="l-grid-body-inner" style="width: 100%;">
							<table class="l-grid-body-table" cellpadding="0" cellspacing="0">
								<tbody id="">
									<tr>
										<td style="width:200px"><div>国有大型商业银行</div></td>
										<td style="width:200px"><div id="100055_BANK_ALL_INFLOW">0</div></td>
										<td style="width:200px"><div id="100055_BANK_SPECIAL_INFLOW">0</div></td>
										<td style="width:200px"><div id="100055_BANK_ALL_OUTFLOW">0</div></td>
										<td style="width:200px"><div id="100055_BANK_SPECIAL_OUTFLOW">0</div></td>
										<td style="width:200px"><div id="100055_BANK_ALL_NETFLOW">0</div></td>
									</tr>
									<tr>
										<td><div>其中：工商银行</div></td>
										<td><div id="100000_BANK_ALL_INFLOW">0</div></td>
										<td><div id="100000_BANK_SPECIAL_INFLOW">0</div></td>
										<td><div id="100000_BANK_ALL_OUTFLOW">0</div></td>
										<td><div id="100000_BANK_SPECIAL_OUTFLOW">0</div></td>
										<td><div id="100000_BANK_ALL_NETFLOW">0</div></td>
									</tr>
									<tr>
										<td><div>农业银行</div></td>
										<td><div id="100001_BANK_ALL_INFLOW">0</div></td>
										<td><div id="100001_BANK_SPECIAL_INFLOW">0</div></td>
										<td><div id="100001_BANK_ALL_OUTFLOW">0</div></td>
										<td><div id="100001_BANK_SPECIAL_OUTFLOW">0</div></td>
										<td><div id="100001_BANK_ALL_NETFLOW">0</div></td>
									</tr>
									<tr>
										<td><div>中国银行</div></td>
										<td><div id="100002_BANK_ALL_INFLOW">0</div></td>
										<td><div id="100002_BANK_SPECIAL_INFLOW">0</div></td>
										<td><div id="100002_BANK_ALL_OUTFLOW">0</div></td>
										<td><div id="100002_BANK_SPECIAL_OUTFLOW">0</div></td>
										<td><div id="100002_BANK_ALL_NETFLOW">0</div></td>
									</tr>
									<tr>
										<td><div>建设银行</div></td>
										<td><div id="100004_BANK_ALL_INFLOW">0</div></td>
										<td><div id="100004_BANK_SPECIAL_INFLOW">0</div></td>
										<td><div id="100004_BANK_ALL_OUTFLOW">0</div></td>
										<td><div id="100004_BANK_SPECIAL_OUTFLOW">0</div></td>
										<td><div id="100004_BANK_ALL_NETFLOW">0</div></td>
									</tr>
									<tr>
										<td><div>交通银行</div></td>
										<td><div id="100003_BANK_ALL_INFLOW">0</div></td>
										<td><div id="100003_BANK_SPECIAL_INFLOW">0</div></td>
										<td><div id="100003_BANK_ALL_OUTFLOW">0</div></td>
										<td><div id="100003_BANK_SPECIAL_OUTFLOW">0</div></td>
										<td><div id="100003_BANK_ALL_NETFLOW">0</div></td>
									</tr>
									<tr>
										<td><div>股份制商业银行</div></td>
										<td><div id="100018_BANK_ALL_INFLOW">0</div></td>
										<td><div id="100018_BANK_SPECIAL_INFLOW">0</div></td>
										<td><div id="100018_BANK_ALL_OUTFLOW">0</div></td>
										<td><div id="100018_BANK_SPECIAL_OUTFLOW">0</div></td>
										<td><div id="100018_BANK_ALL_NETFLOW">0</div></td>
									</tr>
									<tr>
										<td><div>城市商业银行</div></td>
										<td><div id="100028_BANK_ALL_INFLOW">0</div></td>
										<td><div id="100028_BANK_SPECIAL_INFLOW">0</div></td>
										<td><div id="100028_BANK_ALL_OUTFLOW">0</div></td>
										<td><div id="100028_BANK_SPECIAL_OUTFLOW">0</div></td>
										<td><div id="100028_BANK_ALL_NETFLOW">0</div></td>
									</tr>
									<tr>
										<td><div>农村金融机构</div></td>
										<td><div id="100030_BANK_ALL_INFLOW">0</div></td>
										<td><div id="100030_BANK_SPECIAL_INFLOW">0</div></td>
										<td><div id="100030_BANK_ALL_OUTFLOW">0</div></td>
										<td><div id="100030_BANK_SPECIAL_OUTFLOW">0</div></td>
										<td><div id="100030_BANK_ALL_NETFLOW">0</div></td>
									</tr>
									<tr>
										<td><div>邮政储蓄银行</div></td>
										<td><div id="100005_BANK_ALL_INFLOW">0</div></td>
										<td><div id="100005_BANK_SPECIAL_INFLOW">0</div></td>
										<td><div id="100005_BANK_ALL_OUTFLOW">0</div></td>
										<td><div id="100005_BANK_SPECIAL_OUTFLOW">0</div></td>
										<td><div id="100005_BANK_ALL_NETFLOW">0</div></td>
									</tr>
									<tr>
										<td><div>政策性银行</div></td>
										<td><div id="100007_BANK_ALL_INFLOW">0</div></td>
										<td><div id="100007_BANK_SPECIAL_INFLOW">0</div></td>
										<td><div id="100007_BANK_ALL_OUTFLOW">0</div></td>
										<td><div id="100007_BANK_SPECIAL_OUTFLOW">0</div></td>
										<td><div id="100007_BANK_ALL_NETFLOW">0</div></td>
									</tr>
									<tr>
										<td><div>其他机构</div></td>
										<td><div id="100050_BANK_ALL_INFLOW">0</div></td>
										<td><div id="100050_BANK_SPECIAL_INFLOW">0</div></td>
										<td><div id="100050_BANK_ALL_OUTFLOW">0</div></td>
										<td><div id="100050_BANK_SPECIAL_OUTFLOW">0</div></td>
										<td><div id="100050_BANK_ALL_NETFLOW">0</div></td>
									</tr>
									<tr>
										<td><div>合计</div></td>
										<td><div id="CustOrgNoSum_BANK_ALL_INFLOW">0</div></td>
										<td><div id="CustOrgNoSum_BANK_SPECIAL_INFLOW">0</div></td>
										<td><div id="CustOrgNoSum_BANK_ALL_OUTFLOW">0</div></td>
										<td><div id="CustOrgNoSum_BANK_SPECIAL_OUTFLOW">0</div></td>
										<td><div id="CustOrgNoSum_BANK_ALL_NETFLOW">0</div></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					</div>
					
					<div class="l-grid">
					<div class="l-grid-header" style="height:48px;">
						<div class="l-grid-header-inner" style="width: 100%;">
							<table class="l-grid-header-table" cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td colspan="6"><div style="text-align:left;"><span>二、按资金性质分类</span></div></td>
									</tr>
									<tr>
										<td style="width: 200px;"><div><span>业务类型</span></div></td>
										<td style="width: 400px;" colspan="2"><div><span>从商业银行流入国库</span></div></td>
										<td style="width: 400px;" colspan="2"><div><span>从国库流向商业银行</span></div></td>
										<td style="width: 200px;"><div><span>净流入（流出）</span></div></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="l-grid-body">
						<div class="l-grid-body-inner" style="width: 100%;">
							<table class="l-grid-body-table" cellpadding="0" cellspacing="0">
								<tbody id="treasuryFundsNatureList">
									<tr>
										<td rowspan="10" style="width:200px"><div>预算收支退</div></td>
										<td style="width:200px"><div>预算收入流入</div></td>
										<td style="width:200px"><div id="TE_00005">0</div></td>
										<td style="width:200px"><div>预算支出流出</div></td>
										<td style="width:200px"><div id="TE_00036">0</div></td>
										<td rowspan="10" style="width:200px"><div id="TE_00104">0</div></td>
									</tr>
									<tr>
										<td style="width:200px"><div>其中：纸质税票</div></td>
										<td style="width:200px"><div id="TE_00014">0</div></td>
										<td style="width:200px"><div>其中：商业银行代理的集中支付</div></td>
										<td style="width:200px"><div id="TE_00046">0</div></td>
									</tr>
									<tr>
										<td><div>电子税票</div></td>
										<td><div id="TE_00015">0</div></td>
										<td><div>——直接支付</div></td>
										<td><div id="TE_00114">0</div></td>
									</tr>
									<tr>
										<td><div>-</div></td>
										<td><div>-</div></td>
										<td><div>——授权支付</div></td>
										<td><div id="TE_00115">0</div></td>
									</tr>
									<tr>
										<td><div>-</div></td>
										<td><div>-</div></td>
										<td><div>人行直接办理的集中支付</div></td>
										<td><div id="TE_00047">0</div></td>
									</tr>
									<tr>
										<td><div>-</div></td>
										<td><div>-</div></td>
										<td><div>实拨资金</div></td>
										<td><div id="TE_00048">0</div></td>
									</tr>
									<tr>
										<td><div>-</div></td>
										<td><div>-</div></td>
										<td><div>——国库直接支付</div></td>
										<td><div id="TE_00116">0</div></td>
									</tr>
									<tr>
										<td><div>-</div></td>
										<td><div>-</div></td>
										<td><div>预算收入退付流出</div></td>
										<td><div id="TE_00037">0</div></td>
									</tr>
									<tr>
										<td><div>-</div></td>
										<td><div>-</div></td>
										<td><div>其中：手工方式</div></td>
										<td><div id="TE_00049">0</div></td>
									</tr>
									<tr>
										<td><div>-</div></td>
										<td><div>-</div></td>
										<td><div>电子方式</div></td>
										<td><div id="TE_00050">0</div></td>
									</tr>
									<tr>
										<td><div>系统外调拨</div></td>
										<td><div>系统外调拨（转移）收入流入</div></td>
										<td><div id="TE_00006">0</div></td>
										<td><div>系统外调拨（转移）支出流出</div></td>
										<td><div id="TE_00038">0</div></td>
										<td><div id="TE_00105">0</div></td>
									</tr>
									<tr>
										<td><div>国债发行与兑付</div></td>
										<td><div>发行国家债券流入</div></td>
										<td><div id="TE_00007">0</div></td>
										<td><div>兑付国家债券流出</div></td>
										<td><div id="TE_00039">0</div></td>
										<td><div id="TE_00106">0</div></td>
									</tr>
									<tr>
										<td><div>地方政府债券发行与兑付</div></td>
										<td><div>发行地方政府债券流入</div></td>
										<td><div id="TE_00008">0</div></td>
										<td><div>兑付地方政府债券本息流出</div></td>
										<td><div id="TE_00040">0</div></td>
										<td><div id="TE_00107">0</div></td>
									</tr>
									<tr>
										<td><div>国库现金管理</div></td>
										<td><div>国库现金管理到期流入</div></td>
										<td><div id="TE_00009">0</div></td>
										<td><div>国库现金管理操作流出</div></td>
										<td><div id="TE_00041">0</div></td>
										<td><div id="TE_00108">0</div></td>
									</tr>
									<tr>
										<td><div>其他</div></td>
										<td><div>其他流入</div></td>
										<td><div id="TE_00010">0</div></td>
										<td><div>其他流出</div></td>
										<td><div id="TE_00042">0</div></td>
										<td><div id="TE_00109">0</div></td>
									</tr>
									<tr>
										<td><div></div></td>
										<td><div>合  计</div></td>
										<td><div id="NatureBankAllInflowSum">0</div></td>
										<td><div>合  计</div></td>
										<td><div id="NatureBankAllOutflowSum">0</div></td>
										<td><div id="NatureBankAllNetflowSum">0</div></td>
									</tr>
									<tr>
										<td colspan="2"><div>与按资金流向分类的资金流入、流出合计是否一致：</div></td>
										<td><div>核对一致</div></td>
										<td><div></div></td>
										<td><div>核对一致</div></td>
										<td><div>核对一致</div></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					</div>
					
					<div class="l-grid">
					<div class="l-grid-header" style="height:48px;">
						<div class="l-grid-header-inner" style="width: 100%;">
							<table class="l-grid-header-table" cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td colspan="6"><div style="text-align:left;"><span>三、按资金来源分类</span></div></td>
									</tr>
									<tr>
										<td style="width: 200px;"><div><span>系统类别</span></div></td>
										<td style="width: 400px;" colspan="2"><div><span>从商业银行流入国库</span></div></td>
										<td style="width: 400px;" colspan="2"><div><span>从国库流向商业银行</span></div></td>
										<td style="width: 200px;"><div><span>净流入（流出）</span></div></td>
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
										<td style="width:200px"><div>大额支付系统</div></td>
										<td colspan="2" style="width:400px"><div id="TE_00011">0</div></td>
										<td colspan="2" style="width:400px"><div id="TE_00043">0</div></td>
										<td style="width:200px"><div id="TE_00110">0</div></td>
									</tr>
									<tr>
										<td><div>小额支付系统</div></td>
										<td colspan="2"><div id="TE_00012">0</div></td>
										<td colspan="2"><div id="TE_00044">0</div></td>
										<td><div id="TE_00111">0</div></td>
									</tr>
									<tr>
										<td><div>同城票据交换系统</div></td>
										<td colspan="2"><div id="TE_00013">0</div></td>
										<td colspan="2"><div id="TE_00045">0</div></td>
										<td><div id="TE_00112">0</div></td>
									</tr>
									<tr>
										<td><div>其他</div></td>
										<td colspan="2"><div id="TE_00117">0</div></td>
										<td colspan="2"><div id="TE_00118">0</div></td>
										<td><div id="TE_00113">0</div></td>
									</tr>
									<tr>
										<td><div>合  计</div></td>
										<td colspan="2"><div id="SourceBankAllInflowSum">0</div></td>
										<td colspan="2"><div id="SourceBankAllOutflowSum">0</div></td>
										<td><div id="SourceBankAllNetflowSum">0</div></td>
									</tr>
									<tr>
										<td><div>与按资金流向分类的资金流入、流出合计是否一致：</div></td>
										<td colspan="2"><div>核对一致</div></td>
										<td colspan="2"><div>核对一致</div></td>
										<td><div>核对一致</div></td>
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