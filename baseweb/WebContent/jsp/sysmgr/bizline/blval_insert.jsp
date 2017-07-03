<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>务线分类值新增</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
/*body {width:450px;}*/
body {width:400px;margin-top: 10px;margin-bottom: 20px}
.div_bottom {margin-top: 10px}
.table_btn td{padding: 1px 20px 1px 20px}
</style>
</head>
<body>
<n:page action='com.soule.app.sys.bizline.blval.BlvalAction' />

<form id="myform" action="${_CONTEXT_PATH}/Blval!insert.action">
<table class='s1-params'>
	<tr>
		<td><font color="red">*</font>&nbsp;业务线类别</td><td><input type='text' id='bizTypeId' name='bizVal.bizTypeId'  value='${param.bltid}' readonly="readonly"  /></td>
	</tr>
	<tr>
		<td><font color="red">*</font>&nbsp;业务线分类值</td><td><input type='text' id='bizValue' name='bizVal.bizValue' validate='{required:true}'/></td>
	</tr>
	<tr>
		<td><font color="red">*</font>&nbsp;业务线分类名</td><td><input type='text' id='bizName' name='bizVal.bizName' /></td>
	</tr>
	<tr>
		<td>备注</td><td><input type='text' id='remark' name='bizVal.remark' /></td>
	</tr>
</table>
<div align="center" class="div_bottom">
    <table class="table_btn">
        <tr>
           <td><input id="insert" type="button" value="提&nbsp;交" class="l-button"></input></td>
           <td><input id='cancel' type="button" value="取&nbsp;消" class="l-button"> </td>
        </tr>
    </table>
</div>
<!--<table class="s1-button">
	<tr>
		<td>
			<input id='insert' type='button' value='执&nbsp;&nbsp;行' class='l-button'/>
		</td>
		<td>
			<input id='cancel' type='button' value='取&nbsp;消' class='l-button'/>
		</td>
	</tr>
</table>-->
</form>
</body>
<script type='text/javascript'>
$(function () {
	Utils.validateInit();
	$('#cancel').bind('click', close);
	$('#insert').bind('click', execute);

});
function close() {
	 $.dialogBox.close();
}

function execute() {
	if (!$('#myform').valid()){
		return;
	}
	//单记录数据
	var mdata = Utils.convertFormData('insertIn','myform');
	var url = "${_CONTEXT_PATH}/sys/blval!insert.action";
	Utils.ajaxSubmit(url,mdata,function(result){
		$.dialogBox.info(result.retMsg,function(){
			$.dialogBox.opener.queryBlVal();
			$.dialogBox.close();
		});
	});
}

</script>
</html>