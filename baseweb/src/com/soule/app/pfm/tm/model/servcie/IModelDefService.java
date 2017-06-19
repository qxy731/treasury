package com.soule.app.pfm.tm.model.servcie;


import com.soule.app.pfm.tm.model.action.ModelDefQueryIn;
import com.soule.app.pfm.tm.model.action.ModelDefQueryOut;
import com.soule.app.pfm.tm.report.action.UserDefinedReportQueryIn;
import com.soule.app.pfm.tm.report.action.UserDefinedReportQueryOut;
import com.soule.base.service.ServiceException;

/**
 * 自定义报表操作
 */
public interface IModelDefService {

    /**
     * 自定义报表的查询
     */
    public ModelDefQueryOut query(ModelDefQueryIn in) throws ServiceException;
   
}
