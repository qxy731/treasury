package com.soule.base.service.auth;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.logon.LogonInfoPo;
@SuppressWarnings("unchecked")
public class ArcGrantServiceImpl implements IArcGrantService{

    private final static String USER_ROLE_ASSIGN = "auth.getUserRoles";
    private final static String MENU_GRANT_OF_ROLE = "auth.getMenuGrantOfRole";
    private final static String URL_GRANT_OF_ROLE = "auth.getUrlGrantOfRole";
    private final static String FUNC_GRANT_OF_ROLE = "auth.getFuncGrantOfRole";
    private static Log logger = LogFactory.getLog(ArcGrantServiceImpl.class);

    @Autowired
    private IDefaultService defaultService;

    public List getAllGrantsByUser(LogonInfoPo logonInfo) {
        return null;
    }

    public List<GrantedAuthority> getGrantOfRole(String roleId) throws DbAccessException {
        ArrayList<GrantedAuthority> ret = new ArrayList<GrantedAuthority>();
        List<ArcGrantModel> menus = defaultService.getIbatisMediator().find(MENU_GRANT_OF_ROLE, roleId);
        addGrantedAuthority(ret, menus);
        List<ArcGrantModel> urls = defaultService.getIbatisMediator().find(URL_GRANT_OF_ROLE, roleId);
        addGrantedAuthority(ret, urls);
        List<ArcGrantModel> funcs = defaultService.getIbatisMediator().find(FUNC_GRANT_OF_ROLE, roleId);
        addGrantedAuthority(ret, funcs);
        return ret;
    }

    private void addGrantedAuthority(ArrayList<GrantedAuthority> ret, List<ArcGrantModel>  arcGrant) {
        for (ArcGrantModel agm : arcGrant) {
            ResourceAuthority res = new ResourceAuthority(
                    agm.getResType(),agm.getResCode(), agm.getUrl(),agm.getRunFlag(),agm.getAssFlag());
            if (!hasGrantedAuthority(res)) {
                ret.add(res);
            }
        }
    }

    private boolean hasGrantedAuthority(ResourceAuthority res) {
        // TODO Auto-generated method stub
        return false;
    }

	public List<RoleAssignPo> getUserRoleAssign(String userId) throws DbAccessException {
        return defaultService.getIbatisMediator().find(USER_ROLE_ASSIGN, userId);
    }

    public boolean isAdministrator(String logonId) {
        // TODO 从配置文件中设置超级管理员
        return "admin".equals(logonId);
    }

}
