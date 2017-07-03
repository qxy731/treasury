/**
 * 公共js方法
 */
var Utils={};
/**
 * 公共初始化
 */
$.ajaxSetup({
	contentType:"application/x-www-form-urlencoded; charset=utf-8"
});
$.ajaxSettings.traditional = true;

/**
 * 增加事件监听
 * 
 * obj
 * evt
 * func
 */
Utils.addEvent = function (obj,evt,func,evtobj){
	evtobj = !evtobj?obj:evtobj;
	if(obj.addEventListener){
		obj.addEventListener(evt,func,false);
	}else if(evtobj.attachEvent){
		obj.attachEvent('on'+evt,func);
	}
};
/**
 * 主页上新建TAB页
 * 
 * tabid  区分Tab的id
 * text   Tab页显示名称
 * url    路径
 */
Utils.openTab = function(tabid,text, url){
	try{
		parent.tab.addTabItem({ tabid : tabid,text: text, url: url });
	}catch(e){
		alert(e);
	}
}
/*
 * 移除tab项
 */
Utils.removeTabById=function(tabid){
	parent.tab.removeTabItem(tabid);
}
/**
 * 枚举类型变量存放处
 */
var _enum_params ={};

/**
 * 转换方法,配合struct2的映射
 * 
 * method 方法前缀
 * str 参数字符串
 * 例如：
 * method: query
 * obj:    {aname:aval,bname:bval,cname:cval}
 * ==>
 * {query.aname:aval,query.bname:bval,query.cname:cval}
 */
Utils.convertObjectData = function(method,obj) {
	var ret = {};
	if (!obj) {
		return ret;
	}
	for (var x in obj) {
		ret[method + '.' + x] = obj[x];
	}
	return ret;
}

/**
* 转换方法,配合struct2的映射
* 
* method    方法前缀
* formId    form id
* 
* 例如：
* method: query
* formId: myform
* ==>
* query.aname=aval&query.bname=bval&query.cname=cval
*/
Utils.convertFormData = function(method,formId) {
	var ret = {};
	if (!formId) {
		return ret;
	}
	$('#'+formId+' input').each(function(i,ele){
		var key = (method =='' ? ele.name: (method + '.' + ele.name));
		if (ele.name) {
			if (ele.type === "checkbox") {
				var curVal = ret[key];
				if (curVal === undefined) {
					ret[key] = [];
				}
				if (ele.checked) {
					ret[key].push(ele.value);
				}
			}
			else if (ele.type === "radio") {
				if (ele.checked) {
					ret[key] = ele.value;
				}
			}
			else {
				ret[key] = ele.value;
			}
		}
	});
	$('#'+formId+' select').each(function(i,ele){
		var key = (method =='' ? ele.name: (method + '.' + ele.name));
		if (ele.name) {
			ret[key] = ele.value;
		}
	});
	$('#'+formId+' textarea').each(function(i,ele){
		var key = (method =='' ? ele.name: (method + '.' + ele.name));
		if (ele.name) {
			ret[key] = ele.value;
		}
	});
	return ret;
}


/**
* 转换方法,配合grid组件参数
* 
* method    方法前缀
* formId    form id
* 
* 例如：
* method: query
* formId: myform
* 
* ==>
* [{name:'queryIn.roleId',value:roleId}
	,{name:'queryIn.roleStatus',value:roleStatus}
	,{name:'queryIn.roleName',value:roleName}
	,{name:'queryIn.createUser',value:createUser}]
*/
Utils.convertParam = function(method,formId) {
	var ret = [];
	if (!formId) {
		return ret;
	}
	var tmp = Utils.convertFormData(method,formId);
	for (var key in tmp) {
		var pair = {};
		pair.name = key;
		pair.value = tmp[key];
		ret.push(pair);
	}
	return ret;
}

/**
 * 判断结果是否成功
 */
Utils.isSuccess = function(result) {
	if (result && result.retCode) {
		if (result.retCode.substr(0,1) == 'I') {
			return true;
		}
	}
	return false;
}

 /**
  * 提交
  * @param action 地址
  * @param data 数据
  * @param onSuccess 成功执行方法
  * @param onError 失败执行方法
  */
