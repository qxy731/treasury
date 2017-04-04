$(function (){
	$("#resetBtn").click(function (){
		$("#createGs").val("");
	});
	$("#prdtypeName").click(function(){
		 var url=_CONTEXT_PATH+"/jsp/common/pub_productTree.jsp";
		 $.dialog.open(url,
				{
			      id:'prdDailog',
			      title:'产品类型',
			      ok: function(){
	                    var products=$.dialog.data('products');
	                    if(products){
		                    var prdname="";
			                var prdcode="";
	                        for(var i=0;i<products.length;i++){
	                        	var product=products[i];
	                        	prdcode=product[0];
	                        	prdname=product[1];
		                    }
		                    $("#prdtypeCode").val(prdcode);
	                        $("#prdtypeName").val(prdname);
	                    }
	                  }
				});
	});
	$("#subjName").click(function (){
		  var url=_CONTEXT_PATH+"/jsp/common/subjectTree.jsp";
		  $.dialog.open(url,{
			      id:"subjectDailog",
			      title:'科目号',
			      ok: function(){
	                    var subjects=$.dialog.data('subjects');
	                    if(subjects){
		                    var subjName="";
			                var subjCode="";    
	                        for(var i=0;i<subjects.length;i++){
	                        	var subject=subjects[i];
	                        	subjCode=subject[0];
	                        	subjName=subject[1];
		                    }
		                    $("#subjCode").val(subjCode);
	                        $("#subjName").val(subjName);
	                    }
	                  }
				});
	});

	$("#selTarBtn").click( function() {
		//$.dialog.data("tarName","");
		var storeDate=$("#storeDate").val();
		if(!storeDate){
			$.dialogBox.warn("保存日期为必选项。");
			$("#storeDate").focus();
			return;
		}
		$("#tarScope").val(getTarScope());
		var tarScope=$("#tarScope").val();
		if(!tarScope){
			$.dialogBox.warn("适用对象为必选项。");
			$("#tarScope").focus();
			return;
		}
		var paramsObj = {storeDate:storeDate,tarScope:tarScope};
		var oldText = $('#createGs').val();
		addFace("#替代&&字符#");
		var oldTextChange = $('#createGs').val();
		$('#createGs').val(oldText);
		Utils.selectTarget1(false,function(){
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
            //+'<td><input type="checkbox" value="取基数()" name="checkSelGs" />取基数(指标)</td></tr>'
            //+'<tr><td colspan="2"><input type="checkbox" value="取日均(,)" name="checkSelGs" />取日均(时点指标1,周期单位)</td></tr>'
            //+'<td><input type="checkbox" value="取日均(,)" name="checkSelGs" />取日均(时点指标1,周期单位)</td></tr>'
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
			$("#tarScope").val(getTarScope());
			var url = _CONTEXT_PATH+"/qtydefManager!insert.action";
			var mdata = Utils.convertParam('','insertForm');			
			Utils.ajaxSubmit(url,mdata,function(result){
				//$.dialogBox.info(result.retMsg,'提示');
				if(Utils.isSuccess(result)){
					$("#tarCodeText").val(result.tarCode);//添加成功后显示指标代码
					$.dialogBox.info(result.retMsg);
				}else{
					$.dialogBox.error(result.retMsg);
					$("#saveBtn").attr("disabled",false);
				}
			},function(result){
				$.dialogBox.warn(result.retMsg,'提示');
				$("#saveBtn").attr("disabled",false);
			});
		}catch(e){}
	}
	return false;
}
function getTarScope(){
	var tarScope=null;
	var checkedbox=$("input[name='tarScopeCheck']:checkbox:checked");
	if(checkedbox.length==2){
		tarScope= orgPersonCode;
	}else if(checkedbox.length==1){
		tarScope=checkedbox[0].value;
	}
	return tarScope;
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
	 	} else if (typeof obj.selectionStart === 'number' && typeof obj.selectionEnd === 'number') {  
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
	$('#procDateCode').bind('change',procDateCodeChange);	
    Utils.validateInit();
});

function procDateCodeChange(){
	if($('#procDateCode').val()=='OT'){
		$('#otherProcDate').attr('style','display:inline;');
	}else{
		$('#otherProcDate').attr('style','display:none');
	}
}

function update(){
	if($("#insertForm").valid()){
		try{
			$("#tarScope").val(getTarScope());
			var url = _CONTEXT_PATH+"/qtydefManager!update.action";
			var mdata = Utils.convertParam('','insertForm');
			Utils.ajaxSubmit(url,mdata,function(result){
				$.dialogBox.info(result.retMsg,'提示');
				//Utils.removeTabById("updateMixQtyTar");
			},function(result){
				$.dialogBox.warn(result.retMsg,'提示');
				//Utils.removeTabById("updateMixQtyTar");
			});			
		}catch(e){}
	}
}