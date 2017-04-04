package com.soule.app.sys.bizline.blval;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;
/**
 * 业务线分类值维护业务操作
 */
@Service
public class BlvalServiceImpl implements IBlvalService {

    private final static Log logger = LogFactory.getLog(BlvalServiceImpl.class);
    // 数据库通用操作
    @Autowired
    private IDefaultService sDefault;

    /**
     * 查询系统中定义的业务线分类
     */
    public BlvalQueryOut query(BlvalQueryIn in)
        throws ServiceException {
        BlvalQueryOut out = new BlvalQueryOut();
        try {
            int offset = (in.getInputHead().getPageNo() -1 )*in.getInputHead().getPageSize();
            int pagesize = in.getInputHead().getPageSize();
            long cl =  sDefault.getIbatisMediator().getRecordTotal("bizline.getSysBizValue", in.getBizTypeId());
            if (cl > 0) {
                List<BlvalBizValPo> list = sDefault.getIbatisMediator().find("bizline.getSysBizValue",in.getBizTypeId(),offset,pagesize);
                out.setBizVal(list);
                out.getResultHead().setTotal(cl);
            }
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002,e.getMessage());
        } catch (Exception e) {
            logger.error("SYSTEM",e);
            AppUtils.setResult(out, MsgConstants.E0000,e.getMessage());
        }
        return out;
    }
    /**
     * 修改业务线分类信息
     */
    public BlvalModifyOut modify(BlvalModifyIn in)
        throws ServiceException {
        BlvalModifyOut out = new BlvalModifyOut();
        BlvalBizValPo upo = in.getBizVal();
        try {
            AppUtils.setRecordStatus(upo);
            int x = sDefault.getIbatisMediator().update("bizline.updSysBizValue", upo);
            AppUtils.setResult(out, MsgConstants.I0003,x);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002,e.getMessage());
        }
        return out;
    }
    /**
     * 删除业务线分类信息前，判断该业务线是否包业务关系表中的数据
     */
    public BlvalValidDataOut validData(BlvalValidDataIn in) throws ServiceException {
        BlvalValidDataOut out = new BlvalValidDataOut();
        try {
            BlvalBizValPo po = new BlvalBizValPo();
            po.setBizTypeId(in.getBizTypeId());
            po.setBizValue(in.getBizValue());
            long x = sDefault.getIbatisMediator().getRecordTotal("bizline.getSysBizMap", po);
            BlvalResultPo rpo = new BlvalResultPo();
            rpo.setBizMapCount((int) x);
            out.setResult(rpo);
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (DbAccessException e) {
            logger.error("DB", e);
            AppUtils.setResult(out, MsgConstants.E0002, e.getMessage());
        }
        return out;
    }
    /**
     * 删除业务线分类信息，包括业务关系表中的数据
     */
    public BlvalDeleteOut delete(BlvalDeleteIn in)
        throws ServiceException {
        BlvalDeleteOut out = new BlvalDeleteOut();
        try {
            BlvalBizValPo dpo = new BlvalBizValPo();
            dpo.setBizTypeId(in.getBizTypeId());
            dpo.setBizValue(in.getBizValue());
            sDefault.getIbatisMediator().delete("bizline.delSysBizMapByVal", dpo);
            sDefault.getIbatisMediator().delete("bizline.delSysBizValue", dpo);
            AppUtils.setResult(out, MsgConstants.I0002);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002,e.getMessage());
        }
        return out;
    }
    /**
     * 新增业务线分类
     */
    public BlvalInsertOut insert(BlvalInsertIn in)
        throws ServiceException {
        BlvalInsertOut out = new BlvalInsertOut();
        BlvalBizValPo npo = in.getBizVal();
        if (npo == null) {
            AppUtils.setResult(out, MsgConstants.E0001,"新增业务分类");
            return out;
        }
        try {
            AppUtils.initRecordStatus(npo);
            int c = sDefault.getIbatisMediator().save("bizline.saveSysBizValue",npo);
            AppUtils.setResult(out, MsgConstants.I0001, c);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002,e.getMessage());
        }
        return out;
    }

}
