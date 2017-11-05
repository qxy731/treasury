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
.param {border:1px solid #ccc;margin:0;padding:0;}
.param td {margin-top:2px;}
.param input {width:120px;}
</style>
</head>
<!-- border: thick double yellow; -->
<body style="width:545px;margin:0;padding:0;">
<n:enums keys="sex,education_type,partime_job_type"></n:enums>
<table style="width:545px;margin:0;padding:0;">
<td width="213px">
<div class="param" style="width:213px;height:286px;">
<form id="myform" action="${_CONTEXT_PATH}/qstaff!query.action">
<table style="width:213px;">
    <tr>
        <td align="right" width="60px" style="background-color:#E2EAFF;">员工编号 </td><td align="left"><input type='text' id='staffId' name='staffId'/></td>
    </tr>
    <tr>
        <td align="right" width="60px" style="background-color:#E2EAFF;">员工名称 </td><td align="left"><input type='text' id='staffName' name='staffName'/></td>
    </tr>
    <tr>
        <td align="right" width="60px" style="background-color:#E2EAFF;">所属国库 </td><td align="left"><input type='hidden' id='ownerUnitId' name='ownerUnitId' style="width:0px;"/>
        <input type='text' id='ownerUnitName' name='ownerUnitName' readonly="readonly"/><input type="button" value="..." id="createOrgBtn1" onclick="openSelectUnit()" style="width:20px;height:20px;">
        </td>
    </tr>
    <tr>
         <td align="right" width="60px" style="background-color:#E2EAFF;">员工级别 </td><td align="left"><input type='text' id='staffLevel' name='staffLevel'/></td>
    </tr>    
    <tr>
         <td align="right" width="60px" style="background-color:#E2EAFF;">员工性别 </td><td>
         	<n:select codetype="sex" id="sex" name='sex' emptyOption="true" disabled="false" cssStyle="width:124px;"></n:select>
         </td>
    </tr>
    <tr>
         <td align="center" colspan="2">
	         <input id='query' name='query' type='button' value='查询' style="width:60px"/>&nbsp;<input id='reset' name='reset' type='button' value='重置' style="width:60px;"/>
         </td>         
      </tr>
      </table>
</form>
    </div>
</td>
<td align="left">
	<div id='baselist'></div>
</td>
</table>
</body>
<script type='text/javascript'>
    $(function () {
        $('#reset').bind('click', doClear);
        $('#query').bind('click', execute);
     //输出表格
        $("#baselist").ligerGrid({
        		enumlist: _enum_params,
                columns: [
                { display: '员工编号', name: 'staffId', width:70,align: 'left' },
                { display: '员工名称', name: 'staffName',width:90, align: 'left' },
                { display: '所属国库', name: 'ownerUnitName',width:140, align: 'left' },
                { display: '性别', name:'sex',width:26,align:'center',codetype:'sex'}                          
            ],
            pageSize:10,
            sortName: 'staffId',
            width: 330,
            height:284, 
            onDblClickRow: function (data,rowindex,rowobj){
                getStaffIdAndName(data.staffId,data.staffName);
            },
    		onError: function(e) {
    			Utils.toIndex(e);
    		}
        });
    
    });
    $(function (){
    	var operUnitId = '${logUserInfo.operUnitId}';
    	var operUnitName = '${logUserInfo.operUnitName}';
    	setUnitIdName(operUnitId,operUnitName);
    });
    function doClear(){
    	$("#staffId").val("");
 		$("#staffName").val("");
 		$("#staffLevel").val("");
 		$("#ownerUnitId").val("");
 		$("#ownerUnitName").val("");
 		$("#staffStatus").val("");
 		$("#sex").val("");
    }
    function execute(){     
        var staffId = $("#staffId").val();
		var staffName=$("#staffName").val();
		var staffLevel = $("#staffLevel").val();
		var ownerUnitId = $("#ownerUnitId").val();
		var staffStatus = $("#staffStatus").val();
		var sex = $("#sex").val();
		var params = {
			dataAction:'server',
			dataType:'server',
			url: '${_CONTEXT_PATH}/qstaff!query.action',
			newPage:1,
			parms:[{name:'queryIn.staffId',value:staffId}
			,{name:'queryIn.staffName',value:staffName}
			,{name:'queryIn.staffLevel',value:staffLevel}
			,{name:'queryIn.ownerUnitId',value:ownerUnitId}
			,{name:'queryIn.sex',value:sex}
			,{name:'queryIn.staffStatus',value:staffStatus}]
		};
        var gridManager = $("#baselist").ligerGetGridManager(); 
		gridManager.setOptions(params);
		gridManager.loadData();
    }
    
    function openSelectUnit(){    	
    	Utils.openSelectUnit('#createOrgBtn1','',setUnitIdName);
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

    function setUnitIdName(unitId,unitName){
	    	$("#ownerUnitId").val(unitId);
	    	$("#ownerUnitName").val(unitName);
    }
    function getStaffIdAndName(staffId,staffName) {
    	parent.Utils._onStaffDialogSelected(staffId,staffName);
    }
</script>
</html>