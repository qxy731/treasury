package com.soule.app.sys.staff;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.app.sys.orgchange.IOrgchangeService;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.ContextUtil;
import com.soule.comm.tools.ObjectUtil;

/**
 * 人员业务操作
 */
@Service
public class StaffServiceImpl implements IStaffService {

    // 数据库通用操作
	@Autowired
    private IDefaultService defService;
	@Autowired
	private IOrgchangeService orgchangeService;
    private static final Log logger = LogFactory.getLog(StaffServiceImpl.class);
    private String SYS_STAFF_COUNT;
    private String SYS_STAFF_LIST;
    private String SYS_STAFF_DELETE;
    private String SYS_STAFF_INSERT;
    private String SYS_STAFF_UPDATE;
    public StaffServiceImpl() {
        this.SYS_STAFF_COUNT = "staff.getSysStaffCount";
        this.SYS_STAFF_LIST = "staff.getSysStaff";
        this.SYS_STAFF_DELETE = "staff.delSysStaff";
        this.SYS_STAFF_INSERT = "staff.saveSysStaff";
        this.SYS_STAFF_UPDATE="staff.updSysStaff";
    }

    /**
     * 人员的查询
     */
    public StaffQueryOut query(StaffQueryIn in) throws ServiceException {
        StaffQueryOut out = new StaffQueryOut();
        try {
            HashMap condition = new HashMap();
            StaffStaffPo staff=new StaffStaffPo();
            staff.setStaffId(in.getStaffId());
            staff.setStaffName(in.getStaffName());
            staff.setStaffLevel(in.getStaffLevel());
            staff.setStaffStatus(in.getStaffStatus());
            staff.setOwnerUnitid(in.getUnitId());
            condition.put("staff", staff);
            List totalnum = defService.getIbatisMediator().find(this.SYS_STAFF_COUNT, condition);
            long total = 0L;
            if (!(ObjectUtil.isEmpty(totalnum))) {
                total = ((Long) totalnum.get(0)).longValue();
            }
            int pagesize = in.getInputHead().getPageSize();
            if (pagesize < 0) {
                pagesize = 10;
            }
            int pageno = in.getInputHead().getPageNo();
            int start = (pageno - 1) * pagesize;
            if (total > start) {
                List ret = defService.getIbatisMediator().find(this.SYS_STAFF_LIST, condition, start, pagesize);
                out.setStaff(ret);
            }
            out.getResultHead().setTotal(total);
            AppUtils.setResult(out, "I0000");
        } catch (DbAccessException e) {
            logger.error("DB", e);
            AppUtils.setResult(out, "E0002");
        } catch (Exception e) {
            logger.error("SERVICE", e);
            AppUtils.setResult(out, "E0000");
        }
        return out;
    }

    /**
     * 修改人员信息
     */
    public StaffUpdateOut update(StaffUpdateIn in) throws ServiceException,DbAccessException {
        StaffUpdateOut out = new StaffUpdateOut();
        StaffStaffPo po = in.getModifyStaff();
        //从库中获取该人员信息
        StaffStaffPo oldPo = new StaffStaffPo();//此处需要修改查库
        HashMap condition = new HashMap();
        StaffStaffPo staff=new StaffStaffPo();
        staff.setStaffId(po.getStaffId());
        condition.put("staff", staff);
        if(po!=null){
            List staffList=defService.getIbatisMediator().find(this.SYS_STAFF_LIST,condition);
            if(staffList.size()>0){
                oldPo=(StaffStaffPo)staffList.get(0);
            }
        }
        if (po != null) {
            String userId = AppUtils.getLogonUserInfo().getLogonInfo().getLogonId();
            po.setLastUpdUser(userId);
            po.setLastUpdTime(new Date());
            int i = defService.getIbatisMediator().update(this.SYS_STAFF_UPDATE, in.getModifyStaff());
            if (i == 1) {
                AppUtils.setResult(out, MsgConstants.I0003,i);
                //插入历史表数据，机构变更之后置结束时间
                if(!oldPo.getOwnerUnitid().equals(po.getOwnerUnitid())){
                    orgchangeService.recordStfOrgChange(po);
                }
            }
        }
        return out;
    }

    /**
     * 人员的新增
     */
    public StaffInsertOut insert(StaffInsertIn in) throws ServiceException,DbAccessException {
        StaffInsertOut out = new StaffInsertOut();
        StaffStaffPo po = in.getNewStaff();
        if (po != null) {
            String userId = AppUtils.getLogonUserInfo().getLogonInfo().getLogonId();
            po.setCreateUser(userId);
            po.setCreateTime(new Date());
            int i = defService.getIbatisMediator().save(this.SYS_STAFF_INSERT, in.getNewStaff());
            if (i == 1) {
                orgchangeService.recordStfOrgChange(po);
                AppUtils.setResult(out, "I0001");
            }
        }
        return out;
    }

    /**
     * 批量删除人员
     */
    public StaffDeleteOut delete(StaffDeleteIn in) throws ServiceException {
        StaffDeleteOut out = new StaffDeleteOut();
        if (ObjectUtil.isEmpty(in.getDeletes())) {
            AppUtils.setResult(out, "E0001");
            return out;
        }
        int count = 0;
        for (StaffStaffPo po : in.getDeletes()) {
            int ret = 0;
            try {
                if ("admin".equals(po.getStaffId())) {
                    continue;
                }
                else {
                    defService.getIbatisMediator().delete("logonInfo.delSysLogonInfo", po);
                    ret = defService.getIbatisMediator().delete(this.SYS_STAFF_DELETE, po);
                }
            } catch (DbAccessException e) {
                logger.error("DB", e);
            }
            if (ret == 1) {
                ++count;
            }
        }
        if (count == 0) {
            AppUtils.setResult(out, "W0000", new Object[] { "没有一条记录被删除" });
        } else {
            AppUtils.setResult(out, "I0000");
        }
        return out;
    }
    
    public StaffUnlockOut unlock(StaffUnlockIn in) throws ServiceException {
    	StaffUnlockOut out = new StaffUnlockOut();
        if (ObjectUtil.isEmpty(in.getUnlocks())) {
            AppUtils.setResult(out, "E0001");
            return out;
        }
        int count = 0;
        for (StaffStaffPo po : in.getUnlocks()) {
            int ret = 0;
            try {
                if ("admin".equals(po.getStaffId())) {
                    continue;
                }else {
                    ret = defService.getIbatisMediator().update("logonInfo.updateLogonLock", po);
                }
            } catch (DbAccessException e) {
                logger.error("DB", e);
            }
            if (ret == 1) {
                ++count;
            }
        }
        if (count == 0) {
            AppUtils.setResult(out, "W0000", new Object[] { "没有一条记录被删除" });
        } else {
            AppUtils.setResult(out, "I0000");
        }
        return out;
    }

    @SuppressWarnings("unchecked")
    public List<StaffStaffPo> queryByUnit(String unitId) throws ServiceException {
        List<StaffStaffPo> reultList = null;
        HashMap<String, Object> condition = new HashMap<String, Object>();
        StaffStaffPo staff = new StaffStaffPo();
        staff.setOwnerUnitid(unitId);
        condition.put("staff", staff);
        try {
            reultList = (List<StaffStaffPo>) defService.getIbatisMediator().find(this.SYS_STAFF_LIST, condition);
        } catch (DbAccessException e) {
            e.printStackTrace();
        }
        return reultList;
    }

}