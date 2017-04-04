package com.soule.crm.sys.app.homepage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.comm.tools.ContextUtil;
import com.soule.crm.table.SysWidgetPo;

public class WidgetUtil {
    private final static Log logger = LogFactory.getLog(WidgetUtil.class);
    /**
     * 需要使用缓存
     * 
     * @param wid
     * @return
     */
    public static SysWidgetPo getWidget(String wid) {
        IDefaultService sDefault = (IDefaultService) ContextUtil.getBean("defaultService");
        SysWidgetPo pk = new SysWidgetPo();
        pk.setWid(wid);
        SysWidgetPo po = null;
        try {
            po = (SysWidgetPo) sDefault.getIbatisMediator().findById("single.getSysWidgetByKey", pk);
        } catch (DbAccessException e) {
            logger.error("",e);
        }
        return po;
    }

}
