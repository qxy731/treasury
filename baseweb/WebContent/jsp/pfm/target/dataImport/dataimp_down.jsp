<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>下载模板</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.textarea_css {width: 80%;height: 60px;}
.font_css{color:#FF0000 }
body {
	width: 500px;
}
.button_div{margin-bottom: 10px;}
body {background: url(${_CONTEXT_PATH}/images/${SkinType}/bg_${SkinType}.gif) repeat;}
</style>
</head>
<body>
<form id="myform">
<n:page action='com.soule.crm.pub.dataimport.DataImportAction' />
	<n:enums keys="uploadfile_type,dataimp_result"></n:enums>
<table width="100%">
<tr><td>
<fieldset class="outbox"><legend>下载模板</legend>
<br>
<table class='params'>
	<tr>
		<td align="right">文件类型</td>
		<td>
			<s:select list="#fileTypelist" listKey="key" listValue="value" id="fileType" name="fileType" cssStyle="width: 250px;" emptyOption="false" />
		</td>
		<td align="left">
			<input type="button" id="down" name="down" value="下&nbsp;载" class="l-button" />
		</td>
	</tr>
</table>
</fieldset>
</td></tr>
</table>
</form>
</body>
<script type='text/javascript'>
$(function () {
	Utils.validateInit();
	$('#down').bind('click', downFile);
  	//addShadow();
});

function downFile() {
	var fileType = $("#fileType").val();
	if(fileType==""||fileType==null){
		$.dialogBox.info("下载模板前请先在“文件类型”选择对应的值！");
	    return; 
	}
	var url = '${_CONTEXT_PATH}/upload/imp_'+ fileType +'.xls';
    window.open(url);
}

</script>
</html>