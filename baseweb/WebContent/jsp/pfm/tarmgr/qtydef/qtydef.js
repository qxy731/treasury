
/*function openWindow(url,width,height){
	$.ligerDialog.open({
		url : url,
		height : height,
		width : width,
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				dialog.frame.add(item,dialog);
			}
		}, {
			text : '取消',
			onclick : function(item, dialog) {
				dialog.close();
			}
		}]
	});
}*/

//新增基础定量指标
function insertBaseQty() {
	var url = _CONTEXT_PATH+'/jsp/pfm/tarmgr/qtydef/baseQtydefAdd.jsp';
	$.dialog.open(url,{id:"insertBaseQtyTar",title:"新增基础指标",height:270,width:635});
}

//新增复合定量指标
function insertMixQty() {
	var url = _CONTEXT_PATH+'/jsp/pfm/tarmgr/qtydef/mixQtydefAdd.jsp';
	$.dialog.open(url,{id:"insertMixQtyTar",title:"新增衍生指标",height:520,width:750});
}

//修改基础定量指标
function updateBaseQty(tarCode,tarType){
	var url = _CONTEXT_PATH+'/jsp/pfm/tarmgr/qtydef/baseQtydefUpd.jsp?tarCode='+tarCode+"&ts=" + new Date().getTime();
	$.dialog.open(url,{id:"updateBaseQtyTar",title:"修改基础指标",height:360,width:740});
}
//修改衍生指标
function updateMixQty(tarCode,tarType){
  var url = _CONTEXT_PATH+'/jsp/pfm/tarmgr/qtydef/mixQtydefUpd.jsp?tarCode='+tarCode+"&ts=" + new Date().getTime();
  $.dialog.open(url,{id:"updateMixQtyTar",title:"修改衍生指标",height:600,width:740});
}