package com.soule.crm.pfm.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

import com.soule.app.pfm.tm.qtydef.QtyDefPo;
import com.soule.comm.tools.ContextUtil;
import com.soule.crm.pfm.engine.service.IIndicatorService;
public class IndicatorEngine {
    private static IndicatorEngine instance;
    private TreeMap<String,QtyDefPo> indicatorMap;
    private IIndicatorService indicatorService;
    
    public static IndicatorEngine getInstance(){
    	if (instance == null){
            instance = new IndicatorEngine();
        }
        return instance;
    }
    private IndicatorEngine(){
    	indicatorService = (IIndicatorService)ContextUtil.getBean(IIndicatorService.REFER_ID);
        indicatorMap = new TreeMap<String,QtyDefPo>();
    }
    
    /**
     * 按名字查询(DSL校验常用)
     * @param name
     * @return
     */
    public QtyDefPo getModel(String name){
        QtyDefPo model = indicatorMap.get(name);
        if (model == null){
            model = indicatorService.findByName(name);
            if (model != null){
                indicatorMap.put(model.getTarName(), model);
            }
        }
        return model;
    }
    public void removeModel(String name){
        QtyDefPo model = indicatorMap.get(name);
        if (model != null){
            indicatorMap.remove(name);
        }
    }
    /*public boolean existsName(String indicatorName, String indicatorId){
        QtyDefPo model = indicatorMap.get(indicatorName);
        if (model!=null && !model.getTarCode().equals(indicatorId)){
            return true;
        }
        return indicatorService.existsName(indicatorName, indicatorId);
    }*/
    
    /**
     * 按指标ID查询
     * @param indicatorId
     * @return
     */
    public QtyDefPo getModelById(String indicatorId){
        Collection<QtyDefPo> values = indicatorMap.values();
        for (QtyDefPo model : values){
            if (model.getTarCode().equals(indicatorId)){
                return model;
            }
        }
        QtyDefPo model = null;
        model = indicatorService.findById(indicatorId);
        if (model != null){
            indicatorMap.put(model.getTarName(), model);
        }
        return model;
    }
    public void removeModelById(String indicatorId){
        Collection<QtyDefPo> values = indicatorMap.values();
        for (QtyDefPo model : values){
            if (model.getTarCode().equals(indicatorId)){
                indicatorMap.remove(model.getTarName());
                return;
            }
        }
    }
    /*public boolean existsCode(String indicatorId){
        Collection<QtyDefPo> values = indicatorMap.values();
        for (QtyDefPo model : values){
            if (model.getTarCode().equals(indicatorId)){
                return true;
            }
        }
        return indicatorService.existsId(indicatorId);
    }*/
    
    /**
     * 按数据来源查询指标列表
     * @param source  手工指标、系统取数、日常ETL、周期ETL等
     * @return
     */
    /*public List<IndicatorModel> getModelsBySource(String source){
        List<IndicatorModel> lstModel = new ArrayList<IndicatorModel>();
        Collection<QtyDefPo> values = indicatorMap.values();
        String element;
        for (QtyDefPo model : values){
            element = model.getSource();
            if (element!=null && element.equals(source)){
                lstModel.add(model);
            }
        }
        
        return lstModel;
    }*/
    
    /**
     * 按机构查询指标列表
     * @param organId
     * @return
     */
    public List<QtyDefPo> getModelsByOrgan(String organId){
        List<QtyDefPo> lstModel = new ArrayList<QtyDefPo>();
        Collection<QtyDefPo> values = indicatorMap.values();
        String element;
        for (QtyDefPo model : values){
            //element = model.getOrganId();
            element = model.getCreateOrg();
            if (element!=null && element.equals(organId)){
                lstModel.add(model);
            }
        }
        
        return lstModel;
    }
    
}
