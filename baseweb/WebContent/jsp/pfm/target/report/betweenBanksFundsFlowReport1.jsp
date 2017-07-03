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
.params td {
	background-color: #E2EAFF;
	padding: 2px 2px 2px 10px;
}

body {
	width:100%;
	margin: 5px;
}
</style>
<script type="text/javascript">
	$(function () {
		$("#dataDate").ligerDateEditor();
		$("#reportlist1").ligerGrid({
			columns: [
						{ display: '一、按资金流向分类', columns:[
							{ display: '机构类别', name: 'custOrgName', width: '20%', align: 'left' },
							{ display: '从商业银行流入国库', name: 'bankAllInflow', width: '20%', align: 'left' },
							{ display: '其中：来自专户', name: 'bankSpecialInflow', width: '15%', align: 'left' },
							{ display: '从国库流向商业银行', name: 'bankAllOutflow', width: '20%', align: 'left' },
							{ display: '其中：流向专户', name: 'bankSpecialOutflow', width: '20%',align: 'left' },
							{ display: '净流入（流出）', name: 'bankAllNetFlow', width: '20%',align: 'left' }
							]
						}
					],
			height:400,
			width:'100%',
			onError: function() {
				Utils.alert("查询数据失败");
			}
		});
		$("#reportlist2").ligerGrid({
			columns: [
						{ display: '二、按资金性质分类', columns:[
							{ display: '业务类型', name: 'businessTypeName', width: '20%', align: 'left' },
							{ display: '从商业银行流入国库', columns:[
								{ display: '', name: 'bankAllInflowName', width: '20%', align: 'left' },
								{ display: '', name: 'bankAllInflow', width: '15%', align: 'left' }
							                  ]
							},
							{ display: '从国库流向商业银行', columns:[
 								{ display: '', name: 'bankAllOutflowName', width: '20%', align: 'left' },
 								{ display: '', name: 'bankAllOutflow', width: '15%', align: 'left' }
 							                  ]
							},
							{ display: '净流入（流出）', name: 'bankAllNetFlow', width: '20%',align: 'left' }
							]
						}
					],
			height:400,
			width:'100%',
			onError: function() {
				Utils.alert("查询数据失败");
			}
		});
		$("#reportlist3").ligerGrid({
			columns: [
						{ display: '三、按资金来源分类', columns:[
							{ display: '系统类别', name: 'systemTypeName', width: '20%', align: 'left' },
							{ display: '从商业银行流入国库', name: 'bankAllInflow', width: '20%', align: 'left' },
							{ display: '从国库流向商业银行', name: 'bankAllOutflow', width: '20%', align: 'left' },
							{ display: '净流入（流出）', name: 'bankAllNetFlow', width: '20%',align: 'left' }
							]
						}
					],
			height:400,
			width:'100%',
			onError: function() {
				Utils.alert("查询数据失败");
			}
		});
		$("#query").bind('click', query);
		$("#reset").bind('click', doClear);

	});
	
	function query() {
		
		var gridManager1 = $("#reportlist1").ligerGetGridManager();
		var gridManager2 = $("#reportlist2").ligerGetGridManager();
		var gridManager3 = $("#reportlist3").ligerGetGridManager(); 
		var url = "${_CONTEXT_PATH}/report/bank-funds-flow-report!query.action";
		var data = $('#myform').serialize();
		Utils.ajaxSubmit(url, data, function(result) {
			$.dialogBox.info(result.retMsg, function() {
				gridManager1.set({ data: result.bankFundsFlowList });
				gridManager2.set({ data: result.treasuryFundsNatureList });
				gridManager3.set({ data: result.treasuryFundsSourceList });
			});
		});
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
<table class="content" cellpadding="5">
<tr><td>
	<fieldset class="queryBox"><legend>查询条件</legend>
	<form id="myform">
	<table class='params'>
		<tr>
			<td>数据日期</td>
			<td><input type='text' id='dataDate' name='queryIn.dataDate'/></td>
			<td>所属部门</td>
			<td><input type='hidden' id='unitId' name='queryIn.unitId' /><input id="queryIn.unitName" type='text' name="unitName" readonly="readonly" onclick="openSelectUnit()" class="unit_select"/></td>
		</tr>
		<tr><td colspan="4">
				<table width="100%"><tr>
					<td align="right"><input id='query' name='query' type='button' value='查&nbsp;询' class='l-button'/></td>
					<td width="3%"></td>
					<td width="3%"></td>
					<td align="left"><input id='reset' name='reset' type='button' value='重&nbsp;置' class='l-button'/></td>
				</tr></table>
			</td>
		</tr>
	</table>
	</form>
	</fieldset>
	</td>
</tr>
<tr><td style="text-align:center;">与商业银行之间资金流动情况统计表</td></tr>
<tr><td>
	<table><tr>
	<td>填报单位：</td><td>业务期间：</td><td style="text-align:right;">单位：万元</td>
	</tr></table>
</td></tr>
<tr><td><div id='reportlist1'></div></td></tr>
<tr><td><div id='reportlist2'></div></td></tr>
<tr><td><div id='reportlist3'></div></td></tr>
</table>
</body>
</html>