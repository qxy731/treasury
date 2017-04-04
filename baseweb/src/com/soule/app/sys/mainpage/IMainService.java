package com.soule.app.sys.mainpage;

import java.util.List;

import com.soule.app.sys.menu.MenuPo;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.ILogonUserInfo;

/**
 * 菜单服务
 *
 */
public interface IMainService {

    List<MenuPo> getSysTreeModel(ILogonUserInfo logonUserInfo) throws DbAccessException;

    /*List<MenuPo> getMenus(String part);*/

}
