package com.soule.app.pfm.tm.qtydef;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.soule.MsgConstants;
import com.soule.app.pfm.tm.BaseTar;
import com.soule.app.pfm.tm.tools.TarCodeUtils;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ILogonUserInfo;
import com.soule.base.service.ServiceException;
import com.soule.base.service.keygen.IKeyGenerator;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.ObjectUtil;
import com.soule.crm.pfm.dsl.parser.TokenException;
import com.soule.crm.pfm.idl.translator.Translator;

@Service("qtyDefService")
public class QtyDefServiceImpl implements IQtyDefService {
	private static final Log logger = LogFactory.getLog(QtyDefServiceImpl.class);
	private static final String INSERT_QTYDEF = "qtydef.addQtyDef";
	private static final String INSERT_QTYEXP_RELA = "qtydef.addQtyExpRela";
	private static final String INSERT_QTYEXP="qtydef.addQtyExp";
	// 添加时点指标关联日均指标
	//private static final String INSERT_POINTAVER = "qtydef.addPointAverRela";
	private static final String GET_QTYDEF = "qtydef.getQtyDef";
	private static final String GET_ONE_QTYDEF = "qtydef.getOneQtyDef";
	private static final String UPD_QTYDEF = "qtydef.updQtyDef";
	private static final String UPD_STATUS_DEL = "qtydef.updQtyForStatus";
	private static final String GET_QTYDEF_BYSORT = "qtydef.getQtyDefBySort";
	private static final String GET_QTYDEF_BYTARNAME="qtydef.getQtyDefByTarName";
	public static final String TABLE_NAME="PFM_TM_QTY_DEF";
	@Autowired
	private IDefaultService  defService;
	
	 @Autowired
	 private IKeyGenerator keyg;
	
	/**
	 * 保存基础或衍生指标
	 * @param newQtyDef
	 * @param qtyExp
	 * @return
	 * @throws ServiceException
	 * @throws DbAccessException
	 */
	public QtyDefInsertOut insert(QtyDefPo newQtyDef,QtyExpDefPo qtyExp) throws ServiceException, DbAccessException{
		TarCodeUtils tarCodeUtils=new TarCodeUtils(keyg,TABLE_NAME);
		QtyDefInsertOut out = new QtyDefInsertOut();
		String tarType = newQtyDef.getTarType();// 指标类型
		if (!ObjectUtil.isEmpty(newQtyDef)) {
			String tarCode = tarCodeUtils.gerneratedKey(BaseTar.getEcmByTartype(newQtyDef));
			newQtyDef.setTarCode(tarCode);
			ILogonUserInfo logonInfo = (ILogonUserInfo)AppUtils.getLogonUserInfo();
			newQtyDef.setCreateUser(logonInfo.getUser().getUserID());
			newQtyDef.setCreateTime(new Date());
			newQtyDef.setCreateOrg(logonInfo.getOperUnitId());
			if(BaseTar.TAR_TYPE_MIX.equals(tarType)){
			    qtyExp.setTarCode(newQtyDef.getTarCode());//指标代码 
			    qtyExp.setTarScope(newQtyDef.getTarScope());
	            String strExp=qtyExp.getCalcExp();
	            String sqlExp = this.getSqlExp(qtyExp);
                qtyExp.setSqlExp(sqlExp);
                defService.getIbatisMediator().save(INSERT_QTYEXP,qtyExp);
                this.saveQtyExpRela(newQtyDef,strExp);
			}
			defService.getIbatisMediator().save(INSERT_QTYDEF,newQtyDef);
			out.setTarCode(newQtyDef.getTarCode());
		}
		//AppUtils.saveAuditLog("pfm.tm.qtydef","定量指标维护新增",newQtyDef.getTarCode(), BizType.STAFF, FunctionType.INSERT,ExecuteResult.SUCCESS);
		AppUtils.setResult(out,MsgConstants.I_PFM_0003);
		return out;
	}

