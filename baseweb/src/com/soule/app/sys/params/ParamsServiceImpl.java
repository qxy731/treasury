package com.soule.app.sys.params;

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
 * 参数配置业务操作
 */
@Service("ParamsService")
public class ParamsServiceImpl implements IParamsService {

    private final static Log logger = LogFactory.getLog(ParamsServiceImpl.class);

    // 数据库通用操作
    @Autowired
    private IDefaultService sDefault;

    /**
     * 查询获得系统中定义的系统参数值。
     */
    public ParamsQueryOut query(ParamsQueryIn in) throws ServiceException {
        ParamsQueryOut out = new ParamsQueryOut();
        // TODO
        try {
            long x = sDefault.getIbatisMediator().getRecordTotal("sys.params.getSysParams", in.getParams());
            int pageSize = in.getInputHead().getPageSize();
            int pageOffset = (in.getInputHead().getPageNo() - 1) * pageSize;
            if (pageOffset < x) {
                List<ParamsParamsPo> paramsList = sDefault.getIbatisMediator().find("sys.params.getSysParams",
                        in.getParams(), pageOffset, pageSize);
                out.setParams(paramsList);
            }
            out.getResultHead().setTotal(x);
        } catch (DbAccessException e) {
            logger.error("DB", e);
            AppUtils.setResult(out, MsgConstants.E0002, e.getMessage());
        } catch (Exception e) {
            logger.error("SERVICE", e);
            e.printStackTrace();
            AppUtils.setResult(out, MsgConstants.E0000);
        }
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }

    /**
     * 新增系统参数。
     */
    public ParamsInsertOut insert(ParamsInsertIn in) throws ServiceException {
        ParamsInsertOut out = new ParamsInsertOut();
        // TODO
        if (in.getParams() == null) {
            AppUtils.setResult(out, MsgConstants.E0001, "新增系统参数");
            return out;
        }
        try {
            ParamsParamsPo paramsPo = in.getParams();
            int c = sDefault.getIbatisMediator().save("sys.params.saveSysParams", paramsPo);
            AppUtils.setResult(out, MsgConstants.I0001, c);
        } catch (Exception e) {
            logger.error("DB", e);
            AppUtils.setResult(out, MsgConstants.E0002, e.getMessage());
        }
        return out;
    }

    /**
     * 删除系统参数。
     */
    public ParamsDeleteOut delete(ParamsDeleteIn in) throws ServiceException {
        ParamsDeleteOut out = new ParamsDeleteOut();
        try {
            Integer flag = 0;
            if (in != null && in.getDeletes() != null) {
                for (int i = 0; i < in.getDeletes().size(); i++) {
                    ParamsParamsPo po = in.getDeletes().get(i);
                    if (po.getParaId() != null) {
                        sDefault.getIbatisMediator().delete("sys.params.delSysParams", po.getParaId());
                    }
                    flag++;
                }
            }
            if (flag == in.getDeletes().size()) {
                AppUtils.setResult(out, MsgConstants.I0002);
            } else {
                AppUtils.setResult(out, MsgConstants.E0002);
            }
        } catch (DbAccessException e) {
            logger.error("DB", e);
            AppUtils.setResult(out, MsgConstants.E0002);
        } catch (Exception e) {
            logger.error("SERVICE", e);
            AppUtils.setResult(out, MsgConstants.E0000);
        }
        return out;

    }

    /**
     * 修改系统参数。
     */
    public ParamsUpdateOut update(ParamsUpdateIn in) throws ServiceException {
        ParamsUpdateOut out = new ParamsUpdateOut();
        ParamsParamsPo paramsPo = in.getParams();
        try {
            int x = sDefault.getIbatisMediator().update("sys.params.updSysParams", paramsPo);
            AppUtils.setResult(out, MsgConstants.I0003, x);
        } catch (DbAccessException e) {
            // TODO Auto-generated catch block
            logger.error("DB", e);
            AppUtils.setResult(out, MsgConstants.E0002, e.getMessage());
        }
        return out;
    }

    public ParamsQueryOut queryDetail(ParamsQueryIn in) throws ServiceException {
        // TODO Auto-generated method stub
        ParamsQueryOut out = new ParamsQueryOut();
        // TODO
        ParamsParamsPo params = in.getParams();
        try {
            ParamsParamsPo po = (ParamsParamsPo) sDefault.getIbatisMediator().findById("sys.params.getSysParamsByKey",
                    params);
            out.setParamsPo(po);
        } catch (DbAccessException e) {
            logger.error("DB", e);
            e.printStackTrace();
            AppUtils.setResult(out, MsgConstants.E0000);
        }
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }

    public ParamsParamsPo queryParamByParamId(String paraId) {
        ParamsParamsPo ret = null;
        try {
            ParamsParamsPo paramsPo = new ParamsParamsPo();
            paramsPo.setParaId(paraId);
            List<ParamsParamsPo> list = (List<ParamsParamsPo>) sDefault.getIbatisMediator().find(
                    "sys.params.getSysParamsByKey", paramsPo);
            if (list.size() > 0) {
                ret = list.get(0);
            }
        } catch (DbAccessException e) {
            logger.error("DB", e);
        } catch (Exception e) {
            logger.error("SERVICE", e);
        }
        return ret;
    }

    public ParamsParamsPo queryParams(String paraId, String paraRank) throws ServiceException {
        ParamsParamsPo ret = null;
        ParamsParamsPo paramsPo = new ParamsParamsPo();
        paramsPo.setParaId(paraId);
        paramsPo.setParaRank(paraRank);
        try {
            List<ParamsParamsPo> list = sDefault.getIbatisMediator().find("sys.params.getSysParams", paramsPo);
            if (list.size() > 0) {
                ret = list.get(0);
            }
        } catch (DbAccessException e) {
            logger.error("DB", e);
        } catch (Exception e) {
            logger.error("SERVICE", e);
        }
        return ret;
    }

}
