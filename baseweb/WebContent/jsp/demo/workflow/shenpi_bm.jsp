<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假审批页面</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.inbox ,.outbox {padding: 5px;margin: 2px;border: 1px solid #ccc;}
.params tbody tr td {background-color: #E2EAFF; padding:2px 2px 10px 10px;}
#reset {width: 80px;}
#reason,#refuse {width: 400px;height:80px;}
.content1 {width:500px;margin:10px auto;}
.l-button {display: inline;}
#submit {margin:0 10px 0 100px;}
</style>
</head>
<body>
<n:enums keys="yes_no"></n:enums>
<n:page action='com.soule.app.flow.work.WorkFlowAction' initMethod="spInit"/>
<table class='content1'>
	<tr><td>
		<form id="myform" >
		<fieldset class="queryBox"><legend>请假申请审批</legend>
		    <table class='params'>
		        <tr>
		            <td>请假天数 </td><td><input type='text' id='days' name='days' value="${days}" readonly="readonly"/></td>
		        </tr>
		        <tr>
		            <td>请假事由 </td><td><textarea id='reason' name='reason' readonly="readonly">${reason}</textarea></td>
		        </tr>
		        <tr>
		            <td>审批结果 </td><td><n:select id="result" codetype="yes_no"/></td>
		        </tr>
		        <tr>
		            <td>审批意见</td><td><textarea id='refuse' name='refuse'></textarea></td>
		        </tr>
		        <tr>
		            <td colspan="2" nowrap><input id='submit' name='submit' type='button' value='提交' class='l-button'/><input id='cancel' name='cancel' type='button' value='取消'' class='l-button'/>
		            </td>
		        </tr>
		    </table>
		</fieldset>
	</tr></td>
</table>

</body>
<script type='text/javascript'>
    $(function () {
        $('#submit').bind('click', doSubmit);
        //$('#cancel').bind('click', doSubmit);
    
    });
    function doSubmit() {
    	//单记录数据
        var mdata = Utils.convertFormData('queryIn','myform');
        mdata['wfid'] = '${wfid}';
        mdata['pcid'] = '${pcid}';
        var url = "${_CONTEXT_PATH}/flow/work-flow!shenpi.action";
        Utils.ajaxSubmit(url,mdata,function(result){
        	$.dialogBox.info(result.retMsg,function(){
        		parent.tab.removeSelectedTabItem();
        	});
        });
    }

</script>
</html>