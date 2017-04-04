var gridRight = null;
var gridStf = null;
var gridTar = null;
$(function () {
	Utils.validateInit();
	$('#reset').bind('click',doClear);
	$('#tarQuery').bind('click',execute);
	$("#storeDate").ligerDateEditor();
	//$('#staffSel_btn').bind('click',staffImport);
	//$('#staffTargetQuery').bind("click",staffTargetQuery);
	//$('#staffTargetSave').bind("click",staffTargetSave);
	//可选择指标
	gridTar = $("#tarlist").ligerGrid({
		columns: [
			//{ display: '指标代码', name: 'tarCode', align: 'center' },
			{ display: '指标名称', name: 'tarName', align: 'left',width:230}
			//{ display: '指标日期', name: 'storeDate', align: 'center' }
		],
		width:250,height:150,
		usePager: false,
		checkbox: true,
		data:{rows:[]},
		onError: function() {
			$.dialogBox.alert("查询数据失败",'提示');
		}
	});
	//已选择指标
	gridRight = $("#rightSelList").ligerGrid({
        usePager: false,
        columns: [
        { display: '指标名称', name:'tarName', align:'left',width:230}
        ],
        checkbox: true,
        data:{rows:[]},
        width:250,height:150
        });
	//已选择员工
	gridStf = $("#staffSelList").ligerGrid({
        usePager: false,
        columns: [
        { display: '员工编号', name:'staffId',align:'left',width:80},
        { display: '员工姓名', name:'staffName', align:'left',width:100}
        ],
        checkbox: true,
        data:{rows:[]},
        width:200,height:150
        });
});

function doClear() {
	$("#storeDate").val("");
}

function execute() {
	if (!$('#myform').valid()){
		return;
	}
	//单记录数据
	var mdata = Utils.convertParam('tarQueryIn','myform');
	//单记录数据
	var params = {
			dataAction:'server',
			dataType:'server',
			url: _CONTEXT_PATH + '/Qtytarstaff!tarQuery.action',
			newPage:1,
			parms:mdata,
			onError: function() {
				$.dialogBox.error("查询数据失败",'提示');
			}
		};
		gridTar.setOptions(params);
		gridTar.loadData();
}
/**
 * =========================
 * 查询员工指标数据
 */
//动态增加列
function convertColumnParam() {
	var rows =  gridRight.getData();
	var ret = [ {
		display : '员工编号',
		name : 'staffId',
		align : 'left',
		width : 100
	}, {
		display : '员工姓名',
		name : 'staffName',
		align : 'left',
		width : 100
	} ];	
	for ( var i = 0; i < rows.length; i++) {
		if(rows[i].opstatus=='insert'&&rows[i].__status=='add'){
			var one = {
				align : 'right',
				width : 200,
				display : rows[i].tarName,
				name : 'TARVALUE' + (i+1)*1,
				editor:{type:'text'},
				type: 'float',
				render:function(row){
					if(!row||row==null||row==undefined||row.length==0){
						return null;
					}else{
						var colName = this.name;
						var fvalue = fmoney(eval('row.'+colName),2);
						fvalue = retObjValue(fvalue,null);
						return fvalue;
					}
				}
			};			
			ret.push(one);			
		}
	}
	return ret;
}
var gridStfQuery = null;
function initStaffQueryGrid(){
	var columns = convertColumnParam();	
	 //alert(JSON.stringify(columns));
	 //$("#staffTarQuery").empty();
	 $("#staffQueryDiv").empty();
	 $("#staffQueryDiv").html("<div id='staffTarQuery' style='overflow:auto;'></div>");
	 gridStfQuery = $("#staffTarQuery").ligerGrid({
       usePager: false,
       columns: columns,
       checkbox: false,
       data:{rows:[]},
       width:_GRID_WIDTH,height:'100%',
       heightDiff:-20,
       enabledEdit:true
       ,onBeforeSubmitEdit:_onBeforeSubmitEdit
       });
}
function _onBeforeSubmitEdit(e){
		if(e.value==""){
			return true;
		}
		if(!PfmUtil.testPlusNumeric2(e.value)){
			$.dialogBox.warn("请输入最多20位、小数位只能为2位的有效数字。");
			return false;
		}
		var tmp = parseFloat(e.value);
		//alert(e.value+"&"+tmp);
		if(isNaN(tmp)||tmp==null||tmp==undefined||tmp<=0||tmp=="0"){
			tmp="";
			$.dialogBox.warn("指标值不能小于等于零，请重新输入。");
			return false;
		}
		e.value = tmp.toFixed(2);
		return true;
}

