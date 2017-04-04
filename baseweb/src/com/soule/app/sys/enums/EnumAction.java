package com.soule.app.sys.enums;

import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.MsgConstants;
import com.soule.base.action.BaseAction;
import com.soule.base.media.DbAccessException;

@Namespace("/sys")
public class EnumAction extends BaseAction {

    private final static Log logger = LogFactory.getLog(EnumAction.class);
    private static final long serialVersionUID = 1L;
    /**
     * 将要删除的记录
     */
    private List<EnumPo> deletes = null;
    /**
     * 新增数据
     */
    private EnumPo po = new EnumPo();
    /**
     * 查询条件
     */
    private String qenumId;
    private String qenumName;
    @Autowired
    private ISysEnumService service;

    public String execute() throws Exception {
        try {
            Long t = service.getEnumCount(qenumId, qenumName);
            rows = service.getEnumList(qenumId, qenumName, page, pagesize);
            this.total = t;
        } catch (DbAccessException e) {
            logger.error("", e);
        }
        return JSON;
    }
    public String delete() {
        try {
            Integer ret = service.deleteEnums(deletes);
            this.retCode = "I0000";this.retMsg="操作成功";
        } catch (DbAccessException e) {
            logger.error("", e);
        }
        return JSON;
    }
    public String insert() throws Exception {
        service.insert(po);
        this.retCode=MsgConstants.I0000;
        this.retMsg="操作成功";
        return JSON;
    }

    public String getQenumId() {
        return qenumId;
    }
    public void setQenumId(String qenumId) {
        this.qenumId = qenumId;
    }
    public String getQenumName() {
        return qenumName;
    }
    public void setQenumName(String qenumName) {
        this.qenumName = qenumName;
    }
    public String getDeletes() {
        return "";
    }
    public EnumPo getPo() {
        return po;
    }
    public void setPo(EnumPo po) {
        this.po = po;
    }
    @SuppressWarnings({ "unchecked", "static-access" })
    public void setDeletes(String jsonData) {
        JSONArray jsonArray = JSONArray.fromObject(jsonData);
        this.deletes = (List<EnumPo>) jsonArray.toList(jsonArray, EnumPo.class); 
    }
  
}
