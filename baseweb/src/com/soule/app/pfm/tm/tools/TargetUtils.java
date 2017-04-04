package com.soule.app.pfm.tm.tools;

import java.util.List;

import com.soule.app.pfm.tm.BaseTar;
import com.soule.base.po.BizLinePo;
import com.soule.base.service.ILogonUserInfo;
import com.soule.comm.tools.AppUtils;

public class TargetUtils {
	
	
	public static BizLinePo getBusinessLine(){
		ILogonUserInfo  userInfo =  AppUtils.getLogonUserInfo();
    	List<BizLinePo> list = userInfo.getBizLineValue(BaseTar.PFM_BUSINESS_LINE);
    	if(list!=null&&list.size()>0){
	    	BizLinePo po = (BizLinePo)list.get(0);
	    	return po;
    	}
    	return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
