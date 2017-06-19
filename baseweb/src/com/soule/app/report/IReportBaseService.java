package com.soule.app.report;

import java.util.HashMap;
import java.util.List;

import com.soule.base.service.ServiceException;


/**
 * 统计报表业务操作
 */
public interface IReportBaseService {

    /**
     * 查询报表数据
     */
    public HashMap query(HashMap in,String sqlKey) throws ServiceException;

	public List initSel(HashMap params);

}
