package com.soule.crm.pfm.datahelper;

public class PerformanceDataTableMetaData {
    public static final String OBJECT_ID_FROM_PERFORM = "OBJECTID_FROM_PERFORM";
    public static final String INDICATOR_ID = "INDICATOR_ID";
    public static final String INDICATOR_VALUE = "INDICATOR_VALUE";
    public static final String TASK_VALUE = "TASK_VALUE";
    public static final String SCORE_VALUE = "SCORE_VALUE";
    public static final String BASE_LINE_VALUE = "BASE_LINE_VALUE";
    public static final String PERFORM_DATA_VIEW = "PERFORM_DATA_VIEW";
    public static final String SCHEMA_CODE = "SCHEMA_CODE";
    public static final String PHASE_CODE = "PHASE_CODE";
    public String tableName;
    public String objectID;
    public String indicatorID;
    public String schemaID;
    public String phaseID;
    public String indicatorValue;
    public String taskValue;
    public String baselineValue;
    public String scoreValue;

    public PerformanceDataTableMetaData(String table, String obj, String indicator, String schema, String phase,
            String ind, String task, String baseline, String score) {
        this.tableName = table;
        this.objectID = obj;
        this.indicatorID = indicator;
        this.schemaID = schema;
        this.phaseID = phase;
        this.indicatorValue = ind;
        this.taskValue = task;
        this.baselineValue = baseline;
        this.scoreValue = score;
    }
}
