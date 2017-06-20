package com.soule.crm.pfm.base.pattern.servicecomponent.model;
 
 
 import java.io.Serializable;
 import java.util.Date;

import com.soule.crm.pfm.base.pattern.servicecomponent.vo.ValueObject;
 
 public class PlanCyc extends ValueObject implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cycCode;
	private String cycName;
	private String cycUnitId;
	private Date cycStartDat;
	private Date cycEndDat;
	private String codeInfoKy;
	private String codeInfoCode;
	private String codeInfoValue;
	private String indexno;
	private String infoRemark;
	private String codeTypeKy;
	private String codeTypeCode;
	private String codeTypeValue;
	private String typeRemark;
 
	public PlanCyc(){}
 
   public PlanCyc(String cycCode, String cycName, String cycUnitId, Date cycStartDat, Date cycEndDat){
     this.cycCode = cycCode;
     this.cycName = cycName;
     this.cycUnitId = cycUnitId;
     this.cycStartDat = cycStartDat;
     this.cycEndDat = cycEndDat;
   }
 
   public String getCycCode()
   {
     return this.cycCode;
   }
 
   public void setCycCode(String cycCode) {
     this.cycCode = cycCode;
   }
 
   public String getCycName() {
     return this.cycName;
   }
 
   public void setCycName(String cycName) {
     this.cycName = cycName;
   }
 
   public String getCycUnitId() {
     return this.cycUnitId;
   }
 
   public void setCycUnitId(String cycUnitId) {
     this.cycUnitId = cycUnitId;
   }
 
   public Date getCycStartDat() {
     return this.cycStartDat;
   }
 
   public void setCycStartDat(Date cycStartDat) {
     this.cycStartDat = cycStartDat;
   }
 
   public Date getCycEndDat() {
     return this.cycEndDat;
   }
 
   public void setCycEndDat(Date cycEndDat) {
     this.cycEndDat = cycEndDat;
   }
 
   public String getCodeInfoKy() {
     return this.codeInfoKy;
   }
 
   public void setCodeInfoKy(String codeInfoKy) {
     this.codeInfoKy = codeInfoKy;
   }
 
   public String getCodeInfoCode() {
     return this.codeInfoCode;
   }
 
   public void setCodeInfoCode(String codeInfoCode) {
     this.codeInfoCode = codeInfoCode;
   }
 
   public String getCodeInfoValue() {
     return this.codeInfoValue;
   }
 
   public void setCodeInfoValue(String codeInfoValue) {
     this.codeInfoValue = codeInfoValue;
   }
 
   public String getIndexno() {
     return this.indexno;
   }
 
   public void setIndexno(String indexno) {
     this.indexno = indexno;
   }
 
   public String getInfoRemark() {
     return this.infoRemark;
   }
 
   public void setInfoRemark(String infoRemark) {
     this.infoRemark = infoRemark;
   }
 
   public String getCodeTypeKy() {
     return this.codeTypeKy;
   }
 
   public void setCodeTypeKy(String codeTypeKy) {
     this.codeTypeKy = codeTypeKy;
   }
 
   public String getCodeTypeCode() {
     return this.codeTypeCode;
   }
 
   public void setCodeTypeCode(String codeTypeCode) {
     this.codeTypeCode = codeTypeCode;
   }
 
   public String getCodeTypeValue() {
     return this.codeTypeValue;
   }
 
   public void setCodeTypeValue(String codeTypeValue) {
     this.codeTypeValue = codeTypeValue;
   }
 
   public String getTypeRemark() {
     return this.typeRemark;
   }
 
   public void setTypeRemark(String typeRemark) {
     this.typeRemark = typeRemark;
   }
 }