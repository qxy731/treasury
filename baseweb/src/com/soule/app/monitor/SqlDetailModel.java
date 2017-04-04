package com.soule.app.monitor;

public class SqlDetailModel {
	/**
	 * ibatis sqlid
	 */
	private String sqlId;
	/**
	 * 使用最长时间
	 */
	private long useMaxTime;
	/**
	 * 最长时间任务开始时间
	 */
	private long useMaxStartTime;
	/**
	 * 最短时间
	 */
	private long useMinTime;
	/**
	 * 最短时间任务开始时间
	 */
	private long useMinStartTime;
	/**
	 * 执行次数
	 */
	private long execTimes;
	/**
	 * 执行总时间
	 */
	private long execAllTime;
	/**
	 * 最后一次执行使用时间
	 */
	private long lastUseTime;
	/**
	 * 最后一次开始时间
	 */
	private long lastStartTime;
	/**
	 * 最慢一次使用的参数
	 */
	private Object slowArguments;
	/**
	 * 最快一次使用的参数
	 */
	private Object fastArguments;
	
	private long averageTime;

	public SqlDetailModel(String sqlid) {
		this.sqlId = sqlid;
		this.execTimes = 0;
		this.execAllTime = 0;
		this.lastUseTime = 0;
		this.lastStartTime = 0;
		this.useMaxTime = -1;
		this.useMinTime = Long.MAX_VALUE;
	}

	public String getSqlId() {
		return sqlId;
	}

	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}

	public long getUseMaxTime() {
		return useMaxTime;
	}

	public void setUseMaxTime(int useMaxTime) {
		this.useMaxTime = useMaxTime;
	}

	public long getUseMinTime() {
		return useMinTime;
	}

	public void setUseMinTime(int useMinTime) {
		this.useMinTime = useMinTime;
	}

	public long getExecTimes() {
		return execTimes;
	}

	public void setExecTimes(long execTimes) {
		this.execTimes = execTimes;
	}

	public long getExecAllTime() {
		return execAllTime;
	}

	public void setExecAllTime(long execAllTime) {
		this.execAllTime = execAllTime;
	}

	public long getLastUseTime() {
		return lastUseTime;
	}

	public void setLastUseTime(long lastUseTime) {
		this.lastUseTime = lastUseTime;
	}

	public long getLastStartTime() {
		return lastStartTime;
	}

	public void setLastStartTime(long lastStartTime) {
		this.lastStartTime = lastStartTime;
	}

	public void setUseMaxTime(long useMaxTime) {
		this.useMaxTime = useMaxTime;
	}

	public void setUseMinTime(long useMinTime) {
		this.useMinTime = useMinTime;
	}

	public Object getSlowArguments() {
		return slowArguments;
	}

	public void setSlowArguments(Object slowArguments) {
		this.slowArguments = slowArguments;
	}

	public Object getFastArguments() {
		return fastArguments;
	}

	public void setFastArguments(Object fastArguments) {
		this.fastArguments = fastArguments;
	}

	public long getUseMaxStartTime() {
		return useMaxStartTime;
	}

	public void setUseMaxStartTime(long useMaxStartTime) {
		this.useMaxStartTime = useMaxStartTime;
	}

	public long getUseMinStartTime() {
		return useMinStartTime;
	}

	public void setUseMinStartTime(long useMinStartTime) {
		this.useMinStartTime = useMinStartTime;
	}

	public synchronized void addInfo(long time, Object[] args) {
		this.execTimes = this.execTimes + 1;
		this.execAllTime = this.execAllTime + time;
		this.lastUseTime = time;
		this.lastStartTime = System.currentTimeMillis() - time;
		if (time > useMaxTime) {
			this.slowArguments = args.length > 1 ? args[1]:"";
			this.useMaxTime = time;
			this.useMaxStartTime = lastStartTime;
		}
		if (time < useMinTime) {
			this.fastArguments = args.length > 1 ? args[1]:"";
			this.useMinTime = time;
			this.useMinStartTime = lastStartTime;
		}
	}

    public long getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(long averageTime) {
        this.averageTime = averageTime;
    }

}
