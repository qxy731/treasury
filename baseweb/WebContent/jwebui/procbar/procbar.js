/**
 * options
 * {
 *    steps:[{id:'step1',label:'步骤1',selected:true,enabled:false}],
 *    onSelected:function(idx,status) {
 *    }
 * }
 */
if (typeof (UIManagers) == "undefined") UIManagers = {};
(function($){

	$.fn.getProcessBar = function(){
        return UIManagers[this[0].id + "_procbar"];
    };
	$.fn.processBar = function(options) {
		return this.each(function(){
			var p = $.extend({}, options || {});
			$(p.steps).each(function(i,item) {
				if (item.enabled != false){
					item.enabled =true;
				}
			});
			var g = {
				setStepStauts : function(stepIdx,status) {
					$('.pstep',g.bar).each(function(x,item){
						if (stepIdx == x +1) {
							var dis = $(item).hasClass('pstep' + (stepIdx) +'_disable');
							if (!dis) {
								var currSel = 'pstep' + stepIdx +'_select';
								var currNoSel = 'pstep' + stepIdx ;
								if (status) {
									if (!$(item).hasClass(currSel)) {
										var me = 'g';
										$(item).removeClass(currNoSel);
										$(item).addClass(currSel);
										//左边是否选中
										var prev =  $(item).prev().prev().hasClass('pstep' + (stepIdx-1) +'_disable') ? "n" 
											:($(item).prev().prev().hasClass('pstep' + (stepIdx-1) +'_select') ? "g":"b");
										$(item).prev().attr('class',prev+'2'+me);
										//右边是否选中
										var next =  $(item).next().next().hasClass('pstep' + (stepIdx+1) +'_disable') ? "n" 
											:($(item).next().next().hasClass('pstep' + (stepIdx+1) +'_select') ? "g":"b");
										$(item).next().attr('class',me+'2'+next);
									}
								}
								else {
									if (!$(item).hasClass(currNoSel)) {
										var me = 'b';
										$(item).removeClass(currSel);
										$(item).addClass(currNoSel);
										//左边是否选中
										var prev =  $(item).prev().prev().hasClass('pstep' + (stepIdx-1) +'_disable') ? "n" 
											:($(item).prev().prev().hasClass('pstep' + (stepIdx-1) +'_select') ? "g":"b");
										$(item).prev().attr('class',prev+'2'+me);
										//右边是否选中
										var next =  $(item).next().next().hasClass('pstep' + (stepIdx+1) +'_disable') ? "n" 
												:($(item).next().next().hasClass('pstep' + (stepIdx+1) +'_select') ? "g":"b");
										$(item).next().attr('class',me+'2'+next);
									}
								}
								if (p.onSelected) {
									p.onSelected(stepIdx,status);
								}
							}
						}
					});
				},
				setStepEnabled : function(stepIdx,enabled) {
					$('.pstep',g.bar).each(function(x,item){
						if (stepIdx == x +1) {
							var me = ($(item).hasClass('pstep' + (stepIdx) +'_select') ? "g":"b");
							if (enabled) {
								var dsSel = 'pstep' + stepIdx +'_disable';
								if ($(item).hasClass(dsSel)) {
									$(item).removeClass(dsSel);
									//左边是否可用
									var prev =  $(item).prev().prev().hasClass('pstep' + (stepIdx-1) +'_disable') ? "n" 
										:($(item).prev().prev().hasClass('pstep' + (stepIdx-1) +'_select') ? "g":"b");
									$(item).prev().attr('class',prev+"2" +me);
									//右边是否可用
									var next =  $(item).next().next().hasClass('pstep' + (stepIdx+1) +'_disable') ? "n" 
										:($(item).next().next().hasClass('pstep' + (stepIdx+1) +'_select') ? "g":"b");
									$(item).next().attr('class',me + "2" + next);
								}
							}
							else {
								var currSel = 'pstep' + stepIdx +'_disable';
								if (!$(item).hasClass(currSel)) {
									$(item).addClass(currSel);
									me ='n';
									//左边是否可用
									var prev =  $(item).prev().prev().hasClass('pstep' + (stepIdx-1) +'_disable') ? "n" 
										:($(item).prev().prev().hasClass('pstep' + (stepIdx-1) +'_select') ? "g":"b");
									$(item).prev().attr('class',prev+"2" +me);
									//右边是否可用
									var next =  $(item).next().next().hasClass('pstep' + (stepIdx+1) +'_disable') ? "n" 
										:($(item).next().next().hasClass('pstep' + (stepIdx+1) +'_select') ? "g":"b");
									$(item).next().attr('class',me + "2" + next);
								}
							}
						}
					});
				}
			};
			g.bar = $(this);
			var stepHtml = [];
			stepHtml.push("<table><tr>");
			for (var i = 0 ; i < p.steps.length; i++) {
				var item = p.steps[i];
				var nitem = (i+1 < p.steps.length) ? p.steps[i+1] :item;
				var enables = ((item.enabled)?'' : 'pstep' +(i+1) + '_disable');
				stepHtml.push("<td nowrap class='pstep pstep"+(i+1)+ ((item.selected)?'_select':'') + " "+ enables +"'><label>"+item.label+"</label></td>");
				var dme =  item.enabled ? (item.selected?"g":"b"):"n";
				var dnext =  nitem.enabled ? (nitem.selected?"g":"b"):"n";
				stepHtml.push("<td class='" + dme + "2"+ dnext + "'></td>");
			}
			stepHtml.push("</tr></table>");
			$(g.bar).html(stepHtml.join(''));
			$('.pstep',g.bar).each(function(x,item) {
				$(item).click(function(){
					g.setStepStauts(x+1,true);
				});
			});
			var sid =  (this.id == undefined) ? ("processbar" + new Date().getTime()): this.id;
			UIManagers[sid+ "_procbar"] = g;
			return g;
		});
	}
})(jQuery);