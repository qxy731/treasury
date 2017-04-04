package com.soule.base.service.logon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import com.soule.app.sys.bizline.blass.BlassQueryByStaffIn;
import com.soule.app.sys.bizline.blass.BlassQueryByStaffOut;
import com.soule.app.sys.bizline.blass.IBlassService;
import com.soule.app.sys.userinfo.IUserInfoService;
import com.soule.app.sys.userinfo.UserInfoOperUnitIn;
import com.soule.app.sys.userinfo.UserInfoOperUnitOut;
import com.soule.app.sys.userinfo.UserInfoPositionPo;
import com.soule.app.sys.userinfo.UserInfoStaffIn;
import com.soule.app.sys.userinfo.UserInfoStaffOut;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.ILogonUserInfo;
import com.soule.base.service.ServiceException;
import com.soule.base.service.auth.AdminAuthority;
import com.soule.base.service.auth.IAllowSetAuthentication;
import com.soule.base.service.auth.IArcGrantService;
import com.soule.base.service.auth.RoleAssignPo;
import com.soule.base.service.user.DefaultUser;
import com.soule.comm.CommConstants;
import com.soule.comm.enu.BizType;
import com.soule.comm.enu.ScopeType;
import com.soule.comm.enu.YesNoFlag;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.ContextUtil;
import com.soule.comm.tools.ParamsUtil;


/**
 * An authentication success strategy which can make use of the {@link DefaultSavedRequest} which may have been stored in
 * the session by the {@link ExceptionTranslationFilter}. When such a request is intercepted and requires authentication,
 * the request data is stored to record the original destination before the authentication process commenced, and to
 * allow the request to be reconstructed when a redirect to the same URL occurs. This class is responsible for
 * performing the redirect to the original URL if appropriate.
 * <p>
 * Following a successful authentication, it decides on the redirect destination, based on the following scenarios:
 * <ul>
 * <li>
 * If the <tt>alwaysUseDefaultTargetUrl</tt> property is set to true, the <tt>defaultTargetUrl</tt>
 * will be used for the destination. Any <tt>DefaultSavedRequest</tt> stored in the session will be
 * removed.
 * </li>
 * <li>
 * If the <tt>targetUrlParameter</tt> has been set on the request, the value will be used as the destination.
 * Any <tt>DefaultSavedRequest</tt> will again be removed.
 * </li>
 * <li>
 * If a {@link DefaultSavedRequest} is found in the <tt>RequestCache</tt> (as set by the {@link ExceptionTranslationFilter} to
 * record the original destination before the authentication process commenced), a redirect will be performed to the
 * Url of that original destination. The <tt>DefaultSavedRequest</tt> object will remain cached and be picked up
 * when the redirected request is received
 * (See {@link org.springframework.security.web.savedrequest.SavedRequestAwareWrapper SavedRequestAwareWrapper}).
 * </li>
 * <li>
 * If no <tt>DefaultSavedRequest</tt> is found, it will delegate to the base class.
 * </li>
 * </ul>
 */
