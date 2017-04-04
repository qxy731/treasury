package com.soule.app.sys.jsptree;

import com.soule.base.service.ServiceException;

/**
 * 树状显示JSP业务操作
 */
public interface IJspTreeService {

    /**
     * 根据当前目录，获得子目录和文件
     */
    public JspTreeListFileOut listFile(JspTreeListFileIn in) throws ServiceException;


}
