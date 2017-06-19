﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>动态页面</title>
<link href="${_CONTEXT_PATH}/jwebui/widget/widget.css" rel="stylesheet" type="text/css" />

<jsp:include page="/comm_debug.jsp"></jsp:include>
<script type="text/javascript" src="${_CONTEXT_PATH}/jwebui/jquery/jquery.fullscreenBackground.js"></script>

<script type="text/javascript">
        $(document).ready(function () {
            $("#background-image").fullscreenBackground();
            initAll();
        });


function initAll() {
    //TODO 从数据库取个人首页配置
    var initdatas = [
        {positionid:"p0",pageurl:"/jsp/demo/dpage/title.jsp"},
        {positionid:"p1",pageurl:"/jsp/demo/dpage/title.jsp"},
        {positionid:"p2",pageurl:"/jsp/demo/dpage/title.jsp"},
        {positionid:"p3",pageurl:"/jsp/demo/dpage/empty.jsp"},
        {positionid:"p4",pageurl:"/jsp/demo/dpage/empty.jsp"},
        {positionid:"p5",pageurl:"/jsp/demo/dpage/empty.jsp"},
        {positionid:"p6",pageurl:"/jsp/demo/dpage/empty.jsp"},
        {positionid:"p7",pageurl:"/jsp/demo/dpage/title.jsp"},
        {positionid:"p8",pageurl:"/jsp/demo/dpage/title.jsp"},
        {positionid:"p9",pageurl:"/jsp/demo/dpage/title.jsp"},
        {positionid:"p10",pageurl:"/jsp/demo/dpage/empty.jsp"},
        {positionid:"p11",pageurl:"/jsp/demo/dpage/empty.jsp"},
        {positionid:"p12",pageurl:"/jsp/demo/dpage/empty.jsp"},
        {positionid:"p13",pageurl:"/jsp/demo/dpage/title.jsp"},
        {positionid:"p14",pageurl:"/jsp/demo/dpage/title.jsp"},
        {positionid:"p15",pageurl:"/jsp/demo/dpage/title.jsp"},
        {positionid:"p16",pageurl:"/jsp/demo/dpage/empty.jsp"},
        {positionid:"p17",pageurl:"/jsp/demo/dpage/empty.jsp"},
        {positionid:"p18",pageurl:"/jsp/demo/dpage/empty.jsp"},
        {positionid:"p23",pageurl:"/jsp/demo/dpage/button.jsp"},
        {positionid:"p22",pageurl:"/jsp/demo/dpage/button.jsp"}
    ];
    
    for (var x = 0 ; x < initdatas.length; x++) {
        var onedata = initdatas[x];
        $.ajax({
            url: "${_CONTEXT_PATH}" + onedata['pageurl'],
            data: "",
            async: false,
            type: 'POST',
            dataType: 'html',
            success: function(result) {
                var panel = $('#' +onedata['positionid']);
                if (panel) {
                     panel.html(result);
                     initDrag(panel);
                     initPanelEvent(panel);
                }
            },
            error: function(result) {
                alert (result);
            }
        });
    }
    
    $('.addwidget').bind('click',function(e) {
        currPanel = e.target;
        $.dialogBox.openDialog(
            "${_CONTEXT_PATH}/jsp/demo/wpage/choice.jsp",//TODO 功能组件页面选择页面
            {
                title:'选择功能',
                okVal:'关闭',
                width:500,
                height:300,
                button:{name: '确定',focus: true,callback:doChoice}
            },true);
    });

}


var currPanel;
function doChoice() {
    var furl = this.iframe.contentWindow.getSelectFunction();
    addWidgetPage(furl);
}    
function addWidgetPage(furl) {
    $.ajax({
        url: "${_CONTEXT_PATH}" + furl,
        data: "",
        type: 'POST',
        dataType: 'html',
        success: function(result){
             if (currPanel) {
                 var panelroot =  $(currPanel).parent().parent().parent().parent().parent();
                 panelroot.html(result);
                 panelroot[0].useDrag = false;
                 initDrag(panelroot);
                 initPanelEvent(panelroot);
                 currPanel = null;
             }
             $.dialogBox.close();
        },
        error: function(result) {
            alert (result);
            $.dialogBox.close();
        }
    });
}

