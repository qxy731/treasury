package com.soule.app.report;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.MsgConstants;
import com.soule.base.service.ServiceException;
import com.soule.crm.comm.IUserManager;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
/**
 * 统计报表模块表现层处理类
 */
@Namespace("/report")
@Results( {
    @Result(name = "errorpage", location = "/jsp/common/reporterr.jsp"),
    @Result(name = "succDown", type = "stream",params={"bufferSize","2048","contentType","text/plain",
            "contentDisposition","attachment;filename=\"${downloadChineseFileName}\"","inputName","downloadFile"})
    })
public class ReportBaseAction extends RepAction<HashMap> {

    @Autowired
    IUserManager um;
    @Autowired
    IReportBaseService service;
    private static final long serialVersionUID = 1L;
    /**
     * 应用参数
     */
    
    private HashMap<String,String> params = new HashMap<String,String>();
    private String sqlKey;
    private String templateName;
    private String templateNameCN;
    private String viewFlag;
    private String initDate;
    private String initUnitId;
    private String initUnitName;
    private List list;
    
    //自己加的属性
    private String unitId;
    //
    public void doInit() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        initDate = (String)sdf.format(um.getCurrDate());
        //String[] units = um.getFirstOrgInfo(um.getDataScope());
        //initUnitId = units[0];
       // initUnitName = units[1];
    }

    public String[] getXlsTemplate() {
        String[] arrs = new String[3];
        arrs[0] = templateName + ".xls";
        arrs[1] = null;
        if (templateNameCN != null) {
            // arrs[2] = templateName + "Down.xls";// 生成文件名字
            arrs[2] = templateNameCN + ".xls";// 生成文件名字
        } else {
            arrs[2] = templateName + "Down.xls";
        }
        return arrs;
    }

    public HashMap initQueryIn() throws ServiceException {
    	Iterator iter=(Iterator) params.keySet().iterator();
    	while(iter.hasNext()){
    		Object key=iter.next();
    		String val=params.get(key);
    		try {
				params.put((String) key,java.net.URLDecoder.decode(val,"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
    	}
        return params;
    }

    public HashMap fileDatas(HashMap in, String vflag) throws Exception {
        in.put("vflag", vflag);
        in.put("reportCN", templateNameCN);
        HashMap context = service.query(in, sqlKey);
        return context;
    }
//    下拉框初始化
    public String  initSel(){
    	list = service.initSel(params);
    	this.setRetCode(MsgConstants.I0000);
    	return JSON;
    }
    public String getSqlKey() {
        return sqlKey;
    }

    public void setSqlKey(String sqlKey) {
        this.sqlKey = sqlKey;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateNameCN() {
        return templateNameCN;
    }

    public void setTemplateNameCN(String templateNameCN) {
        this.templateNameCN = templateNameCN;
    }

    public String getViewFlag() {
        return viewFlag;
    }

    public void setViewFlag(String viewFlag) {
        this.viewFlag = viewFlag;
    }

    public String getInitDate() {
        return initDate;
    }

    public void setInitDate(String initDate) {
        this.initDate = initDate;
    }

    public String getInitUnitId() {
        return initUnitId;
    }

    public void setInitUnitId(String initUnitId) {
        this.initUnitId = initUnitId;
    }

    public String getInitUnitName() {
        return initUnitName;
    }

    public void setInitUnitName(String initUnitName) {
        this.initUnitName = initUnitName;
    }

    public HashMap getParams() {
        return params;
    }

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}
