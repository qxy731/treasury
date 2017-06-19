package com.soule.app.pfm.tm.report.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.app.pfm.tm.report.action.ModelAnalysisReportQueryIn;
import com.soule.app.pfm.tm.report.action.ModelAnalysisReportQueryOut;
import com.soule.app.pfm.tm.report.po.ModelAnalysisReportPo;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;

/**
 * 自定义报表操作
 */
@Service
public class ModelAnalysisReportServiceImpl implements IModelAnalysisReportService {

    // 数据库通用操作
	@Autowired
    private IDefaultService defService;
    private static final Log logger = LogFactory.getLog(ModelAnalysisReportServiceImpl.class);
    private String RPT_USERDEFINED_LIST;
    public ModelAnalysisReportServiceImpl() {
        this.RPT_USERDEFINED_LIST = "userdefinedreport.getUserDefinedReport";
    }

    /**
     * 自定义报表的查询
     */
    public ModelAnalysisReportQueryOut query(ModelAnalysisReportQueryIn in) throws ServiceException {
    	ModelAnalysisReportQueryOut out = new ModelAnalysisReportQueryOut();
        try {
            HashMap condition = new HashMap();
            ModelAnalysisReportPo modelAnalysisReport=new ModelAnalysisReportPo();
            modelAnalysisReport.setDataDate(in.getDataDate());
            modelAnalysisReport.setUnitId(in.getUnitId());
            modelAnalysisReport.setModelId(in.getModelCode());
            
            condition.put("modelAnalysisReport", modelAnalysisReport);
            long total = defService.getIbatisMediator().getRecordTotal(this.RPT_USERDEFINED_LIST, condition);;
            int pagesize = in.getInputHead().getPageSize();
            if (pagesize < 0) {
                pagesize = 10;
            }
            int pageno = in.getInputHead().getPageNo();
            int start = (pageno - 1) * pagesize;
            if (total > start) {
                List ret = defService.getIbatisMediator().find(this.RPT_USERDEFINED_LIST, condition, start, pagesize);
                out.setModelAnalysisReport(ret);;
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