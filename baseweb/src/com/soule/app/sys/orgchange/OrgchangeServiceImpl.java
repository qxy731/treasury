package com.soule.app.sys.orgchange;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.app.sys.staff.StaffStaffPo;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.ObjectUtil;
/**
 * 员工机构变更业务操作
 */
@Service
public class OrgchangeServiceImpl implements IOrgchangeService {

    private final static Log logger = LogFactory.getLog(OrgchangeServiceImpl.class);
    // 数据库通用操作
    @Autowired
    private IDefaultService sDefault;

    /**
     * 根据条件查询员工机构变更。
     */
    @SuppressWarnings("unchecked")
	public OrgchangeQueryOut query(OrgchangeQueryIn in)
        throws ServiceException {
        OrgchangeQueryOut out = new OrgchangeQueryOut();
        // TODO 
        try {
            List totalnum = sDefault.getIbatisMediator().find("stfOrgChange.getSysStaffOrgChangeHisCount", in.getOrgChange());
            long x = 0L;
            if (!(ObjectUtil.isEmpty(totalnum))) {
                x = ((Long) totalnum.get(0)).longValue();
            }
            int pageSize = in.getInputHead().getPageSize();
            int pageOffset = (in.getInputHead().getPageNo() - 1) * pageSize;
            if (pageOffset < x) {
                List<OrgchangeOrgChangePo> output = sDefault.getIbatisMediator().find("stfOrgChange.getSysStaffOrgChangeHis",
                        in.getOrgChange(), pageOffset, pageSize);
                out.setOrgChange(output);
            }
            out.getResultHead().setTotal(x);
        } catch (DbAccessException e) {
            logger.error("DB", e);
            AppUtils.setResult(out, MsgConstants.E0002, e.getMessage());
        } catch (Exception e) {
            logger.error("SERVICE", e);
            e.printStackTrace();
            AppUtils.setResult(out, MsgConstants.E0000);
        }
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }
    
    public int insert(StaffStaffPo staff) throws ServiceException, DbAccessException {
        OrgchangeOrgChangePo orgChange=new OrgchangeOrgChangePo();
        String updUser = AppUtils.getLogonUserInfo().getUser().getUserID();
        if(staff==null){
            return 0;
        }
      //新增一条机构变更记录，end_time为null.
        orgChange.setStaffId(staff.getStaffId());
        orgChange.setOwnerUnitid(staff.getOwnerUnitid());
        orgChange.setUpdUser(updUser);
        orgChange.setStartTime(new Date());
        int c = sDefault.getIbatisMediator().save("stfOrgChange.saveSysStaffOrgChangeHis", orgChange);
        return c;
    }
    public int delete(StaffStaffPo staff) throws ServiceException, DbAccessException {
        String updUser = AppUtils.getLogonUserInfo().getUser().getUserID();
        if(staff==null){
            return 0;
        }
        //更新该员工前一条机构变更记录的结束时间，更新sql语句的条件为：staff_id 为该员工的staffId,end_time为null.
        OrgchangeOrgChangePo preOrgChange=new OrgchangeOrgChangePo();
        preOrgChange.setEndTime(new Date());
        preOrgChange.setStaffId(staff.getStaffId());        
        int c = sDefault.getIbatisMediator().update("stfOrgChange.updPreStaffOrgChange", preOrgChange);
        return c;
    }
    public int recordStfOrgChange(StaffStaffPo staff) throws ServiceException, DbAccessException {
        String updUser = AppUtils.getLogonUserInfo().getUser().getUserID();
        if(staff==null){
            return 0;
        }
        //更新该员工前一条机构变更记录的结束时间，更新sql语句的条件为：staff_id 为该员工的staffId,end_time为null.
        OrgchangeOrgChangePo preOrgChange=new OrgchangeOrgChangePo();
        preOrgChange.setEndTime(new Date());
        preOrgChange.setStaffId(staff.getStaffId());        
        int c = sDefault.getIbatisMediator().update("stfOrgChange.updPreStaffOrgChange", preOrgChange);
        
        //新增一条机构变更记录，end_time为null.
        OrgchangeOrgChangePo newOrgChange=new OrgchangeOrgChangePo();
        newOrgChange.setStaffId(staff.getStaffId());
        newOrgChange.setOwnerUnitid(staff.getOwnerUnitid());
        newOrgChange.setUpdUser(updUser);
        newOrgChange.setStartTime(new Date());
        int c1 = sDefault.getIbatisMediator().save("stfOrgChange.saveSysStaffOrgChangeHis", newOrgChange);
        return c;
    }
    
}
