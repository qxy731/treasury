package com.soule.app.pfm.tm.qtydef;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
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
import com.soule.comm.tools.StringUtil;

@Service("qtyDefService")
public class QtyDefServiceImpl implements IQtyDefService {
	private static final Log logger = LogFactory.getLog(QtyDefServiceImpl.class);
	private static final String INSERT_QTYDEF = "qtydef.addQtyDef";
	//private static final String INSERT_QTYEXP_RELA = "qtydef.addQtyExpRela";
	private static final String INSERT_QTYEXP="qtydef.addQtyExp";
	// 添加时点指标关联日均指标
	private static final String INSERT_POINTAVER = "qtydef.addPointAverRela";
	private static final String GET_QTYDEF_COUNT = "qtydef.getQtyDefCount";
	private static final String GET_QTYDEF = "qtydef.getQtyDef";
	private static final String GET_ONE_QTYDEF = "qtydef.getOneQtyDef";
	private static final String UPD_QTYDEF = "qtydef.updQtyDef";
	private static final String UPD_STATUS_DEL = "qtydef.updQtyForStatus";
	private static final String GET_QTYDEF_BYSORT = "qtydef.getQtyDefBySort";
	private static final String GET_QTYDEF_BYTARNAME="qtydef.getQtyDefByTarName";
	//要把指标查询日均范围
	private static final String GET_POINTAVERRELA_BYPONTTAR="qtydef.getPointAverRela";
	//private static final String GET_POINTAVERRELA_BYPONTTARDAY="qtydef.getPointByPtarCodeDayScope";
	public static final String IND_TYPE_HOURS_MONEY_P = "P";//时点余额
	public static final String IND_TYPE_DAY_MONEY_A = "A";//日均余额
	public static final String IND_TYPE_FUSHU_H = "H";//户数
	public static final String IND_TYPE_GET_MONEY_F = "F";//发生额
	public static final String IND_TYPE_USE_TAR_X = "X";//效益指标
	//S/L/I/O-存款/贷款/中间业务/其他
	public static final String IND_TYPE_SAVE_S = "S";//存款
	public static final String IND_TYPE_LOAN_L = "L";//贷款
	public static final String IND_TYPE_INNER_I = "I";//中间业务
	public static final String IND_TYPE_OTHER_O = "O";//其他
	
	public static final String TABLE_NAME="PFM_TM_QTY_DEF";
	@Autowired
	private IDefaultService  sDefault;
	
	 @Autowired
	 private IKeyGenerator keyg;
	//protected IndicatorEngine engine;
	
	//private IProductService productService;
	
	
	 
