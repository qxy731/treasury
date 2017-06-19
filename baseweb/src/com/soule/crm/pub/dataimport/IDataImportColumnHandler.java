package com.soule.crm.pub.dataimport;

import java.util.HashMap;
import java.util.List;

/**
 * 行特殊处理的接口
 * @author crm-hh14
 *
 */
public interface IDataImportColumnHandler {

    /**
     * 
     * @param errors 错误
     * @param row 发生行 
     * @param model 列定义
     * @param value 原始数据值
     */
    Object handle(List errors, int row ,HashMap model, String value);

}
