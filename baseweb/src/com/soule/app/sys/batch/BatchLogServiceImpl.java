package com.soule.app.sys.batch;

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

@Service
public class BatchLogServiceImpl implements IBatchLogService {
    private final static Log logger = LogFactory.getLog(BatchLogServiceImpl.class);
    @Autowired
    private IDefaultService sDefault;
    /**
     * 日志查询
     */
    public BatchLogOut querylog(BatchLogIn in) throws ServiceException {
        BatchLogOut out = new BatchLogOut();
       
        try {
            long total = sDefault.getIbatisMediator().getRecordTotal("batch.queryLog", in);
            if (total > 0 ) {
                int offset = (in.getInputHead().getPageNo() -1 )*in.getInputHead().getPageSize();
                int pagesize = in.getInputHead().getPageSize();
                List list = sDefault.getIbatisMediator().find("batch.queryLog", in,offset,pagesize);
                out.setBatchLogs(list);
            }
            out.getResultHead().setTotal(total);
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002,e.getMessage());
        }
        return out;
    }
}
