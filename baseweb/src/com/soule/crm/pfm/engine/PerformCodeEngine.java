package com.soule.crm.pfm.engine;

import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.crm.pfm.base.pattern.servicecomponent.model.SelectModel;
import com.soule.crm.pfm.engine.service.IIndicatorService;
public class PerformCodeEngine {
    private Hashtable<String, List<SelectModel>> codeMap;
    @Autowired
    private IIndicatorService indicatorService;
    
    private static PerformCodeEngine instance;
    public static synchronized PerformCodeEngine getInstance(){
        if (instance == null){
            instance = new PerformCodeEngine();
        }
        return instance;
    }
    
    private PerformCodeEngine(){
        codeMap = new Hashtable<String, List<SelectModel>>();
    }
    
    /**
     * 指标适用对象
     * @return
     */
    public List<SelectModel> getApplyScopes(){
        String mapKey = "applyScopes";
        List<SelectModel> result = codeMap.get(mapKey);
        if (result == null){
            try {
                result = indicatorService.getApplyScopes();
            } catch (Exception e) {
                // e.printStackTrace();
            }
            if (result != null){
                codeMap.put(mapKey, result);
            }
        }
        return result;
    }
    
    /**
     * 聚集范围
     * @return
     */
    public List<SelectModel> getStatScopes(){
        String mapKey = "statScopes";
        List<SelectModel> result = codeMap.get(mapKey);
        if (result == null){
            try {
                result = indicatorService.getStatScopes();
            } catch (Exception e) {
                // e.printStackTrace();
            }
            if (result != null){
                codeMap.put(mapKey, result);
            }
        }
        return result;
    }
    
    /**
     * 处理周期
     * @return
     */
    public List<SelectModel> getDataCycles(){
        String mapKey = "dataCycles";
        List<SelectModel> result = codeMap.get(mapKey);
        if (result == null){
            try {
                result = indicatorService.getDataCycles();
            } catch (Exception e) {
                // e.printStackTrace();
            }
            if (result != null){
                codeMap.put(mapKey, result);
            }
        }
        return result;
    }
    /**
     * 处理日期
     * @param cycleId
     * @return
     */
    public List<SelectModel> getCycleDates(String cycleId){
        String mapKey = "cycleDates_" + cycleId;
        List<SelectModel> result = codeMap.get(mapKey);
        if (result == null){
            try {
                result = indicatorService.getCycleDates(cycleId);
            } catch (Exception e) {
                // e.printStackTrace();
            }
            if (result != null){
                codeMap.put(mapKey, result);
            }
        }
        return result;
    }
    
    /**
     * 数据来源
     * @return
     */
    /*public List<SelectModel> getDataSources(){
        String mapKey = "dataSources";
        List<SelectModel> result = codeMap.get(mapKey);
        if (result == null){
            try {
                result = indicatorService.getDataSources();
            } catch (Exception e) {
                // e.printStackTrace();
            }
            if (result != null){
                codeMap.put(mapKey, result);
            }
        }
        return result;
    }*/
    /**
     * 数据类型
     * @return
     */
    public List<SelectModel> getDataTypes(){
        String mapKey = "dataTypes";
        List<SelectModel> result = codeMap.get(mapKey);
        if (result == null){
            try {
                result = indicatorService.getDataTypes();
            } catch (Exception e) {
                // e.printStackTrace();
            }
            if (result != null){
                codeMap.put(mapKey, result);
            }
        }
        return result;
    }
    /**
     * 计量单位
     * @return
     */
    public List<SelectModel> getDataUnits(){
        String mapKey = "dataUnits";
        List<SelectModel> result = codeMap.get(mapKey);
        if (result == null){
            try {
                result = indicatorService.getDataUnits();
            } catch (Exception e) {
                // e.printStackTrace();
            }
            if (result != null){
                codeMap.put(mapKey, result);
            }
        }
        return result;
    }
    /**
     * 计量精度
     * @return
     */
    public List<SelectModel> getComputePrecise(){
        String mapKey = "computePrecise";
        List<SelectModel> result = codeMap.get(mapKey);
        if (result == null){
            try {
                result = indicatorService.getComputePrecise();
            } catch (Exception e) {
                // e.printStackTrace();
            }
            if (result != null){
                codeMap.put(mapKey, result);
            }
        }
        return result;
    }
    /**
     * 方向性
     * @return
     */
    public List<SelectModel> getDirections(){
        String mapKey = "directions";
        List<SelectModel> result = codeMap.get(mapKey);
        if (result == null){
            try {
                result = indicatorService.getDirections();
            } catch (Exception e) {
                // e.printStackTrace();
            }
            if (result != null){
                codeMap.put(mapKey, result);
            }
        }
        return result;
    }
    /**
     * 状态
     * @return
     */
    public List<SelectModel> getStatus(){
        String mapKey = "stauts";
        List<SelectModel> result = codeMap.get(mapKey);
        if (result == null){
            try {
                result = indicatorService.getStatus();
            } catch (Exception e) {
                // e.printStackTrace();
            }
            if (result != null){
                codeMap.put(mapKey, result);
            }
        }
        return result;
    }
    
    /**
     * 计划周期
     * @param father
     * @return
     */
    public List<SelectModel> getPlanCycles(String father){
        String mapKey = "planCycles";
        if (father!=null && father.trim().length()>0){
            mapKey += ("_"+father);
        }
        List<SelectModel> result = codeMap.get(mapKey);
        if (result == null){
            try {
                result = indicatorService.getPlanCycles(father);
            } catch (Exception e) {
                // e.printStackTrace();
            }
            if (result != null){
                codeMap.put(mapKey, result);
            }
        }
        return result;
    }

}