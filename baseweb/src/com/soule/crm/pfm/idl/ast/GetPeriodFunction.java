package com.soule.crm.pfm.idl.ast;
import com.soule.app.pfm.tm.qtydef.QtyDefPo;
import com.soule.crm.pfm.dsl.ast.Type;
import com.soule.crm.pfm.dsl.compiler.Compiler;
import com.soule.crm.pfm.dsl.compiler.Unit;
import com.soule.crm.pfm.engine.IndicatorEngine;

public class GetPeriodFunction implements Function {
    private static final String IDL_GET_PERIOD = "IDL_GET_PERIOD";
    public static final String name = "取周期";

    private static final Type[] inputs = new Type[] { Compiler.NUMERIC };

    public boolean isAggFunction() {

        return false;
    }

    public String toSQL(String[] params) {
        String indicator = params[2];
        indicator=indicator.substring(0, indicator.length());
        QtyDefPo qty=IndicatorEngine.getInstance().getModelById(indicator);
/*IDL_GET_PERIOD(PERIOD_NO, 0, 006, 003) PFM_IDL_GET_DAT_FST(P_TARGET_DATE in varchar,P_CYC in int)

        作用：取周期
        P_TARGET_DATE:日期
        P_LEN：增加的长度
        P_STORE: 006:年  004:季 003:月
*/
        if (params[1].equals("-12") == false) {
            return IDL_GET_PERIOD + IDLLanguage.OPENED_BRACKET + params[0] + IDLLanguage.COMMA + params[1]
                    + IDLLanguage.COMMA + qty.getStoreDate()  + IDLLanguage.CLOSED_BRACKET;
        } else {
            params[1]="-1";
            return IDL_GET_PERIOD + IDLLanguage.OPENED_BRACKET + params[0] + IDLLanguage.COMMA + params[1]
                    + IDLLanguage.COMMA + qty.getStoreDate() + IDLLanguage.CLOSED_BRACKET;
        }
    }


    public String getName() {

        return name;
    }

    public Type getReturnedType() {

        return IDLLanguage.PERIOD_TYPE;
    }

    public Type[] getSupportedInputTypes() {

        return inputs;
    }

    public boolean validate(Object[] params) {
        Unit p = (Unit) params[0];
        String pn = (String) p.oprands.get(0);
        int n = 0;
        try {
            n = Integer.parseInt(pn);
        } catch (Exception e) {
            return false;
        }

        if (n >= 0 && n <= 4) {
            return true;
        }

        return false;
    }

    public boolean isDeriveFunction() {

        return false;
    }
}