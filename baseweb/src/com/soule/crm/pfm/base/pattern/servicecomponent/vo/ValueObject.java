package com.soule.crm.pfm.base.pattern.servicecomponent.vo;


import java.io.Serializable;
import java.util.Date;

public class ValueObject
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public static final String DEL_FLAG_NORMAL = "n";
  public static final String DEL_FLAG_DELETED = "y";
  private String objectId;
  private String name;
  private Date createdTime;
  private String createdId;
  private Date lastModifiedTime;
  private String lastModifiedId;
  private Long recordState;
  private String delFlag;
  private Long valueStatus;
  private String ext1;
  private String ext2;
  private String ext3;

  public String getName()
  {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getObjectId()
  {
    return this.objectId;
  }

  public void setObjectId(String objectid)
  {
    this.objectId = objectid;
  }

  public Date getCreatedTime()
  {
    return this.createdTime;
  }

  public void setCreatedTime(Date createdTime)
  {
    this.createdTime = createdTime;
  }

  public String getCreatedId()
  {
    return this.createdId;
  }

  public void setCreatedId(String creatorId)
  {
    this.createdId = creatorId;
  }

  public String getDelFlag()
  {
    return this.delFlag;
  }

  public void setDelFlag(String delFlag)
  {
    this.delFlag = delFlag;
  }

  public Date getLastModifiedTime()
  {
    return this.lastModifiedTime;
  }

  public void setLastModifiedTime(Date lastModifiedTime)
  {
    this.lastModifiedTime = lastModifiedTime;
  }

  public String getLastModifiedId()
  {
    return this.lastModifiedId;
  }

  public void setLastModifiedId(String lastModifierId)
  {
    this.lastModifiedId = lastModifierId;
  }

  public Long getRecordState()
  {
    return this.recordState;
  }

  public void setRecordState(Long recordState)
  {
    this.recordState = recordState;
  }

  public boolean equals(Object obj)
  {
    if (this == obj) {
      return true;
    }
    if ((obj == null) || (!(obj.getClass().equals(super.getClass())))) {
      return false;
    }
    ValueObject concretevalueobject = (ValueObject)obj;
    String objectid = getObjectId();
    String objectid1 = concretevalueobject.getObjectId();
    if (objectid != null) {
      return objectid.equals(objectid1);
    }

    if (objectid1 != null) {
      return false;
    }
    return super.equals(obj);
  }

  public String getExt1()
  {
    return this.ext1;
  }

  public void setExt1(String ext1)
  {
    this.ext1 = ext1;
  }

  public String getExt2()
  {
    return this.ext2;
  }

  public void setExt2(String ext2)
  {
    this.ext2 = ext2;
  }

  public Long getValueStatus()
  {
    return this.valueStatus;
  }

  public void setValueStatus(Long valueStatus)
  {
    this.valueStatus = valueStatus;
  }

  public String getExt3() {
    return this.ext3;
  }

  public void setExt3(String ext3) {
    this.ext3 = ext3;
  }
}
