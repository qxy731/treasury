package com.soule.app.pfm.tm.propdef;
import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceException;
import com.soule.base.service.ServiceResult;

/**
 * 定性指标维护模块表现层处理类
 */
public class PropDefAction extends BaseAction{
    private static final long serialVersionUID = 4050269073292938027L;
    //private final static Log logger = LogFactory.getLog(PropDefAction.class);
    
    @Autowired
    private IPropDefService propDefService;
    private PropDefPo propDefPo;
    private String tarCode;
    
    /**
     * 查询定性指标 输入参数 
     */
    private PropDefQueryIn queryIn; //= new TarsortQueryIn();
    /**
     * 修改定性指标信息 输入参数 
     */
    private PropDefUpdateIn updateIn; //= new TarsortUpdateIn();
    /**
     * 新增定性指标 输入参数 
     */
    private PropDefInsertIn insertIn; //= new TarsortInsertIn();
    /**
     * 删除定性指标 输入参数 
     */
    private PropDefDeleteIn deleteIn; //= new TarsortDeleteIn();
    public String query() {
        PropDefQueryIn in = this.queryIn;
        try {
        	in.getInputHead().setPageNo(page);
        	in.getInputHead().setPageSize(pagesize);
            PropDefQueryOut result = propDefService.query(in);
            ServiceResult head = result.getResultHead();
            setRetCode(head.getRetCode());
            setRetMsg(head.getRetMsg());
            this.rows = result.getPropDef();
            total=result.getResultHead().getTotal();
        } catch (Exception e) {
            handleError(e);
        }
        return "json";
    }
    @Override
    public void doInit() {
    	request=ServletActionContext.getRequest();
    	String tarCode=request.getParameter("tarCode");
    	try {
    		PropDefPo propdef=propDefService.getPropDefById(tarCode);
    		updateIn=new PropDefUpdateIn();
    		updateIn.setModifyPropDef(propdef);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
    }
    
    public void validTarName() {
        // request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            String tarName=propDefPo.getTarName();
            PropDefPo prop = propDefService.getPropDefByTarName(tarName);
            String opt = request.getParameter("opt");
            if ("upd".equals(opt)) {//表示修改时验证
                String oldTarName=request.getParameter("oldTarName");
                if(prop != null){
                    if(oldTarName.equals(prop.getTarName())){
                        out.println("true");
                    }else{
                        out.println("false"); 
                    }
                }else{
                    out.println("true");
                }
            } else {//表示添加时验证
                if (prop != null) {
                    out.println("false");
                } else {
                    out.println("true");
                }
            }
            out.flush();
            out.close();
        } catch (Exception e) {
        }
    }
    public String update() {
        PropDefUpdateIn in = updateIn;
        try {
            IServiceResult result = propDefService.update(in);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String insert() {
        PropDefInsertIn in = insertIn;
        try {
            // TODO
        	PropDefInsertOut result = propDefService.insert(in);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
            this.tarCode=result.getTarCode();
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String delete() {
        try {
            IServiceResult result = propDefService.delete(deleteIn);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }

    /**
     * 查询指标分类
     */
    
    public PropDefQueryIn getQueryIn() {
        return this.queryIn;
    }
    /**
     * 查询指标分类
     */
    public void setQueryIn(PropDefQueryIn in) {
        this.queryIn = in;
    }
    /**
     * 修改指标分类信息
     */
    
    public PropDefUpdateIn getUpdateIn() {
        return this.updateIn;
    }
    /**
     * 修改指标分类信息
     */
    public void setUpdateIn(PropDefUpdateIn in) {
        this.updateIn = in;
    }
    /**
     * 新增指标分类
     */
    
    public PropDefInsertIn getInsertIn() {
        return this.insertIn;
    }
    /**
     * 新增指标分类
     */
    public void setInsertIn(PropDefInsertIn in) {
        this.insertIn = in;
    }
    /**
     * 删除指标分类
     */
    
    public PropDefDeleteIn getDeleteIn() {
        return this.deleteIn;
    }
    /**
     * 删除指标分类
     */
    public void setDeleteIn(PropDefDeleteIn in) {
        this.deleteIn = in;
    }
    //@JSON(serialize=true)
    public PropDefPo getPropDefPo() {
		return propDefPo;
	}
    public void setPropDefPo(PropDefPo propDefPo) {
		this.propDefPo = propDefPo;
	}
    public String getTarCode() {
		return tarCode;
	}
    public void setTarCode(String tarCode) {
		this.tarCode = tarCode;
	}
   
}
