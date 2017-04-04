var zTree;
var rMenu = $("#rMenu");
var className = "dark";
var setting = {
	async: {
		enable: true,
		url: getMenuUrl,
		type: "post",
		dataType: "json"
	},
	edit: {
		/*
		drag: {
			prev: dropPrev,
			inner: dropInner,
			next: dropNext
			},
		*/
		enable: true,
		showRemoveBtn: false,
		showRenameBtn: false
	},
	data: {
		simpleData: {
			enable: true
		}
	},			
	view: {
		//dblClickExpand: false
		expandSpeed: ""
	},
	check: {
		enable: true
	},
	callback: {
		onRightClick: OnRightClick,
		/*beforeDrag: beforeDrag,
		beforeDrop: beforeDrop,
		onDrag: onDrag,
		onDrop: onDrop,*/
		beforeExpand: beforeExpand,
		onAsyncSuccess: onAsyncSuccess,
		onAsyncError: onAsyncError,
		onClick:zTreeOnClick
	}
};

function OnRightClick(event, treeId, treeNode) {
	zTree = $.fn.zTree.getZTreeObj(treeId);
	var flags = [1,1,1,1];
	if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
		zTree.cancelSelectedNode();
		if (treeId == 'sysTree') {
			return;
		}
		else {
			flags =[1,0,0,0];
		}
		showRMenu(flags,event.clientX, event.clientY);
	} else if (treeNode && !treeNode.noR) {
		zTree.selectNode(treeNode);
		if (treeId == 'sysTree') {
			if (treeNode.pId) {
				flags =[0,0,1,1];
			}
			else{
				flags =[0,0,1,0];
			}
		}
		else {
			if (treeNode.pId) {
				flags =[0,0,1,1];
			}
			else{
				flags =[0,1,1,0];
			}
		}
		showRMenu(flags,event.clientX, event.clientY);
	}
}

function showRMenu(flags,x, y) {
	if (flags[0] == 0) {
		$("#m_add_tree").hide();
	}
	else {
		$("#m_add_tree").show();
	}
	if (flags[1] == 0) {
		$("#m_del_tree").hide();
	}
	else {
		$("#m_del_tree").show();
	}
	if (flags[2] == 0) {
		$("#m_add").hide();
	}
	else {
		$("#m_add").show();
	}
	if (flags[3] == 0) {
		$("#m_del").hide();
	}
	else {
		$("#m_del").show();
	}
	$("#rMenu ul").show();
	$("#rMenu").css({"top":y+"px", "left":x+"px", "visibility":"visible"});
	$("body").bind("mousedown", onBodyMouseDown);
}
function hideRMenu() {
	rMenu = $("#rMenu");
	if (rMenu) rMenu.css({"visibility": "hidden"});
	$("body").unbind("mousedown", onBodyMouseDown);
}
function onBodyMouseDown(event){
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
		rMenu = $("#rMenu");
		rMenu.css({"visibility" : "hidden"});
	}
}

