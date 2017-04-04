<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <div class="recentTitle font4">
          <div class="reLeft"><a class="blankLeft">近期办理</a><div class="reRight font3">更多</div><div class="close reRight font3">关闭</div></div>
        </div>
        <div class="panal2Main">
             <div id='loglist'></div>
        </div>

<script type="text/javascript">
function execute() {
	var mdata = [{name:'queryIn.log.ipAddr',value:'127.0.0.1'}];
	var params = {
		dataAction:'server',
		dataType:'server',
		url: '${_CONTEXT_PATH}/sys/audit-log!query.action',
		newPage:1,
		parms:mdata,
		onError: function() {
			$.dialogBox.error("查询数据失败");
		}
	};
	var gridManager = $("#loglist").ligerGetGridManager(); 
	gridManager.setOptions(params);
	gridManager.loadData();
}
$("#loglist").ligerGrid({
	enumlist: _enum_params ,
	columns: [
		{ display: '执行人', name: 'operStaffName', align: 'left' },
		{ display: '操作名称', name: 'operName', align: 'left' },
		{ display: '客户端IP', name: 'ipAddr', align: 'left'},
		{ display: '业务类型', name: 'bizType', align: 'left' ,codetype:'log_biz_type' },
		{ display: '操作类型', name: 'funcType', align: 'left' ,codetype:'log_func_type'},
		{ display: '执行结果', name: 'execResult', align: 'left' ,codetype:'log_exec_result'},
		{ display: '执行时间', name: 'execTime', align: 'left' }
	],
	usePager:false,
	pageSize:10,
	enabledSort:false,
	heightDiff:80,
	onError: function() {
		$.dialogBox.error("查询数据失败",'提示');
	},
	onDblClickRow:function(row,id,x) {
	}
});

execute();
</script>
        