function beforeSubmit(){
	if (!$('#myform').valid()){
		return;
	}
	
	var mdata = Utils.convertParam('staffTargetQueryIn','myform');
	//多记录数据
	var rowsRight = gridRight.getData();
	var rowsStf = gridStf.getData();
	var rowsTarCode = [];		
	for ( var i = 0; i < rowsRight.length; i++) {
         if(rowsRight[i].__status != 'delete'&&rowsRight[i].opstatus=='insert'){
                rowsTarCode.push(rowsRight[i]);
          }
	}
	var rowsStaffId = [];
	for (var i = 0; i < rowsStf.length; i++) {
        if(rowsStf[i].__status != 'delete'&&rowsStf[i].opstatus=='insert'){
        	var newRow = {};
        	newRow.staffId = rowsStf[i].staffId;
        	newRow.staffName = rowsStf[i].staffName;
        	newRow.blOrg = rowsStf[i].ownerUnitId;
        	newRow.blOrgName = rowsStf[i].ownerUnitName;
        	newRow.opstatus = 'insert';
        	newRow.__status = 'add';
        	rowsStaffId.push(newRow);
        }
	}
	if(rowsTarCode.length==0){
		$.dialogBox.warn('请选择指标。');
		return;
	}
	if(rowsStaffId.length==0){
		$.dialogBox.warn('请选择员工。');
		return;
	}
	
	mdata.push({name:'staffTargetQueryIn.staffStr',value:JSON.stringify(rowsStaffId)});
	mdata.push({name:'staffTargetQueryIn.tarStr',value:JSON.stringify(rowsTarCode)});
	return mdata;
}

function staffTargetQuery(){
	var mdata = beforeSubmit();
	if(mdata==null||mdata==undefined||mdata==""){
		return;
	}
	initStaffQueryGrid();
	//单记录数据
	var params = {
			dataAction:'server',
			dataType:'server',
			url:_CONTEXT_PATH + "/Qtytarstaff!staffTargetQuery.action",
			newPage:1,
			parms:mdata,
			onError: function() {
				$.dialogBox.error("查询数据失败",'提示');
			}
	};
	gridStfQuery.setOptions(params);
	gridStfQuery.loadData();	
}
/**
 * 查询员工指标数据
 * =========================
 */
/****
 * =========================
 * 选择指标全部方法
 */
//全选
function selectRowsAll() {
	var rows3 = gridTar.getData();
	if(rows3&&rows3.length!=0){
	for ( var i = 0; i < rows3.length; i++) {
		if(checkedSelIndex(rows3[i])){
			var row = rows3[i];
			row.opstatus = 'insert';
			gridRight.addRow(row);
		}
	}
	}else{
		$.dialogBox.warn('请选择指标。');
		return;
	}
}
//验证指标名称是否已经选择
function checkedSelIndex(row){
	var flag = true;
	var rows4 = gridRight.getData();
	for (var i = 0; i < rows4.length; i++) {
		if (rows4[i].tarCode == row.tarCode){
			if(rows4[i].__status!='delete'&&rows4[i].opstatus=='insert'){
              	flag=false;
              	break;
			}
		}
	}
	return flag;		
}
//选择
function selectRows(){
	var rows3 = gridTar.getCheckedRows();
	if (rows3 && rows3.length!=0) {
		for ( var i = 0; i < rows3.length; i++) {
			if(checkedSelIndex(rows3[i])){
				var row = rows3[i];
				row.opstatus = 'insert';
				gridRight.addRow(row);
			}
		}
	}else{
		$.dialogBox.warn('请选择指标。');
		return;
	}
}
//移除
function selectRemove() {
	var rows = gridRight.getCheckedRows();
	if (rows && rows.length!=0) {
		for ( var i = 0; i < rows.length; i++) {
			rows[i].opstatus = 'delete';
			var row = rows[i];			
			gridRight.deleteSelectedRow(row);
		}
	}else{
		$.dialogBox.warn('请选择指标。');
		return;
	}
}
//全部删除
function selectRemoveAll() {
	gridRight.setOptions({data:{rows:[]}});
	gridRight.loadData();
}
/****
 * 选择指标全部方法
 * =========================
 */

