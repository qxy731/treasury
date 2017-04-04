			var tab = null;
            var accordion = null;
            $(function (){
                //布局
                $("#layout1").ligerLayout({ leftWidth: 200, height: '100%', bottomHeight:20,onHeightChanged: f_heightChanged,isLeftCollapse: false,allowBottomResize: false, allowTopResize:false });
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
            });function f_heightChanged(options){
                if (tab)tab.addHeight(options.diff);
                if (accordion && options.middleHeight - 24 > 0)
                    accordion.setHeight(options.middleHeight - 24);
            }
            function f_addTab(tabid,text, url){
            	try{
            		tab.addTabItem({ tabid : tabid,text: text, url: url });
            	}catch(e){            		
            		//alert(e);
            	}finally{
            		//alert(2);
            	}
            }