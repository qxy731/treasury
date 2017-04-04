package com.soule.app.sys.bizline.bltype;

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
 * 业务线类型维护业务操作
 */
@Service
public class BltypeServiceImpl implements IBltypeService {

    private final static Log logger = LogFactory.getLog(BltypeServiceImpl.class);
    @Autowired
    private IDefaultService sDefault;
    /**
     * 查询系统中定义的业务线类型
     */
    @SuppressWarnings("unchecked")
    public BltypeQueryOut query(BltypeQueryIn in)
        throws ServiceException {
        BltypeQueryOut out = new BltypeQueryOut();
        try {
            List<BltypeBizTypePo> list = sDefault.getIbatisMediator().find("bizline.getAllSysBizType",new BltypeBizTypePo());
            out.setBizType(list);
            out.getResultHead().setTotal(list.size());
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
     * 修改业务线类型信息
     */
    public BltypeModifyOut modify(BltypeModifyIn in)
        throws ServiceException {
        BltypeModifyOut out = new BltypeModifyOut();
        BltypeBizTypePo upo = in.getBizType();
        try {
            AppUtils.setRecordStatus(upo);
            int x = sDefault.getIbatisMediator().update("bizline.updSysBizType", upo);
            AppUtils.setResult(out, MsgConstants.I0003,x);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002,e.getMessage());
        }
        return out;
    }
    /**
     * 删除业务线类型信息，包括业务线类型值和业务关系表中的数据
     */
    public BltypeDeleteOut delete(BltypeDeleteIn in)
        throws ServiceException {
        BltypeDeleteOut out = new BltypeDeleteOut();
        try {
            sDefault.getIbatisMediator().delete("bizline.delSysBizMapByType", in.getBizTypeId());
            sDefault.getIbatisMediator().delete("bizline.delSysBizValueByType", in.getBizTypeId());
            sDefault.getIbatisMediator().delete("bizline.delSysBizType", in.getBizTypeId());
            AppUtils.setResult(out, MsgConstants.I0002);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002,e.getMessage());
        }
        return out;
    }
    /**
     * 删除业务线类型信息前，判断该业务线是否包含括业务线类型值和业务关系表中的数据
     */
    public BltypeValidDataOut validData(BltypeValidDataIn in)
        throws ServiceException {
        BltypeValidDataOut out = new BltypeValidDataOut();
        try {
           BltypeResultPo po = new BltypeResultPo();
           long i =  sDefault.getIbatisMediator().getRecordTotal("bizline.getSysBizValue", in.getBizTypeId());
           po.setBizValueCount( (int) i);
           po.setBizMapCount(new Integer(0));
           if (po.getBizValueCount().intValue() > 0) {
               long x = sDefault.getIbatisMediator().getRecordTotal("bizline.getSysBizMapByBizType", in.getBizTypeId());
               po.setBizMapCount((int) x);
           }
           out.setResult(po);
           AppUtils.setResult(out, MsgConstants.I0000);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002,e.getMessage());
        }
        return out;
    }
    /**
     * 新增业务线类型
     */
    public BltypeInsertOut insert(BltypeInsertIn in)  throws ServiceException {
        BltypeInsertOut out = new BltypeInsertOut();
        BltypeBizTypePo npo = in.getBizType();
        if (npo == null) {
            AppUtils.setResult(out, MsgConstants.E0001,"新增业务线类型");
            return out;
        }
        try {
            AppUtils.initRecordStatus(npo);
            int c = sDefault.getIbatisMediator().save("bizline.saveSysBizType",npo);
            AppUtils.setResult(out, MsgConstants.I0001, c);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002,e.getMessage());
        }
        return out;
    }

}
