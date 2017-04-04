package com.soule.app.common.selectunit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.comm.StringUtils;
import com.soule.comm.tools.AppUtils;

/**
 * 机构查询业务操作
 */
@Service
public class SelectUnitServiceImpl implements ISelectUnitService {
	@Autowired
    private IDefaultService sDefault;

    public SelectUnitOut queryUnit(SelectUnitIn in) throws ServiceException {
        SelectUnitOut out = new SelectUnitOut();

        try {
            if (in == null) {
                in = new SelectUnitIn();
            }

            in.setOrgId(AppUtils.getLogonUserInfo().getOperUnitId());
            if ((in.getUnitId() == null || in.getUnitId().equals("null") || in.getUnitId().equals(""))
                    && StringUtils.isEmpty(in.getSuperUnitId()) && StringUtils.isEmpty(in.getUnitName())
                    && StringUtils.isEmpty(in.getType())) {
                List ret = sDefault.getIbatisMediator().find("sysmgr.getUnitInfoByNull", in);
                out.setUnitVOList(ret);
            } else if (!StringUtils.isEmpty(in.getSuperUnitId())) {
                List ret = sDefault.getIbatisMediator().find("sysmgr.getUnitInfo", in);
                out.setUnitVOList(ret);
            } else if ((!StringUtils.isEmpty(in.getUnitId()) || !StringUtils.isEmpty(in.getUnitName()))
                    && StringUtils.isEmpty(in.getType())) {
                List ret = sDefault.getIbatisMediator().find("sysmgr.getUnitInfoByUnitId", in);
                out.setUnitVOList(ret);
            } else if (!StringUtils.isEmpty(in.getType())) {
                if (StringUtils.isEmpty(in.getUnitId()) && StringUtils.isEmpty(in.getUnitName())) {
                    in.setUnitId(AppUtils.getLogonUserInfo().getOperUnitId());
                    List ret = sDefault.getIbatisMediator().find("sysmgr.getUnitInfoByUnitId", in);
                    out.setUnitVOList(ret);
                } else {
                    List ret = sDefault.getIbatisMediator().find("sysmgr.getUnitInfoByUnitIdNext", in);
                    out.setUnitVOList(ret);
                }
            }
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (Exception ex) {
            ex.printStackTrace();
            AppUtils.setResult(out, MsgConstants.E0001);
        }
        return out;
    }

}
