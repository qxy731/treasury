package com.soule.app.monitor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SqlMonitorModel {
    private Date startTime;
    private HashMap<String, SqlDetailModel> datas = new HashMap<String, SqlDetailModel>();
    private ArrayList<SqlDetailModel> datalist = new ArrayList<SqlDetailModel>();

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public SqlDetailModel getData(String sqlid) {
        return datas.get(sqlid);
    }

    public HashMap<String, SqlDetailModel> getDatas() {
        return datas;
    }

    public void setDatas(HashMap<String, SqlDetailModel> datas) {
        this.datas = datas;
    }

    public void addData(SqlDetailModel sdm) {
        datas.put(sdm.getSqlId(), sdm);
        datalist.add(sdm);
    }

    public ArrayList<SqlDetailModel> getDataList(String filterId) {
        ArrayList<SqlDetailModel> ret = new ArrayList<SqlDetailModel>();
        for (SqlDetailModel sdm : datalist) {
            if (filterId == null || sdm.getSqlId().startsWith(filterId)) {
                ret.add(sdm);
                sdm.setAverageTime(sdm.getExecAllTime() / sdm.getExecTimes());
            }
        }
        return ret;
    }
}
