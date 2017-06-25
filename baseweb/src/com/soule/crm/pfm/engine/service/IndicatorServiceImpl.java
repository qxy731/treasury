package com.soule.crm.pfm.engine.service;

import java.util.List;

import com.soule.app.pfm.tm.qtydef.QtyDefPo;
import com.soule.base.service.IDefaultService;
import com.soule.comm.tools.ContextUtil;
import com.soule.crm.pfm.base.pattern.servicecomponent.model.SelectModel;

public class IndicatorServiceImpl implements IIndicatorService {
    private static final String GET_ONE_QTYDEF = "qtydef.getOneQtyDef";
    private static final String GET_ONE_QTYDEF_BYNAME= "qtydef.getQtyDefByTarName";
    //private IDefaultService  defService;
    
    public IndicatorServiceImpl(){
    	//defService = (IDefaultService)ContextUtil.getBean("defaultService");
    }

   /* public boolean existsId(String indicatorId) {
        //System.out.println("IndicatorServiceImpl����qtydef.getOneQtyDef");
        return false;
    }

    public boolean existsName(String indicatorName, String indicatorId) {
        //System.out.println("IndicatorServiceImpl����qtydef.getQtyDefByTarName");
        return false;
    }*/

    public QtyDefPo findById(String indicatorId) {
        try {
        	IDefaultService defService = (IDefaultService)ContextUtil.getBean("defaultService");
            QtyDefPo qtyDef = (QtyDefPo) defService.getIbatisMediator().findById(GET_ONE_QTYDEF, indicatorId);
            return qtyDef;
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public QtyDefPo findByName(String indicatorName) {
        try {
        	IDefaultService defService = (IDefaultService)ContextUtil.getBean("defaultService");
            List<QtyDefPo> qtyDef =(List<QtyDefPo>) defService.getIbatisMediator().find(GET_ONE_QTYDEF_BYNAME, indicatorName);
            if(qtyDef!=null&&qtyDef.size()>0){
               return qtyDef.get(0);
            }
            return null;
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }

    public List<SelectModel> getApplyScopes() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<SelectModel> getComputePrecise() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<SelectModel> getCycleDates(String cycleId) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<SelectModel> getDataCycles() {
        // TODO Auto-generated method stub
        return null;
    }

    /*public List<SelectModel> getDataSources() {
        // TODO Auto-generated method stub
        return null;
    }*/

    public List<SelectModel> getDataTypes() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<SelectModel> getDataUnits() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<SelectModel> getDirections() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<SelectModel> getPlanCycles(String father) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<SelectModel> getStatScopes() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<SelectModel> getStatus() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<QtyDefPo> queryAllData() {
        // TODO Auto-generated method stub
        return null;
    }
  
}
