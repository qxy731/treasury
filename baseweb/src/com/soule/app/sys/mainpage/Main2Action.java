package com.soule.app.sys.mainpage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.comm.StringUtils;
import com.soule.app.sys.menu.MenuPo;
import com.soule.base.action.BaseAction;
import com.soule.base.service.ILogonUserInfo;
import com.soule.comm.tools.AppUtils;

/**
 * 横向菜单主页
 * 
 * @author wuwei
 *
 */
@ParentPackage("json-default") 
@Namespace("/")
@Action("main")
@Results( { 
	@Result(name = "success", location = "/jsp/main2.jsp"),
	@Result(name = "logon", location = "/index.jsp")}
) 
public class Main2Action extends BaseAction {

    private static final long serialVersionUID = 1L;
    private final Log logger = LogFactory.getLog(Main2Action.class);
    /**
     * json格式的字符串，前台js接收不要加引号，如正确写法：var menusJson=<s:property value="menusJson"/>或var menusJson=${menusJson};
     */
    private String menusJson;
    /**
     * 图标集
     */
    private HashMap<String,String> icons = new HashMap<String,String>();

    /**
     * 图标样式
     */
    private String iconsStyle;
    @Autowired
    private IMainService mainService;

    public String getMenusJson() {
        return menusJson;
    }

    public void setMenusJson(String menusJson) {
        this.menusJson = menusJson;
    }

    public String getIconsStyle() {
        return iconsStyle;
    }

    public void setIconsStyle(String iconsStyle) {
        this.iconsStyle = iconsStyle;
    }

    public String execute() {
        try {
            ILogonUserInfo userInfo = AppUtils.getLogonUserInfo();
            if (userInfo == null) {
                return "logon";
            }
            List<MenuPo> listTree = mainService.getSysTreeModel(AppUtils.getLogonUserInfo());
            
            List<MenuPo> rootPos = getCurrentNodes(null, listTree);
            ArrayList<ViewMenuBarItemPo> barItems = new ArrayList<ViewMenuBarItemPo>();
            if (rootPos.size() == 1) {
                MenuPo rootPo  = rootPos.get(0);
                //一级菜单作为菜单条的项
                List<MenuPo> level1Pos = getCurrentNodes(rootPo.getNodeId(), listTree);
                for (int i = 0 ; i < level1Pos.size() ; i++) {
                    ViewMenuBarItemPo item = convertMenuBarItem(level1Pos.get(i),listTree);
                    barItems.add(item);
                }
                this.menusJson = "{\"items\":" + JSONArray.fromObject(barItems).toString() + "}";
                this.menusJson = this.menusJson.replace("\"children\":[],", "");
                this.menusJson = this.menusJson.replace("\"click\":\"itemclick\",", "\"click\":itemclick,");
            }
            else {
                this.menusJson = "{\"items\":[]}";
            }
            StringBuilder sb = new StringBuilder();
            for (String key :icons.keySet()) {
                sb.append(".l-icon-").append(key).append(" {");
                sb.append("background: url('").append(icons.get(key)).append("') no-repeat 2px 4px;}\r\n");
            }
            iconsStyle = sb.toString();
        } catch (Exception e) {
            logger.error("",e);
        }
        return SUCCESS;
    }
    public ViewMenuBarItemPo convertMenuBarItem(MenuPo mpo,List<MenuPo> all) {
        ViewMenuPo menu = convertMenuPo(mpo,all,0);
        ViewMenuBarItemPo item = new ViewMenuBarItemPo();
        item.setText(menu.getText());
        item.setIcon(menu.getIcon());
        item.setUrl(menu.getUrl());
        if (menu.getItems().size() > 0) {
            item.setMenu(menu);
            int maxWidth = 0;
            for (int i = 0 ; i < menu.getItems().size() ; i++) {
                ViewMenuPo one =  menu.getItems().get(i);
                if (maxWidth<Integer.parseInt(one.getWidth())) {
                    maxWidth = Integer.parseInt(one.getWidth());
                }
            }
            for (int i = 0 ; i < menu.getItems().size() ; i++) {
                ViewMenuPo one =  menu.getItems().get(i);
                one.setWidth(String.valueOf(maxWidth));
            }
            menu.setWidth(String.valueOf(maxWidth));
        }
        return item;
    }
    /**
     * 
     * @param mpo
     * @param all
     * @param isFirstLevel 
     * @return
     */
    public ViewMenuPo convertMenuPo(MenuPo mpo,List<MenuPo> all,int level) {
        ViewMenuPo curr = new ViewMenuPo();
        curr.setText(mpo.getNodeName());
        if (!StringUtils.isEmpty(mpo.getNodeImg())) {
            String img = mpo.getNodeImg().replace("\\", "/");
            int x = img.lastIndexOf('/');
            curr.setIcon(img.substring(x+1).replace('.', '_'));
            icons.put(curr.getIcon(), img.startsWith("/")?img.substring(1):img);
        }
        curr.setUrl(mpo.getNodeUrl());
        curr.setNodeId(mpo.getNodeId());
        String x = curr.getText();
        int w = 0;
        for (int i = 0 ; i < x.length(); i++) {
            if (x.charAt(i) > 128) {
                w++;
            }
            w++;
        }
        curr.setWidth(String.valueOf((int)w*7+50));

        List<MenuPo> currentNodes = getCurrentNodes(mpo.getNodeId() ,all);
        if (currentNodes.size() > 0) {
            for (MenuPo one : currentNodes) {
                ViewMenuPo child = convertMenuPo(one,all,level+1);
                if (level > 0) {
                    curr.getChildren().add(child);
                }
                else {
                    curr.getItems().add(child);
                }
            }
        }
        return curr;
    }


    private static List<MenuPo> getCurrentNodes(String nodeId, List<MenuPo> all) {
        ArrayList<MenuPo> ret = new ArrayList<MenuPo>();
        for (int i =0 ; i < all.size() ; i++) {
            if (nodeId == null && all.get(i).getParentNode() == null) {
                ret.add(all.get(i));
            }
            if (nodeId != null && nodeId.equals(all.get(i).getParentNode())) {
                ret.add(all.get(i));
            }
        }
        return ret;
    }

}