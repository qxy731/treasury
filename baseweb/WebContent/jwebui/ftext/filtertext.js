/**
 * 参数options
 * {
 *	url:"demo/dropdown.action",
 *	onSelected:function(idx,status) {
 *	}
 * }
 */
if (typeof (UIManagers) == "undefined") UIManagers = {};
(function($){

	$.fn.getFilterText = function(){
		return UIManagers[this[0].id + "_filtertext"];
	};
	$.fn.filterText = function(options) {
		var p = $.extend({}, {
			url:null,
			dataField:'listIn.simple',
			valueField:'unitId',
			textField:'unitName',
			slide:false
		},options || {});
		return this.each(function(){
			var g = {
				bulidContent: function()
				{
					this.clearContent();
					if (p.url)
					{
						var tdata ={};
						tdata[p.dataField] = g.inputText.val();
						$.ajax({
							type: 'post',
							url: p.url,
							cache: false,
							data: tdata,
							dataType: 'json',
							success: function(data)
							{
								if (data && data.retCode) {
									if (data.retCode.substr(0,1) == 'I') {
										g.setData(data.rows);
									}
									else {
										$("table.l-box-select-table", g.selectBox).append("<tr><td align='left'>" + data.retMsg + "</td>");
									}
								}
							},
							error: function(XMLHttpRequest, textStatus)
							{
								$("table.l-box-select-table", g.selectBox).append("<tr><td align='left'>无法访问服务 </td>");
							}
						});
					}
				},
				clearContent: function()
				{
					$("table", g.selectBox).html("");
				},
				toggleSelectBox: function(isHide)
				{
					var textHeight = g.wrapper.height();
					g.boxToggling = true;
					if (isHide)
					{
						if (p.slide)
						{
							g.selectBox.slideToggle('fast', function()
							{
								g.boxToggling = false;
							});
						}
						else
						{
							g.selectBox.hide();
							g.boxToggling = false;
						}
					}
					else
					{
						var topheight = g.wrapper.offset().top - $(window).scrollTop();
						var selfheight = g.selectBox.height() + textHeight + 4;
						if (topheight + selfheight > $(window).height() && topheight > selfheight)
						{
							g.selectBox.css("marginTop", -1 * (g.selectBox.height() + textHeight + 5));
						}
						if (p.slide)
						{
							g.selectBox.slideToggle('fast', function()
							{
								g.boxToggling = false;
								if (!p.isShowCheckBox && $('td.l-selected', g.selectBox).length > 0)
								{
									var offSet = ($('td.l-selected', g.selectBox).offset().top - g.selectBox.offset().top);
									$(".l-box-select-inner", g.selectBox).animate({ scrollTop: offSet });
								}
							});
						}
						else
						{
							g.selectBox.show();
							g.boxToggling = false;
							if (!g.tree && !g.grid && !p.isShowCheckBox && $('td.l-selected', g.selectBox).length > 0)
							{
								var offSet = ($('td.l-selected', g.selectBox).offset().top - g.selectBox.offset().top);
								$(".l-box-select-inner", g.selectBox).animate({ scrollTop: offSet });
							}
						}
					}
					g.isShowed = g.selectBox.is(":visible");
				},
				setData: function(data)
				{
					if (!data || !data.length) return;
					if (g.data != data) g.data = data;
					for (var i = 0; i < data.length; i++)
					{
						var val = data[i][p.valueField];
						var txt = data[i][p.textField];
						$("table.l-box-select-table", g.selectBox).append("<tr value='" + val + "'><td index='" + i + "' value='" + val + "' align='left'>" + txt + "</td>");
					}
					$("table.l-box-select-table td:first", g.selectBox).addClass("l-over");
					$("table.l-box-select-table td", g.selectBox).hover(function()
					{
						$(this).addClass("l-over");
					}, function()
					{
						$(this).removeClass("l-over");
					});
					g.addClickEvent();
				},
				changeValue: function(newValue, newText)
				{
					g.valueField.val(newValue);
					g.inputText.val(newText);
					if (p.onSelected)
						p.onSelected(newValue, newText);
				},
				addClickEvent: function()
				{
					//选项点击
					$("table.l-box-select-table td", g.selectBox).click(function()
					{
						$(".l-selected", g.selectBox).removeClass("l-selected");
						$(this).addClass("l-selected");
						if (p.slide)
						{
							g.boxToggling = true;
							g.selectBox.hide("fast", function()
							{
								g.boxToggling = false;
							});
						} else {
							g.selectBox.hide();
						}
						g.changeValue($(this).attr("value"), $(this).html());
					});
				}
			 
			};
			g.inputText = $(this);
			//隐藏域初始化
			g.valueField = $('<input type="hidden"/>');
			g.valueField[0].id = g.valueField[0].name = g.inputText.attr('id');
			//下拉框
			g.selectBox = $('<div class="l-box-select"><div class="l-box-select-inner"><table cellpadding="0" cellspacing="0" border="0" class="l-box-select-table l-table-nocheckbox"></table></div></div>');
			g.selectBox.table = $("table:first", g.selectBox);
			//外层
			g.wrapper = g.inputText.wrap('<div class="l-text l-text-combobox"></div>').parent();
			g.wrapper.append('<div class="l-text-l"></div><div class="l-text-r"></div>');
			g.wrapper.after(g.selectBox).after(g.valueField);

			//开关 事件
			p.hideOnLoseFocus = true;
			g.inputText.keydown(function(e){
				var ch = e.keyCode;
				if ((ch >=65 && ch <= 90) || ch == 13) {
					p.valueChanged = true;
				}
			}).keyup(function()
			{
				if (p.onBeforeOpen && p.onBeforeOpen() == false) return false;
				if (p.valueChanged && g.inputText.val().length >=2 ) {
					g.bulidContent();
					g.toggleSelectBox(false);
					p.valueChanged = false;
				}
			}).blur(function()
			{
				g.wrapper.removeClass("l-text-focus");
			}).focus(function()
			{
				g.wrapper.addClass("l-text-focus");
			}).focusout(function()
			{
				if (p.hideOnLoseFocus && g.selectBox.is(":visible") && !g.boxToggling )
				{
					//g.toggleSelectBox(true);
				}
			});
			g.wrapper.hover(function()
			{
				g.wrapper.addClass("l-text-over");
			}, function()
			{
				g.wrapper.removeClass("l-text-over");
			});
			
			g.selectBox.hover(null, function()
			{
				if (p.hideOnLoseFocus && g.selectBox.is(":visible") && !g.boxToggling )
				{
					g.toggleSelectBox(true);
				}
			});
			var sid =  (this.id == undefined) ? ("filtertext" + new Date().getTime()): this.id;
			UIManagers[sid+ "_filtertext"] = g;
			return g;
		});
	}
})(jQuery);