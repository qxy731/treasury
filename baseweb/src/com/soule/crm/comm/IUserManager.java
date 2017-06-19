package com.soule.crm.comm;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.soule.base.po.EnumItem;

/*import com.neusoft.base.po.EnumItem;
import com.neusoft.base.service.ILogonUserInfo;*/

public interface IUserManager {

    public abstract String getStaffId();

    public abstract String getUnitId();

    public abstract String getDataScope();

    public abstract String getStaffName();

    public abstract String getUnitName();

   // public abstract ILogonUserInfo getLogonUserInfo();

    public abstract String getCurrRole();

    /**
     * 获取当前数据日期
     * 
     * @return
     */
    public abstract Date getCurrDate();

    /**
     * 获取导出excel最大记录数
     * 
     * @return
     */
    public abstract int getExportCount();

    public abstract String getCustNameByCustNo(String custNo, String custType);

    public abstract String getRoleStr();

    /**
     * 是否客户经理
     * 
     * @return
     */
    public abstract boolean isCustMgr();

    /**
     * 是否管理者
     * 
     * @return
     */
    public abstract boolean isCustMgrAdmin();

    /**
     * 判断当前用户是否拥有此角色
     * 
     * @param roleId
     * @return
     */
    public abstract boolean isFunction(String roleId);

    public abstract Object getParamValue(String code);

    public abstract Object getParamValueByEnumKey(String key, String code);

    public abstract List<EnumItem> getParamListByEnumKey(String key);

    /**
     * 获取员工所属分行机构
     * 
     * @param unitId
     * @return
     */
    public abstract String getStaffBranchOrg(String unitId);

    public abstract List<String> getStaffByCust(String custNo);

    public abstract List<String> getRemindStaff(String unitId, String roleId);

    public abstract List<String> getRemindStaff(String roleId);

    public abstract boolean isTotalBank(String roleId);

    public abstract Boolean isHaveCustChange(String custNo);

    public abstract Boolean hasManagerInfo(String custNo);

    /**
     * 获取币种枚举值
     * 
     * @return
     */
    public abstract HashMap<String, String> getCurrValue();

    public abstract String getOrglst(String unitlst);

    /**
     * 获取范围大的机构名称
     * 
     * @param unitlist
     * @return
     */
    public abstract String[] getFirstOrgInfo(String unitlist);

    public abstract String getFirstOrg(String unitlst);

    /**
     * 获取最新PA日期
     * 
     * @return
     */
    public abstract Date getPaDate();

    public abstract String getBelongUnitId();
    
    public abstract String getBelongUnitName();

    public abstract String getAuditId();

    public abstract Object isManager();
}