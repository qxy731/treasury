<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批处理维护</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
#edit,#monitor {margin:10px;width:80px;}
#choice1,#choice2 {width:10px;}
.disabled {background-color: #eeeeee;}
body {overflow:hidden;}
</style>
</head>
<body>
<n:page action='com.soule.app.sys.batch.BatchAction' />
<n:enums keys='batstep_type' />
<form id="myform">
<fieldset style="margin:20px auto;width:500px;">
<legend>选择批处理</legend>
<table class='params'>
	<tr>
		<td>批处理编号 </td>
		<td><input id="choice1" type="radio" value="0" name="choice" checked="checked">存在</input></td>
		<td><n:select name="batchId" id="batchId" list='ids' listKey="batchId" listValue="batchId" validate='{required:true}' value='step.batchId'></n:select></td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td><input id="choice2" type="radio" value="1" name="choice">新建</input></td>
		<td><input type="text" id='newbatchid' class='disabled' disabled='disabled'></input></td>
		<td></td>
	</tr>
	<tr>
		<td colspan='4'>
			<div align="center" class="button_div">
				<table width="100%">
						<tr>
							<td></td>
							<td align="right"><input id='edit' type="button" value="编&nbsp;&nbsp;辑" class="l-button"/></td>
							<td width="5%"></td>
							<td width="5%"></td>
							<td  align="left"><input id='monitor' type='button' value='监&nbsp;&nbsp;控' class='l-button'/></td>
							<td></td>
						</tr>
				</table>
			</div>
		</td>
	</tr>
</table>
</fieldset>

</form>

</body>
<script type='text/javascript'>
	var type = '${param.type}';
	$( function() {
		Utils.validateInit();
		$('#edit').bind('click', editBatch);
		$('#monitor').bind('click', monitorBatch);
		$('#choice1').bind('click',changeType1);
		$('#choice2').bind('click',changeType2);
	});
	function changeType1(e) {
		if ($(e.target).attr('checked')) {
			$('#newbatchid').addClass('disabled').attr('disabled','disabled');
			$('#batchId').removeClass('disabled').removeAttr('disabled');
			$('#monitor').addClass('l-button').removeAttr('disabled');
		}
	};
	function changeType2(e) {
		if ($(e.target).attr('checked')) {
			$('#batchId').addClass('disabled').attr('disabled','disabled');
			$('#newbatchid').removeClass('disabled').removeAttr('disabled');
			$('#monitor').removeClass('l-button').attr('disabled','disabled');
		}
	};
	function editBatch() {
		var bid = '';
		var type = '';
		if ($('#choice1').attr('checked')) {
			bid = $('#batchId').val();
			type = 'old';
		}
		else {
			type = 'new';
			bid = $('#newbatchid').val();
			if (!bid) {
				$.dialogBox.alert('新建时，必须输入新ID');
				return;
			}
		}
		Utils.openTab('batchedit' + bid,'批处理' + bid,'${_CONTEXT_PATH}/jsp/sysmgr/batch/batchedit.jsp?type='+type+'&bid=' + bid);
	};
	function monitorBatch() {
		if ($('#choice1').attr('checked')) {
			var url = '${_CONTEXT_PATH}/jsp/sysmgr/batch/choiceinst.jsp?bid=' + $('#batchId').val();
			$.dialogBox.openDialog(url, {id : 'choiceinst',height : '180px',width : '380px',lock : true,opacity : 0.07,title : '选择监控实例ID'}, toMonitorPage,true);
		}
	};
	function toMonitorPage(p) {
		var instid = p.getInstId();
		var bid = $('#batchId').val();
		if (instid) {
			Utils.openTab('batchmonitor' + bid,'监控' + bid,'${_CONTEXT_PATH}/jsp/sysmgr/batch/batchmonitor.jsp?bid=' + bid +'&iid=' +instid);
		}
		else {
			return false;
		}
	};
	
</script>
</html>