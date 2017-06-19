package com.soule.app.report;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.MsgConstants;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * 
 */
@Namespace("/report")
public class MktAction extends ReportBaseAction {
	private static final long serialVersionUID = 1L;
	private List list;
	private List list2;
	private List list3;
	private String resultStr;
	private String resultStr2;
	private String thirdPro;
	private String fourthPro;

	@Autowired
	IDefaultService defService;

	public void doInit() {
		super.doInit();
		try {
			list = defService.getIbatisMediator().find("mkt.month", null);

			resultStr = JSONArray.fromObject(list).toString();
		} catch (DbAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String selFourth() {

		try {
			list2 = defService.getIbatisMediator().find("rpt_setdep.fourthPro", thirdPro);
			this.setRetCode(MsgConstants.I0000);
			// resultStr2=JSONArray.fromObject(list2).toString();
			// JsonConfig jsonCfg=new JsonConfig();
			// jsonCfg.registerJsonValueProcessor(String.class, new
			// JsonValueProcessorImpl());
			// resultStr=JSONArray.fromObject(list.g)
		} catch (DbAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON;

	}

	public String selFifth() {

		try {
			list3 = defService.getIbatisMediator().find("rpt_setdep.fifthPro", fourthPro);
			this.setRetCode(MsgConstants.I0000);
		} catch (DbAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON;

	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List getList2() {
		return list2;
	}

	public void setList2(List list2) {
		this.list2 = list2;
	}

	public List getList3() {
		return list3;
	}

	public void setList3(List list3) {
		this.list3 = list3;
	}

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public String getResultStr2() {
		return resultStr2;
	}

	public void setResultStr2(String resultStr2) {
		this.resultStr2 = resultStr2;
	}

	public String getThirdPro() {
		return thirdPro;
	}

	public void setThirdPro(String thirdPro) {
		this.thirdPro = thirdPro;
	}

	public String getFourthPro() {
		return fourthPro;
	}

	public void setFourthPro(String fourthPro) {
		this.fourthPro = fourthPro;
	}

	public IDefaultService getDefService() {
		return defService;
	}

	public void setDefService(IDefaultService defService) {
		this.defService = defService;
	}

}
