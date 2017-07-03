<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>功能权限资源新增</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
/*body {width:400px;}*/
body {
    width:400px;margin-top:10px;margin-bottom: 20px 
}
.div_bottom {margin-top: 10px}
.table_btn td{padding: 1px 20px 1px 20px}
.fclass {color:#FF0000}
</style>
</head>
<body>
<n:page action='com.soule.app.sys.funcauth.FuncAuthAction'/>
<form id="insertForm" >
<table class='s1-params'>
    <tr>
        <td>页面路径</td>
        <td><input id="jspPath" name="news.jspPath" readonly="readonly" value='${jsppath}'></input></td>
    </tr>
    <tr>
        <td><font class='fclass'>*&nbsp;</font>功能编码 </td>
        <td><input id="funcId" name="news.funcCode" validate="{required:true,maxlength:64}"/></td>
    </tr>
    <tr>
        <td><font class='fclass'>*&nbsp;</font>功能名称 </td>
        <td><input id="funcName" name="news.funcName" validate="{required:true,maxlength:64}"></input></td>
    </tr>
    <tr>
        <td>功能描述 </td>
        <td><input id="funcDesc" name="news.funcDesc" ></input></td>
    </tr>
</table>
<div align="center" class="div_bottom">
    <table class="table_btn">
        <tr>
           <td><input id="commit" type="button" value="提&nbsp;交" class="l-button"></input></td>
           <td><input id="cancel" type="button" value="取&nbsp;消" class="l-button"> </td>
        </tr>
    </table>
</div>
<!--<table class="s1-button">
	<tr>
		<td>
			<input id="commit" type="button" value="提&nbsp;交" class="nbutton"></input>
		</td>
		<td>
			<input id="cancel" type="button" value="取&nbsp;消" class="nbutton"></input>
		</td>
	</tr>
</table>-->
</form>

</body>
<script type='text/javascript'>
$(function() {
    Utils.validateInit();
    $('#commit').bind('click', insert);
    $('#cancel').bind('click', cancelDialog);
});
    function doClear() {
        $(".inbox input[type='text']").each(function(i,item){
            item.value ='';
        });
    }

    function insert() {
        var v = $("#insertForm").valid();
        if (!v){
            return;
        }
        //单记录数据
        var mdata = Utils.convertFormData('insertIn','insertForm');
        var url = "${_CONTEXT_PATH}/FuncAuth!insert.action";
        Utils.ajaxSubmit(url,mdata, function(result){
            $.dialogBox.alert(result.retMsg);
            $.dialogBox.opener.showFuncDetail($('#jspPath').val());
            $.dialogBox.close();
            },
            true);
    }
    function cancelDialog(){
    	$.dialogBox.close();
    }
</script>
</html>