package com.soule.app.sys.enums;

import java.util.List;

import com.soule.base.media.DbAccessException;

public interface ISysEnumService {

    public List<EnumPo> getEnumList(String enumId, String enumName, int page, int pagesize) throws DbAccessException;

    public Long getEnumCount(String enumId, String enumName) throws DbAccessException;

    public Integer deleteEnums(List<EnumPo> deletes) throws DbAccessException;

    public Boolean insert(EnumPo po);

    public List<EnumItemPo> queryEnumItem(String enumId) throws DbAccessException;

    public Integer updateEnum(EnumPo enumPo) throws DbAccessException;

    public Integer updateEnumItem(String enumId,List<EnumItemPo> items) throws DbAccessException;

}
