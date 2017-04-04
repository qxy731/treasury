package com.soule.base.media.impl;

import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import com.ibatis.sqlmap.engine.mapping.parameter.ParameterMap;
import com.ibatis.sqlmap.engine.mapping.result.AutoResultMap;
import com.ibatis.sqlmap.engine.mapping.result.ResultMap;
import com.ibatis.sqlmap.engine.mapping.sql.Sql;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.mapping.statement.SelectStatement;
import com.ibatis.sqlmap.engine.scope.RequestScope;

public class CountStatementUtil {
    public static MappedStatement createCountStatement(MappedStatement selectStatement) {
        return new CountStatement((SelectStatement) selectStatement);
    }

    public static String getCountStatementId(String selectStatementId) {
        return selectStatementId + "Count__";
    }

}

class CountStatement extends SelectStatement {

    public CountStatement(SelectStatement selectStatement) {
        super();
        setId(CountStatementUtil.getCountStatementId(selectStatement.getId()));
        setResultSetType(selectStatement.getResultSetType());
        setFetchSize(1);
        setParameterMap(selectStatement.getParameterMap());
        setParameterClass(selectStatement.getParameterClass());
        setSql(new CountSql(selectStatement.getSql()));
        setSqlMapClient(selectStatement.getSqlMapClient());
        setTimeout(selectStatement.getTimeout());
        setResource(selectStatement.getResource());

//        List executeListeners = (List) ReflectUtil.getFieldValue(selectStatement, "executeListeners");
//        if (executeListeners != null) {
//            for (Object listener : executeListeners) {
//                addExecuteListener((ExecuteListener) listener);
//            }
//        }

        AutoResultMap resultMap = new AutoResultMap(((ExtendedSqlMapClient) getSqlMapClient()).getDelegate(), false);
        resultMap.setId(getId() + "-AutoResultMap");
        resultMap.setResultClass(Long.class);
        resultMap.setResource(getResource());
        setResultMap(resultMap);
    }
}
class CountSql implements Sql {
    private Sql sql;
    public CountSql(Sql s) {
        sql = s;
    }
    public void cleanup(RequestScope paramRequestScope) {
        sql.cleanup(paramRequestScope);
    }

    public ParameterMap getParameterMap(RequestScope paramRequestScope, Object paramObject) {
        return sql.getParameterMap(paramRequestScope, paramObject);
    }

    public ResultMap getResultMap(RequestScope paramRequestScope, Object paramObject) {
        return sql.getResultMap(paramRequestScope, paramObject);
    }

    public String getSql(RequestScope paramRequestScope, Object paramObject) {
        String sqlString = sql.getSql(paramRequestScope, paramObject);
        System.out.print("动态SQL ：" + paramRequestScope.getDynamicSql());
        int start = sqlString.toLowerCase().indexOf("from");
        if (start >= 0) {
            sqlString = "SELECT COUNT(1) AS c " + sqlString.substring(start);
        }
        return sqlString;
    }
}
