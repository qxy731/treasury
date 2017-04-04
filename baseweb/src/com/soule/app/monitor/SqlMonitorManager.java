package com.soule.app.monitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.soule.crm.monitor.realtime.sql.SqlListIn;
import com.soule.crm.monitor.realtime.sql.SqlListOut;

public class SqlMonitorManager {

    private static SqlMonitorModel dataModel = new SqlMonitorModel();

    /**
     * 进行统计
     * 
     * @param sqlid
     * @param time
     * @param args
     */
    public void caculate(String sqlid, long time, Object[] args) {
        SqlDetailModel sdm = dataModel.getData(sqlid);
        if (sdm == null) {
            synchronized (this) {
                sdm = dataModel.getData(sqlid);
                if (sdm == null) {
                    sdm = new SqlDetailModel(sqlid);
                    dataModel.addData(sdm);
                }
            }
        }
        sdm.addInfo(time, args);
    }

    public synchronized void reset() {
        dataModel = null;
        dataModel = new SqlMonitorModel();
    }

    public List<SqlDetailModel> queryList(SqlListIn in, SqlListOut out) {
        int startIdx = (in.getInputHead().getPageNo() - 1) * in.getInputHead().getPageSize();
        int endIdx = startIdx + in.getInputHead().getPageSize();
        List<SqlDetailModel> list = dataModel.getDataList(in.getSqlId());
        out.getResultHead().setTotal(list.size());
        if (startIdx < list.size()) {
            sortList(list, in.getSortName(), in.getSortOrder());
            if (endIdx < list.size()) {
                list = list.subList(startIdx, endIdx);
            } else {
                list = list.subList(startIdx, list.size());
            }
        } else {
            list = new ArrayList<SqlDetailModel>();
        }
        return list;
    }

    private void sortList(List<SqlDetailModel> list, String sortName, String sortOrder) {
        int sortid = 0;
        if ("sqlId".equals(sortName)) {
            sortid = 0;
        } else if ("useMaxTime".equals(sortName)) {
            sortid = 1;
        } else if ("useMinTime".equals(sortName)) {
            sortid = 2;
        } else if ("execTimes".equals(sortName)) {
            sortid = 3;
        } else if ("execAllTime".equals(sortName)) {
            sortid = 4;
        } else if ("lastUseTime".equals(sortName)) {
            sortid = 5;
        } else if ("lastStartTime".equals(sortName)) {
            sortid = 6;
        } else if ("averageTime".equals(sortName)) {
            sortid = 7;
        }
        Collections.sort(list, new ComparatorSqlDetailModel(sortid, sortOrder));
    }

    public SqlDetailModel queryById(String sqlId) {
        return dataModel.getData(sqlId);
    }
}

class ComparatorSqlDetailModel implements Comparator<SqlDetailModel> {

    private int sortid;
    private String sortOrder;

    public ComparatorSqlDetailModel(int sortid, String sortOrder) {
        this.sortid = sortid;
        this.sortOrder = sortOrder;
    }

    public int compare(SqlDetailModel o1, SqlDetailModel o2) {
        int ret = 0;
        switch (sortid) {
        case 0:
            ret = o1.getSqlId().compareTo(o2.getSqlId());
            break;
        case 1:
            ret = o1.getUseMaxTime() > o2.getUseMaxTime() ? 1 : -1;
            break;
        case 2:
            ret = o1.getUseMinTime() > o2.getUseMinTime() ? 1 : -1;
            break;
        case 3:
            ret = o1.getExecTimes() > o2.getExecTimes() ? 1 : -1;
            break;
        case 4:
            ret = o1.getExecAllTime() > o2.getExecAllTime() ? 1 : -1;
            break;
        case 5:
            ret = o1.getLastUseTime() > o2.getLastUseTime() ? 1 : -1;
            break;
        case 6:
            ret = o1.getLastStartTime() > o2.getLastStartTime() ? 1 : -1;
            break;
        case 7:
            ret = o1.getAverageTime() > o2.getAverageTime() ? 1 : -1;
            break;
        }
        if ("desc".equals(sortOrder)) {
            ret = -ret;
        }
        return ret;
    }
}
