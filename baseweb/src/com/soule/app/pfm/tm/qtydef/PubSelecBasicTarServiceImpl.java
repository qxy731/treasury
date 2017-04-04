package com.soule.app.pfm.tm.qtydef;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.ContextUtil;
import com.soule.comm.tools.ObjectUtil;

@Service("PubSelecBasicTarService")
public class PubSelecBasicTarServiceImpl extends QtyDefServiceImpl {
	private static final Log logger = LogFactory.getLog(QtyDefServiceImpl.class);
	private static final String GET_QTYDEF_COUNT = "qtydef.getQtyDefCount";
	private static final String GET_QTYDEF = "qtydef.getQtyDef";
	private static final String GET_PRODUCT_BYID = "prd.getProductById";
	
	@SuppressWarnings("unchecked")
	@Override
	public QtyDefQueryOut query(QtyDefQueryIn in) throws ServiceException {
		QtyDefQueryOut out = new QtyDefQueryOut();
		try {
			IDefaultService defService = (IDefaultService) ContextUtil
					.getBean("defaultService");
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("createOrg", in.getCreateOrg());
			condition.put("tarSortCode", in.getTarSortCode());
			QtyDefPo qtyDef = new QtyDefPo();
			qtyDef.setTarCode(in.getTarCode());
			qtyDef.setTarName(in.getTarName());
			qtyDef.setTarStatus(QtyDefPo.STATUS_INPUT);
			//qtyDef.setTarType(BaseTar.TAR_TYPE_BASE);
			
			qtyDef.setStoreDate(in.getStoreDate());
			condition.put("qtyDef", qtyDef);
			condition.put("dayMoney", "notDayMoney");
			List totalnum = defService.getIbatisMediator().find(
					GET_QTYDEF_COUNT, condition);
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
				List<QtyDefPo> ret = (List<QtyDefPo>) defService
						.getIbatisMediator().find(GET_QTYDEF, condition, start,
								pagesize);
				out.setQtyDef(ret);
			}
			out.getResultHead().setTotal(total);
			AppUtils.setResult(out, MsgConstants.I0000);
		} catch (DbAccessException e) {
			logger.error("DB", e);
			AppUtils.setResult(out, MsgConstants.E0002);
		} catch (Exception e) {
			logger.error("SERVICE", e);
			AppUtils.setResult(out, MsgConstants.E0000);
		}
		return out;
	}
	@Override
	public QtyDefQueryOut getQtyDefById(String tarCode) throws ServiceException, DbAccessException {
		QtyDefQueryOut out= super.getQtyDefById(tarCode);
		return out;
	}
}
