<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>缺失数据导入</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
body {overflow: hidden;}
</style>
</head>
<body>
	<n:page action='com.soule.crm.pub.dataimport.DataImportAction' />
	<n:enums keys="uploadfile_type,dataimp_result"></n:enums>
	<table class='content'>
		<tr>
			<td style="padding-top: 10px; padding-bottom: 15px;">
				<form id="myform" action="">
					<fieldset class="queryBox" style="width: 100%;">
						<legend>查询条件</legend>
						<table class='params'>
							<tr>
								<td>文件类型</td>
								<td>
									<s:select list="#fileTypelist" listKey="key" listValue="value" id="fileType" name="fileType" emptyOption="true" />
								</td>
								<td>文件名称</td>
								<td>
									<input type='text' id='fileName' name='fileName' />
								</td>
								<td>文件上传部门</td>
								<td>
									<input type='hidden' id='orgCode' name='orgCode'  value="${logUserInfo.operUnitId}" /> 
									<input type='text' id='orgName' name='orgName' readonly="readonly" onclick="openSelectUnit()" class="unit_select" value="${logUserInfo.operUnitName}" />
								</td>
							</tr>
							<tr>
								<td>上传日期(从)</td>
								<td>
									<input type='text' id='beginDate' name='beginDate' />
								</td>
								<td>上传日期(到)</td>
								<td>
									<input type='text' id='endDate' name='endDate' />
								</td>
								<td>文件上传者</td>
								<td>
									<input type='hidden' id='staffId' name='staffId' style="width: 0px;" /> 
									<input type='text' id='staffName' name='staffName' readonly="readonly" onclick="openSelectAStaff()" class="user_select" />
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<table>
										<tr>
											<td align="right"><input id='query' type="button" value="查&nbsp;&nbsp;询" class="l-button" /></td>
											<td width="10"></td>
											<td align="left"><input id='reset' type='button' value='重&nbsp;&nbsp;置' class='l-button' /></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</fieldset>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<fieldset class="detailBox" style="width: 100%;">
					<div id="toptoolbar"></div>
					<div id='filelist'></div>
				</fieldset>
			</td>
		</tr>
	</table>
</body>
<script type='text/javascript'>
$(function () {
	Utils.validateInit();
	$('#reset').bind('click', doClear);
	$('#query').bind('click', execute);
	$("#beginDate").ligerDateEditor();
	$("#monthId").ligerDateEditor();
    $("#endDate").ligerDateEditor();
    $("#toptoolbar").ligerToolBar({items:[
  	      {text:'下载模板',name:'insert_btn',icon:'export',click:btn_downmodule_click},
  		  {text:'上传文件',name:'commit_btn',icon:'submit',click:btn_upfile_click},
  		  {text:'删除文件',name:'delete_btn',icon:'delete',click:btn_delfile_click}/* ,
  		  {text:'上传文件查看',name:'btn_detail',icon:'detail',click:queryDetail} */
  	],
  		  width:'99%'
  	});
	//输出表格
	$("#filelist").ligerGrid({
		enumlist: _enum_params ,
		columns: [
			{ display: '处理结果', name: 'resultType', align: 'center',codetype:'dataimp_result', width: 80 },
			{ display: '文件类型', name: 'fileType', align: 'left',codetype:'uploadfile_type', width: 150 },
			{ display: '文件名称', name: 'fileName', align: 'left', width: 220 },
			{ display: '文件上传者', name: 'staffName', align: 'left', width: 80 },
			{ display: '文件上传部门', name: 'orgName', align: 'left', width: 160 },
			{ display: '文件大小(k)', name: 'fileSize', align: 'center',width:85 },
			{ display: '上传日期', name: 'uploadDate', align: 'center',width:150 }
		],
		pageSize:10,
		width: '100%',
		height:'100%',
		heightDiff:-20,
		onDblClickRow:queryDetail,
		onError: function(e) {
			Utils.toIndex(e);
		}
	});	
	//$("#hiddtrId").hide();
	addShadow();
});
function chgFileType(obj){
	var fileType = obj.value;
	if(fileType=="07"||fileType=="09"){
		$("#hiddtrId").show();
		$('#monthId').attr("validate","required:true");
	}else{
		$("#hiddtrId").hide();
		$('#monthId').attr("validate","");
	}
	Utils.validateInit();
}

