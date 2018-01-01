<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>源数据文件查询</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
body {overflow: hidden;}
</style>
</head>
<body>
	<n:page action='com.soule.crm.pub.dataimport.DataImportAction' />
	<n:enums keys="uploadfile_type,dataimp_result,import_type"></n:enums>
	<table class='content'>
		<tr>
			<td style="padding-top: 10px; padding-bottom: 15px;">
				<form id="myform" action="" method="post">
					<fieldset class="queryBox" style="width: 100%;">
						<legend>查询条件</legend>
						<table class='params'>
							<tr>
								<td>文件类型</td>
								<td>
									<n:select id="fileType" name="fileType" codetype="uploadfile_type" emptyOption="true" />
								</td>
								<td>文件名称</td>
								<td>
									<input type='text' id='fileName' name='fileName' />
								</td>
								<td>文件上传国库</td>
								<td>
									<input type='hidden' id='orgCode' name='orgCode'  value="${logUserInfo.operUnitId}" /> 
									<input type='text' id='orgName' name='orgName' readonly="readonly" onclick="openSelectUnit()" class="unit_select" value="${logUserInfo.operUnitName}" />
								</td>
							</tr>
							<tr>
								<td>上传日期(从)</td>
								<td>
									<input type='text' id='beginDate' name='beginDate' readonly="readonly"/>
								</td>
								<td>上传日期(到)</td>
								<td>
									<input type='text' id='endDate' name='endDate' readonly="readonly"/>
								</td>
								<td>文件上传者</td>
								<td>
									<input type='hidden' id='staffId' name='staffId' style="width: 0px;" /> 
									<input type='text' id='staffName' name='staffName' readonly="readonly" onclick="openSelectAStaff()" class="user_select" />
								</td>
							</tr>
							<tr>
								<td colspan="6">
									<div style="float:right;">
										<input id='query' type="button" value="查&nbsp;询" class="l-button" style="float:left;margin-right:5px;" />
										<input id='reset' type='button' value='重&nbsp;置' class='l-button' style="float:left;margin-right:5px;"/>
									</div>
								</td>
							</tr>
						</table>
					</fieldset>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<fieldset class="outbox">
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
    $("#endDate").ligerDateEditor();
    $("#toptoolbar").ligerToolBar({items:[
  	      {text:'下载模板',name:'insert_btn',icon:'export',click:btn_downmodule_click},
  		  {text:'上传文件',name:'commit_btn',icon:'submit',click:btn_upfile_click},
  		  {text:'删除文件',name:'delete_btn',icon:'delete',click:btn_delfile_click},
  		  {text:'加载文件数据',name:'btn_loadFile',icon:'save',click:loadFileData},
  		  {text:'导入错误信息',name:'btn_detail',icon:'detail',click:queryDetail},
  		  {text:'计算指标数据',name:'btn_batchFile',icon:'yunxing',click:batchTargetData},
  		  {text:'批处理监控',name:'btn_batchMonitor',icon:'yunxing',click:monitorBatch},
  		  {text:'删除数据源',name:'btn_deleteFile',icon:'yunxing',click:deleteTargetData}
  	],
  		  width:'99%'
  	});
	//输出表格
	$("#filelist").ligerGrid({
		checkbox:true,
		isSingleCheck :false,
		selectRowButtonOnly:true,
		enumlist: _enum_params ,
		columns: [
			{ display: '文件编号',name:'uploadId',align: 'left',width:200},
			{ display: '处理结果', name: 'resultType', align: 'center',codetype:'dataimp_result', width: 60 },
			{ display: '数据日期', name: 'businessDate', align: 'left', width: 60},
			{ display: '文件类型', name: 'fileType', align: 'left',codetype:'uploadfile_type', width: 310},
			{ display: '文件名称', name: 'fileName', align: 'left', width: 300 },
			{ display: '文件上传者', name: 'staffName', align: 'left', width: 65 },
			{ display: '文件上传国库', name: 'orgName', align: 'left', width: 140 },
			{ display: '文件大小(KB)', name: 'fileSize', align: 'right',width:80 },
			{ display: '上传日期', name: 'uploadDate', align: 'center',width:125 },
			{ display: '处理模式', name: 'importType', align: 'center',codetype:'import_type',width:70}
		],
		pageSize:20,
		width: '100%',
		height:'100%',
		heightDiff:-20,
		onError: function(e) {
			Utils.toIndex(e);
		}
	});
	
});

function btn_downmodule_click() {
	var url = "${_CONTEXT_PATH}/jsp/pfm/target/dataImport/dataimp_down.jsp";
	$.dialogBox.openDialog(url, {title:"下载模板", width:'500px', height:'150px'});
}

