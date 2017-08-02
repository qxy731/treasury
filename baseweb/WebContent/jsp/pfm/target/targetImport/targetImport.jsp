<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>指标数据补录</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
</style>
</head>
<body>
	<n:page action='com.soule.app.report.ReportBaseAction' />
	<%-- <n:enums keys='busi_line,suitable_object'></n:enums> --%>
	<table class='content'>
		<tr>
			<td>
				<fieldset class="queryBox">
					<legend>设置模板</legend>
					<table class='params'>
						<tr>
							<td width="60px" valign="middle"><font color="red">*</font>数据日期</td>
							<td align="left"><input type='text' id='recoreDate' name='recoreDate'/></td>
						</tr>
					</table> 
					<table class='params'>
						<tr>
							<td width="48%" >
								<span style="margin-right: 20px;">
									可选考核指标
								</span>
							</td>
							<td  width="4%"></td>
							<td colspan="1" width="48%">
								<span style="margin-left:0px;">
									<font color="red">*</font>已选考核指标
								</span>
							</td>
						</tr>
						<tr>
							<td colspan="1">
								<span style="margin-right: 20px;"> 
									<select id="indexCodeOptions" name="indexCodeOptions" style="width: 100%;  height: 120px;"  multiple="multiple"> </select>
								</span>
							</td>
							<td>
								<span>
									<input id='addAllIndexCode' type='button' value='>>' class='l-button' onclick="addAllIndexCode();" style="width: 100%;  margin: 3px 3px 3px 3px" /> 
									<input id='addIndexCode' type='button' value='>' class='l-button' onclick="addIndexCode();" style="width: 100%;  margin: 3px 3px 3px 3px" /> 
									<input id='removeIndexCode' type='button' value='<' class='l-button'   onclick="removeIndexCode();" style="width: 100%;  margin: 3px 3px 3px 3px" /> 
									<input id='removeAllIndexCode' type='button' value='<<' class='l-button'   onclick="removeAllIndexCode();" style="width: 100%;  margin: 3px 3px 3px 3px" /> 
								</span>
							</td>
							<td colspan="1">
								<span style="margin-left: 0px;"> 
									<select id="indexCodeChoosed" name="indexCodeChoosed" style="width: 100%; height: 120px;" onchange="" size="5" multiple="multiple"> </select>
								</span>
							</td>
							<%-- <td colspan="1">
								<span style="margin-left: 20px;"> 
									<select id="objectChoosed" name="objectChoosed" style="width: 150px; height: 120px;" onchange="" size="5" multiple="multiple"></select>
								</span>
							</td>
							<td width="20%">
								<span style="margin-left: 0px;"> 
									<input id='addObject' type='button' value='添加' class='l-button' onclick="addObject();" style="width: 60px; margin: 6px 6px 6px 6px" /> 
									<input id='removeObject' type='button' value='删除' class='l-button' onclick="removeObject();" style="width: 60px; margin: 6px 6px 6px 6px" />
								</span>
							</td> --%>
						</tr>
						<tr>
							<td colspan="5">
								<div align="center" style="margin: 10px;">
									<table width="100%">
										<tr>
											<td></td>
											<td align="right">
												<input id='expBtn' type='button' value='导出模板' onclick="execute();" class='l-button' /> 
												<!-- <input id='query' type='button' value='查&nbsp;询' class='l-button' style="display: none"/> -->
											</td>
											<td width="5%"></td>
											<td width="5%"></td>
											<td align="left">
											<input id='impBtn' type='button' value='导&nbsp;入' onclick="upload();" class='l-button' />
												<!-- <input id='reset' type='button' value='重&nbsp;置' class='l-button' style="display: none"/> -->
											</td>
											<td></td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</fieldset>
			</td>
		</tr>

		<tr>
			<td>
				<fieldset class="outbox" style="height: 500px;">
					<legend>导入结果</legend>
					<s:form id="myform" action="/report/report-base.action" target="reportdetail" method="post">
						<input type="hidden" id="sqlKey" name="sqlKey" value="" />
						<input type="hidden" id="templateName" name="templateName" value="" />
						<input type="hidden" id="templateNameCN" name="templateNameCN" value="指标数据导入模板" />
						<!-- <input type="hidden" id="recoreDateRep" name="params.recoreDate" /> -->
						<input type="hidden" id="loadDateRep" name="params.loadDate" />
						<input type="hidden" id="indexCodeRep" name="params.indexCode" />
						<!-- <input type="hidden" id="objectIdRep" name="params.objectId" /> -->
						<table width="50%" style="margin-left: 75px; margin-top: 10px; margin-bottom: 0px; text-align: center; display: none;">
							<tr style="margin: 10px;">
								<td align="right" width="150px;">
									<s:submit id="exportBtn" method="export" value="导出模板" cssClass="l-button" style="display:none;"></s:submit>
								</td>
								<td align="right" width="100px;">
									<input id="importBtn" type='button' value='导&nbsp;入' onclick="upload();" class='l-button' style="display: none;" />
								</td>
							</tr>
						</table>
						<table width="100%">
							<tr>
								<td>
									<iframe frameborder="0" width="100%" height="260px" id="reportdetail" name="reportdetail" scrolling="auto"></iframe>
								</td>
							</tr>
						</table>
					</s:form>
				</fieldset>
			</td>
		</tr>
	</table>
