package com.soule.crm.pfm.datahelper.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class DSLServiceImpl implements IDSLService {

    public String queryStringValue(String querySql){
        //StringValue strValue = 
            //(StringValue)super.findById("dsl.queryStringValue", querySql);
       // if (strValue != null){
            //return strValue.getStrValue();
        //}else{
            return null;
    }
    
    /**
     * @see  IDSLService#getPeriodsOfYear(String)
     */
    public int getPeriodsOfYear(String cycleName){
        /*try{
            StringValue value = (StringValue)super.findById("dsl.getPeriodsOfYear", cycleName);
            return Integer.parseInt(value.getStrValue());
        }catch(Exception e){
            // e.printStackTrace();
            return 0;
        }*/
        return 0;
    }

	public int getPeriodsOfYear(Map params) {
        /*try{
            String value = (String)super.findById("dsl.getPeriodsOfYear1", params);
            return Integer.parseInt(value.getStrValue());
        }catch(Exception e){
            // e.printStackTrace();
            return 0;
        }*/
	    return 0;
	}

}