function btn_delfile_click(){
	var grid = $('#filelist').ligerGetGridManager();
	var rows = grid.getSelectedRows();
	if(rows==null){
		$.dialogBox.info("请选择记录",true);
		return;
	}
	
	var uploadId="";
	var fileId ="";
	for(var i=0;i<rows.length;i++){
		uploadId= uploadId+rows[i].uploadId+",";
		fileId= fileId+rows[i].fileId+",";
	}
	/* if(_CREATE_USER!=rows.staffId){
		$.dialogBox.warn("只能删除自己导入的文件!");
		return;
	} */
	//var uploadId=rows.uploadId;
	doComAndUncomAndDel(uploadId,fileId,'删除',"${_CONTEXT_PATH}/pub/data-import!deleteFile.action");
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

//上传文件
function btn_upfile_click() {
	var url = "${_CONTEXT_PATH}/jsp/pfm/target/dataImport/dataimp_up.jsp";
	$.dialogBox.openDialog(url, {title:"上传文件", width:'860px', height:'550px'});
}


function doClear() {
	$(".queryBox input[type='text']").each(function(i,item){
		item.value ='';
	});
	$(".queryBox select").each(function(i,item){
		item.value ='';
	});
	$("#orgCode").val(_CREATE_ORG);
	$("#orgName").val(_CREATE_ORGNAME);
	$("#staffId").val("");
}

function execute() {
	if (!$('#myform').valid()){
		return;
	}
	var mdata = Utils.convertParam('queryIn','myform');
	var params = {
		dataAction:'server',
		dataType:'server', 
		cache: false,
		url: '${_CONTEXT_PATH}/pub/data-import!query.action',
		newPage:1,
		parms:mdata,
		onError: function(e) {
			Utils.toIndex(e);
		}
	}; 
	/* console.log(mdata);
	alert(JSON.stringify(mdata)); */
	var gridManager = $("#filelist").ligerGetGridManager();
	gridManager.setOptions(params); 
	gridManager.loadData();
}

function getUploadId(){
	var gridManager = $("#filelist").ligerGetGridManager(); 
	var row = gridManager.getSelectedRow();
	if(row){
		return row.uploadId;
	}else{
		return null;
	}
}

function getOpSelectedRow(){
	var gridManager = $("#filelist").ligerGetGridManager(); 
	var row = gridManager.getSelectedRow();
	if(row){
		return row;
	}else{
		return null;
	}
}

function queryDetail(){
	var gridManager = $("#filelist").ligerGetGridManager(); 
	var row = gridManager.getSelectedRow();
	if(row){
		var url = "${_CONTEXT_PATH}/jsp/pfm/target/dataImport/dataimp_temp.jsp";
		$.dialogBox.openDialog(url,{title:"查看导入详情",width:'750px',height:'530px',okVal:'关闭'},true,null);
	}else{
		$.dialogBox.warn("请选择记录！");
	}
}

function loadFileData(){
	var url = "${_CONTEXT_PATH}/pub/data-import!loadFileData.action";
	var data = {};
	$.dialogBox.confirm('您确定现在开始加载文件数据吗？',function () {
		Utils.ajaxSubmit(url, data, function(result) {
			$.dialogBox.info('加载文件数据已全部完成，导入结果信息请查看详情！');
			execute();
		});
},true);
}
//计算指标数据
function batchTargetData(){
	Utils.openSelectDate(null,null,function () {
		var dataDate = this.iframe.contentWindow.select();
		if(!dataDate){
			alert("请选择数据日期");
			return false;
			//return;
		}
		var data = {"dataDate":dataDate};
		var url = "${_CONTEXT_PATH}/pub/data-import!batchTargetData.action"
		 Utils.ajaxSubmit(url, data, function(result) {
			$.dialogBox.info('跑批结束，详情请查看批处理监控。');
			execute();
			});  
		},true);
}

//删除数据源
function deleteTargetData(){
	Utils.openSelectDate(null,null,function () {
		var dataDate = this.iframe.contentWindow.select();
		if(!dataDate){
			alert("请选择数据日期");
			return false;
			//return;
		}
		var data = {"dataDate":dataDate};
		var url = "${_CONTEXT_PATH}/pub/data-import!deleteTargetData.action"
		 Utils.ajaxSubmit(url, data, function(result) {
			$.dialogBox.info('数据源已经清除。');
			execute();
			});  
		},true);
}

//选择国库信息
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

function showUpMessage(){
	$.dialogBox.info("文件上传成功。",true);
	execute();
}

function monitorBatch() {
	var url = '${_CONTEXT_PATH}/jsp/sysmgr/batch/choiceinst.jsp?bid=bat_flow';
	$.dialogBox.openDialog(url, {id : 'choiceinst',height : '180px',width : '380px',lock : true,opacity : 0.07,title : '选择监控实例ID'}, toMonitorPage,true);
};

function toMonitorPage(p) {
	var instid = p.getInstId();
	if (instid) {
		Utils.openTab('batchmonitor-bat_flow','监控-bat_flow','${_CONTEXT_PATH}/jsp/sysmgr/batch/batchmonitor.jsp?bid=bat_flow&iid=' +instid);
	}
	else {
		return false;
	}
};
</script>
</html>