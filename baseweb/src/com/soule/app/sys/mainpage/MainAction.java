package com.soule.app.sys.mainpage;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONArray;

import com.soule.app.sys.menu.MenuPo;
import com.soule.base.action.BaseAction;
import com.soule.base.service.ILogonUserInfo;
import com.soule.comm.po.ZtreeObj;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.ZTreeUtil;

public class MainAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    private final Log logger = LogFactory.getLog(MainAction.class);
    /**
     * json格式的字符串，前台js接收不要加引号，如正确写法：var zNodes=<s:property value="zNodes"/>或var zNodes=${zNodes};
     */
    private String zNodes;
    @Autowired
    private IMainService mainService;

    public String getzNodes() {
        return zNodes;
    }

    public void setzNodes(String zNodes) {
        this.zNodes = zNodes;
    }

    public String execute() {
        try {
            ILogonUserInfo userInfo = AppUtils.getLogonUserInfo();
            if (userInfo == null) {
                return "logon";
            }
            List<MenuPo> listTree = mainService.getSysTreeModel(AppUtils.getLogonUserInfo());
            List<ZtreeObj> viewTree = ZTreeUtil.convertMenuZtree(listTree);
            this.zNodes = JSONArray.fromObject(viewTree).toString();
        } catch (Exception e) {
            logger.error("",e);
        }
        return SUCCESS;
    }
    
}