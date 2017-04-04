package com.soule.app.sys.mainpage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;


import com.soule.app.sys.menu.MenuPo;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ILogonUserInfo;
import com.soule.base.service.auth.AdminAuthority;
import com.soule.base.service.auth.ResourceAuthority;
import com.soule.comm.enu.ResourceType;
import com.soule.comm.tools.ContextUtil;

/**
 * @author wuwei
 */
@Service
public class MainServiceImpl implements IMainService {

    private String SYS_MENU_IBATIS = "web.getSysMenusByMenuId";
    private String SYS_MENU_ID = "sys";
    @Autowired
    private IDefaultService defService;

    @SuppressWarnings("unchecked")
	public List<MenuPo> getSysTreeModel(ILogonUserInfo logonUser) throws DbAccessException {
        List<MenuPo> menuItems = defService.getIbatisMediator().find(SYS_MENU_IBATIS, SYS_MENU_ID);
        ArrayList<MenuPo> selected = new ArrayList<MenuPo>();
        for (MenuPo po : menuItems) {
            if (voteMenu(logonUser.getAuthorities(),po)) {
                selected.add(po);
            }
        }
        return selected;
    }

    protected boolean voteMenu(Collection<GrantedAuthority> collection, MenuPo po) {
        for (GrantedAuthority ga : collection) {
            if (ga instanceof AdminAuthority) {
                return true;
            }
            if (!(ga instanceof ResourceAuthority)) {
                continue;
            }
            ResourceAuthority resAuth = (ResourceAuthority) ga;
            if (resAuth.getRunFlag() && resAuth.getResType() == ResourceType.MENU) {
                String auth = (String) resAuth.getAuthority();
                if (auth.equals(po.getMenuId()+ '_' + po.getNodeId())) {
                    return true;
                }
            }
        }
        return false;
    }

    /*public List<MenuPo> getMenus(String part) {
        // TODO Auto-generated method stub
        return null;
    }*/

}
