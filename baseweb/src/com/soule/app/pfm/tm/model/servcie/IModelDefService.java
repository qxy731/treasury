package com.soule.app.pfm.tm.model.servcie;


import com.soule.app.pfm.tm.model.action.ModelDefDeleteIn;
import com.soule.app.pfm.tm.model.action.ModelDefDeleteOut;
import com.soule.app.pfm.tm.model.action.ModelDefInsertIn;
import com.soule.app.pfm.tm.model.action.ModelDefInsertOut;
import com.soule.app.pfm.tm.model.action.ModelDefQueryIn;
import com.soule.app.pfm.tm.model.action.ModelDefQueryOut;
import com.soule.app.pfm.tm.model.action.ModelDefUpdateIn;
import com.soule.app.pfm.tm.model.action.ModelDefUpdateOut;
import com.soule.app.pfm.tm.model.action.ModelDefUpdateQueryOut;
import com.soule.base.service.ServiceException;

/**
 * 自定义报表操作
 */
public interface IModelDefService {

    /**
     * 自定义报表的查询
     */
    public ModelDefQueryOut query(ModelDefQueryIn in) throws ServiceException;
    
    public ModelDefInsertOut  insertModel(ModelDefInsertIn in) throws ServiceException;
    
    public ModelDefUpdateOut  updateModel(ModelDefUpdateIn in) throws ServiceException;
    
    public ModelDefDeleteOut  deleteModel(ModelDefDeleteIn in) throws ServiceException;
    
    public ModelDefUpdateQueryOut  queryModelTar(ModelDefQueryIn in) throws ServiceException;
    
    public ModelDefUpdateQueryOut  queryTarByCode(ModelDefQueryIn in) throws ServiceException;
    
   
}
