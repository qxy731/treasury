package com.soule.app.sys.staff;

import java.util.List;

import com.soule.base.media.DbAccessException;
import com.soule.base.service.ServiceException;

/**
 * 人员业务操作
 */
public interface IStaffService {

    /**
     * 人员的查询
     */
    public StaffQueryOut query(StaffQueryIn in) throws ServiceException;
    /**
     * 修改人员信息
     */
    public StaffUpdateOut update(StaffUpdateIn in) throws ServiceException,DbAccessException;
    /**
     * 人员的新增
     */
    public StaffInsertOut insert(StaffInsertIn in) throws ServiceException,DbAccessException;
   /**
     * 批量删除人员
     */
    public StaffDeleteOut delete(StaffDeleteIn in) throws ServiceException;
    public List<StaffStaffPo> queryByUnit(String unitId) throws ServiceException;
    public StaffUnlockOut unlock(StaffUnlockIn in) throws ServiceException;
}
