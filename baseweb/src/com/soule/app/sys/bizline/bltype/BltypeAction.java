package com.soule.app.sys.bizline.bltype;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.JsonValueProcessorImpl;
import com.soule.base.action.BaseAction;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceResult;

/**
 * 业务线类型维护维护模块表现层处理类
 */
@Namespace("/sys")
public class BltypeAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private final static Log logger = LogFactory.getLog(BltypeAction.class);
    @Autowired
    private IBltypeService service;
    @Autowired
    private IDefaultService sDefault;
    /**
     * 查询所有业务线类型 输入参数 
     */
    private BltypeQueryIn queryIn; //= new BltypeQueryIn();
    /**
     * 修改业务线类型 输入参数 
     */
    private BltypeModifyIn modifyIn; //= new BltypeModifyIn();
    /**
     * 删除业务线类型 输入参数 
     */
    private BltypeDeleteIn deleteIn; //= new BltypeDeleteIn();
    /**
     * 判断业务线类型数据 输入参数 
     */
    private BltypeValidDataIn validDataIn; //= new BltypeValidDataIn();
    /**
     * 新增业务线类型 输入参数 
     */
    private BltypeInsertIn insertIn; //= new BltypeInsertIn();

    //显示变量
    private String bltypeStr = "[]";
    private List<BltypeBizTypePo> bldtypes = new ArrayList<BltypeBizTypePo>();
    private BltypeBizTypePo bizType;
    private BltypeResultPo resultPo;

    public void doInit() {
        BltypeQueryOut out;
        try {
            out = service.query(new BltypeQueryIn());
            if (out.getResultHead().isSuccess()) {
                JsonConfig jsonCfg = new JsonConfig();
                jsonCfg.registerJsonValueProcessor(java.util.Date.class, new JsonValueProcessorImpl()); 
                this.bltypeStr = JSONArray.fromObject(out.getBizType(),jsonCfg).toString();
            }
        } catch (Exception e) {
            logger.error("SERVICE",e);
        }
    }
    public String query() {
        BltypeQueryIn in = queryIn;
        try {
            BltypeQueryOut result = service.query(in);
            ServiceResult head = result.getResultHead();
            if (head.isSuccess()) {
                rows = result.getBizType();
            }
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public void updateInit() {
        String tid = request.getParameter("bltid");
        BltypeBizTypePo param = new BltypeBizTypePo();
        param.setBizTypeId(tid);
        try {
            bizType = (BltypeBizTypePo) sDefault.getIbatisMediator().findById("bizline.getSysBizTypeByKey", param);
        } catch (DbAccessException e) {
            handleError(e);
        }
    }

    public String modify() {
        BltypeModifyIn in = modifyIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            BltypeModifyOut result = service.modify(in);
            ServiceResult head = result.getResultHead();
            // TODO
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String delete() {
        BltypeDeleteIn in = deleteIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            BltypeDeleteOut result = service.delete(in);
            ServiceResult head = result.getResultHead();
            // TODO
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String validData() {
        BltypeValidDataIn in = validDataIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            BltypeValidDataOut result = service.validData(in);
            ServiceResult head = result.getResultHead();
            resultPo = result.getResult();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String insert() {
        BltypeInsertIn in = insertIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            BltypeInsertOut result = service.insert(in);
            ServiceResult head = result.getResultHead();
            // TODO
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }

    /**
     * 查询所有业务线类型
     */
    @JSON(serialize=false)
    public BltypeQueryIn getQueryIn() {
        return this.queryIn;
    }
    /**
     * 查询所有业务线类型
     */
    public void setQueryIn(BltypeQueryIn in) {
        this.queryIn = in;
    }
    /**
     * 修改业务线类型
     */
    @JSON(serialize=false)
    public BltypeModifyIn getModifyIn() {
        return this.modifyIn;
    }
    /**
     * 修改业务线类型
     */
    public void setModifyIn(BltypeModifyIn in) {
        this.modifyIn = in;
    }
    /**
     * 删除业务线类型
     */
    @JSON(serialize=false)
    public BltypeDeleteIn getDeleteIn() {
        return this.deleteIn;
    }
    /**
     * 删除业务线类型
     */
    public void setDeleteIn(BltypeDeleteIn in) {
        this.deleteIn = in;
    }
    /**
     * 判断业务线类型数据
     */
    @JSON(serialize=false)
    public BltypeValidDataIn getValidDataIn() {
        return this.validDataIn;
    }
    /**
     * 判断业务线类型数据
     */
    public void setValidDataIn(BltypeValidDataIn in) {
        this.validDataIn = in;
    }
    /**
     * 新增业务线类型
     */
    @JSON(serialize=false)
    public BltypeInsertIn getInsertIn() {
        return this.insertIn;
    }
    /**
     * 新增业务线类型
     */
    public void setInsertIn(BltypeInsertIn in) {
        this.insertIn = in;
    }
    public String getBltypeStr() {
        return bltypeStr;
    }
    public void setBltypeStr(String bltypeStr) {
        this.bltypeStr = bltypeStr;
    }
    public BltypeBizTypePo getBizType() {
        return bizType;
    }
    public void setBizType(BltypeBizTypePo bizType) {
        this.bizType = bizType;
    }
    public BltypeResultPo getResultPo() {
        return resultPo;
    }
    public void setResultPo(BltypeResultPo resultPo) {
        this.resultPo = resultPo;
    }
    public List<BltypeBizTypePo> getBldtypes() {
        return bldtypes;
    }
    public void setBldtypes(List<BltypeBizTypePo> bldtypes) {
        this.bldtypes = bldtypes;
    }

}
