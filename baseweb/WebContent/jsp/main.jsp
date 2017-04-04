<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% session.setAttribute("SkinType", "sys"); //切换皮肤%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link rel="shortcut icon" href="${_CONTEXT_PATH}/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="${_CONTEXT_PATH}/jwebui/zTree/css/zTreeStyle/zTreeStyle.css" />
<jsp:include page="/comm.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${_CONTEXT_PATH}/jsp/main.css" />
<script type="text/javascript" src="${_CONTEXT_PATH}/jwebui/zTree/js/jquery.ztree.core-3.0.min.js"></script>
<script type="text/javascript" >
$(document).ready(function() {
	$('#quickLink').hide();
	showTime();
	var zNodes=${zNodes};
	$.fn.zTree.init($("#treespgroup"), setting, zNodes);
});
var tab = null;
var accordion = null;
$(function (){
    //布局
    $("#layout1").ligerLayout({ leftWidth: 200,topHeight:64, height: '100%', bottomHeight:20,onHeightChanged: f_heightChanged,isLeftCollapse: false,allowBottomResize: false, allowTopResize:false });
    var height = $(".l-layout-center").height();
    //Tab
    $("#framecenter").ligerTab({ height: height });
   //面板
    $("#accordion1").ligerAccordion({ height: height - 24, speed: null });
    $(".l-link").hover(function (){
        $(this).addClass("l-link-over");
    }, function (){
        $(this).removeClass("l-link-over");
    });
    tab = $("#framecenter").ligerGetTabManager();
    accordion = $("#accordion1").ligerGetAccordionManager();
    $("#pageloading").hide();
});
function f_heightChanged(options){
    if (tab)tab.addHeight(options.diff);
    if (accordion && options.middleHeight - 24 > 0)
        accordion.setHeight(options.middleHeight - 24);
}
function f_addTab(tabid,text, url){
	try{
		tab.addTabItem({ tabid : tabid,text: text, url: url });
	}catch(e){
	}finally{
	}
}

var setting = {
		check: {enable: false},
		data: {simpleData: {enable: true}},
		view: {expandSpeed: ""},
		callback:{onClick:zTreeOnClick}
};

//树的click事件
function zTreeOnClick(event, treeId, treeNode) {	
	if(treeNode.href==undefined||treeNode.href==""){
		return;
	}
	try{		
		f_addTab(treeNode.tId , treeNode.name, treeNode.href);		
	}catch(e){	
		//alert(e);
	}finally{
		//alert(2);
	}	
}


function showTime(){
	var date=new Date();
	var now="";
	now=now+date.getFullYear()+"-";
	now=now+(date.getMonth()+1)+"-";
	now=now+date.getDate()+" ";
	if (date.getHours() < 10){
		now = now + "0";
	}
	now=now+date.getHours()+":";
	if (date.getMinutes() < 10){
		now = now + "0";
	}
	now=now+date.getMinutes()+":";
	if (date.getSeconds() < 10){
		now = now + "0";
	}
	now=now+date.getSeconds();
	document.getElementById("now").innerHTML=now;
	setTimeout("showTime()",1000);
}

function toTree() {
	$('#treespgroup').show();
	$('#quickLink').hide();
}
function toQuick() {
	$('#treespgroup').hide();
	$('#quickLink').show();
}
function logout() {
	$.dialogBox.confirm('确定要退出吗?',function () {doLogout();});
}
function doLogout() {
	window.location= "${_CONTEXT_PATH}/j_acegi_logout";
}
function changePosition() {
	var url = '${_CONTEXT_PATH}/jsp/public/changePosition.jsp';
	$.dialogBox.openDialog(url,'职位切换');
}
</script>
</head>
<body style="padding:0;" >
<div id="pageloading"></div>
	<div id="layout1">
		<div position="top" id="headcontent">
			<div class="lefthead" >
			</div>
			<div class="righthead">
				<ul>
				<li><span id="now"></span></li><br/>
				<li><span onclick="logout()" ><img src="${_CONTEXT_PATH}/images/reset.png" title="注销"/></span></li>
				<s:if test="%{#session.logUserInfo.positionPo.size()>1}">
					<li><span onclick="changePosition()" ><img src="${_CONTEXT_PATH}/images/chang.png" title="切换职位"/></span></li>
				</s:if>
				<li><span><img src="${_CONTEXT_PATH}/images/admin.png" 
					title="<s:property value="#attr.logUserInfo.getBizLineValue('ENT_PSN').bizName"/> ${logUserInfo.operUnitName}(${logUserInfo.operUnitId})&nbsp;${logUserInfo.roleName}:${logUserInfo.user.userName}" />
					</span></li>
				<li><span onclick="changePassword()"><img src="${_CONTEXT_PATH}/images/password.png" title="修改密码"/></span></li>
				</ul>
			</div>
		</div>
		<div position="left" title="<a onclick='toTree()'>菜单</a>&nbsp;|&nbsp;<a onclick='toQuick()'>快捷</a>" id="accordion1" >
			<ul id="treespgroup" class="ztree"></ul>
			<ul id='quickLink'>
				<div class='quick' style="background: url('images/icons/caifu.png') no-repeat center center;"></div>
				<div class='quick' style="background: url('images/icons/chaxun.png') no-repeat center center;"></div>
				<div class='quick' style="background: url('images/icons/chaxungongsi.png') no-repeat center center;"></div>
				<div class='quick' style="background: url('images/icons/chuangjian.png') no-repeat center center;"></div>
				<div class='quick' style="background: url('images/icons/danmubiao.png') no-repeat center center;"></div>
				<div class='quick' style="background: url('images/icons/gonggao.png') no-repeat center center;"></div>
			</ul>
		</div>
		<div position="center" id="framecenter">
			<div tabid="home" title="我的主页">
				<iframe frameborder="0" name="home" src="" ></iframe>
			</div>
		</div>
		<div position="bottom">
			Copyright © 2012
		</div>
	</div>
		<div style="display:none">
	</div>
</body>
</html>