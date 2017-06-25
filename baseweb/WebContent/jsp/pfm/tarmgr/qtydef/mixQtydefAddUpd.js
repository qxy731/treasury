
$(function (){
	$("#resetBtn").click(function (){
		$("#createGs").val("");
	});

	$("#selTarBtn").click( function() {
		//$.dialog.data("tarName","");
		var storeDate=$("#storeDate").val();
		if(!storeDate){
			$.dialogBox.warn("保存日期为必选项。");
			$("#storeDate").focus();
			return;
		}
		var paramsObj = {storeDate:storeDate};
		var oldText = $('#createGs').val();
		addFace("#替代&&字符#");
		var oldTextChange = $('#createGs').val();
		$('#createGs').val(oldText);
		Utils.selectTarget(false,function(){
			var target = this.iframe.contentWindow.select();
			oldTextChange = oldTextChange.replace(/#替代&&字符#/,"#"+target.tarName);
			$('#createGs').val(oldTextChange);
			$("#selTarBtn").attr("disabled",false);
		},function(){
			$("#selTarBtn").attr("disabled",false);
		},paramsObj);
		$(this).attr("disabled",true);
	});
	
	$("input[type='button']").click(function(){
		if($(this).attr('getvalue')){
			addFace($(this).attr('getvalue'));
		}
	});
	
	$("#selGsBtn").click(function(){
		var html='<table class="inputTable"><tr><td><input type="checkbox" value="同比增幅()" name="checkSelGs" />同比增幅(指标)</td>'
            +'<td><input type="checkbox" value="环比增幅()" name="checkSelGs" />环比增幅(指标)</td></tr>'
            +'<tr><td><input type="checkbox" value="较年初增量()" name="checkSelGs" />较年初增量(指标)</td>'
            +'<td><input type="checkbox" value="同比增量()" name="checkSelGs" />同比增量(指标)</td></tr>'
            +'<tr><td><input type="checkbox" value="环比增量()" name="checkSelGs" />环比增量(指标)</td>'
            +'<td><input type="checkbox" value="较季初增量()" name="checkSelGs" />较季初增量(指标)</td></tr>'
            +'<tr><td><input type="checkbox" value="取期初()" name="checkSelGs" />取期初(期初数)</td>'
            +'<td><input type="checkbox" value="取指标值(,)" name="checkSelGs" />取指标值(指标,期初)</td></tr>'
            +'<tr><td><input type="checkbox" value="舍入(,)" name="checkSelGs" />舍入(表达式,小数位数)</td>'
            +'<td><input type="checkbox" value="空值替换(,)" name="checkSelGs" />空值替换(表达式1,表达式2)</td></tr>'
            +'<tr><td><input type="checkbox" value="条件计算(,,)" name="checkSelGs" />条件计算(条件,式1,式2)</td>'
			+'</table>';
		var dialog = $.dialog({
		    title: '选择公式',
		    content: html,
		    ok: function(){
			    var selValue="";
		        $("input[name='checkSelGs']:checkbox:checked").each(function (){
		        	selValue+=this.value;
			    });
		        addFace(selValue);
		        $("#selGsBtn").attr("disabled",false);
		    },
		    close:function(){
		    	$("#selGsBtn").attr("disabled",false);
			}
		});
		$(this).attr("disabled",true);
	});
});

function add(){
	if($("#insertForm").valid()){
		$("#saveBtn").attr("disabled",true);
		try{
			var url = _CONTEXT_PATH+"/qtydefManager/qty-def!insert.action";
			var mdata = Utils.convertParam('','insertForm');
			Utils.ajaxSubmit(url,mdata,function(result){
				$.dialogBox.info(result.retMsg, function() {
					$.dialogBox.opener.query();
					$.dialogBox.close();
				});
			},function(result){
				$.dialogBox.warn(result.retMsg,'提示');
				$("#saveBtn").attr("disabled",false);
			});
		}catch(e){}
	}
	return false;
}

function cancelDialog() {
	$.dialog.close();
}

function clearScreen() {
	$('input').each(function(i,item) {
		item.value= '';
	});
}

function addFace(newText){	
	insertText(newText);
}
//支持IE和firefox
function insertText(str) {
	   var obj = $('#createGs').get(0);
	   if (document.selection) {
		     $('#createGs').focus();
			 var sel = document.selection.createRange();
			 sel.text = str;
	 	} else if (typeof obj.selectionStart === 'number' && typeof obj.selectionEnd === 'number'){
		 	var startPos = obj.selectionStart;
		 	var endPos = obj.selectionEnd;
		 	var cursorPos = startPos;
		 	var tmpStr = obj.value;
		 	obj.value = tmpStr.substring(0, startPos) + str + tmpStr.substring(endPos, tmpStr.length);
		 	cursorPos += str.length;
		 	obj.selectionStart = obj.selectionEnd = cursorPos;
	 	}else { 
	 		obj.value += str;
	 	}
	 }

$(function() {
    Utils.validateInit();
});

function update(){
	if($("#insertForm").valid()){
		try{
			var url = _CONTEXT_PATH+"/qtydefManager/qty-def!update.action";
			var mdata = Utils.convertParam('','insertForm');
			Utils.ajaxSubmit(url,mdata,function(result){
				$.dialogBox.info(result.retMsg, function() {
					$.dialogBox.opener.query();
					$.dialogBox.close();
				});
			},function(result){
				$.dialogBox.warn(result.retMsg,'提示');
			});			
		}catch(e){}
	}
}