function addTreeNode() {
	hideRMenu();
	var pnode = zTree.getSelectedNodes()[0];
	$.ligerDialog.prompt('请输入菜单ID', function (yes,value) { 
		if(yes){
			if (!pnode) {
				pnode = {name:value,id:'',menuId:value};
			}
			zTree.addNodes(zTree.getSelectedNodes()[0], 
				[{ name:value,id:value,pid:pnode.id,data:{menuId:pnode.menuId,parentNode:pnode.id,nodeId:value,nodeVisible:'1',relaFlag:'1',hasChildFlag:'0'}}]);
		}
	});
}
function removeTreeNode() {
	hideRMenu();
	// zTree = $.fn.zTree.getZTreeObj(treeId);
	var nodes = zTree.getSelectedNodes();
	if (nodes && nodes.length>0) {
		if (nodes[0].childs && nodes[0].childs.length > 0) {
			var msg = "要删除的节点是父节点，请先删除子节点再删除父节点。";
			$.ligerDialog.alert(msg,'提示内容','warn');
			/*
			 * if (confirm(msg)==true){ zTree.removeNode(nodes[0]); }
			 */
		} else {
			try{
				//异步加载树菜单				
				var menuId = nodes[0].menuId;
				var nodeId = nodes[0].id;						
				// alert("menuId="+menuId+"&nodeId="+nodeId);
				// 获取表单值，并以json的数据形式保存到params中
				var params = "menuId="+menuId+"&nodeId="+nodeId;
				var url = "menuManager!deleteMenuModel.action?"+params;
				$.ajax({
					type: "POST",
					contentType: "application/json,charset=utf-8",
					dataType: "json",
					url: url,
					success: function (resData) {
						if(resData.state=='1'){
							zTree.removeNode(nodes[0]);
							$.ligerDialog.alert('删除成功','提示内容','success');
						}
						if(resData.state=='0'){
							$.ligerDialog.alert('删除失败','提示内容','warn');
						}
					},
					error: function (resData) {
						$.ligerDialog.alert('删除失败。请检查网络连接。。。','提示内容','error');
					}
				});
				
			}catch(e){	
				alert(e);
			}finally{
				//alert(2);
			}
		}
	}
}
//树的click事件
function zTreeOnClick(event, treeId, treeNode) {
	if (treeNode.data) {
		displayTreeNode(treeNode.data);
	}
	else{
		treeNodeLoadData(treeId, treeNode);
	}
}
function displayTreeNode(data) {
	$('#node_name').val(data.nodeName);
	$('#menuId').val(data.menuId);
	$('#nodeCmd').val(data.nodeCmd);
	$('#seqId').val(data.seqId);
	$('#nodeTooltip').val(data.nodeTooltip);
	$("input[name='hasChildFlag'][value="+data.hasChildFlag+"]").attr("checked",true);
	$('#nodeId').val(data.nodeId);
	$('#parentId').val(data.parentNode);
	$('#nodeUrl').val(data.nodeUrl);
	$("input[name='nodeVisible'][value="+data.nodeVisible+"]").attr("checked",true);
	$("input[name='relaFlag'][value="+data.relaFlag+"]").attr("checked",true);
	$('#nodeImg').val(data.nodeImg);
	$('#nodeTarget').val(data.nodeTarget);
}
function treeNodeLoadData(treeId, treeNode) {
	try{
		//异步加载树菜单
		var menuId = treeNode.menuId;
		var nodeId = treeNode.id;
		var nodeName = treeNode.name;
		var params = "menuId="+menuId+"&nodeId="+nodeId;
		var url = "menuManager!getTreeNodeModel.action?"+params;
		$.ajax({
			type: "POST",
			contentType: "application/json,charset=utf-8",
			dataType: "json",
			url: url,
			success: function (msg) {
				treeNode.data = msg;
				displayTreeNode(msg);
			},
			error: function (msg) {
				$.ligerDialog.alert('加载失败。请检查网络连接。。。','提示内容','error');
			}
		});
	}catch(e){
		//alert(e);
	}finally{
		//alert(2);
	}
}
//以下异步调用，查找子菜单方法
function getMenuUrl(treeId,treeNode) {			
	var menuId = treeNode.menuId;
	var nodeId = treeNode.id;			
	var param = "nodeId="+nodeId+"&menuId="+menuId;
	return "menuManager!getSubMenuModel.action?" + param;
}
function beforeExpand(treeId, treeNode) {
	zTree = $.fn.zTree.getZTreeObj(treeId);
	if (!treeNode.isAjaxing) {				
		ajaxGetNodes(treeNode, "refresh");
		return true;
	} else {
		$.ligerDialog.alert('正在下载数据中，请稍后展开节点。。。','提示内容','warn');	
		return false;
	}
}
function onAsyncSuccess(event, treeId, treeNode, msg) {
		if (!msg || msg.length == 0) {
			return;
		}
		zTree.updateNode(treeNode);
		zTree.selectNode(treeNode.childs[0]);		
		className = (className === "dark" ? "":"dark");				
}
function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {			
	$.ligerDialog.alert('异步获取数据出现异常。。。','提示内容','warn');	
	zTree.updateNode(treeNode);
}
function ajaxGetNodes(treeNode, reloadType) {			
	if (reloadType == "refresh"){		
		zTree.updateNode(treeNode);
	}
	zTree.reAsyncChildNodes(treeNode, reloadType, true);
}

//以下是拖拽方法

/*
 * var curDragNodes; function dropPrev(treeId, treeNode) { var pNode
 * =treeNode.getParentNode(); if (pNode && pNode.dropInner === false) { return
 * false; } else { for (var i=0,l=curDragNodes.length; i<l; i++) { var curPNode =
 * curDragNodes[i].getParentNode(); if (curPNode && curPNode !==
 * treeNode.getParentNode() && curPNode.childOuter === false) { return false; } } }
 * return true; } function dropInner(treeId, treeNode) { if (treeNode &&
 * treeNode.dropInner === false) { return false; } else { for (var
 * i=0,l=curDragNodes.length; i<l; i++) { if (!treeNode &&
 * curDragNodes[i].dropRoot === false) { return false; } else if
 * (curDragNodes[i].parentTId && curDragNodes[i].getParentNode() !== treeNode &&
 * curDragNodes[i].getParentNode().childOuter === false) { return false; } } }
 * return true; } function dropNext(treeId, treeNode) { var pNode
 * =treeNode.getParentNode(); if (pNode && pNode.dropInner === false) { return
 * false; } else { for (var i=0,l=curDragNodes.length; i<l; i++) { var curPNode =
 * curDragNodes[i].getParentNode(); if (curPNode && curPNode !==
 * treeNode.getParentNode() && curPNode.childOuter === false) { return false; } } }
 * return true; }
 * 
 * 
 * function beforeDrag(treeId, treeNodes) { className = (className === "dark" ?
 * "":"dark"); for (var i=0,l=treeNodes.length; i<l; i++) { if
 * (treeNodes[i].drag === false) { curDragNodes = null; return false; } else if
 * (treeNodes[i].parentTId && treeNodes[i].getParentNode().childDrag === false) {
 * curDragNodes = null; return false; } } curDragNodes = treeNodes; return true; }
 * function beforeDrop(treeId, treeNodes, targetNode, moveType) { className =
 * (className === "dark" ? "":"dark"); return true; } function onDrag(event,
 * treeId, treeNodes) { className = (className === "dark" ? "":"dark"); }
 * function onDrop(event, treeId, treeNodes, targetNode, moveType) { className =
 * (className === "dark" ? "":"dark"); }
 */