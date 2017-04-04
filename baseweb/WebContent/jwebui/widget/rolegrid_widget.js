/**
 * 表格微件例子
 * 
 * @author wuwei
 */
(function($) {
    $.zWidgets = $.zWidgets || {};
    $.zWidgets.rolegrid = {
        context :{
        },
        renderHtml: function(id,model){
           var p = $.zWidgets.rolegrid.context;
           var meths = { };
           $.zWidgets.rolegrid.meths = meths;
           var str = "<div id='rolegrid_"+id+"'></div>";
           return str;
        },
        initWidget: function(cdiv,model){
            var height = 170;
            $(cdiv).css("height",(height + 33) + "px");
            var id = $(cdiv).attr("id");
            $('#rolegrid_' + id ,cdiv).ligerGrid({
                checkbox: false,
                columns: [
                { display: '角色主键', name: 'roleId', align: 'left', width: '30%' },
                { display: '角色名称', name: 'roleName',align: 'left',  width: '40%' },
                { display: '角色描述', name: 'remark', align:'left', width: '30%' }, 
                ],
                pageSize:8,
                selectRowButtonOnly:true,
                sortName: 'roleId', 
                height:height,
                url: _CONTEXT_PATH+ '/sys/role!query.action?queryIn.roleId=',
                onError: function(xrequest,textStatus,error) {
                    $.dialogBox.error(xrequest.responseText);
                }
            });
        },
        /**
         * 更多链接可用
         */
        getIconImage: function(datamodel) {
        	return "../../images/icons/1.png";
        },
        /**
         * 更多链接
         */
        getMore: function(datamodel) {
        	$.dialogBox.info('more');
        },
        /**
         * 更多链接可用
         */
        moreEnabled: function(datamodel) {
        	return true;
        }
    };
})(jQuery);