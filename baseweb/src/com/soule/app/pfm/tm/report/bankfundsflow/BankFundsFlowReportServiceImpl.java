package com.soule.app.pfm.tm.report.bankfundsflow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;

/**
 * 自定义报表操作
 */
@Service
public class BankFundsFlowReportServiceImpl implements IBankFundsFlowReportService {

    // 数据库通用操作
	@Autowired
    private IDefaultService defService;
    private static final Log logger = LogFactory.getLog(BankFundsFlowReportServiceImpl.class);
    private String RPT_LIST_BANKFUNDSFLOW = "bankfundsflowreport.getBankFundsFlowList";
    private String RPT_LIST_TREASURYFUNDSFLOW = "bankfundsflowreport.getTreasuryFundsNatureList";
    private String RPT_LIST_TREASURYFUNDSSOURCE = "bankfundsflowreport.getTreasuryFundsSourceList";
    private String RPT_LIST_ACCOUNTINGANALYSISOTHER = "bankfundsflowreport.getAccountingAnalysisOtherList";
    /*
     * 与商业银行之间资金流动情况统计表
     */
    @SuppressWarnings("unchecked")
	public BankFundsFlowReportQueryOut query(BankFundsFlowReportQueryIn in) throws ServiceException {
    	BankFundsFlowReportQueryOut out = new BankFundsFlowReportQueryOut();
        try {
            List<BankFundsFlowPo> bankFundsFlowList = defService.getIbatisMediator().find(this.RPT_LIST_BANKFUNDSFLOW,in);
            bankFundsFlowList = assemblyBankFundsFlowList(bankFundsFlowList);
            List<ReportTargetPo> treasuryFundsNatureList = defService.getIbatisMediator().find(this.RPT_LIST_TREASURYFUNDSFLOW,in);
            treasuryFundsNatureList = assemblyTreasuryFundsNatureList(treasuryFundsNatureList);
            List<ReportTargetPo> treasuryFundsSourceList = defService.getIbatisMediator().find(this.RPT_LIST_TREASURYFUNDSSOURCE,in);
            treasuryFundsSourceList = assemblyTreasuryFundsSourceList(treasuryFundsSourceList);
            out.setBankFundsFlowList(bankFundsFlowList);
            out.setTreasuryFundsNatureList(treasuryFundsNatureList);
            out.setTreasuryFundsSourceList(treasuryFundsSourceList);
            AppUtils.setResult(out, "I0000");
        } catch (DbAccessException e) {
            logger.error("DB", e);
            AppUtils.setResult(out, "E0002");
        } catch (Exception e) {
            logger.error("SERVICE", e);
            AppUtils.setResult(out, "E0000");
        }
        return out;
    }
    
    
    private List<BankFundsFlowPo> assemblyBankFundsFlowList(List<BankFundsFlowPo> bankFundsFlowList){
    	List<BankFundsFlowPo> newList = null ;
    	BigDecimal bankAllInflow = new BigDecimal(0);
    	BigDecimal bankSpecialInflow = new BigDecimal(0);
    	BigDecimal bankAllOutflow = new BigDecimal(0);
    	BigDecimal bankSpecialOutflow = new BigDecimal(0);
    	BigDecimal bankAllNetflow = new BigDecimal(0);
    	if(bankFundsFlowList == null || bankFundsFlowList.isEmpty()){
    		newList = new ArrayList<BankFundsFlowPo>();
    	}else{
    		newList = bankFundsFlowList;
        	for(BankFundsFlowPo npo : bankFundsFlowList){
        		bankAllInflow = bankAllInflow.add(npo.getBankAllInflow());
        		bankSpecialInflow = bankSpecialInflow.add(npo.getBankSpecialInflow()); 
        		bankAllOutflow = bankAllOutflow.add(npo.getBankAllOutflow()); 
        		bankSpecialOutflow = bankSpecialOutflow.add(npo.getBankSpecialOutflow()); 
        		bankAllNetflow = bankAllNetflow.add(npo.getBankAllNetFlow());
        	}
    	}
    	BankFundsFlowPo po = new BankFundsFlowPo();
    	po.setCustOrgNo("CustOrgNoSum");
    	po.setCustOrgName("合计");
    	po.setBankAllInflow(bankAllInflow);
    	po.setBankSpecialInflow(bankSpecialInflow);
    	po.setBankAllOutflow(bankAllOutflow);
    	po.setBankSpecialOutflow(bankSpecialOutflow);
    	po.setBankAllNetFlow(bankAllNetflow);
    	newList.add(po);
    	return newList;
    }
    
