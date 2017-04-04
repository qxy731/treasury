﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
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
        {positionid:"p0",pageurl:"/jsp/demo/wpage/mywidget0.jsp"},
        {positionid:"p1",pageurl:"/jsp/demo/wpage/mywidget1.jsp"},
        {positionid:"p2",pageurl:"/jsp/demo/wpage/empty.jsp"},
        {positionid:"p3",pageurl:"/jsp/demo/wpage/mywidget4.jsp"},
        {positionid:"p4",pageurl:"/jsp/demo/wpage/mywidget3.jsp"},
        {positionid:"p5",pageurl:"/jsp/demo/wpage/mywidget5.jsp"},
        {positionid:"p6",pageurl:"/jsp/demo/wpage/mywidget6.jsp"},
        {positionid:"p7",pageurl:"/jsp/demo/wpage/mywidget7.jsp"},
        {positionid:"p8",pageurl:"/jsp/demo/wpage/mywidget8.jsp"}
    ];
    
    for (var x = 0 ; x< initdatas.length; x++) {
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
            handler:'.font4',
            onStartDrag: function (current){
                    var wig = current.target;
                    var height = wig.height();
                    var width = wig.width();
                    var gdiv = $('#tempdiv');
                    gdiv.css('height',height);
                    wig.css('width',width);
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
                    var target = current.target;
                    var centerX = target.offset().left + target.width()/2;
                    var centerY = target.offset().top + target.height()/2;
                    //获取所有div
                    var wids = $('.panal2');
                    var other ;
                    for (var i = 0 ; i < wids.length ; i++) {
                        var idd = $(wids[i]).attr('id');
                        var tidd = target.attr('id');
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
                        target.css('width',one.width());
                        target.css('height',one.height());
                        one.after(target);
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
.inner { width: 80%; height: auto; margin-top: 0; margin-right: auto; margin-left: auto; } 
.part1 { float: left; height: auto; width: 28.5%; }
.part2 { float: left; height: auto; width: 70%; padding-left:1.5%;}
.part3 { float: left; height: auto; width: 50%;}
.part4 { float: left; height: auto; width: 48%; padding-left:2%;}
.headPhoto { background-color: #FFF; float: left; height: 142px; width: 106px; margin-top: 7px; margin-left: 7px; }
.data { float: left; height: 142px; width: 141px; margin-top: 7px; margin-left: 8px; }
.dataAll { background-color: #bcbebe; height: 25px; width: 141px; margin-bottom: 0px; text-indent: 25px; line-height: 25px; background-attachment: scroll; background-image: url(images/dataAll.png); background-repeat: no-repeat; background-position: 5px; }
.limits { background-color: #b1b4b5; height: 25px; width: 141px; margin-top: 9px; text-indent: 25px; line-height: 25px; background-attachment: scroll; background-image: url(images/limit.png); background-repeat: no-repeat; background-position: 5px; }
.helpTitle { line-height: 28px; text-indent: 9px; float: left; height: 28px; width: 100%; background-color: #6297bd; }
.recentTitle { float: left; height: 28px; width: 100%; }
.reLeft { background-color: #6297bd; float: left; height: 28px; width: 100%; line-height: 28px; }
.reRight { line-height: 28px; background-color: #666666; height: 28px; width: 46px; float: right; text-align: center; }
.blankLeft { margin-left: 9px; }
.shadow{ -moz-box-shadow: 0px 0px 5px #a0a2ae; -webkit-box-shadow: 0px 0px 5px #a0a2ae; box-shadow: 0px 0px 5px #a0a2ae; }
.font1{ font-size: 12px; font-weight: bold; color: #9fa5ab; }
.font2{ font-size: 14px; font-weight: bold; color: #9fa5ab; }
.font3{ font-size: 12px; font-weight: normal; color: #fff; }
.font4{ font-size: 12px; font-weight: bold; color: #fff; }
.font5{ font-size: 12px; font-weight: normal; color: #6a6e7a; }

.panal1 { float: left; width: 100%; background-color: #e2e5e9;  margin-top: 24px;}
.panal2 { float: left; width: 100%; background-color: #e2e5e9;  margin-bottom:24px; }
.panal2Main { float: left; width: 100%; overflow:auto;}
.empty { float: left; width: 100%; background-color: #e2e5e9;  margin-top: 24px;}
.empty a {font-size:50px; font-weight: bold; color: #fff;}
.panal2 img{vertical-align:middle}
.addwidget,.close {cursor: pointer;}
#tempdiv {float: left; width: 100%; margin-bottom:24px;}
</style>
</head>

<body>
<div id='tempdiv' class='unshow_replace'></div>
<div class="outer">
	<div class="inner">
		<div class="part1">
			<div class="panal2 shadow" style="height:156px" id="p0">
			</div>
			<div class="panal2 shadow" style="height:133px;" id="p1">
			</div>
			<div class="panal2 shadow" style="height:261px;"  id="p2">
			</div>
		</div>
		<div class="part2">
			<div class="part3">
				<div class="panal2 shadow" style="height:156px;"  id="p3">
				</div>
				<div class="panal2 shadow" style="height:217px"  id="p4">
				</div>
			</div>
			<div class="part4">
				<div class="panal2 shadow" style="height:156px;"  id="p5">
				</div>
				<div class="panal2 shadow" style="height:217px"  id="p6">
				</div>
			</div> 
			<div class="panal2 shadow" style="height:156px;"  id="p7">
			</div>
		</div>
		<div class="panal2 shadow" style="height:133px;"  id="p8">
		</div>
	</div>
</div>

</body>
</html>
