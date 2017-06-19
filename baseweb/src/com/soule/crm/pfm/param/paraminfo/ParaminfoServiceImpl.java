package com.soule.crm.pfm.param.paraminfo;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * 指标信息业务操作
 */
@Service
public class ParaminfoServiceImpl implements IParaminfoService {

    private final static Log logger = LogFactory.getLog(ParaminfoServiceImpl.class);
    // 数据库通用操作
    @Autowired 
    IDefaultService sDefault;

    /**
     * 指标信息查询
     */
    public ParaminfoQueryOut query(ParaminfoQueryIn in)
        throws ServiceException {
        ParaminfoQueryOut out = new ParaminfoQueryOut();
        if (in == null) {
			AppUtils.setResult(out, MsgConstants.E0001, "");
			return out;
		}
        
        HashMap<String, String> map = new HashMap<String, String>(1);
        map.put("indexCode", in.getIndexCode());
        map.put("indexName", in.getIndexName());
		map.put("busiLine", in.getBusiLine());
		map.put("suitableObject", in.getSuitableObject());
		map.put("dataSource", in.getDataSource());

		int pageNo = in.getInputHead().getPageNo();
		int pageSize = in.getInputHead().getPageSize();
		int offset = (pageNo - 1) * pageSize;
		try {
			long total = sDefault.getIbatisMediator().getRecordTotal("paraminfo.getPfmParamIndex", map);

			if (total > offset) {
				List<ParaminfoQPo> list = sDefault.getIbatisMediator().find("paraminfo.getPfmParamIndex", map, offset, pageSize);
				out.getResultHead().setTotal(total);
				out.setQ(list);
			}
			AppUtils.setResult(out, MsgConstants.I0000);
		} catch (DbAccessException e) {
			logger.error("DB", e);
			AppUtils.setResult(out, MsgConstants.E0002, e.getMessage());
		}
        return out;
    }
    
    /**
     * 指标信息查询
     */
    public ParaminfoQueryOut queryAll(ParaminfoQueryIn in)
        throws ServiceException {
        ParaminfoQueryOut out = new ParaminfoQueryOut();
        if (in == null) {
			AppUtils.setResult(out, MsgConstants.E0001, "");
			return out;
		}
        
        HashMap<String, String> map = new HashMap<String, String>(1);
		//map.put("busiLine", in.getBusiLine());
		//map.put("suitableObject", in.getSuitableObject());
		map.put("dataSource", in.getDataSource());

		try {
			List<ParaminfoQPo> list = sDefault.getIbatisMediator().find("paraminfo.getPfmParamIndex", map);
			out.setQ(list);
			AppUtils.setResult(out, MsgConstants.I0000);
		} catch (DbAccessException e) {
			logger.error("DB", e);
			AppUtils.setResult(out, MsgConstants.E0002, e.getMessage());
		}
        return out;
    }

}