	/**
	 * 修改定量指标
	 */
	public QtyDefUpdateOut update(QtyDefPo qtydef,QtyExpDefPo qtyExp) throws ServiceException,DbAccessException {
		QtyDefUpdateOut out = new QtyDefUpdateOut();
		try {
            if (!ObjectUtil.isEmpty(qtydef)) {
            	if(BaseTar.TAR_TYPE_BASE.equals(qtydef.getTarType())){
            	    updateBaseTar(qtydef);
            	}else if(BaseTar.TAR_TYPE_MIX.equals(qtydef.getTarType())){
            	    this.updateMixTar(qtydef, qtyExp);
            	}
    			//AppUtils.saveAuditLog("pfm.tm.qtydef","定量指标维护更新",qtydef.getTarCode(), BizType.STAFF, FunctionType.UPDATE,ExecuteResult.SUCCESS);
            	AppUtils.setResult(out,MsgConstants.I_PFM_0001);
            }
        } catch (DbAccessException e) {
        	e.printStackTrace();
            AppUtils.setResult(out,MsgConstants.E_PFM_0003);
        }catch (ServiceException e) {
        	e.printStackTrace();
            AppUtils.setResult(out,e.getErrorCode(),e.getErrorMsg());
        }catch (Exception e) {
        	e.printStackTrace();
            AppUtils.setResult(out,MsgConstants.E_PFM_0002);
        }
		return out;
	}
	
	/**
	 * 
	 * @param qtydef
	 * @param qtyExp
	 * @throws ServiceException 
	 * @throws DbAccessException 
	 * @throws Exception
	 */
	private void updateMixTar(QtyDefPo qtydef,QtyExpDefPo qtyExp) throws ServiceException, DbAccessException {
		Map<String,Object> expMap = new HashMap<String,Object>();
		String sqlExp = this.getSqlExp(qtyExp);
        qtyExp.setSqlExp(sqlExp);
		expMap.put("calcExp",qtyExp.getCalcExp());
		expMap.put("sqlExp",qtyExp.getSqlExp());
		expMap.put("lastUpdOrg", qtyExp.getLastUpdOrg());
		expMap.put("lastUpdTime",qtyExp.getLastUpdTime());
		expMap.put("lastUpdUser",qtyExp.getLastUpdUser());
		expMap.put("tarCode",qtyExp.getTarCode());
		defService.getIbatisMediator().update("qtydef.updQtyExpDefPo",expMap);//衍生指标定义表
		defService.getIbatisMediator().update(UPD_QTYDEF, qtydef);//指标定义表
		//衍生指标引用关联指标
		//defService.getIbatisMediator().delete("qtydef.delQtyExpRela", qtydef.getTarCode());
		this.saveQtyExpRela(qtydef,qtyExp.getCalcExp());
	}
	
	/**
	 * 根据calcExp获取sqlExp
	 * @param qtyExp
	 * @return
	 * @throws ServiceException
	 */
	private String getSqlExp(QtyExpDefPo qtyExp) throws ServiceException{
        try {
        	
        	Translator tlr = Translator.getInstance();
            String strExp=qtyExp.getCalcExp();
			String sqlExp = tlr.translate(strExp,new BitSet(8),"PeriodType","Current","");
			System.out.println(sqlExp);
			return sqlExp;
		} catch (TokenException e) {
			e.printStackTrace();
			throw new ServiceException("E0001",e.getMessage());
		}
	}
	
