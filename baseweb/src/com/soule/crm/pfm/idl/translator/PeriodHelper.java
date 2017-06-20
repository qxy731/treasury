package com.soule.crm.pfm.idl.translator;

import com.soule.crm.pfm.datahelper.DataHelper;

public class PeriodHelper {
    
    private PeriodHelper(){}
    private static PeriodHelper instance;
    public static PeriodHelper getIntance(){
    	if(instance == null){
    		instance = new PeriodHelper();
    	}
    	return instance;
    }
    
        
    public  int getPeriodNumPerYear(String periodType){
        return DataHelper.getInstance().getPeriodNumPerYear(periodType);
    }

}
