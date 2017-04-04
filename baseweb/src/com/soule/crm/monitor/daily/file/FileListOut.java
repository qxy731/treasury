package com.soule.crm.monitor.daily.file;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数文件系统监控:文件系统信息
 */
public class FileListOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<FileDetailPo> detail;


    public List<FileDetailPo> getDetail() {
        return detail;
    }

    public void setDetail(List<FileDetailPo> output) {
        this.detail = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}