package com.soule.crm.pfm.idl.ast;

import com.soule.crm.pfm.dsl.ast.Type;

public class GetDaysFunction implements Function {
    private static final String IDL_DAYS_OF_PRD = "IDL_DAYS_OF_PRD";
    private static final String name = "取周期天数";
    
    private static final Type[] inputs = new Type[]{
        IDLLanguage.PERIOD_TYPE
    };

    public boolean isAggFunction() {
        
        return false;
    }

    public String toSQL(String[] params) {
    
        return IDL_DAYS_OF_PRD + IDLLanguage.OPENED_BRACKET 
        + params[0] + IDLLanguage.CLOSED_BRACKET;
    }

    public String getName() {
        
        return name;
    }

    public Type getReturnedType() {
    
        return IDLLanguage.NUMERIC;
    }

    public Type[] getSupportedInputTypes() {
        
        return inputs;
    }

    public boolean validate(Object[] params) {
        
        return true;
    }
public boolean isDeriveFunction() {
        
        return false;
    }
}
