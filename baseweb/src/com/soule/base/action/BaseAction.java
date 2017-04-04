package com.soule.base.action;

import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import com.opensymphony.xwork2.ActionSupport;
import com.soule.MsgConstants;
import com.soule.app.sys.funcauth.FuncAuthQueryIn;
import com.soule.app.sys.funcauth.FuncAuthQueryOut;
import com.soule.app.sys.funcauth.FuncAuthRecordPo;
import com.soule.app.sys.funcauth.IFuncAuthService;
import com.soule.base.service.ILogonUserInfo;
import com.soule.base.service.ServiceException;
import com.soule.base.service.auth.AdminAuthority;
import com.soule.base.service.auth.ResourceAuthority;
import com.soule.comm.CommConstants;
import com.soule.comm.enu.ResourceType;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.AuthUtil;

/**
 * @author peter
 * @version Create On：2011-11-29
 */
@Results( { @Result(name = "json", type = "json",params={"ignoreHierarchy","false"})})
public class BaseAction extends ActionSupport implements ServletResponseAware, ServletRequestAware,ServletContextAware {
    private static final long serialVersionUID = 1L;
    protected static final String JSON = "json";

    private static Log logger = LogFactory.getLog(BaseAction.class);

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected ServletContext application;

    protected String retMsg = "";
    protected String retCode = "";
    protected long total = 0;
    protected int page = 1;
    protected int pagesize = 10;
    protected String sortname = "";
    protected String sortorder = "";
    @Autowired
    private IFuncAuthService sFuncAuth;
    
    /**
     * 查询结果
     */
    protected List rows;

    protected void handleError(Exception e) {
        if (e instanceof ServiceException) {
            logger.error("ACTION", e);
            ServiceException se = (ServiceException) e;
            this.retCode = se.getErrorCode();
            this.retMsg = se.getErrorMsg();
        } else {
            logger.error("ACTION", e);
            this.retCode = MsgConstants.E0000;
            this.retMsg = "Error:" + e.getMessage();
        }
    }

    /**
     * 页面中tag page 会自动调用
     */
    public void init() {
        ILogonUserInfo user = AppUtils.getLogonUserInfo();
        if (user == null) {
            return;
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        String jsp = request.getRequestURI();
        int idx = jsp.indexOf('?');
        String url = jsp;
        if (idx > 0) {
            url = jsp.substring(0, idx);
        }
        url = url.substring(request.getContextPath().length());
        // 根据jsp获得功能点定义
        FuncAuthQueryIn in = new FuncAuthQueryIn();
        in.setJspPath(url);
        try {
            FuncAuthQueryOut result = sFuncAuth.query(in);
            // 功能资源点的设置
            List<FuncAuthRecordPo> funcs = result.getRecord();
            Collection<GrantedAuthority> auths = user.getAuthorities();
            for (FuncAuthRecordPo fid : funcs) {
                if (vote(auths, fid.getFuncId())) {
                    AuthUtil.addAuth(request, fid.getFuncCode());
                }
            }
        } catch (Exception e) {
            logger.error("ACTION",e);
        }
    }

    private boolean vote(Collection<GrantedAuthority> auths, String fid) {
        if (auths == null) {
            return false;
        }
        for (GrantedAuthority ga : auths) {
            if (ga instanceof AdminAuthority) {
                return true;
            }
            if (!(ga instanceof ResourceAuthority)) {
                continue;
            }
            ResourceAuthority resAuth = (ResourceAuthority) ga;
            if (resAuth.getResType() == ResourceType.FUNCTION) {
                if (resAuth.getAuthority().equals(fid) ) {
                    if (resAuth.getRunFlag() != null && resAuth.getRunFlag().booleanValue()) {
                        return true;
                    }
                }
            }
            continue;
        }
        return false;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public void doInit() {
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
        String c = request.getContextPath();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute(CommConstants.CONTEXT_PATH_FIELD,c);
        }
    }

    public void setServletContext(ServletContext application) {
        this.application = application;
    }

    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    public String getSortorder() {
        return sortorder;
    }

    public void setSortorder(String sortorder) {
        this.sortorder = sortorder;
    }

}
