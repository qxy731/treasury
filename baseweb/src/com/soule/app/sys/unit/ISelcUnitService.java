package com.soule.app.sys.unit;
import java.util.List;

import com.soule.base.media.DbAccessException;
import com.soule.base.service.ServiceException;
public interface ISelcUnitService {
   public List<UnitPo> getSubUnitTreeModel(String unitId) throws DbAccessException;
   public UnitPo getUnitById(String unitId) throws DbAccessException;
   public UnitPo getUnitFetchParent(String unitId) throws DbAccessException;
   public UnitPo getUnitFetchChilds(String unitId) throws DbAccessException;
   public List<UnitPo> getUnitByRoot()throws ServiceException;
   
   public List<UnitPo> getUnitByParent(String unitId)throws ServiceException;
   
   public List<UnitPo> getUnitParentsIncludeOwner(String unitId) throws DbAccessException;
}
