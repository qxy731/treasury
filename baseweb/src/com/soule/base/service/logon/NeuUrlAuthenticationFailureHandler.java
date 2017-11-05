package com.soule.base.service.logon;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import com.soule.app.sys.auditlog.AuditLogInsertIn;
import com.soule.app.sys.auditlog.AuditLogLogPo;
import com.soule.app.sys.auditlog.IAuditLogService;
import com.soule.base.service.ServiceException;
import com.soule.base.service.keygen.IKeyGenerator;
import com.soule.comm.enu.BizType;
import com.soule.comm.enu.ExecuteResult;
import com.soule.comm.enu.FunctionType;

/**
 * <tt>AuthenticationFailureHandler</tt> which performs a redirect to the value
 * of the {@link #setDefaultFailureUrl defaultFailureUrl} property when the
 * <tt>onAuthenticationFailure</tt> method is called. If the property has not
 * been set it will send a 401 response to the client, with the error message
 * from the <tt>AuthenticationException</tt> which caused the failure.
 * <p>
 * If the <tt>forwardToDestination</tt> parameter is set, a
 * <tt>RequestDispatcher.forward</tt> call will be made to the destination
 * instead of a redirect.
 * 
 * @author Denong <Shania>
 * @since 3.0
 * 
 */
public class NeuUrlAuthenticationFailureHandler implements
		AuthenticationFailureHandler {
	private static Log logger = LogFactory
			.getLog(NeuUrlAuthenticationFailureHandler.class);

	private String defaultFailureUrl;
	private boolean forwardToDestination = false;
	private boolean allowSessionCreation = true;
	@Autowired
	private IUserLogonDaoService logDao;
	@Autowired
	private IAuditLogService sAuditLog;
	@Autowired
	private IKeyGenerator keyg;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public final static String TRY_MAX_COUNT = "tryMaxCount";

	public NeuUrlAuthenticationFailureHandler() {
	}

	public NeuUrlAuthenticationFailureHandler(String defaultFailureUrl) {
		setDefaultFailureUrl(defaultFailureUrl);
	}

	/**
	 * Performs the redirect or forward to the {@code defaultFailureUrl} if set,
	 * otherwise returns a 401 error code.
	 * <p>
	 * If redirecting or forwarding, {@code saveException} will be called to
	 * cache the exception for use in the target view.
	 */
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		Authentication au = exception.getAuthentication();
		String params = "f";
		if (au != null) {
			LogonInfoPo lpo;
			try {
				String exceptionName = exception.getClass().getName();
				lpo = logDao.updateLogonFailed(au.getName());
				if (lpo != null) {
					lpo.setLastLogonIp(request.getRemoteAddr());
				}
				saveLogonFailed(lpo, exception);
				if(exceptionName.equals("org.springframework.security.authentication.BadCredentialsException")){
					params = "i";
				}else if(exceptionName.equals("org.springframework.security.authentication.CredentialsExpiredException")){
					params = "e";
				}else if(exceptionName.equals("org.springframework.security.core.userdetails.UsernameNotFoundException")){
					params = "f";
				}else if(exceptionName.equals("org.springframework.security.authentication.LockedException")){
					params = "l";
				}else if(exceptionName.equals("org.springframework.security.authentication.DisabledException")){
					params = "d";
				}else if(exceptionName.equals("org.springframework.security.authentication.AccountExpiredException")){
					params = "a";
				}
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}

		// forward when failure
		if (defaultFailureUrl == null) {
			logger.debug("No failure URL set, sending 401 Unauthorized error");

			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
					"Authentication Failed: " + exception.getMessage());
		} else {
			saveException(request, exception);

			if (forwardToDestination) {
				logger.debug("Forwarding to " + defaultFailureUrl);
				request.getRequestDispatcher(defaultFailureUrl+"?l="+params).forward(
						request, response);
			} else {
				logger.debug("Redirecting to " + defaultFailureUrl);
				redirectStrategy.sendRedirect(request, response,
						defaultFailureUrl+"?l="+params);
			}
		}
	}

	private void saveLogonFailed(LogonInfoPo po, AuthenticationException f)
			throws ServiceException {
		if (po == null) {
			return;
		}
		AuditLogInsertIn in = new AuditLogInsertIn();
		AuditLogLogPo alog = new AuditLogLogPo();
		in.setLog(alog);
		alog.setOperCode("user_logon");
		alog.setOperName("员工登陆");
		alog.setBizType(BizType.LOGON.toString());
		alog.setExecResult(ExecuteResult.FAIL.getValue());
		alog.setId(String.valueOf(keyg.getSeqence("SYS_LOG_AUDIT")));
		alog.setIpAddr(po.getLastLogonIp());
		alog.setOperStaffid(po.getStaffId());
		alog.setFuncType(FunctionType.NORMAL.getValue());
		alog.setLogDetail(f.getClass().getName() + ":" + f.getMessage());
		sAuditLog.insert(in);
	}

	/**
	 * Caches the {@code AuthenticationException} for use in view rendering.
	 * <p>
	 * If {@code forwardToDestination} is set to true, request scope will be
	 * used, otherwise it will attempt to store the exception in the session. If
	 * there is no session and {@code allowSessionCreation} is {@code true} a
	 * session will be created. Otherwise the exception will not be stored.
	 */
	protected final void saveException(HttpServletRequest request,
			AuthenticationException exception) {
		if (forwardToDestination) {
			request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
					exception);
		} else {
			HttpSession session = request.getSession(false);

			if (session != null || allowSessionCreation) {
				request.getSession().setAttribute(
						WebAttributes.AUTHENTICATION_EXCEPTION, exception);
			}
		}
	}

	/**
	 * The URL which will be used as the failure destination.
	 * 
	 * @param defaultFailureUrl
	 *            the failure URL, for example "/loginFailed.jsp".
	 */
	public void setDefaultFailureUrl(String defaultFailureUrl) {
		Assert.isTrue(UrlUtils.isValidRedirectUrl(defaultFailureUrl), "'"
				+ defaultFailureUrl + "' is not a valid redirect URL");
		this.defaultFailureUrl = defaultFailureUrl;
	}

	protected boolean isUseForward() {
		return forwardToDestination;
	}
	
	public void setForwardToDestination(boolean forwardToDestination) {  
        this.forwardToDestination = forwardToDestination;  
    } 

	/**
	 * If set to <tt>true</tt>, performs a forward to the failure destination
	 * URL instead of a redirect. Defaults to <tt>false</tt>.
	 */
	public void setUseForward(boolean forwardToDestination) {
		this.forwardToDestination = forwardToDestination;
	}

	/**
	 * Allows overriding of the behaviour when redirecting to a target URL.
	 */
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	protected boolean isAllowSessionCreation() {
		return allowSessionCreation;
	}

	public void setAllowSessionCreation(boolean allowSessionCreation) {
		this.allowSessionCreation = allowSessionCreation;
	}
}
