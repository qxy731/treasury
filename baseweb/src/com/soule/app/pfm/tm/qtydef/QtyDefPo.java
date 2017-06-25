package com.soule.app.pfm.tm.qtydef;

import java.io.Serializable;
import java.util.Date;

import com.soule.app.pfm.tm.BaseTar;
public class QtyDefPo extends BaseTar implements Serializable{
	private static final long serialVersionUID = 8221962141764051470L;
	//指标状态（1：已启用;2：已停用）
	public static String STATUS_DEL = "2";
	public static String STATUS_INPUT = "1";
	//是否存在基数指标
	public static String BSFLAG_YES = "1";
	public static String BSFLAG_NO = "0";
	//指标代码	
	private String tarCode;
	//指标名称	
	private String tarName;
	//是否存在基数指标(默认为否)
	private String bsFlag=BSFLAG_NO;
	private String bsFlagName;
	//计量单位	
	private String measUnitCode;
	private String measUnitCodeName;
	//数据类型	
	private String measDefCode;
	//指标类型
	private String tarType;
	private String tarTypeName;
	//指标属性	
	private String tarProperty;
	//统计范围	
	private String statScopeCode;
	//处理日期	
	private String procDateCode;
	//指定处理日期	
	private String tarProcDate;
	//存储日期	
	private String storeDate;
	private String storeDateName;
	//产品类型	
	private String prdtypeCode;
	private String prdtypeName;
	//科目	
	private String subjCode;
	private String subjName;
	//适用对象	
	private String tarScope;
	//业务条线分类	
	private String tarBizType;
	//指标分类编码	
	private String tarSortCode;
	//指标状态	（默认为已录入）
	private String tarStatus=STATUS_INPUT;
	//指标名称
	private String statusName;
	//指标说明	
	private String tarDesc;
	//杂项	
	private String misc;
	private String remark;
	//日均范围	
	private String dayScope;
	//是否关注
	private String attention;
	//数据来湃
	private String dataSource;
	//创建人	
	private String createUser;
	//创建时间	
	private Date createTime;
	//创建机构	
	private String createOrg;
	private String unitName;
	//修改人	
	private String lastUpdUser;
	//修改机构	
	private String lastUpdOrg;
	//修改时间	
	private Date lastUpdTime;
	
	public String getTarCode() {
		return tarCode;
	}
	public void setTarCode(String tarCode) {
		this.tarCode = tarCode;
	}
	public String getTarName() {
		return tarName;
	}
	public void setTarName(String tarName) {
		this.tarName = tarName;
	}
	public String getBsFlag() {
		return bsFlag;
	}
	public void setBsFlag(String bsFlag) {
		/*if (BSFLAG_NO.equals(tarStatus)) {
			bsFlagName = "是";
		} else if (STATUS_DEL.equals(tarStatus)) {
			bsFlagName = "否";
		}else{
		    bsFlagName = "否";
		}*/
		this.bsFlag = bsFlag;
	}
	public String getMeasUnitCode() {
		return measUnitCode;
	}
	public void setMeasUnitCode(String measUnitCode) {
		/*if (IND_UNIT_Y.equals(measUnitCode)) {
			measUnitCodeName = IND_UNIT_Y_NAME;
		} else if (IND_UNIT_MY.equals(measUnitCode)) {
			measUnitCodeName =IND_UNIT_MY_NAME;
		}else if (IND_UNIT_B.equals(measUnitCode)) {
			measUnitCodeName =IND_UNIT_B_NAME;
		}else if (IND_UNIT_C.equals(measUnitCode)) {
			measUnitCodeName =IND_UNIT_C_NAME;
		}else if (IND_UNIT_Z.equals(measUnitCode)) {
			measUnitCodeName =IND_UNIT_Z_NAME;
		}else if (IND_UNIT_F.equals(measUnitCode)) {
			measUnitCodeName =IND_UNIT_F_NAME;
		}*/
		this.measUnitCode = measUnitCode;
	}
	
	public String getMeasUnitCodeName() {
		return measUnitCodeName;
	}
	public String getMeasDefCode() {
		return measDefCode;
	}
	public void setMeasDefCode(String measDefCode) {
		this.measDefCode = measDefCode;
	}
	public String getTarType() {
		return tarType;
	}
	public void setTarType(String tarType) {
	   /* if(TAR_TYPE_BASE.equals(tarType)){
	        this.tarTypeName=TAR_TYPE_BASE_NAME;
	    }else if(TAR_TYPE_MIX.equals(tarType)){
	        this.tarTypeName=TAR_TYPE_MIX_NAME;
	    }*/
		this.tarType = tarType;
	}
	public String getTarTypeName() {
        return tarTypeName;
    }
	public String getTarProperty() {
		return tarProperty;
	}
	public void setTarProperty(String tarProperty) {
		this.tarProperty = tarProperty;
	}
	public String getStatScopeCode() {
		return statScopeCode;
	}
	public void setStatScopeCode(String statScopeCode) {
		this.statScopeCode = statScopeCode;
	}
	public String getProcDateCode() {
		return procDateCode;
	}
	public void setProcDateCode(String procDateCode) {
		this.procDateCode = procDateCode;
	}
	public String getTarProcDate() {
		return tarProcDate;
	}
	public void setTarProcDate(String tarProcDate) {
		this.tarProcDate = tarProcDate;
	}
	public String getStoreDate() {
		return storeDate;
	}
	
