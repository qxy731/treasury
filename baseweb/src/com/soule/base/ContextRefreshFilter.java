package com.soule.base;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.soule.comm.enu.ScopeType;
import com.soule.comm.tools.ContextUtil;

public class ContextRefreshFilter implements Filter {

	public void destroy()
	{
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
		throws IOException, ServletException
	{
		if (arg0 instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest) arg0;
			ContextUtil.put(ScopeType.Request + "Context", req, ScopeType.Request);
			//若httpSession对象的生命周期和Spring scope session不一致可能会导致数据出问题
			ContextUtil.put(ScopeType.Session + "Context", req.getSession(), ScopeType.Session);
		}
		arg2.doFilter(arg0, arg1);
	}

	public void init(FilterConfig arg0) throws ServletException
	{
	}

}
