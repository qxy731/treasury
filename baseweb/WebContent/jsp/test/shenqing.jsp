<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>�������ҳ��</title>
<link href="${_CONTEXT_PATH}/jwebui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<script src="${_CONTEXT_PATH}/jwebui/jquery/jquery-1.4-dev.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/js/Utils.js" type="text/javascript"></script> 
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
<style type="text/css">
.inbox ,.outbox {padding: 5px;margin: 2px;border: 1px solid #ccc;}
.params td {background-color: #E2EAFF; padding:2px 2px 2px 10px;}
#reset {width: 80px;}
</style>
</head>
<body>
<n:page action='com.soule.dborg.WorkFlowAction' />
<form id="myform" >
<fieldset class="inbox"><legend>��������</legend>
    <table class='params'>
        <tr>
            <td>������� </td><td><input type='text' id='days' name='days' value="${days}"/></td>
        </tr>
        <tr>
            <td>������� </td><td><input type='text' id='reason' name='reason'/></td>
        </tr>
    </table>
</fieldset>
</form>
<div>
    <input id='save' name='save' type='button' value='����'/>
    <input id='commit' name='commit' type='button' value='�ύ'/>
</div>

</body>
<script type='text/javascript'>
	$(function () {
	    $('#save').bind('click', save);
	    $('#commit').bind('click', doCommit);
	});
    function doCommit() {
        var mdata = Utils.convertFormData('queryIn','myform');
        var url = "${_CONTEXT_PATH}/WorkFlow!commit.action";
        mdata['wfid'] = '${wfid}';
        mdata['pcid'] = '${pcid}';
        $.ajax ({
            url: url,
            type:'POST',
            data: mdata,
            success: function(result){
                $.ligerDialog.alert(result.retMsg,'��ʾ����','info');
            },
            error:function(result){
                $.ligerDialog.alert("ϵͳ����",'��ʾd����','error');
            }
        });
    }
    
    function save() {
        //����¼����
        var mdata = Utils.convertFormData('queryIn','myform');
        var url = "${_CONTEXT_PATH}/WorkFlow!save.action";
        mdata['queryIn.wfid'] = '${wfid}';
        mdata['queryIn.pcid'] = '${pcid}';
        $.ajax ({
            url: url,
            type:'POST',
            data: mdata,
            success: function(result){
                $.ligerDialog.alert(result.retMsg,'��ʾ����','info');
            },
            error:function(result){
                $.ligerDialog.alert("ϵͳ����",'��ʾd����','error');
            }
        });
    }

</script>
</html>