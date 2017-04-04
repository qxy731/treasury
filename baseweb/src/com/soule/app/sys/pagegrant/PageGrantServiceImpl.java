package com.soule.app.sys.pagegrant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.app.sys.funcauth.FuncAuthQueryIn;
import com.soule.app.sys.funcauth.FuncAuthQueryOut;
import com.soule.app.sys.funcauth.FuncAuthRecordPo;
import com.soule.app.sys.funcauth.IFuncAuthService;
import com.soule.app.sys.jsptree.IJspTreeService;
import com.soule.app.sys.jsptree.JspTreeListFileIn;
import com.soule.app.sys.jsptree.JspTreeListFileOut;
import com.soule.app.sys.jsptree.JspTreeListPo;
import com.soule.app.table.SysArcGrantPo;
import com.soule.app.table.SysResUrlPo;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ILogonUserInfo;
import com.soule.base.service.ServiceException;
import com.soule.base.service.auth.AdminAuthority;
import com.soule.base.service.auth.ResourceAuthority;
import com.soule.base.service.keygen.IKeyGenerator;
import com.soule.comm.CommConstants;
import com.soule.comm.enu.ResourceType;
import com.soule.comm.enu.YesNoFlag;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.ObjectUtil;
/**
 * 页面功能权限设置业务操作
 */
@Service
public class PageGrantServiceImpl implements IPageGrantService {

    private final static Log logger = LogFactory.getLog(PageGrantServiceImpl.class);
    private String tableName = "SysArcGrant";
    private String urlTableName = "SysResUrl";
    @Autowired
    private IKeyGenerator kenGen;
    @Autowired
    private IJspTreeService service;
    @Autowired
    private IDefaultService sDefault;
    @Autowired
    private IFuncAuthService sFuncAuth;

    /**
     * 当前角色可分配节点范围内，
     * 根据父节点，查询返回子节点信息。
     * 父节点为目录，列举子目录和jsp文件
     * 父节点为jsp，列举功能点
     */
    public PageGrantListChildOut listChild(PageGrantListChildIn in)
        throws ServiceException, DbAccessException {
        PageGrantListChildOut out = new PageGrantListChildOut();
        if (StringUtils.isEmpty(in.getRoleId()) ) {
            AppUtils.setResult(out, MsgConstants.E0001);
            return out;
        }
        String nodePath = in.getNodePath();
        List<PageGrantNodePo> output = new ArrayList<PageGrantNodePo>();
        String tmp = nodePath == null?"":nodePath.toLowerCase();
        if (tmp.endsWith(".jsp")) {
            FuncAuthQueryOut ret = getFuncAuth(nodePath);
            if (ret.getResultHead().isSuccess()) {
                convert2(ret.getRecord(),output);
            }
            else{
                out.setResultHead(ret.getResultHead());
            }
        }
        else {
            JspTreeListFileIn jin = new JspTreeListFileIn();
            jin.setRootPath(in.getRootPath());
            jin.setCurrPath(in.getNodePath());
            JspTreeListFileOut result = service.listFile(jin );
            if (result.getResultHead().isSuccess()) {
                convert(result.getList(),output);
            }
            for (PageGrantNodePo po :output) {
                if (!po.getHasChild()) {//没有叶子的就是jsp
                    FuncAuthQueryOut ret = getFuncAuth(po.getNodePath());
                    po.setHasChild(ret.getRecord().size() > 0);
                }
            }
            fillNodeId(output);
        }
        output = filterAssNode(output);
        out.setNode(output);
        fillFlag(in.getRoleId(),output);
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }

