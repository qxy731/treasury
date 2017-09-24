package com.soule.app.report;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.app.report.po.SetdepPo;
import com.soule.app.report.tools.RptUtils;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.comm.StringUtils;
import com.soule.comm.enu.BizType;
import com.soule.comm.enu.ExecuteResult;
import com.soule.comm.enu.FunctionType;
import com.soule.comm.po.IEnumItem;
import com.soule.comm.po.IEnumType;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.EnumTypeUtil;
import com.soule.comm.tools.ParamsUtil;
import com.soule.crm.comm.IUserManager;


/**
 * 统计报表业务操作
 */
@Service
public class ReportBaseServiceImpl implements IReportBaseService {

	private final static Log logger = LogFactory.getLog(IReportBaseService.class);

	@Autowired
	IDefaultService sDefault;
	@Autowired
	IUserManager um;
	@Autowired
	private AppUtils appUtils;
	@Autowired
	ParamsUtil paramsUtil;

	/**
	 * 查询报表数据
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String ,Object> query(HashMap in, String sqlKey) throws ServiceException {
		HashMap<String, Object> context = new HashMap<String, Object>();
		ReportOut out = new ReportOut();
		try {
			List<SetdepPo> list = new ArrayList<SetdepPo>();
			String cntExport = (String)in.get("excelCount");
			String cntPreview = (String)in.get("previewCount");
			int maxCount = 0;
			if (in.get("vflag") != null && in.get("vflag").equals("1")) {// 点击预览显示前2000条
			    int preCnt = StringUtils.parseInt(cntPreview, -1);
			    maxCount = (preCnt <= 0 ) ? 2000:preCnt;
			}
			else{
			    int expCnt = StringUtils.parseInt(cntExport, -1);
			    maxCount = (expCnt <= 0)? 
			        Integer.parseInt(paramsUtil.getParamValueByParamId("excelCount")):expCnt;
			
			}
			if (!"2".equals(in.get("dataType"))) {
			    in.put("dataType", "");
			}
			
			String flag = (String)in.get("checkFlag");//判断多项条件
            if(flag!=null){
                String orgCodes = (String)in.get("orgCodes");
                in.put("orgCodes",  "'" + orgCodes.replace(";", "','") + "'");
            }
			
			long actCount = sDefault.getIbatisMediator().getRecordTotal(sqlKey, in);
			if ((in.get("vflag") == null || !in.get("vflag").equals("1")) && actCount > maxCount) {
			    throw new ServiceException(MsgConstants.E9999,"需要导出的数据超过最大允许导出数,请设置条件缩小导出范围");
			}
			list = (List<SetdepPo>) sDefault.getIbatisMediator().find(sqlKey, in, 0, maxCount);
			out.setList(list);
			try {
				if (in.containsKey("orgCode") && in.get("orgCode") != null && !in.get("orgCode").equals("")) {
					List list2 = sDefault.getIbatisMediator().find("capital.orgName", (String) in.get("orgCode"));
					in.put("orgName", list2.get(0).toString());
				}
				if (in.containsKey("cryType") && in.get("cryType") != null && !in.get("cryType").equals("")) {
					in.put("cryType",getEnumValue(in.get("cryType").toString(), "cry_type"));
				}
				if (in.containsKey("fourthProduct") && in.get("fourthProduct") != null && !in.get("fourthProduct").equals("")) {
					List list2 = sDefault.getIbatisMediator().find("capital.fourthProduct", (String) in.get("fourthProduct"));
					in.put("fourthProduct", list2.get(0).toString());
				}
				if (in.containsKey("fifthProduct") && in.get("fifthProduct") != null && !in.get("fifthProduct").equals("")) {
					List list2 = sDefault.getIbatisMediator().find("capital.fifthProduct", (String) in.get("fifthProduct"));
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < list2.size(); i++){
						if (i == list2.size() - 1){
							sb.append(list2.get(i));
						}else{
							sb.append(list2.get(i));
							sb.append("、");
						}
					}
					in.put("fifthProduct", sb.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// String orgName = (String) in.get("orgName");
			//
			// in.put("orgName", URLDecoder.decode(orgName));
			// 填充表头数据信息
			context.putAll(in);
			if (out.getList().size() == 0) {
				context.put("rows", list);// RptUtils.initNull()

			} else {
				// 填充表中数据信息
				context.put("rows", list);
				// 填充表尾数据信息
				context = RptUtils.getSumTotal(context, list);
			}
			appUtils.saveAuditLog(sqlKey, URLDecoder.decode(in.get("reportCN").toString(), "UTF-8"), BizType.REPM,FunctionType.QUERY, ExecuteResult.SUCCESS);
			return context;

		} catch (DbAccessException e) {
			logger.error("DB", e);
			AppUtils.setResult(out, MsgConstants.E0002, e.getMessage());
		}catch (ServiceException e) {
            logger.error("SERVICE", e);
            AppUtils.setResult(out, MsgConstants.E9999, e.getMessage());
        }
		catch (Exception e) {
			logger.error("SYSTEM", e);
			AppUtils.setResult(out, MsgConstants.E0000);
		}
		return context;
	}
	private String getEnumValue(String flag, String enmuType){
    	String value = "";
    	IEnumType type = EnumTypeUtil.getEnumType(enmuType);
    	List<IEnumItem> items  = type.getItems();
    	for (IEnumItem item : items){
    		if (item.getKey().equals(flag)){
    			value = item.getValue();
    		}
    	}
    	return value;
    }
	public List initSel(HashMap params){
		List list = null;
		try {
			list = sDefault.getIbatisMediator().find((String)params.get("initSql"), params);
		} catch (DbAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
