package com.soule.app.sys.batch;

import com.soule.base.service.ServiceException;

/**
 * 批处理配置业务操作
 */
public interface IBatchService {

    /**
     * 步骤查询
     */
    public BatchQueryMainOut queryMain(BatchQueryMainIn in) throws ServiceException;
    /**
     * 步骤查询
     */
    public BatchQueryStepOut queryStep(BatchQueryStepIn in) throws ServiceException;
    /**
     * 批处理类别查询
     */
    public BatchQueryIdOut queryId(BatchQueryIdIn in) throws ServiceException;
    /**
     * 修改步骤信息
     */
    public BatchUpdateOut update(BatchUpdateIn in) throws ServiceException;
    /**
     * 新增步骤
     */
    public BatchInsertOut insert(BatchInsertIn in) throws ServiceException;
    /**
     * 批量删除步骤
     */
    public BatchDeleteOut delete(BatchDeleteIn in) throws ServiceException;
    /**
     * 查询最多步骤号
     */
    public Integer queryMaxStepId(String batchId);

}
