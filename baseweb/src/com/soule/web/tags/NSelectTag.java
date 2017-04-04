package com.soule.web.tags;

import org.apache.struts2.components.Select;
import org.apache.struts2.views.jsp.ui.SelectTag;

import com.soule.comm.CommConstants;

/**
 * @see Select
 */
public class NSelectTag extends SelectTag {

    private static final long serialVersionUID = 1L;
    private String codetype;
    private String format;
    private String validate;

    protected void populateParams() {
        super.populateParams();
        Select select = ((Select) component);
        select.setTemplate("nselect");
        if (codetype!=null) {
            select.setListKey("key");
            select.setListValue("value");
            select.setList("#attr." + CommConstants.ENUMS_MAP + "." + codetype + ".items");
            select.setEmptyOption(emptyOption);
        }
        if (validate != null ) {
            select.addParameter("validate", validate);
        }
    }
    public void setCodetype(String codetype) {
        this.codetype = codetype;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }
}
