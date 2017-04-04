package com.soule.app.sys.enums;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.base.media.DbAccessException;
import com.soule.base.po.EnumItem;
import com.soule.base.po.EnumType;
import com.soule.base.service.IDefaultService;
import com.soule.comm.tools.EnumTypeUtil;
import com.soule.comm.tools.ObjectUtil;
import com.soule.comm.tools.StringUtil;

@Service
public class EnumServiceImpl implements ISysEnumService {
    private final static Log logger = LogFactory.getLog(EnumServiceImpl.class);
    private String SYS_ENUM = "enum.getSysEnum";
    private String SYS_ENUM_DEL = "enum.delSysEnum";
    private String SYS_ENUM_INS = "enum.saveSysEnum";
    private String SYS_ENUM_ITEM = "enum.getSysEnumItem";
    private String SYS_ENUM_UPD = "enum.updSysEnum";
    private String SYS_ENUM_ITEM_DEL = "enum.delSysEnumItems";
    private String SYS_ENUM_ITEM_INS = "enum.saveSysEnumItem";
    @Autowired
    private IDefaultService defService;

    @SuppressWarnings("unchecked")
    public List<EnumPo> getEnumList(String enumId, String enumName, int page, int pagesize) throws DbAccessException {
        HashMap<String,String> condition = new HashMap<String,String>(8);
        condition.put("enumId", enumId);
        condition.put("enumName", enumName);
        int start = (page - 1) * pagesize;
        if (pagesize < 0) {
            pagesize = 10;
        }
        return defService.getIbatisMediator().find(SYS_ENUM , condition,start,pagesize);
    }

    public Long getEnumCount(String enumId, String enumName) throws DbAccessException {
        HashMap<String,String> condition = new HashMap<String,String>(8);
        condition.put("enumId", enumId);
        condition.put("enumName", enumName);
        return defService.getIbatisMediator().getRecordTotal(SYS_ENUM, condition);
    }

    public Integer deleteEnums(List<EnumPo> enums) throws DbAccessException {
        if (ObjectUtil.isEmpty(enums) ) {
            return 0;
        }
        int count = 0 ;
        for (EnumPo po: enums) {
            defService.getIbatisMediator().delete(SYS_ENUM_DEL, po);
            defService.getIbatisMediator().delete(SYS_ENUM_ITEM_DEL, po.getEnumId());
            count++;
        }
        return count;
    }

    public Boolean insert(EnumPo po) {
        if (po == null) {
            throw new RuntimeException("Parameter is null.(EnumPo)");
        }
        try {
            po.setCreateTime(new Date());
            po.setLastUpdTime(new Date());
            defService.getIbatisMediator().save(SYS_ENUM_INS, po);
            return Boolean.TRUE;
        } catch (DbAccessException e) {
            logger.error("",e);
        }
        return Boolean.FALSE;
    }

    public List<EnumItemPo> queryEnumItem(String enumId) throws DbAccessException {
        if (StringUtil.isEmpty(enumId)) {
            throw new RuntimeException("Parameter is null.(EnumPo)");
        }
        HashMap<String,String> map = new HashMap<String,String>(1);
        map.put("enumId", enumId);
        return defService.getIbatisMediator().find(SYS_ENUM_ITEM, map);
    }

    public Integer updateEnum(EnumPo enumPo) throws DbAccessException {
        if (enumPo == null) {
            throw new RuntimeException("Parameter is null.(EnumPo)");
        }
        return defService.getIbatisMediator().update(SYS_ENUM_UPD, enumPo);
    }

    public Integer updateEnumItem(String enumId, List<EnumItemPo> items) throws DbAccessException {
        if (StringUtil.isEmpty(enumId)) {
            throw new RuntimeException("Parameter is null.(enumId)");
        }
        defService.getIbatisMediator().delete(SYS_ENUM_ITEM_DEL, enumId);
        int c=0;
        EnumType et = new EnumType(enumId);
        for (EnumItemPo item :items) {
            item.setEnumId(enumId);
            et.addItem(new EnumItem(item.getItemId(),item.getItemValue(),item.getItemDesc()));
            int a=defService.getIbatisMediator().save(SYS_ENUM_ITEM_INS, item);
            if (a==1) {
                c++;
            }
        }
        EnumTypeUtil.addEnumType(et);
        return c;
    }

}
