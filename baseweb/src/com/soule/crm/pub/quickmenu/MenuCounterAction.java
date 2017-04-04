package com.soule.crm.pub.quickmenu;

import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.MsgConstants;
import com.soule.base.action.BaseAction;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ILogonUserInfo;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.StringUtil;

/**
 * 用户菜单使用次数
 */
@Namespace("/sys")
public class MenuCounterAction extends BaseAction {
    private static final long serialVersionUID = 2497770263781014790L;
    private String requestPath;
    private String returnStr;
    @Autowired
    private IDefaultService sDefault ;

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public String count() {
        String in = requestPath;
        try {
            ILogonUserInfo logonVO = AppUtils.getLogonUserInfo();
            String userId = logonVO.getLogonInfo().getStaffId();
            String unitId = logonVO.getOperUnitId();
            String roleId = logonVO.getRoleId();

            List l = sDefault.getIbatisMediator().find("sys.quickmenu.getMenuNodeIdByUrl", in);
            if (l.size() == 0)
                return null;
            HashMap<String, String> menuMap = (HashMap<String, String>) l.get(0);
            String nodeId = menuMap.get("NODE_ID");

            if (StringUtil.isEmpty(nodeId))
                return null;

            HashMap<String, String> map = new HashMap<String, String>();
            map.put("STAFF_ID", userId);
            map.put("UNIT_ID", unitId);
            map.put("ROLE_ID", roleId);
            map.put("MENU_ID", nodeId);

            // 查询count
            Long x = (Long) sDefault.getIbatisMediator().findById("sys.quickmenu.getMenuClickedCount", map);

            if (x == 0) {// insert
                sDefault.getIbatisMediator().save("sys.quickmenu.saveMenuClickedCount", map);
            } else {// update
                sDefault.getIbatisMediator().update("sys.quickmenu.updMenuClickedCount", map);
            }

        } catch (Exception e) {
            handleError(e);
        }
        return JSON;
    }

    public String query() {
        try {
            ILogonUserInfo logonVO = AppUtils.getLogonUserInfo();
            String userId = logonVO.getLogonInfo().getStaffId();
            String unitId = logonVO.getOperUnitId();
            String roleId = logonVO.getRoleId();
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("STAFF_ID", userId);
            map.put("UNIT_ID", unitId);
            map.put("ROLE_ID", roleId);

            String mappedSql = "sys.quickmenu.queryUserMenuCount";

            long x = sDefault.getIbatisMediator().getRecordTotal(mappedSql, map);
            int pageOffset = (page - 1) * pagesize;

            List list = null;
            if (pageOffset < x) {
                list = sDefault.getIbatisMediator().find(mappedSql, map, pageOffset, pagesize);
            }

            this.rows = list;
            this.setTotal(x);

            this.setRetCode(MsgConstants.I0000);
            this.setRetMsg("sss");
        } catch (Exception e) {
            handleError(e);
        }
        return JSON;
    }

    public String getReturnStr() {
        return returnStr;
    }

    public void setReturnStr(String returnStr) {
        this.returnStr = returnStr;
    }
}
