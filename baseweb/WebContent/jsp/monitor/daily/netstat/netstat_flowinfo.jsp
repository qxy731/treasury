<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网络状况I/O监控</title>
<jsp:include page="/comm.jsp"></jsp:include>
<script src="${_CONTEXT_PATH}/jwebui/highcharts/highcharts.js"></script>
<style type="text/css">
.inbox ,.outbox {padding: 5px;margin: 2px;border: 1px solid #ccc;}
.params td {background-color: #E2EAFF; padding:2px 2px 2px 10px;}
#reset {width: 80px;}
</style>
</head>
<body>
<n:page action='com.soule.crm.monitor.daily.netstat.NetstatAction' />
<table class='content'>
<tr><td>
	<fieldset class="outbox">
		<div id="container" style="margin: 0 auto"></div>
</fieldset>
</td></tr>
</table>
</body>
<script type='text/javascript'>
$(function () {
    $(document).ready(function() {
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });

        var chart;
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                type: 'area',
                marginRight: 10,
                events: {
                    load: function() {

                        // set up the updating of the chart each second
                        var series = this.series[0];
                        var series1 = this.series[1];
                        setInterval(function() {
                            var url = "${_CONTEXT_PATH}/monitor/netstat!flowInfo.action";
                            var mdata = {'hwid':'${hwid}'};
                            Utils.ajaxSubmit(url,mdata,function(result) {
                            	var x = result.netPo.current;
                            	var y = result.netPo.rxBps;
                            	var z = result.netPo.txBps;
                            	series.addPoint([x, y], true, true);
                            	series1.addPoint([x, z], true, true);
                            });
                        }, 2000);
                    }
                }
            },
            title: {
                text: '网络IO(NETWORK I0)'
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 100
            },
            yAxis: {
                title: {
                    text: '速度(Speed)'
                },
                plotLines: [{
                    value: 1,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function() {
                        return '<b>'+ this.series.name +'</b><br/>'+
                        Highcharts.dateFormat('%H:%M:%S', this.x) +'<br/>'+
                        Highcharts.numberFormat(this.y, 0);
                }
            },
            credits:{
                enabled:false
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            plotOptions: {
                area: {
                    fillColor: {
                        linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1},
                        stops: [
                            [0, Highcharts.getOptions().colors[0]],
                            [1, 'rgba(2,0,0,0)']
                        ]
                    },
                    lineWidth: 1,
                    marker: {
                        enabled: false,
                        states: {
                            hover: {
                                enabled: true,
                                radius: 5
                            }
                        }
                    },
                    shadow: false,
                    states: {
                        hover: {
                            lineWidth: 1
                        }
                    },
                    threshold: null
                }
            },
            series: [{
                name: 'RxSpeed',
                data: (function() {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        i;

                    for (i = -200; i <= 0; i++) {
                        data.push({
                            x: time + i * 2000,
                            y: 0
                        });
                    }
                    return data;
                })()
            },
            {
                name: 'TxSpeed',
                data: (function() {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        i;

                    for (i = -200; i <= 0; i++) {
                        data.push({
                            x: time + i * 2000,
                            y: 0
                        });
                    }
                    return data;
                })()
            }]
        });
    });
});
</script>
</html>