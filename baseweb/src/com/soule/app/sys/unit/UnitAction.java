package com.soule.app.sys.unit;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soule.MsgConstants;
import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;
import com.soule.base.service.user.IUser;
import com.soule.comm.enu.ReturnCodeType;
import com.soule.comm.enu.YesNoFlag;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.StringUtil;


/**
 * @author peter
 * @version Create On：2011-12-6
 */
@Namespace("/sys")
@Results( { 
	@Result(name = "success", location = "/jsp/sysmgr/unit/unit.jsp"),
	@Result(name = "detail", location = "/jsp/sysmgr/unit/unitDetail.jsp"),
	@Result(name = "edit", location = "/jsp/sysmgr/unit/unitEdit.jsp")
})

public class UnitAction extends BaseAction {
    private static final long serialVersionUID = 2618154545560168244L;
    private final Log log = LogFactory.getLog(UnitAction.class);
    @Autowired
    private IUnitService unitService ;

    /**
     * 查询组织信息 输入参数 
     */
    private UnitQueryIn queryIn;
    private UnitQueryOut queryOut;
    private String unitLevel;
    /**
     * 查询组织信息
     */
    @JSON(serialize=false)
    public UnitQueryIn getQueryIn() {
        return this.queryIn;
    }
    /**
     * 查询组织信息
     */
    public void setQueryIn(UnitQueryIn in) {
        this.queryIn = in;
    }
    
    
    public void doInit() {
    }
    public String query() {
    	UnitQueryIn in = queryIn;
        try{
        	in.getInputHead().setPageNo(page);
        	in.getInputHead().setPageSize(pagesize);
        	UnitQueryOut result = unitService.query(in);
            rows = result.getUnit();
            total=(int)result.getResultHead().getTotal();
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    
    public String queryByUnitId() {
        try{
        	this.unit = unitService.getOneUnitById(queryIn.getUnitId());
        	setRetMsg(AppUtils.getMessage(MsgConstants.I0000));
        	setRetCode(MsgConstants.I0005);
        	unitLevel = unit.getUnitLevel();
        }catch(Exception e) {
        	setRetMsg(AppUtils.getMessage(MsgConstants.E0014));
            setRetCode(MsgConstants.E0014);
        }
        return JSON;
    }
    
    /**
     * 删除子菜单
     * 
     * @return
     */
    public String deleteUnitModel() {
        try {
            int flag = unitService.deleteUnit(queryIn.getUnitId());
            if (flag == 1) {
            	setRetMsg(AppUtils.getMessage(MsgConstants.I0002));
                setRetCode(MsgConstants.I0002);
            } else {
            	setRetMsg(AppUtils.getMessage(MsgConstants.E0013));
                setRetCode(MsgConstants.E0013);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            setRetMsg(AppUtils.getMessage(MsgConstants.E0013));
            setRetCode(MsgConstants.E0013);
        }
        return JSON;
    }

    /**
     * 增加新组织
     * 
     * @return
     */
    public String addUnitModel() {
        try {
            Map map = new HashMap();
            map.put("unitId", queryIn.getUnitId());
            map.put("unitName", queryIn.getUnitName());
            map.put("superUnitId", queryIn.getSuperUnitId());
            map.put("unitAddress", queryIn.getUnitAddress());
            map.put("unitLevel", queryIn.getUnitLevel());
            map.put("unitStatus",queryIn.getUnitStatus());
            
            map.put("settUnitId",queryIn.getSettUnitId());
            map.put("mgrUnitId",queryIn.getMgrUnitId());
            map.put("startDate",queryIn.getStartDate());
            map.put("endDate",queryIn.getEndDate());
            
            IUser user = (IUser) AppUtils.getLogonUserInfo().getUser();
            map.put("createUser", user.getUserID());
            map.put("createTime", new Date());
            map.put("leafFlag",YesNoFlag.YES.getValue());
            int flag = unitService.insertUnit(map);
            if (flag == 1) {
                setRetMsg(AppUtils.getMessage(MsgConstants.I0001));
                setRetCode(MsgConstants.I0001);
            } else {
                setRetMsg(AppUtils.getMessage(MsgConstants.E0011));
                setRetCode(MsgConstants.E0011);
            }
        } catch (Exception e) {
            setRetMsg(AppUtils.getMessage(MsgConstants.E0011));
            setRetCode(MsgConstants.E0011);
            log.error(e.getMessage());
        }
        return JSON;
    }

    /**
     * 保存数据
     * 
     * @return
     */
    public String update() {
        try {
            Map paramMap = new HashMap();
            paramMap.put("unitId", queryIn.getUnitId());
            paramMap.put("unitName", queryIn.getUnitName());
            paramMap.put("unitAddress", queryIn.getUnitAddress());
            //paramMap.put("unitKind", queryIn.getUnitKind());
            IUser user = (IUser) AppUtils.getLogonUserInfo().getUser();
            paramMap.put("lastUpdUser", user.getUserID());
            paramMap.put("lastUpdTime", new Date());
            paramMap.put("unitStatus", queryIn.getUnitStatus());
            paramMap.put("superUnitId", queryIn.getSuperUnitId());
            paramMap.put("settUnitId",queryIn.getSettUnitId());
            paramMap.put("mgrUnitId",queryIn.getMgrUnitId());
            paramMap.put("startDate",queryIn.getStartDate());
            paramMap.put("endDate",queryIn.getEndDate());
            int flag = unitService.updateUnit(paramMap);
            if (flag == 1) {
                setRetMsg(AppUtils.getMessage(MsgConstants.I0003));
                setRetCode(MsgConstants.I0003);
            } else {
                setRetMsg(AppUtils.getMessage(MsgConstants.E0012));
                setRetCode(MsgConstants.E0012);
            }
        } catch (Exception e) {
            setRetMsg(AppUtils.getMessage(MsgConstants.E0012));
            setRetCode(MsgConstants.E0012);
            log.error("",e);
        }
        return JSON;
    }
    
    
    @RequestMapping(value="/FormattingTest")
    public String openUnitById() {
        try {
            this.unit = (UnitPo) unitService.getUnitById(queryIn.getUnitId());
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        if ("1".equals(opType)) {
            return "edit";
        } else {
            return "detail";
        }
    }
    
  //查看或编辑类型
    private String opType;

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }
    private UnitPo unit;

    public UnitPo getUnit() {
        return unit;
    }

    public void setUnit(UnitPo unit) {
        this.unit = unit;
    }
	public String getUnitLevel() {
		return unitLevel;
	}
	public void setUnitLevel(String unitLevel) {
		this.unitLevel = unitLevel;
	}
}
