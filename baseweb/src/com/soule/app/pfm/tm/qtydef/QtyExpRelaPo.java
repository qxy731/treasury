package com.soule.app.pfm.tm.qtydef;

import java.io.Serializable;

/**
 * 复合指标引用关联指标
 * @author Administrator
 *
 */
public class QtyExpRelaPo implements Serializable{
    private static final long serialVersionUID = 1711018232498417268L;
    private String tarCode;
    private String tarScope;
    private String relaTarCode;
    public QtyExpRelaPo(){}
    public QtyExpRelaPo(String tarCode,String tarScope,String relaTarCode){
        this.tarCode=tarCode;
        this.tarScope=tarScope;
        this.relaTarCode=relaTarCode;
    }
    public String getTarCode() {
        return tarCode;
    }
    public void setTarCode(String tarCode) {
        this.tarCode = tarCode;
    }
    public String getTarScope() {
        return tarScope;
    }
    public void setTarScope(String tarScope) {
        this.tarScope = tarScope;
    }
    public String getRelaTarCode() {
        return relaTarCode;
    }
    public void setRelaTarCode(String relaTarCode) {
        this.relaTarCode = relaTarCode;
    }

}