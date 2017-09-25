package com.soule.app.pfm.tm.report.bankfundsflow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReportTargetPo2Map {
	
	public static Map<String,Object> po2Map(List<ReportTargetPo> list) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(null!=list && list.size()>0){
			for(ReportTargetPo po :list){
				map.put(po.getTarCode(), po.getTarValue());
			}
		}
		return map;
		
	}

}
