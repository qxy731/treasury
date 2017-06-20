package com.soule.crm.pfm.dsl.ast.base;

import com.soule.crm.pfm.dsl.ast.Operator;
import com.soule.crm.pfm.dsl.ast.Type;

public class OrOperator implements Operator {
    private static final Type[] inputTypes = new Type[]{
        BaseLanguage.BOOLEAN,
        BaseLanguage.BOOLEAN
        };

    public static final String OR = "或者";
    
    public Type getReturnedType() {
        
        return BaseLanguage.BOOLEAN;
    }

    public Type[] getSupportedInputTypes() {
        
        return inputTypes;
    }

    public String getWord() {
        
        return OR;
    }

}
