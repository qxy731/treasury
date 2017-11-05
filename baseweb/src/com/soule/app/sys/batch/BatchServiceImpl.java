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
import com.soule.comm.tools.ObjectUtil;

/**
 * 批处理配置业务操作
 */
@Service
public class BatchServiceImpl implements IBatchService {

    private final static Log logger = LogFactory.getLog(BatchServiceImpl.class);
    @Autowired
    private IDefaultService sDefault;

    /**
     * 步骤查询
     */
    public BatchQueryMainOut queryMain(BatchQueryMainIn in) throws ServiceException {
        BatchQueryMainOut out = new BatchQueryMainOut();
        List<BatchStepPo> poLists;
        try {
            if (in != null) {
                poLists = sDefault.getIbatisMediator().find("batch.queryMainStep", in.getStep());
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

    /**
     * 步骤查询，包括主步骤、子步骤
     */
    public BatchQueryStepOut queryStep(BatchQueryStepIn in) throws ServiceException {
        BatchQueryStepOut out = new BatchQueryStepOut();
        try {
            if (!ObjectUtil.isEmpty(in)) {
                String ibatisName = "batch.qureyMainStepById";
                BatchStepPo po = (BatchStepPo) sDefault.getIbatisMediator().findById(ibatisName, in);
                out.setOne(po);
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


    /**
     * 批处理类别查询
     */
    public BatchQueryIdOut queryId(BatchQueryIdIn in) throws ServiceException {
        BatchQueryIdOut out = new BatchQueryIdOut();
        try {
            if (!ObjectUtil.isEmpty(in)) {
                List temp = sDefault.getIbatisMediator().find("batch.queryAllBatchId", in);
                out.setStepId(temp);
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

    /**
     * 修改步骤信息，包括主步骤、子步骤
     */
    public BatchUpdateOut update(BatchUpdateIn in) throws ServiceException {
        BatchUpdateOut out = new BatchUpdateOut();
        try {
            if (in != null && in.getStep() != null) {
                String ibatisName = "batch.updateMainPo";
                Integer flag = sDefault.getIbatisMediator().update(ibatisName, in.getStep());
                if (flag > 0) {
                    AppUtils.setResult(out, MsgConstants.I0003);
                } else {
                    AppUtils.setResult(out, MsgConstants.W0000,"没有一条记录被删除");
                }
            } else {
                AppUtils.setResult(out, MsgConstants.E0002);
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

    private String[][] initparams = new String[][]{
            {"9001","启停标志","Y"},
            {"9002","批处理重跑标志","0"},
            {"9015","月批量日","1"},
            {"9021","当前启动业务日期","2013-08-13"},
            {"9022","下次批处理启动日期","2013-08-14"},
    };
    /**
     * 新增步骤
     */
    public BatchInsertOut insert(BatchInsertIn in) throws ServiceException {
        BatchInsertOut out = new BatchInsertOut();
        Integer flag = 0;
        try {
            if (in != null) {
                String ibatisName = "batch.insertMainStep";
                flag = sDefault.getIbatisMediator().save(ibatisName, in.getStep());
                if (flag > 0) {
                    if ( in.getStep().getParentId() == 0 ) {//是根节点
                        for (int i = 0 ; i < initparams.length ;i++) {
                            BatParamsPo param = new BatParamsPo();
                            param.setBatchId(in.getStep().getBatchId());
                            param.setParaId(initparams[i][0]);
                            param.setParaName(initparams[i][1]);
                            param.setParaValue(initparams[i][2]);
                            sDefault.getIbatisMediator().save("batch.insertBatParam",param);
                        }
                    }
                    AppUtils.setResult(out, MsgConstants.I0001);
                } else {
                    AppUtils.setResult(out, MsgConstants.W0000);
                }
            } else {
                AppUtils.setResult(out, MsgConstants.E0002);
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

    /**
     * 批量删除步骤，包括主步骤、子步骤
     */
    public BatchDeleteOut delete(BatchDeleteIn in) throws ServiceException {
        BatchDeleteOut out = new BatchDeleteOut();
        try {
            Integer flag = 0;
            if (in != null && in.getDeletes() != null) {
                for (int i = 0; i < in.getDeletes().size(); i++) {
                    BatchStepPo po = in.getDeletes().get(i);
                    String ibatisName = "batch.deleteMainStep";
                    sDefault.getIbatisMediator().delete(ibatisName, po);
                    flag++;
                }
            }
            if (flag == in.getDeletes().size()) {
                AppUtils.setResult(out, MsgConstants.I0002);
            } else {
                AppUtils.setResult(out, MsgConstants.E0002);
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
    public Integer queryMaxStepId(String batchId) {
        try {
            Integer ret = (Integer) sDefault.getIbatisMediator().findById("batch.queryMaxStepId", batchId);
            return ret;
        } catch (DbAccessException e) {
            logger.error("DB", e);
            return 0;
        }
    }

}
