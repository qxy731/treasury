package com.soule.app.pfm.tm.report.bankfundsflow;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数人员:查询人员
 */
public class BankFundsFlowReportQueryOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<BankFundsFlowPo> bankFundsFlowList;//资金流向分类
    private List<ReportTargetPo> treasuryFundsNatureList;//资金性质分类
    private List<ReportTargetPo> treasuryFundsSourceList;//资金来源分类
    private List<ReportTargetPo> accountingAnalysisOtherList;//国库会计分析其他数据统计表
    private List<ReportTargetPo> betweenTreasuryFundsSourceList;//国库之间资金流动情况统计
    
    private List<TreasuryIncomePo> treasuryIncomList;//大连市国库收支统计
    
	public List<TreasuryIncomePo> getTreasuryIncomList() {
		return treasuryIncomList;
	}

	public void setTreasuryIncomList(List<TreasuryIncomePo> treasuryIncomList) {
		this.treasuryIncomList = treasuryIncomList;
	}

	public List<ReportTargetPo> getBetweenTreasuryFundsSourceList() {
		return betweenTreasuryFundsSourceList;
	}

	public void setBetweenTreasuryFundsSourceList(List<ReportTargetPo> betweenTreasuryFundsSourceList) {
		this.betweenTreasuryFundsSourceList = betweenTreasuryFundsSourceList;
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

	public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}