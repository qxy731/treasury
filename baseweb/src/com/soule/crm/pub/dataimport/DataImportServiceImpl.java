package com.soule.crm.pub.dataimport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.app.table.SysUploadFileErrorDetailPo;
import com.soule.base.media.DbAccessException;
/*import com.neusoft.MsgConstants;
import com.neusoft.app.table.SysUploadFilePo;
import com.neusoft.base.media.DbAccessException;
import com.neusoft.base.service.IDefaultService;
import com.neusoft.base.service.ILogonUserInfo;
import com.neusoft.base.service.ServiceException;
import com.neusoft.base.service.auth.AdminAuthority;
import com.neusoft.base.service.auth.ResourceAuthority;
import com.neusoft.comm.StringUtils;
import com.neusoft.comm.enu.ResourceType;
import com.neusoft.comm.po.IEnumItem;
import com.neusoft.comm.po.IEnumType;
import com.neusoft.comm.tools.AppUtils;
import com.neusoft.comm.tools.ContextUtil;
import com.neusoft.comm.tools.EnumTypeUtil;
import com.neusoft.comm.tools.StringUtil;
import com.neusoft.crm.comm.IUserManager;
import com.neusoft.crm.tools.ExcelUtil;
import com.neusoft.crm.tools.TimeTool;*/
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ILogonUserInfo;
import com.soule.base.service.ServiceException;
import com.soule.base.service.auth.AdminAuthority;
import com.soule.base.service.auth.ResourceAuthority;
import com.soule.comm.enu.ResourceType;
import com.soule.comm.po.IEnumItem;
import com.soule.comm.po.IEnumType;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.EnumTypeUtil;
import com.soule.crm.comm.IUserManager;

@Service
@Qualifier("dataImportService")
public class DataImportServiceImpl implements IDataImportService {
    private final static Log logger = LogFactory.getLog(DataImportServiceImpl.class);

    @Autowired
    private IDefaultService sDefault;
    @Autowired
    private IUserManager um;

    public List queryFileTypeList(ILogonUserInfo logonUser) throws ServiceException {
        ArrayList<IEnumItem> selected = new ArrayList<IEnumItem>();
        IEnumType uf = EnumTypeUtil.getEnumType("uploadfile_type");
        for (IEnumItem po : uf.getItems()) {
            if (voteMenu(logonUser.getAuthorities(), po)) {
                selected.add(po);
            }
        }
        return selected;
    }

    protected boolean voteMenu(Collection<GrantedAuthority> auths, IEnumItem po) {
        for (GrantedAuthority ga : auths) {
            if (ga instanceof AdminAuthority) {
                return true;
            }
            if (!(ga instanceof ResourceAuthority)) {
                continue;
            }
            ResourceAuthority resAuth = (ResourceAuthority) ga;
            if (resAuth.getRunFlag() && resAuth.getResType() == ResourceType.DATAIMP) {
                String auth = (String) resAuth.getAuthority();
                if (auth.equals(po.getKey())) {
                    return true;
                }
            }
        }
        return false;
    }

