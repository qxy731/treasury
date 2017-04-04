package com.soule.app.sys.orgchange;

import com.soule.app.sys.staff.StaffStaffPo;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.ServiceException;

/**
 * 员工机构变更业务操作
 */
public interface IOrgchangeService {

    /**
     * 根据条件查询员工机构变更。
     */
    public OrgchangeQueryOut query(OrgchangeQueryIn in) throws ServiceException;
    /**
     * 新增。
     */
    
    public int insert(StaffStaffPo staff) throws ServiceException,DbAccessException;
    public int delete(StaffStaffPo staff) throws ServiceException, DbAccessException;
    public int recordStfOrgChange(StaffStaffPo staff) throws ServiceException, DbAccessException;
}