	private void updateBaseTar(QtyDefPo qtydef) throws DbAccessException {
      defService.getIbatisMediator().update(UPD_QTYDEF, qtydef);
    }
	/**
	 * 查询定量指标
	 */
	public QtyDefQueryOut query(QtyDefQueryIn in) throws ServiceException {
		QtyDefQueryOut out = new QtyDefQueryOut();
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("createOrg", in.getCreateOrg());
			condition.put("tarSortCode", in.getTarSortCode());
			QtyDefPo qtyDef = new QtyDefPo();
			qtyDef.setTarCode(in.getTarCode());
			qtyDef.setTarName(in.getTarName());
			qtyDef.setTarScope(in.getTarScope());
			qtyDef.setTarStatus(QtyDefPo.STATUS_INPUT);
			qtyDef.setCreateOrg(in.getCreateOrg());
			qtyDef.setTarSortCode(in.getTarSortCode());
			qtyDef.setTarType(in.getTarType());
			qtyDef.setDataSource(in.getDataSource());
			condition.put("qtyDef", qtyDef);
			long total = defService.getIbatisMediator().getRecordTotal(GET_QTYDEF, condition);
			int pagesize = in.getInputHead().getPageSize();
			if (pagesize < 0) {
				pagesize = 10;
			}
			int pageno = in.getInputHead().getPageNo();
			int start = (pageno - 1) * pagesize;
			if (total > start) {
				List<QtyDefPo> ret = (List<QtyDefPo>)defService.getIbatisMediator().find(GET_QTYDEF, condition, start,pagesize);
				out.setQtyDef(ret);
			}
			out.getResultHead().setTotal(total);
			//AppUtils.saveAuditLog("pfm.tm.qtydef","定量指标维护查询",AppUtils.getLogonUserInfo().getUser().getUserID(), BizType.STAFF, FunctionType.QUERY,ExecuteResult.SUCCESS);
			AppUtils.setResult(out, MsgConstants.I0000);
		} catch (DbAccessException e) {
			logger.error("DB", e);
			AppUtils.setResult(out, MsgConstants.E0002);
		}catch (ServiceException e) {
			logger.error("DB", e);
			AppUtils.setResult(out,e.getErrorCode(),e.getErrorMsg());
		}catch (Exception e) {
			logger.error("DB", e);
			AppUtils.setResult(out,MsgConstants.E0002);
		}
		return out;
	}

	/**
	 * 根据Id查询指标
	 * @throws DbAccessException 
	 */
    public QtyDefQueryOut getQtyDefById(String tarCode) throws ServiceException, DbAccessException {
		QtyDefQueryOut out = new QtyDefQueryOut();
		try {
			QtyDefPo qtyDef = (QtyDefPo) defService.getIbatisMediator().findById(GET_ONE_QTYDEF, tarCode);
			out.setOneQtyDef(qtyDef);
			if(BaseTar.TAR_TYPE_MIX.equals(qtyDef.getTarType())){
				QtyExpDefPo qtyExp = (QtyExpDefPo) defService.getIbatisMediator().findById("qtydef.getQtyExpDefPo", tarCode);
				out.setQtyExp(qtyExp);
			}
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
	
	/**
	 * 定量考评指标定义表：PFM_TM_QTY_DEF
	 * 复合指标定义表：PFM_TM_QTY_EXP_DEF
	 * 复合指标引用关联指标： PFM_TM_QTY_EXP_RELA
	 */
	public QtyDefDeleteOut delete(QtydefDeleteIn in) throws ServiceException,DbAccessException {
		QtyDefDeleteOut out = new QtyDefDeleteOut();
		int count = 0;
		List<QtyDefPo> list = in.getDeletes();
		List<String> tarList = new ArrayList<String>();
		StringBuffer sb =  new StringBuffer();
		if (!ObjectUtil.isEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				QtyDefPo po = list.get(i);
				String tarCode = po.getTarCode();
				String tarName = po.getTarName();
				//1.是否被其他指标引用
				long relaCount = defService.getIbatisMediator().getRecordTotal("qtydef.getQtyExpRela", tarCode);
				//2.如果被引用则抛出异常，提示：此指标被其他指标引用不能删除
				if(relaCount>0){
					sb.append(tarName+"指标被其他指标引用;\n");
					continue;
				}else{
					tarList.add(tarCode);
				}
			}
			if(sb.length()>0){
				AppUtils.setResult(out, MsgConstants.E_PFM_0000,sb.toString());
				return out;
			}
			if(tarList.isEmpty()){
				AppUtils.setResult(out, MsgConstants.E_PFM_0000,"没有一条记录被删除");
				return out;
			}
			//更新指标状态
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tarCodeList", tarList);
			params.put("tarStatus", QtyDefPo.STATUS_DEL);
			count = defService.getIbatisMediator().update(UPD_STATUS_DEL,params);
		}
		if (count == 0) {
			AppUtils.setResult(out, MsgConstants.E_PFM_0000,"没有一条记录被删除");
		} else {
			AppUtils.setResult(out, MsgConstants.I0000);
		}
		return out;
	}

	public int batchInsert(String sqlId, Object[] paraObjects)	throws SQLException, DbAccessException {
		if (paraObjects == null || paraObjects.length == 0) {
			return 0;
		}
		SqlMapClient client = defService.getIbatisMediator().getSqlMapClientTemplate().getSqlMapClient();
		client.startBatch();
		for (Object o : paraObjects) {
			if (o != null)
				client.insert(sqlId, o);
		}
		return client.executeBatch();
	}

	public int batchInsert(String sqlId, Collection paraCollection)	throws SQLException, DbAccessException {
		if (paraCollection == null || paraCollection.size() == 0) {
			return 0;
		}
		Object[] paraObjects = new Object[paraCollection.size()];
		paraObjects = paraCollection.toArray(paraObjects);
		return batchInsert(sqlId, paraObjects);
	}

	
	public List<QtyDefPo> getQtyDefListBySortCode(String tarSortCode) throws ServiceException {
			try {
				 List<QtyDefPo> ret = (List<QtyDefPo>) defService.getIbatisMediator().find(GET_QTYDEF_BYSORT, tarSortCode);
			    return ret;
			} catch (DbAccessException e) {
			    logger.error("DB", e);
			    throw new ServiceException(MsgConstants.E0002,"DB Is Error");
			} catch (Exception e) {
			    logger.error("SERVICE", e);
			    throw new ServiceException(MsgConstants.E0000,"Server Is Error");
			}
	    }
	    public QtyDefPo getQtyDefByTarName(String tarName){
	        try {
	            List<QtyDefPo> qtydefs =(List<QtyDefPo>) defService.getIbatisMediator().find(GET_QTYDEF_BYTARNAME, tarName);
	            if(qtydefs!=null&&qtydefs.size()>0){
	                return qtydefs.get(0);
	            }
	            return null;    
	        } catch (DbAccessException e) {
	            logger.error("DB", e);
	        } 
	        return null;
	    }
	
	 
	 /**
	  * 验证引用指标周期是否和保存日期一致
	  * @param cycleDate
	  * @param targetSet
	  * @return
	  * @throws ServiceException
	  */
	/* private Boolean checkCycleDate(String cycleDate,Set targetSet) throws ServiceException {
			java.util.Iterator it = targetSet.iterator();
			String targetCode, dataCycle;
			Boolean flag = false;
			QtyDefPo indicator;
			while (it.hasNext()) {
				targetCode = (String) it.next();
				targetCode = targetCode.substring(1, targetCode.length() - 1); // 带有引号
				indicator = engine.getModelById(targetCode);
				dataCycle = indicator.getStoreDate();
				if (dataCycle != null && !dataCycle.equals(cycleDate)){
					flag = true;
					break;
				}
			}
			return flag;
		}*/
	 
	 /**
	  * 保存衍生指标引用关联指标
	  * @param indicator
	  * @param calcExp
	  * @throws ServiceException
	  * @throws DbAccessException
	  */
	 public void saveQtyExpRela(QtyDefPo indicator,String calcExp) throws ServiceException, DbAccessException {
		 try {
		 	Translator tlr = Translator.getInstance();
		 	Set set = tlr.translate01(calcExp);
		 	defService.getIbatisMediator().delete("qtydef.delQtyExpRela",indicator.getTarCode());
			Iterator it = set.iterator();
			String targetCode;
			QtyExpRelaPo expRela=new QtyExpRelaPo();
            expRela.setTarCode(indicator.getTarCode());
			while (it.hasNext()) {
				targetCode = (String) it.next();
				//targetCode = targetCode.substring(1, targetCode.length() - 1);//带有引号
                expRela.setRelaTarCode(targetCode);
                defService.getIbatisMediator().save(INSERT_QTYEXP_RELA,expRela);
			}
		} catch (TokenException e) {
			//e.printStackTrace();
			throw new ServiceException("E0000","保存复合指标引用关联指标出现异常。");
		}
	}
}