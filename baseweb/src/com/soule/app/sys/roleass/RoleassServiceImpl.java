package com.soule.app.sys.roleass;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.app.table.SysPosiAssignPo;
import com.soule.app.table.SysPositionPo;
import com.soule.app.table.SysRoleAssignPo;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ILogonUserInfo;
import com.soule.base.service.ServiceException;
import com.soule.base.service.keygen.IKeyGenerator;
import com.soule.comm.enu.YesNoFlag;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.ObjectUtil;
import com.soule.comm.tools.StringUtil;

/**
 * 角色分配业务操作
 */
@Service
public class RoleassServiceImpl implements IRoleassService {

    private final static Log logger = LogFactory.getLog(RoleassServiceImpl.class);
    private static final String T_SYS_POSITION = "SysPosition";
    private static final String T_SYS_POSI_ASSIGN = "SysPosiAssign";
    private String NAME_SPACE = "roleass";
    private String IBATIS_GET_ROLEASS = NAME_SPACE + ".getSysRoleAssign";
    private String IBATIS_GET_ROLEASS_COUNT = NAME_SPACE + ".getSysRoleAssignCount";
    private String IBATIS_GET_STAFF = NAME_SPACE + ".getSysStaff";
    private String IBATIS_GET_STAFF_COUNT = NAME_SPACE + ".getSysStaffCount";
    private String IBATIS_DEL_POSI = NAME_SPACE + ".delPosiWithoutAss";
    private String tableName ="SysRoleAssign";
    @Autowired
    private IKeyGenerator keyGen;
    @Autowired
    private IDefaultService sDefault;
    /**
     * 根据角色查询当前机构下的人,有翻页
     */
    public RoleassQueryByRoleOut queryByRole(RoleassQueryByRoleIn in) throws ServiceException {
        RoleassQueryByRoleOut out = new RoleassQueryByRoleOut();
        if (in == null || StringUtil.isEmpty(in.getRoleId())) {
            AppUtils.setResult(out, MsgConstants.E0001,"角色ID");
            return out;
        }
        int pageNo = in.getInputHead().getPageNo();
        int pageSize = in.getInputHead().getPageSize();
        int offset = (pageNo - 1) * pageSize;
        try {
            Long cl = (Long) sDefault.getIbatisMediator().findById(IBATIS_GET_ROLEASS_COUNT, in);
            long c = cl.longValue();
            if (c > 0) {
                List<RoleassRoleStaffPo> list = sDefault.getIbatisMediator().find(IBATIS_GET_ROLEASS,
                        in, offset, pageSize);
                out.getResultHead().setTotal(c);
                out.setRoleStaff(list);
            }
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (DbAccessException e) {
            logger.error("DB", e);
            AppUtils.setResult(out, MsgConstants.E0002,e.getMessage());
        }
        return out;
    }

    /**
     * 根据基本条件查询当前机构下的人，有翻页
     */
    public RoleassQueryStaffOut queryStaff(RoleassQueryStaffIn in) throws ServiceException {
        RoleassQueryStaffOut out = new RoleassQueryStaffOut();
        if (in == null ) {
            AppUtils.setResult(out, MsgConstants.E0001,"基本条件");
            return out;
        }
        int pageNo = in.getInputHead().getPageNo();
        int pageSize = in.getInputHead().getPageSize();
        int offset = (pageNo - 1) * pageSize;
        try {
            Long cl = (Long) sDefault.getIbatisMediator().findById(IBATIS_GET_STAFF_COUNT, in);
            long c = cl.longValue();
            if (c > 0) {
                List<RoleassStaffPo> list = sDefault.getIbatisMediator().find(IBATIS_GET_STAFF,
                        in, offset, pageSize);
                out.getResultHead().setTotal(c);
                out.setStaff(list);
            }
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (DbAccessException e) {
            logger.error("DB", e);
            AppUtils.setResult(out, MsgConstants.E0002,e.getMessage());
        }
        return out;
    }

    /**
     * 删除当前角色下的人
     */
    public RoleassDeleteOut delete(RoleassDeleteIn in) throws ServiceException {
        RoleassDeleteOut out = new RoleassDeleteOut();
        if (in == null) {
            AppUtils.setResult(out, MsgConstants.E0001,"RoleassDeleteIn");
            return out;
        }
        if (ObjectUtil.isEmpty(in.getDeletes())) {
            AppUtils.setResult(out, MsgConstants.E0001,"deletes");
            return out;
        }
        int c = 0;
        for (RoleassRoleStaffPo po : in.getDeletes()) {
            SysRoleAssignPo srap = new SysRoleAssignPo();
            srap.setRoleId(po.getRoleId());
            srap.setStaffId(po.getStaffId());
            Boolean ret = sDefault.deleteReocrd(tableName, srap);
            if (ret != null && ret.booleanValue()) {
                c ++;
                deletePositionInfo(sDefault, po);
            }
        }
        AppUtils.setResult(out, MsgConstants.I0002, c);
        return out;
    }

    private void deletePositionInfo(IDefaultService ds, RoleassRoleStaffPo po) throws ServiceException {
        SysPosiAssignPo pk = new SysPosiAssignPo();
        pk.setPosiId(po.getPosiId());
        pk.setStaffId(po.getStaffId());
        Boolean dok = ds.deleteReocrd(T_SYS_POSI_ASSIGN, pk);
        if (dok.booleanValue()) {
            try {
                ds.getIbatisMediator().delete(IBATIS_DEL_POSI, po.getPosiId());
            } catch (DbAccessException e) {
                logger.error("DB",e);
                throw new ServiceException(MsgConstants.E0002, e.getErrorMsg());
            }
        }
    }

    /**
     * 当前角色下新增人员
     */
    public RoleassInsertOut insert(RoleassInsertIn in) throws ServiceException {
        RoleassInsertOut out = new RoleassInsertOut();
        if (in == null ) {
            AppUtils.setResult(out, MsgConstants.E0001,"RoleassInsertIn");
            return out;
        }
        ILogonUserInfo userInfo = AppUtils.getLogonUserInfo();
        int c = 0;
        if (!ObjectUtil.isEmpty(in.getInserts())) {
            for (RoleassRoleStaffPo po:in.getInserts()) {
                SysRoleAssignPo srap = new SysRoleAssignPo();
                srap.setRoleId(po.getRoleId());
                srap.setStaffId(po.getStaffId());
                SysRoleAssignPo dpo = (SysRoleAssignPo) sDefault.getRecordByKey(tableName, srap);
                if (dpo == null) {
                    srap.setCreateTime(new Date());
                    srap.setCreateUser(userInfo.getUsername());
                    srap.setDefFlag(YesNoFlag.NO.getValue());
                    srap.setLastUpdTime(srap.getCreateTime());
                    srap.setLastUpdUser(srap.getCreateUser());
                    srap.setRemark("");
                    boolean r = sDefault.saveRecord(tableName , srap);
                    if (r) {
                        c ++;
                    }
                }
                savePositionInfo(sDefault, po);
            }
        }
        AppUtils.setResult(out, MsgConstants.I0001, c);
        return out;
    }

    private void savePositionInfo(IDefaultService sDefault, RoleassRoleStaffPo po) {
        HashMap<String,Object> condition = new HashMap<String,Object> ();
        condition.put("unitId", po.getOperUnitid());
        condition.put("roleId", po.getRoleId());
        List pos = sDefault.getRecordByMap(T_SYS_POSITION, condition);
        SysPositionPo spp = null;
        if (ObjectUtil.isEmpty(pos)) {
            spp = new SysPositionPo();
            spp.setPosiId(keyGen.getUUIDKey());
            spp.setRoleId(po.getRoleId());
            spp.setUnitId(po.getOperUnitid());
            sDefault.saveRecord(T_SYS_POSITION, spp);
        }
        else {
            spp = (SysPositionPo)pos.get(0);
        }
        SysPosiAssignPo pk = new SysPosiAssignPo();
        pk.setPosiId(spp.getPosiId());
        pk.setStaffId(po.getStaffId());
        SysPosiAssignPo poInst = (SysPosiAssignPo) sDefault.getRecordByKey(T_SYS_POSI_ASSIGN, pk);
        if (poInst == null) {
            sDefault.saveRecord(T_SYS_POSI_ASSIGN, pk);
        }
    }

}
