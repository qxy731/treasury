package com.soule.crm.monitor.daily.file;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;

/**
 * 文件系统监控维护模块表现层处理类
 */
@Namespace("/monitor")
public class FileAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
    private IFileService service;

    /**
     * 文件系统信息 输入参数 
     */
    private FileListIn listIn; //= new FileListIn();

    public void doInit() {
    }
    public String list() {
        FileListIn in = listIn;
        try {
            FileListOut result = service.list(in);
            ServiceResult head = result.getResultHead();
            this.rows = result.getDetail();
            this.total = head.getTotal();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }

    /**
     * 文件系统信息
     */
    @JSON(serialize=false)
    public FileListIn getListIn() {
        return this.listIn;
    }
    /**
     * 文件系统信息
     */
    public void setListIn(FileListIn in) {
        this.listIn = in;
    }

}
