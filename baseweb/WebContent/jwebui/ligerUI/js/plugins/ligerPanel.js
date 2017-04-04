/**
* jQuery ligerUI 1.0.2
* 
* Author peter731 [ gd_star@163.com ] 
* 
*/
if (typeof (LigerUIManagers) == "undefined") LigerUIManagers = {};
(function($)
{ 
    ///	<param name="$" type="jQuery"></param>

    $.fn.ligerGetPanelManager = function()
    {
        return LigerUIManagers[this[0].id + "_Panel"];
    };
    $.fn.ligerRemovePanelManager = function()
    {
        return this.each(function()
        {
            LigerUIManagers[this.id + "_Panel"] = null;
        });
    };

    $.fn.ligerPanel = function(p)
    { 
        this.each(function()
        {
            p = $.extend({
                height: null,
                speed : "normal",
                changeHeightOnResize: false,
                heightDiff: 0 // 高度补差  
            }, p || {});
            
            if (this.usedAccordion) return;
            var g = {
                onResize: function()
                {
                    if (!p.height || typeof (p.height) != 'string' || p.height.indexOf('%') == -1) return false;
                    //set Panel height
                    if (g.panel.parent()[0].tagName.toLowerCase() == "body")
                    {
                        var windowHeight = $(window).height();
                        windowHeight -= parseInt(g.layout.parent().css('paddingTop'));
                        windowHeight -= parseInt(g.layout.parent().css('paddingBottom'));
                        g.height = p.heightDiff + windowHeight * parseFloat(g.height) * 0.01;
                    }
                    else
                    {
                        g.height = p.heightDiff + (g.panel.parent().height() * parseFloat(p.height) * 0.01);
                    }
                    g.panel.height(g.height);
                    g.setContentHeight(g.height - g.headerHoldHeight);
                },
                setHeight: function(height)
                {
                    g.panel.height(height);
                    height -= g.headerHoldHeight;
                    $("> .l-accordion-content", g.panel).height(height);
                }
            };
            g.panel = $(this);
            if (!g.panel.hasClass("l-accordion-panel")) g.panel.addClass("l-accordion-panel");
            var selectedIndex = 0;
            if ($("> div[lselected=true]", g.panel).length > 0)
                selectedIndex = $("> div", g.panel).index($("> div[lselected=true]", g.panel));

            $("> div", g.panel).each(function(i, box)
            {
                var header = $('<div class="l-accordion-header"><div class="l-accordion-toggle"></div><div class="l-accordion-header-inner"></div></div>');
                if (i == selectedIndex)
                    //$(".l-accordion-toggle", header).addClass("l-accordion-toggle-open");
                if ($(box).attr("title"))
                {
                    $(".l-accordion-header-inner", header).html($(box).attr("title"));
                    $(box).attr("title","");
                }
                $(box).before(header);
                if (!$(box).hasClass("l-accordion-content")) $(box).addClass("l-accordion-content");
            });

            //add Even          
            $(".l-accordion-header", g.panel).hover(function()
            {
                $(this).addClass("l-accordion-header-over");
            }, function()
            {
                $(this).removeClass("l-accordion-header-over");
            });           
            
            //init
            g.headerHoldHeight = 0;
            $("> .l-accordion-header", g.panel).each(function()
            {
                g.headerHoldHeight += $(this).height();
            });
            if (p.height && typeof (p.height) == 'string' && p.height.indexOf('%') > 0)
            {
                g.onResize();
                if (p.changeHeightOnResize)
                {
                    $(window).resize(function()
                    {
                        g.onResize();
                    });
                }
            }
            else
            {
                if (p.height)
                {
                    g.height = p.heightDiff + p.height;
                    g.panel.height(g.height);
                    g.setHeight(p.height);
                }
                else
                {
                    g.header = g.panel.height();
                }
            }

            if (this.id == undefined) this.id = "LigerUI_" + new Date().getTime();
            LigerUIManagers[this.id + "_Panel"] = g;
            this.usedAccordion = true; 
        });
        if (this.length == 0) return null;
        if (this.length == 1) return LigerUIManagers[this[0].id + "_Panel"];
        var managers = [];
        this.each(function() {
            managers.push(LigerUIManagers[this.id + "_Panel"]);
        });
        return managers;
    };

})(jQuery);