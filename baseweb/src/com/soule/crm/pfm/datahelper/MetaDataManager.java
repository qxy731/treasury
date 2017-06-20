package com.soule.crm.pfm.datahelper;

import com.soule.crm.pfm.datahelper.IndicatorDataTableMetaData;
import com.soule.crm.pfm.datahelper.ObjectTableMetaData;
import com.soule.crm.pfm.datahelper.PerformanceDataTableMetaData;

public class MetaDataManager {
    public static final int TYPE_PERSON = 1;
    public static final int TYPE_GOV = 2;
    private static ObjectTableMetaData STUFF_TABLE_META = new ObjectTableMetaData("V_CRM_STAFF", "CODE", "NAME");

    private static ObjectTableMetaData GOV_TABLE_META = new ObjectTableMetaData("V_CRM_ORG", "CODE", "NAME");

    private static IndicatorDataTableMetaData STUFF_IND_DATA_META = new IndicatorDataTableMetaData("V_PFM_CM_TARGET",
            "CUSTMG_CODE", "TARGET_CODE", "CYC_CODE", "TARGET_VALUE");

    private static IndicatorDataTableMetaData GOV_IND_DATA_META = new IndicatorDataTableMetaData("V_PFM_ORG_TARGET",
            "ORG_CODE", "TARGET_CODE", "CYC_CODE", "TARGET_VALUE");

    private static PerformanceDataTableMetaData STUFF_PERFORM_DATA_META = new PerformanceDataTableMetaData(
            "V_PFM_CM_EXAM", "CUSTMG_CODE", "TARGET_CODE", "WAY_CODE", "SEG_CODE", "TARGET_VALUE", "PT_VALUE",
            "BASE_NUM", "SCORE");

    private static PerformanceDataTableMetaData GOV_PERFORM_DATA_META = new PerformanceDataTableMetaData(
            "V_PFM_ORG_EXAM", "ORG_CODE", "TARGET_CODE", "WAY_CODE", "SEG_CODE", "TARGET_VALUE", "PT_VALUE",
            "BASE_NUM", "SCORE");

    public static ObjectTableMetaData getObjectMetaData(int type) {
        switch (type) {
        case 1:
            return STUFF_TABLE_META;
        case 2:
            return GOV_TABLE_META;
        }

        return null;
    }

    public static IndicatorDataTableMetaData getIndicatorDataTableMetaData(int type) {
        switch (type) {
        case 1:
            return STUFF_IND_DATA_META;
        case 2:
            return GOV_IND_DATA_META;
        }

        return null;
    }

    public static PerformanceDataTableMetaData getPerformanceDataTableMetaData(int type) {
        switch (type) {
        case 1:
            return STUFF_PERFORM_DATA_META;
        case 2:
            return GOV_PERFORM_DATA_META;
        }

        return null;
    }
}
