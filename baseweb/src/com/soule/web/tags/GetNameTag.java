package com.soule.web.tags;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.components.Property;
import org.apache.struts2.components.Select;
import org.apache.struts2.views.jsp.PropertyTag;

import com.soule.app.sys.gname.IGetNameService;
import com.soule.comm.tools.ContextUtil;

/**
 * @see Select
 */
public class GetNameTag extends PropertyTag {

    private static final long serialVersionUID = 1L;
    private final static Log logger = LogFactory.getLog(GetNameTag.class);

    private String value;

    private String service = "StaffGetNameService";

    @Override
    protected void populateParams() {
        IGetNameService getNameService = (IGetNameService) ContextUtil.getBean(service);

        Property prop = ((Property) component);
        String str = "";
        // 先去取出值栈中的值放入temp中
        String finalValue = (String) prop.getStack().findValue(value, java.lang.String.class);
        try {
            // 判断是否为空,如果为空,将原来的value赋给temp
            if (finalValue == null || finalValue.length() == 0) {
                finalValue = value;
            }

            // 数据库ID字段只有16位
            if (finalValue.length() > 16) {
                logger.error("ID.LENGTH IS TOO LONG!");
                str = finalValue;
            } else {
                if (getNameService == null) {
                    logger.error("SERVICE NOT FOUND");
                    str = finalValue;
                }
                else {
                    str = getNameService.queryResult(finalValue);
                }
            }
        } catch (Exception e) {
            logger.error("SERVICE", e);
            str = finalValue;
        }
        prop.setDefault(str);
        prop.setValue(str);
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
