package com.soule.app.sys.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.comm.tools.ContextUtil;

/**
 * @author wuwei
 */
@SuppressWarnings("unchecked")
@Service
public class MenuServiceImpl implements IMenuService {
    private String SUB_MENU_IBATIS = "sysmgr.getSubMenusByPId";
    private String MENU_IBATIS = "sysmgr.getMenuItemById";
    private String MENU_INSERT_IBATIS = "sysmgr.insertMenuModel";
    private String MENU_UPDATE_IBATIS = "sysmgr.updateMenuModel";
    private String MENU_DELETE_IBATIS = "sysmgr.deleteMenuModel";
    private String MENU_UPDATE_PARENT = "sysmgr.updateParentMenu";;
    @Autowired
    private IDefaultService defService;

    public List<MenuPo> getSysMenuRoot() throws DbAccessException {
        Map map = new HashMap();
        map.put("parentNode",null);
        map.put("menuId", "sys");
        List<MenuPo> menuItems = defService.getIbatisMediator().find(SUB_MENU_IBATIS, map);
        return menuItems;
    }

    public List<MenuPo> getCusMenusRoot() throws DbAccessException {
        Map map = new HashMap();
        map.put("parentNode",null);
        List<MenuPo> menuItems = defService.getIbatisMediator().find(SUB_MENU_IBATIS , map);
        MenuPo sysRoot = null;
        for (MenuPo po : menuItems) {
            if ("sys".equals(po.getMenuId())) {
                sysRoot = po;
                break;
            }
        }
        if (sysRoot!= null) {
            menuItems.remove(sysRoot);
        }
        return menuItems;
    }

    /**
     * 查看某菜单详情
     */
    public MenuPo getMenuItemById(String menuId, String nodeId) throws DbAccessException {
        Map map = new HashMap();
        map.put("menuId", menuId);
        map.put("nodeId", nodeId);
        MenuPo menuItem = (MenuPo) defService.getIbatisMediator().findById(MENU_IBATIS, map);
        return menuItem;
    }

    /**
     * 查找子菜单
     * 
     * @param menuId
     * @param nodeId
     * @return
     * @throws DbAccessException
     */
    public List<MenuPo> getSubMenuModel(String menuId, String parentNode) throws DbAccessException {
        Map map = new HashMap();
        map.put("menuId", menuId);
        map.put("parentNode", parentNode);
        List<MenuPo> menuItems = defService.getIbatisMediator().find(SUB_MENU_IBATIS, map);
        return menuItems;
    }

    public int deleteMenuModel(String menuId, String nodeId) throws DbAccessException {
        Map map = new HashMap();
        map.put("menuId", menuId);
        map.put("nodeId", nodeId);
        MenuPo delMenu = getMenuItemById(menuId,nodeId);
        int flag = 0;
        if (delMenu != null) {
            flag = defService.getIbatisMediator().delete(MENU_DELETE_IBATIS, map);
            if (flag==1) {
                Map paramMap = new HashMap();
                List<MenuPo> menus = getSubMenuModel(menuId,delMenu.getParentNode());
                paramMap.put("menuId",menuId);
                paramMap.put("nodeId",delMenu.getParentNode());
                paramMap.put("hasChildFlag",(menus != null && menus.size() >0)?"1":"0");
                flag = defService.getIbatisMediator().update(MENU_UPDATE_PARENT, paramMap);
            }
        }
        return flag;
    }

    /**
     * flag=1成功，flag=0失败
     * 
     * @param paramMap
     * @return
     * @throws DbAccessException
     */
    public int saveMenuModel(Map paramMap) throws DbAccessException {
        Object obj = defService.getIbatisMediator().findById(MENU_IBATIS, paramMap);
        int flag = 0;
        if (obj == null) {
            flag = defService.getIbatisMediator().update(MENU_INSERT_IBATIS, paramMap);
            if (flag==1) {
                paramMap.put("nodeId",paramMap.get("parentNode"));
                paramMap.put("hasChildFlag","1");
                flag = defService.getIbatisMediator().update(MENU_UPDATE_PARENT, paramMap);
            }
        } else {
            flag = defService.getIbatisMediator().update(MENU_UPDATE_IBATIS, paramMap);
        }
        return flag;
    }
 

}
