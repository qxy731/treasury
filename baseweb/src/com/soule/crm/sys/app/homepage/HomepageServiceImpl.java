package com.soule.crm.sys.app.homepage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ILogonUserInfo;
import com.soule.base.service.ServiceException;
import com.soule.base.service.auth.RoleAssignPo;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.ContextUtil;
import com.soule.comm.tools.ParamsUtil;
import com.soule.comm.tools.StringUtil;
import com.soule.crm.table.SysHomepagePo;
import com.soule.crm.table.SysWidgetPo;
/**
 * 首页定制业务操作
 */
@Service
public class HomepageServiceImpl implements IHomepageService {

    private final static Log logger = LogFactory.getLog(HomepageServiceImpl.class);
    @Autowired
    ParamsUtil paramsUtil;
    @Autowired
    IDefaultService sDefault;
    /**
     * 查询系统中某个用户的首页配置信息
     */
    public HomepageInitDataOut initData(HomepageInitDataIn in) throws ServiceException {
        HomepageInitDataOut out = new HomepageInitDataOut();
        SysHomepagePo pk = new SysHomepagePo();
        pk.setUserId(in.getUserId());
        try {
            String ldata = "";
            SysHomepagePo po = (SysHomepagePo) sDefault.getIbatisMediator().findById("single.getSysHomepageByKey", pk);
            if ( po == null) {
                ldata = paramsUtil.getParamValueByParamId("HOMEPAGE_LAYOUT");
            }
            else {
                ldata = po.getLayoutData();
            }
            String[] cols = StringUtil.toArray(ldata,'|');
            //微件ID集合
            HashSet<String> wids = new HashSet<String>();
            ArrayList<ArrayList<HomepagePageConfigPo>> result = new ArrayList<ArrayList<HomepagePageConfigPo>>();
            if (!StringUtil.isEmpty(ldata)){
                for (int i = 0 ; i < cols.length ; i++) {
                    ArrayList<HomepagePageConfigPo> col = new ArrayList<HomepagePageConfigPo>();
                    String[] ids = StringUtil.toArray(cols[i],',');
                    for (int x = 0 ; x < ids.length ; x++) {
                        SysWidgetPo wid = WidgetUtil.getWidget(ids[x]);
                        if (wid != null) {
                            HomepagePageConfigPo hpp = new HomepagePageConfigPo();
                            hpp.setTitle(wid.getTitle());
                            hpp.setType(wid.getType());
                            hpp.setWid(wid.getWid());
                            hpp.setParama(wid.getParama());
                            hpp.setParamb(wid.getParamb());
                            hpp.setParamc(wid.getParamc());
                            initModelData(wid.getDataService(),in.getUserId(),hpp);
                            col.add(hpp);
                            wids.add(wid.getWid());
                            out.getResultHead().setTotal(1);
                        }
                    }
                    result.add(col);
                }
            }
            out.setPageConfig(result);
            HashMap map =new HashMap();
            ILogonUserInfo logonUser = AppUtils.getLogonUserInfo();
            List list =new ArrayList();
            if(!logonUser.getUsername().equals("admin")){
                List<RoleAssignPo> roles = logonUser.getAssignRole();
	            for(RoleAssignPo role:roles){
	            	 map.put("roleId", role.getRoleId());
	                 list.addAll((List) sDefault.getIbatisMediator().find("single.getSysWidget",map ));
	            }
	            list=trimList(list);
            }else{
            	list=(List) sDefault.getIbatisMediator().find("single.getSysWidget",new HashMap() );
            }
            ArrayList<HomepageWidgetPo> hlist = new ArrayList<HomepageWidgetPo>();
            if (list != null) {
                for (int x=0 ; x< list.size() ; x++) {
                    SysWidgetPo swp = (SysWidgetPo)list.get(x);
                    HomepageWidgetPo hwp = new HomepageWidgetPo();
                    hwp.setTitle(swp.getTitle());
                    hwp.setWid(swp.getWid());
                    hwp.setDataService(swp.getDataService());
                    hwp.setParama(swp.getParama());
                    hwp.setParamb(swp.getParamb());
                    hwp.setParamc(swp.getParamc());
                    hwp.setType(swp.getType());
                    if (wids.contains(hwp.getWid())) {
                        hwp.setSelected("true");
                    }
                    else {
                        hwp.setSelected("false");
                    }
                    hlist.add(hwp);
                }
            }
            out.setWidget(hlist);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0000);
        }
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }
    private List trimList(List list) {
    	List list2=new ArrayList();
    	for(int i=list.size()-1;i>=0;i--){
    		SysWidgetPo o=(SysWidgetPo)list.get(i);
    		if(list2.indexOf(o)==-1){
    			list2.add(o);
    		}
    	}
		return list2;
	}
	private void initModelData(String dataService, String userId, HomepagePageConfigPo hpp) {
        if (StringUtil.isEmpty(dataService)) {
            return;
        }
        Object service =  ContextUtil.getBean(dataService);
        if (service !=null && service instanceof IWidgetService) {
            IWidgetService iws = (IWidgetService)service;
            try {
                hpp.setModel(iws.getInitModelData(userId));
            }catch(Exception ex){
                logger.error("SERVICE",ex);
            }
        }
        else {
            logger.error("Data service [" + dataService + "] not found!" );
        }
    }
    /**
     * 修改首页布局信息
     */
    public HomepageModifyOut modify(HomepageModifyIn in)
        throws ServiceException {
        HomepageModifyOut out = new HomepageModifyOut();
        SysHomepagePo shpo = new SysHomepagePo();
        shpo.setLayoutData(in.getLayoutData());
        shpo.setUserId(in.getUserId());
        if (StringUtil.isEmpty(in.getUserId())) {
            throw new ServiceException(MsgConstants.E0001, "用户ID");
        }
        try {
            SysHomepagePo sp = (SysHomepagePo) sDefault.getIbatisMediator().findById("single.getSysHomepageByKey", shpo);
            if ( sp == null) {
                sDefault.getIbatisMediator().save("single.saveSysHomepage", shpo);
            }
            else {
                sDefault.getIbatisMediator().update("single.updSysHomepage", shpo);
            }
        } catch (DbAccessException e) {
            logger.error("DB",e);
            throw new ServiceException(MsgConstants.E0002,e.getMessage());
        }
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }
    /**
     * 查询所有微件信息
     */
    public HomepageListWidgetOut listWidget(HomepageListWidgetIn in)
        throws ServiceException {
        HomepageListWidgetOut out = new HomepageListWidgetOut();
        List list = null;
        try {
            list = (List) sDefault.getIbatisMediator().find("single.getSysWidget", new HashMap());
        } catch (DbAccessException ex) {
            logger.error("DB",ex);
            throw new ServiceException(MsgConstants.E0002,ex.getMessage());
        }
        out.setWidget(list);
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }

}
