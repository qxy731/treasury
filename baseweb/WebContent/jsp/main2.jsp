<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% session.setAttribute("SkinType", "sys"); //切换皮肤%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>国库数据库指标系统</title>
<link rel="shortcut icon" href="${_CONTEXT_PATH}/favicon.ico" type="image/x-icon" />
<jsp:include page="/comm.jsp"></jsp:include>
<script src="${_CONTEXT_PATH}/js/menuhtml.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${_CONTEXT_PATH}/jsp/main2.css" />
<STYLE type="text/css">

</STYLE>
<script type="text/javascript">
$(document).ready(function() {
    //showTime();
    //var zNodes=${zNodes};
    //$.fn.zTree.init($("#treespgroup"), setting, zNodes);
});
var tab = null;
var accordion = null;
var newaHeight = null;
var subcount=0;
var rmdcount=0;

$(function (){
	//只选择第一个角色
	var orgAndRole=$('#orgAndRole').html();
	$('#orgAndRole').html(orgAndRole.substring(0,orgAndRole.indexOf(']')+1)+orgAndRole.substring(orgAndRole.lastIndexOf(']')+1));
    //布局
    $("#layout1").ligerLayout({ 
        topHeight:78,
        height: '100%',
        bottomHeight:20,
        onHeightChanged: f_heightChanged
    });
    var height = $(".l-layout-center").height();
    //Tab
    $("#framecenter").ligerTab({
        height: height
    });
   //面板
   /*  $("#accordion1").ligerAccordion({
        height: height - 24,
        speed: null
    }); */
    $(".l-link").hover(
        function (){
            $(this).addClass("l-link-over");
        }, 
        function (){
            $(this).removeClass("l-link-over");
        }
    );
    tab = $("#framecenter").ligerGetTabManager();
    //accordion = $("#accordion1").ligerGetAccordionManager();
    $("#pageloading").hide();

    //查询快捷菜单
    var url2 = "${_CONTEXT_PATH}/sys/quick-menu!query.action";
    Utils.ajaxSubmit(url2, null, onSuccess2);

    newaHeight = document.body.clientHeight+25;
    //addEvent(window,'resize',chgheight,document);
    
    $("#framecenter").bind('mouseover', 
    	function (){
    	    $(".l-menu,.l-menu-shadow").hide();
        }
    );
    $(".gn_btn").click(function(){queryEhrCust();})
    $("#ehrcustId").keydown(function(e){
    	var curKey = e.which;
    	if(curKey==13){
    		$(".gn_btn").click();
    		return false;
    	}
    });//follow,follow:$('#topmenu')
    
    $('#framecenter .l-tab-links ul li:first').append("<div class='l-tab-links-item-home'></div>");
    $('.l-tab-links .l-tab-links-item-home').click(function() {
    	$("#home")[0].contentWindow.showHomepageConfig();
    });
});

function openSubQuery(){
	Utils.openTab('recesubquery','收件箱',_CONTEXT_PATH+'/jsp/pub/sub/recesubquery/recesubquery_query.jsp');
	$('#subdiv').hide();
	//$('#subdivhide').show();
}

function openRmdQuery(){
	Utils.openTab('remindinfo','提醒信息查询',_CONTEXT_PATH+'/jsp/mkt/remind/remindinfo/remindinfo_query.jsp');
	$('#reminddiv').hide();
}

function onSuccess2(ret) {
    /* var g1rows = ret.rows;
    var str = "";
    for(var i=0;i<g1rows.length;i++) {
        var row = g1rows[i];
        var imgstr = getShortCutIconUrl(row.NODE_IMG);
        str += "<div class=\"quick\" style=\"background: url('"+imgstr+"') no-repeat top center;padding-bottom: 15px;\" onclick=\"goTopage('"+row.MENU_ID+"','"+row.NODE_NAME+"','"+row.NODE_URL+"')\"><div title=\""+row.NODE_NAME+"\" style=\"padding-top: 45px;text-align: center;cursor: pointer;\"></div></div>";
    }
    str+="<div class=\"quick\" id=\"allmenu\" style=\"background: url('${_CONTEXT_PATH}/images/icons/22.png') no-repeat top center;padding-bottom: 15px; \" ><div title=\"菜单\" style=\"padding-top: 45px; text-align: center;cursor: pointer;\"></div></div>";
    $("#accordion1").append(str); */
    
    $("#allmenu").click(function (){
  			if($("#hh")[0].style.display=='none'){
  				$("#hh")[0].style.display='';
  			}else{
  				$("#hh")[0].style.display='none';
  			}
    });
}
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