Utils.ajaxSubmit = function(action, data, onSuccess,onError)
{
	$.ajax({
		url: action,
		data: data,
		type: 'POST',
		dataType: 'json',
		success: function(result){
			if (Utils.isSuccess(result)) {
				if (onSuccess ) {
					onSuccess(result);
				}
				else {
					if (result.retMsg) {
						$.dialogBox.info(result.retMsg);
					}
				}
			}
			else{
				if (onError ) {
					onError(result);
				}
				else {
					$.dialogBox.error(result.retMsg);
				}
			}
		},
		error: function(result) {
			$.dialogBox.alert(result.responseText || "无法访问服务器",null,null,null,'error');
		}
	});
}

/**
 * 打开选择机构单元对话框
 * b 定位组件
 * unitId 选择机构根节点
 * callback 回调函数 参数 unitId,unitName
 */
Utils.openSelectUnit = function(b,unitId,okFunction,cancelFunction) {
	var url = _CONTEXT_PATH + "/jsp/common/includeOrg.jsp?unitid=" + unitId;
	var p = {id:"_selectUnitDialog",title:'选择组织机构',width: 360,height:400,lock:true,opacity:0.07};
	if (b) {
		p.follow = $(b)[0];
	}
	$.dialogBox.openDialog(url,p,okFunction,cancelFunction);
}

Utils.getAbsPosition = function(object) {
	var o = $(object);
	if (o.length == 0) {
		return false;
	}
	o = o[0];
	var left, top;
	left = o.offsetLeft;
	top = o.offsetTop;
	while (o = o.offsetParent) {
		left += o.offsetLeft;
		top += o.offsetTop;
	}
	return {left: left,top: top};
}

Utils.validateInit = function() {
	$.metadata.setType("attr", "validate");
	$("form").validate({
		debug: false,
		errorPlacement: function(lable, element) {
			element.ligerTip({ content: lable.html(), appendIdTo: lable });
		},
		success: function(lable) {
			lable.ligerHideTip();
		},
		submitHandler: function() {
		}
	});
}

/**
 * 打开选择人员对话框,多选
 * okFunction 回调函数
 */
Utils.openSelectStaff = function(okFunction,cancelFunction){
	var url = _CONTEXT_PATH + "/jsp/common/pubstaffquery.jsp?dup=true";
	var p = {id:"_selectStaffDialog",title:'选择员工',lock:true,opacity:0.07};
	$.dialogBox.openDialog(url,p,okFunction,cancelFunction);
}
/**
* 打开选择人员对话框，单选
* okFunction 回调函数
*/
Utils.openSelectAStaff = function(okFunction,cancelFunction){
	var url = _CONTEXT_PATH + "/jsp/common/pubstaffquery.jsp?dup=false";
	var p = {id:"_selectStaffDialog",title:'选择员工',lock:true,opacity:0.07};
	$.dialogBox.openDialog(url,p,okFunction,cancelFunction);
}

/**
* 获取随机代理主键,前13位是时间戳+后17位随机数
*/
Utils.getRandomKey = function(){
	var random = (Math.random()+"").replace("0.", "").substr(0,17);
	var len = random.length ; 
	if(len<17){
		for(var j=0;j<17-len;j++){
			random = random+"0";
		}
	}
	var curtime = new Date().getTime();
	return curtime+random;
}

/**
 * 获取枚举类型变量对应值
 */
Utils.getCodeValue = function(codetype,itemid) {
	var xe = _enum_params[codetype];
	if (xe) {
		for (var x =0 ; x < xe.length ; x++) {
			if (xe[x][0] == itemid) {
				return xe[x][1];
			}
		}
	}
	return itemid;
}

 /**
  * 转换为图表显示数据格式(单列数据)
  * rows        数据数组
  * labelField  显示列域名
  * valueField  值列域名
  * linkField   链接列域名
  */
 Utils.convertChartData = function(rows,labelField,valueField,linkField) {
 	var ret = {};
 	var data = [];
 	if (rows && rows.length > 0) {
 		for (var x = 0 ; x < rows.length; x++) {
 			var one = {};
 			one.label = rows[x][labelField];
 			one.value = rows[x][valueField];
 			if (linkField) {
 				one.link = rows[x][linkField];
 			}
 			data.push(one);
 		}
 		ret['data'] = data;
 	}
 	return ret;
 }

 /**
 * 转换为图表显示数据格式(多列数据)
 * rows        数据数组
 * labelField  显示列域名
 * serialNames  系列域名
 * valueFields  系列值域名
 * linkField    链接列域名
 */
 Utils.convertMsChartData = function(rows,labelField,serialNames,valueFields,linkField) {
 	var ret = {};
 	var data = [];
 	var categories = [];
 	var category = [];
 	if (rows && rows.length > 0) {
 		for (var x = 0 ; x < rows.length; x++) {
 			var one = {};
 			one.label = rows[x][labelField];
 			category.push(one);
 		}
 	}
 	categories.push({'category':category});
 	ret['categories'] = categories;
 	var datasets = [];
 	if (serialNames && serialNames.length > 0 && valueFields && valueFields.length == serialNames.length) {
 		for (var y = 0 ; y < serialNames.length ; y++) {
 			var dataset = {};
 			dataset.seriesname = serialNames[y];
 			var datas = [];
 			for (var i = 0 ; i < rows.length; i++) {
 				var onedata = {};
 				onedata.value = rows[i][valueFields[y]];
 				if (linkField) {
 					onedata.link = rows[i][linkField];
 				}
 				datas.push(onedata);
 			}
 			dataset.data = datas;
 			datasets.push(dataset);
 		}
 	}
 	ret['dataset'] = datasets;
 	return ret;
 }
 
