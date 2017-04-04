package com.soule.web.tags;

import org.apache.struts2.components.TextField;
import org.apache.struts2.views.jsp.ui.TextFieldTag;

public class NumberTag extends TextFieldTag {

    private static final long serialVersionUID = 1L;

    private String pre = "";
    private int cnt = 3;
    private int dec = 2;
    private String valCN = "false";
    private String suf = "";
    private String validate;
    private String cssClass = "numberClass";

    protected void populateParams() {
        super.populateParams();
        TextField textField = ((TextField) component);
        textField.setTemplate("nnumber");// 模板
        textField.addParameter("pre", pre);
        textField.addParameter("cnt", cnt);
        textField.addParameter("dec", dec);
        textField.addParameter("suf", suf);
        textField.addParameter("valCN", valCN);
        if(!cssClass.equals("numberClass")){
            cssClass = "numberClass" + " " + cssClass;
        }
        textField.addParameter("cssClass", cssClass);
        if (validate != null) {
            textField.addParameter("validate", validate);
        }
    }

    public String getPre() {
        return pre;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public String getSuf() {
        return suf;
    }

    public void setSuf(String suf) {
        this.suf = suf;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int getDec() {
        return dec;
    }

    public void setDec(int dec) {
        this.dec = dec;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public String getValCN() {
        return valCN;
    }

    public void setValCN(String valCN) {
        this.valCN = valCN;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

}
