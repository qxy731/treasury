var zTree1;
var setting;
var zNodes=[];

var setting = {		
		check: {
			enable: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		view: {
			expandSpeed: ""
		},
		callback:{
			onClick:zTreeOnClick
		}
	};

/*$(document).ready(function() {		
	zNodes=${zNodes};		
	//alert(zNodes);
	$.fn.zTree.init($("#treespgroup"), setting, zNodes);		
});*/

//树的click事件
function zTreeOnClick(event, treeId, treeNode) {	
	if(treeNode.href==undefined||treeNode.href==""){
		return;
	}
	try{		
		f_addTab(treeNode.tId , treeNode.name, treeNode.href);		
	}catch(e){	
		//alert(e);
	}finally{
		//alert(2);
	}	
}