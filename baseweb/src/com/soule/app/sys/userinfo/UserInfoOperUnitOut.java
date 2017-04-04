package com.soule.app.sys.userinfo;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数登陆用户信息装载:取职位信息
 */
public class UserInfoOperUnitOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<UserInfoPositionPo> position;


    public List<UserInfoPositionPo> getPosition() {
        return position;
    }

    public void setPosition(List<UserInfoPositionPo> output) {
        this.position = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}