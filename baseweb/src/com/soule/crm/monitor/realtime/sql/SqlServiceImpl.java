package com.soule.crm.monitor.realtime.sql;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.scope.RequestScope;
import com.soule.MsgConstants;
import com.soule.app.monitor.SqlDetailModel;
import com.soule.app.monitor.SqlTimeAdvice;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;

/**
 * Sql监控业务操作
 */
@Service
public class SqlServiceImpl implements ISqlService {

    private final static Log logger = LogFactory.getLog(SqlServiceImpl.class);
    @Autowired
    private SqlTimeAdvice sta;
    @Autowired
    private IDefaultService sDefault;
    /**
     * Sql执行情况查询
     */
    public SqlListOut list(SqlListIn in) throws ServiceException {
        SqlListOut out = new SqlListOut();
        List<SqlDetailModel> list = sta.getMgr().queryList(in,out);
        ArrayList<SqlDetailPo> nlist = new ArrayList<SqlDetailPo>();
        for (SqlDetailModel sdm : list) {
            SqlDetailPo sdp = copyDetailInfo(sdm);
            nlist.add(sdp);
        }
        out.setDetail(nlist);
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }

    private SqlDetailPo copyDetailInfo(SqlDetailModel sdm) {
        SqlDetailPo sdp = new SqlDetailPo();
        sdp.setExecAllTime("" + sdm.getExecAllTime() / 1000f);
        sdp.setAverageTime("" + sdm.getAverageTime() / 1000f);
        sdp.setExecTimes("" + sdm.getExecTimes());
        sdp.setLastStartTime(convertDate(sdm.getLastStartTime()));
        sdp.setLastUseTime("" + sdm.getLastUseTime() / 1000f);
        sdp.setSqlId(sdm.getSqlId());
        sdp.setUseMaxTime("" + sdm.getUseMaxTime() / 1000f);
        sdp.setUseMinTime("" + sdm.getUseMinTime() / 1000f);
        String tmp = null;
        if (sdm.getFastArguments() instanceof String) {
            tmp = sdm.getFastArguments().toString();
        }
        else {
            tmp = JSONObject.fromObject(sdm.getFastArguments()).toString();
        }
        sdp.setSqlParamsFast(tmp);
        if (sdm.getSlowArguments() instanceof String) {
            tmp = sdm.getSlowArguments().toString();
        }
        else {
            tmp = JSONObject.fromObject(sdm.getSlowArguments()).toString();
        }
        sdp.setSqlParamsSlow(tmp);
        sdp.setStartTimeFast(convertDate(sdm.getUseMinStartTime()));
        sdp.setStartTimeSlow(convertDate(sdm.getUseMaxStartTime()));
        return sdp;
    }

    private Date convertDate(long lastStartTime) {
        Date d = new Date(lastStartTime);
        return d;
    }

    /**
     * Sql执行明细情况查询
     */
    public SqlDetailOut detail(SqlDetailIn in) throws ServiceException {
        SqlDetailOut out = new SqlDetailOut();
        SqlDetailModel sdm = sta.getMgr().queryById(in.getSqlId());
        SqlDetailPo sdp = null;
        if (sdm != null) {
            sdp = copyDetailInfo(sdm);
            fillSqlStatement(sdp);
        }
        if (sdp == null) {
            sdp = new SqlDetailPo();
        }
        out.setDetail(sdp);
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }

    private void fillSqlStatement(SqlDetailPo sdp) {
        try {
            SqlMapClient sqlMapClient = sDefault.getIbatisMediator().getSqlMapClientTemplate().getSqlMapClient();
            if (sqlMapClient instanceof ExtendedSqlMapClient) {
                SqlMapExecutorDelegate delegate = ((ExtendedSqlMapClient) sqlMapClient).getDelegate();
                MappedStatement sql = delegate.getMappedStatement(sdp.getSqlId());
                RequestScope requestScope = new RequestScope();
                requestScope.setStatement(sql);
                SqlDetailModel sdm = sta.getMgr().queryById(sdp.getSqlId());
                sdp.setSqlContent(sql.getSql().getSql(requestScope, sdm.getSlowArguments()));
            }
        } catch (Exception e) {
            logger.error("获取sql失败",e);
            sdp.setSqlContent("获取SQL失败:" + e.getMessage());
        }
    }

    public void reset() throws ServiceException {
        sta.getMgr().reset();
    }

}
