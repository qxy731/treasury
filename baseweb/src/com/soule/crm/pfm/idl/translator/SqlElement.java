package com.soule.crm.pfm.idl.translator;

import java.util.HashMap;
import java.util.Map;

import com.soule.crm.pfm.datahelper.IndicatorLibMetaData;
import com.soule.crm.pfm.idl.ast.IDLLanguage;

public class SqlElement {
	//private final static String TABLE_PREFIX = "RICK_T_";
	public static final String GET_PERIOD_CODE_4_FIRST_OF_YEAR = "IDL_GETPRDCD4LSTY(PERIOD_NO)";
	public static final String GET_PERIOD_CODE_4_FIRST_OF_QUATER = "IDL_GETPRDCD4LSTQ(PERIOD_NO)";
	private Map<Integer, String> indicateMap = new HashMap<Integer, String>();
	private Map<Integer, Integer> periodMap = new HashMap<Integer, Integer>();
	private Map<Integer, String> tableMap = new HashMap<Integer, String>();
	private String formulaExp = null;
	private int IndicateCount = 0;

	public SqlElement(String formulaExp, Map indicateMap) {
		this.formulaExp = formulaExp;
		init(indicateMap);
	}

	private void init(Map indicateMap) {
		Object ss[] = indicateMap.keySet().toArray();
		IndicateCount = ss.length;
		if (IndicateCount < 1) {
			throw new IllegalArgumentException("派生指标的基硄指标个数必须>=1");
		}
		for (int i = 0; i < IndicateCount; i++) {
			Object key = ss[i];
			FormulaStruct s = (FormulaStruct) indicateMap.get(key);
			addIndicate(i, s.indicatorID);
			addTable(i, s.fromName);
			addPeriod(i, s.period);
		}
	}

	public String getBaseTable() {
		StringBuffer result = new StringBuffer();
		result.append("SELECT DISTINCT ").append(IndicatorLibMetaData.OBJECT_ID_FROM_IND_LIB);
		result.append(" FROM ").append(IndicatorLibMetaData.IND_DATA_LIB_TABLE).append(" RICK_T_HLV");
		result.append(" WHERE ");
		result.append("RICK_T_HLV.").append(IndicatorLibMetaData.IND_DATA_LIB_IND_ID).append(" IN( ");
		for (int index = 0; index < IndicateCount; index++) {
			if (index != 0) {
				result.append(" , ");
			}
			result.append(getIndicate(index));
		}
		result.append(" )");
		result.append(" AND ").append("RICK_T_HLV.").append(IndicatorLibMetaData.PERIOD_NO).append(" IN( ");
		for (int index = 0; index < IndicateCount; index++) {
			if (index != 0) {
				result.append(" , ");
			}
			result.append(getPeriodNo(index));
		}
		result.append(" )");
		return result.toString();
	}

	public static void main(String[] args) {
		FormulaStruct s1 = new FormulaStruct();
		s1.fromName = "RICK_T_0";
		s1.indicatorID = "存款指标";
		s1.period = 0;
		FormulaStruct s2 = new FormulaStruct();
		s2.fromName = "RICK_T_1";
		s2.indicatorID = "贷款指标";
		s2.period = 0;
		FormulaStruct s3 = new FormulaStruct();
		s3.fromName = "RICK_T_2";
		s3.indicatorID = "贴现指标";
		s3.period = 0;
		Map ins = new HashMap();
		ins.put(1, s1);
		// ins.put(2, s2);
		// ins.put(3, s3);

		SqlElement tt = new SqlElement("((RICK_T_1.IND_VALUE + RICK_T_2.IND_VALUE) - RICK_T_0.IND_VALUE)", ins);
		System.out.println(tt.getBaseTable());
		System.out.println(tt.genSql());
	}

	public String genSql() {
		StringBuffer result = new StringBuffer();
		result.append("SELECT HLV.").append(IndicatorLibMetaData.OBJECT_ID_FROM_IND_LIB);
		result.append(" , ").append(formulaExp).append(" ").append(IndicatorLibMetaData.IND_VALUE_FROM_IND_LIB);
		result.append(" FROM ");
		result.append("( ").append(getBaseTable()).append(" ) HLV ");
		result.append("LEFT JOIN ");
		for (int index = 0; index < IndicateCount; index++) {
			result.append(" ( ").append(genIndicateTable(index)).append(" ) ").append(getTableExp(index));
			result.append(" ON ");
			result.append(" HLV.").append(IndicatorLibMetaData.OBJECT_ID_FROM_IND_LIB);
			result.append(" = ");
			result.append(getTableExp(index)).append(".").append(IndicatorLibMetaData.OBJECT_ID_FROM_IND_LIB);
			if (index != (IndicateCount - 1)) {
				result.append(" LEFT JOIN ");
			}
		}
		return result.toString();
	}

	public String genIndicateTable(int index) {
		StringBuffer result = new StringBuffer();
		result.append(" SELECT ");
		result.append(IndicatorLibMetaData.OBJECT_ID_FROM_IND_LIB);
		result.append(" , ");
		result.append(IndicatorLibMetaData.IND_VALUE_FROM_IND_LIB);
		result.append(" FROM ");
		String tableAlias=" RICK_T_HLV_"+index;
		result.append(IndicatorLibMetaData.IND_DATA_LIB_TABLE).append(tableAlias);
		result.append(" WHERE ");
		result.append(tableAlias).append(".").append(IndicatorLibMetaData.IND_DATA_LIB_IND_ID).append(" = ").append(getIndicate(index));
		result.append(" AND ");
		result.append(tableAlias).append(".").append(IndicatorLibMetaData.PERIOD_NO).append(" = ").append(getPeriodNo(index));
		return result.toString();
	}

	private String getPeriodNo(int index) {
		StringBuffer result = new StringBuffer();
		int flag = getPeriod(index);
		if (flag <= 0) {
			String[] params = new String[] { IndicatorLibMetaData.PERIOD_NO, getPeriod(index) + "", getIndicate(index) };
			result.append(IDLLanguage.IDL_GET_PERIOD.toSQL(params));
		} else if (flag <= 6 && flag >= 1) {
			String[] params = new String[] { IndicatorLibMetaData.PERIOD_NO, getPeriod(index) + "" };
			result.append(IDLLanguage.IDL_GET_DAT_FST.toSQL(params));
		} else if (flag == 11) {
			result.append(GET_PERIOD_CODE_4_FIRST_OF_YEAR);
		} else if (flag == 12) {
			result.append(GET_PERIOD_CODE_4_FIRST_OF_QUATER);
		}
		return result.toString();
	}

	public void addIndicate(int index, String indicateCode) {
		indicateMap.put(index, indicateCode);

	}

	public void addTable(int index, String tableName) {
		tableMap.put(index, tableName);
	}

	public void addPeriod(int index, Integer periodExp) {
		periodMap.put(index, periodExp);
	}

	public String getIndicate(int index) {
		return indicateMap.get(index);
	}

	public int getPeriod(int index) {
		return periodMap.get(index);
	}

	public String getIndicateExp(int index) {
		return IndicatorLibMetaData.IND_DATA_LIB_IND_ID + IDLLanguage.EQUAL + indicateMap.get(index);
	}

	public String getPeriodExp(int index) {
		return IndicatorLibMetaData.PERIOD_NO + IDLLanguage.EQUAL + periodMap.get(index);
	}

	public String getTableExp(int index) {
		return tableMap.get(index);
	}

}