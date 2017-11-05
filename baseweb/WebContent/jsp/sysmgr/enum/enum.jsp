<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>枚举参数维护</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.left {
	float: left;
	width: 50%;
}

.right {
	float: left;
	width: 50%;
}
</style>
</head>
<body>
<div class="content">
		<div class="left">
			<fieldset class="queryBox"><legend>查询条件</legend>
				<table class='params'>
					<tr>
						<td>参数编码</td>
						<td><input id='qenumId' name='qenumId' type="text" /></td>
						<td>参数名称</td>
						<td><input id='qenumName' name="qenumName" type="text" /></td>
					</tr>
					<tr>
					<td colspan="4" style="height:28px;">
						<div style="float:right;">
							<input id='query_btn' name='query_btn'	type="button" value="查&nbsp;询" class="l-button" style="float:left;margin-right:5px;"/>
							<input id='reset_btn' name='reset_btn'	type='button' value='重&nbsp;置' class='l-button' style="float:left;margin-right:5px;"/>
						</div>
					</td>
				</tr>
				</table>
			</fieldset>
			<fieldset class="outbox">
				<div id='toptoolbar'></div>
				<div id='enumlist'></div>
			</fieldset>
		</div>
		<div class="right">
			<fieldset class="queryBox"><legend>枚举参数明细</legend>
				 <form id='detail'> 
					<table class='params'>
						<tr>
							<td nowrap="nowrap">参数编码</td>
							<td><input id='enumId' name='enumId' type="text" validate="{required:true}" /></td>
							<td nowrap="nowrap">参数名称</td>
							<td><input id='enumName' name='enumName' type="text" validate="{required:true}" /></td>
						</tr>
						<tr>
							<td>参数描述</td>
							<td><input id='enumDesc' type="text" /></td>
							<td colspan="2"></td>
						</tr>
					</table>
				 </form> 
			</fieldset>
			<fieldset class="outbox">
				<div id='toptoolbarOther'></div>
				<div id='enum_item_list'></div>
			</fieldset>
		</div>
	</div>
</body>
<script type="text/javascript">
    $(function () {
        Utils.validateInit();
        $("#toptoolbar").ligerToolBar({items:[
       		{text:'新增',name:'insert_btn',icon:'add',click:insertEnum},
     		{text:'删除',name:'delete_btn',icon:'delete',click:deleteEnum}
           ] 
		});
        $("#enumlist").ligerGrid({
            enumlist: _enum_params,
            checkbox: true,
            isSingleCheck :true,
            allowAdjustColWidth:false,
            selectRowButtonOnly:true,
            columns: [
            { display: '参数编码', name: 'enumId'  ,align:'left', width: 100 },
            { display: '参数名称', name: 'enumName' ,align:'left', width: 150 },
            { display: '参数描述', name: 'enumDesc' ,align:'left', width: 200  }
            ],
            pageSize:20,
            sortName: 'enumId', 
            width: '100%',
            height: '98%',
    		onError: function(e) {
    			Utils.toIndex(e);
    		},
            onSelectRow: function(row,idx,context){
                queryItemData(row);
            }
        });


        $("#toptoolbarOther").ligerToolBar({items:[
            {text:'添加值',name:'addItem',icon:'add',click:addNewRow},
            {text:'删除值',name:'delItem',icon:'delete',click:deleteRow},
            {text:'提交',name:'commitItem',icon:'submit',click:commitItem}
           ]
        });
        $("#enum_item_list").ligerGrid({
            usePager: false,
            enabledEdit:true,
            allowAdjustColWidth:false,
            enumlist: _enum_params,
            checkbox: true,
            columns: [
            { display: '顺序号', name: 'seqId',editor:{type:'int'},align:'left',  width: 50},
            { display: '枚举编码', name: 'itemId',editor:{type:'text'} ,align:'left',  width: 100},
            { display: '枚举名称', name: 'itemValue',editor:{type:'text'},align:'left',  width: 150},
            { display: '枚举描述', name: 'itemDesc',editor:{type:'text'} ,align:'left',  width: 200}
            
            ],
            pageSize:20,
            sortName: 'seqId', 
            width:'100%',
            height: '98%',
    		onError: function(e) {
    			Utils.toIndex(e);
    		}
        });

        $('#query_btn').bind('click', queryData);
        $('#reset_btn').bind('click', clearData);

    });
    function queryData() {
        var enumId = $("#qenumId").val();
        var enumName = $("#qenumName").val();
        //var enumDesc = $("#qenumName").val();
        var params = {
            dataAction:'server',
            dataType:'server',
            url: '${_CONTEXT_PATH}/sys/enum.action',
            newPage:1,
            parms:[{name:'qenumId',value:enumId}
            ,{name:'qenumName',value:enumName}
            ]
        };
        var gridManager = $("#enumlist").ligerGetGridManager(); 
        gridManager.setOptions(params);
        gridManager.loadData();
        clearDetail();
    }
    function clearDetail() {
        $('#enumId').val('');
        $('#enumName').val('');
        $('#enumDesc').val('');
        var gridManager = $("#enum_item_list").ligerGetGridManager(); 
        gridManager.setOptions({data:{rows:[]}});
        gridManager.loadData();
    }
    function clearData() {
        $(".queryBox input[type='text']").each(function(i,item){
            item.value ='';
        });
    }
    function insertEnum() {
        var url = 'jsp/sysmgr/enum/enumAdd.jsp';
        $.dialog.open(url,{
            id:"insert-enum",
            title:"新增枚举参数",
            height:'35%',width:'50%'
        });
    }
    function deleteEnum() {
        var grid = $("#enumlist").ligerGetGridManager();
        var rows = grid.getCheckedRows();
        if (rows.length < 1) {
            Utils.alert('请先选择需要删除的记录','提示内容');
            return ;
        }
        var mdata = {"deletes":JSON.stringify(rows)};
        var url = "${_CONTEXT_PATH}/sys/enum!delete.action";
        Utils.ajaxSubmit(url,mdata);
    }
    function queryItemData(aenum) {
        $('#enumId').val(aenum.enumId);
        $('#enumName').val(aenum.enumName);
        $('#enumDesc').val(aenum.enumDesc);
        var params = {
            dataAction:'server',
            dataType:'server',
            url: '${_CONTEXT_PATH}/sys/enum-detail!queryDetail.action',
            newPage:1,
            parms:[{name:'enumId',value:aenum.enumId}]
        };
        var gridManager = $("#enum_item_list").ligerGetGridManager(); 
        gridManager.setOptions(params);
        gridManager.loadData();
    }
    function commitItem() {
        if ( $("#detail").valid() ) {
        }
        else {
            return;
        }
        var grid = $("#enum_item_list").ligerGetGridManager();
        var rows = grid.getData();
        var mdata = {"items":JSON.stringify(rows)};
        mdata["po.enumId"] = $('#enumId').val();
        mdata["po.enumName"] = $('#enumName').val();
        mdata["po.enumDesc"] = $('#enumDesc').val();
        var url = "${_CONTEXT_PATH}/sys/enum-detail!commitDetail.action";
        Utils.ajaxSubmit(url,mdata);
    }
    function deleteRow()
    {
        var manager = $("#enum_item_list").ligerGetGridManager();
        manager.deleteSelectedRow();
    }
    function addNewRow()
    {
        var manager = $("#enum_item_list").ligerGetGridManager();
        manager.addRow();
    } 
</script>
</html>