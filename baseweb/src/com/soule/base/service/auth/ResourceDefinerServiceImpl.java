package com.soule.base.service.auth;


import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class ResourceDefinerServiceImpl implements FilterInvocationSecurityMetadataSource {
	private String uncheckSource;

	public String getUncheckSource()
	{
		return uncheckSource;
	}

	public void setUncheckSource(String uncheckSource)
	{
		this.uncheckSource = uncheckSource;
	}

	private boolean isUncheckResource(String url)
	{
//		String[] uncheckResource = getUncheckSource().split(",");
//		for (int i = 0; i < uncheckResource.length; i++)
//			if (url.endsWith(uncheckResource[i].trim()))
//				return true;
//		return false;
	    String[] uncheckResource = getUncheckSource().split(",");
	    for (int i = 0; i < uncheckResource.length; i++) {
	        String res = uncheckResource[i].trim();
	        if (url.endsWith(res) || url.startsWith(res)){
	            return true;
	        }
	    }
	    return false;
	}

	public boolean supports(Class clazz)
	{
		return clazz.isAssignableFrom(FilterInvocation.class);
	}

	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();
		if (url.lastIndexOf('?') >= 0){
			//if(!url.startsWith("/jsp/")){	//“/jsp/”目录下的业务jsp不进行参数过滤
			if(url.startsWith("/index.jsp")
				||url.startsWith("/main.jsp")
				||url.startsWith("/randomImage.jsp")
				||url.startsWith("/uncaughtException.jsp")){	
				url = url.substring(0, url.lastIndexOf('?'));
			}
		}
		if (isUncheckResource(url))
			return null;

		ArrayList<ConfigAttribute> confDef = new ArrayList<ConfigAttribute>();
		while (url.length() > 0) {
			confDef.add(new SecurityConfig(url));
			if (url.lastIndexOf('/') >= 0)
				url = url.substring(0, url.lastIndexOf('/'));
			else
				url = "";
		}

		HttpServletRequest req = ((FilterInvocation) object).getHttpRequest();
		Map parameterMap = req.getParameterMap();
		for (Iterator it = parameterMap.keySet().iterator(); it.hasNext();) {
			String key = it.next().toString();
			confDef.add(new SecurityConfig(key + "="
				+ parameterMap.get(key)));
		}
		return confDef;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

}
