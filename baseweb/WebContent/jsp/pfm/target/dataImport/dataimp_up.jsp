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
</style>
</head>
<body>
<div id="mask" class="l-window-mask"></div>
<form id="myform">
<n:page action='com.soule.crm.pub.dataimport.DataImportAction' />
<table width="100%">
<tr><td>
<fieldset class="outbox"><legend>上传文件</legend>
<table class='params'>
	<tr>
		<td style="width:100px;text-align:right;" ><font color="red">*</font>数据日期</td>
		<td style="width:100px;">
			<input id="businessDate" name="businessDate" type='text' style="width:100px;" class="Wdate" validate="{required:true}"  readonly onClick="WdatePicker({dateFmt:'yyyyMMdd'})"/>
		</td>
		<td><font color="red">说明：季频度文件选择季末日期(如：20170331)；月频度文件选择月末日期(如：20170930)；日频度文件选择具体日期(如：20171008)。</font>
		</td>
	</tr>	
</table>
</fieldset>
</td></tr>
<tr>
	<td>
		<fieldset class="outbox"><legend>选择上传文件</legend>
			<iframe id='fileUploadFrame' src='${_CONTEXT_PATH}/jsp/pfm/target/dataImport/dataimp_upload.jsp' frameborder="0" style="width:100%;height:280px;"></iframe>
		</fieldset>
	</td>
</tr>
<tr>
	<td colspan="2">
		<div style="text-align:center;magin-left:350px;margin-top:5px;">
			<input id="saveBtn" type="button" value="上&nbsp;传" class="l-button"/>
			<input id="cancelBtn" type="button" value="取&nbsp;消" class="l-button"/>
		</div>
	</td>
</tr>
</table>
</form>
</body>
<script type='text/javascript'>
function showMask(){
    $("#mask").css("height",$(document).height());     
    $("#mask").css("width",$(document).width());     
    $("#mask").show();
}  
//隐藏遮罩层  
function hideMask(){     
    $("#mask").hide();     
}  

$(function () {
	Utils.validateInit();
	$("#saveBtn").bind('click', doUploadFile);
	$("#cancelBtn").bind('click', cancelDialog);
});

function doUploadFile(){
	if (!$('#myform').valid()){
		return;
	}
	showMask();
	$("#saveBtn").attr({"disabled":"disabled"});
	var params = {
			businessDate : null //业务日期
	};
	var businessDate = $("#businessDate").val();
	params.businessDate = businessDate;
	var childWindow = $("#fileUploadFrame")[0].contentWindow;
	//文件上传
	$("#fileUploadFrame")[0].contentWindow.doMySubmit(params);
}

function callback(responseText){
	if(responseText.retCode.substr(0,1) == 'I') {
		$.dialogBox.opener.execute();
		$.dialogBox.close();
	}else{
		hideMask();
		$("#saveBtn").removeAttr("disabled");
		$.dialogBox.warn(responseText.retMsg);
	}
}

function cancelDialog() {
	$.dialogBox.close();
}


</script>
</html>