package com.soule.crm.pub.quickmenu;

import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONArray;

import com.soule.MsgConstants;
import com.soule.app.sys.mainpage.IMainService;
import com.soule.base.action.BaseAction;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ILogonUserInfo;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.ZTreeUtil;

/**
 * 用户菜单使用次数
 */
@Namespace("/sys")
public class QuickMenuAction extends BaseAction {
    private static final long serialVersionUID = 2497770263781014790L;
    private String requestPath;
    private String returnStr;
    private String menus;

    private String resultStr = "{}";
    @Autowired
    private IMainService mainService;
    @Autowired
    private IDefaultService sDefault ;

    public void init() {
        List listTree = null;
        try {
            listTree = mainService.getSysTreeModel(AppUtils.getLogonUserInfo());
        } catch (DbAccessException e) {
            e.printStackTrace();
        }
        List viewTree = ZTreeUtil.convertMenuZtree(listTree);
        this.returnStr = JSONArray.fromObject(viewTree).toString();
        query();
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

            List list = (List) sDefault.getIbatisMediator().find("sys.quickmenu.queryQucikMenu", map);

            this.rows = list;

            this.setRetCode(MsgConstants.I0000);
            this.setRetMsg("sss");
        } catch (Exception e) {
            handleError(e);
        }
        return JSON;
    }

    public String save() {
        try {
            ILogonUserInfo logonVO = AppUtils.getLogonUserInfo();
            String userId = logonVO.getLogonInfo().getStaffId();
            String unitId = logonVO.getOperUnitId();
            String roleId = logonVO.getRoleId();
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("STAFF_ID", userId);
            map.put("UNIT_ID", unitId);
            if (roleId == null || roleId.length() == 0) {
                roleId = "*";
            }
            map.put("ROLE_ID", roleId);

            JSONArray arr = JSONArray.fromObject(returnStr);
            List li = JSONArray.toList(arr);

            for (int i = 0; i < li.size(); i++) {
                MorphDynaBean menu = (MorphDynaBean) li.get(i);
                map.put("MENU_ID", (String) menu.get("MENU_ID"));
                map.put("SEQ", menu.get("SEQ") + "");

                String isSaved = (String) menu.get("SAVED");
                String status = "";
                try {
                    status = (String) menu.get("__status");
                } catch (MorphException e) {
                    status = "none";
                }
                if (status.equals("add")) {
                    sDefault.getIbatisMediator().save("sys.quickmenu.saveQucikMenu", map);
                } else if ("1".equals(isSaved) && status.equals("delete")) {
                    sDefault.getIbatisMediator().delete("sys.quickmenu.deleteQucikMenu", map);
                } else if (status.equals("update")) {
                    sDefault.getIbatisMediator().update("sys.quickmenu.updQucikMenu", map);
                }
            }

            this.setRetCode(MsgConstants.I0000);
            this.setRetMsg("操作成功");
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

    public String getMenus() {
        return menus;
    }

    public void setMenus(String menus) {
        this.menus = menus;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public String getResultStr() {
        return resultStr;
    }

    public void setResultStr(String resultStr) {
        this.resultStr = resultStr;
    }
}
