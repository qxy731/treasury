package com.soule.app.sys.menugrant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.app.sys.menu.IMenuService;
import com.soule.app.sys.menu.MenuPo;
import com.soule.app.table.SysArcGrantPo;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ILogonUserInfo;
import com.soule.base.service.ServiceException;
import com.soule.base.service.auth.AdminAuthority;
import com.soule.base.service.auth.ResourceAuthority;
import com.soule.comm.enu.ResourceType;
import com.soule.comm.enu.YesNoFlag;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.ContextUtil;
/**
 * 菜单权限设置业务操作
 */
@Service
public class MenuGrantServiceImpl implements IMenuGrantService {

    private final static Log logger = LogFactory.getLog(MenuGrantServiceImpl.class);
    private String tableName = "SysArcGrant";
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IDefaultService sDefault;
    /**
     * 根据当前菜单节点，获得当前用户角色可分配子菜单和菜单目录及分配角色已获权限菜单标志信息
     */
    public MenuGrantListSubOut listSub(MenuGrantListSubIn in) throws ServiceException {
        MenuGrantListSubOut out = new MenuGrantListSubOut();
        if (StringUtils.isEmpty(in.getRoleId()) ) {
            AppUtils.setResult(out, MsgConstants.E0001);
            return out;
        }

        try {
            List<MenuPo> allSubTree = menuService.getSubMenuModel(in.getMenuId(), in.getNodeId());
            List<MenuGrantNodePo> assSubTree = filterMenu(allSubTree);
            fillFlag(in.getRoleId(),assSubTree);
            out.setNode(assSubTree);
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002);
        }
        return out;
    }
    /**
     * 被调整角色的当前权限状态
     */
    private void fillFlag(String roleId, List<MenuGrantNodePo> assSubTree) {
        if (assSubTree.size()>0) {
            for (MenuGrantNodePo mpo:assSubTree) {
                SysArcGrantPo po = getGrantByMenuPo(roleId, mpo);
                if (po == null){
                    mpo.setAssFlag(Boolean.FALSE);
                    mpo.setRunFlag(Boolean.FALSE);
                }
                else {
                    mpo.setAssFlag(YesNoFlag.getInstance(po.getAssFlag()) == YesNoFlag.YES);
                    mpo.setRunFlag(YesNoFlag.getInstance(po.getRunFlag()) == YesNoFlag.YES);
                }
            }
        }
    }
    private SysArcGrantPo getGrantByMenuPo(String roleId, MenuGrantNodePo mpo) {
        SysArcGrantPo pk = new SysArcGrantPo();
        pk.setPrincipalCode(roleId);
        pk.setPrincipalType("ROLE");
        pk.setResType("MENU");
        pk.setResCode(mpo.getMenuId() +"_"+ mpo.getNodeId());
        SysArcGrantPo po = (SysArcGrantPo) sDefault.getRecordByKey(tableName, pk);
        return po;
    }
    /**
     * 根据当前用户过滤出可分配的菜单
     * @throws ServiceException 
     */
    private List<MenuGrantNodePo> filterMenu(List<MenuPo> allSubTree) throws ServiceException {
        ILogonUserInfo userInfo = AppUtils.getLogonUserInfo();
        if (userInfo == null ) {
            throw new ServiceException(MsgConstants.E0005, "");
        }
        List<MenuGrantNodePo> ret = new ArrayList<MenuGrantNodePo>();
        for (MenuPo mpo : allSubTree) {
            if (allowAss(mpo,userInfo.getAuthorities())) {
                MenuGrantNodePo npo = new MenuGrantNodePo();
                npo.setMenuId(mpo.getMenuId());
                npo.setNodeId(mpo.getNodeId());
                npo.setNodeName(mpo.getNodeName());
                npo.setAssFlag(Boolean.FALSE);
                npo.setRunFlag(Boolean.FALSE);
                npo.setHasChild(YesNoFlag.getInstance(mpo.getHasChildFlag()) == YesNoFlag.YES);
                ret.add(npo);
            }
        }
        return ret;
    }
    private boolean allowAss(MenuPo mpo, Collection<GrantedAuthority> collection) {
        if (collection == null) {
            return false;
        }
        String mk = mpo.getMenuId() + "_"+ mpo.getNodeId();
        for (GrantedAuthority granted : collection) {
            if (granted instanceof AdminAuthority) {
                return true;
            }
            if (granted instanceof ResourceAuthority) {
                ResourceAuthority ra = (ResourceAuthority)granted;
                if (ra.getResType() ==ResourceType.MENU) {
                    if (ra.getAuthority().equals(mk)) {
                        if (ra.getAssFlag() != null && ra.getAssFlag().booleanValue()){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    /**
     * 保存当前用户角色菜单权限的信息。（新增和删除）
     */
    public MenuGrantSaveAuthOut saveAuth(MenuGrantSaveAuthIn in) throws ServiceException {
        MenuGrantSaveAuthOut out = new MenuGrantSaveAuthOut();
        if (StringUtils.isEmpty(in.getRoleId())) {
            throw new ServiceException(MsgConstants.E0001, "角色ID");
        }
        if (in.getModify() != null ) {
            ILogonUserInfo userInfo = AppUtils.getLogonUserInfo();
            for (MenuGrantNodePo mpo : in.getModify()) {
                SysArcGrantPo spo = getGrantByMenuPo(in.getRoleId(), mpo);
                if (spo == null){
                    spo = new SysArcGrantPo();
                    spo.setPrincipalType("ROLE");
                    spo.setPrincipalCode(in.getRoleId());
                    spo.setResType("MENU");
                    spo.setResCode(mpo.getMenuId() + "_"+ mpo.getNodeId());
                    spo.setCreateTime(new Date());
                    spo.setLastUpdTime(spo.getCreateTime());
                    spo.setCreateUser(userInfo.getLogonInfo().getStaffId());
                    spo.setLastUpdUser(userInfo.getLogonInfo().getStaffId());
                    spo.setRunFlag(mpo.getRunFlag()?"1":"0");
                    spo.setAssFlag(mpo.getAssFlag()?"1":"0");
                    sDefault.saveRecord(tableName, spo);
                }
                else {
                    spo.setLastUpdTime(new Date());
                    spo.setLastUpdUser(userInfo.getLogonInfo().getStaffId());
                    spo.setRunFlag(mpo.getRunFlag()?"1":"0");
                    spo.setAssFlag(mpo.getAssFlag()?"1":"0");
                    sDefault.updateRecord(tableName, spo);
                }
            }
        }
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }

}
