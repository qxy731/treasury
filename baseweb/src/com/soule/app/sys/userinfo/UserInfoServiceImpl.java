package com.soule.app.sys.userinfo;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;
/**
 * 登陆用户信息装载业务操作
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    private final static Log logger = LogFactory.getLog(UserInfoServiceImpl.class);
    @Autowired
    private IDefaultService sDefault;
    /**
     */
    public UserInfoStaffOut staff(UserInfoStaffIn in)
        throws ServiceException {
        UserInfoStaffOut out = new UserInfoStaffOut();
        try {
            UserInfoStaffPo staff = (UserInfoStaffPo) sDefault.getIbatisMediator().findById("position.getStaff", in.getStaffId());
            out.setStaff(staff);
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002,e.getMessage());
        }
        return out;
    }
    /**
     */
    public UserInfoOperUnitOut operUnit(UserInfoOperUnitIn in)
        throws ServiceException {
        UserInfoOperUnitOut out = new UserInfoOperUnitOut();
        try {
            List ret = sDefault.getIbatisMediator().find("position.getPositions", in.getStaffId());
            out.setPosition(ret);
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002,e.getMessage());
        }
        return out;
    }

}