</body>
<script type='text/javascript'>
$(function () {
	//Utils.validateInit();
	$("#recoreDate").ligerDateEditor();
	var today = new Date();
	var y = today.getFullYear();
	var m = today.getMonth()+1;
	var d = today.getDate();
	if(parseInt(d, 0)<10){
		d = "0" + d;
	}
	//alert(d+'-'+m+'-'+y);
	$("#loadDateRep").val(y+"-"+m+"-"+d);
	initIndexCodeOption();
	//addShadow();
	//$("#recoreDate").val(new Date());
// 	$('#reset').bind('click', doClear);
// 	$('#query').bind('click', execute);
	
// 	$("#reportdetail").height($(document).height()-$("#myform").height()-6);
 	$('#myform').action="/report/report-base!execute.action"
// 	$('#myform').submit();

});
function doClear() {
	var obj = document.getElementById('indexCodeChoosed');
	obj.options.length=0;
	/* var obj2 = document.getElementById('objectChoosed');
	obj2.options.length=0;
	$('#busiLine').val('1');
	$('#objectType').val('1'); */
	initIndexCodeOption();
}



function execute() {
// 	if (!$('#myform').valid()){
// 		return;
// 	}
	var recoreDate = $('#recoreDate').val()
	if(!recoreDate){
		$.dialogBox.warn("请选择数据日期!");
		return;
	}
	
	var obj = document.getElementById('indexCodeChoosed');
	var indexCodeStr = "";
	if(obj.options.length == 0){
		$.dialogBox.warn("请选择考核指标!");
		return;
	}else{
		for(var i=0; i<obj.options.length; i++){
// 			indexCodeStr = indexCodeStr + obj.options[i].value + "','";
			indexCodeStr = indexCodeStr + obj.options[i].text + ",";
		}
		indexCodeStr = indexCodeStr.substring(0, indexCodeStr.length-1);
		//alert(indexCodeStr);
	}
	
/* 	obj = document.getElementById('objectChoosed');
	var objectStr = "";
	var objectNameStr = "";
	if(obj.options.length == 0){
		$.dialogBox.warn("请选择考核对象!");
		return;
	}else{
		for(var i=0; i<obj.options.length; i++){
			objectStr =objectStr+ obj.options[i].value + ",";
			objectNameStr =objectNameStr+ obj.options[i].text + ",";
		}
		objectStr = objectStr.substring(0, objectStr.length-1);
		objectNameStr = objectNameStr.substring(0, objectNameStr.length-1);
	} */
	
	var mdata = [];
	mdata.push({name:'queryIn.recoreDate',value:$('#recoreDate').val()});
	mdata.push({name:'queryIn.indexCode',value:indexCodeStr});
	//mdata.push({name:'queryIn.objectId',value:objectStr});
	//mdata.push({name:'queryIn.objectName',value:objectNameStr});
	//mdata.push({name:'queryIn.objectType',value:$('#objectType').val()});
	var url = "${_CONTEXT_PATH}/pfm/param/index-data!exportTemplate.action";
	
	Utils.ajaxSubmit(url, mdata, 
	function (result){
		var template = result.templateFile;
		template = template.replace(/\\/g,'/');
		var url = "../../../.." + template;
		//var url = '${_CONTEXT_PATH}/upload/index_data_import.xls';
		//alert(url);
	 	window.open(url);
	}, 
	function (){
		$.dialogBox.error("导出模板失败！", function() {
		});
	});
}