function initDrag(eles) {
    eles.ligerDrag({
            handler:'.awidget_head',
            onStartDrag: function (current){
                    var wig = current.target;
                    var height = wig.height();
                    var width = wig.width();
                    var gdiv = $('#tempdiv');
                    gdiv.css('height',height);
                    gdiv.css('width',width);
                    var pdiv = $('.inner');
                    wig.css('left',wig.offset().left);
                    wig.css('top',wig.offset().top);
                    wig.removeClass('unfloatdiv');
                    wig.addClass('floatdiv');
                    wig.before(gdiv);
                    gdiv.removeClass('unshow_replace');
                    gdiv.addClass('show_replace');
            },
            onStopDrag: function (current){
                    var wig = current.target;
                    var centerX = wig.offset().left + wig.width()/2;
                    var centerY = wig.offset().top + wig.height()/2;
                    //获取所有div
                    var wids = $('.awidget');
                    var other ;
                    for (var i = 0 ; i < wids.length ; i++) {
                        var idd = $(wids[i]).attr('id');
                        var tidd = wig.attr('id');
                        if (idd != tidd) {
                            var one = $(wids[i]);
                            var oneleft = one.offset().left;
                            var oneright = one.offset().left + one.width();
                            var onetop = one.offset().top;
                            var onebuttom = one.offset().top + one.height();
                            if (oneleft <centerX && centerX < oneright && 
                            onetop <centerY && centerY < onebuttom ) {
                                other = one; 
                                break;
                            }
                        }
                    }

                    var gdiv = $('#tempdiv');
                    if (other && other.attr("id") != 'tempdiv') {
                        //执行交换位置
                        //TODO 此处需要把位置信息提交服务器
                        wig.css('width',one.width());
                        wig.css('height',one.height());
                        one.after(wig);
                        one.css('width',gdiv.width());
                        one.css('height',gdiv.height());
                        gdiv.after(one);

                        wig.removeClass('floatdiv');
                        wig.addClass('unfloatdiv');
                        wig.css('left','auto');
                        wig.css('top','auto');
                        gdiv.removeClass('show_replace');
                        gdiv.addClass('unshow_replace');
                        
                        $('.panal2Main',one).height(one.height()-28);
                        $('.panal2Main',wig).height(wig.height()-28);
                    }
                    else {
                        wig.removeClass('floatdiv');
                        wig.addClass('unfloatdiv');
                        wig.css('left','auto');
                        wig.css('top','auto');
                        gdiv.removeClass('show_replace');
                        gdiv.addClass('unshow_replace');
                    }
            },
            onDrag: function (current,e){
                    return true;
            }
        });
}
function initPanelEvent(panel) {
    $('.panal2Main',panel).height(panel.height()-28);
    $('.close',panel).bind('mousedown',function(){
        var obj = $(this).parent().parent().parent();
        obj.html("<table width='100%' height='100%' border='0' cellspacing='0' cellpadding='0'><tr><td align='center' valign='middle' bgcolor='#ebeae9'><img src='../../../images/other.png' width='34' height='34' class='addwidget'/></td></tr></table>");
        $('img',obj).bind('click',function(e) {
            currPanel = e.target;
            $.dialogBox.openDialog(
                "${_CONTEXT_PATH}/jsp/demo/wpage/choice.jsp",//TODO 功能组件页面选择页面
                {
                    title:'选择功能',
                    okVal:'关闭',
                    width:500,
                    height:300,
                    button:{name: '确定',focus: true,callback:doChoice}
                },true);
        });
    });
}
</script>
<style type="text/css">
body,td,th { font-family: "宋体", "微软雅黑"; }
body,html { margin: 0px; padding: 0px; }
.outer { height: auto; position: absolute; width: 100%; z-index: 2; }
.inner { width: 99%; height: auto; margin-top: 0; margin-right: auto; margin-left: auto; }
.part1 { float: left; height: auto; width: 100%; }
.part2 { float: left; height: auto; width: 100%;margin-top: 20px;margin-bottom: 20px;}
.part3 { float: left; height: 500px; width: 100%;}
.part1a { float: left; height: auto; width:12%;margin-left: 2%;}
.part1b { float: left; height: auto; width:18%;margin-left: 1%;}
.part1c { float: left; height: 30px; width:12.5%;}
.shadow{ -moz-box-shadow: 0px 0px 5px #a0a2ae; -webkit-box-shadow: 0px 0px 5px #a0a2ae; box-shadow: 0px 0px 5px #a0a2ae; }
.font1{ font-size: 12px; font-weight: bold; color: #9fa5ab; }
.font2{ font-size: 14px; font-weight: bold; color: #9fa5ab; }
.font3{ font-size: 12px; font-weight: normal; color: #fff; }
.font4{ font-size: 12px; font-weight: bold; color: #fff; }
.font5{ font-size: 12px; font-weight: normal; color: #6a6e7a; }

.panal2 { float: left; width: 100%; background-color: #e2e5e9;  margin-bottom:5px; }
.panal2Main { float: left; width: 100%; overflow:auto;}
.panel3 { float: left; margin-bottom:2px; width:100%;}
.awidget{}
.awidget_head{}

.addwidget,.close {cursor: pointer;}
#tempdiv {float: left; width: 100%; margin-bottom:2px;}
</style>
</head>

<body>
<div id='tempdiv' class='unshow_replace'></div>
<div class="outer">
	<div class="inner">
		<div class="part1" >
			<fieldset class='queryBox'>
				<legend>查询条件</legend>
				<div class="part1a">
					<div class="panel3 awidget" id="p0">1</div>
					<div class="panel3 awidget" id="p1">2</div>
					<div class="panel3 awidget" id="p2">3</div>
				</div>
				<div class="part1b" >
					<div class="panel3 awidget" id="p4">1</div>
					<div class="panel3 awidget" id="p5">2</div>
					<div class="panel3 awidget" id="p6">3</div>
				</div>
				<div class="part1a" >
					<div class="panel3 awidget" id="p7">1</div>
					<div class="panel3 awidget" id="p8">2</div>
					<div class="panel3 awidget" id="p9">3</div>
				</div>
				<div class="part1b" >
					<div class="panel3 awidget" id="p10">1</div>
					<div class="panel3 awidget" id="p11">2</div>
					<div class="panel3 awidget" id="p12">3</div>
				</div>
				<div class="part1a" >
					<div class="panel3 awidget" id="p13">1</div>
					<div class="panel3 awidget" id="p14">2</div>
					<div class="panel3 awidget" id="p15">3</div>
				</div>
				<div class="part1b" >
					<div class="panel3 awidget" id="p16">1</div>
					<div class="panel3 awidget" id="p17">2</div>
					<div class="panel3 awidget" id="p18">3</div>
				</div>
			</fieldset>
		</div>
		<div class="part2" id="p7">
			<div class="part1c awidget" id="p19"></div>
			<div class="part1c awidget" id="p20"></div>
			<div class="part1c awidget" id="p21"></div>
			<div class="part1c awidget" id="p22"></div>
			<div class="part1c awidget" id="p23"></div>
			<div class="part1c awidget" id="p24"></div>
			<div class="part1c awidget" id="p25"></div>
			<div class="part1c awidget" id="p26"></div>
		</div>
		<div class="part3 awidget" id="p8">
			<fieldset class="outbox awidget_head"><legend>部门列表</legend>
				<div id='toptoolbar'></div>
				<div id='unitlist'></div>
			</fieldset>
		</div>
	</div>
</div>

</body>

<script type="text/javascript">
    $(function () {
    
    	 $("#toptoolbar").ligerToolBar({items:[
    	 	{text:'查看详情',name:'detail_btn',icon:'detail',enabled:'<n:auth key='unit_detail'/>',click:detailUnit},
       		{text:'新&nbsp;&nbsp;增',name:'insert_btn',icon:'insert',enabled:'<n:auth key='unit_insert'/>',click:insertUnit},
     		{text:'修&nbsp;&nbsp;改',name:'update_btn',icon:'update',enabled:'<n:auth key='unit_modify'/>',click:updateUnit}
           ],
          width:'100%'
		});
        $("#unitlist").ligerGrid({
            enumlist: _enum_params,
            checkbox: false,
            enabledSort: false,
           
            columns: [
            { display: '部门编号', name: 'unitId',  width:'10%',align:'left'},           
            { display: '部门名称', name: 'unitName', width:'11%',align:'left'},
            { display: '上级部门编号', name: 'superUnitId', align:'left'},
            //{ display: '上级部门名称', name: 'superUnitName', width:'11%',align:'left'},
            { display: '部门级别', name: 'unitLevel', width:'10%'}, 
            { display: '部门类型', name: 'unitKind', width: '10%',codetype:'unit_kind'}, 
            { display: '部门地址', name: 'unitPath',width: '11%',align:'left'},
            { display: '创建人', name: 'createUser',width: '10%',align:'left'},
            { display: '状态', name:'unitStatus',width:'10%',codetype:'valid_type'}
            ],
            enabledEdit:true,
            pageSize:10,
            sortName: 'unitId', 
            height:300,
            width:'100%',
            onError: function() {
                Utils.alert("查询数据失败。");
            }
        });
       
		$("#query").bind('click', query);
		$("#reset").bind('click', doClear);
    });
   //重置
   function doClear(){
	   $("#unitId").val("");
	   $("#unitName").val("");
	   $("#superUnitId").val("");
	   $("#superUnitName").val("");
	   $("#unitLevel").val("");
	   $("#unitKind").val("");
	   $("#unitStatus").val("");
   }
   //选择部门
   function openSelectUnit(){
   }
   function setUnitIdName(unitId,unitName){
   }
   
   function query(){}
   function insertUnit(){}
   //编辑部门信息
   function updateUnit(){}
   //删除部门信息
   function deleteUnit(){}
   function detailUnit(){}
</script>
</html>
