package com.soule.crm.pfm.datahelper;

public class IndicatorDataTableMetaData {
    public static final String OBJECT_ID_FROM_IND_VIEW = "OBJECTID_FROM_IND_VIEW";
    public static final String INDCATOR_DATA_VIEW = "INDCATOR_DATA_VIEW";
    public static final String PERIOD_NO = "PERIOD_NO";
    public static final String INDICATOR_ID = "INDICATOR_ID";
    public static final String INDICATOR_VALUE = "INDICATOR_VALUE";
    public String tableName;
    public String objectID;
    public String indicatorID;
    public String periodCode;
    public String indicatorValue;

    public IndicatorDataTableMetaData(String table, String obj, String indicator, String period, String value) {
        this.tableName = table;
        this.objectID = obj;
        this.indicatorID = indicator;
        this.periodCode = period;
        this.indicatorValue = value;
    }
}