/**
 * 文件上传
 * @param callback(uploadId,files) 回调函数(上传编号,上传文件)
 * @param uploadId 上传编号，如果提供该值，将列出先前已上传文件
 * @param filetypes 允许上传文件类型,缺省不控制 例如： jar,txt
 * @param canDelete 是否允许删除 'true' or 'false'
 * @param canDownload 是否允许下载 'true' or 'false'
 * @param filemax 一次上传文件数量最大数,缺省为1
 * 
 **/
Utils.uploadFile = function(callback,uploadId,filetypes,canDelete,canDownload,filemax) {
	if (!$.isFunction(callback)){
		$.dialogBox.error('uploadFile 参数1必须为方法');
		return;
	}
	var types = "*";
	var filenum = "1";
	if (filetypes) {
		types = filetypes;
	}
	if (filemax || filemax == 0) {
		filenum = parseInt(filemax);
	}
	var url = _CONTEXT_PATH + "/jsp/common/upload/file_upload.jsp?filemax="+filenum+"&filter=" + types;
	if (uploadId){
		url = url + "&uid=" + uploadId;
	}
	var param = {title:"文件上传对话框",width:'360px',height:'200px',lock:true};
	param.init = function(){
		this.iframe.contentWindow.initMethod(filenum,canDelete,canDownload);
	}
	
	$.dialogBox.openDialog(url,param,
		function (){
			var uid = this.iframe.contentWindow.getUploadId();
			var files = this.iframe.contentWindow.getUploadFiles();
			callback(uid,files);
		},
		true);
};

/**
 * 提示信息框
 * content 提示信息
 * title 标题
 * okFunction 确认按钮事件(可直接使用true，表示只是显示按钮)
 * cancelFunction 取消按钮事件(可直接使用true，表示只是显示按钮)
 * @deprecated
 */
Utils.alert = function(content,title,okFunction,cancelFunction) {
	$.dialogBox.alert(content,title,okFunction,cancelFunction);
};

/**
* 弹出页面
* content 提示信息
* title 标题
* okFunction 确认按钮事件(可直接使用true，表示只是显示按钮)
* cancelFunction 取消按钮事件(可直接使用true，表示只是显示按钮)
* @deprecated
*/
Utils.openDialog = function(url,title,okFunction,cancelFunction) {
	$.dialogBox.openDialog(url,title,okFunction,cancelFunction);
};

/**
 * 选择指标
 * dup=true多选;dup=false单选
 * okFunction正常返回回调函数，cancelFunction异常返回回调函数
 * paramsObj：JSON类型的参数
 */
Utils.selectTarget = function(dup,okFunction,cancelFuntion,paramsObj){
	var mode = false;
	if(dup=='false'||dup==false||dup==true||du=='true'){
		mode = dup;
	}
	var str = "";
	if(!jQuery.isEmptyObject(paramsObj)){
		str = jQuery.param(paramsObj);
	}
	var url = _CONTEXT_PATH+"/jsp/common/pubtargetselect.jsp?dup="+mode;
	if(str!=""){
		url=url+"&"+str;
	}
	//alert(url);
	var p = {height:500,width:450,lock:false,opacity:0.07,title:'选择指标'};
	$.dialogBox.openDialog(url,p,okFunction,cancelFuntion);
};