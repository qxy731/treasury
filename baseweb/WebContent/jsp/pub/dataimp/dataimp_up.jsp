<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.textarea_css {width: 80%;height: 60px;}
.font_css{color:#FF0000 }
body {
	width: 400px;
}
.button_div{margin-bottom: 10px;}
body {background: url(${_CONTEXT_PATH}/images/${SkinType}/bg_${SkinType}.gif) repeat;}
</style>
</head>
<body>
<form id="myform">
<n:page action='com.soule.crm.pub.dataimport.DataImportAction' />
	<n:enums keys="uploadfile_type,dataimp_result"></n:enums>
<table class='content'>
<tr><td>

<fieldset class="outbox"><legend>上传文件</legend>
<br>
<table class='params'>
	<tr>
		<td align="right"><font color="red">*</font>&nbsp;数据日期</td>
		<td>
			<input name="monthId" type='text' id="monthId" class="Wdate" validate="{required:true}" />
		</td>
	</tr>
	<tr>
		<td align="right"><font color="red">*</font>&nbsp;文件类型</td>
		<td>
			<s:select list="#fileTypelist" listKey="key" listValue="value" id="fileType" name="fileType" emptyOption="false" validate="{required:true}" />
		</td>
	</tr>
	<tr>
		<td align="center" colspan="2">
			<input type="button" id="up" name="up" value="上&nbsp;&nbsp;传" class="l-button" />
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
	$("#monthId").ligerDateEditor();
	$("#monthId").bind('click', function() {
		$(this).parent().next('.l-box-dateeditor').attr('style', 'display:block');
	});
	
	$('#up').bind('click', upFile);
	
	$("#monthId").ligerDateEditor();
	
  	addShadow();
});

function upFile() {
	/* var fileType = $("#fileType").val();
	if(fileType==""||fileType==null){
		$.dialogBox.info("请选择需要上传的文件类型！");
	    return; 
	} */
	if (!$('#myform').valid()){
		return;
	}

	Utils.uploadFile(doUploadFile,null,'xls','false','false',1);
}

function doUploadFile(uploadId,files){
	var fileType = $("#fileType").val();
	var url = "${_CONTEXT_PATH}/pub/data-import!uploadFile.action";
	var monthId = $('#monthId').val();
// 	$.ajax({
// 		url: url,
// 		type: 'POST',
// 		data: {"uploadId":uploadId,"monthId":monthId,"uploadFileType":fileType},
// 		dataType: 'json',
// 		async: false,
// 		success: function(result) {
// 			$.dialogBox.confirm('文件上传成功,是否确认导入?',function(){
// 				callProcedureFile(uploadId,fileType);
// 				$.dialogBox.opener.execute();
// 			});
// 		},
// 		error: function(result) {
// 			var url = "${_CONTEXT_PATH}/jsp/pub/dataimp/dataimp_errorlist.jsp";
// 			errordata = result.rows;
// 			if (result.retMsg) {
// 				$.dialogBox.error(result.retMsg);
// 			}
// 			else {
// 				$.dialogBox.openDialog(url,{title:"错误信息",width:'700px',height:'500px'},true);
// 			}
// 		}
// 	});

	Utils.ajaxSubmit(url,{"uploadId":uploadId,"monthId":monthId,"uploadFileType":fileType}, function(result){
		$.dialogBox.confirm('文件上传成功,是否确认导入?',function(){
			callProcedureFile(uploadId,fileType);
			$.dialogBox.opener.execute();
		},true);
	}, function(result) {
		var url = "${_CONTEXT_PATH}/jsp/pub/dataimp/dataimp_errorlist.jsp";
		errordata = result.rows;
		if (result.retMsg) {
			Utils.toIndex(result);
		}
		else {
			$.dialogBox.openDialog(url,{title:"错误信息",width:'700px',height:'500px'},true);
		}
	});
}

var errordata = [];
function callProcedureFile(uploadId,fileType){
	var z = $("#monthId").val().split("-");
	var monthId = z[0]+z[1]+z[2];
	var url = "${_CONTEXT_PATH}/pub/data-import!callUploadData.action";
// 	$.ajax({
// 		url: url,
// 		type: 'POST',
// 		data: {"uploadId":uploadId,"uploadFileType":fileType,"monthId":monthId},
// 		dataType: 'json',
// 		async: false,
// 		success: function(result) {
// 			$.dialogBox.close();
// 		},
// 		error: function(result) {
// 		}
// 	});

	Utils.ajaxSubmit(url,{"uploadId":uploadId,"uploadFileType":fileType,"monthId":monthId},function(){
		$.dialogBox.close();
	},function(){});
}

function doComAndUncomAndDel(uploadId,fileId,type,url){
	var data = {'queryIn.uploadId' : uploadId,'queryIn.fileId' : fileId};
	$.dialogBox.confirm('您确定'+type+'吗？',function () {
			Utils.ajaxSubmit(url, data, function(result) {
				$.dialogBox.info(type+'成功',onSucc); 
			});
	},true);
}

function onSucc() {
	$.dialogBox.opener.execute();
	$.dialogBox.close();
}

</script>
</html>