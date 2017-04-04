package com.soule.crm.demo.comp.dropdown;

import java.util.List;

import com.soule.app.sys.unit.UnitPo;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.ServiceException;

/**
 * 机构名称拼音初始化
 */
public interface IUnitInitService {

    /**
     * 把所有机构名称拼音导入到内存中
     * @throws DbAccessException 
     */
    public void load() throws ServiceException, DbAccessException;

    public List<UnitPo> getAllUnits();
}
