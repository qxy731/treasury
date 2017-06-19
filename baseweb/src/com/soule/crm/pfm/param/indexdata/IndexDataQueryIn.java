package com.soule.crm.pfm.param.indexdata;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输入参数对象指标数据补录:指标数据补录信息查询
 */
public class IndexDataQueryIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private java.util.Date recoreDate;
    private String indexCode;
    private String objectId;
    private String objectName;
    private String objectType;
    private String busiLine;

   
    public String getBusiLine() {
		return busiLine;
	}

	public void setBusiLine(String busiLine) {
		this.busiLine = busiLine;
	}

	public java.util.Date getRecoreDate() {
        return recoreDate;
    }

    public void setRecoreDate(java.util.Date recoreDate) {
        this.recoreDate= recoreDate;
    }

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode= indexCode;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId= objectId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType= objectType;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

}