package com.soule.comm.tools;

import java.util.ArrayList;
import java.util.List;

import com.soule.app.sys.menu.MenuPo;
import com.soule.comm.enu.YesNoFlag;
import com.soule.comm.po.ZtreeObj;

/**
 * @author peter E-mail: peter731@163.com
 * @version Create On：2011-11-29
 */
public class ZTreeUtil {
    /**
     * 菜单转换成树
     * 
     * @param listTree
     * @return
     */
    public static List<ZtreeObj> convertMenuZtree(List<MenuPo> listTree) {
        ArrayList<ZtreeObj> ret = new ArrayList<ZtreeObj>();
        for (MenuPo mpo : listTree) {
            ZtreeObj one = new ZtreeObj();
            one.setId(mpo.getNodeId());
            one.setpId(mpo.getParentNode());
            one.setName(mpo.getNodeName());
            one.setHref(mpo.getNodeUrl());
            YesNoFlag flag = YesNoFlag.getInstance(mpo.getHasChildFlag());
            one.setIsParent(flag == YesNoFlag.YES);
            ret.add(one);
        }
        return ret;
    }

}
