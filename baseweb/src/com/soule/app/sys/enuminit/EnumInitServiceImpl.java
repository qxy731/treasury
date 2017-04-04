package com.soule.app.sys.enuminit;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.app.sys.enums.EnumItemPo;
import com.soule.base.media.DbAccessException;
import com.soule.base.po.EnumItem;
import com.soule.base.po.EnumType;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.EnumTypeUtil;
/**
 * 枚举参数初始化业务操作
 */
public class EnumInitServiceImpl implements IEnumInitService {

    private final static Log logger = LogFactory.getLog(EnumInitServiceImpl.class);
    private String SYS_ENUM_LOAD = "enum.loadAll";
    @Autowired
    private IDefaultService service;
    /**
     * 把所有枚举参数导入到内存中
     * @throws DbAccessException 
     */
    public void load() throws ServiceException, DbAccessException {
        List<EnumItemPo> data = service.getIbatisMediator().find(SYS_ENUM_LOAD, "");
        EnumType one = null;
        for (EnumItemPo po:data) {
            if (one == null) {
                one = new EnumType(po.getEnumId());
            }
            if (!one.getKey().equals(po.getEnumId())) {
                EnumTypeUtil.addEnumType(one);
                one = new EnumType(po.getEnumId());
            }
            EnumItem item = convertItem(po);
            one.addItem(item);
        }
        if (one != null) {
            EnumTypeUtil.addEnumType(one);
        }
        logger.info("枚举参数装载完成");
    }

    private EnumItem convertItem(EnumItemPo po) {
        return new EnumItem(po.getItemId(),po.getItemValue(),po.getItemDesc());
    }

}
