package com.soule.app.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.app.sys.unit.ISelcUnitService;
import com.soule.app.sys.unit.UnitPo;
import com.soule.base.action.BaseAction;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;

@Namespace("/sys")
public class UnitCommonAction extends BaseAction {
    private static final long serialVersionUID = 5228597648520439036L;
    private final static Log logger = LogFactory.getLog(UnitCommonAction.class);
    @Autowired
    private ISelcUnitService selcUnitService;
    private String unitId;
    private String initStr;

    public void doInit() {
        StringBuffer sb = new StringBuffer();
        try {
            getOrgStr(sb);
        } catch (ServiceException e) {
            logger.error("SERVICE", e);
        } catch (DbAccessException e) {
            logger.error("DB", e);
        }
        if (sb.length() == 0) {
            initStr = "[\"text\":\"获取数据失败\"]";
        }
    }

    private void getOrgStr(StringBuffer sb) throws DbAccessException, ServiceException {
        unitId = request.getParameter("unitid");
        if (unitId == null || unitId.length() == 0) {
            unitId = AppUtils.getLogonUserInfo().getOperUnitId();
        }
        UnitPo unit = selcUnitService.getUnitById(unitId);
        if (unit != null) {
            sb.append("[");
            sb.append("{\"text\":\"").append(unit.getUnitName()).append("\"").append(",").append("\"attribute\"")
                    .append(":").append("[\"").append(unit.getUnitId()).append("\",\"").append(unit.getUnitName())
                    .append("\"]");
            if ("0".equals(unit.getLeafFlag())) {
                List<UnitPo> unitList = selcUnitService.getUnitByParent(unitId);
                sb.append(",\"children\":[");
                for (UnitPo u : unitList) {
                    sb.append("{\"text\":\"").append(u.getUnitName()).append("\"").append(",").append(
                            "\"attribute\"").append(":").append("[\"").append(u.getUnitId()).append("\",\"")
                            .append(u.getUnitName()).append("\"]").append(
                                    "0".equals(u.getLeafFlag()) ? ",\"children\":[]" : "");
                    joinIsExpand(u, sb);
                    sb.append("}").append(",");
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append("]");
            }
            sb.append(",\"unitType\":").append("\"").append(unit.getLeafFlag()).append("\"");
            sb.append(",\"unitParent\":").append(getUnitParentsIncludeOwnerToStr())
                    .append(",\"isParent\":\"true\"");
            sb.append("}]");
        }
        initStr = sb.toString();
    }

    @Override
    public String execute() throws Exception {
        PrintWriter out = null;
        StringBuffer sb = new StringBuffer();
        try {
            out = response.getWriter();
        } catch (IOException e) {
            return null;
        }
        getOrgStr(sb);
        out.println(sb.toString());
        out.close();
        return null;
    }

    public String queryChildUnit() throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            return null;
        }
        List<UnitPo> unitList = selcUnitService.getUnitByParent(unitId);
        StringBuffer sb = new StringBuffer();
        if (unitList != null && unitList.size() > 0) {
            sb.append("[");
            for (UnitPo unit : unitList) {
                // sb.append("{\"text\":\"").append(unit.getUnitName()+"[<a href=javascript:getData('"+unit.getUnitId()+"','"+unit.getUnitName()+"')><font color=red>选取</font></a>]").append("\"")
                sb.append("{\"text\":\"").append(unit.getUnitName()).append("\"").append(",").append("\"attribute\"")
                        .append(":").append("[\"").append(unit.getUnitId()).append("\",\"").append(unit.getUnitName())
                        .append("\"]").append("0".equals(unit.getLeafFlag()) ? ",\"children\":[]" : "");
                joinIsExpand(unit, sb);
                sb.append("}").append(",");
            }
            if (sb.length() > 1) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("]");
        }
        out.println(sb.toString());
        out.close();
        return null;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    private void joinIsExpand(UnitPo unit, StringBuffer sb) {
        if ("0".equals(unit.getLeafFlag())) {
            sb.append(",\"isexpand\":false");
        } else {
            sb.append(",\"isexpand\":true");
        }
        sb.append(",\"unitType\":").append("\"").append(unit.getLeafFlag()).append("\"");
    }

    public String getUnitParentsIncludeOwnerToStr() {
        List<UnitPo> unitParentList = null;
        StringBuffer sb = new StringBuffer();
        try {
            unitParentList = selcUnitService.getUnitParentsIncludeOwner(unitId);
        } catch (DbAccessException e) {
            e.printStackTrace();
        }
        if (unitParentList != null && unitParentList.size() > 1) {
            sb.append("[");
            for (UnitPo unit : unitParentList) {
                sb.append("{\"unitId\":").append("\"").append(unit.getUnitId()).append("\",\"unitName\":\"").append(
                        unit.getUnitName()).append("\"},");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
        } else {
            sb.append("[]");
        }
        return sb.toString();
    }

    public String getInitStr() {
        return initStr;
    }

    public void setInitStr(String initStr) {
        this.initStr = initStr;
    }

}
