package com.soule.app.sys.bizline.blass;

import com.soule.base.service.ServiceException;

/**
 * 业务线人员分配业务操作
 */
public interface IBlassService {

    /**
     * 查询业务线中的人员
     */
    public BlassQueryOut query(BlassQueryIn in) throws ServiceException;
    /**
     * 新增业务线中的人员
     */
    public BlassInsertOut insert(BlassInsertIn in) throws ServiceException;
    /**
     * 删除业务线中的人员
     */
    public BlassDeleteOut delete(BlassDeleteIn in) throws ServiceException;
    /**
     * 删除业务线中全部人员
     */
    public BlassDeleteAllOut deleteAll(BlassDeleteAllIn in) throws ServiceException;
    /**
     * 业务线中添加查询结果中全部人员
     */
    public BlassInsertAllOut insertAll(BlassInsertAllIn in) throws ServiceException;
    /**
     * 查询人员的业务线
     */
    public BlassQueryByStaffOut queryByStaff(BlassQueryByStaffIn in) throws ServiceException;

}