/****
 * =========================
 * 选择员工全部方法
 */
function staffImport(){
	/*var url = _CONTEXT_PATH+'/jsp/public/pubstaffquery.jsp';
    Utils.openDialog(url,'选择人员',importOK,true);*/
	Utils.openSelectStaff(importOK);
}

//选择员工回调函数
function importOK(){	
    var rows = this.iframe.contentWindow.select();
    if(!rows||rows.length==0)return false;
    for (var i = 0; i < rows.length; i++){
    	var row = rows[i];
    	row.opstatus = 'insert';
    	if(checkedDetailStaff(row.staffId)){    		
    		gridStf.addRow(row);
    	}
    }
}
//验证员工是否已选择
function checkedDetailStaff(staffId) {
	var flag = true;
	var rows = gridStf.getData();
	for ( var i = 0; i < rows.length; i++) {
		if (rows[i].staffId == staffId) {
			if (rows[i].__status != 'delete'&&rows[i].opstatus=='insert') {
				flag = false;
				break;
			}
		}
	}
	return flag;
}

//删除员工
function staffDel() {
	var rows = gridStf.getCheckedRows();
	if (rows) {
		for ( var i = 0; i < rows.length; i++) {
			rows[i].opstatus = 'delete';
			gridStf.deleteSelectedRow(rows[i]);
		}
	}
}
//删除全部员工
function staffDelAll() {
	gridStf.setOptions({data:{rows:[]}});
	gridStf.loadData();
}
/**
 * 选择员工全部方法
 * =========================
 */

function staffTargetSave(){
	if (!$('#myform').valid()){
		return;
	}
	var rows = gridStfQuery.getData();
	var mdata = Utils.convertFormData('staffTargetSaveIn','myform');
	mdata['staffTargetSaveIn.qtyTarStaffSaveStr'] = JSON.stringify(rows);
	url = _CONTEXT_PATH + "/Qtytarstaff!staffTargetSave.action";
	Utils.ajaxSubmit(url,mdata,saveSuccess);
}

function saveSuccess(result){
	if(Utils.isSuccess(result)){
		$.dialogBox.info(result.retMsg);
		staffTargetQuery();
	}else{
		$.dialogBox.error(result.retMsg);
	}
}

function importFile(){
	if (!$('#myform').valid()){
		return;
	}
	Utils.uploadFile(doUploadFile,null,'xls','true','false');
}

//导入前验证
function doUploadFile(uploadId,files){
	$("#uploadId").val(uploadId);
	$("#uploadName").val(files[0].fileName);
	$("#uploadPath").val(files[0].filePath);
	$("#uploadFileId").val(files[0].fileId);
	var mdata = beforeSubmit();
	if(mdata==null||mdata==undefined||mdata==""){
		return;
	}
	//mdata['staffTargetQueryIn.uploadId'] = uploadId;
	//mdata['staffTargetQueryIn.uploadName'] = files[0].fileName;
	//mdata['staffTargetQueryIn.uploadPath'] = files[0].filePath;
	//mdata['staffTargetQueryIn.uploadFileId'] = files[0].fileId;
	url = _CONTEXT_PATH + "/Qtytarstaff!importBeforeStaffTargetSave.action";
	Utils.ajaxSubmit(url,mdata,impSuccess1);
}
//导入，成功后回显数据
function impSuccess1(result){
	if(Utils.isSuccess(result)){
		$.dialogBox.info(result.retMsg);
		staffTargetQuery();
	}else{
		$.dialogBox.error(result.retMsg);
	}
}

function exportFile(){
	var mdata = beforeSubmit();
	if(mdata==null||mdata==undefined||mdata==""){
		return;
	}
	url = _CONTEXT_PATH + "/Qtytarstaff!exportForStaffTarget.action";
	Utils.ajaxSubmitForExport(url,mdata);
}