package com.soule.base;

import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.soule.comm.CommConstants;
import com.soule.comm.tools.ContextUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * 环境初始化类，以便获取spring容器中的环境信息供系统调用spring中注册的bean
 */
public class ContextLoaderListener extends org.springframework.web.context.ContextLoaderListener {
    protected final static Log logger = LogFactory.getLog(ContextLoaderListener.class);

    private static ApplicationContext context;

    public ContextLoaderListener() {
        super();
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static void setContext(ApplicationContext ctx) {
        context = ctx;
    }

    public void contextInitialized(ServletContextEvent context) {
        initLog("/config/log/log4j_config.xml");
        super.contextInitialized(context);
        //ServletContext c =  context.getServletContext().getContext("/");
        //context.getServletContext().setAttribute(CommConstants.CONTEXT_PATH_FIELD, "/baseweb");
        setContext(WebApplicationContextUtils.getRequiredWebApplicationContext(context.getServletContext()));
        ContextUtil.setApplicationContext(ContextLoaderListener.context);
        /** 应用平台系统初始化 */
        AppInit.init(context);
    }

    private synchronized void initLog(String logConfigPath) {
        System.setProperty("org.apache.commons.logging.LogFactory", "org.apache.commons.logging.impl.LogFactoryImpl");
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Log4JLogger");

        URL path = this.getClass().getResource(logConfigPath);
        System.setProperty("log4j.configuration", path.getFile());
        DOMConfigurator.configure(path);
    }

    /**
     * 服务器停止时，自动执行方法
     * 
     * @param servlet
     *            event
     */
    public void contextDestroyed(ServletContextEvent servlet) {
        super.contextDestroyed(servlet);
        // try {
        // AppInit.destroy();
        // BaseInit.destroy();
        // logger.info("Release base data object successfully!");
        // } catch (Exception ex) {
        // // logger.error("Relase bese data object failed：{}",
        // // ex.getMessage());
        // // TODO FXLI
        // }
    }
}
