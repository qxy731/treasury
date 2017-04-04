package com.soule.app.sys.batch;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Namespace;

import com.soule.base.service.ServiceException;
import com.soule.comm.po.IEnumItem;
import com.soule.comm.po.IEnumType;
import com.soule.comm.tools.EnumTypeUtil;
import com.soule.comm.tools.StringUtil;

@Namespace("/sys")
public class BatchDetailAction extends BatchAction {
    private static final long serialVersionUID = 1L;
    private final static Log logger = LogFactory.getLog(BatchDetailAction.class);
    private BatchStepPo step;

    private List<IEnumItem> batchProcesses;
    public void doInit() {
        super.doInit();
        IEnumType enumtype = EnumTypeUtil.getEnumType("batch_process");
        if (enumtype != null) {
            batchProcesses = enumtype.getItems();
        }
        String type = request.getParameter("type");
        if ("insert".equals(type)) {
            BatchStepPo bsp = new BatchStepPo();
            bsp.setBatchId(request.getParameter("bid"));
            bsp.setStepId(this.service.queryMaxStepId(bsp.getBatchId())+1);
            bsp.setStepName("NEW_STEP" + bsp.getStepId());
            bsp.setStepNo(0);
            this.setStep(bsp);
        } else if ("update".equals(type)) {
            BatchQueryStepIn qsi = new BatchQueryStepIn();
            qsi.setBatchId(request.getParameter("batchId"));
            qsi.setStepId(StringUtil.parseInt(request.getParameter("stepId"), 0));
            qsi.setChildId(StringUtil.parseInt(request.getParameter("childId"), 0));
            try {
                BatchQueryStepOut out = service.queryStep(qsi);
                this.setStep(out.getOne());
            } catch (ServiceException e) {
                logger.error(e);
            }
        }
    }

    public List<IEnumItem> getBatchProcesses() {
        return batchProcesses;
    }

    public void setBatchProcesses(List<IEnumItem> batchProcesses) {
        this.batchProcesses = batchProcesses;
    }

    public BatchStepPo getStep() {
        return step;
    }

    public void setStep(BatchStepPo step) {
        this.step = step;
    }
}
