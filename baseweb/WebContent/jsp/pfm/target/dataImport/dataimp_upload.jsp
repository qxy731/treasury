<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>上传文件</title>
<script type="text/javascript" src="${_CONTEXT_PATH}/jwebui/jquery/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="${_CONTEXT_PATH}/jwebui/jquery/jquery.form.js"></script>
<style type="text/css">
html,body {margin:0;padding:0;width:100%;height:100%;font-size:12px;font-family:"新宋体";overflow:hidden;}
.l-panel {border: 1px solid #99BBE8;position: relative;text-align: left;}
.l-panel table{ width:auto;margin:0; padding:0;border-spacing:0 0;}
.l-panel input,select{height:22px;line-height:22px;}
.l-panel-body {position:relative; overflow:hidden; width:100%;}
.l-grid {position:relative;text-align:left;}
.l-grid-header {
	border-bottom: 1px solid #A3C0E8;height: 22px;line-height: 22px;overflow: hidden;width: 100%;
	background: #E2F0FF url('${_CONTEXT_PATH}/jwebui/skins/Aqua/images/grid/header-bg.gif') repeat-x left bottom;
}
.l-grid-body {position: relative;width:100%;top: 0px;left: 0px;overflow-y:auto;overflow-x:hidden;}
.l-button { 
	height:22px;overflow:hidden;width:70px;float:right;line-height:22px;cursor:pointer;
	position: relative;text-align:center;border: solid 1px #A3C0E8;color:#333333;border-radius: 3px;
	background:#E0EDFF url('${_CONTEXT_PATH}/jwebui/skins/Aqua/images/controls/button-bg.gif') repeat-x center;
}
.l-button:hover {
	background: url('${_CONTEXT_PATH}/images/button.png') repeat scroll 0px -26px transparent;
	box-shadow: none;color: #fff;
}
.l-grid-header-table{}
.l-grid-header-table tr td{
	padding:0; margin:0;overflow:hidden; 
    border-right:1px solid #A3C0E8;
    border-bottom:1px solid #A3C0E8;
    text-align:center;
}
.l-grid-header-table tr td div{text-align:center;}
.l-grid-header-table tr td div span{text-align:center;cursor:pointer;overflow:hidden;}
.l-grid-body-table{}
.l-grid-body-table tr td{overflow:hidden;border-right:1px solid #A3C0E8;border-bottom:1px solid #A3C0E8;text-align:left;}
.l-grid-body-table tr td div {text-align:left;line-height:22px;min-height:22px;_height:22px;overflow:hidden;}
.delarea{
	width:18px;height:18px;float:left;cursor:pointer;margin-left:5px;
	background:#fff url('${_CONTEXT_PATH}/images/delete.gif') no-repeat right center;
}
</style>
</head>
<n:page action='com.soule.app.sys.fileupdown.FileUploadDownAction' />
<n:enums keys="uploadfile_type,import_type"></n:enums>
<body>
<form id='fileform' action='' method='post' enctype='multipart/form-data'>
<div id="filelist" class="l-panel" style="width:848px;">
	<div class="l-panel-body">
		<div class="l-grid" style="height:250px;">
			<div class="l-grid-header">
				<div class="l-grid-header-inner">
					<table class="l-grid-header-table" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td style="width:320px;"><div><span>文件名称</span></div></td>
								<td style="width:80px;"><div><span>文件大小(KB)</span></div></td>
								<td style="width:320px;"><div><span>文件类型</span></div></td>
								<td style="width:60px;"><div><span>处理模式</span></div></td>
								<td style="width:30px;"><div><span>删除</span></div></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="l-grid-body" style="height:228px;">
				<div class="l-grid-body-inner" style="width:848px;">
					<table class="l-grid-body-table" cellpadding="0" cellspacing="0">
						<tbody id="displayFiles">
							<tr>
								<td style='width:320px;'>
									<input type='hidden' name='myFileName' style='width:0;'></input>							
									<input type='file' name='myFile' onchange='uploadFile5(this)' style='display:none;'></input>
									<input type='button' class='l-button' value='选择文件' onclick='$(this).prev().click();'></input>
								</td>
								<td style='width:80px;'></td>
								<td style='width:320px;'>									
									<n:select codetype='uploadfile_type' name='myFileType' cssStyle='width:320px;' emptyOption='false' headerKey="" headerValue="==请选择=="/>
								</td>
								<td style='width:60px;'>
									<select name='myImportType' style='width:60px'><option value='1' selected>覆盖</option><option value='2'>追加</option></select>
								</td>
								<td style='width:30px;'>
									<span class='delarea' onclick='deleteFile(this)'></span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<input type='hidden' id='filter' name='filter' value="csv,xls,xlsx"></input>
<input type='hidden' id='fileMax' name='fileMax' value="15"></input>
<input type='hidden' id='fileSizeMax' name='fileSizeMax' value="1024*1024*5"></input>
<input type='hidden' id="businessDate" name="businessDate" />
<input type='button' id="fileBtn" name='fileBtn' class='l-button' style="float:left;" value='添加行' onClick='addFileComponent5()'></input>
</form>
<div id='msg' style="padding:5px;"></div>
</body>
<script type='text/javascript'>
var filter = "*";
var fileMax = 1;
var fileSizeMax = 1024*1024;//默认1M

$(function () {
	filter = $("#filter").val();
	fileMax =  $("#fileMax").val();
	fileSizeMax = $("#fileSizeMax").val();
	setTipMessage();
	/* $(".l-grid-body").scroll(function() {
		$(".l-grid-header").scrollLeft = $(".l-grid-body").scrollLeft;
	}); */
});

function getUploadFileSize(){
	return $('input[type=file]').toArray().length;
}

function checkFileNumber(){
	var len = getUploadFileSize();
	return len < fileMax;
}

function setTipMessage(){
	if (!checkFileNumber()){
		$('#fileBtn').hide();
		$('#msg').html("已达文件最大数["+fileMax+"]!");
	}else{
		$('#fileBtn').show();
		$('#msg').html('&nbsp;文件大小<5M,允许文件类型['+filter+'],最多可上传文件数[' + fileMax+']');
	}
}

//验证文件后缀是否合法
function checkFileSuffix(obj) {
	if (filter != '*') {
		var fileName = $(obj).val();
		var i = fileName.lastIndexOf('.');
		if (i >= 0){
			var suffix = fileName.substring(i+1);
			var x = filter.indexOf(suffix);
			if (x >= 0) {
				return true;
			}else {
				return false;
			}
		}
	}
	return true;
}

//获取文件名称  
function getFileName(fname) {
    var pos1 = fname.lastIndexOf('/');  
    var pos2 = fname.lastIndexOf('\\');
    var pos = Math.max(pos1, pos2);
    if (pos < 0) {  
        return fname;  
    }else {  
        return fname.substring(pos + 1);
    }
}

function checkFileRepeat(obj){
	var fileStr = $(obj).val();
	var fileName = getFileName(fileStr);
	var file = $("input[name='myFileName']").toArray();
	for(var i=0;i<file.length;i++){
		 var fname = $(file[i]).val();
		 if(fileName==fname){
			 alert("该文件["+fname+"]已被上传，请选择其他文件!");
			 return false;
		 }
	}
	return true;
}

//页面已选择文件总共大小KB
var selectedFileSize = 0;

//当前上传文件大小KB
function getCurrentFileSize(obj){
	var file = $(obj).get(0).files[0];
	var filesize = Math.round(file.size*100/1024)/100;
	return filesize;
}
//设置页面已选择文件总共大小KB
function setSelectedFileSize(obj,add){
	try{
		var file = $(obj).get(0).files[0];
		var filesize = Math.round(file.size*100/1024)/100;
		console.log("[before deal] selectedFileSize:"+selectedFileSize+"&filesize:"+filesize);
		if(add){
			selectedFileSize = Math.round((selectedFileSize + filesize)*100)/100;
		}else{
			selectedFileSize = Math.round((selectedFileSize - filesize)*100)/100;
		}
		console.log("[end deal] selectedFileSize:"+selectedFileSize);
	}catch(e){}
}

function checkTotalFileSize(obj){
	var curFileSize = getCurrentFileSize(obj);
	var totalFileSize = Math.round((selectedFileSize + curFileSize)*100)/100;
	if(totalFileSize >= fileSizeMax){
		var size = fileSizeMax/1024;//最大文件大小限制KB
		alert("已选文件大小["+selectedFileSize+"KB],当前文件大小["+curFileSize+"KB],总共["+totalFileSize+"KB],已达到最大["+size+"KB]文件大小上传限制!");
		return false;
	}
	return true;
}

function uploadFile5(obj){
	if(!checkFileNumber()){
		$('#fileBtn').hide();
		alert("已达文件最大数"+fileMax+"!");
		return;
	}
	if(!checkFileSuffix(obj)){
		alert("文件后缀不合法，只允许上传文件的类型["+filter+"]!");
		return;
	}
	if(!checkFileRepeat(obj)){
		return;
	}
	if(!checkTotalFileSize(obj)){
		return;
	}
	addFile(obj);
}

function getMyFileTypeOptions(){
	try{
		var select = "<option value=''>==请选择==</option>";
		var myFileType = _enum_params.uploadfile_type;
		for(var i=0;i<myFileType.length;i++){
			var fileTypeObj = myFileType[i];
			select += "<option value='"+fileTypeObj[0]+"'>"+fileTypeObj[1]+"</option>" ;
		}
		return select;
	}catch(e){
		return "";
	}
}

function addFileComponent5(){
	if(!checkFileNumber()){
		$('#fileBtn').hide();
		alert("已达文件最大数"+fileMax+"!");
		return;
	}
	var displayStr = "<tr>";
	displayStr += "<td style='width:320px;'><input type='hidden' name='myFileName'></input>";
	displayStr += "<input type='file' name='myFile' onchange='uploadFile5(this)' style='display:none;'></input>";
	displayStr += "<input type='button' class='l-button' value='选择文件' onclick='$(this).prev().click()'>";
	displayStr += "</td>";
	displayStr += "<td style='width:80px;padding-left:2px;'></td>";
	displayStr += "<td style='width:320px;'><select name='myFileType' style='width:320px;'>"+getMyFileTypeOptions()+"</select></td>";
	displayStr += "<td style='width:60px;'><select name='myImportType' style='width:60px'><option value='1' selected>覆盖</option><option value='2'>追加</option></select></td>";
	displayStr += "<td style='width:30px;'><span class='delarea' onclick='deleteFile(this)'></span></td>";
	displayStr += "</tr>";
	$('#displayFiles').append(displayStr);
	setTipMessage();
}

function addFile(obj){
	var jqFileObj = $(obj);
	var fname = getFileName(jqFileObj.val());
	var myFileNameSpan = "<span>"+fname+"</span>";
	jqFileObj.prev().before(myFileNameSpan);
	jqFileObj.prev().val(fname);
	jqFileObj.next().hide();
	var sizeSpan = getCurrentFileSize(obj);
	jqFileObj.parent().next().html("<span>&nbsp;"+sizeSpan+"</span>");
	setTipMessage();
	setSelectedFileSize(obj,true);
}


function deleteFile(obj){
	var fileObj = $(obj).parent().parent().children("input:file");
	setSelectedFileSize(fileObj,false);
	$(obj).parent().parent().remove();
	setTipMessage();
}

function doMySubmit(params){
	$("#businessDate").val(params.businessDate);
	var url = '${_CONTEXT_PATH}/sys/file-upload-down!upload.action?tms='+new Date().getTime();
	var options = {  
	        url: url,
	        beforeSubmit: showRequest,//提交前处理 
	        success: showResponse,//处理完成 
	        resetForm: false,
	        dataType: 'json' 
	       };
	 $('#fileform').ajaxSubmit(options);
}

function showRequest(formData, jqForm, options){
	var businessDate = $("#businessDate").val();
	var flag = false;
	var msg = "";
	var len = getUploadFileSize();
	if(len<1){
		msg = "请选择上传的文件！"
		flag = false;
	}else{
		flag = true;
	}
	if(flag){
		var files = $("input[type=file]").toArray();
		for(var i=0;i<files.length;i++){
			var fval = null;
			try{
				//fval = files[i].files[0].name;
				fval = $(files[i]).val();
				//alert(fval);
			}catch(e){}
			if(fval==null||fval==undefined||fval==""||fval=="undefined"||fval=="null"){
				msg += "\n第"+(i+1)+"行未选择上传的文件！"
				flag = false;
			}
		}
		var fileTypes = $("select[name='myFileType']").toArray();
		console.log(fileTypes);
		for(var i=0;i<fileTypes.length;i++){
			var fval = null;
			try{
				//fval = fileTypes[i].options[fileTypes[i].selectedIndex].value;
				fval = $(fileTypes[i]).val();
				alert(fval);
			}catch(e){}
			if(fval==null||fval==undefined||fval==""||fval=="undefined"||fval=="null"){
				msg += "\n第"+(i+1)+"行未选择文件类型！"
				flag = false;
			}
		}
	}
	if(!flag){
		alert(msg);
		parent.hideMask();
		parent.$("#saveBtn").removeAttr("disabled");
	}
	return flag;
}

function showResponse(responseText, statusText, xhr, $form){
	parent.callback(responseText);
}

</script>
</html>