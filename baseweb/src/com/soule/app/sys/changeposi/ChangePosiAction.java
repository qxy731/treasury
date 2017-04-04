package com.soule.app.sys.changeposi;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.app.sys.userinfo.UserInfoPositionPo;
import com.soule.base.action.BaseAction;
import com.soule.base.service.ILogonUserInfo;
import com.soule.base.service.ServiceResult;
import com.soule.comm.tools.AppUtils;

/**
 * 职位切换维护模块表现层处理类
 */
@Namespace("/sys")
public class ChangePosiAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Log logger = LogFactory.getLog(ChangePosiAction.class);
	@Autowired
    private IChangePosiService service;

    /**
     * 修改缺省职位 输入参数 
     */
    private ChangePosiChangeIn changeIn; //= new ChangePosiChangeIn();
    private List positions;
    private String posiId;

    public void doInit() {
        positions = new ArrayList();
        ILogonUserInfo logonInfo = AppUtils.getLogonUserInfo();
        if (logonInfo != null) {
            List<UserInfoPositionPo> ps = logonInfo.getPositionPo();
            positions.addAll(ps);
            UserInfoPositionPo defaultPosition = AppUtils.selectDefaultPosition(ps);
            if (defaultPosition!= null) {
                posiId = defaultPosition.getPosiId();
            }
        }
    }
    public String change() {
        ChangePosiChangeIn in = changeIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            ChangePosiChangeOut result = service.change(in);
            ServiceResult head = result.getResultHead();
            // TODO
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }

    /**
     * 修改缺省职位
     */
    @JSON(serialize=false)
    public ChangePosiChangeIn getChangeIn() {
        return this.changeIn;
    }
    /**
     * 修改缺省职位
     */
    public void setChangeIn(ChangePosiChangeIn in) {
        this.changeIn = in;
    }
    public String getPosiId() {
        return posiId;
    }
    public void setPosiId(String posiId) {
        this.posiId = posiId;
    }
    public List getPositions() {
        return positions;
    }
    public void setPositions(List positions) {
        this.positions = positions;
    }

}