	public void setStoreDate(String storeDate) {
		/*if (SAVE_TYPE_DAY.equals(storeDate)) {
			storeDateName = SAVE_TYPE_DAY_NAME;
		} else if (SAVE_TYPE_HALFYEAR.equals(storeDate)) {
			storeDateName =SAVE_TYPE_HALFYEAR_NAME;
		}else if (SAVE_TYPE_MONTH.equals(storeDate)) {
			storeDateName =SAVE_TYPE_MONTH_NAME;
		}else if (SAVE_TYPE_QUARTER.equals(storeDate)) {
			storeDateName =SAVE_TYPE_QUARTER_NAME;
		}else if (SAVE_TYPE_TENDAYS.equals(storeDate)) {
			storeDateName =SAVE_TYPE_TENDAYS_NAME;
		}else if (SAVE_TYPE_YEAR.equals(storeDate)) {
			storeDateName =SAVE_TYPE_YEAR_NAME;
		}*/
		this.storeDate = storeDate;
	}
	public String getStoreDateName() {
		return storeDateName;
	}
	
	public String getPrdtypeCode() {
		return prdtypeCode;
	}
	public void setPrdtypeCode(String prdtypeCode) {
		this.prdtypeCode = prdtypeCode;
	}
	public String getSubjCode() {
		return subjCode;
	}
	public void setSubjCode(String subjCode) {
		this.subjCode = subjCode;
	}
	public String getTarScope() {
		return tarScope;
	}
	public void setTarScope(String tarScope) {
		this.tarScope = tarScope;
	}
	public String getTarBizType() {
		return tarBizType;
	}
	public void setTarBizType(String tarBizType) {
		this.tarBizType = tarBizType;
	}
	public String getTarSortCode() {
		return tarSortCode;
	}
	public void setTarSortCode(String tarSortCode) {
		this.tarSortCode = tarSortCode;
	}
	
	public String getTarStatus() {
		return tarStatus;
	}
	public void setTarStatus(String tarStatus) {
		/*if (STATUS_INPUT.equals(tarStatus)) {
			statusName = "已录入";
		} else if (STATUS_DEL.equals(tarStatus)) {
			statusName = "已删除";
		}*/
		this.tarStatus = tarStatus;
	}
	public String getTarDesc() {
		return tarDesc;
	}
	public void setTarDesc(String tarDesc) {
		this.tarDesc = tarDesc;
	}
	public String getMisc() {
		return misc;
	}
	public void setMisc(String misc) {
		this.misc = misc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getCreateOrg() {
		return createOrg;
	}
	public void setCreateOrg(String createOrg) {
		this.createOrg = createOrg;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getLastUpdUser() {
		return lastUpdUser;
	}
	public void setLastUpdUser(String lastUpdUser) {
		this.lastUpdUser = lastUpdUser;
	}
	public String getLastUpdOrg() {
		return lastUpdOrg;
	}
	public void setLastUpdOrg(String lastUpdOrg) {
		this.lastUpdOrg = lastUpdOrg;
	}
	public Date getLastUpdTime() {
		return lastUpdTime;
	}
	public void setLastUpdTime(Date lastUpdTime) {
		this.lastUpdTime = lastUpdTime;
	}
	public String getDayScope() {
		return dayScope;
	}
	public void setDayScope(String dayScope) {
		this.dayScope = dayScope;
	}
	public String getStatusName() {
		return statusName;
	}
	public String getBsFlagName() {
		return bsFlagName;
	}
	public String getPrdtypeName() {
		return prdtypeName;
	}
	public void setPrdtypeName(String prdtypeName) {
		this.prdtypeName = prdtypeName;
	}
	public String getSubjName() {
		return subjName;
	}
	public void setSubjName(String subjName) {
		this.subjName = subjName;
	}
	
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public void setBsFlagName(String bsFlagName) {
		this.bsFlagName = bsFlagName;
	}
	public void setMeasUnitCodeName(String measUnitCodeName) {
		this.measUnitCodeName = measUnitCodeName;
	}
	public void setTarTypeName(String tarTypeName) {
		this.tarTypeName = tarTypeName;
	}
	public void setStoreDateName(String storeDateName) {
		this.storeDateName = storeDateName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getAttention() {
		return attention;
	}
	public void setAttention(String attention) {
		this.attention = attention;
	}
}