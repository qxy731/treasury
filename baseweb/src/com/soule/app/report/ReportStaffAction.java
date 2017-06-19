package com.soule.app.report;

import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.app.report.tools.SubRptUtils;
import com.soule.base.service.IDefaultService;

/**
 * 统计报表模块表现层处理类
 */
@Namespace("/reportstaff")
@Results( { 
    @Result(name = "succDown", type = "stream",params={"bufferSize","2048","contentType","text/plain",
            "contentDisposition","attachment;filename=\"${downloadChineseFileName}\"","inputName","downloadFile"})
    })

public class ReportStaffAction extends ReportBaseAction {
    private static final long serialVersionUID = 1L;
    @Autowired
	IDefaultService defService;
    public HashMap fileDatas(HashMap in, String vflag) throws Exception {
        in.put("vflag", vflag);
        in.put("reportCN", getTemplateNameCN());
        HashMap context = service.query(in, getSqlKey());
        List list = (List) context.get("rows");
        context = SubRptUtils.getSumStaffTotal(context, list);
        return context;
    }
    
}
