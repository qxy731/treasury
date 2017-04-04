<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工机构变更</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.inbox ,.outbox {padding: 5px;margin: 2px;border: 1px solid #ccc;}
.params td {background-color: #E2EAFF; padding:2px 2px 2px 10px;}
#reset {width: 80px;}
body {
	margin-top: 10px;
}
</style>
</head>
<body>
<n:page action='com.soule.app.sys.orgchange.OrgchangeAction' />

<table class='content'>
<tr><td>
	
		<div id='orgChangelist'></div>
    
</td></tr>
</table>
</body>
<script type='text/javascript'>
$(function () {
	$("#myform").hide();
	Utils.validateInit();
    $("#staffId").val(getUrlVars()["staffId"]);
	//输出表格
	$("#orgChangelist").ligerGrid({
		enumlist:{dateNull:[[null,'至今']]},
		columns: [
			{ display: '员工编号', name: 'staffId', align: 'center',width:80 },
            { display: '员工姓名', name: 'staffName', align: 'center',width:80},
            { display: '所属机构', name: 'ownerUnitid', align: 'center'},
            { display: '所属机构', name: 'unitName', align: 'center',width:80},
            { display: '修改人', name: 'updUserName', align: 'center',width:80},
            { display: '开始时间', name: 'startTime', align: 'center'},
            { display: '结束时间', name: 'endTime', align: 'center',codetype:'dateNull'}
		],
		pageSize:10,
		sortName: 'startTime',
		width: '687px',
		height: '300px',
		dataAction:'server',
        dataType:'server',
        url: '${_CONTEXT_PATH}/sys/orgchange!query.action',
        newPage:1,
        parms:[{name:'queryIn.orgChange.staffId',value:getUrlVars()['staffId']}],
        onError: function() {
            $.dialogBox.error("查询数据失败");
        }
	});
	//execute();
});
//获取url参数
function getUrlVars()  
{  
    var vars = [], hash;  
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');  
    for(var i = 0; i < hashes.length; i++)  
    {  
        hash = hashes[i].split('=');  
        vars.push(hash[0]);  
        vars[hash[0]] = hash[1];  
    }  
    return vars;  
} 

</script>
</html>