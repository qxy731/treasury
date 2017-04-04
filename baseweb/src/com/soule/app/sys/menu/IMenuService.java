package com.soule.app.sys.menu;

import java.util.List;
import java.util.Map;

import com.soule.base.media.DbAccessException;

/**
 * 菜单服务
 *
 */
public interface IMenuService {

    /**
     * 获得首页菜单根
     */
    public List<MenuPo> getSysMenuRoot() throws DbAccessException;
    
    /**
     * 获得自定义菜单根
     */
    public List<MenuPo> getCusMenusRoot() throws DbAccessException;
    
    /**
     *获取某个菜单
     * @param menuId
     * @param nodeId
     */
    public MenuPo getMenuItemById(String menuId,String nodeId) throws DbAccessException;
    /**
     * 查找子菜单项
     * @param menuId 当前菜单
     * @param nodeId 当前节点
     */
    public List<MenuPo> getSubMenuModel(String menuId,String nodeId) throws DbAccessException;
    /**
     * 保存菜单信息
     * flag=1成功，flag=0失败
     * @param paramMap
     * @return
     * @throws DbAccessException
     */
    public int saveMenuModel(Map paramMap) throws DbAccessException ;
    /**
     * 删除菜单信息
     * flag=1成功，flag=0失败
     * @param paramMap
     * @return
     * @throws DbAccessException
     */
    public int deleteMenuModel(String menuId,String nodeId) throws DbAccessException;

}
