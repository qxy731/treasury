package com.soule.app.sys.gname;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.app.table.SysStaffPo;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;

/**
 * <!-- 取名的服务名约定名称为 [类型名]+GetNameService -->
 * @author wuwei
 *
 */
@Service("StaffGetNameService")
public class GetStaffNameServiceImpl implements IGetNameService {
    private final static Log logger = LogFactory.getLog(GetStaffNameServiceImpl.class);

    @Autowired
    private IDefaultService defService;
    /**
     * @param finalValue
     *            根据value在值栈中查找过后 产生的值
     */
    public String queryResult(String finalValue) throws ServiceException {
        SysStaffPo model;
        HashMap<String, String> in = new HashMap<String, String>();
        in.put("staffId", finalValue);
        String queryKey = "getProps.getSysStaffByKey";
        try {
            model = (SysStaffPo) defService.getIbatisMediator().findById(queryKey, in);
        } catch (DbAccessException e) {
            logger.error("SERVICE", e);
            throw new ServiceException(MsgConstants.E0002, e.getMessage());
        }
        return model.getStaffName();
    }
}
