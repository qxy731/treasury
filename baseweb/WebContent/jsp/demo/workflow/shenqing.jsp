<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假申请页面</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.inbox ,.outbox {padding: 5px;margin: 2px;border: 1px solid #ccc;}
.params tbody tr td {background-color: #E2EAFF; padding:2px 2px 10px 10px;}
#reset {width: 80px;}
#reason {width: 400px;height:80px;}
.content1 {width:500px;margin:10px auto;}
.l-button {display: inline;}
#save ,#commit {margin:10px 100px;}
</style>
</head>
<body>
<n:page action='com.soule.app.flow.work.WorkFlowAction' initMethod="spInit"/>

<table class='content1'>
	<tr><td>
		<form id="myform" >
		<fieldset class="queryBox"><legend>请假申请</legend>
		    <table class='params'>
		        <tr>
		            <td>请假天数 </td><td><input type='text' id='days' name='days' value="${days}"/></td>
		        </tr>
		        <tr>
		            <td>请假事由 </td><td><textarea id='reason' name='reason' >${reason}</textarea></td>
		        </tr>
		        <tr>
		            <td colspan="2" nowrap><input id='save' name='save' type='button' value='创&nbsp;&nbsp;建' class='l-button'/><input id='commit' name='commit' type='button' value='提&nbsp;&nbsp;交' class='l-button'/>
		            </td>
		        </tr>
		    </table>
		</fieldset>
		</form>
	</tr></td>
</table>
</body>
<script type='text/javascript'>
	$(function () {
	    $('#save').bind('click', save);
	    $('#commit').bind('click', doCommit);
	    var wfid = '${wfid}';
	    if (wfid == '') {
	    	$('#save').show();
	    	$('#commit').hide();
	    }
	    else {
	    	$('#save').hide();
	    	$('#commit').show();
	    }
	});
    function doCommit() {
        var mdata = Utils.convertFormData('','myform');
        var url = "${_CONTEXT_PATH}/flow/work-flow!commit.action";
        mdata['wfid'] = '${wfid}';
        mdata['pcid'] = '${pcid}';
        Utils.ajaxSubmit(url,mdata,function(result){
        	$.dialogBox.info(result.retMsg,function(){
        		parent.tab.removeSelectedTabItem();
        	});
        });
    }
    
    function save() {
        var mdata = Utils.convertFormData('','myform');
        var url = "${_CONTEXT_PATH}/flow/work-flow!save.action";
        mdata['wfid'] = '${wfid}';
        mdata['pcid'] = '${pcid}';
        Utils.ajaxSubmit(url,mdata,function(result){
        	$.dialogBox.info(result.retMsg,function(){
        		parent.tab.removeSelectedTabItem();
        	});
        });
    }

</script>
</html>