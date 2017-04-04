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
.params td {background-color: #E2EAFF; padding:2px 2px 2px 10px;}
#reset {width: 80px;}
</style>
</head>
<body>
<n:enums keys="yes_no"></n:enums>
<form id="myform" action="${_CONTEXT_PATH}/Test!query.action">
<fieldset class="inbox"><legend>输入条件</legend>
    <table class='params'>
        <tr>
            <td>请假天数 </td><td><input type='text' id='days' name='days'/></td>
        </tr>
        <tr>
            <td>请假事由 </td><td><input type='text' id='reason' name='reason'/></td>
        </tr>
        <tr>
            <td>审批结果 </td><td><n:select id="result" codetype="yes_no"/></td>
        </tr>
        <tr>
            <td>不通过说明 </td><td><input type='text' id='refuse' name='refuse'/></td>
        </tr>
    </table>
</fieldset>
</form>
<div>
    <input id='submit' name='submit' type='button' value='提交'/>
    <input id='cancel' name='cancel' type='button' value='取消'/>
</div>
</body>
<script type='text/javascript'>
    $(function () {
        $('#submit').bind('click', doSubmit);
        $('#cancel').bind('click', doSubmit);
    
    });
    function doSubmit() {
    	//单记录数据
        var mdata = Utils.convertFormData('queryIn','myform');
        mdata['workItemId'] = '$(workItemId)';
        var url = "${_CONTEXT_PATH}/WorkFlow!shenpi.action";
        $.ajax ({
            url: url,
            type:'POST',
            data: mdata,
            success: function(result){
                $.ligerDialog.alert(result.retMsg,'提示内容','info');
            },
            error:function(result){
                $.ligerDialog.alert("系统出错",'提示d内容','error');
            }
        });
    }
    
    function execute() {
        //单记录数据
        var mdata = Utils.convertFormData('queryIn','myform');
        var url = "${_CONTEXT_PATH}/WorkFlow!qingjia.action";
        $.ajax ({
            url: url,
            type:'POST',
            data: mdata,
            success: function(result){
                $.ligerDialog.alert(result.retMsg,'提示内容','info');
            },
            error:function(result){
                $.ligerDialog.alert("系统出错",'提示d内容','error');
            }
        });
    }

</script>
</html>