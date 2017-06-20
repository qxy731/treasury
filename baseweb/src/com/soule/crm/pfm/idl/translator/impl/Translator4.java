package com.soule.crm.pfm.idl.translator.impl;

import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.soule.crm.pfm.datahelper.AssertManager;
import com.soule.crm.pfm.datahelper.IndicatorLibMetaData;
import com.soule.crm.pfm.dsl.ast.base.DivOperator;
import com.soule.crm.pfm.dsl.compiler.Compiler;
import com.soule.crm.pfm.dsl.compiler.TakeHolderContainer;
import com.soule.crm.pfm.dsl.compiler.Unit;
import com.soule.crm.pfm.dsl.parser.Token;
import com.soule.crm.pfm.dsl.parser.TokenException;
import com.soule.crm.pfm.dsl.parser.TokenParser;
import com.soule.crm.pfm.idl.ast.CraFunction;
import com.soule.crm.pfm.idl.ast.CrsFunction;
import com.soule.crm.pfm.idl.ast.CwFQFunction;
import com.soule.crm.pfm.idl.ast.CwFYFunction;
import com.soule.crm.pfm.idl.ast.CwaFunction;
import com.soule.crm.pfm.idl.ast.CwsFunction;
import com.soule.crm.pfm.idl.ast.GetDateFstFunction;
import com.soule.crm.pfm.idl.ast.GetDaysInPeriodFunction;
import com.soule.crm.pfm.idl.ast.GetPeriodFunction;
import com.soule.crm.pfm.idl.ast.GetSumTargetsInPeriodFunction;
import com.soule.crm.pfm.idl.ast.IDLLanguage;
import com.soule.crm.pfm.idl.ast.IfFunction;
import com.soule.crm.pfm.idl.ast.IndicatorTakeHolder;
import com.soule.crm.pfm.idl.ast.IndicatorValueFunction;
import com.soule.crm.pfm.idl.ast.NVLFunction;
import com.soule.crm.pfm.idl.ast.RangeTakeHolder;
import com.soule.crm.pfm.idl.ast.RoundFunction;
import com.soule.crm.pfm.idl.translator.RangeHelper;
import com.soule.crm.pfm.param.ast.ParameterLibMetaData;
import com.soule.crm.pfm.param.ast.ParameterTakeholder;
import com.soule.crm.pfm.param.processor.ParameterHelper;

/**
 * DSL 新增系统参数设置
 * 
 * 采用了函数的方法来完成区间的指标的合计查询
 */
public class Translator4 {
	private static final String SUM_PADDING = "_SUM_";
	private static final IDLLanguage lang = new IDLLanguage();
	private static final TokenParser parser = new TokenParser(lang);
	private static final Compiler compiler = new Compiler(parser);
	private static RangeHelper rh = RangeHelper.getInstance();
	private static final Translator4 translator = new Translator4();
	private static final char SPACE = ' ';
	private static final char OPENED_BRACKET = '(';
	private static final char CLOSED_BRACKET = ')';
	public static final String GET_PERIOD_CODE_4_FIRST_OF_YEAR = "IDL_GETPRDCD4LSTY(PERIOD_NO)";
	public static final String GET_PERIOD_CODE_4_FIRST_OF_QUATER = "IDL_GETPRDCD4LSTQ(PERIOD_NO)";
	private static final String TABLE_ALIAS = "DSL";
	public static final String AND = " AND ";
	public static final String SELECT = "SELECT ";
	public static final String FROM = " FROM ";
	public static final String WHERE = " WHERE ";
	public static final String SPACE_OPENED = " (";
	public static final String SPACE_CLOSED = ") ";
	private static final String PARAM_DSL_EXP = TABLE_ALIAS + IDLLanguage.DOT + IndicatorLibMetaData.OBJECT_ID_FROM_IND_LIB;
	
	public static final int SUM_INDICATOR = 10000;

	private Translator4() {

	}

