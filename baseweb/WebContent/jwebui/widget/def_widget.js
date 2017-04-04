/**
 * 首页微件
 * 默认微件
 * 
 * @author wuwei
 */
(function($) {
    $.zWidgets = $.zWidgets || {};
    $.zWidgets.def = {
        /**
         * 返回html 字符串
         * @param id 微件ID
         * @param datamodel 数据
         * @returns {String}
         */
        renderHtml: function(id,datamodel){
           return "";
        },
        /**
         * 微件初始化
         * @param contentDiv 微件内容DIV
         * @param datamodel 数据
         */
        initWidget: function(contentDiv,datamodel) {
            //alert(model.name);
        },
        /**
         * 更多链接
         */
        getMore: function(datamodel) {
        },
        /**
         * 更多链接可用
         */
        moreEnabled: function(datamodel) {
        	return false;
        },
        /**
         * 可用关闭
         */
        closeabled: function(datamodel) {
        	return true;
        },
        /**
         * 图标
         */
        getIconImage: function(datamodel) {
        	return "";
        }
    };
})(jQuery);