	public QtyDefInsertOut insert(QtyDefPo newQtyDef, List<String> dayScopeList,QtyExpDefPo qtyExp)
			throws ServiceException, DbAccessException{
		TarCodeUtils tarCodeUtils=new TarCodeUtils(keyg,TABLE_NAME);
		QtyDefInsertOut out = new QtyDefInsertOut();
		String tarType = newQtyDef.getTarType();// 指标类型
		if (!ObjectUtil.isEmpty(newQtyDef)) {
			String prdType= "";
			//String prdType=productService.getPrdTypeById(newQtyDef.getPrdtypeCode());
			//prdType = getSlioByPrdtype(prdType);
			String pid=tarCodeUtils.gerneratedKey("Q",getEcmByTartype(newQtyDef), prdType, getPahfx(newQtyDef));
			newQtyDef.setTarCode(pid);
			ILogonUserInfo logonInfo = (ILogonUserInfo)AppUtils.getLogonUserInfo();
			newQtyDef.setCreateUser(logonInfo.getUser().getUserID());
			newQtyDef.setCreateTime(new Date());
			newQtyDef.setCreateOrg(logonInfo.getOperUnitId());
			if (dayScopeList != null && dayScopeList.size() > 0) {
				Object[] qtyArr = new Object[dayScopeList.size() + 1];
				Object[] pointAver = new Object[dayScopeList.size()];
				qtyArr[0] = newQtyDef;
				for (int i = 0; i < dayScopeList.size(); i++) {
					String dayScope = dayScopeList.get(i);
					if(!StringUtil.isEmpty(dayScope)){
					QtyDefPo qty = new QtyDefPo();
					PointAverRelaPo point = new PointAverRelaPo();
					try {
						BeanUtils.copyProperties(qty, newQtyDef);
						qty.setDayScope(dayScope);
						qty.setTarName(newQtyDef.getTarName() + "-" + dayScope);
						//生成主健Start
						String primaryKey=tarCodeUtils.gerneratedKey("Q", getEcmByTartype(newQtyDef), prdType, "A");
						qty.setTarCode(primaryKey);
						//生成主健End
						qty.setTarProperty(QtyDefPo.IND_TYPE_DAY_MONEY);// 日余额
						point.setPtarCode(pid);
						point.setAtarCode(primaryKey);
						point.setDayScope(qty.getDayScope());

					} catch (Exception e) {
						qty = null;
						point = null;
						e.printStackTrace();
					}
					if (null != qty) {
						qtyArr[i + 1] = qty;
						pointAver[i] = point;
					}
				  }
				}
				try {
					batchInsert(INSERT_QTYDEF, qtyArr);
					batchInsert(INSERT_POINTAVER, pointAver);
					out.setTarCode(newQtyDef.getTarCode());
					AppUtils.setResult(out, MsgConstants.I0001);
				} catch (SQLException e) {
					AppUtils.setResult(out, MsgConstants.E0002);
					e.printStackTrace();
				}
			} else {
				if (BaseTar.TAR_TYPE_HANDLE.equals(tarType)) {
					newQtyDef.setProcDateCode(newQtyDef.getStoreDate());//如果是手工指标 处理日期与保存日期相同
				}
				if(BaseTar.TAR_TYPE_MIX.equals(tarType)){
				    qtyExp.setTarCode(newQtyDef.getTarCode());//指标代码
				    qtyExp.setTarScope(newQtyDef.getTarScope());//适用对象
		            String strExp=qtyExp.getCalcExp();
		            String sqlExp = this.getSqlExp(qtyExp);
	                qtyExp.setSqlExp(sqlExp);
	                sDefault.getIbatisMediator().save(INSERT_QTYEXP,qtyExp);
	                this.saveQtyExpRela(newQtyDef,strExp);
				}
				sDefault.getIbatisMediator().save(INSERT_QTYDEF,newQtyDef);
				out.setTarCode(newQtyDef.getTarCode());
			}
		}
		//AppUtils.saveAuditLog("pfm.tm.qtydef","定量指标维护新增",newQtyDef.getTarCode(), BizType.STAFF, FunctionType.INSERT,ExecuteResult.SUCCESS);
		AppUtils.setResult(out,MsgConstants.I_PFM_0003);
		return out;
	}

