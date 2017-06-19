package com.soule.crm.pub.dataimport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soule.crm.tools.ErrorInfoPo;

/*import com.neusoft.crm.tools.ErrorInfoPo;
*/
@Service("todate")
public class ToDate implements IDataImportColumnHandler {

    @Override
    public Object handle(List errors,int row, HashMap model, String value) {
        String format = (String)model.get("FORMAT");
        if (format == null) {
            format = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
        	if(value!=null && !value.equals("")){
        		 return sdf.parse(value);
        	}
        } catch (ParseException e) {
            addMsg(errors,row,(String)model.get("TITLE_NAME"),"日期正确格式为[" + format + "],数据[" + value + "]");
        }
        return null;
    }
    private void addMsg(List errors, Integer j, String titleCnName, String info) {
        ErrorInfoPo ep = new ErrorInfoPo();
        ep.setRowid(j +1);
        ep.setColumnName(titleCnName);
        ep.setErrorInfo(info);
        errors.add(ep);
    }
}
