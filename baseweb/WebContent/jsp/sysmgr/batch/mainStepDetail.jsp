<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人员</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
body{
    width: 650px;
    margin-bottom: 20px
   /* margin-right: 60px*/
}
#insert_btn {
	width:22px;height:22px;
}
#stepDesc,#procClass { width:50%;}
.div_bottom {margin-top: 10px}
.table_btn td{padding: 1px 20px 1px 20px}
</style>
</head>
<body>
<n:page action='com.soule.app.sys.batch.BatchDetailAction' />
<n:enums keys='batstep_type' />
<form id="myform" action="${_CONTEXT_PATH}/Janel!insert.action">
<table class='s1-params' >
	<tr>
		<td><font color="red">*</font>&nbsp;批处理编号</td>
		<td id='parentSelect'><n:select name="step.batchId" id="batchId"
			list='ids' listKey="batchId" listValue="batchId" validate='{required:true}' value='step.batchId'></n:select>
			<input type="button" value='新' id='insert_btn'></input>
		</td>
		<td class='label'><font color="red">*</font>&nbsp;步骤编号</td>
		<td><input type='text' id='stepId' name='step.stepId' validate='{required:true,digits:true}' value='${step.stepId}'/></td>
	</tr>
	<tr>
		<td class='label'><font color="red">*</font>&nbsp;步骤名称</td>
		<td><input type='text' id='stepName' name='step.stepName' validate='{required:true}' value='${step.stepName}'/></td>
		<td class='label'>跳转步骤编号</td>
		<td><input type='text' id='skipStepId' name='step.skipStepId' validate='{digits:true}' value='${step.skipStepId}'/></td>
		
	</tr>
	<tr>
		<td class='label'><font color="red">*</font>&nbsp;执行频率</td>
		<td><n:select codetype="batstep_type" id="procFreq" name='step.procFreq' validate='{required:true}' value='step.procFreq'></n:select></td>
		<td class='label'>依赖步骤编号</td>
		<td><input type='text' id='dependIds' name='step.dependIds' value='${step.dependIds}'/></td>
	</tr>
	<tr>
		<td class='label'>步骤描述</td>
		<td colspan="3"><input type='text' id='stepDesc' name='step.stepDesc' value='${step.stepDesc}'/></td>
	</tr>
	<tr>
		<td class='label'><font color="red">*</font>&nbsp;执行类名</td>
		<td colspan="3"><input type='text' id='procClass' name='step.procClass' validate='{required:true}' value='${step.procClass}'/></td>
	</tr>
	<tr>
		<td class='label'>并发数</td>
		<td><input type='text' id='compCount' name='step.compCount' value='1' validate='{digits:true}' value='${step.compCount}'/></td>
		<td class='label'>执行时间参考值</td>
		<td><input type='text' id='refTime' name='step.refTime' value='${step.refTime}'/></td>
	</tr>
	<tr>
		<td class='label'>参数1</td>
		<td><input type='text' id='param1' name='step.param1' value='${step.param1}'/></td>
		<td class='label'>参数2</td>
		<td><input type='text' id='param2' name='step.param2' value='${step.param2}'/></td>
	</tr>
	<tr>
		<td class='label'>参数3</td>
		<td><input type='text' id='param3' name='step.param3' value='${step.param3}'/></td>
		<td class='label'>参数4</td>
		<td><input type='text' id='param4' name='step.param4' value='${step.param4}'/></td>
	</tr>
	<tr>
		<td class='label'>参数5</td>
		<td><input type='text' id='param5' name='step.param5' value='${step.param5}'/></td>
		<td class='label'>EXT1</td>
		<td><input type='text' id='ext1' name='step.ext1' value='${step.ext1}'/></td>
	</tr>
	<tr>
		<td class='label'>EXT2</td>
		<td><input type='text' id='ext2' name='step.ext2' value='${step.ext2}'/></td>
		<td class='label'>EXT3</td>
		<td><input type='text' id='ext3' name='step.ext3' value='${step.ext3}'/></td>
	</tr>
	
</table>
<div align="center" class="div_bottom">
    <table class="table_btn">
        <tr>
           <td><input id="commit" type="button" value="提&nbsp;交" class="l-button"></input></td>
           <td><input id="cancel" type="button" value="取&nbsp;消" class="l-button"> </td>
        </tr>
    </table>
</div>
<!--<table class="s1-button">
	<tr>
		<td>
			<input id="commit" type="button" value="提交" class="l-button"></input>
		</td>
		<td>
			<input id="cancel" type="button" value="取消" class="l-button"></input>
		</td>
	</tr>
</table>-->
</form>

</body>
<script type='text/javascript'>
	var type = '${param.type}';
	$( function() {
		Utils.validateInit();
		$('#cancel').bind('click', cancelDialog);
		$('#commit').bind('click', execute);
		$('#insert_btn').bind('click', newBatchId);
		if (type=='update') {
			$('#batchId').attr('disabled','true');
			$('#stepId').attr('disabled','true');
			$('#insert_btn').attr('visible','false');
		}
	});
	function cancelDialog(){
		//Utils.removeTabById('StepDetail');
		$.dialogBox.close();
	}
	function validate() {
		return $("#myform").valid();
	}
	function newBatchId() {
		var url = "${_CONTEXT_PATH}/jsp/sysmgr/batch/imptext.jsp";
		$.dialogBox.openDialog(url,"新的批处理类型",function(){
			var nb = this.iframe.contentWindow.getInput();
			$('#batchId').append('<option value="'+nb+'" selected>'+nb+'</option>');
		});
	}
	function execute() {

		if (type == 'insert'){
			//单记录数据
			if (validate()) {
				var mdata = Utils.convertFormData('insertIn', 'myform');
				var url = "${_CONTEXT_PATH}/sys/batch!insert.action";
				Utils.ajaxSubmit(url,mdata, function(result) {
					$.dialogBox.info(result.retMsg,function() {
						cancelDialog();
					});
				});
			}
		}
		if (type == 'update') {
			//单记录数据
			if (validate()) {
				var mdata = Utils.convertFormData('updateIn', 'myform');
				var url = "${_CONTEXT_PATH}/sys/batch!update.action";
				Utils.ajaxSubmit(url,mdata, function(result) {
					$.dialogBox.info(result.retMsg,function() {
						cancelDialog();
					});
				});
			}
		}
	}
</script>
</html>