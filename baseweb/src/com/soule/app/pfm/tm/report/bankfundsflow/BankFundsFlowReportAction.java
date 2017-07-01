package com.soule.app.pfm.tm.report.bankfundsflow;


import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;

/**
 * 人员维护模块表现层处理类
 */
@Namespace("/report")
public class BankFundsFlowReportAction extends BaseAction {
    private static final long serialVersionUID = 1295775504760653068L;
    
    @Autowired
    private IBankFundsFlowReportService bankFundsFlowReportService;
    
    private List<BankFundsFlowPo> bankFundsFlowList;//资金流向分类
    private List<ReportTargetPo> treasuryFundsNatureList;//资金性质分类
    private List<ReportTargetPo> treasuryFundsSourceList;//资金来源分类
    
    private List<ReportTargetPo> accountingAnalysisOtherList;//国库会计分析其他数据统计表

    private BankFundsFlowReportQueryIn queryIn;
    
    public void doInit() {
    }
    
    public String query() {
        try{
            BankFundsFlowReportQueryOut result = bankFundsFlowReportService.query(queryIn);
            ServiceResult head = result.getResultHead();
            bankFundsFlowList = result.getBankFundsFlowList();
            if(bankFundsFlowList == null)bankFundsFlowList = new ArrayList<BankFundsFlowPo>();
            treasuryFundsNatureList = result.getTreasuryFundsNatureList();
            if(treasuryFundsNatureList == null)treasuryFundsNatureList = new ArrayList<ReportTargetPo>();
            treasuryFundsSourceList = result.getTreasuryFundsSourceList();
            if(treasuryFundsSourceList == null)treasuryFundsSourceList = new ArrayList<ReportTargetPo>();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    
    public String query4() {
        try{
            BankFundsFlowReportQueryOut result = bankFundsFlowReportService.query4(queryIn);
            ServiceResult head = result.getResultHead();
            accountingAnalysisOtherList = result.getAccountingAnalysisOtherList();
            if(accountingAnalysisOtherList == null)accountingAnalysisOtherList = new ArrayList<ReportTargetPo>();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
   

    /**
     * 查询报表
     */
    @JSON(serialize=false)
    public BankFundsFlowReportQueryIn getQueryIn() {
        return this.queryIn;
    }
    /**
     * 查询报表
     */
    public void setQueryIn(BankFundsFlowReportQueryIn in) {
        this.queryIn = in;
    }

	public List<BankFundsFlowPo> getBankFundsFlowList() {
		return bankFundsFlowList;
	}

	public void setBankFundsFlowList(List<BankFundsFlowPo> bankFundsFlowList) {
		this.bankFundsFlowList = bankFundsFlowList;
	}

	public List<ReportTargetPo> getTreasuryFundsNatureList() {
		return treasuryFundsNatureList;
	}

	public void setTreasuryFundsNatureList(List<ReportTargetPo> treasuryFundsNatureList) {
		this.treasuryFundsNatureList = treasuryFundsNatureList;
	}

	public List<ReportTargetPo> getTreasuryFundsSourceList() {
		return treasuryFundsSourceList;
	}

	public void setTreasuryFundsSourceList(List<ReportTargetPo> treasuryFundsSourceList) {
		this.treasuryFundsSourceList = treasuryFundsSourceList;
	}

	public List<ReportTargetPo> getAccountingAnalysisOtherList() {
		return accountingAnalysisOtherList;
	}

	public void setAccountingAnalysisOtherList(List<ReportTargetPo> accountingAnalysisOtherList) {
		this.accountingAnalysisOtherList = accountingAnalysisOtherList;
	}
	
}