<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工作台</title>
<jsp:include page="/comm_debug.jsp"></jsp:include>
<script type='text/javascript' src='${_CONTEXT_PATH}/jwebui/charts/FusionCharts.js'></script>
<link href="${_CONTEXT_PATH}/jwebui/widget/widget.css" rel="stylesheet" />
<script src="${_CONTEXT_PATH}/jwebui/widget/widget_panel.js" type="text/javascript" ></script>
<script src="${_CONTEXT_PATH}/jwebui/widget/def_widget.js" type="text/javascript" ></script>
<script src="${_CONTEXT_PATH}/jwebui/widget/chart_widget.js" type="text/javascript" ></script>
<script src="${_CONTEXT_PATH}/jwebui/widget/calc_widget.js" type="text/javascript" ></script>
<script src="${_CONTEXT_PATH}/jwebui/widget/rolegrid_widget.js" type="text/javascript" ></script>
<style type="text/css">
body { font-family:Verdana; font-size:14px; margin:0;}
.bt {
	width:50px;
	/*background-color: #EAF2FE;*/
}
#ipts{
	width:350px;
	text-align: right;
}
.bt2{
	width:110px;
	/*background-color: #EAF2FE;*/
}
.c-button {
    width: 35px;
}
.bt,.bt2 {
    background: url("../../../images/controls/button-bg.gif") repeat-x scroll center center #E0EDFF;
    border: 1px solid #A3C0E8;
    color: #2C4D79;
    cursor: pointer;
    display: block;
    height: 20px;
    line-height: 20px;
    overflow: hidden;
    text-align: center;
    text-decoration: none;
    width: 50px;
}
.tableStype{width: 100%;margin: 3,3,3,3;}
.tableStype th{
font-size:12px;
text-align: center;
width:20%;
vertical-align: center;
height: 18px;
font-weight:bold;
background:#fff url('../../images/header-bg.gif');
border-bottom: 1px solid #A3C0E8;
border-top: 1px solid #A3C0E8;}
.tableStype td{
text-align: center;
vertical-align: center;
font-size:12px;
height: 23px;
border-bottom-style: dashed ;
border-bottom-width: 1px;
border-bottom-color: #A3C0E8}
.tableStype>thead>tr>th:first-child{width:75%;}
.tableStype>tbody>tr>td:first-child{
background:#fff url('../../images/login.gif') no-repeat left center;
text-align: left;padding-left: 20px}
 /**/
.tableStype1{width: 100%;margin: 3,3,3,3;}
.tableStype1 th{
font-size:12px;
text-align: center;
width:21%;
vertical-align: center;
height: 18px;
font-weight:bold;
background:#fff url('../../images/header-bg.gif');
border-bottom: 1px solid #A3C0E8;
border-top: 1px solid #A3C0E8;}
.tableStype1 td{
text-align: center;
vertical-align: center;
font-size:12px;
height: 23px;
border-bottom-style: dashed ;
border-bottom-width: 1px;
border-bottom-color: #A3C0E8}
.tableStype1>thead>tr>th:first-child{width:15%;}
.tableStype1>tbody>tr>td:first-child{
background:#fff url('../../images/login.gif') no-repeat left center;
text-align: left;
padding-left: 10px}
.divstyle1{
padding-left:20px;
background:#fff url('../../images/login.gif') no-repeat left center;
}
.fontcss1{
 font-size: 14px;
 font-weight:bold;
 color:#D65929;
}
.param {margin-top: 2px;}
.param tbody tr td {ine-height: 22px;font-size:11px;padding:1px 1px 1px 10px;}
.param input[type=text],.param select { border:1px solid #b3bcFF;border-radius:2px 2px 2px 2px;vertical-align: bottom;}
.param select {height:22px;width:138px;padding-bottom: 1px;}
.param input[type=text] {height:16px;width:135px;}

.tableStype2{width: 100%;margin: 3,3,3,3;}
.tableStype2 th{
font-size:12px;
text-align: center;
width:15%;
vertical-align: center;
height: 18px;
font-weight:bold;
background:#fff url('../../images/header-bg.gif');
border-bottom: 1px solid #A3C0E8;
border-top: 1px solid #A3C0E8;}
.tableStype2 td{
text-align: center;
vertical-align: center;
font-size:12px;
height: 23px;
border-bottom-style: dashed ;
border-bottom-width: 1px;
border-bottom-color: #A3C0E8}
.tableStype2>thead>tr>th:first-child{width:35%;}
.tableStype2>tbody>tr>td:first-child{
background:#fff url('../../images/login.gif') no-repeat left center;
text-align: left;
padding-left: 10px}
</style>
</head>
<n:page action='com.soule.crm.sys.app.homepage.HomepageAction' initMethod="initWidget"/>
<n:enums keys='prd_type,remind_type,prd_remindevent,careevent_type,caution_type,mktevent_type,taskevent_type'/>
<body>
<div id="container">
  <div id="panelcontent"></div>
</div>
</body>
<script type="text/javascript">
 
$(function (){
    // 1,2,3|1,2,3
    //var layoutData = [
    //    [{id:'test',title:'计算器',type:'calc',model:{"name":"Haha"}},
    //     {id:'chart1',title:'图形显示-饼图',type:'chart',parama:'Pie2D',model:{"name":"Hah1a" }}],
    //    [{id:'21',title:'图形显示-柱状图',type:'chart',parama:'Line',model:{"name":"normal",chartType:"Column2D"}},
    //     {id:'22',title:'角色展示',type:'rolegrid',model:{}}],
    //   [{id:'31',model:{}},{id:'34',model:{}}]
    //];
    var layoutData = ${initStr};
    var widgets = ${widgetsStr};
    var iWidth = screen.availWidth; 
    var clWidth = 300;
    if(iWidth>1024){
    	clWidth+=Math.ceil((iWidth-1024) / 3);
    }
    $('#panelcontent').attr("style","width:"+iWidth);
    $('#panelcontent').zWidgetPanel({
        layoutData:layoutData,
        widgetsData:widgets,
        colwidth:clWidth,
        onLayoutChange:function (str,wdiv){
            var url = "${_CONTEXT_PATH}/sys/homepage!modify.action";
            var mdata = {"modifyIn.layoutData":str};
            Utils.ajaxSubmit(url,mdata,function(){});
        }
    });
    $('.p-title .p-close').hide();
});
var ss=1;

function showHomepageConfig(){
	$('.widgetlist').slideToggle("fast",function(){
		if(this.style.display=='none'){
			$('.addbuttons').css('border-bottom-width','0');
			$('.p-title .p-close').hide();
			$('.p-title .p-more').removeClass('p-moreconf');
		}else if(this.style.display=='block'||this.style.display==''){
			$('.addbuttons').css('border-bottom-width','1px');
			$('.p-title .p-close').show();
			$('.p-title .p-more').addClass('p-moreconf');
		}
	});
	
}

</script>
</html>