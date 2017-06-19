package com.soule.crm.pfm.idl.translator;

import java.util.BitSet;
import java.util.List;
import java.util.Set;

import com.soule.crm.pfm.dsl.ast.Function;
import com.soule.crm.pfm.dsl.compiler.Compiler;
import com.soule.crm.pfm.dsl.compiler.TakeHolderContainer;
import com.soule.crm.pfm.dsl.compiler.Unit;
import com.soule.crm.pfm.dsl.parser.Token;
import com.soule.crm.pfm.dsl.parser.TokenException;
import com.soule.crm.pfm.dsl.parser.TokenParser;
import com.soule.crm.pfm.idl.ast.IDLLanguage;
import com.soule.crm.pfm.idl.ast.Indicator;
import com.soule.crm.pfm.idl.ast.IndicatorTakeHolder;
import com.soule.crm.pfm.idl.ast.Period;
import com.soule.crm.pfm.idl.ast.Range;
import com.soule.crm.pfm.idl.translator.impl.Translator4;

public class Translator {
    static final IDLLanguage lang = new  IDLLanguage();
    static final TokenParser parser = new TokenParser(lang);
    static final Compiler compiler = new Compiler(parser);
    private IndicatorTakeHolder takeHolder;
    private Translator(){}
    private Translator(IndicatorTakeHolder takeHolder){
        this.takeHolder=takeHolder;
    }
    private static final Translator instance = new Translator();
    public static Translator getInstance(){return instance;}
    
    /**
     * 
     * @param exp
     * @param targetType 适用对象类型
     * @return
     * @throws TokenException
     */
    //trans.translate("同比增量(#中间业务入_Q)", bitset, "001", "季", "1000");
    //String res = tlr.translate(test1, bs, "PeriodType", "Current", "");
    public String translate(String exp, BitSet targetType, String periodType, String current, String defGov) throws TokenException{
        List<Token> tokens = parser.parse(exp);
        TakeHolderContainer container = new TakeHolderContainer();
        Unit ast = compiler.compile(tokens, container);
        Set<String> usedIndicators = container.getUsedTakeHolders(IndicatorTakeHolder.SHARP);
        if(usedIndicators == null || usedIndicators.size() == 0){
            throw new TokenException("未引用任何指标");
        }
        if(!isCompatible(usedIndicators, targetType)){
            throw new TokenException("所选指标的适用类型与公式的适用类型不匹配");
        }
        
         /*   
        if(!isVector(ast)){
            throw new TokenException("输入公式不是向量表达式，即输入公式的计算结果与考核对象无关");
        }
        */
        return Translator4.getInstance().translate(exp, targetType, periodType, defGov);
        //return Translator1.getInstance().translate(exp, periodType, targetType) ;
    }
    
    private boolean isCompatible(Set usedIndicators, BitSet targetType){
        Object names[] = usedIndicators.toArray();
        for(int i=0; i< names.length;i++){
            String name = (String)names[i];
            BitSet bs = takeHolder.getIndicatorType(name);
            bs.and(targetType);
            if(!bs.equals(targetType))return false;
        }
        return true;
    }
    
    private boolean isVector(Unit ast){
        if(ast.type == IndicatorTakeHolder.INDICATOR){
            return true;
        }
        if(ast.type == Unit.NUMERIC ){
            return false;
        }
        Class returnedClass = ast.returnedType.getClass();
        if(ast.type == Unit.FUNCTION){
            Function f = (Function)ast.ref;
            if(f.isAggFunction())return false;
            
            if(Indicator.class.isAssignableFrom(returnedClass)){
                return true;
            }
            if(Range.class.isAssignableFrom(returnedClass)
                ||  Period.class.isAssignableFrom(returnedClass)
            ){
                return false;
            }
            List ops = ast.oprands;
            for(int i=0; i<ops.size();i++){
                Unit u = (Unit)ops.get(i);
                if(isVector(u))return true;
            }
            
        }
        if(ast.type == Unit.OPERATION){
            /*
            if(com.longtop.crm.dsl.ast.base.Boolean.class.isAssignableFrom(returnedClass)){
                return false;
            }
            */
            
            List ops = ast.oprands;
            for(int i=0; i<ops.size();i++){
                Unit u = (Unit)ops.get(i);
                if(isVector(u))return true;
            }
            
        }
        
        return false;
    }
    /*
     * 
  IDL_GET_PERIOD(PERIOD_NO, 0, 006, 003) 
  PERIOD_NO:日期
  0:增加长度，
  006：现在没有用，后台已经去除

  CASE WHEN P_STORE = '006' THEN 12
                WHEN P_STORE = '004' THEN 3
                WHEN P_STORE = '003' THEN 1
  1.PFM_IDL_GET_PERIOD(P_TARGET_DATE in varchar,P_LEN in int,P_STORE VARCHAR)
作用：取周期
P_TARGET_DATE:日期
P_LEN：增加的长度
P_STORE: 006:年  004:季 003:月



2.PFM_IDL_GET_DAT_FST(P_TARGET_DATE in varchar,P_CYC in int)
作用：取指定日期，周期初对应的日期
P_TARGET_DATE :日期
P_CYC:周期  6:年，5:半年，4:季，3:月，2:旬，1：日


OBJECT_ID_FROM_IND_LIB：若是机构，则是ORG_ID
                        若是员工，则是STAFF_ID

表别名：DSL
指标编码：INDICATOR_ID
指标值：IND_VALUE
时间周期字段：PERIOD_NO

INDICATOR0
*/
    
    public static void main(String[] args){
        String test = "#存款余额 * 0.5";
        String test1 = "最大值(#存款余额, $全体) * 0.5 + 环比增量(#贷款余额)";
        String test2 = "最大值(#存款余额, $全体) * 0.5 + 4 * 绝对值(#贷款余额) + #存款余额 / 4"; 
        String test3 = "条件计算(#存款余额>50000,最大值(#存款余额, $全体) * 0.5 + 4 * 绝对值(#存款余额)), 6)";
        String test4 = "平均值( #考核定量指标3, $当前机构)*0.5+#考核定量指标1";
        String test5 = "平均值( #考核定量指标3,$全体)*0.5+#考核定量指标1+取指标(#测试 , 取周期(-1 ))";
        String test6 = "最大值(#存款余额, $全体)/2 * 0.5 + 4 * 绝对值(#存款余额)";  
        String test7 = "条件计算(#存款余额>50000, 最大值(#存款余额, $全体) * 0.5 + 4 * 绝对值(#存款余额), 6)";
        String test8 = "条件计算(8>5, 最大值(#存款余额, $全体) * 0.5 + 4 * 绝对值(#存款余额), 6)";
        String test9 = "取指标(#存款余额, 取周期(-2)) * 0.5";
        String test10 = "11";
        String test11 = "#存款余额 +#存款总额";
        String ddd="环比增幅(#存款余额)";
        Translator tlr = Translator.getInstance();
        BitSet bs = new BitSet(8);
        try {
        String res = tlr.translate(ddd, bs, "PeriodType", "Current", "");
        System.out.print(res);
        } catch (TokenException e) {
            
            // e.printStackTrace();
        }
        
    }
    
    public Set translate01(String exp) throws TokenException{
		List tokens = parser.parse(exp);
		TakeHolderContainer container = new TakeHolderContainer();
		Unit ast = compiler.compile(tokens, container);
		Set usedIndicators = container.getUsedTakeHolders(IndicatorTakeHolder.SHARP);
		return usedIndicators;
	}
    public void setTakeHolder(IndicatorTakeHolder takeHolder) {
        this.takeHolder = takeHolder;
    }
 
}