package com.soule.app.pfm.tm.qtydef;
import java.io.PrintWriter;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.app.pfm.tm.BaseTar;
import com.soule.base.action.BaseAction;
import com.soule.base.service.ILogonUserInfo;
import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;
import com.soule.comm.ObjectUtil;
import com.soule.comm.tools.AppUtils;

@Namespace("/qtydefManager")
public class QtyDefAction extends BaseAction{
	private static final long serialVersionUID = 7712415512542415471L;
	private final static Log logger = LogFactory.getLog(QtyDefAction.class);
	@Autowired
	private IQtyDefService qtyDefService;
	
	private String tarCode;
	private QtyDefPo newQtyDef;
	
	private QtyExpDefPo qtyExp;
	//private List<String> dayScopeList;
	private String dayScopeStatus;
	private QtyDefQueryIn queryIn;
	private QtydefDeleteIn deleteIn;
	
	
	public String query() {
        try {
        	queryIn.getInputHead().setPageNo(page);
        	queryIn.getInputHead().setPageSize(pagesize);
            QtyDefQueryOut result = qtyDefService.query(queryIn);
            ServiceResult head = result.getResultHead();
            setRetCode(head.getRetCode());
            setRetMsg(head.getRetMsg());
            this.rows = result.getQtyDef();
            total=result.getResultHead().getTotal();
        } catch (Exception e) {
            handleError(e);
        }
        return JSON;
    }
	 public String insert(){
        try {
            QtyDefInsertOut result=new QtyDefInsertOut();
            if(BaseTar.TAR_TYPE_MIX.equals(newQtyDef.getTarType())){
                result = qtyDefService.insert(newQtyDef, qtyExp);
            }else{
                result = qtyDefService.insert(newQtyDef,null);
            }
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
            this.tarCode = result.getTarCode();
            this.newQtyDef = null;
        } catch (Exception e) {
            logger.error("db is error");
            handleError(e);
        }
        return JSON;
    }
	 public String update() {
	        try {
	            IServiceResult result =new QtyDefInsertOut();
	            ILogonUserInfo logonInfo = (ILogonUserInfo)AppUtils.getLogonUserInfo();
	            newQtyDef.setLastUpdOrg(logonInfo.getOperUnitId());
	            newQtyDef.setLastUpdTime(new Date());
	            newQtyDef.setLastUpdUser(logonInfo.getUser().getUserID());
	            if(BaseTar.TAR_TYPE_MIX.equals(newQtyDef.getTarType())){
	            	qtyExp.setLastUpdUser(logonInfo.getUser().getUserID());
		            qtyExp.setLastUpdTime(new Date());
		            qtyExp.setLastUpdOrg(logonInfo.getOperUnitId());
		            qtyExp.setTarCode(newQtyDef.getTarCode());
	                result = qtyDefService.update(newQtyDef,qtyExp);
	            }else{
	                result = qtyDefService.update(newQtyDef,null);
	            }
	            ServiceResult head = result.getResultHead();
	            this.setRetCode(head.getRetCode());
	            this.setRetMsg(head.getRetMsg());
	        }catch(Exception e) {
	            handleError(e);
	        }
	        return JSON;
	    }
	 public String delete() {
	        try {
	            IServiceResult result = qtyDefService.delete(deleteIn);
	            ServiceResult head = result.getResultHead();
	            this.setRetCode(head.getRetCode());
	            this.setRetMsg(head.getRetMsg());
	        }catch(Exception e) {
	            handleError(e);
	        }
	        return JSON;
	    }
    @Override
    public void doInit() {
    	request=ServletActionContext.getRequest();
    	String tarCode=request.getParameter("tarCode");
    	try {
			QtyDefQueryOut result=qtyDefService.getQtyDefById(tarCode);
			this.newQtyDef=result.getOneQtyDef();
			this.qtyExp = result.getQtyExp();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void validTarName() {
        // request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            String tarName=newQtyDef.getTarName().trim();
            QtyDefPo qty = qtyDefService.getQtyDefByTarName(tarName);
            String opt = request.getParameter("opt");
            if ("upd".equals(opt)) {
            	String oldTarName=request.getParameter("oldTarName").trim();
                if(qty != null){
                    if(oldTarName.equals(qty.getTarName())){
                        out.println("true");
                    }else{
                        out.println("false"); 
                    }
                }else{
                    out.println("true");
                }
            } else {
            	if(qty != null) {
                    out.println("false");
                } else {
                    out.println("true");
                }
            }
            out.flush();
            out.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    public void doInitBusinessLine(){
    	if(ObjectUtil.isEmpty(newQtyDef)){
    		newQtyDef = new QtyDefPo();
    	}
    }
	public QtyDefPo getNewQtyDef() {
		return newQtyDef;
	}
	public void setNewQtyDef(QtyDefPo newQtyDef) {
		this.newQtyDef = newQtyDef;
	}
	/*public List<String> getDayScopeList() {
		return dayScopeList;
	}
	public void setDayScopeList(List<String> dayScopeList) {
		this.dayScopeList = dayScopeList;
	}*/
	public String getTarCode() {
		return tarCode;
	}
	public QtyDefQueryIn getQueryIn() {
		return queryIn;
	}
	public void setQueryIn(QtyDefQueryIn queryIn) {
		this.queryIn = queryIn;
	}
	public QtydefDeleteIn getDeleteIn() {
		return deleteIn;
	}
	public void setDeleteIn(QtydefDeleteIn deleteIn) {
		this.deleteIn = deleteIn;
	}
	public QtyExpDefPo getQtyExp() {
        return qtyExp;
    }
	public void setQtyExp(QtyExpDefPo qtyExp) {
        this.qtyExp = qtyExp;
    }
	public String getDayScopeStatus() {
        return dayScopeStatus;
    }
}