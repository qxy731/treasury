package com.soule.web.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.components.Component;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;
import com.soule.comm.tools.ContextUtil;

import org.apache.commons.beanutils.MethodUtils;

/**
 * 页面初始化
 * 权限控制
 * @author wuwei
 */
public class PageTag extends ComponentTagSupport {

    private static final long serialVersionUID = 1L;
    private static final Log logger = LogFactory.getLog(PageTag.class);
    String action;
    String initMethod = "doInit";
    private String content = "";

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        Object o = ContextUtil.getActionBean(logger, action);
        if (o == null) {
            content = "Class [" + action + "] instanced failed!";
        }
        else {
            try {
                if (o instanceof ServletRequestAware) {
                    ServletRequestAware sra = (ServletRequestAware)o;
                    sra.setServletRequest(req);
                }
                if (o instanceof ServletResponseAware) {
                    ServletResponseAware sra = (ServletResponseAware)o;
                    sra.setServletResponse(res);
                }
            }
            catch(Exception e){
            }
            try {
                MethodUtils.invokeExactMethod(o, "init", null);
                MethodUtils.invokeExactMethod(o, initMethod, null);
                stack.getRoot().push(o);
            } catch (NoSuchMethodException e) {
                logger.warn("Method [" + initMethod + "] not exist in class [" + action + "]");
            } catch (Exception e) {
                logger.warn("init", e);
            }
        }
        return new Component(stack);
    }
    public String getBody() {
        return content ;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public void setInitMethod(String initmethod) {
        this.initMethod = initmethod;
    }
}
