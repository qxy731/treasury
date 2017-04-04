package com.soule.app.sys.role;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ILogonUserInfo;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.ContextUtil;
import com.soule.comm.tools.ObjectUtil;
/**
 * 角色业务操作
 */
@Service
public class RoleServiceImpl implements IRoleService {
    private final static Log logger = LogFactory.getLog(RoleServiceImpl.class);
    private String SYS_ROLE_COUNT = "role.getSysRoleCount";
    private String SYS_ROLE_LIST = "role.getSysRole";
    private String SYS_ROLE_DELETE = "role.delSysRole";
    private String SYS_ROLE_INSERT = "role.saveSysRole";
    @Autowired
    IDefaultService defService;
    /**
     * 角色的查询
     */
    public RoleQueryOut query(RoleQueryIn in)
        throws ServiceException {
        RoleQueryOut out = new RoleQueryOut();
        try {
            HashMap<String,String> condition = new HashMap<String,String>(8);
            condition.put("roleId", in.getRoleId());
            condition.put("roleName", in.getRoleName());
            condition.put("roleStatus", in.getRoleStatus());
            condition.put("createUser", in.getCreateUser());
            List<Long> totalnum = defService.getIbatisMediator().find(SYS_ROLE_COUNT, condition);
            long total = 0;
            if (!ObjectUtil.isEmpty(totalnum) ) {
                total = totalnum.get(0).longValue();
            }
            int pagesize = in.getInputHead().getPageSize();
            if ( pagesize < 0) {
                pagesize = 10;
            }
            int pageno =  in.getInputHead().getPageNo();
            int start = (pageno - 1) * pagesize;
            if (total > start) {
                List ret = defService.getIbatisMediator().find(SYS_ROLE_LIST, condition,start,pagesize);
                out.setRole(ret);
            }
            out.getResultHead().setTotal(total);
            AppUtils.setResult(out,MsgConstants.I0000);
        } catch(DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out,MsgConstants.E0002);
        } catch(Exception e) {
            logger.error("SERVICE",e);
            AppUtils.setResult(out,MsgConstants.E0000);
        }
        return out;
    }
    /**
     * 修改角色信息
     */
    public RoleUpdateOut update(RoleUpdateIn in)
        throws ServiceException {
        RoleUpdateOut out = new RoleUpdateOut();
        try {
            RoleRolePo po = (RoleRolePo) defService.getIbatisMediator().findById("role.getSysRoleByKey", in.getModifyRole());
            if (po == null){
                AppUtils.setResult(out, MsgConstants.E0007);
            }
            defService.getIbatisMediator().update("role.updSysRole", in.getModifyRole());
            AppUtils.setResult(out,MsgConstants.I0000);
        } catch(DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out,MsgConstants.E0002);
        } catch(Exception e) {
            logger.error("SERVICE",e);
            AppUtils.setResult(out,MsgConstants.E0000);
        }
        return out;
    }
    /**
     * 角色的新增
     * @throws DbAccessException 
     */
    public RoleInsertOut insert(RoleInsertIn in) throws ServiceException, DbAccessException {
        RoleInsertOut out = new RoleInsertOut();
        RoleRolePo po = in.getNewRole();
        if (po != null) {
            ILogonUserInfo userInfo = AppUtils.getLogonUserInfo();
            po.setCreateUser(userInfo.getUsername());
            po.setCreateTime(new Date());
            int i = defService.getIbatisMediator().save(SYS_ROLE_INSERT, in.getNewRole());
            if (i == 1) {
                AppUtils.setResult(out, MsgConstants.I0001,i);
            }
        }
        return out;
    }
    /**
     * 批量删除角色
     */
    public RoleDeleteOut delete(RoleDeleteIn in) throws ServiceException {
        RoleDeleteOut out = new RoleDeleteOut();
        if (ObjectUtil.isEmpty(in.getDeletes())) {
            AppUtils.setResult(out,MsgConstants.E0001);
            return out;
        }
        int count =0;
        for (RoleRolePo po: in.getDeletes()) {
            int ret = 0;
            try {
                ret = defService.getIbatisMediator().delete(SYS_ROLE_DELETE, po);
            } catch (DbAccessException e) {
                logger.error("DB",e);
            }
            if (ret ==1) {
                count ++;
            }
        }
        if ( count == 0 ){
            AppUtils.setResult(out,MsgConstants.W0000,"没有一条记录被删除");
        }
        else {
            AppUtils.setResult(out, MsgConstants.I0002,count);
        }
        return out;
    }
}
