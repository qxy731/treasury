package com.soule.app.sys.unit;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数人员:查询人员
 */
public class UnitQueryIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String unitId;
    private String unitName;
    private String superUnitId;
    private String unitLevel;
   // private String unitKind;
    private String superUnitName;
    private String unitStatus;// 组织状态
    private Integer unitIndex;// 组织索引
    private String unitAddress;// 组织路径
    private String settUnitId;
    private String mgrUnitId;
    private String startDate;
    private String endDate;
    private String createUser;
    private Date createTime;
    private String lastUpdUser;
    private Date lastUpdTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private String leafFlag;

    public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getSuperUnitId() {
		return superUnitId;
	}

	public void setSuperUnitId(String superUnitId) {
		this.superUnitId = superUnitId;
	}

	public String getUnitLevel() {
		return unitLevel;
	}

	public void setUnitLevel(String unitLevel) {
		this.unitLevel = unitLevel;
	}


	public String getSuperUnitName() {
		return superUnitName;
	}

	public void setSuperUnitName(String superUnitName) {
		this.superUnitName = superUnitName;
	}

	public String getUnitStatus() {
		return unitStatus;
	}

	public void setUnitStatus(String unitStatus) {
		this.unitStatus = unitStatus;
	}

	public Integer getUnitIndex() {
		return unitIndex;
	}

	public void setUnitIndex(Integer unitIndex) {
		this.unitIndex = unitIndex;
	}


	public String getUnitAddress() {
		return unitAddress;
	}

	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}

	public String getSettUnitId() {
		return settUnitId;
	}

	public void setSettUnitId(String settUnitId) {
		this.settUnitId = settUnitId;
	}

	public String getMgrUnitId() {
		return mgrUnitId;
	}

	public void setMgrUnitId(String mgrUnitId) {
		this.mgrUnitId = mgrUnitId;
	}
	
	

	/*public Date getStartDate() {
		
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}*/

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdUser() {
		return lastUpdUser;
	}

	public void setLastUpdUser(String lastUpdUser) {
		this.lastUpdUser = lastUpdUser;
	}

	public Date getLastUpdTime() {
		return lastUpdTime;
	}

	public void setLastUpdTime(Date lastUpdTime) {
		this.lastUpdTime = lastUpdTime;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	public String getExt4() {
		return ext4;
	}

	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}

	public String getExt5() {
		return ext5;
	}

	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}

	public String getLeafFlag() {
		return leafFlag;
	}

	public void setLeafFlag(String leafFlag) {
		this.leafFlag = leafFlag;
	}

	public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}