	public static Translator4 getInstance() {
		return translator;
	}
	/**
	 * #存款余额*&员工级别权重+#贷款余额&自定义参数
	 * 2:SELECT OBJECT_ID_FROM_IND_LIB , 
	 * 	(INDICATOR0 * PARAM0 +INDICATOR1*PARAM1) IND_VALUE 
	 *	FROM  
	 *		( SELECT DSL.OBJECT_ID_FROM_IND_LIB, 
	 *			SUM (CASE WHEN  DSL.INDICATOR_ID='T_E_CI_NUM_Y_3_90110'  
	 *			AND DSL.PERIOD_NO = IDL_GET_PERIOD(PERIOD_NO, 0, 006, 003) THEN DSL.IND_VALUE END ) INDICATOR1, 
	 *			SUM (CASE WHEN  DSL.INDICATOR_ID='T_E_CI_NUM_Y_3_90120'  
	 *			AND DSL.PERIOD_NO = IDL_GET_PERIOD(PERIOD_NO, 0, 006, 003) THEN DSL.IND_VALUE END ) INDICATOR0,
	 *			(SELECT PARAM_VALUE FROM SYS_PARAM_VALUE 
	 *				WHERE START_DAT <= GETDATE() AND ENDDAT >= GETDATE() AND PARAM_GROUP_CODE = 'XXXXX'
	 *					AND PARAM_ITEM_CODE01 = (SELECT POSITIONID FROM Wf_Duty WHERE STAFFID = DSL.CUSTMG_ID)
	 *					AND PARAM_ITEM_CODE02 = ' '
	 *					AND PARAM_ITEM_CODE03 = ' '
	 *			) PARAM0,
	 *			(SELECT PARAM_VALUE FROM SYS_PARAM_VALUE 
	 *				WHERE START_DAT <= GETDATE() AND ENDDAT >= GETDATE() AND PARAM_GROUP_CODE = 'XXXXX'
	 *					AND PARAM_ITEM_CODE01 = 'XXXX'
	 *					AND PARAM_ITEM_CODE02 = ' '
	 *					AND PARAM_ITEM_CODE03 = ' '
	 *			) PARAM1 
	 *		  FROM IND_DATA_LIB_TABLE DSL 
	 *		  WHERE  ( 
	 *			( DSL.INDICATOR_ID='T_E_CI_NUM_Y_3_90120' AND DSL.PERIOD_NO IN (IDL_GET_PERIOD(PERIOD_NO, 0, 006, 003)) ) 
	 *			OR 
	 *			( DSL.INDICATOR_ID='T_E_CI_NUM_Y_3_90110' AND DSL.PERIOD_NO IN (IDL_GET_PERIOD(PERIOD_NO, 0, 006, 003)) ) 	
	 *		  )  
	 *		  GROUP BY DSL.OBJECT_ID_FROM_IND_LIB 
	 *		) DSL
	 */
	public String translate(String exp, BitSet targetType, String periodType,
			String defGov) throws TokenException {
		List<Token> tokens = parser.parse(exp);
		TakeHolderContainer container = new TakeHolderContainer();
		Unit ast = compiler.compile(tokens, container);
		Set<String> usedRanges = container.getUsedTakeHolders(RangeTakeHolder.DOLLER);

		Hashtable<String, Set<Integer>> indRef = getUnitRef(ast, periodType);
		boolean hasAgg = indRef.get("AGG") != null;
		Hashtable<String, Struct> map = getStructMap(indRef);

		String formula = toFormula(ast, map, periodType);
		// 指标数量
		Hashtable<String, Struct> indicatorMap = getStructByType(map, IndicatorTakeHolder.INDICATOR);

		// 参数数量
		Hashtable<String, Struct> paramMap = getStructByType(map, ParameterTakeholder.PARAMETER);
		
		StringBuffer dslSql = new StringBuffer();
		dslSql.append(SELECT).append(
				IndicatorLibMetaData.OBJECT_ID_FROM_IND_LIB);
		dslSql.append(",").append(formula).append(SPACE).append(
				IndicatorLibMetaData.IND_VALUE_FROM_IND_LIB);
		dslSql.append(FROM);
		dslSql.append(SPACE_OPENED);
		dslSql.append(SELECT + TABLE_ALIAS + IDLLanguage.DOT
				+ IndicatorLibMetaData.OBJECT_ID_FROM_IND_LIB + ",");
		//拼指标 
		dslSql.append(indicatorSql(indicatorMap));

		//是否包含参数
		if (!paramMap.isEmpty()) {
			dslSql.append(",");
			dslSql.append(parameterSqlFunc(paramMap));
		}

		dslSql.append(FROM + IndicatorLibMetaData.IND_DATA_LIB_TABLE + SPACE + TABLE_ALIAS);
		
		String whereSql = indicatorWhereSql(indicatorMap);
		// 由于可能只使用函数：取指标累计，导致没有whereSql生成
		if (whereSql != null && whereSql.length() > 0) {
			dslSql.append(WHERE);
			dslSql.append("(");
			//接参数where条件
			//dslSql.append(indicatorWhereSql(indicatorMap));
			dslSql.append(whereSql);
			dslSql.append(")");
		}

		dslSql.append(" GROUP BY " + TABLE_ALIAS + IDLLanguage.DOT
				+ IndicatorLibMetaData.OBJECT_ID_FROM_IND_LIB);
		dslSql.append(") "+ TABLE_ALIAS);
		
		if (!hasAgg) {
			//return "2:" + dslSql;
			return "1:" + dslSql;
		} else {
			if (rh.isSingleValue(usedRanges)) {
				return "3:" + dslSql;
			} else {
				return "4:" + dslSql;
			}
		}

	}
	/**
	 * 拼指标where条件
	 * @param indicatorMap
	 * @return
	 */
	private String indicatorWhereSql(Hashtable<String, Struct> indicatorMap) {
		Object ss[] = indicatorMap.keySet().toArray();
		int indicateCount = ss.length;
		StringBuffer dslSql = new StringBuffer();
		HashMap<String, StringBuffer> tempMap = new HashMap<String, StringBuffer>();
		for (int index = 0; index < indicateCount; index++) {
			Struct s = (Struct) indicatorMap.get(ss[index]);
			// 合计的使用函数(取指标累计)完成，不用where语句
			if (s.getPeriodRangeFlag()) {
				continue;
			}

			// 同一指标已经存在
			if (tempMap.containsKey(s.getId())) {
				StringBuffer whereapp = tempMap.get(s.getId());
				whereapp.append("," + getWhereIdl(s));
			} else {
				StringBuffer whereapp = new StringBuffer();
				whereapp.append(" " + TABLE_ALIAS + IDLLanguage.DOT
						+ IndicatorLibMetaData.IND_DATA_LIB_IND_ID + "='"
						+ s.getId() + "'");
				whereapp.append(" AND " + TABLE_ALIAS + IDLLanguage.DOT
						+ IndicatorLibMetaData.PERIOD_NO + " IN (");
				whereapp.append(getWhereIdl(s));
				tempMap.put(s.getId(), whereapp);
			}
		}
		Object obj[] = tempMap.keySet().toArray();
		for (int i = 0; i < obj.length; i++) {
			StringBuffer sb = tempMap.get(obj[i]);
			if (obj.length > 1) {
				dslSql.append("(" + sb.toString() + ") )");
			} else {
				dslSql.append("" + sb.toString() + ")");
			}
			if (i != obj.length - 1) {
				dslSql.append(" OR ");
			}
		}
		return dslSql.toString();
	}
	/**
	 * 拼参数
	 * @param paramMap
	 * @return
	 */
	private String parameterSqlFunc(Hashtable<String, Struct> paramMap) {
		StringBuffer dslSql = new StringBuffer();
		Object param[] = paramMap.keySet().toArray();
		int paramCount = param.length;
		//拼参数SQL
		for (int index = 0; index < paramCount; index ++) {
			Struct s = (Struct) paramMap.get(param[index]);
			String paramterId = s.getId();
			dslSql.append(ParameterHelper.parameterSqlFunc(paramterId));
			dslSql.append(SPACE + s.getAlias());
			if (index != paramCount - 1) {
				dslSql.append(",");
			}
		}
		return dslSql.toString();
	}
	/**
	 * 拼指标
	 * @param indicatorMap
	 * @return
	 */
	private String indicatorSql(Hashtable<String, Struct> indicatorMap) {
		Object ss[] = indicatorMap.keySet().toArray();
		int indicateCount = ss.length;
		StringBuffer dslSql = new StringBuffer();
		//拼指标
		for (int index = 0; index < indicateCount; index++) {
			Struct s = indicatorMap.get(ss[index]);
			if (!s.getPeriodRangeFlag()) {	// 非取指标累计部分，普通模式生成
				dslSql.append(" SUM (CASE WHEN");
				dslSql.append("  " + TABLE_ALIAS + IDLLanguage.DOT
						+ IndicatorLibMetaData.IND_DATA_LIB_IND_ID + "= '"
						+ s.getId() +  "'");//修改by qxy 20120809
				dslSql.append(AND + TABLE_ALIAS + IDLLanguage.DOT
						+ IndicatorLibMetaData.PERIOD_NO + " = ");
				dslSql.append(getWhereIdl(s));
				dslSql.append(" THEN " + TABLE_ALIAS + IDLLanguage.DOT
						+ IndicatorLibMetaData.IND_VALUE_FROM_IND_LIB + " END )");
				dslSql.append(" " + s.getAlias());
			}
			else {	// 取指标累计部分,
				String[] params = new String[3];
				//String targetCode = params[0];
		    	//String currentDate = params[1];
				//String periodType = params[2];
				String id = s.getId();
				String[] array = id.split("_");

				params[0] = IndicatorLibMetaData.IND_DATA_LIB_IND_ID;
				params[1] = IndicatorLibMetaData.PERIOD_NO ;
				params[2] = array[array.length-1];	// sumPeriod

				String sumSql = IDLLanguage.IDL_SUM_TARGETS_IN_PERIOD.toSQL(params);
				dslSql.append(sumSql);
				dslSql.append(" " + s.getAlias());
			}

			if (index != indicateCount - 1) {
				dslSql.append(",");
			}
		}
		return dslSql.toString();
	}

	
	/**
	 * @return 参数：id = 参数查询条件的sql(组合时以';'分隔),name = 参数别名, previous = 100
	 * 			EG:ID = param_group_code.'XXXX' 或 param_group_code.(select aa from table where bb = 'XXX')
	 * 		   指标: id = 指标代码, name = 指标别名, previous前一指标
	 */
	private Hashtable<String, Struct> getStructMap(Hashtable<String, Set<Integer>> indRef) {
		Iterator<Entry<String, Set<Integer>>> itr = indRef.entrySet().iterator();
		int countTable = 0;
		int countParam = 0;
		Hashtable<String, Struct> map = new Hashtable<String, Struct>();
		while (itr.hasNext()) {
			Entry<String, Set<Integer>> entry = itr.next();
			String key = entry.getKey();
			Set<Integer> set = entry.getValue();
			Object[] ps = set.toArray();

			for (int i = 0; i < ps.length; i++) {
				Integer previous = (Integer) ps[i];
				Struct struct = new Struct();
				String alias = "";
				/**
				if (previous.intValue() == ParameterLibMetaData.PARAMETER_KEY.intValue()) {
					//系统参数
					alias = ParameterLibMetaData.PARAMETER_ALIAS + countParam++;
					struct.setType(ParameterTakeholder.PARAMETER);
				} else {
					alias = IndicatorLibMetaData.IND_ALIAS + countTable++;
					struct.setType(IndicatorTakeHolder.INDICATOR);
				}
				**/

				String keyForMap = key + previous;
				
				if (previous.intValue() == ParameterLibMetaData.PARAMETER_KEY.intValue()) {
					//系统参数
					alias = ParameterLibMetaData.PARAMETER_ALIAS + countParam++;
					struct.setType(ParameterTakeholder.PARAMETER);
				} 
				else if (previous.intValue() == SUM_INDICATOR) { // 对于累计指标，采取不同的alias和不同的key，以便区分
					// key: xxxx_SUM_3
					alias = IndicatorLibMetaData.IND_ALIAS + SUM_PADDING + countTable++;
					struct.setType(IndicatorTakeHolder.INDICATOR);
					struct.setPeriodRangeFlag(true);
					keyForMap = key;
				}
				else {
					alias = IndicatorLibMetaData.IND_ALIAS + countTable++;
					struct.setType(IndicatorTakeHolder.INDICATOR);
				}
				
				struct.setPeriod(previous);
				struct.setAlias(alias);
				struct.setId(key);
				//
				//map.put(key + previous, struct);
				map.put(keyForMap, struct);
			}
		}
		return map;
	}
	/**
	 * Unit中指标和参数统一
	 * @param ast
	 * @param periodType
	 * @return 
	 * 这里最主要的是指出对指标,系统参数的引用(包括operation,function中的引用),并返回映射关系供后续使用
	 * 返回Hashtable<String, Set<Integer>>, String为引用的主键(指标/系统参数名), Set表存放各种引用类型
	 */
	private Hashtable<String, Set<Integer>> getUnitRef(Unit ast, String periodType) {
		Hashtable<String, Set<Integer>> ref = new Hashtable<String, Set<Integer>>();
		//指标
		if (ast.type == IndicatorTakeHolder.INDICATOR) {
			String indicator = ast.oprands.get(0).toString();
			Set<Integer> set = (Set<Integer>) ref.get(indicator);
			if (set == null) {
				set = new HashSet<Integer>();
				ref.put(indicator, set);
			}
			set.add(new Integer(0));
			return ref;
		}
		//系统参数
		if (ast.type == ParameterTakeholder.PARAMETER) {
			String parameter = ast.oprands.get(0).toString();
			//重新设置参数类别代码.参数代码
			parameter = ParameterHelper.resetParamExp(parameter, ast, PARAM_DSL_EXP);
			Set<Integer> set = (Set<Integer>) ref.get(parameter);
			if (set == null) {
				set = new HashSet<Integer>();
				ref.put(parameter, set);
			}
			set.add(ParameterLibMetaData.PARAMETER_KEY);
			return ref;
		}
		if (ast.type == Unit.OPERATION) {
			int length = ast.oprands.size();
			if (length == 1) {
				Unit u = (Unit) ast.oprands.get(0);
				Hashtable<String, Set<Integer>> right = getUnitRef(u, periodType);
				Object[] keys = right.keySet().toArray();
				for (int i = 0; i < keys.length; i++) {
					String key = keys[i].toString();
					Set<Integer> set = (Set<Integer>) ref.get(key);
					if (set == null) {
						set = new HashSet<Integer>();
						ref.put(key, set);
					}
					Set<Integer> rs = (Set<Integer>) right.get(key);
					set.addAll(rs);
				}
			} else {
				Unit lu = (Unit) ast.oprands.get(0);
				Hashtable<String, Set<Integer>> left = getUnitRef(lu, periodType);
				Object[] keys = left.keySet().toArray();
				for (int i = 0; i < keys.length; i++) {
					String key = keys[i].toString();
					Set<Integer> set = (Set<Integer>) ref.get(key);
					if (set == null) {
						set = new HashSet<Integer>();
						ref.put(key, set);
					}
					Set<Integer> ls = (Set<Integer>) left.get(key);
					set.addAll(ls);
				}
				Unit ru = (Unit) ast.oprands.get(1);
				Hashtable<String, Set<Integer>> right = getUnitRef(ru, periodType);
				keys = right.keySet().toArray();
				for (int i = 0; i < keys.length; i++) {
					String key = keys[i].toString();
					Set<Integer> set = (Set<Integer>) ref.get(key);
					if (set == null) {
						set = new HashSet<Integer>();
						ref.put(key, set);
					}
					Set<Integer> rs = (Set<Integer>) right.get(key);
					set.addAll(rs);
				}
			}
		}
		if (ast.type == Unit.FUNCTION) {
			com.soule.crm.pfm.idl.ast.Function func = (com.soule.crm.pfm.idl.ast.Function) ast.ref;
			String name = ast.operator;
			if (name.equals(IndicatorValueFunction.name)) {
				Unit u = (Unit) ast.oprands.get(0);
				String indicator = u.oprands.get(0).toString();
				Set<Integer> set = (Set<Integer>) ref.get(indicator);
				if (set == null) {
					set = new HashSet<Integer>();
					ref.put(indicator, set);
				}
				Unit u2 = (Unit) ast.oprands.get(1);
				String period = ((Unit) u2.oprands.get(0)).oprands.get(0).toString();
				set.add(new Integer(period));
				return ref;
			}

			// 新增 GetSumTargetsInPeriodFunction
			if (name.equals(GetSumTargetsInPeriodFunction.name)) {
				Unit u = (Unit) ast.oprands.get(0);
				String sumIndicator = u.oprands.get(0).toString();
				if (sumIndicator.charAt(0) == '\'') {
					sumIndicator = sumIndicator.substring(1);
				}
				if (sumIndicator.charAt(sumIndicator.length()-1) == '\'') {
					sumIndicator = sumIndicator.substring(0, sumIndicator.length()-1);
				}
				
				Unit u2 = (Unit) ast.oprands.get(1);
				String sumPeriod = (String) u2.oprands.get(0);
				String key = sumIndicator + SUM_PADDING + sumPeriod;

				Set<Integer> set = (Set<Integer>) ref.get(key);
				if (set == null) {
					set = new HashSet<Integer>();
					ref.put(key, set);
				}
				set.add(new Integer(SUM_INDICATOR));
				return ref;
			}
			
			if (func.isAggFunction()) {
				ref.put("AGG", new HashSet<Integer>());
				return ref;
			}
			if (func.isDeriveFunction()) {
				Unit u = (Unit) ast.oprands.get(0);
				String indicator = u.oprands.get(0).toString();
				Set<Integer> set = (Set<Integer>) ref.get(indicator);
				if (set == null) {
					set = new HashSet<Integer>();
					ref.put(indicator, set);
				}
				set.add(new Integer(0));
				if (CraFunction.name.equals(name)
						|| CrsFunction.name.equals(name)) {
					set.add(new Integer(-1));
				} else if (CwaFunction.name.equals(name)
						|| CwsFunction.name.equals(name)) {
					set.add(new Integer(-12));
					// hlv 修改end:
				} else if (CwFYFunction.name.equals(name)) {
					set.add(new Integer(11));
				} else if (CwFQFunction.name.equals(name)) {
					set.add(new Integer(12));
				}
				return ref;
			}
			Object[] opa = ast.oprands.toArray();
			for (int i = 0; i < opa.length; i++) {
				Unit u = (Unit) opa[i];
				Hashtable<String, Set<Integer>> right = getUnitRef(u, periodType);
				Object[] keys = right.keySet().toArray();
				for (int j = 0; j < keys.length; j++) {
					String key = keys[j].toString();
					Set<Integer> set = (Set<Integer>) ref.get(key);
					if (set == null) {
						set = new HashSet<Integer>();
						ref.put(key, set);
					}
					Set<Integer> rs = (Set<Integer>) right.get(key);
					set.addAll(rs);
				}
			}
		}
		return ref;
	}
	/**
	 * 获得所定类型的struct
	 * @param map
	 * @return
	 */
	private Hashtable<String, Struct> getStructByType(Hashtable<String, Struct> map, int type) {
		Hashtable<String, Struct> ref = new Hashtable<String, Struct>();
		Iterator<String> ite = map.keySet().iterator();
		while (ite.hasNext()) {
			String key = (String)ite.next();
			Struct s = (Struct)map.get(key);
			if (s.getType() == type) {
				ref.put(key, s);
			}
		}
		return ref;
	}

