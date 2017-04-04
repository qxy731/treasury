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
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false,
				renameTitle: "重命名"
			},
		 data: {
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId"
				}
			},
			view: {
				//dblClickExpand: false,
				expandSpeed: ""
			},
			check: {
				enable: false
			},
			callback: {
				onRightClick: OnRightClick,
				onRename:onRename,
				beforeExpand: beforeExpand,
				onAsyncSuccess: onAsyncSuccess,
				onAsyncError: onAsyncError,
				onClick:zTreeOnClick
			}
		};

function OnRightClick(event, treeId, treeNode) {
	zTree = $.fn.zTree.getZTreeObj(treeId);
	if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
		zTree.cancelSelectedNode();
		showRMenu("root", event.clientX, event.clientY);
	} else if (treeNode && !treeNode.noR) {
		zTree.selectNode(treeNode);
		showRMenu("node", event.clientX, event.clientY);
	}
}
function showRMenu(type, x, y) {
	if (type=="root") {
		hideRMenu();
	} else {
		$("#rMenu ul").show();
		$("#m_add").show();
		$("#m_del").show();
		$("#m_att").show();
		rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
		$("body").bind("mousedown", onBodyMouseDown);
	}
	
}
function hideRMenu() {
	if (rMenu) rMenu.css({"visibility": "hidden"});
	$("body").unbind("mousedown", onBodyMouseDown);
}
function onBodyMouseDown(event){
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
		rMenu.css({"visibility" : "hidden"});
	}
}

function addTreeNode() {
	hideRMenu();
	var pnode = zTree.getSelectedNodes()[0];	
	//var url = 'jsp/sysmgr/unit/unitAdd.jsp?superUnitId='+superUnitId+"&unitLevel="+unitLevel+"&superUnitName="+superUnitName;
	//alert(superUnitName);	
	var paramObj ={
			superUnitId : pnode.id,
			unitLevel : parseInt(pnode.unitLevel)+1,
			superUnitName : encodeURI(pnode.name)
	}; 
	var params = $.param(paramObj,true);
	var url = 'jsp/sysmgr/unit/unitAdd.jsp?'+params;
	$.ligerDefaults.DialogString.titleMessage='新增组织';
	$.ligerDialog.open({url:url,height: 240,width: 700, buttons: [ 
	   {text: '确定', onclick: function (item, dialog) {
		  if(dialog.frame.addUnit()){
			  var unitId = dialog.frame.document.getElementById("unitId").value;
			  var superUnitId = pnode.id;
			  var unitLevel = parseInt(pnode.unitLevel)+1;
			  var unitName = dialog.frame.document.getElementById("unitName").value;
			  var newNode = [{id:unitId,pId:superUnitId,name:unitName,isParent:false,data:{unitLevel:unitLevel,id:unitId,pId:superUnitId}}];
			  zTree.addNodes(pnode,newNode);
		  }else{
			  //alert(item.text);
		  }
		} },
		{ text: '取消', onclick: function (item, dialog) { dialog.close(); } }] });
}
  
  function queryTreeNode() {
		hideRMenu();
		var pnode = zTree.getSelectedNodes()[0];
		var paramObj ={
				unitId : pnode.id,
				opType: '0'
		}; 
		var params = $.param(paramObj,true);
		var url = 'unitManager!queryTreeNodeModel.action?'+params;
		$.ligerDefaults.DialogString.titleMessage='查看'+pnode.name+'详细';
		$.ligerDialog.open({url:url,height: 300,width: 450});
	}
  function editTreeNode() {
		hideRMenu();
		var pnode = zTree.getSelectedNodes()[0];
		var paramObj ={
				unitId : pnode.id,
				opType: '1'
		}; 
		var params = $.param(paramObj,true);
		var url = 'unitManager!queryTreeNodeModel.action?'+params;
		$.ligerDefaults.DialogString.titleMessage='编辑'+pnode.name;
		$.ligerDialog.open({url:url,height: 200,width: 700, buttons: [
		 {text: '确定', onclick: function (item, dialog) {
			 //alert(dialog.frame.editUnit());
		 if(dialog.frame.editUnit()){
			  //pnode.name = dialog.frame.document.getElementById("unitName").value;
			  //alert(pnode.name);
			  //zTree.updateNodes(pnode);
		 }else{//alert(item.text);
		  }
		  }},{text:'取消',onclick:function(item,dialog){dialog.close();}}]});
	}

