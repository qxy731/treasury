package com.soule.crm.pub.dataimport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soule.crm.tools.ErrorInfoPo;

/*import com.neusoft.crm.tools.ErrorInfoPo;*/

@Service("biz_type13")
public class InsertBizType13 implements IDataImportHandler {

    @Override
    public void handle(List<ErrorInfoPo> errors, List<HashMap> modellist, List<HashMap> datas) {
    	ArrayList<String> pks = new ArrayList<String>();
        for (int m = 0; m < modellist.size(); m++) {
            HashMap hm = (HashMap) modellist.get(m);
            if (hm.get("TITLE_NAME").equals("BIZ_TYPE")) {
                pks.add(hm.get("TITLE_NAME").toString());
            }
        }
    if(pks.size()>0){
    	for(int i=0;i<datas.size();i++){
    		HashMap hm=datas.get(i);
    		for(int j=0;j<pks.size();j++){
    			String ss="1";
    			hm.put(pks.get(j), ss);
    		}
    	
    	}
    }
    }

}
