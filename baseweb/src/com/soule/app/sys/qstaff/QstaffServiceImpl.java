package com.soule.app.sys.qstaff;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.app.sys.staff.StaffServiceImpl;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.comm.enu.VaildType;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.ObjectUtil;
/**
 * 选择客户公共页面业务操作
 */
@Service
public class QstaffServiceImpl implements IQstaffService {

	private static final Log logger = LogFactory.getLog(StaffServiceImpl.class);
    private String SYS_STAFF_COUNT= "staffquery.getSysStaffQueryCount";
    private String SYS_STAFF_LIST= "staffquery.getSysStaffQuery";
    @Autowired
    private IDefaultService defService;
    /**
     * 查询
     */
    public QstaffQueryOut query(QstaffQueryIn in)
        throws ServiceException {
        QstaffQueryOut out = new QstaffQueryOut();
        try {
            HashMap condition = new HashMap();
            QstaffBasePo staff=new QstaffBasePo();
            staff.setStaffId(in.getStaffId());
            staff.setStaffName(in.getStaffName());
            staff.setStaffLevel(in.getStaffLevel());
            staff.setStaffStatus(VaildType.VALID.getValue());
            //后续待修改
            staff.setOwnerUnitId("00000".equals(in.getOwnerUnitId())?"":in.getOwnerUnitId());
            staff.setSex(in.getSex());
            condition.put("staff", staff);
            List totalnum = defService.getIbatisMediator().find(this.SYS_STAFF_COUNT, condition);
            long total = 0L;
            if (!(ObjectUtil.isEmpty(totalnum))) {
                total = ((Long) totalnum.get(0)).longValue();
            }
            int pagesize = in.getInputHead().getPageSize();
            if (pagesize < 0) {
                pagesize = 10;
            }
            int pageno = in.getInputHead().getPageNo();
            int start = (pageno - 1) * pagesize;
            if (total > start) {
                List ret = defService.getIbatisMediator().find(this.SYS_STAFF_LIST, condition, start, pagesize);
                out.setBase(ret);
            }
            out.getResultHead().setTotal(total);
            AppUtils.setResult(out, "I0000");
        } catch (DbAccessException e) {
            logger.error("DB", e);
            AppUtils.setResult(out, "E0002");
        } catch (Exception e) {
            logger.error("SERVICE", e);
            AppUtils.setResult(out, "E0000");
        }
        return out;
    }
}