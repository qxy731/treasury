package com.soule.crm.monitor.realtime.sql;

import java.io.Serializable;
import java.util.Date;

/**
 * 参数传递Sql监控明细的类
 */
public class SqlDetailPo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sqlId;
    private String useMaxTime;
    private String useMinTime;
    private String execTimes;
    private String execAllTime;
    private String lastUseTime;
    private Date lastStartTime;
    private String averageTime;
    private String sqlContent;
    private String sqlParamsSlow;
    private Date startTimeSlow;
    private String sqlParamsFast;
    private Date startTimeFast;

    /**
     * @return 语句ID
     */
    public String getSqlId() {
        return sqlId;
    }

    /**
     * @param sqlId 语句ID
     */
    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }
    /**
     * @return 最长时间
     */
    public String getUseMaxTime() {
        return useMaxTime;
    }

    /**
     * @param useMaxTime 最长时间
     */
    public void setUseMaxTime(String useMaxTime) {
        this.useMaxTime = useMaxTime;
    }
    /**
     * @return 最短时间
     */
    public String getUseMinTime() {
        return useMinTime;
    }

    /**
     * @param useMinTime 最短时间
     */
    public void setUseMinTime(String useMinTime) {
        this.useMinTime = useMinTime;
    }
    /**
     * @return 执行次数
     */
    public String getExecTimes() {
        return execTimes;
    }

    /**
     * @param execTimes 执行次数
     */
    public void setExecTimes(String execTimes) {
        this.execTimes = execTimes;
    }
    /**
     * @return 执行总时间
     */
    public String getExecAllTime() {
        return execAllTime;
    }

    /**
     * @param execAllTime 执行总时间
     */
    public void setExecAllTime(String execAllTime) {
        this.execAllTime = execAllTime;
    }
    /**
     * @return 最后一次执行使用时间
     */
    public String getLastUseTime() {
        return lastUseTime;
    }

    /**
     * @param lastUseTime 最后一次执行使用时间
     */
    public void setLastUseTime(String lastUseTime) {
        this.lastUseTime = lastUseTime;
    }
    /**
     * @return 最后一次开始时间
     */
    public Date getLastStartTime() {
        return lastStartTime;
    }

    /**
     * @param lastStartTime 最后一次开始时间
     */
    public void setLastStartTime(Date lastStartTime) {
        this.lastStartTime = lastStartTime;
    }
    /**
     * @return 平均时间
     */
    public String getAverageTime() {
        return averageTime;
    }

    /**
     * @param averageTime 平均时间
     */
    public void setAverageTime(String averageTime) {
        this.averageTime = averageTime;
    }
    /**
     * @return SQL语句
     */
    public String getSqlContent() {
        return sqlContent;
    }

    /**
     * @param sqlContent SQL语句
     */
    public void setSqlContent(String sqlContent) {
        if (sqlContent != null ) {
            sqlContent = sqlContent.trim();
        }
        this.sqlContent = sqlContent;
    }
    /**
     * @return 最慢一次参数
     */
    public String getSqlParamsSlow() {
        return sqlParamsSlow;
    }

    /**
     * @param sqlParamsSlow 最慢一次参数
     */
    public void setSqlParamsSlow(String sqlParamsSlow) {
        this.sqlParamsSlow = sqlParamsSlow;
    }
    /**
     * @return 最快一次参数
     */
    public String getSqlParamsFast() {
        return sqlParamsFast;
    }

    /**
     * @param sqlParamsFast 最快一次参数
     */
    public void setSqlParamsFast(String sqlParamsFast) {
        this.sqlParamsFast = sqlParamsFast;
    }

    public Date getStartTimeSlow() {
        return startTimeSlow;
    }

    public void setStartTimeSlow(Date startTimeSlow) {
        this.startTimeSlow = startTimeSlow;
    }

    public Date getStartTimeFast() {
        return startTimeFast;
    }

    public void setStartTimeFast(Date startTimeFast) {
        this.startTimeFast = startTimeFast;
    }
}