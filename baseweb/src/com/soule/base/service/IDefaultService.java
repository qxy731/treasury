package com.soule.base.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soule.base.media.IMediaForIbatis;
import com.soule.base.media.IMediaForJdbc;

/**
 * Service层的默认/基本接口<br>
 * 虽然属于Service层，但并不涉及任何业务， 原则上仅仅对持久层操作如增删，条件查询，更新等操进行转换
 */
public interface IDefaultService {

    public IMediaForJdbc getJdbcMediator();

    public IMediaForIbatis getIbatisMediator();

    /**
     * 根据主键获得表的一条记录
     * 
     * @param pk
     */
    public Object getRecordByKey(String tableName, Serializable pk);

    public List getRecordByMap(String tableName, Map condition);

    /**
     * 保存一条记录
     * 
     * @param tableName
     *            表名
     * @param record
     *            记录对象
     */
    public Boolean saveRecord(String tableName, Serializable record);

    /**
     * 根据主键修改一条记录
     * 
     * @param record
     *            记录对象
     */
    public Boolean updateRecord(String tableName, Serializable record);

    /**
     * 根据主键删除一条记录
     * 
     * @param tableName
     *            表名
     * @param pk
     *            主键对象
     */
    public Boolean deleteReocrd(String tableName, Serializable pk);
}
