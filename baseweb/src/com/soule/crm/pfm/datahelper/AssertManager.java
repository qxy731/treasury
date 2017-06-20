package com.soule.crm.pfm.datahelper;

import java.util.HashMap;
import java.util.Map;

import com.soule.crm.pfm.dsl.parser.TokenException;

public class AssertManager {
    private static AssertManager instance = new AssertManager();

    private Map assert2SQLCondition4p = new HashMap();

    private Map assert2SQLCondition4g = new HashMap();

    private AssertManager() {
        init();
    }

    public static AssertManager getInstance() {
        return instance;
    }

    private void init() {
    }

    public String toSQL(String word) throws TokenException {
        String sql = null;
        String key = word.replaceAll("\\s", word);
        sql = (String) this.assert2SQLCondition4p.get(key);
        if (sql == null) {
            sql = (String) this.assert2SQLCondition4g.get(key);
        }
        if (sql == null)
            throw new TokenException("无法理解的限制条件");
        return sql;
    }
}