function removeTreeNode() {
			hideRMenu();
			//zTree = $.fn.zTree.getZTreeObj(treeId);
			var nodes = zTree.getSelectedNodes();
			if (nodes && nodes.length>0) {
				if (nodes[0].childs && nodes[0].childs.length > 0) {
					var msg = "要删除的节点是父节点，请先删除子节点再删除父节点。";
					$.ligerDialog.alert(msg,'提示内容','warn');
				} else {
					$.ligerDialog.confirm("你确定要删除当前机构，如果继续，请点击“确定”按钮。", function (yes) {
						if(yes){
						//异步加载树菜单
						var unitId = nodes[0].id;
						var params = "unitId="+unitId;
				        var url = "unitManager!deleteUnitModel.action?"+params;
							$.ajax({
						        type: "POST",
						        contentType: "application/json,charset=utf-8",
						        dataType: "json",
						        url: url,
						        success: function (resData) {
						        	//alert("true"+resData.state);
						        	if(resData.state=='1'){
						        		zTree.removeNode(nodes[0]);
						        		$.ligerDialog.alert('删除成功','提示内容','success');
						        	}if(resData.state=='0'){
						        		$.ligerDialog.alert('删除失败','提示内容','warn');
						        	}
						        },error: function (resData) {
						        	$.ligerDialog.alert('删除失败。请检查网络连接。。。','提示内容','error');
						        }
						    });
						}
					});
				}
			}
		}	
function onRename(event, treeId, treeNode) {
			className = (className === "dark" ? "":"dark");
			try{
				//异步加载树菜单
				var unitId = treeNode.id;
				var unitName = treeNode.name;
				//alert("menuId="+menuId+"&nodeId="+nodeId);
				//获取表单值，并以json的数据形式保存到params中
	            var params = "unitId="+unitId+"&unitName="+encodeURI(encodeURI(unitName));
	            var url = "unitManager!updateUnitNameModel.action?"+params;
				$.ajax({
			        type: "POST",
			        contentType: "application/json,charset=utf-8",
			        dataType: "json",
			        url: url,
			        success: function (resData) {
			        	//alert("true"+resData.state);
			        	if(resData.state=='1'){
			        		$.ligerDialog.alert('重命名成功','提示内容','success');
			        	}if(resData.state=='0'){
			        		$.ligerDialog.alert('重命名失败','提示内容','warn');
			        	}
			        },error: function (resData) {
			        	$.ligerDialog.alert('重命名失败。请检查网络连接。。。','提示内容','error');
			        }
			    });
			}catch(e){
				alert(e);
			}finally{
				//alert(2);
			}
		}
		
		//树的click事件
		function zTreeOnClick(event, treeId, treeNode) {
			   try{
					//异步加载树菜单
					var unitId = treeNode.id;
					//alert("menuId="+menuId+"&nodeId="+nodeId);
					var params = "unitId="+unitId;
					var url = "unitManager!getSubUnitsGridModel.action?"+params;
					$.ajax({
				        type: "POST",
				        contentType: "application/json,charset=utf-8",
				        dataType: "json",
				        url: url,
				        success: function (msg) {
				        	var jsonObj = {};
				        	jsonObj.rows = msg;
				        	var gridManager = $("#unitlist").ligerGetGridManager();
				        	gridManager.setOptions({ data: jsonObj });
				        	gridManager.loadData();
				        },
				        error: function (msg) {
				        	$.ligerDialog.alert('加载失败。请检查网络连接。。。','提示内容','error');
				        }
				    });
				}catch(e){
					alert(e);
				}finally{
					//alert(2);
				}
		}
		
		//以下异步调用，查找子菜单方法
		function getMenuUrl(treeId,treeNode) {
			var unitId = treeNode.id;
			var param = "unitId="+unitId;
			return "unitManager!getSubUnitsListModel.action?" + param;
		}
		function beforeExpand(treeId, treeNode) {
			zTree = $.fn.zTree.getZTreeObj(treeId);
			if (!treeNode.isAjaxing) {
				ajaxGetNodes(treeNode, "refresh");
				return true;
			} else {
				//alert("正在下载数据中，请稍后展开节点。。。");
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
			//alert("异步获取数据出现异常。");
			$.ligerDialog.alert('异步获取数据出现异常。。。','提示内容','warn');	
			zTree.updateNode(treeNode);
		}
		function ajaxGetNodes(treeNode, reloadType) {
			if (reloadType == "refresh"){
				zTree.updateNode(treeNode);
			}
			zTree.reAsyncChildNodes(treeNode, reloadType, true);
		}
		
		$(function (){
			//面板
			var height = $(window).height();
			//alert(height);	
		    $("#accordion1").ligerAccordion({ height: height, speed: null});
		    $(".l-link").hover(function (){
		        $(this).addClass("l-link-over");
		    }, function (){
		        $(this).removeClass("l-link-over");
		    });
		    $("#accordion2").ligerAccordion({ height: height, speed: null});
		    $(window).resize(function(){
		    	var accordion = $("#accordion1").ligerGetAccordionManager();
				var height = $(window).height();
			    accordion.setHeight(height);
			    accordion = $("#accordion2").ligerGetAccordionManager();
			    accordion.setHeight(height);
		    	});
		});