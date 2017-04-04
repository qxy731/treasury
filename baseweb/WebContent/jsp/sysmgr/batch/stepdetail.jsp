<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批处理节点明细</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
html,body {background: url(${_CONTEXT_PATH}/images/${SkinType}/bg_${SkinType}.gif) repeat;}
body{ width: 650px;}
#stepDesc,#procClass { width:470px;}
.div_bottom {margin-top: 10px}
.table_btn td{padding: 1px 20px 1px 20px}
.disabled {background-color: #eeeeee;}
</style>
</head>
<body>
<n:page action='com.soule.app.sys.batch.BatchDetailAction' />
<n:enums keys='batstep_type,batch_node_type' />
<form id="myform" action="${_CONTEXT_PATH}/batch!insert.action">
<table class='s1-params' >
	<tr>
		<td><font color="red">*</font>&nbsp;批处理编号</td>
		<td id='parentSelect'><input name="batchId" id="batchId" validate='{required:true}' value='${step.batchId}'></input>
		</td>
		<td class='label'><font color="red">*</font>&nbsp;父节点编号</td>
		<td><input type='text' id='parentId' name='parentId' validate='{required:true,digits:true}' value='${step.parentId}'/></td>
	</tr>
	<tr>
		<td class='label'><font color="red">*</font>&nbsp;步骤名称</td>
		<td><input type='text' id='stepName' name='stepName' validate='{required:true}' value='${step.stepName}'/></td>
		<td class='label'><font color="red">*</font>&nbsp;步骤编号</td>
		<td><input type='text' id='stepId' name='stepId' validate='{required:true,digits:true}' value='${step.stepId}'/></td>
	</tr>
	<tr>
		<td class='label'><font color="red">*</font>&nbsp;执行频率</td>
		<td><n:select codetype="batstep_type" id="procFreq" name='procFreq' validate='{required:true}' value='step.procFreq'></n:select></td>
		<td class='label'>依赖步骤编号</td>
		<td><input type='text' id='dependIds' name='dependIds' value='${step.dependIds}'/></td>
	</tr>
	<tr>
		<td class='label'>失败跳转步骤</td>
		<td><input type='text' id='skipStepId' name='skipStepId' validate='{digits:true}' value='${step.skipStepId}'/></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td class='label'>步骤描述</td>
		<td colspan="3"><textarea type='text' id='stepDesc' name='stepDesc' value='${step.stepDesc}'></textarea></td>
	</tr>
	<tr>
		<td class='label'><font color="red">*</font>&nbsp;节点类型</td>
		<td><n:select id='stepType' name='stepType' validate='{required:true}' value='step.stepType' codetype="batch_node_type" /></td>
		<td class='label'>步骤顺序号</td>
		<td><input type='text' id='stepNo' name='stepNo' value='${step.stepNo}'></input></td>
	</tr>
	<tr>
		<td class='label'><font color="red">*</font>&nbsp;执行类名</td>
		<td colspan="3"><n:select name="procClass" id="procClass" list='batchProcesses' listKey="value" listValue="desc" validate='{required:true}' value='step.procClass'></n:select>
		</td>
	</tr>
	<tr>
		<td class='label'>并发数</td>
		<td><input type='text' id='compCount' name='compCount' value='1' validate='{digits:true}' value='${step.compCount}'/></td>
		<td class='label'>执行时间参考值</td>
		<td><input type='text' id='refTime' name='refTime' value='${step.refTime}'/></td>
	</tr>
	<tr>
		<td class='label'>参数1</td>
		<td><input type='text' id='param1' name='param1' value='${step.param1}'/></td>
		<td class='label'>参数2</td>
		<td><input type='text' id='param2' name='param2' value='${step.param2}'/></td>
	</tr>
	<tr>
		<td class='label'>参数3</td>
		<td><input type='text' id='param3' name='param3' value='${step.param3}'/></td>
		<td class='label'>参数4</td>
		<td><input type='text' id='param4' name='param4' value='${step.param4}'/></td>
	</tr>
	<tr>
		<td class='label'>参数5</td>
		<td><input type='text' id='param5' name='param5' value='${step.param5}'/></td>
		<td class='label'>EXT1</td>
		<td><input type='text' id='ext1' name='ext1' value='${step.ext1}'/></td>
	</tr>
	<tr>
		<td class='label'>EXT2</td>
		<td><input type='text' id='ext2' name='ext2' value='${step.ext2}'/></td>
		<td class='label'>EXT3</td>
		<td><input type='text' id='ext3' name='ext3' value='${step.ext3}'/></td>
	</tr>
</table>
</form>

</body>
<script type='text/javascript'>
	var type = '${param.type}';
	$( function() {
		Utils.validateInit();
		$('#batchId').val('${param.bid}');
		$('#batchId').attr('readonly','readonly');
		$('#batchId').addClass('disabled');
		$('#parentId').addClass('disabled');
		$('#parentId').attr('readonly','readonly');
		if (type == "update") {
			$('#stepId').attr('readonly','readonly');
			var x = $.dialogBox.opener.modifyData;
			for (a in x) {
				$('#' + a).val(x[a]);
			}
			$('#stepDesc').html(x.stepDesc);
		}
		else {
			$('#parentId').val('${param.pid}');
		}
	});
	function validate() {
		return $("#myform").valid();
	}
	function execute() {
		if (type == 'insert'){
			//单记录数据
			if (validate()) {
				var mdata = Utils.convertFormData('insertIn.step', 'myform');
				var url = "${_CONTEXT_PATH}/sys/batch!insert.action";
				Utils.ajaxSubmit(url,mdata, function(result) {
					$.dialogBox.info(result.retMsg,function() {
						var node = Utils.convertFormData('', 'myform');
						$.dialogBox.opener.view.insertNode(node);
						$.dialogBox.close();
					});
				});
			}
		}
		if (type == 'update') {
			//单记录数据
			if (validate()) {
				var mdata = Utils.convertFormData('updateIn.step', 'myform');
				var url = "${_CONTEXT_PATH}/sys/batch!update.action";
				Utils.ajaxSubmit(url,mdata, function(result) {
					$.dialogBox.info(result.retMsg,function() {
						var node = Utils.convertFormData('', 'myform');
						$.dialogBox.opener.view.modifyNode(node);
						$.dialogBox.close();
					});
				});
			}
		}
	}
</script>
</html>