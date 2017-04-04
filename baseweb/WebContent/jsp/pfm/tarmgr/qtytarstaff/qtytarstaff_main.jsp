<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工手工数据维护</title>
<jsp:include page="/comm.jsp"></jsp:include>
<script type="text/javascript" src="${_CONTEXT_PATH}/jsp/pfm/tarmgr/qtytarstaff/qtytarstaff_main.js"></script>
<script type="text/javascript" src="${_CONTEXT_PATH}/jsp/pfm/pfmjs/pfmcommon.js"></script>
<style type="text/css">
	body{width:850px;}
</style>
</head>
<body>
<n:page action='com.soule.crm.pfm.tarmgr.qtytarstaff.QtytarstaffAction' />
<table class='content'>
<tr><td>
<form id="myform" action="${_CONTEXT_PATH}/Qtytarstaff!tarQuery.action">
<fieldset class="queryText"><legend>查询条件</legend>
<table class='params'>
	<tr>
	<td>
	<table>
	<tr>
		<td align="right"><font color="red">*</font>指标日期</td>
		<td ><input type='text' id='storeDate' name='base.storeDate'  validate="{required:true}"/></td>
		<td align="left" valign="center">
				<input id='tarQuery' name='tarQuery' type='button' value='查&nbsp;&nbsp;询' class='l-button'/>
				<input id='reset' name='reset' type='button' value='重&nbsp;&nbsp;置' class='l-button'/>
		</td>
		<td>
			<input type="hidden" id="uploadId" name="uploadId" />
			<input type="hidden" id="uploadName" name="uploadName" />
			<input type="hidden" id="uploadPath" name="uploadPath" />
			<input type="hidden" id="uploadFileId" name="uploadFileId" />
		</td>
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
		<table>
		<tr>
		<td>
		<fieldset class="detailList" style="width:250px;"><legend>可选择指标</legend>
				<div id='tarlist' style="overflow:auto;"></div>
		</fieldset>
		</td>
		<td>
		      <input type="button" class="l-button" id="buttonid" value="全选&gt;&gt;" onclick="selectRowsAll();" style="width:45px;margin:2px;"/><br>
		      <input type="button" class="l-button" id="buttonid" value="选择&gt;" onclick="selectRows();" style="width:45px;margin:2px;"/><br>
		      <input type="button" class="l-button" id="buttonid" value="&lt;移除" onclick="selectRemove();" style="width:45px;margin:2px;"/><br>
		      <input type="button" class="l-button" id="buttonid" value="&lt;&lt;全移" onclick="selectRemoveAll();" style="width:45px;margin:2px;"/>
		</td>
		<td>
		<fieldset class="detailList" style="width:250px;"><legend>已选择指标</legend>
				<div id="rightSelList" style="overflow:auto;"></div>
		</fieldset>
		</td>
		<td>
		<fieldset class="detailList" style="width:200px;"><legend>已选择员工</legend>
				<div id="staffSelList" style="overflow:auto;"></div>
		</fieldset>
		</td>
		<td>
		     <input type="button" class="l-button" id="staffSel_btn" value="选&nbsp;&nbsp;择" onclick="staffImport();" style="width:55px;margin:2px;"/><br>
		     <input type="button" class="l-button" id="buttonid" value="删&nbsp;&nbsp;除" onclick="staffDel();" style="width:55px;margin:2px;"/><br>
		     <input type="button" class="l-button" id="buttonid" value="全部删除" onclick="staffDelAll();" style="width:55px;margin:2px;"/><br>
		</td>
		</tr>
		</table>
	</td>
	</tr>
	<tr>
		<td align="right">
				<input id='staffTargetImportFile' name='staffTargetImportFile' type='button' value='模板下载' class='l-button' onclick="exportFile()"/>
				<input id='staffTargetImport' name='staffTargetImport' type='button' value='批量导入' class='l-button' onclick="importFile()"/>
				<input id='staffTargetQuery' name='staffTargetQuery' type='button' value='查&nbsp;&nbsp;询' class='l-button' onclick="staffTargetQuery()"/>
				<input id='staffTargetSave' name='staffTargetSave' type='button' value='保&nbsp;&nbsp;存' class='l-button' onclick="staffTargetSave()"/>
		</td>
	</tr>
	<tr>
	<td>
		<fieldset class="detailList"><legend>手工数据维护</legend>
			<div id="staffQueryDiv">
	       		<div id="staffTarQuery" style="overflow:auto;"></div>
	    	</div>
	    </fieldset>
	</td>
	</tr>
</table>
</body>
<script type='text/javascript'>

</script>
</html>