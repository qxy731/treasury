package com.soule.app.sys.bizline.bltype;

import com.soule.base.service.ServiceException;

/**
 * 业务线类型维护业务操作
 */
public interface IBltypeService {

    /**
     * 查询系统中定义的业务线类型
     */
    public BltypeQueryOut query(BltypeQueryIn in) throws ServiceException;
    /**
     * 修改业务线类型信息
     */
    public BltypeModifyOut modify(BltypeModifyIn in) throws ServiceException;
    /**
     * 删除业务线类型信息，包括业务线类型值和业务关系表中的数据
     */
    public BltypeDeleteOut delete(BltypeDeleteIn in) throws ServiceException;
    /**
     * 删除业务线类型信息前，判断该业务线是否包含括业务线类型值和业务关系表中的数据
     */
    public BltypeValidDataOut validData(BltypeValidDataIn in) throws ServiceException;
    /**
     * 新增业务线类型
     */
    public BltypeInsertOut insert(BltypeInsertIn in) throws ServiceException;


}
