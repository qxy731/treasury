package com.soule.crm.pfm.datahelper;

public class ObjectTableMetaData {
    public static final String OBJECT_TABLE = "OBJECT_TABLE";
    public static final String OBJECT_ID = "OBJECT_ID";
    public static final String OBJECT_NAME = "OBJECT_NAME";
    public String tableName;
    public String IDColumnName;
    public String nameColumnName;

    public ObjectTableMetaData(String tName, String idName, String nameName) {
        this.tableName = tName;
        this.IDColumnName = idName;
        this.nameColumnName = nameName;
    }
}
