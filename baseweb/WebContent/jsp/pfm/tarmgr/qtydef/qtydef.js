var ctx;
$(function (){
	//获取web路径
	ctx=$("input[name='ctx']").val();
});


function openWindow(url,width,height){
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
}
