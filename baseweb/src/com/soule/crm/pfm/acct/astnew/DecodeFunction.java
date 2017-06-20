package com.soule.crm.pfm.acct.astnew;

import com.soule.crm.pfm.dsl.ast.Function;
import com.soule.crm.pfm.dsl.ast.Type;
import com.soule.crm.pfm.dsl.compiler.Compiler;
public class DecodeFunction implements Function {
    public static final String IIF = "IDL_DECODE";

    public static final String name = "额度区间";

    private static final Type NUMERIC = Compiler.NUMERIC;

    private static final Type[] inputs = new Type[] { NUMERIC, NUMERIC,
            NUMERIC, NUMERIC, NUMERIC, NUMERIC, NUMERIC, NUMERIC, NUMERIC,
            NUMERIC, NUMERIC, NUMERIC, NUMERIC, NUMERIC, NUMERIC, NUMERIC,
            NUMERIC, NUMERIC, NUMERIC, NUMERIC, NUMERIC, NUMERIC, NUMERIC,
            NUMERIC, NUMERIC, NUMERIC };

    public String toSQL(String[] params) {

        return IIF + "(" + params[0] + ", " + params[1] + ", " + params[2]
                + ")";
    }

    public String getName() {

        return name;
    }

    public Type getReturnedType() {

        return NUMERIC;
    }

    public Type[] getSupportedInputTypes() {

        return inputs;
    }

    public boolean validate(Object[] params) {

        return true;
    }

    public boolean isAggFunction() {

        return false;
    }

    public boolean isDeriveFunction() {

        return false;
    }
}
