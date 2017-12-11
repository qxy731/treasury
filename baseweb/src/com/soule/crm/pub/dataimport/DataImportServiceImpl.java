package com.soule.crm.pub.dataimport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapException;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;
import com.ibatis.sqlmap.engine.mapping.parameter.BasicParameterMap;
import com.ibatis.sqlmap.engine.mapping.parameter.BasicParameterMapping;
import com.ibatis.sqlmap.engine.mapping.result.AutoResultMap;
import com.ibatis.sqlmap.engine.mapping.sql.simple.SimpleDynamicSql;
import com.ibatis.sqlmap.engine.mapping.statement.InsertStatement;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.type.DateOnlyTypeHandler;
import com.ibatis.sqlmap.engine.type.ObjectTypeHandler;
import com.soule.MsgConstants;
import com.soule.app.table.SysUploadFileErrorDetailPo;
import com.soule.app.table.SysUploadFilePo;
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
            sDefault.getIbatisMediator().update("dataimport.deleteImpFile", in);
            sDefault.getIbatisMediator().update("dataimport.deleteFileQueue", in);
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
    			//删除 大额来账清单
				sDefault.getIbatisMediator().delete("dataimport.deleteBatLargeFromAcctList", dataDate);
				//删除小额来账清单
				sDefault.getIbatisMediator().delete("dataimport.deleteBatDribletFromAcctList", dataDate);
				//删除大额往账清单
				sDefault.getIbatisMediator().delete("dataimport.deleteBatLargeIntoAcctList", dataDate);
				//-删除小额往账清单
				sDefault.getIbatisMediator().delete("dataimport.deleteBatDribletinfoAcctList", dataDate);
				//删除 大连市分库预算收入对账月报表[全辖] 
				sDefault.getIbatisMediator().delete("dataimport.deleteBatRptBudgetMAll", dataDate);
				//删除大连市分库地方级预算收入月报表[全辖]
				sDefault.getIbatisMediator().delete("dataimport.deleteBatRptLocalBudgetMAll", dataDate);
				//删除大连市分库地市级预算收入月报表[全辖]
				sDefault.getIbatisMediator().delete("dataimport.deleteBatRptCityBudgetMAll01", dataDate);
				//删除大连市分库预算收入对账月报表[全辖-国税]
				sDefault.getIbatisMediator().delete("dataimport.deleteBatRptBudgetMAlltax", dataDate);
				
				// 删除 大连市分库中央级预算收入月报表[全辖]
				sDefault.getIbatisMediator().delete("dataimport.deleteBatRptCenterBudgetMAll", dataDate);
				//删除大连市分库地市级预算收入月报表[全辖非本级]
				sDefault.getIbatisMediator().delete("dataimport.deleteBatRptCityBudgetMAll02", dataDate);
				//删除大连市分库地市级预算收入月报表[全辖非本级-预算外]
				sDefault.getIbatisMediator().delete("dataimport.deleteBatRptCityBudgetMAll03", dataDate);
				//删除 大连市分库地市级预算收入月报表[本级-大连市财政局]
				sDefault.getIbatisMediator().delete("dataimport.deleteBatRptCityBudgetMAll04", dataDate);
				//删除大连市分库一般预算支出月报表[本级] 
				sDefault.getIbatisMediator().delete("dataimport.deleteBatRptExpendM", dataDate);
				//删除实拨资金财政错误退回清单 
				sDefault.getIbatisMediator().delete("dataimport.deleteBatFinaMistakRollbackList", dataDate);
				//删除清算国库与收款国库对账单（非税）
				sDefault.getIbatisMediator().delete("dataimport.deleteBatClearClloAcctInfo", dataDate);
				//删除清算国库与清算银行对账单 
				sDefault.getIbatisMediator().delete("dataimport.deleteBatClearBankAcctInfo", dataDate);
				//删除大连市分库单一账户支出授权支付月报表
				sDefault.getIbatisMediator().delete("dataimport.deleteBatRptAuthPayM", dataDate);
				//删除大连市分库单一账户支出直接支付月报表
				sDefault.getIbatisMediator().delete("dataimport.deleteBatRptDirectSpendM", dataDate);
				//删除大连市分库收支存明细表[全辖]
				sDefault.getIbatisMediator().delete("dataimport.deleteBatBalanceListAll", dataDate);
				//删除大连市分库预算收入退库统计表[全辖]
				sDefault.getIbatisMediator().delete("dataimport.deleteBatBudgetCanlStatAll", dataDate);
				//删除TIPS手续费查询[实时扣税]
				sDefault.getIbatisMediator().delete("dataimport.deleteBatTipsFee01", dataDate);
				//删除TIPS手续费查询[银行端缴款]
				sDefault.getIbatisMediator().delete("dataimport.deleteBatTipsFee02", dataDate);
				//删除TIPS手续费查询[自缴核销]
				sDefault.getIbatisMediator().delete("dataimport.deleteBatTipsFee03", dataDate);
				//删除TIPS手续费查询[批量扣税]
				sDefault.getIbatisMediator().delete("dataimport.deleteBatTipsFee04", dataDate);
				//删除大连市分库收支存明细表[本级]
				sDefault.getIbatisMediator().delete("dataimport.deleteBatBalanceList", dataDate);
				//删除同城票据交换明细 -
				sDefault.getIbatisMediator().delete("dataimport.deleteBatLocalClearingList", dataDate);
				//删除 大连市分库预算收支存分科目统计表[中央]
				sDefault.getIbatisMediator().delete("dataimport.deleteBatRptBudgetSubStat", dataDate);
				//删除大连市分库预算收支存分科目统计表[地方]
				sDefault.getIbatisMediator().delete("dataimport.deleteBatRptLocalBudgetSubStat", dataDate);
				
			} catch (DbAccessException e) {
				 logger.error("DB", e);
			}
    	}
    	
    	
    	
    }

}
