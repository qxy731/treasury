<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>业务线人员分配</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.content {width:100%; height: 100%;}
.detailBox {height: 100%;}
.nbutton {width:80px;}
body {width :800px;}
/*body {width :850px;}*/
.bizass {margin-top: 5px}
</style>
</head>
<n:page action='com.soule.app.sys.bizline.blass.BlassAction' />
<body>
<table class='content'>
	<tr>
		<td>
			<form id="myform" action="${_CONTEXT_PATH}/Blass!query.action">
			<fieldset class="detailBox"><legend>当前已选人员</legend>
				<table>
					<tr>
						<td>业务线类型<input id='bizTypeId' name='bizTypeId' type='hidden' value='${param.bltid}'/></td><td>${param.bltid}</td>
						<td>业务线分类<input id='bizValueId' name='bizValueId' type='hidden' value='${param.blvid}'/></td><td>${param.blvid}</td>
						<td style="padding-left: 5px"><input id='refesh_btn' name='refesh_btn' type='button' value='手动刷新' class='l-button'/></td>
					</tr>
				</table>
				<div id='bizasslist' class="bizass"></div>
			</fieldset>
			</form>
		</td>
		<td>
			<fieldset class="detailBox"><legend>操作</legend>
				<input id='addall_btn' type='button' value='<<添加全部' class='l-button'/><br/>
				<input id='addsel_btn' type='button' value='<添加' class='l-button'/><br/>
				<input id='remvoesel_btn' type='button' value='>移除' class='l-button'/><br/>
				<input id='removeall_btn' type='button' value='>>移除全部' class='l-button'/><br/>
			</fieldset>
		</td>
		<td>
			<fieldset class="detailBox"><legend>待选人员</legend>
			<jsp:include page="/jsp/common/staffquery_inter.jsp?dup=true"></jsp:include>
			</fieldset>
		</td>
	</tr>
</table>
</body>
<script type='text/javascript'>
$(function () {

	//输出表格
	$("#bizasslist").ligerGrid({
		columns: [
			//{ display: '业务线类别', name: 'bizTypeId', align: 'center' },
			//{ display: '业务线分类值', name: 'bizValue', align: 'center' },
			{ display: '员工ID', name: 'staffId', align: 'center',width:120},
			{ display: '员工姓名', name: 'staffName', align: 'center',width:100 },
			{ display: '创建人', name: 'createUser', align: 'center' ,width:100}
			//{ display: '创建时间', name: 'createTime', align: 'center' },
			//{ display: '业务线类型名', name: 'bizTypeName', align: 'center' },
			//{ display: '业务线分类名', name: 'bizName', align: 'center' }
		],
		checkbox:true,
		pageSize:10,
		enabledSort:false,
		height: 350,
		width:400,
		onError: function() {
			$.dialogBox.alert("查询数据失败");
		}
	});

	$('#refesh_btn').bind('click', executeQuery);
	$('#addall_btn').bind('click', addAll);
	$('#addsel_btn').bind('click', addSelected);
	$('#remvoesel_btn').bind('click', removeSelected);
	$('#removeall_btn').bind('click', removeAll);
	executeQuery();
});

function addAll() {
	var sc = getQueryStaffCount();
	if (sc == 0) {
		$.dialogBox.alert('请先执行待选人员中的查询');
		return;
	}
	if (sc > 500) {
		$.dialogBox.alert('你选择全部人员数量为'+sc+',运行时间较长，<br\>建议分批处理，是否确认执行?',function(){
			executeAddAll();
		},true);
	}
	else {
		executeAddAll();
	}
}
function executeAddAll() {
	var mydata = Utils.convertFormData('','myform');
	var condition = getQueryStaffCondition();
	$.extend(condition,mydata);
	var mdata = Utils.convertObjectData('insertAllIn',condition);
	var url = "${_CONTEXT_PATH}/sys/blass!insertAll.action";
	Utils.ajaxSubmit(url,mdata,function(result) {
		$.dialogBox.info(result.retMsg);
		executeQuery();
	});
}
function removeSelected() {
	var manager = $('#bizasslist').ligerGetGridManager();
	var selected = manager.getCheckedRows();
	if (selected.length > 0){
		var datas = [];
		for (var x = 0 ; x< selected.length; x++) {
			var one = {};
			one.staffId = selected[x].staffId;
			one.bizTypeId = $('#bizTypeId').val();
			one.bizValue =  $('#bizValueId').val();
			datas.push(one);
		}
		var mdata = {};
		mdata['deleteIn.bizassStr'] = JSON.stringify(datas);
		var url = "${_CONTEXT_PATH}/sys/blass!delete.action";
		$.dialogBox.alert('确认删除选择的'+selected.length+'条记录吗?',function(){
			Utils.ajaxSubmit(url,mdata,function(result) {
				$.dialogBox.info(result.retMsg);
				executeQuery();
			});
		},true);
	}
}

function removeAll() {
	$.dialogBox.alert('确认删除全部记录吗?',function(){
		var mdata = Utils.convertFormData('deleteAllIn','myform');
		var url = "${_CONTEXT_PATH}/sys/blass!deleteAll.action";
		Utils.ajaxSubmit(url,mdata,function(result) {
			$.dialogBox.info(result.retMsg);
			executeQuery();
		});
	},true);
}

function addSelected() {
	var selected = select();
	if (selected.length > 0){
		var datas = [];
		for (var x = 0 ; x< selected.length; x++) {
			var one = {};
			one.staffId = selected[x].staffId;
			one.bizTypeId = $('#bizTypeId').val();
			one.bizValue =  $('#bizValueId').val();
			datas.push(one);
		}
		var mdata = {};
		mdata['insertIn.bizassStr'] = JSON.stringify(datas);
		var url = "${_CONTEXT_PATH}/sys/blass!insert.action";
		Utils.ajaxSubmit(url,mdata);
	}
}

function executeQuery() {
	var formdata = Utils.convertParam('queryIn','myform');
	var params = {
		dataAction:'server',
		dataType:'server',
		url: '${_CONTEXT_PATH}/sys/blass!query.action',
		newPage:1,
		parms:formdata
	};
	var gridManager = $("#bizasslist").ligerGetGridManager(); 
	gridManager.setOptions(params);
	gridManager.loadData();
}

</script>
</html>