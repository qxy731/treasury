package com.soule.crm.pub.dataimport;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soule.comm.po.IEnumItem;
import com.soule.comm.po.IEnumType;
import com.soule.comm.tools.EnumTypeUtil;
import com.soule.crm.tools.ErrorInfoPo;


@Service("cenum")
public class ConvertEnum implements IDataImportColumnHandler {

    @Override
    public Object handle(List errors,int row, HashMap model, String value) {
        String format = (String)model.get("FORMAT");
        if (format == null) {
            addMsg(errors,row,(String)model.get("TITLE_NAME"),"枚举值ID未定义");
            return value;
        }
        IEnumType x = EnumTypeUtil.getEnumType(format);
        if (x== null) {
            addMsg(errors,row,(String)model.get("TITLE_NAME"),"枚举值ID定义[" +x+"]未找到");
        }
        else {
            List items = x.getItems();
            for (int i = 0 ; i < items.size(); i++) {
                IEnumItem item = (IEnumItem)items.get(i);
                if (item.getValue().equals(value)) {
                    return item.getKey();
                }
            }
        }
        addMsg(errors,row,(String)model.get("TITLE_NAME"),"枚举值[" + format + "]未找到对应ID[" + value + "]");
        return value;
    }
    private void addMsg(List errors, Integer j, String titleCnName, String info) {
        ErrorInfoPo ep = new ErrorInfoPo();
        ep.setRowid(j +1);
        ep.setColumnName(titleCnName);
        ep.setErrorInfo(info);
        errors.add(ep);
    }
}
