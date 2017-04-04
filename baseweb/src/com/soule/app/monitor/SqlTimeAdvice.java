package com.soule.app.monitor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SqlTimeAdvice implements MethodInterceptor {

    protected final Log logger = LogFactory.getLog(SqlTimeAdvice.class);

    /**
     * 开启监控标志
     */
    private Boolean runFlag = Boolean.TRUE;
    private SqlMonitorManager mgr = new SqlMonitorManager();

    /**
     * 拦截要执行的目标方法
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object result = null;
        if (runFlag) {
            StopWatch clock = new StopWatch();
            clock.start();
            result = invocation.proceed();
            clock.stop();

            Object[] args = invocation.getArguments();
            String sqlid = ((args != null && args.length > 0) ? (String) args[0] : null);
            if (sqlid != null) {
                mgr.caculate(sqlid, clock.getTime(), args);
            }
        } else {
            result = invocation.proceed();
        }
        return result;
    }

    public Boolean getRunFlag() {
        return runFlag;
    }

    public void setRunFlag(Boolean runFlag) {
        this.runFlag = runFlag;
    }

    public SqlMonitorManager getMgr() {
        return mgr;
    }

}
