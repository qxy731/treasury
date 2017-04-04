package com.soule.base.dialect;

import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.mapping.statement.RowHandlerCallback;
import com.ibatis.sqlmap.engine.scope.RequestScope;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 针对iBatis的分页机制做的改进<br>
 */
public class IBatisSqlExecutor extends SqlExecutor {
    private static final Log logger = LogFactory.getLog(IBatisSqlExecutor.class);

    private IDialect dialect;

    /*
     * 确定是否使用平台优化过的分页机制
     */
    private boolean enableCustomizedPaged = true;

    public IDialect getDialect() {
        return dialect;
    }

    public void setDialect(IDialect dialect) {
        this.dialect = dialect;
    }

    public boolean isEnableCustomizedPaged() {
        return enableCustomizedPaged;
    }

    public void setEnableCustomizedPaged(boolean enableCustomizedPaged) {
        this.enableCustomizedPaged = enableCustomizedPaged;
    }

    @Override
    public void executeQuery(RequestScope request, Connection conn, String sql, Object[] parameters, int skipResults,
            int maxResults, RowHandlerCallback callback) throws SQLException {
        if ((skipResults != NO_SKIPPED_RESULTS || maxResults != NO_MAXIMUM_RESULTS) && supportsPaged()) {
            sql = dialect.getPagedString(sql, skipResults, maxResults);
            if (logger.isDebugEnabled()) {
                logger.debug(sql);
            }
            skipResults = NO_SKIPPED_RESULTS;
            maxResults = NO_MAXIMUM_RESULTS;
        }
        super.executeQuery(request, conn, sql, parameters, skipResults, maxResults, callback);
    }

    /*
     * 即使是要使用平台提供的优化分页功能，也要再判断数据库本身是否支持分页 1：如果数据库支持分页，则从对应的dialect中获取分页语句
     * 2：如果数据库不支持分页，则按照iBatis的ResultSet的absolute方法进行。
     */
    public boolean supportsPaged() {
        if (enableCustomizedPaged && dialect != null) {
            return dialect.supportsPaged();
        }
        return false;
    }

}
