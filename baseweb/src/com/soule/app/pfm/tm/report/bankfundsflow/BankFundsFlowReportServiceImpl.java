package com.soule.app.pfm.tm.report.bankfundsflow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private String RPT_LIST_BETWEENTREASURYFUNDSFLOW = "bankfundsflowreport.getBetweenTreasuryFundsSourceList";
    
    private String RPT_LIST_EXPORT_ACCOUNTINGANALYSISOTHER = "bankfundsflowreport.exportAccountingAnalysisOtherList";
    
    private String RPT_LIST_TREASURYINCOM ="bankfundsflowreport.getTreasuryIncomeExpenditureReport";

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
            //treasuryFundsNatureList = assemblyTreasuryFundsNatureList(treasuryFundsNatureList);
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
    
    public Map export(BankFundsFlowReportQueryIn in) throws ServiceException{
    	Map map = new HashMap();
    	List<BankFundsFlowPo> bankFundsFlowList = new ArrayList<BankFundsFlowPo>();
    	List<ReportTargetPo> treasuryFundsNatureList = new ArrayList<ReportTargetPo>();
    	List<ReportTargetPo> treasuryFundsSourceList = new ArrayList<ReportTargetPo>();
		try {
			  bankFundsFlowList = defService.getIbatisMediator().find(this.RPT_LIST_BANKFUNDSFLOW,in);
	            bankFundsFlowList = assemblyBankFundsFlowList(bankFundsFlowList);
	            treasuryFundsNatureList = defService.getIbatisMediator().find(this.RPT_LIST_TREASURYFUNDSFLOW,in);
	            //treasuryFundsNatureList = assemblyTreasuryFundsNatureList(treasuryFundsNatureList);
	            treasuryFundsSourceList = defService.getIbatisMediator().find(this.RPT_LIST_TREASURYFUNDSSOURCE,in);
	            treasuryFundsSourceList = assemblyTreasuryFundsSourceList(treasuryFundsSourceList);
	            
	            map.put("bankFundsFlowList", bankFundsFlowList);
	            map.put("treasuryFundsNatureList", treasuryFundsNatureList);
	            map.put("treasuryFundsSourceList", treasuryFundsSourceList);
		} catch (DbAccessException e) {
            logger.error("DB", e);
        } catch (Exception e) {
            logger.error("SERVICE", e);
        }
    		map.put("bankFundsFlowList", bankFundsFlowList);
    		map.put("treasuryFundsNatureList", treasuryFundsNatureList);
    		map.put("treasuryFundsSourceList", treasuryFundsSourceList);
    		
    	return map;
    }
    
    /***
     * 100055	国有大型商业银行	
		100018	股份制商业银行	
		100028	城市商业银行 	
		100008	农业发展银行	
		100005	邮政储蓄银行	
		100007	政策性银行	
		100050	其他机构	
     * @param bankFundsFlowList
     * @return
     */
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
        		String custOrgNo = npo.getCustOrgNo();
        		if("100055".equals(custOrgNo)||"100018".equals(custOrgNo)||"100028".equals(custOrgNo)||"100008".equals(custOrgNo)
        				||"100005".equals(custOrgNo)||"100007".equals(custOrgNo)||"100050".equals(custOrgNo)){
	        		bankAllInflow = bankAllInflow.add(npo.getBankAllInflow());
	        		bankSpecialInflow = bankSpecialInflow.add(npo.getBankSpecialInflow()); 
	        		bankAllOutflow = bankAllOutflow.add(npo.getBankAllOutflow()); 
	        		bankSpecialOutflow = bankSpecialOutflow.add(npo.getBankSpecialOutflow()); 
	        		bankAllNetflow = bankAllNetflow.add(npo.getBankAllNetFlow());
        		}
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
    
    /*private List<ReportTargetPo> assemblyTreasuryFundsNatureList(List<ReportTargetPo> reportTargetList){
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
    			if(tarCodeInflow.contains(tarCode)){
    				bankAllInflow = bankAllInflow.add(npo.getTarValue());
    			}
    			if(tarCodeOutflow.contains(tarCode)){
    				bankAllOutflow = bankAllOutflow.add(npo.getTarValue());
    			}
    			if(tarCodeNetflow.contains(tarCode)){
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
    }*/
    
    private List<ReportTargetPo> assemblyBetweenTreasuryFundsNatureList(List<ReportTargetPo> reportTargetList){
    	List<ReportTargetPo> newList = new ArrayList<ReportTargetPo>();
    	BigDecimal r1flow = new BigDecimal(0);
    	BigDecimal r2flow = new BigDecimal(0);
    	BigDecimal r3flow = new BigDecimal(0);
    	BigDecimal r4flow = new BigDecimal(0);
    	BigDecimal r5flow = new BigDecimal(0);
    	BigDecimal r6flow = new BigDecimal(0);
    	BigDecimal r7flow = new BigDecimal(0);
    	BigDecimal r8flow = new BigDecimal(0);
    	BigDecimal r9flow = new BigDecimal(0);
    	BigDecimal r10flow = new BigDecimal(0);
    	BigDecimal r11flow = new BigDecimal(0);
    	BigDecimal r12flow = new BigDecimal(0);
    	BigDecimal r13flow = new BigDecimal(0);
    	BigDecimal r14flow = new BigDecimal(0);
    	BigDecimal r15flow = new BigDecimal(0);
    	BigDecimal r16flow = new BigDecimal(0);
    	BigDecimal r17flow = new BigDecimal(0);
    	BigDecimal r18flow = new BigDecimal(0);
    	BigDecimal r19flow = new BigDecimal(0);
    	BigDecimal r20flow = new BigDecimal(0);
    	BigDecimal r21flow = new BigDecimal(0);
    	
    	String r1="TE_00119|TE_00125|TE_00133|TE_00141|TE_00149";
    	String r2="TE_00120|TE_00126|TE_00134|TE_00142|TE_00150";
    	String r3="TE_00127|TE_00135|TE_00143|TE_00151";
    	String r4="TE_00128|TE_00136|TE_00144|TE_00152";       
    	String r5="TE_00121";
    	String r6="TE_00129|TE_00137|TE_00145|TE_00153";    
    	String r7="TE_00122|TE_00130|TE_00138|TE_00146|TE_00154";
    	String r8="TE_00123|TE_00131|TE_00139|TE_00147|TE_00155";
    	String r9="TE_00031";
    	String r10="TE_00124|TE_00132|TE_00140|TE_00148|TE_00156|TE_00031";
    	String r11="TE_00157|TE_00164|TE_00173|TE_00182|TE_00191";
    	String r12="TE_00158|TE_00165|TE_00174|TE_00183|TE_00192";
    	String r13="TE_00159|TE_00166|TE_00175|TE_00184";
    	String r14="TE_00160|TE_00167|TE_00176|TE_00185";
    	String r15="TE_00168|TE_00177|TE_00186|TE_00193";
    	String r16="TE_00169|TE_00178|TE_00187|TE_00194";
    	String r17="TE_00161|TE_00170|TE_00179|TE_00188|TE_00195";
		String r18="TE_00162|TE_00171|TE_00180|TE_00189|TE_00196";
    	String r19="TE_00065";
    	String r20="TE_00163|TE_00172|TE_00181|TE_00190|TE_00197|TE_00065";
    	String r21="TE_00198|TE_00199|TE_00200|TE_00201|TE_00202|TE_00203";

    	
    	
    	if(reportTargetList == null || reportTargetList.isEmpty()){
    		newList = new ArrayList<ReportTargetPo>();
    	}else{
    		newList = reportTargetList;
    		for(ReportTargetPo npo : reportTargetList){
    			String tarCode = npo.getTarCode();
    			if(r1.contains(tarCode)){
    				r1flow = r1flow.add(npo.getTarValue());
    			}
    			if(r2.contains(tarCode)){
    				r2flow = r2flow.add(npo.getTarValue());
    			}
    			if(r3.contains(tarCode)){
    				r3flow = r3flow.add(npo.getTarValue());
    			}
    			if(r4.contains(tarCode)){
    				r4flow = r4flow.add(npo.getTarValue());
    			}
    			if(r5.contains(tarCode)){
    				r5flow = r5flow.add(npo.getTarValue());
    			}
    			if(r6.contains(tarCode)){
    				r6flow = r6flow.add(npo.getTarValue());
    			}
    			if(r7.contains(tarCode)){
    				r7flow = r7flow.add(npo.getTarValue());
    			}
    			if(r8.contains(tarCode)){
    				r8flow = r8flow.add(npo.getTarValue());
    			}
    			if(r9.contains(tarCode)){
    				r9flow = r9flow.add(npo.getTarValue());
    			}
    			if(r10.contains(tarCode)){
    				r10flow = r10flow.add(npo.getTarValue());
    			}
    			if(r11.contains(tarCode)){
    				r11flow = r11flow.add(npo.getTarValue());
    			}
    			if(r12.contains(tarCode)){
    				r12flow = r12flow.add(npo.getTarValue());
    			}
    			if(r13.contains(tarCode)){
    				r13flow = r13flow.add(npo.getTarValue());
    			}
    			if(r14.contains(tarCode)){
    				r14flow = r14flow.add(npo.getTarValue());
    			}
    			if(r15.contains(tarCode)){
    				r15flow = r15flow.add(npo.getTarValue());
    			}
    			if(r16.contains(tarCode)){
    				r16flow = r16flow.add(npo.getTarValue());
    			}
    			if(r17.contains(tarCode)){
    				r17flow = r17flow.add(npo.getTarValue());
    			}
    			if(r18.contains(tarCode)){
    				r18flow = r18flow.add(npo.getTarValue());
    			}
    			if(r19.contains(tarCode)){
    				r19flow = r19flow.add(npo.getTarValue());
    			}
    			if(r20.contains(tarCode)){
    				r20flow = r20flow.add(npo.getTarValue());
    			}
    			if(r21.contains(tarCode)){
    				r21flow = r21flow.add(npo.getTarValue());
    			}
        	}
    	}
    	ReportTargetPo po = new ReportTargetPo();
    	po.setTarCode("R1");
    	po.setTarName("小计");
    	po.setTarValue(r1flow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("R2");
    	po.setTarName("小计");
    	po.setTarValue(r2flow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("R3");
    	po.setTarName("小计");
    	po.setTarValue(r3flow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("R4");
    	po.setTarName("小计");
    	po.setTarValue(r4flow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("R5");
    	po.setTarName("小计");
    	po.setTarValue(r5flow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("R6");
    	po.setTarName("小计");
    	po.setTarValue(r6flow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("R7");
    	po.setTarName("小计");
    	po.setTarValue(r7flow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("R8");
    	po.setTarName("小计");
    	po.setTarValue(r8flow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("R9");
    	po.setTarName("小计");
    	po.setTarValue(r9flow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("R10");
    	po.setTarName("小计");
    	po.setTarValue(r10flow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("R11");
    	po.setTarName("小计");
    	po.setTarValue(r11flow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("R12");
    	po.setTarName("小计");
    	po.setTarValue(r12flow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("R13");
    	po.setTarName("小计");
    	po.setTarValue(r13flow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("R14");
    	po.setTarName("小计");
    	po.setTarValue(r14flow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("R15");
    	po.setTarName("小计");
    	po.setTarValue(r15flow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("R16");
    	po.setTarName("小计");
    	po.setTarValue(r16flow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("R17");
    	po.setTarName("小计");
    	po.setTarValue(r17flow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("R18");
    	po.setTarName("小计");
    	po.setTarValue(r18flow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("R19");
    	po.setTarName("小计");
    	po.setTarValue(r19flow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("R20");
    	po.setTarName("小计");
    	po.setTarValue(r20flow);
    	newList.add(po);
    	po = new ReportTargetPo();
    	po.setTarCode("R21");
    	po.setTarName("小计");
    	po.setTarValue(r21flow);
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
    			if(tarCodeInflow.contains(tarCode)){
    				bankAllInflow = bankAllInflow.add(npo.getTarValue());
    			}
    			if(tarCodeOutflow.contains(tarCode)){
    				bankAllOutflow = bankAllOutflow.add(npo.getTarValue());
    			}
    			if(tarCodeNetflow.contains(tarCodeNetflow)){
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
    
    
    public Map export4(BankFundsFlowReportQueryIn in) throws ServiceException{
    	Map map = new HashMap();
    	List<HashMap> accountingAnalysisOtherList = new ArrayList<HashMap>();
		try {
			accountingAnalysisOtherList = defService.getIbatisMediator().find(this.RPT_LIST_EXPORT_ACCOUNTINGANALYSISOTHER,in);
		} catch (DbAccessException e) {
            logger.error("DB", e);
        } catch (Exception e) {
            logger.error("SERVICE", e);
        }
    	
    	if(null!= accountingAnalysisOtherList && accountingAnalysisOtherList.size()>0){
    		map = accountingAnalysisOtherList.get(0);
    	}
    	return map;
    }
    
    /*
     * 与其他国库之间资金流动情况统计表
     */
    @SuppressWarnings("unchecked")
	public BankFundsFlowReportQueryOut query2(BankFundsFlowReportQueryIn in) throws ServiceException {
    	BankFundsFlowReportQueryOut out = new BankFundsFlowReportQueryOut();
        try {
        	List<TreasuryIncomePo> treasuryIncomList = defService.getIbatisMediator().find(this.RPT_LIST_TREASURYINCOM,in);
            out.setTreasuryIncomList(treasuryIncomList);
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
            List<ReportTargetPo> betweenTreasuryFundsList = defService.getIbatisMediator().find(this.RPT_LIST_BETWEENTREASURYFUNDSFLOW,in);
            betweenTreasuryFundsList =  assemblyBetweenTreasuryFundsNatureList(betweenTreasuryFundsList);
            out.setAccountingAnalysisOtherList(betweenTreasuryFundsList);
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
    
   
    
   
}