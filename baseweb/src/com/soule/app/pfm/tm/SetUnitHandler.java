package com.soule.app.pfm.tm;

import java.util.List;
import java.util.Map;

import com.soule.comm.file.IValueHandler;
import com.soule.comm.file.config.Field;

public class SetUnitHandler implements IValueHandler{

    public String special(Map context, List<Field> list, String[] values, int i) {
        String createOrg=(String)context.get("createOrg");
        return createOrg;
    }

}
