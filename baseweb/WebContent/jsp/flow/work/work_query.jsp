<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工作任务</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.inbox ,.outbox {padding: 5px;margin: 2px;border: 1px solid #ccc;}
.params td {background-color: #E2EAFF; padding:2px 2px 2px 10px;}
.l-button {display:inline;}
</style>
</head>
<body>
<n:page action='com.soule.app.flow.work.WorkAction' />
<table class='content'>
	<tr>
		<td>
		<form id="myform" action="${_CONTEXT_PATH}/Work!query.action">
			<fieldset class="queryBox"><legend>查询条件</legend>
			<table class='params'>
				<tr>
					<td>用户ID </td><td><input type='text' id='userId' name='userId'/></td>
					<td>任务ID </td><td><input type='text' id='taskID' name='taskID'/></td>
					<td>任务名称 </td><td><input type='text' id='taskName' name='taskName'/></td>
				</tr>
			</table>
			</fieldset>
		</form>
		</td>
	</tr>
	<tr>
		<td>
			<table class="s1-button">
					<tr>
						<td>
							<input id='query' name='query' type='button' value='查&nbsp;询' class='l-button'/>
						</td>
						<td>
							<input id='reset' name='reset' type='button' value='重&nbsp;置' class='l-button'/>
						</td>
					</tr>
			</table>
		</td>
	</tr>
	<tr><td>
		<fieldset class="outbox"><legend>待办任务列表</legend>
			<div id='workItemlist'></div>
	</fieldset>
	</td></tr>
</table>
</body>
<script type='text/javascript'>
    $(function () {
        //输出表格
        $("#workItemlist").ligerGrid({
                buttons:[
                    {text:'打开',name:'open_btn',clazz:'open_btn'}
                ],
                columns: [
                { display: '任务ID', name: 'taskId', align: 'left',width:220 },
                { display: '任务名称', name: 'taskName', align: 'center' },
                //{ display: '任务类型', name: 'taskType', align: 'center' },
                { display: '创建时间', name: 'createTime', align: 'center',width:150 },
                { display: '业务分类', name: 'bizType', align: 'center' },
                { display: '办理方式', name: 'handleType', align: 'center' }
            ],
            pageSize:10,
            sortName: 'taskId',
            width: '100%',
    		onError: function(e) {
    			Utils.toIndex(e);
    		}
        });
        $('#reset').bind('click', doClear);
        $('#query').bind('click', execute);
        $('#open_btn').bind('click', doOpen);
        execute();
    
    });
    function doClear() {
        $(".inbox input[type='text']").each(function(i,item){
            item.value ='';
        });
    }
    function doOpen() {
    	var manager = $("#workItemlist").ligerGetGridManager();
    	var data = manager.getSelectedRow();
    	var url = "${_CONTEXT_PATH}" + data.appUrl + "?wfid=" + data.taskId + "&pcid=" + data.procId;
    	Utils.openTab(data.taskId,data.taskName,url);
    }

    function execute() {
    	var pp = Utils.convertParam('queryIn','myform');
		var params = {
			dataAction:'server',
			dataType:'server',
			url: '${_CONTEXT_PATH}/flow/work!query.action',
			newPage:1,
			parms:pp
		};
		var gridManager = $("#workItemlist").ligerGetGridManager(); 
		gridManager.setOptions(params);
		gridManager.loadData();
	}
    

</script>
</html>