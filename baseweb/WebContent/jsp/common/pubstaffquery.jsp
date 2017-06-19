<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择客户公共页面</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
body {width:600px}
.s1-params input, .s1-params select {width:150px;}
.s1-params>tbody>tr>td:first-child { width:60px;}
</style>
</head>
<body>
<n:enums keys="sex"></n:enums>
<table>
		<tr>
			<td style="padding-right: 10px; vertical-align: top;">
				<form id="myform" action="${_CONTEXT_PATH}/qstaff!query.action">
					<table class='s1-params'>
						<tr>
							<td nowrap="nowrap">员工编号</td>
							<td align="left"><input type='text' id='staffId'
								name='staffId' /></td>
						</tr>
						<tr>
							<td>员工名称</td>
							<td align="left"><input type='text' id='staffName'
								name='staffName' /></td>
						</tr>
						<tr>
							<td>归属部门</td>
							<td align="left"><input type='hidden' id='ownerUnitId'
								name='ownerUnitId' style="width: 0px;" /><input type='text'
								id='ownerUnitName' name='ownerUnitName' readonly="readonly"
								class="unit_select" /></td>
						</tr>
						<tr>
							<td>员工级别</td>
							<td align="left"><input type='text' id='staffLevel'
								name='staffLevel' /></td>
						</tr>
						<tr>
							<td>员工性别</td>
							<td><n:select codetype="sex" id="sex" name='sex'
									emptyOption="true" disabled="false"></n:select></td>
						</tr>
						<tr>
							<td align="center" colspan="2"><input id='query'
								name='query' type='button' value='查询' style="width: 60px" />&nbsp;<input
								id='reset' name='reset' type='button' value='重置'
								style="width: 60px;" /></td>
						</tr>
					</table>
				</form>
			</td>
			<td>
				<div id='baselist'></div>
			</td>
		</tr>
	</table>
</body>
<script type='text/javascript'>
	var dupFlag = '${param.dup}';
    $(function () {
        $('#reset').bind('click', doClear);
        $('#query').bind('click', execute);
        $("#ownerUnitName").bind('click ', openSelectUnit);
        //$('#ownerUnitName').bind('click', function openSelectUnit(){
        //	Utils.openSelectUnit(null,'',setUnitIdName);
        //});
     //输出表格
        $("#baselist").ligerGrid({
        		enumlist: _enum_params,
                columns: [
                { display: '员工编号', name: 'staffId', width:100,align: 'left' },
                { display: '员工名称', name: 'staffName',width:100, align: 'left' },
                { display: '所属部门', name: 'ownerUnitName',width:120, align: 'left' },
                { display: '性别', name:'sex',width:70,align:'center',codetype:'sex'}
            ],
            pageSize:10,
            sortName: 'staffId',
            height:284,
            //width:350,
            width:420,
            checkbox: (dupFlag == 'true'),
            onDblClickRow: function (data,rowindex,rowobj){
               // getStaffIdAndName(data.staffId,data.staffName);
            },
            onError: function() {
                $.dialobBox.alert("查询数据失败",'提示内容');
            }
        });

        var operUnitId = '${logUserInfo.operUnitId}';
    	var operUnitName = '${logUserInfo.operUnitName}';
    	$("#ownerUnitId").val(operUnitId);
    	$("#ownerUnitName").val(operUnitName);
    
    });
	function openSelectUnit(){
	    Utils.openSelectUnit(null,'',setUnitIdName);
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

    function doClear(){
    	$("#staffId").val("");
 		$("#staffName").val("");
 		$("#staffLevel").val("");
 		$("#ownerUnitId").val("");
 		$("#ownerUnitName").val("");
 		$("#staffStatus").val("");
 		$("#sex").val("");
    }
    function execute() {
        var formdata = Utils.convertParam('queryIn','myform');
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

    function select() {
    	var manager = $('#baselist').ligerGetGridManager();
    	if(dupFlag == 'true') {
    		var rows = manager.getCheckedRows();
    		if (!rows||rows.length == 0){
    			Utils.alert("请选择员工");
    		}
    		return rows;
    	}
    	else {
    		var sel = manager.getSelectedRow();
    		if (!sel){
    			Utils.alert("请选择员工");
    		}
    		return sel;
    	}
    }
</script>
</html>