function btn_downmodule_click() {
	var url = "${_CONTEXT_PATH}/jsp/pfm/target/dataImport/dataimp_down.jsp";
	$.dialogBox.openDialog(url, {title:"下载模板", width:'500px', height:'150px'});
}

function btn_delfile_click(){
	var grid = $('#filelist').ligerGetGridManager();
	var rows = grid.getSelectedRow();
	if(rows==null){
		$.dialogBox.info("请选择记录",true);
		return;
	}
// 	if(rows.resultType=='0'){
// 		$.dialogBox.warn("不能删除正在处理的文件!");
// 		return;
// 	}
	if(_CREATE_USER!=rows.staffId){
		$.dialogBox.warn("只能删除自己导入的文件!");
		return;
	}
	var uploadId=rows.uploadId;
	doComAndUncomAndDel(uploadId,rows.fileId,'删除',"${_CONTEXT_PATH}/pub/data-import!deleteFile.action");
}

//上传文件
function btn_upfile_click() {
	var url = "${_CONTEXT_PATH}/jsp/pfm/target/dataImport/dataimp_up.jsp";
	$.dialogBox.openDialog(url, {title:"上传文件", width:'400px', height:'280px'});
}

function doUploadFile(uploadId,files){
	var fileType = $("#fileType").val();
	var url = "${_CONTEXT_PATH}/pub/data-import!uploadFile.action";
	var monthId = $('#monthId').val();
	Utils.ajaxSubmit(url,{"uploadId":uploadId,"monthId":monthId,"uploadFileType":fileType}, function(result){
		$.dialogBox.confirm('文件上传成功,是否确认导入?',function(){
			execute();
			callProcedureFile(uploadId,fileType);
		},true);
	}, function(result) {
		var url = "${_CONTEXT_PATH}/jsp/pfm/target/dataImport/dataimp_errorlist.jsp";
		errordata = result.rows;
		if (result.retMsg) {
			$.dialogBox.error(result.retMsg);
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
	Utils.ajaxSubmit(url,{"uploadId":uploadId,"uploadFileType":fileType,"monthId":monthId},function(){},function(){});
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

function doClear() {
	$(".queryBox input[type='text']").each(function(i,item){
		item.value ='';
	});
	$("#staffId").val('');
	$(".queryBox select").each(function(i,item){
		item.value ='';
	});
}

function execute() {
	if (!$('#myform').valid()){
		return;
	}
	var mdata = Utils.convertParam('queryIn','myform');
	var params = {
		dataAction:'server',
		dataType:'server', 
		url: '${_CONTEXT_PATH}/pub/data-import!query.action',
		newPage:1,
		parms:mdata,
		onError: function(e) {
			Utils.toIndex(e);
		}
	}; 
	var gridManager = $("#filelist").ligerGetGridManager();
	gridManager.setOptions(params); 
	gridManager.loadData();
}
function queryDetail(){
	var gridManager = $("#filelist").ligerGetGridManager(); 
	var row = gridManager.getSelectedRow();
	if(row){
		var fileType=row.fileType;
		var url= "";
		if(fileType!=null){
			url = "${_CONTEXT_PATH}/jsp/pub/dataimp/dataimp_temp.jsp?fileId="+row.fileId+"&uploadFileType="+fileType;
		}
		if(url!=""){
			$.dialogBox.openDialog(url,{title:"查看导入详情",width:'750px',height:'430px',okVal:'关闭'},true,null);
		}
	}else{
		$.dialogBox.warn("请选择记录！");
	} 
}
//选择部门信息
function openSelectUnit(){
	AppUtils.openSelectUnit(null,$("#orgCode").val(),setUnitIdName);
}



function setUnitIdName(){
	var selectNode = this.iframe.contentWindow.manager.getChecked();
	if (selectNode) {
		var unitId = selectNode[0].data.unitId;
		var unitName = selectNode[0].data.unitName;
		$("#orgCode").val(unitId);
		$("#orgName").val(unitName);
	}
}
function openSelectAStaff(){
	AppUtils.openSelectAStaff(setStaffIdName);
}

function openSelectAStaff() {
	Utils.openSelectAStaff(function() {
		var single = this.iframe.contentWindow.select();
		if (!single) {
			return false;
		}
		$('#staffId').val(single.staffName);
		$('#staffName').val(single.staffId);
	}, true);
}

function setStaffIdName(){
	var staff = this.iframe.contentWindow.select();
	$("#staffId").val(staff.staffId);
	$("#staffName").val(staff.staffName);
}
</script>
</html>