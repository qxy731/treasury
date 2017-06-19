package com.soule.app.report;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;


/**
 * 
 */
@Namespace("/setdep") 
public class BizRateAction extends ReportBaseAction {
    private static final long serialVersionUID = 1L;
    private List list;
    private String resultStr;
    @Autowired
	IDefaultService defService;
    
    public void doInit(){
    	super.doInit();
    	try {
			list=defService.getIbatisMediator().find("rpt_setdep.prdCode1", null);
			
			resultStr=JSONArray.fromObject(list).toString();
		} catch (DbAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

}
