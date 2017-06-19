package com.soule.crm.pub.dataimport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.crm.tools.ErrorInfoPo;

/*import com.neusoft.base.media.DbAccessException;
import com.neusoft.base.service.IDefaultService;
import com.neusoft.comm.tools.StringUtil;
import com.neusoft.crm.tools.ErrorInfoPo;*/

@Service("check_prdcode")
public class PrdCodeCheck implements IDataImportHandler {

    @Autowired
    IDefaultService sDefault;
	@Override
	public void handle(List<ErrorInfoPo> errors, List<HashMap> modellist, List<HashMap> datas) {
		ArrayList<String> pks = new ArrayList<String>();
		for (int m = 0; m < modellist.size(); m++) {
			HashMap hm = (HashMap) modellist.get(m);
			if (hm.get("TITLE_NAME").equals("PRD_CODE")) {
				pks.add(hm.get("TITLE_NAME").toString());
			}
		}
		if (pks.size() > 0) {
			for (int i = 0; i < datas.size(); i++) {
				HashMap hm = datas.get(i);
				for (int j = 0; j < pks.size(); j++) {
					String ss = (String) hm.get(pks.get(j));
//					String str = StringUtil.strFormat(16, ss);
					String name = pks.get(j);
					try {
                        List list = sDefault.getIbatisMediator().find("dataimport.checkPrdCode",ss);
                        if(list.size() == 0){
                            addMsg(errors,i,"产品代码","值[" +ss+"]未找到");
                        }
                    } catch (DbAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
				}
			}
		}
	}
     private void addMsg(List errors, Integer j, String titleCnName, String info) {
        ErrorInfoPo ep = new ErrorInfoPo();
        ep.setRowid(j + 2);
        ep.setColumnName(titleCnName);
        ep.setErrorInfo(info);
        errors.add(ep);
     }
}
