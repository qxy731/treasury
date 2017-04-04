/**
 * 图形微件
 * @author wuwei
 */
(function($) {
    $.zWidgets = $.zWidgets || {};
    $.zWidgets.chart = {
            chartKey :0,
            renderHtml: function(id,datamodel){
                var str = "<script type='text/javascript' src='"+ _CONTEXT_PATH +
                "/jwebui/charts/FusionCharts.js'></script><div id='datachart"+id+"'></div>";
                return str;
            }
            ,
            initWidget: function(contentDiv,datamodel){
                var chartFile = datamodel.parama + ".swf";
                var mdata = {"displayIn.chartType": chartFile};
                var url = _CONTEXT_PATH +"/demo/single!display.action";
                Utils.ajaxSubmit(url,mdata,function(result) {
                    var custNo = datamodel.name;
                    var height = 220;
                    $(contentDiv).css('height',"" +(height+30)+"px");
                    var myChart = new FusionCharts( _CONTEXT_PATH + "/jwebui/charts/swf/" + chartFile,
                       "myChart" +($.zWidgets.chart.chartKey++), ""+$.zWidgetPanel.context.colwidth , "" + height, "0", "1");
                    for (var u = 0 ; u < result.rows.length; u ++){
                        result.rows[u]['mylink'] = "JavaScript:selectOne("+result.rows[u].month+")";
                    }
                    var jsonData = Utils.convertChartData(result.rows,'month','amt','mylink');
                    jsonData['chart'] = {
                              "formatNumberScale":"0",
                              "showBorder":"0",
                              "bgColor" : "F0F0FF",
                              "caption" : "用户("+custNo+")月存款额" ,
                              "xaxisname" : "月份",
                              "yaxisname" : "Money",
                              "numberprefix" : "$" };
                    myChart.setJSONData(jsonData);
                    var id = datamodel['wid'];
                    myChart.render("datachart" + id);
                });
            },
            /**
             * 图标
             */
            getIconImage: function(datamodel) {
            	//return datamodel['paramb'];
            	return "../../images/icons/1.png";
            }
        };
})(jQuery);