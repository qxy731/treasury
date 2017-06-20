package com.soule.crm.pfm.datahelper.service;

import java.util.Map;

import com.soule.base.service.ServiceException;
public interface IDSLService {
    public static final String REFER_ID = "dsl.IDSLService";
    
    /**
     * 
     * @param querySql
     * @return
     * @throws ServiceException
     */
    public String queryStringValue(String querySql) throws ServiceException;
    
    /**
     * 
     * @param cycleName  
     * @return
     */
    public int getPeriodsOfYear(String cycleName);

	public int getPeriodsOfYear(Map params);
    
}
