package com.soule.crm.pub.dataimport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soule.crm.tools.ErrorInfoPo;

/*import com.neusoft.crm.tools.ErrorInfoPo;
*/
@Service("strwipe")
public class StringWipeSign implements IDataImportHandler {

	@Override
	public void handle(List<ErrorInfoPo> errors, List<HashMap> modellist, List<HashMap> datas) {
		ArrayList<String> pks = new ArrayList<String>();
		for (int m = 0; m < modellist.size(); m++) {
			HashMap hm = (HashMap) modellist.get(m);
			if (hm.get("TITLE_CNNAME").equals("分行期权费") || hm.get("TITLE_CNNAME").equals("分行利润") || hm.get("TITLE_CNNAME").equals("客户利润") || hm.get("TITLE_CNNAME").equals("客户期权费")||hm.get("TITLE_CNNAME").equals("客户利润折美元")) {
				pks.add(hm.get("TITLE_NAME").toString());
			}
		}
		if (pks.size() > 0) {
			for (int i = 0; i < datas.size(); i++) {
				HashMap hm = datas.get(i);
				for (int j = 0; j < pks.size(); j++) {
					String ss = (String) hm.get(pks.get(j));
					String yy=ss.replace(",", "");
					hm.put(pks.get(j), yy);
				}
				datas.set(i, hm);
			}
		}
	}

}