    private List<PageGrantNodePo> filterAssNode(List<PageGrantNodePo> list) throws ServiceException {
        ILogonUserInfo userInfo = AppUtils.getLogonUserInfo();
        if (userInfo == null ) {
            throw new ServiceException(MsgConstants.E0005, "");
        }
        List<PageGrantNodePo> ret = new ArrayList<PageGrantNodePo>();
        for (PageGrantNodePo mpo : list) {
            if (allowAss(mpo,userInfo.getAuthorities())) {
                ret.add(mpo);
            }
        }
        return ret;
    }
    private boolean allowAss(PageGrantNodePo mpo, Collection<GrantedAuthority> collection) {
        if (collection == null) {
            return false;
        }
        String mk = mpo.getNodeId();
        for (GrantedAuthority granted : collection) {
            if (granted instanceof AdminAuthority) {
                return true;
            }
            if (granted instanceof ResourceAuthority) {
                ResourceAuthority ra = (ResourceAuthority)granted;
                if (ra.getResType() ==ResourceType.URL || ra.getResType() ==ResourceType.FUNCTION) {
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
    private void fillFlag(String roleId, List<PageGrantNodePo> list) {
        if (list.size()>0) {
            for (PageGrantNodePo mpo:list) {
                SysArcGrantPo po = getGrantByPo(sDefault, roleId, mpo);
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

    private void fillNodeId(List<PageGrantNodePo> list) throws ServiceException {
        if (!ObjectUtil.isEmpty(list)) {
            for (PageGrantNodePo po :list) {
                Map map = new HashMap();
                map.put("urlPath", po.getNodePath());
                List ret = sDefault.getRecordByMap(urlTableName, map);
                SysResUrlPo npo = new SysResUrlPo();
                if (ObjectUtil.isEmpty(ret)) {
                    npo.setUrlId(String.valueOf(kenGen.getSeqence(CommConstants.URL_SEQ)));
                    npo.setUrlPath(po.getNodePath());
                    npo.setUrlName(po.getNodeName());
                    sDefault.saveRecord(urlTableName, npo);
                }
                else {
                    npo = (SysResUrlPo) ret.get(0);
                }
                po.setNodeId(String.valueOf(npo.getUrlId()));
            }
        }
    }
    private FuncAuthQueryOut getFuncAuth(String nodePath) throws ServiceException, DbAccessException {
        FuncAuthQueryIn fin = new FuncAuthQueryIn();
        fin.setJspPath(nodePath);
        FuncAuthQueryOut ret = sFuncAuth.query(fin);
        return ret;
    }

    private void convert2(List<FuncAuthRecordPo> list, List<PageGrantNodePo> output) {
        if (!ObjectUtil.isEmpty(list)) {
            for (FuncAuthRecordPo po :list) {
                PageGrantNodePo ppo = new PageGrantNodePo();
                ppo.setHasChild(Boolean.FALSE);
                ppo.setNodeName(po.getFuncName());
                ppo.setNodePath(po.getFuncCode());
                ppo.setNodeTyp("2");
                ppo.setNodeId(po.getFuncId());
                output.add(ppo);
            }
        }
    }

    private void convert(List<JspTreeListPo> list, List<PageGrantNodePo> output) {
        if (!ObjectUtil.isEmpty(list)) {
            for (JspTreeListPo po :list) {
                PageGrantNodePo ppo = new PageGrantNodePo();
                ppo.setHasChild(YesNoFlag.getInstance(po.getPathType()) == YesNoFlag.YES);
                ppo.setNodeName(po.getName());
                ppo.setNodePath(po.getPath());
                ppo.setNodeTyp(po.getPathType());
                output.add(ppo);
            }
        }
    }

    /**
     * 保存当前用户角色页面和功能点权限的信息。（新增和删除）
     */
    public PageGrantSaveAuthOut saveAuth(PageGrantSaveAuthIn in)
        throws ServiceException {
        PageGrantSaveAuthOut out = new PageGrantSaveAuthOut();
        if (StringUtils.isEmpty(in.getRoleId())) {
            throw new ServiceException(MsgConstants.E0001, "角色ID");
        }
        if (in.getModify() != null ) {
            ILogonUserInfo userInfo = AppUtils.getLogonUserInfo();
            for (PageGrantNodePo mpo : in.getModify()) {
                SysArcGrantPo spo = getGrantByPo(sDefault, in.getRoleId(), mpo);
                if (spo == null){
                    spo = new SysArcGrantPo();
                    spo.setPrincipalType("ROLE");
                    spo.setPrincipalCode(in.getRoleId());
                    spo.setResType("2".equals(mpo.getNodeTyp()) ? ResourceType.FUNCTION.name():ResourceType.URL.name());
                    spo.setResCode(mpo.getNodeId());
                    spo.setCreateTime(new Date());
                    spo.setLastUpdTime(spo.getCreateTime());
                    spo.setCreateUser(userInfo.getLogonInfo().getStaffId());
                    spo.setLastUpdUser(userInfo.getLogonInfo().getStaffId());
                    if (mpo.getRunFlag() !=null) {
                        spo.setRunFlag(mpo.getRunFlag()?"1":"0");
                    }
                    if (mpo.getAssFlag() != null) {
                        spo.setAssFlag(mpo.getAssFlag()?"1":"0");
                    }
                    sDefault.saveRecord(tableName, spo);
                }
                else {
                    spo.setLastUpdTime(new Date());
                    spo.setLastUpdUser(userInfo.getLogonInfo().getStaffId());
                    if (mpo.getRunFlag() !=null) {
                        spo.setRunFlag(mpo.getRunFlag()?"1":"0");
                    }
                    if (mpo.getAssFlag() != null) {
                        spo.setAssFlag(mpo.getAssFlag()?"1":"0");
                    }
                    sDefault.updateRecord(tableName, spo);
                }
            }
        }
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }
    
    private SysArcGrantPo getGrantByPo(IDefaultService sDefault, String roleId, PageGrantNodePo mpo) {
        SysArcGrantPo pk = new SysArcGrantPo();
        pk.setPrincipalCode(roleId);
        pk.setPrincipalType("ROLE");
        pk.setResType("2".equals(mpo.getNodeTyp()) ? ResourceType.FUNCTION.name():ResourceType.URL.name());
        pk.setResCode(mpo.getNodeId());
        SysArcGrantPo po = (SysArcGrantPo) sDefault.getRecordByKey(tableName, pk);
        return po;
    }

}
