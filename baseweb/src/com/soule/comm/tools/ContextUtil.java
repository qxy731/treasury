package com.soule.comm.tools;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;

import com.opensymphony.xwork2.spring.SpringObjectFactory;
import com.soule.comm.enu.ScopeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContextUtil {
    private static final String NULL_BEAN = "null";

    private static Log log = LogFactory.getLog(ContextUtil.class);
    /**
     * spring的context对象
     */
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        ContextUtil.applicationContext = applicationContext;
    }

    /**
     * 获得spring容器管理的bean对象实例
     */
    public static Object getBean(String beanId) {
        if (beanId == null || NULL_BEAN.equalsIgnoreCase(beanId)) {
            return null;
        }
        return getBeanByBeanIdOrClass(beanId, null);
    }

    public static List<Object> getBeansByClass(Class targetClass) {
        List<Object> ret = new ArrayList<Object>();
        if (applicationContext == null || targetClass == null)
            return ret;

        Map beans = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, targetClass, true, true);

        if (beans.size() == 0)
            log.info("No bean of type " + targetClass.toString() + " found.");

        return new ArrayList<Object>(beans.values());
    }

    /**
     * 根据beanId和bean类型从applicationContext中取得bean, 若applicationContext中存在beanId对应的bean则返回此bean
     * 否则返回applicationContext中类型为clazz的bean中的第一个
     * 
     * @param beanId
     * @param clazz
     * @return 返回结果对象
     */
    public static Object getBeanByBeanIdOrClass(String beanId, Class clazz) {
        if (applicationContext == null)
            return null;

        if (NULL_BEAN.equalsIgnoreCase(beanId))
            return null;

        if (beanId != null)
            if (applicationContext.containsBean(beanId))
                return applicationContext.getBean(beanId);

        List l = getBeansByClass(clazz);
        if (l.size() > 0)
            return l.get(0);
        return null;
    }

    public static void put(String key, Object value, ScopeType scope) {
        ContextHolder contextHolder = (ContextHolder) getBean(scope.name() + "Context");
        if (contextHolder != null)
            contextHolder.put(key, value);
    }

    public static Object get(String key, ScopeType scope) {
        ContextHolder contextHolder = (ContextHolder) getBean(scope.name() + "Context");
        if (contextHolder != null)
            return contextHolder.get(key);
        return null;
    }

    public static void clear(ScopeType scope) {
        ContextHolder contextHolder = (ContextHolder) getBean(scope.name() + "Context");
        if (contextHolder != null)
            contextHolder.clear();
    }
    
    public static ContextHolder getSessionContext() {
        return (ContextHolder) getBean(ScopeType.Session + "Context");
    }

    public static ContextHolder getRequestContext() {
        return (ContextHolder) getBean(ScopeType.Request + "Context");
    }
    public static ContextHolder getAppContext() {
        return (ContextHolder) getBean(ScopeType.Application + "Context");
    }
    private static SpringObjectFactory factory;
    public static Object getActionBean(Log logger,String className){
    	Object action = ObjectUtil.toObject(logger, className);
    	if (factory == null) {
    		factory = new SpringObjectFactory();
    		factory.setApplicationContext(getApplicationContext());
    	}
    	if (action != null) {
    		factory.autoWireBean(action);
    	}
        return action;
    }
}
