<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单设置</title>
<%-- <s:head theme="ajax" /> --%>
<jsp:include page="/comm.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${_CONTEXT_PATH}/jwebui/zTree/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="${_CONTEXT_PATH}/jwebui/zTree/js/jquery.ztree.core-3.0.js"></script>
<script type="text/javascript" src="${_CONTEXT_PATH}/jwebui/zTree/js/jquery.ztree.excheck-3.0.js"></script>
<script type="text/javascript" src="${_CONTEXT_PATH}/jwebui/zTree/js/jquery.ztree.exedit-3.0.js"></script>
<script type="text/javascript" src="${_CONTEXT_PATH}/jsp/sysmgr/menu/menu.js"></script>
<style type="text/css">
.params {margin:3px;}
.params tbody tr td {padding-left:10px;padding:0px 2px 2px 10px;line-height: 22px;background-color: #fff;}
#rbtnl_3,#rbtnl_4,#rbtnl_5,#rbtnl_6 {width: 16px;}
div#rMenu {	position: absolute;	visibility: hidden;background-color: #555;text-align: center;padding: 1px;}
div#rMenu ul li {min-width:100px;margin: 1px 0;padding: 0 5px;cursor: pointer;list-style: none outside none;background-color: #EEEEEE;}
div#rMenu ul li:hover{background-color: #EEEE99;}
.l-table-edit {font-size: 12px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test,.l-button-reset {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
#accordion2 table tr td:first-child {text-align: left;}
#accordion2 table tr td {padding: 4px 2px 2px 4px;}
#accordion2 table tr td input:focus {/*border: 1px solid #ff9911;*/}
</style>
<script type="text/javascript">

$( function() {
    //面板
    var height = $(window).height();
    $("#accordion1").ligerAccordion( {height : height,speed : null});
    $("#accordion2").ligerAccordion( {height : height,speed : null});
    $(window).resize( function() {
        var accordion = $("#accordion1").ligerGetAccordionManager();
        var height = $('#mybody').height();
        accordion.setHeight(height);
        accordion = $("#accordion2").ligerGetAccordionManager();
        accordion.setHeight(height);
    });
});

$(document).ready(function(){
    var sysZNodes = ${sysZNodes};
    var cusZNodes = ${cusZNodes};
    $.fn.zTree.init($("#sysTree"),setting,sysZNodes);
    $.fn.zTree.init($("#cusTree"), setting, cusZNodes);
});

$(function() {
    Utils.validateInit();
});

function doSubmit() {
    var v = $("#form1").valid();
    if (v) {
        var mdata = Utils.convertFormData('','form1');
        Utils.ajaxSubmit("${_CONTEXT_PATH}/sys/menu!saveMenu.action",mdata);
    }
}
function doReset() {
    $('#node_name').val("");
    $('#nodeCmd').val("");
    $('#seqId').val(0);
    $('#nodeTooltip').val("");
    $("input[name='hasChildFlag'][value=0]").attr("checked",true);
    $('#nodeUrl').val('');
    $("input[name='nodeVisible'][value=1]").attr("checked",true);
    $("input[name='relaFlag'][value=0]").attr("checked",true);
    $('#nodeImg').val('');
    $('#nodeTarget').val('');
}
</script>
</head>
<body style="overflow: hidden;" id="mybody">
<table cellpadding="5" width="100%" >
	<tr>
		<td width="300">
		<div title="菜单列表" id="accordion1">
		<div title="系统菜单">
			<ul id="sysTree" class="ztree"></ul>
		</div>
		<div title="自定义菜单">
		<ul id="cusTree" class="ztree"></ul>
		</div>
		</div>
		<div id="rMenu">
		<ul>
			<li id="m_add" onclick="addTreeNode();" >增加节点</li>
			<li id="m_del" onclick="removeTreeNode();">删除节点</li>
			<li id="m_add_tree" onclick="addTreeNode();">增加菜单</li>
			<li id="m_del_tree" onclick="removeTreeNode();">删除菜单</li>
		</ul>
		</div>
		</td>
		<td id="accordion2">
		<div title="菜单详情">
		<form id="form1" method="post" action="sys/menu!saveMenu.action" name="form1">
		<table class="params">
		    <input id="hasChildFlag" name="hasChildFlag" type="hidden"/>
			<tr>
				<td nowrap="nowrap" align="left">菜单编号:</td>
				<td><input id="menuId" name="menuId"
					type="text" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>节点编码:</td>
				<td><input id="nodeId" name="nodeId" 
					type="text" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>节点名称:</td>
				<td><input id="node_name" name="node_name" type="text" 
					validate="{required:true,minlength:2,maxlength:64}" /></td>
			</tr>
			<tr>
				<td>节点路径:</td>
				<td><input id="nodeUrl" name="nodeUrl" type="text" validate="{maxlength:64}"
					style="width: 400px" /></td>
			</tr>
			<tr>
				<td>父节点:</td>
				<td><input id="parentId" name="parentId" type="text" validate="{maxlength:16}" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>顺序号:</td>
				<td><input id="seqId" name="seqId" type="text"
					validate="{required:true,digits:true}" /></td>
			</tr>
			<tr>
				<td>节点图标:</td>
				<td><input id="nodeImg" name="nodeImg" type="text" validate="{maxlength:128}" /></td>
			</tr>
			<tr>
				<td>是否相对路径:</td>
				<td>
					<input id="rbtnl_3" type="radio" name="relaFlag" value="1" /><label for="rbtnl_3">是</label>
					<input id="rbtnl_4" type="radio" name="relaFlag" value="0" /><label for="rbtnl_4">否</label>
				</td>
			</tr>
			<tr>
				<td>执行目标:</td>
				<td><input id="nodeTarget" name="nodeTarget" type="text" validate="{maxlength:10}" /></td>
			</tr>
			<tr>
				<td>执行命令:</td>
				<td><input id="nodeCmd" name="nodeCmd" type="text" validate="{maxlength:128}" /></td>
			</tr>
			<tr>
				<td>可视标志:</td>
				<td>
					<input id="rbtnl_5" type="radio" name="nodeVisible" value="1" /><label for="rbtnl_5">是</label>
					<input id="rbtnl_6" type="radio" name="nodeVisible" value="0" /><label for="rbtnl_6">否</label>
				</td>
			</tr>
			<tr>
				<td>节点提示信息:</td>
				<td><textarea id="nodeTooltip" name="nodeTooltip" cols="100"
					rows="3" style="width: 400px" validate="{maxlength:128}"></textarea></td>
			</tr>
			<tr>
				<td align="center" style="text-align: center; align: center;"></td>
				<!--
				<td align="center" style="text-align: center; align: center;">
					<input type="button" value="提交" id="Button1" class="l-button" onclick="doSubmit();"/>
					<input type="button" value="重置" class="l-button" onclick="doReset()"/>
				</td>
				-->
				<td align="center" style="text-align: center; align: center;">
				<div>
				    <table>
				       <tr>
				         <td>
				         <input type="button" value="提&nbsp;&nbsp;&nbsp;&nbsp;交" id="Button1" class="l-button" onclick="doSubmit();"/>
				         </td>
				         <td>
				         <input type="button" value="重&nbsp;&nbsp;&nbsp;&nbsp;置" class="l-button" onclick="doReset()"/>
				         </td>
				      </tr>
				     </table>
				 </div>
				</td>
			</tr>
		</table>
		</form>
		</div>
		</td>
	</tr>
</table>
</body>

</html>