public class NeuUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private static Log logger = LogFactory.getLog(UserDetailsServiceImpl.class);

    private RequestCache requestCache = new HttpSessionRequestCache();
	@Autowired
	private IUserLogonDaoService logDao;
	@Autowired
	private IBlassService blservice;
	@Autowired
	private IUserInfoService uis;
	@Autowired
	private IArcGrantService grantService;
	@Autowired
	private ParamsUtil paramsUtil;
	@Autowired
	private AppUtils appUtils;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
       SavedRequest savedRequest = requestCache.getRequest(request, response);
       //ILogonUserInfo user = (ILogonUserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       HttpSession session = request.getSession(false);
       if(session!=null){
    	   session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
    	   session.removeAttribute("validationCode");
       }
       try {
	       ILogonUserInfo userInfo = (ILogonUserInfo)authentication.getPrincipal();
	       //成功后才装载权限信息
	       //装载用户信息
	       UserInfoStaffIn in = new UserInfoStaffIn();
	       in.setStaffId(userInfo.getLogonInfo().getStaffId());
	       UserInfoStaffOut staffOut = uis.staff(in);
	       if (staffOut.getResultHead().isSuccess()) {
	           userInfo.setUser(new DefaultUser(staffOut.getStaff()));
	       }
	       //装载职位信息
	       UserInfoOperUnitIn uiou = new UserInfoOperUnitIn();
	       uiou.setStaffId(userInfo.getLogonInfo().getStaffId());
	       UserInfoOperUnitOut posiOut = uis.operUnit(uiou);
	       if (posiOut.getResultHead().isSuccess()) {
	           userInfo.setPositionPo(posiOut.getPosition());
	       }
	       //装载业务线信息
	       BlassQueryByStaffIn bqin = new BlassQueryByStaffIn();
	       bqin.setStaffId(in.getStaffId());
	       BlassQueryByStaffOut bqout = blservice.queryByStaff(bqin);
	       if (bqout.getResultHead().isSuccess()) {
	           userInfo.setBizLines(bqout.getBizass());
	       }
	       //装载角色信息
	       List<RoleAssignPo> roleAss = grantService.getUserRoleAssign(userInfo.getLogonInfo().getStaffId());
	       userInfo.setAssignRole(roleAss);
	       //装载权限信息
	       List<GrantedAuthority> granted = new ArrayList<GrantedAuthority>();
	       boolean isAdmin = grantService.isAdministrator(userInfo.getLogonInfo().getLogonId());
	       if (isAdmin) {
	           granted.add(new AdminAuthority());
	           userInfo.setRoleId("");
	           userInfo.setRoleName("超级管理员");
	       } else {
	           YesNoFlag yesNoFlag = YesNoFlag.getInstance(paramsUtil.getParamValueByParamIdAndRank("ROLE_MIXED","SYS"));
	           request.getSession().setAttribute("_ROLE_MIXED_",Boolean.valueOf(yesNoFlag == YesNoFlag.YES));
	           if (YesNoFlag.NO == yesNoFlag) {
	               UserInfoPositionPo defaultPosition = AppUtils.selectDefaultPosition(userInfo.getPositionPo());
	               if (defaultPosition == null) {
	                   for (RoleAssignPo role:roleAss) {
	                       addResourceAuth(grantService, granted, role.getRoleId());
	                   }
	                   if (userInfo.getPositionPo() != null && userInfo.getPositionPo().size() >0 ) {
	                       defaultPosition = userInfo.getPositionPo().get(0);
	                   }
	               }
	               else{
	                   addResourceAuth(grantService, granted, defaultPosition.getRoleId());
	               }
	               if (defaultPosition != null) {
	                   userInfo.setOperUnitId(defaultPosition.getOperUnitid());
	                   userInfo.setOperUnitName(defaultPosition.getOperUnitname());
	                   userInfo.setRoleId(defaultPosition.getRoleId());
	                   userInfo.setRoleName(defaultPosition.getRoleName());
	               }
	           }
	           else {
	               for (RoleAssignPo role:roleAss) {
	                   addResourceAuth(grantService, granted, role.getRoleId());
	               }
	           }
	       }
	
	       if ( userInfo.getOperUnitId() == null) {
	           userInfo.setOperUnitId(userInfo.getUser().getOwnerUnitId());
	           userInfo.setOperUnitName(userInfo.getUser().getOwnerUnitName());
	       }
	       userInfo.setAuthorities(granted);
	       IAllowSetAuthentication allow = (IAllowSetAuthentication)authentication;
	       allow.setAuthorities(granted);
	
	       userInfo.setIpAddress(request.getRemoteAddr());
	       ContextUtil.put(CommConstants.LOGON_USER_INFO, userInfo,ScopeType.Session);
	       request.getSession().setAttribute(CommConstants.LOGON_USER_INFO,userInfo);
	       try {
				appUtils.saveAuditLog("user_logon", "员工登陆", BizType.LOGON);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
	       //失败登陆次数清零,记录最后登陆ip
	       userInfo.getLogonInfo().setLastLogonIp(request.getRemoteAddr());
	       userInfo.getLogonInfo().setLastLogonTime(new Date());
	       try {
				logDao.updateLogonStatus(userInfo.getLogonInfo());
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
	        if (savedRequest == null) {
	            super.onAuthenticationSuccess(request, response, authentication);
	            return;
	        }
	
	        if (isAlwaysUseDefaultTargetUrl() || StringUtils.hasText(request.getParameter(getTargetUrlParameter()))) {
	            requestCache.removeRequest(request, response);
	            super.onAuthenticationSuccess(request, response, authentication);
	            return;
	        }
	       } catch (Exception e1) {
	   		// TODO Auto-generated catch block
	   		e1.printStackTrace();
	   	}
	        clearAuthenticationAttributes(request);

        // Use the DefaultSavedRequest URL
        String targetUrl = savedRequest.getRedirectUrl();
        logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
    private void addResourceAuth(IArcGrantService grantService, List<GrantedAuthority> granted, String roleId)  {
        List<GrantedAuthority> rolegrants;
		try {
			rolegrants = grantService.getGrantOfRole(roleId);
	        for (GrantedAuthority one:rolegrants){
	            granted.add(one);
	        }
		} catch (DbAccessException e) {
			e.printStackTrace();
		}
    }
    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
}
