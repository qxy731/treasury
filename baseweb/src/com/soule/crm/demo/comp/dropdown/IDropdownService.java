package com.soule.crm.demo.comp.dropdown;

import com.soule.base.service.ServiceException;

/**
 * 机构下拉框业务操作
 */
public interface IDropdownService {

    public static final String BEAN_ID = "DropdownService";

    /**
     */
    public DropdownListOut list(DropdownListIn in) throws ServiceException;


}
