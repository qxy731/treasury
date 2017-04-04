package com.soule.app.common.selectunit;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONObject;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;
import com.soule.comm.tools.StringUtil;

/**
 * 查询模块表现层处理类
 */
@Namespace("/sys")
public class SelectUnitAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Autowired
    private ISelectUnitService service ;

    private SelectUnitIn queryIn;

    private SelectUnitIn queryUnitIn;

    private String resultUnit = "{}";

    public SelectUnitIn getQueryIn() {
        return queryIn;
    }

    public void setQueryIn(SelectUnitIn queryIn) {
        this.queryIn = queryIn;
    }

    public String getResultUnit() {
        return resultUnit;
    }

    public void setResultUnit(String resultUnit) {
        this.resultUnit = resultUnit;
    }

    public SelectUnitIn getQueryUnitIn() {
        return queryUnitIn;
    }

    public void setQueryUnitIn(SelectUnitIn queryUnitIn) {
        this.queryUnitIn = queryUnitIn;
    }

    public void doInit() {
        String unitId = request.getParameter("unitId");
        String type = request.getParameter("type");
        if (!StringUtil.isEmpty(unitId)) {
            queryUnitIn = new SelectUnitIn();
            queryUnitIn.setUnitId(unitId);
            queryUnitIn.setType(type);
            resultUnit = JSONObject.fromObject(queryUnitIn).toString();
        }
    }

    public String queryUnit() {
        SelectUnitIn unitIn = queryUnitIn;
        try {
            SelectUnitOut result = service.queryUnit(unitIn);
            ServiceResult head = result.getResultHead();
            if (head.isSuccess()) {
                this.rows = result.getUnitVOList();
            }
            // TODO
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        } catch (Exception ex) {
            handleError(ex);
        }
        return JSON;
    }
}
