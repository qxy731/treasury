package com.soule.app.sys.bizline.blass;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输出参数业务线人员分配:业务线中添加查询结果中全部人员
 */
public class BlassInsertAllIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String bizTypeId;
    private String bizValueId;
    private String staffId;
    private String staffName;
    private String owerUnitId;
    private Integer staffLevel;
    private String sex;


    public String getBizTypeId() {
        return bizTypeId;
    }

    public void setBizTypeId(String bizTypeId) {
        this.bizTypeId= bizTypeId;
    }

    public String getBizValueId() {
        return bizValueId;
    }

    public void setBizValueId(String bizValueId) {
        this.bizValueId= bizValueId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId= staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName= staffName;
    }

    public String getOwerUnitId() {
        return owerUnitId;
    }

    public void setOwerUnitId(String owerUnitId) {
        this.owerUnitId= owerUnitId;
    }

    public Integer getStaffLevel() {
        return staffLevel;
    }

    public void setStaffLevel(Integer staffLevel) {
        this.staffLevel= staffLevel;
    }

    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}