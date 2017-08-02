<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>prdTree</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style>
#query,#reset {width:60px;}
.params {width:100%;}
</style>
</head>
<body>
<n:page action='com.soule.app.common.selectunit.SelectUnitAction'/>	
<table >
<tr><td>
<form id="myform">
<table class='params'>
<tr><td align="right">部门编号</td><td><input type="text" id="unitId" name="unitId"/><input type="hidden" id="type" name="type"/></td><td>
<input id='query' name='query' type='button' value='查询' class='l-button'/></td></tr>
<tr><td align="right">部门名称</td><td><input type="text" id="unitName" name="unitName"/></td><td><input id='reset' name='reset' type='button' value='重置' class='l-button'/></td></tr>
</table>
</form>
</td></tr>
<tr>
<td>
<span id="panelTd">
<div style="width:340px; height:320px; margin:5px; float:left; border:1px solid #ccc; overflow:auto;">
<ul id="unitTree"></ul>
</div>
</span>
</td>
</tr>
</table>
</body>
<script type='text/javascript'>
var manager;

$(function () {
	$('#reset').bind('click', doClear);
	$('#query').bind('click', execute);
	
	var resultObj=${resultUnit};
	$('#type').val(resultObj.type);
	var data=[];
	manager=$("#unitTree").ligerTree({ 
        checkbox: false, 
        idFieldName :'unitId',
        textFieldName:'unitName',
        parentIcon: null, 
        childIcon: null,
        nodeWidth:200,
        onSelect: onSelect,
        isLeaf:function(nodedata) {
            //有没有子节点
            return nodedata.leafFlag == '0';
        }
     });


	/* var url = '${_CONTEXT_PATH}/sys/select-unit!queryUnit.action';
	Utils.ajaxSubmit(url,{"queryUnitIn.unitId":resultObj.unitId}, function(result){
		var ndata = result.rows;
		for (var x = 0 ; x< ndata.length ; x++) {
			if (ndata[x].leafFlag == '0') {
				ndata[x].children =[];
				ndata[x].isexpand ='false';
			}
		}
		manager.append(null,result.rows);
	}); */
});
function doClear() {
	$(".params input[type='text'], .params select").each(function(i,item){
		item.value ='';
	});
}

function clearPanel(){
	$('#panelTd').empty();
	$('#panelTd').append("<div style='width:340px; height:320px; margin:5px; float:left; border:1px solid #ccc; overflow:auto;'><ul id='unitTree'></ul></div>");
	manager=$("#unitTree").ligerTree({ 
        checkbox: false, 
        idFieldName :'unitId',
        textFieldName:'unitName',
        parentIcon: null, 
        childIcon: null,
        onSelect: onSelect,
        nodeWidth:200 
     });
}

function execute() {
	if (!$('#myform').valid()){
		return;
	}
	clearPanel();
	//单记录数据
	var mdata = Utils.convertParam('queryUnitIn','myform');
	
	var url = "${_CONTEXT_PATH}/sys/select-unit!queryUnit.action";
	
	Utils.ajaxSubmit(url,mdata, function(result){
		var ndata = result.rows;
		for (var x = 0 ; x < ndata.length ; x++) {
			if (ndata[x].leafFlag == '0') {
				ndata[x].children =[];
				ndata[x].isexpand ='false';
			}
		}
		manager.render= onSelect;
		manager.append(null,result.rows);
		
	});
}

function onSelect(node,e){
 
	if (node.data.children && node.data.children.length == 0){
		var params = {"queryUnitIn.superUnitId":node.data.unitId};
		var url = '${_CONTEXT_PATH}/sys/select-unit!queryUnit.action';
		Utils.ajaxSubmit(url,params, function(result) {
			var ndata = result.rows;
			for (var x = 0 ; x< ndata.length ; x++) {
				if (ndata[x].leafFlag == '0') {
					ndata[x].children =[];
					ndata[x].isexpand ='false';
				}
			}
			manager.append(node.target, ndata);
			e.update();
		});
	}
}
function select(){
	var rows = manager.getSelected();
	///alert(JSON.stringify(rows));
	if(rows){
		return rows;
	}else{
		$.dialogBox.warn("请选择部门");
	}
	
}
</script>
</html>