function upload() {
	var recoreDate = $('#recoreDate').val()
	if(!recoreDate){
		$.dialogBox.warn("请选择数据日期!");
		return;
	}
	var obj = document.getElementById('indexCodeChoosed');
	var indexCodeStr = "";
	if(obj.options.length == 0){
		$.dialogBox.warn("请选择考核指标!");
		return;
	}else{
		for(var i=0; i<obj.options.length; i++){
			indexCodeStr = indexCodeStr + obj.options[i].value + "','";
		}
		indexCodeStr = indexCodeStr.substring(0, indexCodeStr.length-3);
	}
	
/* 	obj = document.getElementById('objectChoosed');
	var objectStr = "";
	if(obj.options.length == 0){
		$.dialogBox.warn("请选择考核对象!");
		return;
	}else{
		for(var i=0; i<obj.options.length; i++){
			objectStr =objectStr+ obj.options[i].value + "','";
		}
		objectStr = objectStr.substring(0, objectStr.length-3);
	} */
	$('#myform').action="/report/report-base!execute.action"
 	/* if($('#objectType').val() && $('#objectType').val() == '1'){
 		$('#sqlKey').val('indexdataorg.getPfmIndexDataMaul');
 		$('#templateName').val('pfm_index_data_maul_org');
 	}else{
 		$('#sqlKey').val('indexdatastf.getPfmIndexDataMaul');
 		$('#templateName').val('pfm_index_data_maul_per');
 	} */
 	$('#templateName').val('pfm_index_data_maul_per')
 	$('#sqlKey').val('indexdatastf.getPfmTmQtyOrg');
	$('#recoreDateRep').val($('#recoreDate').val());
	$('#indexCodeRep').val(indexCodeStr);
	//$('#objectIdRep').val(objectStr);
// 	document.getElementById('exportBtn').style.display='';
// 	document.getElementById('importBtn').style.display='';
//  	$('#myform').submit();
	
	Utils.uploadFile(afterUpload, null, 'xls', 'true', 'true', 1);
}

function afterUpload(uploadid, files) {
	var mdata = [];
	mdata.push({name:'readXlsFileIn.recoreDate',value:$('#recoreDate').val()})
	//mdata.push({name:'readXlsFileIn.objectType',value:$('#objectType').val()});
	mdata.push({name:'readXlsFileIn.uploadId',value:uploadid})
	var url = "${_CONTEXT_PATH}/pfm/param/index-data!readXlsFile.action";
	Utils.ajaxSubmit(url, mdata, importSuccess, importError);
}

function importSuccess() {
	$.dialogBox.info("导入数据成功", function() {
		$('#myform').action="/report/report-base!execute.action"
		$('#myform').submit();
	});
}

function importError() {
	$.dialogBox.info("导入数据失败！", function() {
	});
}

function initIndexCodeOption() {
	var mdata = [];
	//mdata.push({name:'queryIn.busiLine',value:$('#busiLine').val()});
	//mdata.push({name:'queryIn.suitableObject',value:$('#objectType').val()});
	mdata.push({name:'queryIn.dataSource',value:'2'});
	var url = "${_CONTEXT_PATH}/pfm/param/paraminfo!queryAll.action";
	Utils.ajaxSubmit(url, mdata, initSuccess, initError);
}

