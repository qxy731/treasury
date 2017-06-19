package com.soule.crm.pfm.param.ast;

import com.soule.crm.pfm.datahelper.DataHelper;
import com.soule.crm.pfm.dsl.ast.TakeHolder;
import com.soule.crm.pfm.dsl.ast.Type;
/**
 * ȡϵͳ����
 * @author MXB
 *
 */
public class ParameterTakeholder implements TakeHolder {

    public static final String SHARP = "&";
    public static final int PARAMETER = 99;
    private static Type PARAMTER_TYPE = new Parameter();
    
    public Type getReturnedType() {
        return PARAMTER_TYPE;
    }

    public String getStart() {
        return SHARP;
    }

    public boolean isValid(String token) {
        return getParameterId(token) == null ? false : true;
    }

    public String translate(String token) {
        return getParameterId(token);
    }
    
    public int getType() {
        return PARAMETER;
    }
    /**
     * �ж��Ƿ�Ϊ�Զ������
     * @param parameterId
     * @return
     */
    public boolean isParamDefine(String parameterId) {
        return DataHelper.getInstance().isParamDefine(parameterId);
    }

    private String getParameterId(String parameterName) {
        //ȥ���൱����
        String parameter = parameterName.replace("\"", "");
        return DataHelper.getInstance().getParameterId(parameter);
    }
}
