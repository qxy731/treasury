package com.soule.app.sys.unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.SortListUtils;

@Service
public class SelcUnitServiceImpl implements ISelcUnitService {
    // private static final Log logger = LogFactory.getLog(SelcUnitServiceImpl.class);
    private static final String SYSGETUNIT = "sysmgr_unit.querySubUnitsById";
    private static final String QUERY_ONE_UNIT_IBATIS = "sysmgr_unit.getOneUnitById";
    @Autowired
    private IDefaultService defaultService;

    @SuppressWarnings("unchecked")
    public UnitPo getUnitById(String unitId) throws DbAccessException {
        Map map = new HashMap();
        map.put("unitId", unitId);
        return ((UnitPo) defaultService.getIbatisMediator().findById(QUERY_ONE_UNIT_IBATIS, map));
    }
    @SuppressWarnings("unchecked")
    public List<UnitPo> getUnitByRoot()throws ServiceException{
        List<UnitPo> unitList=null;
        try {
            unitList=(List<UnitPo>)defaultService.getIbatisMediator().find("sysmgr_unit.getRootUnits", null);
        } catch (DbAccessException e) {
            e.printStackTrace();
            throw new ServiceException("error","DataBase is error");
        }
        return unitList;
    }
    @SuppressWarnings("unchecked")
    public List<UnitPo> getSubUnitTreeModel(String unitId) throws DbAccessException {
        if (null != unitId && unitId.length() > 0) {
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("unitId", unitId);
            List<UnitPo> subUnitList = (List<UnitPo>) defaultService.getIbatisMediator().find(SYSGETUNIT, paraMap);
            if (subUnitList != null && subUnitList.size() > 0) {
                Iterator<UnitPo> unitIterator = subUnitList.iterator();
                while (unitIterator.hasNext()) {
                    UnitPo unit = unitIterator.next();
                    unit.setSubUnitList(getSubUnitTreeModel(unit.getUnitId()));
                }
            }
            return subUnitList;
        }
        return null;
    }
    @SuppressWarnings("unchecked")
    public List<UnitPo> getUnitByParent(String unitId) throws ServiceException {
        List<UnitPo> subUnitList=null;
        if (null != unitId && unitId.length() > 0) {
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("unitId", unitId);
            try {
                subUnitList = (List<UnitPo>) defaultService.getIbatisMediator().find("sysmgr_unit.querySubUnitsById", paraMap);
            } catch (DbAccessException e) {
                e.printStackTrace();
                throw new ServiceException("error","DataBase Is Error.");
            }
            
        }
        return subUnitList;
    }
    public UnitPo getUnitFetchChilds(String unitId) throws DbAccessException {
        UnitPo unit = getUnitById(unitId);
        if(unit!=null){
        List<UnitPo> subUnitList=getSubUnitTreeModel(unitId);
        unit.setSubUnitList(subUnitList);
        }
        return unit;
    }
    public List<UnitPo> getUnitParentsIncludeOwner(String unitId) throws DbAccessException {
        List<UnitPo> unitList=new ArrayList<UnitPo>();
        String tempUnitId=unitId;
        while(tempUnitId!=null&&tempUnitId.length()>0){
           UnitPo parent=getUnitById(tempUnitId);
           unitList.add(parent);
           tempUnitId=parent.getSuperUnitId();
        }
        SortListUtils.Sort(unitList, "getUnitLevel", "asc");
        return unitList;
    }
    public UnitPo getUnitFetchParent(String unitId) throws DbAccessException {
        UnitPo unit = getUnitById(unitId);
        setCurrentUnitParent(unit);
        return unit;
    }

    private void setCurrentUnitParent(UnitPo unit) throws DbAccessException {
        if (unit != null) {
            if (null != unit.getSuperUnitId() && unit.getSuperUnitId().length() > 0) {
                UnitPo child = getUnitById(unit.getSuperUnitId());
                unit.setParent(child);
                setCurrentUnitParent(child);
            }
        }
    }
}