function initSuccess(result) {
	//alert(result.rows.length);
	var obj = document.getElementById('indexCodeOptions');
	obj.options.length=0;
	data = result.rows;
	for(var i=0; i<data.length; i++){
		var op1 = new Option(data[i].indexName,data[i].indexCode);
		obj.add(op1);
	}
}

function initError() {
	$.dialogBox.info("获取数据失败！", function() {
	});
}

function clearIndexCodeAndObject() {
	var obj = document.getElementById('indexCodeChoosed');
	obj.options.length=0;
	var obj2 = document.getElementById('objectChoosed');
	obj2.options.length=0;
}
function addAllIndexCode() {
	var orentObj = document.getElementById('indexCodeChoosed');
	var obj = document.getElementById('indexCodeOptions');
	for(var i=0; i<obj.options.length; i++){
		if(!isExist(orentObj,obj.options[i])){
			orentObj.add(new Option(obj.options[i].text,obj.options[i].value));
		}
	}
}

function isExist(obj,option){
	for(var i=0; i<obj.options.length; i++){
		if(obj.options[i].value == option.value){
			return true;
		}
	}
	return false;
}

function addIndexCode() {
	var orentObj = document.getElementById('indexCodeChoosed');
	var obj = document.getElementById('indexCodeOptions');
	for(var i=0; i<obj.options.length; i++){
		if(obj.options[i].selected){
			if(!isExist(orentObj,obj.options[i])){
				orentObj.add(new Option(obj.options[i].text,obj.options[i].value));
			}
		}
	}
}
function removeAllIndexCode() {
	var obj = document.getElementById('indexCodeChoosed');
	obj.options.length=0;
}
function removeIndexCode() {
	var obj = document.getElementById('indexCodeChoosed');
	for(var i=obj.options.length-1; i>=0; i--){
		if(obj.options[i].selected){
			obj.options[i] = null;
		}
	}
	
}

function addObject() {
	/* var object = $('#objectType').val();
	if(object == '1') {
		unitOrgName();
	}else if(object == '2'){
		unitStaffName();
	} */
}

function unitOrgName() {
	AppUtils.openSelectMulUnit(null, _CREATE_ORG, setUnitIdName);
}

function setUnitIdName() {
// 	var selectNode = this.iframe.contentWindow.manager.getSelected();
// 	if(!selectNode) {
// 		$.dialogBox.warn("未选中部门信息记录!");
// 		return;
// 	} 
// 	var unitId = selectNode.data.unitId;
// 	var unitName = selectNode.data.unitName;
	
	var rows = this.iframe.contentWindow.select();
	if (!rows) {
		$.dialogBox.warn("未选中部门!");
		return;
	}
	
	var unitId="";
	var unitName="";
	var obj = document.getElementById('objectChoosed');
	for(var i=0; i<rows.length; i++){
		unitId = rows[i].data.unitId;
		unitName = rows[i].data.unitName;
		if(!isExist(obj,new Option(unitName,unitId))){
			obj.add(new Option(unitName,unitId));
		}
	}
}


function unitStaffName() {
	AppUtils.openSelectStaff(setUnitStaffName, true);
}

function setUnitStaffName() {
	var selectNode = this.iframe.contentWindow.select();
	if(!selectNode) {
		$.dialogBox.warn("未选中员工信息记录!");
		return;
	} 
	var staffId="";
	var staffName="";
	var obj = document.getElementById('objectChoosed');
	for(var i=0; i<selectNode.length; i++){
		staffId = selectNode[i].staffId;
		staffName = selectNode[i].staffName;
		if(!isExist(obj,new Option(staffName,staffId))){
			obj.add(new Option(staffName,staffId));
		}
	}
		
}

function removeObject() {
	var obj = document.getElementById('objectChoosed');
	//alert($('#objectChoosed').val());
	//return;
	for(var i=obj.options.length-1; i>=0; i--){
		if(obj.options[i].selected){
			obj.options[i] = null;
		}
	}
}


</script>
</html>