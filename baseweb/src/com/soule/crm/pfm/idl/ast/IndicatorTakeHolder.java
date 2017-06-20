package com.soule.crm.pfm.idl.ast;

import java.util.BitSet;

import com.soule.app.pfm.tm.qtydef.QtyDefPo;
import com.soule.crm.pfm.acct.astnew.ACCTLanguage;
import com.soule.crm.pfm.datahelper.DataHelper;
import com.soule.crm.pfm.dsl.ast.TakeHolder;
import com.soule.crm.pfm.dsl.ast.Type;
import com.soule.crm.pfm.engine.IndicatorEngine;

public class IndicatorTakeHolder implements TakeHolder {
    
    public static final String SHARP = "#";
    public static final int INDICATOR = 13;
    
    public Type getReturnedType() {
        
        return ACCTLanguage.INDICATOR_TYPE;
    }

    public String getStart() {
        
        return SHARP;
    }

    public boolean isValid(String token) {
        return (getIndicatorID(token) != null);
    }
    
    public static BitSet getIndicatorType(String token){
        return DataHelper.getInstance().getIndicatorType(token);
    }
    
    public static String getIndicatorID(String token){
        return token;
        
    }
    public String translate(String token) {
        QtyDefPo qty=IndicatorEngine.getInstance().getModel(token);
        String indicatorID = qty.getTarCode();
        return indicatorID;
    }
    
        public int getType() {
        
        return INDICATOR;
    }   
    

}