	/**
	 * 修改定量指标
	 */
	public QtyDefUpdateOut update(QtyDefPo qtydef,List<String> dayScopeList,QtyExpDefPo qtyExp) throws ServiceException,
			DbAccessException {
		QtyDefUpdateOut out = new QtyDefUpdateOut();
		try {
            if (!ObjectUtil.isEmpty(qtydef)) {
            	if(BaseTar.TAR_TYPE_BASE.equals(qtydef.getTarType())){
            	    updateBaseTar(qtydef,dayScopeList);
            	}else if(BaseTar.TAR_TYPE_MIX.equals(qtydef.getTarType())){
            	    this.updateMixTar(qtydef, qtyExp);
            	}else if(BaseTar.TAR_TYPE_HANDLE.equals(qtydef.getTarType())){
            		sDefault.getIbatisMediator().update(UPD_QTYDEF, qtydef);//定量考评指标定义表
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
		sDefault.getIbatisMediator().update("qtydef.updQtyExpDefPo",expMap);//复合指标定义表
		sDefault.getIbatisMediator().update(UPD_QTYDEF, qtydef);//定量考评指标定义表
		//复合指标引用关联指标
		sDefault.getIbatisMediator().delete("qtydef.delQtyExpRela", qtydef.getTarCode());
		this.saveQtyExpRela(qtydef,qtyExp.getCalcExp());
	}
	
	/**
	 * 根据calcExp获取sqlExp
	 * @param qtyExp
	 * @return
	 * @throws ServiceException
	 */
	private String getSqlExp(QtyExpDefPo qtyExp) throws ServiceException{
       /* try {
        	Translator tlr = Translator.getInstance();
            String strExp=qtyExp.getCalcExp();
			String sqlExp = tlr.translate(strExp,new BitSet(8),"PeriodType","Current","");
			//Set set = tlr.translate01(sqlExp,new BitSet(8), dataCycle, "");
			//tlr.translate(exp, targetType, periodType, current, defGov);
			System.out.println(sqlExp);
			return sqlExp;
		} catch (TokenException e) {
			e.printStackTrace();
			throw new ServiceException("E0001",e.getMessage());
		}*/
		return null;
	}
	
	/**
	 * 保存复合指标引用关联指标
	 * @param qtydef
	 * @param calcExp
	 * @throws DbAccessException 
	 * @throws ServiceException 
	 * @throws Exception
	 */
	/*private void saveQtyExpRela(QtyDefPo qtydef,String calcExp) throws DbAccessException, ServiceException{
		try {
			TokenParser tp=new TokenParser(new IDLLanguage());
	        List<Token> tokens = tp.parse(calcExp);
			if(tokens!=null&&tokens.size()>0){
		         for(Token token : tokens){
		             //if(token!=null&&Token.OPERAND==token.getType()){//待修改
		        	 //token.setType(2);
		        	 if(token!=null&&Token.OPERAND==token.getType()){
		                 String tarExp=token.getExp();
		                 System.out.println(tarExp);
		                 if(tarExp.indexOf(IndicatorTakeHolder.SHARP)==0){
		                     String tarName=tarExp.substring(1);
		                     QtyDefPo qtyref=IndicatorEngine.getInstance().getModel(tarName);
		                     QtyExpRelaPo expRela=new QtyExpRelaPo();
		                     expRela.setTarCode(qtydef.getTarCode());
		                     expRela.setTarScope(qtydef.getTarScope());
		                     expRela.setRelaTarCode(qtyref.getTarCode());
		                     sDefault.getIbatisMediator().save(INSERT_QTYEXP_RELA,expRela);
		                 }
		             }
		         }
		     }
		} catch (Exception e) {
			throw new ServiceException("E0001",e.getMessage());
		}
	}*/
	
    @SuppressWarnings("unchecked")
    private void updateBaseTar(QtyDefPo qtydef, List<String> dayScopeList) throws DbAccessException {
        if (BaseTar.IND_TYPE_HOURS_MONEY.equals(qtydef.getTarProperty())) {
            List<PointAverRelaPo> prList = (List<PointAverRelaPo>) sDefault.getIbatisMediator().find(GET_POINTAVERRELA_BYPONTTAR, qtydef.getTarCode());
            List<String> allDayScopeList=Arrays.asList(BaseTar.AVER_SCOPE_Y,BaseTar.AVER_SCOPE_Q,BaseTar.AVER_SCOPE_M);
            if(dayScopeList!=null&&dayScopeList.size()>0){
                for(String dayScope : allDayScopeList){
                    if(isContainsDayScope(prList, dayScope)){
                        PointAverRelaPo pont=getTarCodeByDayScope(prList, dayScope);
                        if(!dayScopeList.contains(dayScope)){                            
                            if(!ObjectUtil.isEmpty(pont)){
                                Map<String, String> params=new HashMap<String, String>();
                                params.put("tarStatus", QtyDefPo.STATUS_DEL);
                                params.put("tarCode", pont.getAtarCode());
                                sDefault.getIbatisMediator().update("qtydef.updQtyForStatusById", params);
                            }                            
                        }else{
                            if(!ObjectUtil.isEmpty(pont)){
                                Map<String, String> params=new HashMap<String, String>();
                                params.put("tarStatus", QtyDefPo.STATUS_INPUT);
                                params.put("tarCode", pont.getAtarCode());
                                sDefault.getIbatisMediator().update("qtydef.updQtyForStatusById", params);
                            } 
                        }
                    }else{
                       if(dayScopeList.contains(dayScope)){
                           try{
                           TarCodeUtils tarCodeUtils=new TarCodeUtils(keyg,TABLE_NAME);
                           QtyDefPo qty = new QtyDefPo();
                           PointAverRelaPo point = new PointAverRelaPo();
                           BeanUtils.copyProperties(qty, qtydef);
                           qty.setDayScope(dayScope);
                           qty.setTarName(qtydef.getTarName() + "-" + dayScope);
                           //生成主健Start
                           String prdType="";
                          /* String prdType=productService.getPrdTypeById(qtydef.getPrdtypeCode());
                           prdType = getSlioByPrdtype(prdType);*/
                           String primaryKey=tarCodeUtils.gerneratedKey("Q", getEcmByTartype(qtydef), prdType, "A");
                           qty.setTarCode(primaryKey);
                           qty.setCreateOrg(qtydef.getLastUpdOrg());
                               //生成主健End
                           qty.setTarProperty(QtyDefPo.IND_TYPE_DAY_MONEY);// 日余额
                           point.setPtarCode(qtydef.getTarCode());
                           point.setAtarCode(primaryKey);
                           point.setDayScope(qty.getDayScope());
                           sDefault.getIbatisMediator().save(INSERT_QTYDEF, qty);
                           sDefault.getIbatisMediator().save(INSERT_POINTAVER, point);
                           }catch(Exception ex){
                             ex.printStackTrace();  
                           }
                       } 
                    }
                }
            }else{
                if(null!=prList&&prList.size()>0){
                Map<String, String> params=new HashMap<String, String>();
                params.put("tarStatus", QtyDefPo.STATUS_DEL);
                for(PointAverRelaPo point : prList){
                    params.put("tarCode", point.getAtarCode());
                    sDefault.getIbatisMediator().update("qtfdef.updQtyForStatusById", params);
                }
              }
            }
         }
      sDefault.getIbatisMediator().update(UPD_QTYDEF, qtydef);
    }
	/**
	 * 查询定量指标
	 */
	@SuppressWarnings("unchecked")
	public QtyDefQueryOut query(QtyDefQueryIn in) throws ServiceException {
		QtyDefQueryOut out = new QtyDefQueryOut();
		try {
			//IDefaultService defService = (IDefaultService) ContextUtil.getBean("defaultService");
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
			condition.put("qtyDef", qtyDef);
			if(BaseTar.APPOBJ_PERSONCODE.equals(in.getTarScope())){
			    List<String> tarScopeList=Arrays.asList(BaseTar.APPOBJ_PERSONCODE,BaseTar.APPOBJ_ORGANDPERCODE);
			    condition.put("tarScopeList", tarScopeList);
			}
			if(BaseTar.APPOBJ_ORGCODE.equals(in.getTarScope())){
                List<String> tarScopeList=Arrays.asList(BaseTar.APPOBJ_ORGCODE,BaseTar.APPOBJ_ORGANDPERCODE);
                condition.put("tarScopeList", tarScopeList);
            }
			if(BaseTar.APPOBJ_ORGANDPERCODE.equals(in.getTarScope())){
                List<String> tarScopeList=Arrays.asList(BaseTar.APPOBJ_ORGANDPERCODE);
                condition.put("tarScopeList", tarScopeList);
            }
			List<Long> totalnum = sDefault.getIbatisMediator().find(GET_QTYDEF_COUNT, condition);
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
				List<QtyDefPo> ret = (List<QtyDefPo>)sDefault.getIbatisMediator().find(GET_QTYDEF, condition, start,pagesize);
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
	 * 根据Id查询定量指标
	 * @throws DbAccessException 
	 */
	@SuppressWarnings("unchecked")
    public QtyDefQueryOut getQtyDefById(String tarCode) throws ServiceException, DbAccessException {
		QtyDefQueryOut out = new QtyDefQueryOut();
		try {
			QtyDefPo qtyDef = (QtyDefPo) sDefault.getIbatisMediator().findById(GET_ONE_QTYDEF, tarCode);
			out.setOneQtyDef(qtyDef);
			if(BaseTar.TAR_TYPE_MIX.equals(qtyDef.getTarType())){
				QtyExpDefPo qtyExp = (QtyExpDefPo) sDefault.getIbatisMediator().findById("qtydef.getQtyExpDefPo", tarCode);
				out.setQtyExp(qtyExp);
			}
			if(BaseTar.IND_TYPE_HOURS_MONEY.equals(qtyDef.getTarProperty())){
			   List<String> tarCodeList=(List<String>)sDefault.getIbatisMediator()
                .find("qtydef.getAtarCodeByPtarCode", tarCode);
			    if(tarCodeList!=null&&tarCodeList.size()>0){
			     Map<String, Object> param=new HashMap<String, Object>();
			     param.put("tarCodeList", tarCodeList);
			     List<QtyDefPo> qtyListForDaScope=(List<QtyDefPo>)sDefault.getIbatisMediator().find("qtydef.getQtyDefByPointPtar", param);
			     out.setQtyDayScopeList(qtyListForDaScope);
			    }
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
	 * 时点指标关联日均指标：PFM_TM_POINT_AVER_RELA
	 */
	public QtyDefDeleteOut delete(QtydefDeleteIn in) throws ServiceException,
			DbAccessException {
		QtyDefDeleteOut out = new QtyDefDeleteOut();
		int count = 0;
		List<QtyDefPo> list = in.getDeletes();
		//List<QtyDefPo> tarList = new ArrayList<QtyDefPo>();
		Map<String,Object> tarMap  = new HashMap<String,Object>();
		StringBuffer sb =  new StringBuffer();
		if (!ObjectUtil.isEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				QtyDefPo po = list.get(i);
				String tarType = po.getTarType();
				String tarCode = po.getTarCode();
				String tarName = po.getTarName();
				if(BaseTar.TAR_TYPE_BASE.equals(tarType)){//基础指标
					//1.是否被复合指标引用
					List relaList = sDefault.getIbatisMediator().find("qtydef.getQtyExpRela", tarCode);
					//2.如果被引用则抛出异常，提示：此基础指标被复合指标引用不能删除
					if(!ObjectUtil.isEmpty(relaList)){
						sb.append(tarName+"指标被复合指标引用;\n");
						continue;
					}else{
						//3.如果不存在，继续下一步
						//判断基础指标的指标属性是否为时点余额，只有时点余额才会存在日均指标
						if(BaseTar.IND_TYPE_HOURS_MONEY.equals(po.getTarProperty())){//时点余额
							//4.是否存在关联日均指标
							List pointList = sDefault.getIbatisMediator().find("qtydef.getPointAverRela", tarCode);
							//5.如果不存在,continue
							if(ObjectUtil.isEmpty(pointList)){
								tarMap.put(tarCode,tarCode);
								continue;
							}else{
								//6.如果存在，判断关联日均指标是否被引用
								for(int j=0;j<pointList.size();j++){
									PointAverRelaPo relaPo = (PointAverRelaPo)pointList.get(j);
									List relaList1 = sDefault.getIbatisMediator().find("qtydef.getQtyExpRela", relaPo.getAtarCode());
									//7.如果被引用则抛出异常，提示：此基础指标关联的日均指标被复合指标引用不能删除
									if(!ObjectUtil.isEmpty(relaList1)){
										sb.append(tarName+"指标关联的日均指标被复合指标引用;\n");
										continue;
									}else{
										//8.如果没有被引用，则先update基础指标状态=2，再update关联指标状态
										tarMap.put(relaPo.getAtarCode(),relaPo.getAtarCode());
									}
								}
								tarMap.put(tarCode,tarCode);
							}
						}else{
							tarMap.put(tarCode,tarCode);
						}			
					}
				}else{
					tarMap.put(tarCode,tarCode);
				}
			}
			//更新基础指标状态
			String[] tarCodeArray = new String[tarMap.size()];
			int k=0;
			for (Iterator iter = tarMap.keySet().iterator(); iter.hasNext();){
				String key = (String) iter.next();
				Object obj = tarMap.get(key);
				if(!ObjectUtil.isEmpty(obj)){
					tarCodeArray[k]=obj.toString();
					k++;
				}				
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tarCodeList", tarCodeArray);
			params.put("tarStatus", QtyDefPo.STATUS_DEL);
			count = sDefault.getIbatisMediator().update(UPD_STATUS_DEL,params);
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
		SqlMapClient client = sDefault.getIbatisMediator().getSqlMapClientTemplate().getSqlMapClient();
		client.startBatch();
		for (Object o : paraObjects) {
			if (o != null)
				client.insert(sqlId, o);
		}
		return client.executeBatch();
	}

	@SuppressWarnings("unchecked")
	public int batchInsert(String sqlId, Collection paraCollection)	throws SQLException, DbAccessException {
		if (paraCollection == null || paraCollection.size() == 0) {
			return 0;
		}
		Object[] paraObjects = new Object[paraCollection.size()];
		paraObjects = paraCollection.toArray(paraObjects);
		return batchInsert(sqlId, paraObjects);
	}

	
	/*public void setProductService(IProductService productService) {
		this.productService = productService;
	}*/
	 @SuppressWarnings("unchecked")
		public List<QtyDefPo> getQtyDefListBySortCode(String tarSortCode)
	        throws ServiceException {
	        try {
	        	 List<QtyDefPo> ret = (List<QtyDefPo>) sDefault.getIbatisMediator().find(GET_QTYDEF_BYSORT, tarSortCode);
	            return ret;
	        } catch (DbAccessException e) {
	            logger.error("DB", e);
	            throw new ServiceException(MsgConstants.E0002,"DB Is Error");
	        } catch (Exception e) {
	            logger.error("SERVICE", e);
	            throw new ServiceException(MsgConstants.E0000,"Server Is Error");
	        }
	    }
	 @SuppressWarnings("unchecked")
	    public QtyDefPo getQtyDefByTarName(String tarName){
	        try {
	            List<QtyDefPo> qtydefs =(List<QtyDefPo>) sDefault.getIbatisMediator().find(GET_QTYDEF_BYTARNAME, tarName);
	            if(qtydefs!=null&&qtydefs.size()>0){
	                return qtydefs.get(0);
	            }
	            return null;    
	        } catch (DbAccessException e) {
	            logger.error("DB", e);
	        } 
	        return null;
	    }
	 private String getEcmByTartype(QtyDefPo qty){
		 if(QtyDefPo.TAR_TYPE_BASE.equals(qty.getTarType())){
			 return "E";//生成主健时的基础指标代码
		 }else if(QtyDefPo.TAR_TYPE_HANDLE.equals(qty.getTarType())){
			 return "M";//生成主健时的手工指标代码
		 }else if(QtyDefPo.TAR_TYPE_MIX.equals(qty.getTarType())){
			 return "C";//生成主健时的复合指标代码
		 }
		 return "";
	 }
	 
	 private String getSlioByPrdtype(String prdType){
		/* if(PrdType.SAVE.getValue().equals(prdType)){
			 return "S";//存款
		 }else if(PrdType.LOAN.getValue().equals(prdType)){
			 return "L";//贷款
		 }else if(PrdType.INNER.getValue().equals(prdType)){
			 return "I";//中间业务
		 }else{
			 return "O";
		 }*/
		 return "O";
	 }
	    private String getPahfx(QtyDefPo qty){
			 if(QtyDefPo.IND_TYPE_HOURS_MONEY.equals(qty.getTarProperty())){
				 return IND_TYPE_HOURS_MONEY_P;
			 }else if(QtyDefPo.IND_TYPE_DAY_MONEY.equals(qty.getTarProperty())){
				 return IND_TYPE_DAY_MONEY_A;
			 }else if(QtyDefPo.IND_TYPE_FUSHU.equals(qty.getTarProperty())){
				 return IND_TYPE_FUSHU_H;
			 }else if(QtyDefPo.IND_TYPE_GET_MONEY.equals(qty.getTarProperty())){
				 return IND_TYPE_GET_MONEY_F;
			 }else if(QtyDefPo.IND_TYPE_USE_TAR.equals(qty.getTarProperty())){
				 return IND_TYPE_USE_TAR_X;
			 }
			 return "";
		 }
	 private boolean isContainsDayScope(List<PointAverRelaPo> prList,String dayScope){
	     for(PointAverRelaPo p :prList){
	         if(dayScope.equals(p.getDayScope())){
	             return true;
	         }
	     }
	     return false;
	 } 
	 private PointAverRelaPo getTarCodeByDayScope(List<PointAverRelaPo> prList,String dayScope){
	     for(PointAverRelaPo p :prList){
             if(dayScope.equals(p.getDayScope())){
                 return p;
             }
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
	  * 保存复合指标引用关联指标
	  * @param indicator
	  * @param calcExp
	  * @throws ServiceException
	  * @throws DbAccessException
	  */
	 public void saveQtyExpRela(QtyDefPo indicator,String calcExp) throws ServiceException, DbAccessException {
		 /*try {
		 	Translator tlr = Translator.getInstance();
		 	Set set = tlr.translate01(calcExp);
		 	sDefault.getIbatisMediator().delete("qtydef.delQtyExpRela",indicator.getTarCode());
			Iterator it = set.iterator();
			String targetCode;
			QtyExpRelaPo expRela=new QtyExpRelaPo();
            expRela.setTarCode(indicator.getTarCode());
            //expRela.setTarScope(indicator.getTarScope());
			while (it.hasNext()) {
				targetCode = (String) it.next();
				//targetCode = targetCode.substring(1, targetCode.length() - 1);//带有引号
                expRela.setRelaTarCode(targetCode);
                sDefault.getIbatisMediator().save(INSERT_QTYEXP_RELA,expRela);
			}
		} catch (TokenException e) {
			e.printStackTrace();
			throw new ServiceException("E0000","保存复合指标引用关联指标出现异常。");
		}*/
	}
}