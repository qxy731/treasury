package com.soule.crm.pfm.param.indexdata;

import com.soule.base.service.ServiceException;

/**
 * 指标数据补录 业务操作接口
 */
public interface IIndexDataService {


    /**
     * 指标数据补录信息的查询
     */
    public IndexDataQueryOut query(IndexDataQueryIn in) throws ServiceException;
    /**
     * 解析指标数据补录文件并更新表数据
     */
    public IndexDataReadXlsFileOut readXlsFile(IndexDataReadXlsFileIn in) throws ServiceException;
    
    public IndexDataQueryOut exportTemplate(IndexDataQueryIn in) throws ServiceException;
            


}