	private String getWhereIdl(Struct s) {
		StringBuffer sb = new StringBuffer();
		if (s.getPeriod().intValue() <= 0) {
			String[] params = new String[] { IndicatorLibMetaData.PERIOD_NO,
					s.getPeriod() + "", s.getId() };
			sb.append(IDLLanguage.IDL_GET_PERIOD.toSQL(params));
		} else if (s.getPeriod().intValue() <= 6 && s.getPeriod().intValue() >= 1) {
			String[] params = new String[] { IndicatorLibMetaData.PERIOD_NO,
					s.getPeriod() + "" };
			sb.append(IDLLanguage.IDL_GET_DAT_FST.toSQL(params));
		} else if (s.getPeriod().intValue() == 11) {
			sb.append(GET_PERIOD_CODE_4_FIRST_OF_YEAR);
		} else if (s.getPeriod().intValue() == 12) {
			sb.append(GET_PERIOD_CODE_4_FIRST_OF_QUATER);
		}
		return sb.toString();
	}

	/**
	 * 生成DSL查询计算公式
	 * @param ast
	 * @param map indicator+period或者是parameter + 100: struct
	 * @return formula
	 * @throws TokenException
	 */
	private String toFormula(Unit ast, Hashtable<String, Struct> map, String periodType)
			throws TokenException {

		if (ast.operator == null) {
			String exp = ast.oprands.get(0).toString();
			//指标
			if (ast.type == IndicatorTakeHolder.INDICATOR) {
				String key = exp + 0;
				Struct s = (Struct) map.get(key);
				if (s == null) {
					return exp;
				}
				return s.getAlias();
			}
			//系统
			if (ast.type == ParameterTakeholder.PARAMETER) {
				//重新设置参数类型代码.参数代码
				String key = ParameterHelper.resetParamExp(exp, ast, PARAM_DSL_EXP);
				key = key + ParameterLibMetaData.PARAMETER_KEY;
				Struct s = (Struct) map.get(key);
				if (s == null) {
					return exp;
				}
				return s.getAlias();
			}

			if (ast.type == Unit.ASSERT) {

				return AssertManager.getInstance().toSQL(exp);
			}
			Object o = ast.oprands.get(0);
			return o.toString();
		}
		StringBuffer sb = new StringBuffer();
		if (ast.type == Unit.OPERATION) {
			int length = ast.oprands.size();
			if (length == 1) {
				Unit u = (Unit) ast.oprands.get(0);
				String right = toFormula(u, map, periodType);
				sb.append(OPENED_BRACKET);
				sb.append(ast.operator).append(SPACE).append(right);
				sb.append(CLOSED_BRACKET);
			} else {
				Unit lu = (Unit) ast.oprands.get(0);
				String left = toFormula(lu, map, periodType);

				Unit ru = (Unit) ast.oprands.get(1);
				String right = toFormula(ru, map, periodType);
				sb.append(OPENED_BRACKET);
				//如果是'/'号时，需要检查分母是否为0
				if (ast.operator.equals(DivOperator.DIV)) {
					sb.append(left).append(SPACE).append(ast.operator)
						.append(SPACE)
						.append("(CASE WHEN " + right + " = 0 THEN NULL ELSE " + right + " END)");
				} else {
					sb.append(left).append(SPACE).append(ast.operator).append(SPACE).append(right);
				}
				sb.append(CLOSED_BRACKET);
			}
		}
		if (ast.type == Unit.FUNCTION) {
			String name = ast.operator;
			com.soule.crm.pfm.idl.ast.Function func = (com.soule.crm.pfm.idl.ast.Function) ast.ref;

			if (name.equals(IfFunction.IIF) || name.equals(IfFunction.name)) {
				Unit u = (Unit) ast.oprands.get(0);
				String condition = toFormula(u, map, periodType);
				Unit u1 = (Unit) ast.oprands.get(1);
				String c1 = toFormula(u1, map, periodType);
				Unit u2 = (Unit) ast.oprands.get(2);
				String c2 = toFormula(u2, map, periodType);
				return "(CASE WHEN " + condition + " THEN " + c1 + " ELSE "
						+ c2 + " END)";
			}
			// 舍入函数
			if (name.equals(RoundFunction.name)) {
				Unit u1 = (Unit) ast.oprands.get(0);
				String c1 = toFormula(u1, map, periodType);
				Unit u2 = (Unit) ast.oprands.get(1);
				String c2 = ((u2.oprands.get(0))).toString();
				return RoundFunction.ROUND + IDLLanguage.OPENED_BRACKET + c1
						+ IDLLanguage.COMMA + c2 + IDLLanguage.CLOSED_BRACKET;
			}
			// 空值替换函数
			if (name.equals(NVLFunction.name)) {
				Unit u1 = (Unit) ast.oprands.get(0);
				String exp1 = toFormula(u1, map, periodType);
				Unit u2 = (Unit) ast.oprands.get(1);
				String exp2 = toFormula(u2, map, periodType);

				return "(CASE WHEN " + exp1 + " IS NULL THEN " + exp2
						+ " ELSE " + exp1 + " END)";
			}

			if (name.equals(IndicatorValueFunction.name)) {
				Unit u = (Unit) ast.oprands.get(0);
				String indicator = u.oprands.get(0).toString();
				Unit u2 = (Unit) ast.oprands.get(1);
				String period = ((Unit) u2.oprands.get(0)).oprands.get(0)
						.toString();
				String key = indicator + period;
				Struct s = (Struct) map.get(key);
				return s.getAlias();

			}
			
			// 新增 GetSumTargetsInPeriodFunction
			if (name.equals(GetSumTargetsInPeriodFunction.name)) {
				Unit u = (Unit) ast.oprands.get(0);
				String sumIndicator = u.oprands.get(0).toString();
				// 去掉指标代码前后可能的''
				if (sumIndicator.charAt(0) == '\'') {
					sumIndicator = sumIndicator.substring(1);
				}
				if (sumIndicator.charAt(sumIndicator.length()-1) == '\'') {
					sumIndicator = sumIndicator.substring(0, sumIndicator.length()-1);
				}
				Unit u2 = (Unit) ast.oprands.get(1);
				String sumPeriod = (String)u2.oprands.get(0);;
				String key = sumIndicator + SUM_PADDING + sumPeriod;
				Struct s = (Struct) map.get(key);
				return s.getAlias();
			}
			
			if (func.isAggFunction()) {
				Unit u1 = (Unit) ast.oprands.get(0);
				String p1 = u1.oprands.get(0).toString();
				Unit u2 = (Unit) ast.oprands.get(1);
				String p2 = u2.oprands.get(0).toString();
				String[] params = new String[] { p1, p2 };
				return func.toSQL(params);
			}
			if (func.isDeriveFunction()) {
				Unit u = (Unit) ast.oprands.get(0);
				String indicator = u.oprands.get(0).toString();
				Struct s1 = (Struct) map.get(indicator + 0);
				if (CraFunction.name.equals(name)) {
					String key = indicator + ((-1));
					Struct s2 = (Struct) map.get(key);
					sb.append(OPENED_BRACKET);
					sb.append("CASE WHEN ");
					sb.append(s2.getAlias());
					sb.append(" <> 0  THEN ");
					sb.append(OPENED_BRACKET);
					sb.append(OPENED_BRACKET);
					sb.append("(CASE WHEN "+s1.getAlias()+" IS NULL THEN 0 ELSE "+s1.getAlias()+" END)");
					sb.append(" - ");
					sb.append("(CASE WHEN "+s2.getAlias()+" IS NULL THEN 0 ELSE "+s2.getAlias()+" END)");
					sb.append(CLOSED_BRACKET);
					sb.append(" / ");
					sb.append(s2.getAlias());
					sb.append(CLOSED_BRACKET);
					sb.append(" ELSE NULL END ");
					sb.append(CLOSED_BRACKET);
					return sb.toString();
				}
				if (CrsFunction.name.equals(name)) {
					String key = indicator + ((-1));
					Struct s2 = (Struct) map.get(key);
					sb.append(OPENED_BRACKET);
					sb.append("(CASE WHEN "+s1.getAlias()+" IS NULL THEN 0 ELSE "+s1.getAlias()+" END)");
					sb.append(" - ");
					sb.append("(CASE WHEN "+s2.getAlias()+" IS NULL THEN 0 ELSE "+s2.getAlias()+" END)");
					sb.append(CLOSED_BRACKET);
					return sb.toString();
				}
				if (CwaFunction.name.equals(name)) {
					String key = indicator + (-12);
					Struct s2 = (Struct) map.get(key);
					sb.append(OPENED_BRACKET);
					sb.append("CASE WHEN ");
					sb.append(s2.getAlias());
					sb.append(" <> 0  THEN ");
					sb.append(OPENED_BRACKET);
					sb.append(OPENED_BRACKET);
					sb.append("(CASE WHEN "+s1.getAlias()+" IS NULL THEN 0 ELSE "+s1.getAlias()+" END)");
					sb.append(" - ");
					sb.append("(CASE WHEN "+s2.getAlias()+" IS NULL THEN 0 ELSE "+s2.getAlias()+" END)");
					sb.append(CLOSED_BRACKET);
					sb.append(" / ");
					sb.append(s2.getAlias());
					sb.append(CLOSED_BRACKET);
					sb.append(" ELSE NULL END ");
					sb.append(CLOSED_BRACKET);
					return sb.toString();

				}
				if (CwsFunction.name.equals(name)) {
					String key = indicator + (-12);
					Struct s2 = (Struct) map.get(key);
					sb.append(OPENED_BRACKET);
					sb.append("(CASE WHEN "+s1.getAlias()+" IS NULL THEN 0 ELSE "+s1.getAlias()+" END)");
					sb.append(" - ");
					sb.append("(CASE WHEN "+s2.getAlias()+" IS NULL THEN 0 ELSE "+s2.getAlias()+" END)");
					sb.append(CLOSED_BRACKET);
					return sb.toString();
				}

				if (CwFYFunction.name.equals(name)) {
					String key = indicator + 11;
					Struct s2 = (Struct) map.get(key);
					sb.append(OPENED_BRACKET);
					sb.append("(CASE WHEN "+s1.getAlias()+" IS NULL THEN 0 ELSE "+s1.getAlias()+" END)");
					sb.append(" - ");
					sb.append("(CASE WHEN "+s2.getAlias()+" IS NULL THEN 0 ELSE "+s2.getAlias()+" END)");
					sb.append(CLOSED_BRACKET);
					return sb.toString();
				}
				if (CwFQFunction.name.equals(name)) {
					String key = indicator + 12;
					Struct s2 = (Struct) map.get(key);
					sb.append(OPENED_BRACKET);
					sb.append("(CASE WHEN "+s1.getAlias()+" IS NULL THEN 0 ELSE "+s1.getAlias()+" END)");
					sb.append(" - ");
					sb.append("(CASE WHEN "+s2.getAlias()+" IS NULL THEN 0 ELSE "+s2.getAlias()+" END)");
					sb.append(CLOSED_BRACKET);
					return sb.toString();
				}

			}

			Object[] us = ast.oprands.toArray();
			String[] ps = new String[us.length];
			for (int i = 0; i < us.length; i++) {
				Unit u = (Unit) us[i];
				String p = toFormula(u, map, periodType);
				ps[i] = p;

			}
			if (GetPeriodFunction.name.equals(func.getName())) {
				String p1 = ps[0];
				ps = new String[2];
				ps[0] = IndicatorLibMetaData.PERIOD_NO;
				ps[1] = p1;
			}
			if (GetDateFstFunction.name.equals(func.getName())) {
				String p1 = ps[0];
				ps = new String[2];
				ps[0] = IndicatorLibMetaData.PERIOD_NO;
				ps[1] = p1;
			}

			// 新增取周期内天数方法(暂时认为此方法只会在formula中出现)
			if (GetDaysInPeriodFunction.name.equals(func.getName())) {
				String p1 = ps[0];
				String p2 = ps[1];
				ps = new String[3];
				ps[0] = IndicatorLibMetaData.PERIOD_NO;
				ps[1] = p1;
				ps[2] = p2;
			}

			sb.append(func.toSQL(ps));

		}

		return sb.toString();

	}

}