    public DataImportQueryOut query(DataImportQueryIn in) throws ServiceException {
        DataImportQueryOut out = new DataImportQueryOut();

        try {
            String querySql = "dataimport.queryFileList";
            if (um.isCustMgr()) {
                in.setStaffId(um.getStaffId());
            }
            in.setOrgCode(um.getOrglst(in.getOrgCode()));
            long x = sDefault.getIbatisMediator().getRecordTotal(querySql, in);
            int pageOffset = (in.getInputHead().getPageNo() - 1) * in.getInputHead().getPageSize();
            if (pageOffset < x) {
                List ret = sDefault.getIbatisMediator().find(querySql, in, pageOffset, in.getInputHead().getPageSize());
                out.getResultHead().setTotal(x);
                out.setDatalist(ret);
            }
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (Exception ex) {
            logger.error("SERVICE", ex);
            AppUtils.setResult(out, MsgConstants.E0002);
        }
        return out;
    }

    public DataBatchUploadOut deleteFile(DataImportQueryIn in) throws ServiceException {
        DataBatchUploadOut out = new DataBatchUploadOut();
        try {
        	
        	String uploadId  = in.getUploadId();
        	String fileId = in.getFileId();
        	String[] uploadIdTemp = uploadId.split(",");
        	String[] fileIdTemp = fileId.split(",");
        	for(int i=0;i<uploadIdTemp.length;i++){
        		DataImportQueryIn newIn = new DataImportQueryIn();
        		newIn.setUploadId(uploadIdTemp[i]);
        		newIn.setFileId(fileIdTemp[i]);
        		sDefault.getIbatisMediator().update("dataimport.deleteImpFile", newIn);
                sDefault.getIbatisMediator().update("dataimport.deleteFileQueue", newIn);
        	}
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (DbAccessException e) {
            logger.error("SERVICE", e);
            AppUtils.setResult(out, MsgConstants.E0001, "删除文件失败！");
        }
        return out;
    }

    
   
    @SuppressWarnings("unchecked")
	public DataImportErrorDetailOut queryFileDetail(DataImportErrorDetailIn in) throws ServiceException {
    	DataImportErrorDetailOut out = new DataImportErrorDetailOut();
        try {
        	DataImportErrorDetailVo errorObject = (DataImportErrorDetailVo)sDefault.getIbatisMediator().findById("dataimport.findFileErrorObject",in);
        	out.setErrorObject(errorObject);
            String querySql = "dataimport.findFileErrorDetailList";
            long x = sDefault.getIbatisMediator().getRecordTotal(querySql,in);
            int pageOffset = (in.getInputHead().getPageNo() - 1) * in.getInputHead().getPageSize();
            if (pageOffset < x) {
                List<SysUploadFileErrorDetailPo> errorDetailList = sDefault.getIbatisMediator().find(querySql, in, pageOffset, in.getInputHead().getPageSize());
                out.getResultHead().setTotal(x);
                out.setErrorDetailList(errorDetailList);
            }
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (DbAccessException ex) {
            logger.error("DB", ex);
            AppUtils.setResult(out, MsgConstants.E0002);
        }
        return out;
    }
    
    public void deleteTargetData(String dataDate) throws ServiceException{
    	//判断日期是否为空，若为空，不执行任何操作
    	if(null==dataDate || "".equals(dataDate)){
    		
    	}else{
    		try {
    			List<String> list = new ArrayList<String>();
    			List<Map<String,String>> list1 = new ArrayList<Map<String,String>>();
    			 list.add("bat_large_from_acct_list"); 
    			 list.add("bat_driblet_from_acct_list"); 
    			 list.add("bat_large_into_acct_list"); 
    			 list.add("bat_driblet_into_acct_list"); 
    			 list.add("bat_rpt_budget_m_all"); 
    			 list.add("bat_rpt_local_budget_m_all"); 
    			 list.add("bat_rpt_city_budget_m_all01"); 
    			 list.add("bat_rpt_budget_m_alltax"); 
    			 list.add("bat_rpt_center_budget_m_all"); 
    			 list.add("bat_rpt_city_budget_m_all02"); 
    			 list.add("bat_rpt_city_budget_m_all03"); 
    			 list.add("bat_rpt_city_budget_m_all04"); 
    			 list.add("bat_rpt_expend_m"); 
    			 list.add("bat_fina_mistake_rollback_list"); 
    			 list.add("bat_clear_cllo_acct_info"); 
    			 list.add("bat_clear_bank_acct_info"); 
    			 list.add("bat_rpt_auth_pay_m"); 
    			 list.add("bat_rpt_direct_spend_m"); 
    			 list.add("bat_balance_list_all"); 
    			 list.add("bat_budget_canl_stat_all"); 
    			 list.add("bat_tips_fee01"); 
    			 list.add("bat_tips_fee02"); 
    			 list.add("bat_tips_fee03"); 
    			 list.add("bat_tips_fee04"); 
    			 list.add("bat_balance_list"); 
    			 list.add("bat_local_clearing_list"); 
    			 list.add("bat_rpt_budget_sub_stat"); 
    			 list.add("bat_rpt_local_budget_sub_stat");
    			 list.add("bat_rpt_budget_income");
    			 list.add("bat_rpt_budget_stat_m_his");//TAR_DATE
    			 
    			 for(int i=0;i<list.size();i++){
    				 Map<String,String> map = new HashMap<String,String>();
    				 String table = list.get(i).toString();
    				 map.put("batTable", table);
    				 if("bat_rpt_budget_income".equals(table)){
    					map.put("tarDate", dataDate.substring(0,6));
    					map.put("hisStartDate", dataDate.substring(0,6));
    				 }else{
    					map.put("tarDate", dataDate);
    					map.put("hisStartDate", dataDate);
    				 }
    				 list1.add(map);
    			 }
    			 //批量删除数据源表
    			 for(int i=0;i<list1.size();i++){
    				 sDefault.getIbatisMediator().delete("dataimport.truncateTableBat", list1.get(i));
    			 }
    			 
    			 list = new ArrayList<String>();
    			 list1 = new ArrayList<Map<String,String>>();
    			 list.add("bat_large_from_acct_list_his");// HIS_START_DATE
    			 list.add("bat_driblet_from_acct_list_his"); // HIS_START_DATE
    			 list.add("bat_large_into_acct_list_his");  // HIS_START_DATE
    			 list.add("bat_driblet_into_acct_list_his"); // HIS_START_DATE
    			 list.add("bat_rpt_budget_m_all_his"); // HIS_START_DATE
    			 list.add("bat_rpt_local_budget_m_all_his"); // HIS_START_DATE
    			 list.add("bat_rpt_city_budget_m_all01_his"); // HIS_START_DATE
    			 list.add("bat_rpt_budget_m_alltax_his"); // HIS_START_DATE
    			 list.add("bat_rpt_center_budget_m_all_his"); // HIS_START_DATE
    			 list.add("bat_rpt_city_budget_m_all02_his"); // HIS_START_DATE
    			 list.add("bat_rpt_city_budget_m_all03_his"); // HIS_START_DATE
    			 list.add("bat_rpt_city_budget_m_all04_his");// HIS_START_DATE 
    			 list.add("bat_rpt_expend_m_his"); // HIS_START_DATE
    			 list.add("bat_fina_mistake_rollback_list_his");  // HIS_START_DATE
    			 list.add("bat_clear_cllo_acct_info_his"); // HIS_START_DATE
    			 list.add("bat_clear_bank_acct_info_his"); // HIS_START_DATE
    			 list.add("bat_rpt_auth_pay_m_his"); // HIS_START_DATE
    			 list.add("bat_rpt_direct_spend_m_his"); // HIS_START_DATE
    			 list.add("bat_balance_list_all_his"); // HIS_START_DATE
    			 list.add("bat_budget_canl_stat_all_his");// HIS_START_DATE 
    			 list.add("bat_tips_fee01_his"); // HIS_START_DATE
    			 list.add("bat_tips_fee02_his"); // HIS_START_DATE
    			 list.add("bat_tips_fee03_his"); // HIS_START_DATE
    			 list.add("bat_tips_fee04_his"); // HIS_START_DATE
    			 list.add("bat_balance_list_his"); // HIS_START_DATE
    			 list.add("bat_local_clearing_list_his"); // HIS_START_DATE
    			 for(int i=0;i<list.size();i++){
    				 Map<String,String> map = new HashMap<String,String>();
    				 String table = list.get(i).toString();
    				 map.put("batTable", table);
    				 if("bat_rpt_budget_income".equals(table)){
    					map.put("hisStartDate", dataDate.substring(0,6));
    				 }else{
    					map.put("hisStartDate", dataDate);
    				 }
    				 list1.add(map);
    			 }
    			 //批量删除数据源表
    			 for(int i=0;i<list1.size();i++){
    				 sDefault.getIbatisMediator().delete("dataimport.truncateTableBatHis", list1.get(i));
    			 }
    			 
			} catch (DbAccessException e) {
				 logger.error("DB", e);
			}
    	}
    }
}
