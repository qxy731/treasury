<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>业务线类型修改</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
/*body {width:450px;}*/
body {width:400px;margin-top: 10px;margin-bottom: 20px}
.div_bottom {margin-top: 10px}
.table_btn td{padding: 1px 20px 1px 20px}
</style>
</head>
<body>
<n:page action='com.soule.app.sys.bizline.blval.BlvalAction'/>
<form id="myform" action="${_CONTEXT_PATH}/Bltype!modify.action">
<table class='s1-params'>
	<tr>
		<td><font color="red">*</font>&nbsp;业务线类别</td><td><input type='text' id='bizTypeId' name='bizVal.bizTypeId' validate='{required:true}' readonly="readonly"/></td>
	</tr>
	<tr>
		<td><font color="red">*</font>&nbsp;业务线分类值</td><td><input type='text' id='bizValue' name='bizVal.bizValue' validate='{required:true}' readonly="readonly"/></td>
	</tr>
	<tr>
		<td><font color="red">*</font>&nbsp;业务线分类名</td><td><input type='text' id='bizName' name='bizVal.bizName' /></td>
	</tr>
	<tr>
		<td>备注</td><td><input type='text' id='remark' name='bizVal.remark' /></td>
	</tr>
	<tr>
		<td>创建人</td><td><input type='text' id='createUser' name='bizVal.createUser' readonly="readonly"/></td>
	</tr> 
	<tr>
		<td>创建时间</td><td><input type='text' id='createTime' name='bizVal.createTime' readonly="readonly"/></td>
	</tr> 
	<tr>
		<td>最后修改人</td><td><input type='text' id='lastUpdUser' name='bizVal.lastUpdUser' readonly="readonly"/></td>
	</tr>
	<tr>
		<td>最后修改时间</td><td><input type='text' id='lastUpdTime' name='bizVal.lastUpdTime' readonly="readonly"/></td>
	</tr>
</table>
<div align="center" class="div_bottom">
    <table class="table_btn">
        <tr>
           <td><input id="modify" name='modify' type="button" value="提&nbsp;&nbsp;交" class="l-button"></input></td>
           <td><input id='reset' name='reset' type="button" value="取&nbsp;&nbsp;消" class="l-button"> </td>
        </tr>
    </table>
</div>
<!--<table class="s1-button">
	<tr>
		<td>
			<input id='modify' name='modify' type='button' value='执&nbsp;&nbsp;行' class='l-button'/>
		</td>
		<td>
			<input id='reset' name='reset' type='button' value='取&nbsp;&nbsp;消' class='l-button'/>
		</td>
	</tr>
</table>-->
</form>

</body>
<script type='text/javascript'>
$(function () {
	Utils.validateInit();
	var row = $.dialogBox.opener.getSelectedBlval();
	$('#myform input').each(function(i,item){
		if (row[item.id]) {
			$(item).val(row[item.id]);
		}
	});
	
	$('#reset').bind('click', doClear);
	$('#modify').bind('click', execute);

});
function doClear() {
	$.dialogBox.close();
}

function execute() {
	if (!$('#myform').valid()){
		return;
	}
	//单记录数据
	var mdata = Utils.convertFormData('modifyIn','myform');
	var url = "${_CONTEXT_PATH}/sys/blval!modify.action";
	Utils.ajaxSubmit(url,mdata,function(result){
		$.dialogBox.info(result.retMsg,function(){
			$.dialogBox.opener.queryBlVal();
			$.dialogBox.close();
		});
	});
}

</script>
</html>