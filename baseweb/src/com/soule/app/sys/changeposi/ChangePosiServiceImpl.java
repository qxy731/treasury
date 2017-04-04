package com.soule.app.sys.changeposi;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.soule.MsgConstants;
import com.soule.app.sys.userinfo.UserInfoPositionPo;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.comm.enu.YesNoFlag;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.ContextUtil;
import com.soule.comm.tools.StringUtil;
/**
 * 职位切换业务操作
 */
@Service
public class ChangePosiServiceImpl implements IChangePosiService {

    private final static Log logger = LogFactory.getLog(ChangePosiServiceImpl.class);
    // 数据库通用操作x
    @Autowired
    private IDefaultService sDefault;

    /**
     * 
     */
    public ChangePosiChangeOut change(ChangePosiChangeIn in)
        throws ServiceException {
        ChangePosiChangeOut out = new ChangePosiChangeOut();
        if (StringUtil.isEmpty(in.getStaffId())) {
            AppUtils.setResult(out, MsgConstants.E0001,"staffId");
            return out;
        }
        try {
            List<UserInfoPositionPo> list = sDefault.getIbatisMediator().find("position.getPositions", in.getStaffId());
            UserInfoPositionPo po = AppUtils.selectDefaultPosition(list);
            if (po == null || !po.getPosiId().equals(in.getPosiId())) {
                if (!StringUtil.isEmpty(in.getPosiId())) {
                    sDefault.getIbatisMediator().update("position.updateDefaultPosi", in);
                }
            }
            if (po != null) {
                sDefault.getIbatisMediator().update("position.updateNoDefaultPosi", po);
            }
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002, e.getMessage());
        }
        return out;
    }

}
