package com.soule.app.sys.batch;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;

/**
 * 批处理监控业务操作
 */
@Service
public class BatchMonitorServiceImpl implements IBatchMonitorService {

    private final static Log logger = LogFactory.getLog(BatchMonitorServiceImpl.class);
    @Autowired
    private IDefaultService sDefault;

    @Override
    public BatchQueryMonitorOut queryMonitor(BatchQueryMonitorIn in) throws ServiceException {
        BatchQueryMonitorOut out = new BatchQueryMonitorOut();
        List<BatchMonitorPo> poLists;
        try {
            if (in != null) {
                poLists = sDefault.getIbatisMediator().find("batch.queryMonitor", in.getStep());
                out.setSteps(poLists);
                AppUtils.setResult(out, MsgConstants.I0000);
            } else {
                AppUtils.setResult(out, MsgConstants.E0001);
            }
        } catch (DbAccessException e) {
            logger.error("DB", e);
            AppUtils.setResult(out, MsgConstants.E0002);
        } catch (Exception e) {
            logger.error("SERVICE", e);
            AppUtils.setResult(out, MsgConstants.E0000);
        }
        return out;
    }

    @Override
    public BatchQueryMonitorOut queryStatus(BatchQueryMonitorIn in) throws ServiceException {
        BatchQueryMonitorOut out = new BatchQueryMonitorOut();
        List<BatchMonitorPo> poLists;
        try {
            if (in != null) {
                poLists = sDefault.getIbatisMediator().find("batch.queryStatus", in.getStep());
                out.setSteps(poLists);
                AppUtils.setResult(out, MsgConstants.I0000);
            } else {
                AppUtils.setResult(out, MsgConstants.E0000);
            }
        } catch (DbAccessException e) {
            logger.error("DB", e);
            AppUtils.setResult(out, MsgConstants.E0002);
        } catch (Exception e) {
            logger.error("SERVICE", e);
            AppUtils.setResult(out, MsgConstants.E0000);
        }
        return out;
    }

    @Override
    public List queryInstIds(String param) {
        List<BatchMonitorPo> poLists;
        try {
             poLists = sDefault.getIbatisMediator().find("batch.queryInstIds",param);
        } catch (Exception e) {
            logger.error("SERVICE", e);
            poLists = new ArrayList<BatchMonitorPo>();
        }
        return poLists;
    }

}
