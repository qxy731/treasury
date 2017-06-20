package com.soule.crm.pfm.engine.service;

import java.util.List;

import com.soule.app.pfm.tm.qtydef.QtyDefPo;
import com.soule.base.service.ServiceException;
import com.soule.crm.pfm.base.pattern.servicecomponent.model.SelectModel;

public interface IIndicatorService {
    public static String REFER_ID = "dsl.IndicatorService";
    
    /**
     * 加载所有的指标定义
     * @return
     * @throws ServiceException
     */
    public List<QtyDefPo> queryAllData();
    
    /**
     * 通过ID查找指标
     * @param indicatorId  指标ID
     * @return
     * @throws ServiceException
     */
    public QtyDefPo findById(String indicatorId);
    
    /**
     * 判断是否存在同名的指标
     * @param indicatorName  指标名称
     * @param indicatorId  指标代码（新增时为null）
     * @return
     * @throws ServiceException
     */
   // public boolean existsName(String indicatorName, String indicatorId) ;
    
    /**
     * 判断是否存在相同ID的指标
     * @param indicatorId
     * @return
     * @throws ServiceException
     */
    //public boolean existsId(String indicatorId);
    
    /**
     * 通过名称插座指标
     * @param indicatorName  指标名
     * @return
     * @throws ServiceException
     */
    public QtyDefPo findByName(String indicatorName);
    
    /**
     * 取得所有的适用对象
     * @return
     * @throws ServiceException
     */
    public List<SelectModel> getApplyScopes();
    
    /**
     * 取得所有聚集范围
     * @return
     * @throws ServiceException
     */
    public List<SelectModel> getStatScopes();
    
    /**
     * 计量精度
     * @return
     * @throws ServiceException
     */
    public List<SelectModel> getComputePrecise();
    
    /**
     * 数据单位（个、十、百、千、万……）
     * @return
     * @throws ServiceException
     */
    public List<SelectModel> getDataUnits();
    
    /**
     * 数据类型
     * @return
     * @throws ServiceException
     */
    public List<SelectModel> getDataTypes() ;
    
    /**
     * 数据来源
     * @return
     * @throws ServiceException
     */
    //public List<SelectModel> getDataSources();
    
    /**
     * 数据处理周期
     * @return
     * @throws ServiceException
     */
    public List<SelectModel> getDataCycles();
    
    /**
     * 数据周期处理日
     * @param cycleId 处理周期
     * @return
     * @throws ServiceException
     */
    public List<SelectModel> getCycleDates(String cycleId);
    
    /**
     * 选择计划周期Combox
     * @return
     * @throws ServiceException
     */
    public List<SelectModel> getPlanCycles(String father);
    
    /**
     * 数据排序的方向性
     * @return
     * @throws ServiceException
     */
    public List<SelectModel> getDirections();
    
    /**
     * 指标的状态
     * @return
     * @throws ServiceException
     */
    public List<SelectModel> getStatus();
    
    
}