//树的click事件
function zTreeOnClick(event, treeId, treeNode) {
    if(treeNode.href==undefined||treeNode.href==""){
        return;
    }
    try{
        f_addTab(treeNode.tId , treeNode.name, treeNode.href);
    }catch(e){
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
    now=now+date.getMinutes();
    document.getElementById("now").innerHTML=now;
    setTimeout("showTime()",60000);
}

function logout() {
    $.dialogBox.confirm('确定要退出吗?',function () {doLogout();});
}
function doLogout() {
	window.location= "${_CONTEXT_PATH}/j_acegi_logout";
}
function changePosition() {
    var url = '${_CONTEXT_PATH}/jsp/public/changePosition.jsp';
    $.dialogBox.openDialog(url,{title:'职位切换',height:160,width:330,id:"modifyposition"});
}

function changePassword() {
    var url = '${_CONTEXT_PATH}/jsp/public/changePassword.jsp';
    $.dialogBox.openDialog(url,{title:'密码修改',height:240,width:390,id:"modifypassword"});
}

function itemclick(item) {
    if(item.url==undefined||item.url==""){
        return;
    }
    try{
        f_addTab(item.nodeId , item.text, item.url);
    }catch(e){}
}

$(function (){
    var menuJson =${menusJson};
    $("#topmenu").ligerMenuBar(menuJson);
    
    $("#hh").append(allmenuhtml);
    $("#mclose").bind('click',function(){$("#hh")[0].style.display='none';});
    var root = getMenutable(menuJson);
    $("#menushowid").append(root);
    
});

function  chgheight(){
	newaHeight = document.body.clientHeight+25;
}

function getShortCutIconUrl(img) {
	if (img == null) {
		return "";
	}
	var x = img.lastIndexOf('/');
	return "${_CONTEXT_PATH}/images/icons/" +img.substr(x+1);
}

function goTopage(MENU_ID,NODE_NAME,NODE_URL) {
    try{
        f_addTab(MENU_ID , NODE_NAME, NODE_URL);
    }catch(e) {
    }
}

function changeStaffInfo(){
	var url = '${_CONTEXT_PATH}/jsp/sysmgr/staff/staffExtUpdate.jsp?queryIn.staffId='+_CREATE_USER+'&homepage=yes';
	$.dialogBox.openDialog(url,'修改人员');
}
function getradiochk(obj){
	obj.checked=true;
}
function showehrcust(){
	if($("#ehrcust")[0].style.display=='none'){
		$("#ehrcust")[0].style.display='';
	}else{
		$("#ehrcust")[0].style.display='none';
	}
}
function setTab(n){
	 var tli=$("#menu4 li");
	 for(i=0;i<tli.length;i++){
	  tli[i].className=i==n?"o":"";
	 }
}

</script>
</head>
<body style="padding: 0;">
<input type="hidden" id="myparams" name="myparams" value="" />
<input type="hidden" id="myparams2" name="myparams2" value="" />
<div id="pageloading"></div>

<div id="layout1" style="width:100%">
<div position="top" id="headcontent">
<div class="righthead">
<ul>
    <li><span onclick="logout()">
    	<img  class="logout" src="${_CONTEXT_PATH}/images/logout.png" title = "注销" /></span></li>
    <s:if test="%{#session._ROLE_MIXED_ == false}">
    <s:if test="%{#session.logUserInfo.positionPo.size()>1 }">
        <li><span onclick="changePosition()"><img
            src="${_CONTEXT_PATH}/images/chang.png" title="切换职位" /></span></li>
    </s:if>
    </s:if>
   <%--  <li><span onclick="changeStaffInfo()"><img src="${_CONTEXT_PATH}/images/admin.png" title="我的信息"/></span></li> --%>
<%--     <li><span onclick="changePassword()"><img src="${_CONTEXT_PATH}/images/admin.png" title="修改密码"/></span></li> --%>
</ul>


</div>
    <div id="topmenu" >
        <div style="float:right;padding:5px;color:#fff;">
        <table style='border:0px;'>
        <tr>
        <td id='orgAndRole'>
        <s:property value="#attr.logUserInfo.getBizLineValue('ENT_PSN').bizName" />
        <s:if test="#attr.logUserInfo.roleNameString!=null" >${logUserInfo.roleNameString}:</s:if>${logUserInfo.user.userName}
        &nbsp;
        <td>
        <td>
        <div id='subdiv' title="未读站内信" style='display: none;'>
        <img alt="未读站内信" onclick='openSubQuery()' src="images/subbtn_ss.gif" style='margin-bottom:1px;cursor: pointer;'/>
        </div>
        </td>
        <td>
        <div id='reminddiv' title="未读提醒" style='display: none;'>
        <img alt="未读提醒" onclick='openRmdQuery()' src="images/remindbtn_ss.gif" style='margin-top:0px;cursor: pointer;'/>
        </div>
        </td>
        </tr>
        </table>
        </div>
    </div>
    
</div>
<div id="hh" style="display: none;"></div>
<!-- <div position="left" title="" id="accordion1"> </div>-->

<div position="center" id="framecenter">
<div tabid="home" title="我的主页"><iframe frameborder="0" name="home" id="home"
    src="${_CONTEXT_PATH}/jsp/sysmgr/mypage.jsp"></iframe></div>
</div>
<!-- <div position="bottom" >Copyright @ 2012 www.soule.com</div> -->
</div>
<div style="display: none"></div>
</body>
</html>