    private List<ReportTargetPo> assemblyTreasuryFundsNatureList(List<ReportTargetPo> reportTargetList){
    	List<ReportTargetPo> newList = new ArrayList<ReportTargetPo>();
    	BigDecimal bankAllInflow = new BigDecimal(0);
    	BigDecimal bankAllOutflow = new BigDecimal(0);
    	BigDecimal bankAllNetflow = new BigDecimal(0);
    	String tarCodeInflow = "TE_00005|TE_00014|TE_00015|TE_00006|TE_00007|TE_00008|TE_00009|TE_00010";
    	String tarCodeOutflow = "TE_00036|TE_00046|TE_00114|TE_00115|TE_00047|TE_00048|TE_00116|TE_00037|TE_00049|TE_00050|TE_00038|TE_00039|TE_00040|TE_00041|TE_00042";
    	String tarCodeNetflow = "TE_00104|TE_00105|TE_00106|TE_00107|TE_00108|TE_00109";
    	if(reportTargetList == null || reportTargetList.isEmpty()){
    		newList = new ArrayList<ReportTargetPo>();
    	}else{
    		newList = reportTargetList;
    		for(ReportTargetPo npo : reportTargetList){
    			String tarCode = npo.getTarCode();
    			if(tarCode.contains(tarCodeInflow)){
    				bankAllInflow = bankAllInflow.add(npo.getTarValue());
    			}
    			if(tarCode.contains(tarCodeOutflow)){
    				bankAllOutflow = bankAllOutflow.add(npo.getTarValue());
    			}
    			if(tarCode.contains(tarCodeNetflow)){
    				bankAllNetflow = bankAllNetflow.add(npo.getTarValue());
    			}
        	}
    	}
    	ReportTargetPo po = new ReportTargetPo();
    	po.setTarCode("NatureBankAllInflowSum");
    	po.setTarName("从商业银行流入国库");
    	po.setTarValue(bankAllInflow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("NatureBankAllOutflowSum");
    	po.setTarName("从国库流向商业银行");
    	po.setTarValue(bankAllOutflow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("NatureBankAllNetflowSum");
    	po.setTarName("净流入（流出）");
    	po.setTarValue(bankAllNetflow);
    	newList.add(po);
    	return newList;
    }
    
    private List<ReportTargetPo> assemblyTreasuryFundsSourceList(List<ReportTargetPo> reportTargetList){
    	List<ReportTargetPo> newList = new ArrayList<ReportTargetPo>();
    	BigDecimal bankAllInflow = new BigDecimal(0);
    	BigDecimal bankAllOutflow = new BigDecimal(0);
    	BigDecimal bankAllNetflow = new BigDecimal(0);
    	String tarCodeInflow = "TE_00011|TE_00012|TE_00013|TE_00117";
    	String tarCodeOutflow = "TE_00043|TE_00044|TE_00045|TE_00118";
    	String tarCodeNetflow = "TE_00110|TE_00111|TE_00112|TE_00113";
    	if(reportTargetList == null || reportTargetList.isEmpty()){
    		newList = new ArrayList<ReportTargetPo>();
    	}else{
    		newList = reportTargetList;
    		for(ReportTargetPo npo : reportTargetList){
    			String tarCode = npo.getTarCode();
    			if(tarCode.contains(tarCodeInflow)){
    				bankAllInflow = bankAllInflow.add(npo.getTarValue());
    			}
    			if(tarCode.contains(tarCodeOutflow)){
    				bankAllOutflow = bankAllOutflow.add(npo.getTarValue());
    			}
    			if(tarCode.contains(tarCodeNetflow)){
    				bankAllNetflow = bankAllNetflow.add(npo.getTarValue());
    			}
        	}
    	}
    	ReportTargetPo po = new ReportTargetPo();
    	po.setTarCode("SourceBankAllInflowSum");
    	po.setTarName("从商业银行流入国库");
    	po.setTarValue(bankAllInflow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("SourceBankAllOutflowSum");
    	po.setTarName("从国库流向商业银行");
    	po.setTarValue(bankAllOutflow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("SourceBankAllNetflowSum");
    	po.setTarName("净流入（流出）");
    	po.setTarValue(bankAllNetflow);
    	newList.add(po);
    	return newList;
    }
    
    
    /*
     * 国库会计分析其他数据统计表
     */
    @SuppressWarnings("unchecked")
	public BankFundsFlowReportQueryOut query4(BankFundsFlowReportQueryIn in) throws ServiceException {
    	BankFundsFlowReportQueryOut out = new BankFundsFlowReportQueryOut();
        try {
            List<ReportTargetPo> accountingAnalysisOtherList = defService.getIbatisMediator().find(this.RPT_LIST_ACCOUNTINGANALYSISOTHER,in);
            if(accountingAnalysisOtherList==null)accountingAnalysisOtherList=new ArrayList<ReportTargetPo>();
            out.setAccountingAnalysisOtherList(accountingAnalysisOtherList);
            AppUtils.setResult(out, "I0000");
        } catch (DbAccessException e) {
            logger.error("DB", e);
            AppUtils.setResult(out, "E0002");
        } catch (Exception e) {
            logger.error("SERVICE", e);
            AppUtils.setResult(out, "E0000");
        }
        return out;
    }
    
    /*
     * 国库会计分析其他数据统计表
     */
    @SuppressWarnings("unchecked")
	public BankFundsFlowReportQueryOut query3(BankFundsFlowReportQueryIn in) throws ServiceException {
    	BankFundsFlowReportQueryOut out = new BankFundsFlowReportQueryOut();
        try {
            List<ReportTargetPo> accountingAnalysisOtherList = defService.getIbatisMediator().find(this.RPT_LIST_ACCOUNTINGANALYSISOTHER,in);
            if(accountingAnalysisOtherList==null)accountingAnalysisOtherList=new ArrayList<ReportTargetPo>();
            out.setAccountingAnalysisOtherList(accountingAnalysisOtherList);
            AppUtils.setResult(out, "I0000");
        } catch (DbAccessException e) {
            logger.error("DB", e);
            AppUtils.setResult(out, "E0002");
        } catch (Exception e) {
            logger.error("SERVICE", e);
            AppUtils.setResult(out, "E0000");
        }
        return out;
    }
    
    public static void main(String[] args){
    	String tarCodeInflow = "TE_00005|TE_00014|TE_00015|TE_00006|TE_00007|TE_00008|TE_00009|TE_00010";
    	String tarCode = "TE_00014";
    	if(tarCodeInflow.contains(tarCode)){
    		System.out.println(true);
    	}else{
    		System.out.println(false);
    	}
    }
}