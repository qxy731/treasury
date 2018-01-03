<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>国库预算收入月报表</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.l-grid-row-cell div{
	text-align:left;
}

body{
	width:1250px;
	margin:0 auto;
}
</style>
<script type="text/javascript">
	$(function () {
		Utils.validateInit();
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
			var url = "${_CONTEXT_PATH}/report/bank-funds-flow-report!query5.action";
			var data = $('#myform').serialize();
			Utils.ajaxSubmit(url, data, function(result) {
				$.dialogBox.info(result.retMsg, function() {
					$("#selectDataDate").text("业务日期："+$("#dataDate").val());
					var cntType = $("#cntType").val();
					if(cntType=='0'){
						$("#selectCntType").text("汇表范围：全辖");
					}else if(cntType=='1'){
						$("#selectCntType").text("汇表范围：本级");
					}else if(cntType=='2'){
						$("#selectCntType").text("汇表范围：全辖非本级");
					}
					var dataType = $("#dataType").val();
					if(dataType=='1'){
						$("#selectDataType").text("数据范围：本月发生额");
					}else if(dataType=='2'){
						$("#selectDataType").text("汇表范围：本年累计");
					}
					
					//result.bugetIncomeList
					$("#tbody").empty();
					$.each(result.budgetIncomeList,function(index,obj){
						$("#tbody").append("<tr class='l-grid-row'>");
						$("#tbody").append("  <td style='width:70px;' class='l-grid-row-cell'><div class='l-grid-row-cell-inner l-grid-row-cell-inner-fixedheight'>"+obj.unitId+"</div></td>");
						$("#tbody").append("  <td style='width:160px;'  class='l-grid-row-cell'><div class='l-grid-row-cell-inner l-grid-row-cell-inner-fixedheight'>"+obj.unitName+"</div></td>");
						$("#tbody").append("  <td style='width:70px;'  class='l-grid-row-cell'><div class='l-grid-row-cell-inner l-grid-row-cell-inner-fixedheight'>"+obj.subCode+"</div></td>");
						$("#tbody").append("  <td style='width:200px;'  class='l-grid-row-cell'><div class='l-grid-row-cell-inner l-grid-row-cell-inner-fixedheight'>"+obj.subName+"</div></td>");
						$("#tbody").append("  <td style='width:150px;'  class='l-grid-row-cell'><div class='l-grid-row-cell-inner l-grid-row-cell-inner-fixedheight'>"+obj.unitLevel1+"</div></td>");
						$("#tbody").append("  <td style='width:150px;'  class='l-grid-row-cell'><div class='l-grid-row-cell-inner l-grid-row-cell-inner-fixedheight'>"+obj.unitLevel2+"</div></td>");
						$("#tbody").append("  <td style='width:150px;'  class='l-grid-row-cell'><div class='l-grid-row-cell-inner l-grid-row-cell-inner-fixedheight'>"+obj.unitLevel3+"</div></td>");
						$("#tbody").append("  <td style='width:150px;'  class='l-grid-row-cell'><div class='l-grid-row-cell-inner l-grid-row-cell-inner-fixedheight'>"+obj.unitLevel4+"</div></td>");
						$("#tbody").append("  <td style='width:150px;'  class='l-grid-row-cell'><div class='l-grid-row-cell-inner l-grid-row-cell-inner-fixedheight'>"+obj.unitLevel5+"</div></td>");
						$("#tbody").append("</tr>");
					});
				});
			});
		}
	}
	function doClear() {
		if(validate()){
			var dataDate =   $('#dataDate').val();
			var subCode = $('#subCode').val();
			var cntType = $("#cntType").val();
			var dataType = $("#dataType").val();
			location.href="${_CONTEXT_PATH}/report/bank-funds-flow-report!export5.action?dataDate="+dataDate+"&subCode="+subCode+"&cntType="+cntType+"&dataType="+dataType;  
		}
	}
</script>
</head>
<body>
<n:page action='com.soule.app.pfm.tm.report.bankfundsflow.BankFundsFlowReportAction' />
<n:enums keys='cnt_type,data_type'/>
<fieldset class="queryBox"><legend>查询条件</legend>
	<form id="myform">
	<table class='params'>
		<tr>
			<td>数据日期</td>
			<td><input type='text' id='dataDate' name='queryIn.dataDate' validate="{required:true}" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM'})"/></td>
			<td>预算科目代码</td>
			<td><input type='text' id='subCode' name='queryIn.subCode' validate="{required:true}"/></td>
			<td>汇表范围</td>
			<td>
			 <n:select codetype="cnt_type" id="cntType" name='queryIn.cntType' emptyOption="false" validate="{required:true}"/>
			</td>
			<td>数据范围</td>
			<td >
			 <n:select codetype="data_type" id="dataType" name='queryIn.dataType' emptyOption="false" validate="{required:true}"/>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="8">
				<input id='reset' name='reset' type='button' value='导&nbsp;出' class='l-button'  style="float:right;margin-right:10px;"/>
				<input id='query' name='query' type='button' value='查&nbsp;询' class='l-button' style="float:right;margin-right:10px;"/>
			</td>
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
										<td colspan="9"><div><span style="font-size:14pt;">国库预算收入月报表</span></div></td>
									</tr>
									<tr>
										<td colspan="2"><div style="text-align:left;"><span id="selectDataDate">业务期间：</span></div></td>
										<td colspan="2"><div style="text-align:left;"><span id="selectCntType">汇表范围：</span></div></td>
										<td colspan="2"><div style="text-align:left;"><span id="selectDataType">数据范围：</span></div></td>
										<td colspan="3"><div style="text-align:right;padding-right:5px;"><span>单位：万元</span></div></td>
									</tr>
									<tr>
										<td style="width: 70px;"><div><span>国库代码</span></div></td>
										<td style="width: 160px;"><div><span>国库名称</span></div></td>
										<td style="width: 70px;"><div><span>科目代码</span></div></td>
										<td style="width: 200px;"><div><span>科目名称</span></div></td>
										<td style="width: 150px;"><div><span>中央</span></div></td>
										<td style="width: 150px;"><div><span>省</span></div></td>
										<td style="width: 150px;"><div><span>地市</span></div></td>
										<td style="width: 150px;"><div><span>区（县）</span></div></td>
										<td style="width: 150px;"><div><span>乡镇</span></div></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="l-grid-body">
						<div class="l-grid-body-inner" style="width: 100%;">
							<table class="l-grid-body-table" cellpadding="0" cellspacing="0">
								<tbody id="tbody">
																
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