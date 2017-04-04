package com.soule.web.tags;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;
import com.soule.comm.CommConstants;
import com.soule.comm.po.IEnumType;
import com.soule.comm.tools.EnumTypeUtil;

/**
 * 枚举参数值的导入
 * 一设置到request中
 * 二输出到页面
 * @author wuwei
 */
public class EnumsTag extends ComponentTagSupport {

    private static final long serialVersionUID = 1L;
    String scope = "request";
    String keys;
    private String content;

    @SuppressWarnings("unchecked")
    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        StringBuilder sb = new StringBuilder();

        sb.append("<script type=\"text/javascript\">");
        sb.append("var _enum_params = {");

        Map<String, IEnumType> enums;
        if ("application".equals(scope)) {
            enums = (Map<String, IEnumType>) ServletActionContext.getServletContext().getAttribute(
                    CommConstants.ENUMS_MAP);
            if (enums == null) {
                enums = new HashMap<String, IEnumType>();
                ServletActionContext.getServletContext().setAttribute(CommConstants.ENUMS_MAP, enums);
            }
        } else if ("session".equals(scope)) {
            enums = (Map<String, IEnumType>) req.getSession().getAttribute(CommConstants.ENUMS_MAP);
            if (enums == null) {
                enums = new HashMap<String, IEnumType>();
                req.getSession().setAttribute(CommConstants.ENUMS_MAP, enums);
            }
        } else {
            enums = (Map<String, IEnumType>) req.getAttribute(CommConstants.ENUMS_MAP);
            if (enums == null) {
                enums = new HashMap<String, IEnumType>();
                req.setAttribute(CommConstants.ENUMS_MAP, enums);
            }
        }
        enumlist(enums, sb);
        sb.append("</script>");
        content = sb.toString();
        return new Component(stack);
    }

    private void enumlist(Map<String, IEnumType> enums, StringBuilder sb) {
        String[] keyArr = keys.split(",");
        for (String key : keyArr) {
            IEnumType enumType = EnumTypeUtil.getEnumType(key);
            if (enumType != null) {
                enums.put(enumType.getKey(), enumType);
                sb.append(enumType.getKey()).append(":");
                sb.append(enumType.toJsString());
                sb.append(",");
            }
        }
        if (sb.charAt(sb.length()-1) == ',') {
            sb.setLength(sb.length()-1);
        }
        sb.append("};");
    }

    public String getBody() {
        return content;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

}
