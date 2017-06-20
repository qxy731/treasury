package com.soule.crm.pfm.acct.astnew;

import java.util.List;

import com.soule.crm.pfm.acct.ast.AcctParameterTakeHolder;
import com.soule.crm.pfm.dsl.ast.Function;
import com.soule.crm.pfm.dsl.ast.TakeHolder;
import com.soule.crm.pfm.dsl.ast.Type;
import com.soule.crm.pfm.dsl.ast.base.BaseLanguage;
import com.soule.crm.pfm.idl.ast.AbsFunction;
import com.soule.crm.pfm.idl.ast.IfFunction;
import com.soule.crm.pfm.idl.ast.Indicator;
import com.soule.crm.pfm.idl.ast.IndicatorTakeHolder;
import com.soule.crm.pfm.idl.ast.LnFunction;
import com.soule.crm.pfm.idl.ast.MaxFunction;
import com.soule.crm.pfm.idl.ast.MinFunction;
import com.soule.crm.pfm.idl.ast.NVLFunction;
import com.soule.crm.pfm.idl.ast.PowerFunction;
import com.soule.crm.pfm.idl.ast.Range;
import com.soule.crm.pfm.idl.ast.RangeTakeHolder;
import com.soule.crm.pfm.idl.ast.StdFunction;
import com.soule.crm.pfm.idl.ast.SumFunction;


public class ACCTLanguage extends BaseLanguage {

    public static final char SINGLE_QUOTE = '\'';

    public static final char DOT = '.';

    public static final char EQUAL = '=';
    
    public static final String DSL_ETL_DATE = "PFM_ACCT_INFO.TAR_DATE";
    


    public static final Type INDICATOR_TYPE = new Indicator();
    public static final Type RANGE_TYPE = new Range();

    public static final Function IDL_IF_FUNCTION = new IfFunction();
    public static final Function IDL_DECODE_FUNCTION = new DecodeFunction();

    public static final Function ABS = new AbsFunction();
    public static final Function LN = new LnFunction();
    public static final Function POWER = new PowerFunction();
    public static final Function NVL = new NVLFunction();
    // public static final Function IDL_CWFQ = new CwFQFunction();
    // public static final Function IDL_CWFY = new CwFYFunction();
    // public static final Function ACCT_Y_BGN = new YearBeginFunction();
    // public static final Function ACCT_Q_BGN = new QuarterBeginFunction();
    // public static final Function ACCT_M_BGN = new MonthBeginFunction();

    public static final TakeHolder INDICATOR_TAKE_HOLDER = new IndicatorTakeHolder();
    private static final RangeTakeHolder RANGE_TAKE_HOLDER = new RangeTakeHolder();
    private static final AcctParameterTakeHolder PARAMETER_TAKE_HOLDER = new AcctParameterTakeHolder();

    public static final String ALL_RANGE_CN = "全体";
    private static final String ALL_RANGE = "0";

    public static final String OF_RANGE_CN = "当前机构及下级机构";
    private static final String OF_RANGE = "1";

    public static final String THIS_RANGE_CN = "当前机构";
    private static final String THIS_RANGE = "2";

    public static final String UNDER_1_RANGE_CN = "直辖下级行";
    private static final String UNDER_1_RANGE = "3";

    public static final String CREATOR_CN = "定义机构";
    private static final String CREATOR_RANGE = "9";

    public static final Function IDL_SUM = new SumFunction();
    // public static final Function IDL_AVG = new AvgFunction();
    public static final Function IDL_MAX = new MaxFunction();
    public static final Function IDL_MIN = new MinFunction();
    public static final Function IDL_STD = new StdFunction();

    public ACCTLanguage() {
        super();

        RANGE_TAKE_HOLDER.putRange(ALL_RANGE_CN, ALL_RANGE);
        RANGE_TAKE_HOLDER.putRange(OF_RANGE_CN, OF_RANGE);
        RANGE_TAKE_HOLDER.putRange(THIS_RANGE_CN, THIS_RANGE);
        RANGE_TAKE_HOLDER.putRange(UNDER_1_RANGE_CN, UNDER_1_RANGE);
        RANGE_TAKE_HOLDER.putRange(CREATOR_CN, CREATOR_RANGE);

        List takeHolders = super.getSupportedTakeHolders();
        takeHolders.add(INDICATOR_TAKE_HOLDER);
        takeHolders.add(RANGE_TAKE_HOLDER);
        takeHolders.add(PARAMETER_TAKE_HOLDER);

        List funcs = super.getSupportedFunctions();
        funcs.add(IDL_IF_FUNCTION);
        funcs.add(IDL_DECODE_FUNCTION);

        funcs.add(ABS);
        funcs.add(LN);
        funcs.add(POWER);
        funcs.add(NVL);

        // funcs.add(ACCT_Y_BGN);
        // funcs.add(ACCT_Q_BGN);
        // funcs.add(ACCT_M_BGN);

        // funcs.add(IDL_CWFQ);
        // funcs.add(IDL_CWFY);

        funcs.add(IDL_SUM);
        // funcs.add(IDL_AVG);
        funcs.add(IDL_MAX);
        funcs.add(IDL_MIN);
        funcs.add(IDL_STD);
    }

}