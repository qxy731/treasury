package com.soule.app.demo.charts.single;


import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.po.EnumItem;
import com.soule.base.po.EnumType;
import com.soule.base.service.ServiceResult;

/**
 * 基本图形维护模块表现层处理类
 */
@Namespace("/demo")
public class SingleAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Autowired
    private ISingleService service;

    /**
     * 显示图形 输入参数 
     */
    private SingleDisplayIn displayIn; //= new SingleDisplayIn();
    private EnumType chartType = new EnumType("chart_type");

    public void doInit() {
        chartType.addItem(new EnumItem("Column2D.swf","Column2D",""));
        chartType.addItem(new EnumItem("Column3D.swf","Column3D",""));
        chartType.addItem(new EnumItem("Pie2D.swf","Pie2D",""));
        chartType.addItem(new EnumItem("Pie3D.swf","Pie3D",""));
        chartType.addItem(new EnumItem("Line.swf","Line",""));
        chartType.addItem(new EnumItem("Doughnut2D.swf","Doughnut2D",""));
        chartType.addItem(new EnumItem("Doughnut3D.swf","Doughnut3D",""));
        
        chartType.addItem(new EnumItem("Bar2D.swf","Bar2D.swf",""));
        chartType.addItem(new EnumItem("Area2D.swf","Area2D",""));
    }
    public String display() {
        SingleDisplayIn in = displayIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            SingleDisplayOut result = service.display(in);
            ServiceResult head = result.getResultHead();
            this.rows = result.getData();
            this.total = result.getResultHead().getTotal();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }

    /**
     * 显示图形
     */
    @JSON(serialize=false)
    public SingleDisplayIn getDisplayIn() {
        return this.displayIn;
    }
    /**
     * 显示图形
     */
    public void setDisplayIn(SingleDisplayIn in) {
        this.displayIn = in;
    }
    public EnumType getChartType() {
        return chartType;
    }
    public void setChartType(EnumType chartType) {
        this.chartType = chartType;
    }

}
