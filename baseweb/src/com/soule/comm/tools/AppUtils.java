package com.soule.comm.tools;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.MethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.app.sys.auditlog.AuditLogInsertIn;
import com.soule.app.sys.auditlog.AuditLogLogPo;
import com.soule.app.sys.auditlog.IAuditLogService;
import com.soule.app.sys.userinfo.UserInfoPositionPo;
import com.soule.base.service.ILogonUserInfo;
import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceException;
import com.soule.base.service.ServiceResult;
import com.soule.base.service.keygen.IKeyGenerator;
import com.soule.comm.CommConstants;
import com.soule.comm.enu.BizType;
import com.soule.comm.enu.ExecuteResult;
import com.soule.comm.enu.FunctionType;
import com.soule.comm.enu.ScopeType;
import com.soule.comm.enu.YesNoFlag;

/**
 * 定义应用的公共方法
 * 
 * @author wei.wu
 *
 */
@Service
public class AppUtils {

	@Autowired
	private IAuditLogService sAuditLog;
	@Autowired
	private IKeyGenerator keyg;

    /**
     * 获取登陆信息
     * @param context
     * @return
     */
    public static ILogonUserInfo getLogonUserInfo() {
        ILogonUserInfo user = (ILogonUserInfo) ContextUtil.get(CommConstants.LOGON_USER_INFO, ScopeType.Session);
        return user;
    }

    public static UserInfoPositionPo selectDefaultPosition(List<UserInfoPositionPo> posAss) {
        if ( posAss == null || posAss.size() == 0) {
            return null;
        }
        for (UserInfoPositionPo role:posAss) {
            YesNoFlag flag = YesNoFlag.getInstance(role.getDefFlag());
            if (flag == YesNoFlag.YES) {
                return role;
            }
        }
        return null;
    }

    /**
     * 返回资源文件信息
     * @param messageResource
     * @param key
     * @return
     */    
    public static String getMessage(String messageResource,String key){
    	//java.util.Locale local = java.util.Locale.getDefault();
    	//ResourceBundle resourceBundle = ResourceBundle.getBundle(messageResource,local);
    	ResourceBundle resourceBundle = ResourceBundle.getBundle("config/msg/message_zh_CN");
    	return resourceBundle.getString(key);    	
    }
    
    /**
     * 返回资源文件信息
     * @param messageResource
     * @param key
     * @return
     */    
    public static String getMessage(String key) {
        //java.util.Locale local = java.util.Locale.getDefault();
        try {
            //ResourceBundle resourceBundle = ResourceBundle.getBundle("config/msg/MessagesResources", local);
        	ResourceBundle resourceBundle = ResourceBundle.getBundle("config/msg/MessagesResources_zh_CN");
            return resourceBundle.getString(key);
        } catch (java.util.MissingResourceException e) {
            return null;
        }
    }

    public static void setResult(IServiceResult result,String retCode, Object ... param) throws ServiceException {
        ServiceResult head = result.getResultHead();
        head.setRetCode(retCode);
        String origMsg = getMessage(retCode);
        if (origMsg != null) {
            String mf = MessageFormat.format(origMsg, param);
            head.setRetMsg(mf);
        }
        else {
            head.setRetMsg(retCode);
        }
        if (retCode != null && retCode.startsWith("E")) {
            throw new ServiceException(retCode,head.getRetMsg());
        }
    }

    /**
     * 设置记录的创建人，创建时间
     * @param npo
     */
    public static void initRecordStatus(Serializable npo) {
        if (npo != null) {
            ILogonUserInfo luser = getLogonUserInfo();
            if (luser != null){
                String staffId = luser.getLogonInfo().getStaffId();
                try {
                    MethodUtils.invokeExactMethod(npo, "setCreateUser", staffId);
                } catch (Exception e) {
                    System.err.println("class[" + npo.getClass().getName() + "] no setCreateUser method");
                }
                String createOrg = luser.getOperUnitId();
                try {
                    MethodUtils.invokeExactMethod(npo, "setCreateOrg", createOrg);
                } catch (Exception e) {
                    System.err.println("class[" + npo.getClass().getName() + "] no setCreateUser method");
                }
            }
            try {
                MethodUtils.invokeExactMethod(npo, "setCreateTime", new Date());
            } catch (Exception e) {
                System.err.println("class[" + npo.getClass().getName() + "] no setCreateTime method");
            }
        }
    }
    /**
     * 设置记录的修改人，修改人时间
     * @param npo
     */
    public static void setRecordStatus(Serializable npo) {
        if (npo != null) {
            ILogonUserInfo luser = getLogonUserInfo();
            if (luser != null){
                String staffId = luser.getLogonInfo().getStaffId();
                try {
                    MethodUtils.invokeExactMethod(npo, "setLastUpdUser", staffId);
                } catch (Exception e) {
                    System.err.println("class[" + npo.getClass().getName() + "] no setCreateUser method");
                }
            }
            try {
                MethodUtils.invokeExactMethod(npo, "setLastUpdTime", new Date());
            } catch (Exception e) {
                System.err.println("class[" + npo.getClass().getName() + "] no setCreateTime method");
            }
        }
    }

    /**
     * 记录审计日志
     * 
     * @param operCode 操作代码
     * @param operName 操作名称
     * @param logDetail 详细日志
     * @param funcType 操作类型
     * @param result 执行结果
     * @param bizType 业务类型
     * @return 
     * @throws ServiceException
     */
    public String saveAuditLog(String operCode,String operName,String logDetail,BizType bizType,FunctionType funcType,
            ExecuteResult result) throws ServiceException {
        AuditLogInsertIn in = new AuditLogInsertIn();
        String key = String.valueOf(keyg.getSeqence("SYS_LOG_AUDIT"));
        AuditLogLogPo alog = new AuditLogLogPo();
        in.setLog(alog);
        alog.setLogDetail(logDetail);
        alog.setOperCode(operCode);
        alog.setOperName(operName);
        alog.setBizType(bizType.toString());
        alog.setExecResult(result.getValue());
        alog.setId(String.valueOf(keyg.getSeqence("SYS_LOG_AUDIT")));
        ILogonUserInfo user = getLogonUserInfo();
        alog.setIpAddr(user.getIpAddress());
        alog.setOperStaffid(user.getUser().getUserID());
        alog.setOperStaffName(user.getUser().getUserName());
        alog.setRoleId(user.getRoleId());
        alog.setRoleName(user.getRoleName());
        alog.setFuncType(funcType.getValue());
        sAuditLog.insert(in);
        return key;
    }
    
    /**
     * 记录审计日志
     * 
     * @param operCode 操作代码
     * @param operName 操作名称
     * @param logDetail 详细日志
     * @param funcType 操作类型
     * @param result 执行结果
     * @param bizType 业务类型
     * @throws ServiceException
     */
    public void saveAuditLog(String operCode,String operName,BizType bizType) throws ServiceException {
        saveAuditLog(operCode,operName,null,bizType,FunctionType.NORMAL,ExecuteResult.SUCCESS);
    }
    
    /**
     * 记录审计日志
     * 
     * @param operCode 操作代码
     * @param operName 操作名称
     * @param logDetail 详细日志
     * @param funcType 操作类型
     * @param result 执行结果
     * @param bizType 业务类型
     * @throws ServiceException
     */
    public String saveAuditLog(String operCode,String operName,BizType bizType, FunctionType fun, ExecuteResult exec) throws ServiceException {
        return saveAuditLog(operCode,operName,null,bizType,fun,exec);
    }

}

