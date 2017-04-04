/**
 * 首页微件
 * @author wuwei
 */
(function($) {
    $.zWidgetPanel = $.zWidgetPanel || {};
    $.zWidgetPanel.context = {
       colwidth: 400,
       spaceWidth:10,
       layoutData:[],
       widgetsData:[],
       onLayoutChange:null,
       onPanelClose:null,
       renderList:true
    };

    $.fn.zWidgetPanel = function(p) {
        p = $.extend( $.zWidgetPanel.context, p || {});
        return this.each(function() {
            var g = {
                getWidgetRender: function(type) {
                    var x = $.zWidgets[type];
                    if (!x) {
                        x = $.zWidgets.def;
                    }
                    return x;
                },
                setGridXY :function (gx,gy) {
                    g.tdiv.attr('girdx',gx);
                    g.tdiv.attr('girdy',gy);
                },
                _renderAWidget: function(hstr,map) {
                    hstr.push("<div class='mywidget' id='");
                    hstr.push(map['wid']);
                    hstr.push("' style='width:");
                    hstr.push(p.colwidth);
                    hstr.push("px'>");
                    //微件开始
                    hstr.push("<h5 class='p-title'>");
                    hstr.push("<span>");
                    hstr.push(map['title']);
                    hstr.push("</span>");
                    var render = g.getWidgetRender(map['type']);
                    if (render) {
                    	if(render.moreEnabled && render.moreEnabled(map)){
                        	hstr.push("<a class='p-more' title='更多' onclick='return false;' href=''></a>");
                        }
                        //关闭按钮
                        if(!render.closeabled || render.closeabled(map)){
                        	hstr.push("<a class='p-close' title='关闭' onclick='return false;' href='#'></a>");
                        }
                    }
                    hstr.push("</h5>");
                    hstr.push("<div class='p-content'>");
                    try {
                        if (render) {
                            hstr.push(render.renderHtml(map['wid'],map));
                        }
                    } catch(ex){
                        hstr.push("init error");
                    }
                    hstr.push("</div>");
                    //
                    hstr.push("</div>");
                },
                _render:function(layouts) {
                    var htmlstr = [];
                    htmlstr.push("<div id='tmpdiv' class='unshow_replace' style='width:");
                    htmlstr.push(p.colwidth);
                    htmlstr.push("px'></div>");
                    for (var x = 0 ; x < layouts.length ; x++) {
                        htmlstr.push("<div class='zw_column' id='col");
                        htmlstr.push((x+1));
                        htmlstr.push("' style='width:");
                        htmlstr.push(p.colwidth);
                        htmlstr.push("px'>");
                        for (var y = 0 ; y < layouts[x].length; y ++) {
                             var map = layouts[x][y];
                             g._renderAWidget(htmlstr,map);
                        }
                        htmlstr.push("</div>");
                    }
                    g.target.empty();
                    g.target.append(htmlstr.join(''));
                    if (p.renderList) {
                        var hstr = [];
                        hstr.push("<div class='addbuttons'>");
                        hstr.push("   <div class='widgetlist' style='display:none;'></div>");
                        hstr.push("</div>");
                        g.target.before(hstr.join(''));
                    }
                },
                _onStartDrag:function (current) {
                    var wig = current.target;
                    var height = wig.height();
                    g.tdiv.css('height',height);
                    wig.removeClass('unfloatdiv');
                    wig.addClass('floatdiv');
                    wig.css('left',wig.offset().left);
                    wig.css('top',wig.offset().top );
                    wig.before(g.tdiv);
                    g.tdiv.removeClass('unshow_replace');
                    g.tdiv.addClass('show_replace');
                    var gx = Math.floor(g.tdiv.offset().left/(p.colwidth+p.spaceWidth));
                    var wids = $('#col' + (gx+1) + ' .mywidget');
                    var gy = 0;
                    for (var i = 0 ; i < wids.length ; i++) {
                        if (wig.attr('id') == $(wids[i]).attr('id')) {
                           gy = i;
                           break;
                        }
                    }
                    g.setGridXY(gx,gy);
                    wig.attr('girdx',gx);
                    wig.attr('girdy',gy);
                },
                _onStopDrag:function (current) 
                {
                    var wig = current.target;
                    var curr_gx = parseInt(g.tdiv.attr('girdx'));
                    var curr_gy = parseInt(g.tdiv.attr('girdy'));
                    var old_gx = parseInt(wig.attr('girdx'));
                    var old_gy = parseInt(wig.attr('girdy'));
                    if (curr_gx != old_gx || curr_gy != old_gy) {
                        g.tdiv.before(wig);
                        wig.attr('girdx',curr_gx);
                        wig.attr('girdy',curr_gy);
                        if (p.onLayoutChange) {
                           p.onLayoutChange(g.caculateLayoutStr(),wig);
                        }
                    }
                    wig.removeClass('floatdiv');
                    wig.addClass('unfloatdiv');
                    wig.css('left','auto');
                    wig.css('top','auto');
                    g.tdiv.removeClass('show_replace');
                    g.tdiv.addClass('unshow_replace');
                },
                _onDrag:function (current,e) {
                    //选择区域
                    var target = current.target;
                    var centerX = target.offset().left + target.width()/2;
                    var centerY = target.offset().top + target.height()/2;
                    var x = Math.floor(centerX/(p.colwidth+p.spaceWidth));
                    //获取所有div
                    var wids = $('#col' + (x+1) + '> div');
                    var y = -1;
                    var y_targetline = 10;
                    var chdiv ;
                    for (var i = 0 ; i < wids.length ; i++) {
                        // 需要排除移动中的那块
                        var isSelf = $(wids[i]).hasClass('floatdiv');
                        if (!isSelf) {
                            var tmpline = y_targetline;
                            if (i+1 < wids.length) {
                                y_targetline = y_targetline + ($(wids[i]).height() + $(wids[i+1]).height() + 10)/2;
                            }
                            else {
                                y_targetline = y_targetline + $(wids[i]).height()/2;
                            }
                            y ++;
                            chdiv = $(wids[i]);
                            if (centerY < y_targetline) {
                                break;
                            }
                            else {
                                y_targetline = tmpline + $(wids[i]).height();
                            }
                        }
                    }
                    var curr_gx = parseInt(g.tdiv.attr('girdx'));
                    var curr_gy = parseInt(g.tdiv.attr('girdy'));
                    if ( x != curr_gx || y != curr_gy) {
                        if ( y == -1 ){
                            chdiv =  $('#col' + (x+1) + ' .mywidget').last();
                            if (chdiv.length > 0) {
                                chdiv.after(g.tdiv);
                            }
                            else {
                                if ($('#col' + (x+1) + '> div').length == 0) {
                                   $('#col' + (x+1)).append(g.tdiv);
                                }
                            }
                        }
                        else if (y == curr_gy+1 && x == curr_gx){
                            if ($(chdiv).attr('id') != 'tmpdiv')
                            {
                                chdiv.after(g.tdiv);
                            }
                        }
                        else {
                            chdiv.before(g.tdiv);
                        }
                        g.setGridXY(x,y);
                    }
                    return true;
                },
                //形成布局字符串
                caculateLayoutStr : function () {
                    var str = "";
                    $('#panelcontent .zw_column').each(function(x,col){
                        if (x>0) {
                           str += "|";
                        }
                        $('#col'+(x+1)+ ' .mywidget').each(function (y,n) {
                            if (y != 0 ){
                                str += ",";
                            }
                            var mid = $(n).attr('id');
                            str += mid;
                        });
                    });
                    return str;
                },
                _closePanelEvent :function() {
                	var wid = $(this).parent().parent().attr('id');
                    $(this).parent().parent().remove();
                    if (p.onPanelClose) {
                        p.onPanelClose(this);
                    }
                    if (p.onLayoutChange) {
                        p.onLayoutChange(g.caculateLayoutStr(),this);
                    }
                    var chbox = $('#img_' + wid);
                    var i = chbox.attr('src').lastIndexOf('/');
                    var srcname = chbox.attr('src').substring(0,i+1)+"c"+chbox.attr('src').substring(i+1);
                    chbox.attr("src",srcname);
                    return false;
                },
                _checkbox : function() {
                	var img = g._revertImage($(this).attr('src'));
                	$(this).attr('src',img);
                },
                _revertImage :function(imgurl) {
                	if (imgurl) {
                		var i = imgurl.lastIndexOf('/');
                    	var src = imgurl.substring(i+1);
                    	var flag = imgurl.substring(i+1,i+2);
                    	if(flag=='c'){
                    		return imgurl.substring(0,i+1) + src.substring(1);
                    	}else{
                    		return imgurl.substring(0,i+1) + 'c' + src;
                    	}
                	}
                }
            };
            g.target = $(this);
            g._render(p.layoutData);
            g.tdiv = $("#tmpdiv");
            //面板关闭
            $('.p-title .p-close').bind('mousedown',g._closePanelEvent);
            //微件按钮列表
            var wstr = [];
            var rowcnt = 11;
            wstr.push("<table class='widgetlisttable'>");
            for(var n = 0;n<p.widgetsData.length;n=n+rowcnt){
            	wstr.push("<tr>");
                for (var x = n ; x< n+rowcnt ;x++) {
                   wstr.push("<td>");
                   if(x<p.widgetsData.length){
                	   var render = g.getWidgetRender(p.widgetsData[x].type);
                	   wstr.push("<img id='img_"+p.widgetsData[x].wid+"' ");
                	   var imgurl ="";
                	   if (render.getIconImage){
                		   imgurl = render.getIconImage(p.widgetsData[x]);
                	   }
                       if ( p.widgetsData[x].selected == "true") {
                    	   wstr.push(" src='"+imgurl+"' alt='"+p.widgetsData[x].title+"' "); 
                       }else{
                    	   wstr.push(" src='"+g._revertImage(imgurl) +"' alt='"+p.widgetsData[x].title+"' ");
                       }
                       wstr.push("/>");
                   }else{
                	   wstr.push("");
                   }
                   wstr.push("</td>");
                }
                wstr.push("</tr>");
                wstr.push("<tr>");
                for (var x = n ; x< n+rowcnt ;x++) {
                	if(x<p.widgetsData.length){
                		wstr.push("<td>"+p.widgetsData[x].title+"</td>");
                	}else{
                 	   wstr.push("");
                    }
                 }
                wstr.push("</tr>");
            }
            wstr.push("</table>");
            $('.widgetlist').append(wstr.join(''));
            
            //图标变换
            $('.widgetlisttable td img').bind('click',g._checkbox);
        	$('.widgetlisttable td img').each(function(i,ele){
        		$(ele).addClass("imgout");
        		$(ele)[0].onmouseover=function() {
           		   	 this.className="imgover";
          		}
        		$(ele)[0].onmouseout=function() {
           		   	 this.className="imgout";
          		}
        	});
            //添加删除微件功能
            $('.widgetlisttable img').click(function(){
                var wid = $(this).attr('id').substring(4);
                var i = $(this).attr('src').lastIndexOf('/');
                var srcname = $(this).attr('src').substring(i+1);
                
                if (srcname.substring(0,1)!= 'c') {
                    var hstr = [];
                    var map ;
                    for (var u = 0 ; u < p.widgetsData.length; u++) {
                        if (p.widgetsData[u].wid == wid) {
                           map = p.widgetsData[u];
                           break;
                        }
                    }
                    //选择最少一列放
                    var xcol = 1;
                    var min = 10;
                    $('.zw_column').each(function(zi,zcol){
                        var len = $('.mywidget',zcol).length;
                        if (len < min) {
                            xcol = zi + 1;
                            min = len;
                        }
                    });
                    g._renderAWidget(hstr,map);
                    $('#col' +xcol).append(hstr.join(''));
                    if (p.onLayoutChange) {
                        p.onLayoutChange(g.caculateLayoutStr(),null);
                    }
                    var render = g.getWidgetRender(map['type']);
                    var wdiv = $("#col"+xcol+">div[id='"+ wid +"']");
                    try {
                        render.initWidget(wdiv,map);
                        if(render.getMore!=undefined){
                      	    $('.p-title .p-more',wdiv).bind('mouseup',render.getMore);
                      	    $('.p-title .p-more',wdiv).addClass('p-moreconf');
                        }
                        //支持拖拽
                        wdiv.ligerDrag({
                            handler:'.p-title',
                            onStartDrag: g._onStartDrag,
                            onStopDrag: g._onStopDrag,
                            onDrag: g._onDrag
                        });
                        $('.p-title .p-close',wdiv).bind('mousedown',g._closePanelEvent);
                    } catch(ex){}
                }
                else {
                    var wdiv = $(".zw_column >div[id='"+ wid +"']");
                    wdiv.remove();
                    if (p.onPanelClose) {
                        p.onPanelClose(wdiv);
                    }
                    if (p.onLayoutChange) {
                        p.onLayoutChange(g.caculateLayoutStr(),wdiv);
                    }
                }
            });

            //支持拖拽
            $('.zw_column .mywidget').ligerDrag({
                handler:'.p-title',
                onStartDrag: g._onStartDrag,
                onStopDrag: g._onStopDrag,
                onDrag: g._onDrag
            });
            //html建立后初始化
            $('.zw_column').each(function(x,col){
                $('.mywidget',col).each(function(y,w){
                    var layoutData = p.layoutData[x][y];
                    var wid = layoutData['type'];
                    var render = g.getWidgetRender(wid);
                    try {
                        render.initWidget(w,layoutData);
                        if(render.getMore!=undefined){
                        	$('.p-title .p-more',w).bind('mouseup',render.getMore);
                        }
                    } catch(ex){}
                });
            });
        });
    };
})(jQuery);