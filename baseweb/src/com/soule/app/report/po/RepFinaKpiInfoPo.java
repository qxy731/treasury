package com.soule.app.report.po;

import java.io.Serializable;

/**
 * 对应表REP_FINA_KPI_INFO的类
 */
public class RepFinaKpiInfoPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户号
     */
    private String custNo;
    private String custName;
    
    private String industryName;
    /**
     * 报表日期
     */
    private String rptDate;
    /**
     * 五级分类
     */
    private String fiveLvl;
    /**
     * 净资产收益率
     */
    private Double netRate;
    /**
     * 资产收益率
     */
    private Double assetRate;
    /**
     * 销售净利率
     */
    private Double saleNetRate;
    /**
     * 盈余现金保障倍数
     */
    private Double proMul;
    /**
     * 资产负债率
     */
    private Double debtRate;
    /**
     * 流动比率
     */
    private Double flowRate;
    /**
     * 速动比率
     */
    private Double spdRate;
    /**
     * 现金比率
     */
    private Double cashRate;
    /**
     * 利息保障倍数
     */
    private Double inteMul;
    /**
     * 现金流动负债比率
     */
    private Double cashDebtRate;
    /**
     * 总资产周转率
     */
    private Double turnRate;
    /**
     * 存货周转率
     */
    private Double stockRate;
    /**
     * 应收账款周转率
     */
    private Double collTurnRate;
    /**
     * 资产现金回收率
     */
    private Double recoverRate;
    
    private Integer upCnt;

    private Double netRateKpi;
    private Double assetRateKpi;
    private Double saleNetRateKpi;
    private Double proMulKpi;
    private Double debtRateKpi;
    private Double flowRateKpi;
    private Double spdRateKpi;
    private Double cashRateKpi;
    private Double inteMulKpi;
    private Double cashDebtRateKpi;
    private Double turnRateKpi;
    private Double stockRateKpi;
    private Double collTurnRateKpi;
    private Double recoverRateKpi;    
    
	/**
     * @return 客户号
     */
    public String getCustNo () {
        return custNo;
    }

    /**
     * @param custNo 客户号
     */
    public void setCustNo (String custNo) {
        this.custNo = custNo;
    }
    /**
     * @return 报表日期
     */
    public String getRptDate () {
        return rptDate;
    }

    /**
     * @param 报表日期 报表日期
     */
    public void setRptDate (String rptDate) {
        this.rptDate = rptDate;
    }
    /**
     * @return 五级分类
     */
    public String getFiveLvl () {
        return fiveLvl;
    }

    /**
     * @param fiveLvl 五级分类
     */
    public void setFiveLvl (String fiveLvl) {
        this.fiveLvl = fiveLvl;
    }
    /**
     * @return 净资产收益率
     */
    public Double getNetRate () {
        return netRate;
    }

    /**
     * @param netRate 净资产收益率
     */
    public void setNetRate (Double netRate) {
        this.netRate = netRate;
    }
    /**
     * @return 资产收益率
     */
    public Double getAssetRate () {
        return assetRate;
    }

    /**
     * @param assetRate 资产收益率
     */
    public void setAssetRate (Double assetRate) {
        this.assetRate = assetRate;
    }
    /**
     * @return 销售净利率
     */
    public Double getSaleNetRate () {
        return saleNetRate;
    }

    /**
     * @param saleNetRate 销售净利率
     */
    public void setSaleNetRate (Double saleNetRate) {
        this.saleNetRate = saleNetRate;
    }
    /**
     * @return 盈余现金保障倍数
     */
    public Double getProMul () {
        return proMul;
    }

    /**
     * @param proMul 盈余现金保障倍数
     */
    public void setProMul (Double proMul) {
        this.proMul = proMul;
    }
    /**
     * @return 资产负债率
     */
    public Double getDebtRate () {
        return debtRate;
    }

    /**
     * @param debtRate 资产负债率
     */
    public void setDebtRate (Double debtRate) {
        this.debtRate = debtRate;
    }
    /**
     * @return 流动比率
     */
    public Double getFlowRate () {
        return flowRate;
    }

    /**
     * @param flowRate 流动比率
     */
    public void setFlowRate (Double flowRate) {
        this.flowRate = flowRate;
    }
    /**
     * @return 速动比率
     */
    public Double getSpdRate () {
        return spdRate;
    }

    /**
     * @param spdRate 速动比率
     */
    public void setSpdRate (Double spdRate) {
        this.spdRate = spdRate;
    }
    /**
     * @return 现金比率
     */
    public Double getCashRate () {
        return cashRate;
    }

    /**
     * @param cashRate 现金比率
     */
    public void setCashRate (Double cashRate) {
        this.cashRate = cashRate;
    }
    /**
     * @return 利息保障倍数
     */
    public Double getInteMul () {
        return inteMul;
    }

    /**
     * @param inteMul 利息保障倍数
     */
    public void setInteMul (Double inteMul) {
        this.inteMul = inteMul;
    }
    /**
     * @return 现金流动负债比率
     */
    public Double getCashDebtRate () {
        return cashDebtRate;
    }

    /**
     * @param cashDebtRate 现金流动负债比率
     */
    public void setCashDebtRate (Double cashDebtRate) {
        this.cashDebtRate = cashDebtRate;
    }
    /**
     * @return 总资产周转率
     */
    public Double getTurnRate () {
        return turnRate;
    }

    /**
     * @param turnRate 总资产周转率
     */
    public void setTurnRate (Double turnRate) {
        this.turnRate = turnRate;
    }
    /**
     * @return 存货周转率
     */
    public Double getStockRate () {
        return stockRate;
    }

    /**
     * @param stockRate 存货周转率
     */
    public void setStockRate (Double stockRate) {
        this.stockRate = stockRate;
    }
    /**
     * @return 应收账款周转率
     */
    public Double getCollTurnRate () {
        return collTurnRate;
    }

    /**
     * @param collTurnRate 应收账款周转率
     */
    public void setCollTurnRate (Double collTurnRate) {
        this.collTurnRate = collTurnRate;
    }
    /**
     * @return 资产现金回收率
     */
    public Double getRecoverRate () {
        return recoverRate;
    }

    /**
     * @param recoverRate 资产现金回收率
     */
    public void setRecoverRate (Double recoverRate) {
        this.recoverRate = recoverRate;
    }

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public Double getNetRateKpi() {
		return netRateKpi;
	}

	public void setNetRateKpi(Double netRateKpi) {
		this.netRateKpi = netRateKpi;
	}

	public Double getAssetRateKpi() {
		return assetRateKpi;
	}

	public void setAssetRateKpi(Double assetRateKpi) {
		this.assetRateKpi = assetRateKpi;
	}

	public Double getSaleNetRateKpi() {
		return saleNetRateKpi;
	}

	public void setSaleNetRateKpi(Double saleNetRateKpi) {
		this.saleNetRateKpi = saleNetRateKpi;
	}

	public Double getProMulKpi() {
		return proMulKpi;
	}

	public void setProMulKpi(Double proMulKpi) {
		this.proMulKpi = proMulKpi;
	}

	public Double getDebtRateKpi() {
		return debtRateKpi;
	}

	public void setDebtRateKpi(Double debtRateKpi) {
		this.debtRateKpi = debtRateKpi;
	}

	public Double getFlowRateKpi() {
		return flowRateKpi;
	}

	public void setFlowRateKpi(Double flowRateKpi) {
		this.flowRateKpi = flowRateKpi;
	}

	public Double getSpdRateKpi() {
		return spdRateKpi;
	}

	public void setSpdRateKpi(Double spdRateKpi) {
		this.spdRateKpi = spdRateKpi;
	}

	public Double getCashRateKpi() {
		return cashRateKpi;
	}

	public void setCashRateKpi(Double cashRateKpi) {
		this.cashRateKpi = cashRateKpi;
	}

	public Double getInteMulKpi() {
		return inteMulKpi;
	}

	public void setInteMulKpi(Double inteMulKpi) {
		this.inteMulKpi = inteMulKpi;
	}

	public Double getCashDebtRateKpi() {
		return cashDebtRateKpi;
	}

	public void setCashDebtRateKpi(Double cashDebtRateKpi) {
		this.cashDebtRateKpi = cashDebtRateKpi;
	}

	public Double getTurnRateKpi() {
		return turnRateKpi;
	}

	public void setTurnRateKpi(Double turnRateKpi) {
		this.turnRateKpi = turnRateKpi;
	}

	public Double getStockRateKpi() {
		return stockRateKpi;
	}

	public void setStockRateKpi(Double stockRateKpi) {
		this.stockRateKpi = stockRateKpi;
	}

	public Double getCollTurnRateKpi() {
		return collTurnRateKpi;
	}

	public void setCollTurnRateKpi(Double collTurnRateKpi) {
		this.collTurnRateKpi = collTurnRateKpi;
	}

	public Double getRecoverRateKpi() {
		return recoverRateKpi;
	}

	public void setRecoverRateKpi(Double recoverRateKpi) {
		this.recoverRateKpi = recoverRateKpi;
	}

	public Integer getUpCnt() {
		return upCnt;
	}

	public void setUpCnt(Integer upCnt) {
		this.upCnt = upCnt;
	}

}