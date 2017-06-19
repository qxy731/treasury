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
/* .params td {background-color: #E2EAFF; padding:2px 2px 2px 10px;}
.params1 td {background-color: #E2EAFF; padding:2px 2px 2px 2px;}
.xcontent {
	width: 100%;
	
}
.content1 {
    width:100%;
}

.content2 {
    width:100%;
}
.left {
 padding: 10px;
}

.right {
 padding: 10px;
}
.mytoolbar {
    margin:0;
    overflow: hidden;
}
.mytoolbar {height:24px;background: url("${_CONTEXT_PATH}/jwebui/skins/sys/images/panel/panel-toolbar.jpg") repeat-x scroll 0 0 #CEDFEF;border:1px solid #EFF7F7;  border-top:1px solid #9CBAE7; border-left: 1px solid #9CBAE7;border-right: 1px solid #9CBAE7;}
.mytoolbar .l-icon {left: 2px; position: absolute; top: 1px; }
.mytoolbar .l-toolbar-item-hasicon {_margin-left: 4px;padding-left:20px;_margin-top:2px}
.mytoolbar .l-toolbar-item-disable{display: none} */


.xcontent {
	width: 100%;
	height: 100%;
	/* display: inline; */
}

.left {
	margin: 5px 0 0 20px;
	float: left;
	padding: 0;
	width: 45%;
}

.right {
	margin: 5px 0 0 20px;
	float: left;
	padding: 0;
	width: 50%;
}

.params tbody tr td {
	background-color: #EAEFFF;
	padding: 1px 1px 1px 10px;
	line-height: 28px;
}

.params input, .params select {
	width: 200px;
	border: 1px solid #b3bcFF;
	border-radius: 2px 2px 2px 2px;
	vertical-align: bottom;
}

.params input {
	padding-left: 4px;
	padding-bottom: 4px;
	width: 141px;
}

.params select {
	height: 22px;
}

.params input {
	height: 16px;
}

.params {
	width: 100%;
}

.params1 {
	width: 100%;
}
.params1 tbody tr td {
	background-color: #EAEFFF;
	padding: 1px 1px 1px 10px;
	line-height: 28px;
}

.params1 input, .params select {
	width: 200px;
	border: 1px solid #b3bcFF;
	border-radius: 2px 2px 2px 2px;
	vertical-align: bottom;
}

.params1 input {
	padding-left: 4px;
	padding-bottom: 4px;
	width: 141px;
}

.params1 select {
	height: 22px;
}

.params1 input {
	height: 16px;
}

.params1 {
	width: 100%;
}

#operUnitname {
	width: 198px;
}

#lefttoptoolbar {
	wdith: 100%
}

#righttoptoolbar {
	wdith: 100%
}

.mytoolbar {
	height: 24px;
	background:
		url("${_CONTEXT_PATH}/jwebui/skins/sys/images/panel/panel-toolbar.jpg")
		repeat-x scroll 0 0 #CEDFEF;
	border: 1px solid #EFF7F7;
	border-top: 1px solid #9CBAE7;
	border-left: 1px solid #9CBAE7;
	border-right: 1px solid #9CBAE7;
	margin-top: 10px
}

.mytoolbar .l-icon {
	left: 2px;
	position: absolute;
	top: 1px;
}

.mytoolbar .l-toolbar-item-hasicon {
	_margin-left: 4px;
	padding-left: 20px;
	_margin-top: 2px
}

.mytoolbar .l-toolbar-item-disable {
	display: none
}

#staffName, #staffId, #unitname, #roleId {
	width: 120px;
}
</style>
</head>
<body>
<div class="xcontent">
		<div class="left">
			<fieldset class="queryBox">
				<legend>查询条件</legend>
				<table class='params'>
					<tr>
						<td>参数编码</td>
						<td><input id='qenumId' name='qenumId' type="text" /></td>
					</tr>
					<tr>
						<td>参数名称</td>
						<td><input id='qenumName' name="qenumName" type="text" /></td>
					</tr>

				</table>
			</fieldset>
			<table width="100%">
				<tr>
					<td><br /></td>
				</tr>
				<tr>
					<td></td>
					<td align="right"><input id='query_btn' name='query_btn'
						type="button" value="查&nbsp;&nbsp;询" class="l-button" /></td>
					<td width="5%"></td>
					<td width="5%"></td>
					<td align="left"><input id='reset_btn' name='reset_btn'
						type='button' value='重&nbsp;&nbsp;置' class='l-button' /></td>
					<td></td>
				</tr>
				<tr>
					<td><br /></td>
				</tr>
			</table>
			<fieldset class="detailBox">
				<div id='toptoolbar9' class='mytoolbar'></div>
				<div id='enumlist'></div>
			</fieldset>
		</div>
		<div class="right">
			<fieldset class="queryBox" style="width=100%;">
				<legend>枚举参数明细</legend>
				 <form id='detail'> 
					<table class='params1'>
						<tr>
							<td nowrap="nowrap">参数编码</td>
							<td><input id='enumId' name='enumId' type="text" validate="{required:true}" /></td>
	
						</tr>
						<tr>
							<td nowrap="nowrap">参数名称</td>
							<td><input id='enumName' name='enumName' type="text" validate="{required:true}" /></td>
						</tr>
						<tr>
							<td>参数描述</td>
							<td><input id='enumDesc' type="text" /></td>
						</tr>
					</table>
				 </form> 
			</fieldset>
			<table width="100%">
				<tr>
					<td><br /></td>
				</tr>
			</table>
			<fieldset class="detailBox">
				<div id='toptoolbarOther' class='mytoolbar'></div>
				<div id='enum_item_list'></div>
			</fieldset>
		</div>
	</div>
</body>
<script type="text/javascript">
    $(function () {
        Utils.validateInit();
        $("#toptoolbar9").ligerToolBar({items:[
       		{text:'新&nbsp;&nbsp;增',name:'insert_btn',icon:'add',click:insertEnum},
     		{text:'删&nbsp;&nbsp;除',name:'delete_btn',icon:'delete',click:deleteEnum}
           ] 
		});
        $("#enumlist").ligerGrid({
            enumlist: _enum_params,
            checkbox: true,
            allowAdjustColWidth:false,
            selectRowButtonOnly:true,
            
            columns: [
            { display: '参数编码', name: 'enumId'  , width: '30%' },
            { display: '参数名称', name: 'enumName' , width: '30%' },
            { display: '参数描述', name: 'enumDesc' , width: '40%'  }
            ],
            pageSize:20,
            sortName: 'enumId', 
            width: '100%',
            height: '98%',
            onError: function() {
                alert("查询数据失败");
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
            { display: '顺序号', name: 'seqId',editor:{type:'int'}, width: '25%' },
            { display: '枚举编码', name: 'itemId',editor:{type:'text'} , width: '25%'},
            { display: '枚举名称', name: 'itemValue',editor:{type:'text'}, width: '25%'},
            { display: '枚举描述', name: 'itemDesc',editor:{type:'text'} , width: '25%'}
            
            ],
            pageSize:20,
            sortName: 'seqId', 
            width: '100%',
            height: '98%',
            onError: function() {
                alert("查询数据失败");
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
            height:'35%',width: 500
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