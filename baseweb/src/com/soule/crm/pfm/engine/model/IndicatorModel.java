package com.soule.crm.pfm.engine.model;

public class IndicatorModel {
    public static final String TYPE_QUALITY = "2";
    public static final String TYPE_QUANTITY = "1";
    
    private String indicatorId;
    private String indicatorName;
    private String indicatorDesc;
    private String measureUnit;
    private String precise;    
    private String category;         //定性或定量
    private String applyScope;       //适用对象，八位二进制
    private String statScope;        //统计范围，本机构、所有机构等
    private String dataCycle;        //处理周期
    private String finishId;         //处理日期类型（月末、季末等）
    private String finishDate;       //指定处理日期
    private String source;           //数据来源：手工指标、系统取数、日常ETL、周期ETL等
    private String direct;           //排序方向：越大越好、越小越好、不能超出指定范围
    private String dataType;         //数据类型
    private String organId;
    private String storeDate;
    private String staticDate;
    
    
    public String getStaticDate() {
		return staticDate;
	}

	public void setStaticDate(String staticDate) {
		this.staticDate = staticDate;
	}

	public String getStoreDate() {
		return storeDate;
	}

	public void setStoreDate(String storeDate) {
		this.storeDate = storeDate;
	}

	public String getMeasureUnitWithPrecise(){
        String precise = this.precise==null ? "" : this.precise;
        String measUnit = this.measureUnit==null ? "" : this.measureUnit;
        int index1 = precise.indexOf("分之一");
        int index2 = precise.indexOf("位");
        if (index1 != -1){
            return measUnit + "/" + precise.substring(0, index1);
        }else if (index2 != -1){
            precise = precise.substring(0, index2);
            if (precise.startsWith("个")){
                precise = precise.substring(1);
            }
            return precise + measUnit;
        }
        return "";
    }
    
    public static void main(String[] args){
        IndicatorModel model = new IndicatorModel();
        model.setPrecise("千分之一");
        model.setMeasureUnit("元");
        System.out.println(model.getMeasureUnitWithPrecise());
        model.setPrecise("个位");
        System.out.println(model.getMeasureUnitWithPrecise());
        model.setPrecise("万位");
        System.out.println(model.getMeasureUnitWithPrecise());
    }
    
    
    public String getIndicatorId() {
        return indicatorId;
    }
    public void setIndicatorId(String indicatorId) {
        this.indicatorId = indicatorId;
    }
    public String getIndicatorName() {
        return indicatorName;
    }
    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }
    public String getIndicatorDesc() {
        return indicatorDesc;
    }
    public void setIndicatorDesc(String indicatorDesc) {
        this.indicatorDesc = indicatorDesc;
    }
    public String getApplyScope() {
        return applyScope;
    }
    public void setApplyScope(String applyScope) {
        this.applyScope = applyScope;
    }
    public String getStatScope() {
        return statScope;
    }
    public void setStatScope(String statScope) {
        this.statScope = statScope;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getDirect() {
        return direct;
    }
    public void setDirect(String direct) {
        this.direct = direct;
    }
    public String getOrganId() {
        return organId;
    }
    public void setOrganId(String organId) {
        this.organId = organId;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDataCycle() {
        return dataCycle;
    }
    public void setDataCycle(String dataCycle) {
        this.dataCycle = dataCycle;
    }
    public String getMeasureUnit() {
        return measureUnit;
    }
    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }
    public String getPrecise() {
        return precise;
    }
    public void setPrecise(String precise) {
        this.precise = precise;
    }
    public String getFinishId() {
        return finishId;
    }
    public void setFinishId(String finishId) {
        this.finishId = finishId;
    }
    public String getFinishDate() {
        return finishDate;
    }
    public void setFinishDate(String finsihDate) {
        this.finishDate = finsihDate;
    }
    public String getDataType() {
        return dataType;
    }
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    
}