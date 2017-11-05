(function($){
	$.dialogBox = {};
	$.dialogBox.opener = $.dialog.opener;

	/**
	 * 普通提示信息框
	 * content 提示信息
	 * okFunction 确认按钮事件(可直接使用true，表示只是显示按钮)
	 */
	$.dialogBox.info = function(content,okFunction) {
		var p = {height:80,width:350,padding:'1px 1px',ok:true,title: '提示'};
		var str = "<div class='dialog-image dialog-image-done'/><div class='dialog-content'>"+content+"</div>";
		if (okFunction) {
			p.ok = okFunction;
		}
		$.dialogBox.msgbox(str,p);
	}
	/**
	 * 普通提示信息框
	 * content 提示信息
	 * okFunction 确认按钮事件(可直接使用true，表示只是显示按钮)
	 */
	$.dialogBox.alert = function(content,okFunction) {
		var p = {height:80,width:350,padding:'1px 1px',ok:true,title:'提示'};
		var str = "<div class='dialog-image dialog-image-warn'/><div class='dialog-content'>"+content+"</div>";
		if (okFunction) {
			p.ok = okFunction;
		}
		$.dialogBox.msgbox(str,p);
	}
	/**
	 * 警告信息框
	 * content 提示信息
	 * okFunction 确认按钮事件(可直接使用true，表示只是显示按钮)
	 */
	$.dialogBox.warn = function(content,okFunction) {
		var p = {height:80,width:350,padding:'1px 1px',ok:true,title: '警告'};
		var str = "<div class='dialog-image dialog-image-warn'/><div class='dialog-content'>"+content+"</div>";
		if (okFunction) {
			p.ok = okFunction;
		}
		$.dialogBox.msgbox(str,p,true);
	}
	/**
	 * 错误信息框
	 * content 提示信息
	 * okFunction 确认按钮事件
	 */
	$.dialogBox.error = function(content,okFunction,id) {
		var p = {height:80,width:350,padding:'1px 1px',ok:true,title:'错误',id: id |'error'};
		var str = "<div class='dialog-image dialog-image-error'/><div class='dialog-content'>"+content+"</div>";
		if (okFunction) {
			p.ok = okFunction;
		}
		$.dialogBox.msgbox(str,p,true);
	}
	
	/**
	 * 确认对话框
	 * content 提示信息
	 * okFunction 确认按钮事件(可直接使用true，表示只是显示按钮)
	 * cancelFunction 取消按钮事件(可直接使用true，表示只是显示按钮)
	 */
	$.dialogBox.confirm = function(content,okFunction,cancelFunction) {
		var p = {height:80,width:350,padding:'1px 1px',title:'请选择',lock:true,ok:true,cancel:true};
		var str = "<div class='dialog-image dialog-image-question'/><div>"+content+"</div>";
		$.dialogBox.msgbox(str,p,okFunction,cancelFunction);
	}
	/**
	 * 选择对话框
	 * content 提示信息
	 * okFunction 确认按钮事件(可直接使用true，表示只是显示按钮)
	 * cancelFunction 取消按钮事件(可直接使用true，表示只是显示按钮)
	 */
	$.dialogBox.choice = function(content,yesFunction,noFunction,cancelFunction) {
		var p = {height:80,width:350,padding:'1px 1px',title:'请选择',lock:true};
		var buttons = [{name:'是',callback:yesFunction,focus:true},
		               {name:'否',callback:noFunction},
		               {name:'取消',callback:cancelFunction}];
		p.button = buttons;
		var str = "<div class='dialog-image dialog-image-question'/><div>"+content+"</div>";
		$.dialogBox.msgbox(str,p);
	}
	
	$.dialogBox.choice2 = function(content,yesFunction,noFunction,cancelFunction) {
		var p = {height:80,width:350,padding:'1px 1px',title:'请选择',lock:true};
		var buttons = [{name:'本月',callback:yesFunction},
		               {name:'次月',callback:noFunction,focus:true},
		               {name:'取消',callback:cancelFunction}];
		p.button = buttons;
		var str = "<div class='dialog-image dialog-image-question'/><div>"+content+"</div>";
		$.dialogBox.msgbox(str,p);
	}
	/**
	 * 提示信息框
	 * content 提示信息
	 * config 配置/标题
	 * okFunction 确认按钮事件(可直接使用true，表示只是显示按钮)
	 * cancelFunction 取消按钮事件(可直接使用true，表示只是显示按钮)
	 */
	$.dialogBox.msgbox = function(content,config,okFunction,cancelFunction,id) {
		var p = {height:80,width:300,lock:false,opacity:0.07,content:content,title:'提示',id: id ||'error'};
		if ( typeof config === 'string') {
			if (config) {
				p.title = config;
			}
		}
		else {
			$.extend(p,config);
		}
		if (okFunction) {
			p.ok = okFunction;
		}
		if (cancelFunction) {
			p.cancel = cancelFunction;
		}
		$.dialog(p);
	}
	/**
	* 弹出页面
	* content 提示信息
	* config/title 配置/标题
	* okFunction 确认按钮事件(可直接使用true，表示只是显示按钮)
	* cancelFunction 取消按钮事件(可直接使用true，表示只是显示按钮)
	*/
	$.dialogBox.openDialog = function(url,config,okFunction,cancelFunction) {
		var p = {title:'提示',opacity:0.07};
		if ( typeof config === 'string') {
			if (config) {
				p.title = config;
			}
		}
		else {
			$.extend(p,config);
		}
		if (okFunction) {
			p.ok = okFunction;
		}
		if (cancelFunction) {
			p.cancel = cancelFunction;
		}
		$.dialog.open(url,p);
	}
	
	$.dialogBox.close = function() {
		try{
			$.dialog.close();
		}catch(e){
			
		}
	}
})(jQuery);