<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择客户公共页面</title>
<style type="text/css">
body {width:600px}
.s1-params input, .s1-params select {width:120px;}
.s1-params>tbody>tr>td:first-child { padding:0;width:60px;}
</style>
</head>
<body>
<n:enums keys="sex"></n:enums>
<table>
<tr>
	<td>
		<form id="queryStaffForm" action="${_CONTEXT_PATH}/qstaff!query.action">
		<table class='s1-params'>
			<tr>
			<!--
				<td nowrap="nowrap">员工编号</td>
				<td align="left"><input type='text' id='staffId' name='staffId' /></td>
				<td nowrap="nowrap">员工名称</td>
				<td align="left"><input type='text' id='staffName'
					name='staffName' /></td>
			-->
			    <td>员工编号</td>
				<td ><input type='text' id='staffId' name='staffId' /></td>
				<td >员工名称</td>
				<td ><input type='text' id='staffName'
					name='staffName' /></td>
			</tr>
			<tr>
				<td >归属国库</td>
				<td ><input type='hidden' id='ownerUnitId'
					name='ownerUnitId' style="width: 0px;" /><input type='text'
					id='ownerUnitName' name='ownerUnitName' readonly="readonly" class="unit_select"/>
				</td>
				<td>员工级别</td>
				<td align="left"><input type='text' id='staffLevel' name='staffLevel' /></td>
			</tr>
			<tr>
				<td>员工性别</td>
				<td><n:select codetype="sex" id="sex" name='sex' emptyOption="true" disabled="false" ></n:select>
				</td>
				<td align="right"><input id='queryStaff' type='button' value='查&nbsp;询' class='l-button' style="width: 60px" /></td>
				<td align="center"><input id='resetStaff' type='button' class='l-button' value='重&nbsp;置' style="width: 60px;" /></td>
				<!--<td align="center" colspan="2"><input id='queryStaff'
					type='button' value='查&nbsp;询' class='l-button' style="width: 60px" />&nbsp;<input
					id='resetStaff' type='button' class='l-button' value='重&nbsp;置'
					style="width: 60px;" /></td>-->
			</tr>
		</table>
		</form>
	</td>
</tr>
<tr>
	<td>
		<div id='baselist'></div>
	</td>
</tr>
</table>
</body>
<script type='text/javascript'>
	var dupFlag = '${param.dup}';
    $(function () {
        $('#resetStaff').bind('click', doClearStaff);
        $('#queryStaff').bind('click', executeStaff);
        $('#ownerUnitName').bind('click', function openSelectUnit(){
        	Utils.openSelectUnit(null,'',setUnitIdName);
        });
        $("#baselist").ligerGrid({
                enumlist: _enum_params,
                columns: [
                { display: '员工编号', name: 'staffId', width:90,align: 'left' },
                { display: '员工名称', name: 'staffName',width:90, align: 'left' },
                { display: '所属国库', name: 'ownerUnitName',width:120, align: 'left' },
                { display: '性别', name:'sex',width:60,align:'center',codetype:'sex'}
            ],
            pageSize:10,
            sortName: 'staffId',
            height:284,
            //width:400,
            width:420,
            checkbox: (dupFlag == 'true'),
            onDblClickRow: function (data,rowindex,rowobj){
               // getStaffIdAndName(data.staffId,data.staffName);
            },
    		onError: function(e) {
    			Utils.toIndex(e);
    		}
        });
    });
    $(function (){
    	var operUnitId = '${logUserInfo.operUnitId}';
    	var operUnitName = '${logUserInfo.operUnitName}';
    	setUnitIdNameDirect(operUnitId,operUnitName);
    });
    function doClearStaff(){
    	$("#staffId").val("");
 		$("#staffName").val("");
 		$("#staffLevel").val("");
 		$("#ownerUnitId").val("");
 		$("#ownerUnitName").val("");
 		$("#staffStatus").val("");
 		$("#sex").val("");
    }
    function executeStaff() {
        var formdata = Utils.convertParam('queryIn','queryStaffForm');
		var params = {
			dataAction:'server',
			dataType:'server',
			url: '${_CONTEXT_PATH}/sys/qstaff!query.action',
			newPage:1,
			parms:formdata
		};
		var gridManager = $("#baselist").ligerGetGridManager(); 
		gridManager.setOptions(params);
		gridManager.loadData();
	}

    function setUnitIdName(){
    	var selectNode=this.iframe.contentWindow.manager.getSelected();
    	if(selectNode){
    		var unitId=selectNode.data.unitId;
    		var unitName=selectNode.data.unitName;
	    	$("#ownerUnitId").val(unitId);
	    	$("#ownerUnitName").val(unitName);
    	}
    }
    function setUnitIdNameDirect(unitId,unitName){
	    	$("#ownerUnitId").val(unitId);
	    	$("#ownerUnitName").val(unitName);
    }
    /**
     * 获取当前查询条件
     */
    function getQueryStaffCondition() {
    	return Utils.convertFormData('','queryStaffForm');
    }
    /**
     *获取当前条件下记录数
     */
    function getQueryStaffCount() {
        var manager = $('#baselist').ligerGetGridManager();
        var total = manager.data.total;
        if (total){
            return total;
        }
        return 0;
    }
    function select() {
    	var manager = $('#baselist').ligerGetGridManager();
    	if(dupFlag == 'true') {
    		var rows = manager.getCheckedRows();
    		if (!rows||rows.length == 0){
    			$.dialogBox.warn("请选择员工",'提示',true);
    		}
    		return rows;
    	}
    	else {
    		var sel = manager.getSelectedRow();
    		if (!sel){
    			$.dialogBox.warn("请选择员工",'提示',true);
    		}
    		return sel;
    	}
    }
</script>
</html>