package com.soule.crm.pfm.param.indexdata;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;


/**
 * 输出参数指标数据补录:指标数据补录信息查询
 */
public class IndexDataQueryOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<IndexDataPo> rows;
    private String templateFile;


    public List<IndexDataPo> getRows() {
        return rows;
    }

    public void setRows(List<IndexDataPo> output) {
        this.rows = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

	public String getTemplateFile() {
		return templateFile;
	}

	public void setTemplateFile(String templateFile) {
		this.templateFile = templateFile;
	}
    
    

}