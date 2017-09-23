package com.soule.app.pfm.tm.report.bankfundsflow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReportTargetPo2Map {
	
	public static Map po2Map(List<ReportTargetPo> list) {
		
		Map map = new HashMap();
		if(null!=list && list.size()>0){
			for(ReportTargetPo po :list){
				map.put(po.getTarCode(), po.getTarValue());
			}
		}
		return map;
		
	}

}
