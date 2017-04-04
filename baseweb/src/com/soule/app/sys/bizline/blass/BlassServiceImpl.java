package com.soule.app.sys.bizline.blass;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.app.sys.bizline.blval.BlvalBizValPo;
import com.soule.app.sys.qstaff.IQstaffService;
import com.soule.app.sys.qstaff.QstaffBasePo;
import com.soule.app.sys.qstaff.QstaffQueryIn;
import com.soule.app.sys.qstaff.QstaffQueryOut;
import com.soule.base.media.DbAccessException;
import com.soule.base.po.BizLinePo;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.StringUtil;
/**
 * 业务线人员分配业务操作
 */
@Service
public class BlassServiceImpl implements IBlassService {

    private final static Log logger = LogFactory.getLog(BlassServiceImpl.class);
    // 数据库通用操作
    @Autowired
    private IDefaultService sDefault;
    @Autowired
    private IQstaffService sStaff;
    /**
     * 查询业务线中的人员
     */
    @SuppressWarnings("unchecked")
    public BlassQueryOut query(BlassQueryIn in)
        throws ServiceException {
        BlassQueryOut out = new BlassQueryOut();
        try {
            int offset = (in.getInputHead().getPageNo() -1 )*in.getInputHead().getPageSize();
            int pagesize = in.getInputHead().getPageSize();
            BlvalBizValPo po = new BlvalBizValPo();
            po.setBizTypeId(in.getBizTypeId());
            po.setBizValue(in.getBizValueId());
            long cl =  sDefault.getIbatisMediator().getRecordTotal("bizline.getSysBizMap", po);
            if (cl > 0) {
                List<BlassBizassPo> list = sDefault.getIbatisMediator().find("bizline.getSysBizMap",po,offset,pagesize);
                out.setBizass(list);
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
     * 新增业务线中的人员
     */
    public BlassInsertOut insert(BlassInsertIn in) throws ServiceException {
        BlassInsertOut out = new BlassInsertOut();
        List<BlassBizassPo> npo = in.getBizass();
        if (npo == null) {
            AppUtils.setResult(out, MsgConstants.E0001,"新增业务线关联关系");
            return out;
        }
        try {
            int count = 0 ;
            for (int i = 0 ;i < npo.size();i++) {
                BlassBizassPo epo = (BlassBizassPo) sDefault.getIbatisMediator().findById("bizline.getSysBizMapByKey",npo.get(i));
                if (epo==null){
                    AppUtils.initRecordStatus(npo.get(i));
                    int c = sDefault.getIbatisMediator().save("bizline.saveSysBizMap",npo.get(i));
                    count += c;
                }
            }
            AppUtils.setResult(out, MsgConstants.I0001, count);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002,e.getMessage());
        }
        return out;
    }
    /**
     * 删除业务线中的人员
     */
    public BlassDeleteOut delete(BlassDeleteIn in)
        throws ServiceException {
        BlassDeleteOut out = new BlassDeleteOut();
        try {
            int count = 0;
            for (int i = 0 ;i < in.getBizass().size();i++) {
                int c = sDefault.getIbatisMediator().delete("bizline.delSysBizMap", in.getBizass().get(i));
                count += c;
            }
            AppUtils.setResult(out, MsgConstants.I0002,count);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002,e.getMessage());
        }
        return out;
    }

    /**
     * 删除业务线中全部人员
     */
    public BlassDeleteAllOut deleteAll(BlassDeleteAllIn in)
        throws ServiceException {
        BlassDeleteAllOut out = new BlassDeleteAllOut();
        if (StringUtil.isEmpty(in.getBizTypeId())) {
            AppUtils.setResult(out, MsgConstants.E0001,"业务线类型");
            return out;
        }
        if (StringUtil.isEmpty(in.getBizValueId())) {
            AppUtils.setResult(out, MsgConstants.E0001,"业务线分类");
            return out;
        }
        try {
            int count = sDefault.getIbatisMediator().delete("bizline.delSysBizMapAll", in);
            AppUtils.setResult(out, MsgConstants.I0002,count);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002,e.getMessage());
        }
        return out;
    }
    /**
     * 业务线中添加查询结果中全部人员
     */
    public BlassInsertAllOut insertAll(BlassInsertAllIn in)
        throws ServiceException {
        BlassInsertAllOut out = new BlassInsertAllOut();
        if (StringUtil.isEmpty(in.getBizTypeId())) {
            AppUtils.setResult(out, MsgConstants.E0001,"业务线类型");
            return out;
        }
        if (StringUtil.isEmpty(in.getBizValueId())) {
            AppUtils.setResult(out, MsgConstants.E0001,"业务线分类");
            return out;
        }
        try {
            QstaffQueryIn qsi = new QstaffQueryIn();
            qsi.getInputHead().setPageSize(Integer.MAX_VALUE);
            qsi.setOwnerUnitId(in.getOwerUnitId());
            qsi.setSex(in.getSex());
            qsi.setStaffId(in.getStaffId());
            qsi.setStaffLevel(in.getStaffLevel());
            qsi.setStaffName(in.getStaffName());
            QstaffQueryOut qout = sStaff.query(qsi);
            int count = 0 ;
            if (qout.getResultHead().isSuccess()) {
                List<QstaffBasePo> staffs = qout.getBase();
                BlassBizassPo param = new BlassBizassPo();
                param.setBizValue(in.getBizValueId());
                param.setBizTypeId(in.getBizTypeId());
                for (int i = 0 ;i < staffs.size();i++) {
                    param.setStaffId(staffs.get(i).getStaffId());
                    BlassBizassPo epo = (BlassBizassPo) sDefault.getIbatisMediator().findById("bizline.getSysBizMapByKey",param);
                    if (epo==null){
                        AppUtils.initRecordStatus(param);
                        int c = sDefault.getIbatisMediator().save("bizline.saveSysBizMap",param);
                        count += c;
                    }
                }
            }
            AppUtils.setResult(out, MsgConstants.I0001, count);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002,e.getMessage());
        }
        return out;
    }
    /**
     * 查询人员的业务线
     */
    public BlassQueryByStaffOut queryByStaff(BlassQueryByStaffIn in)
        throws ServiceException {
        BlassQueryByStaffOut out = new BlassQueryByStaffOut();
        if (in == null || StringUtil.isEmpty(in.getStaffId())) {
            AppUtils.setResult(out, MsgConstants.E0001,"员工号");
            return out;
        }
        List<BizLinePo> list;
        try {
            list = sDefault.getIbatisMediator().find("bizline.getAllBizLineByStaff", in.getStaffId());
            out.setBizass(list);
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002,e.getMessage());
        }
        return out;
    }
}
