package com.soule.app.sys.unit;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UnitPo implements Serializable {
    private static final long serialVersionUID = 8366881532617420996L;
    private String unitId;
    private String unitName;
    private String superUnitId;
    private String unitLevel;
    private String unitKind;
    private String superUnitName;
	private String unitStatus; // 组织状态
    private Integer unitIndex;// 组织索引
    private String unitPath;// 组织路径
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
    
    private List<UnitPo> subUnitList;

    private UnitPo parent;

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

    public String getUnitKind() {
        return unitKind;
    }

    public void setUnitKind(String unitKind) {
        this.unitKind = unitKind;
    }

    public String getUnitStatus() {
        return unitStatus;
    }

    public void setStaffStatus(String unitStatus) {
        this.unitStatus = unitStatus;
    }

    public Integer getUnitIndex() {
        return unitIndex;
    }

    public void setUnitIndex(Integer unitIndex) {
        this.unitIndex = unitIndex;
    }

    public String getUnitPath() {
        return unitPath;
    }

    public void setUnitPath(String unitPath) {
        this.unitPath = unitPath;
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
    public String getSuperUnitName() {
        return superUnitName;
    }
    public void setSuperUnitName(String superUnitName) {
        this.superUnitName = superUnitName;
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

    public void setLeafFlag(String leaf) {
        this.leafFlag = leaf;
    }

    public List<UnitPo> getSubUnitList() {
        return subUnitList;
    }

    public void setSubUnitList(List<UnitPo> subUnitList) {
        this.subUnitList = subUnitList;
    }

    public UnitPo getParent() {
        return parent;
    }

    public void setParent(UnitPo parent) {
        this.parent = parent;
    }
    
    public void setUnitStatus(String unitStatus) {
		this.unitStatus = unitStatus;
	}
}
