package com.soule.crm.pub.dataimport;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soule.crm.tools.ErrorInfoPo;

/*import com.neusoft.crm.tools.ErrorInfoPo;
*/
@Service("pkcheck")
public class PrimaryKeyChecker implements IDataImportHandler {

    @Override
    public void handle(List<ErrorInfoPo> errors, List<HashMap> modellist, List<HashMap> datas) {
        String columnDisName = "";
        ArrayList<String> pks = new ArrayList<String>();
        for (int m = 0; m < modellist.size(); m++) {
            HashMap hm = (HashMap) modellist.get(m);
            if (hm.get("PK_FLAG").equals("1")) {
                columnDisName = columnDisName + "_" + hm.get("TITLE_CNNAME").toString();
                pks.add(hm.get("TITLE_NAME").toString());
                continue;
            }
        }
        if (pks.size() > 0) {
            HashSet<Object> mapcust = new HashSet<Object>();// 验证重复数据
            for (int n = 0; n < datas.size(); n++) {
                HashMap one = (HashMap) datas.get(n);
                StringBuilder pkval = new StringBuilder();
                for (int k = 0; k < pks.size(); k++) {
                	//
//                	if(pks.get(k).equals("BIZ_DATE")){
//                		Date date=new Date();
//                		one.put(pks.get(k), date);
//                	}
                	//
                    pkval.append(one.get(pks.get(k)));
                    if (k < pks.size() -1 ) {
                        pkval.append("_");
                    }
                }
                if (mapcust.contains(pkval.toString())) {
                    ErrorInfoPo epo = new ErrorInfoPo();
                    epo.setRowid(n + 2);
                    epo.setColumnName(columnDisName.substring(1));
                    epo.setErrorInfo("主键重复[" +pkval + "]");
                    errors.add(epo);
                }
                mapcust.add(pkval.toString());
            }
        }
    }

}
