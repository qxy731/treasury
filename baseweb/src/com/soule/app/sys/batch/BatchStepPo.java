package com.soule.app.sys.batch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 参数传递查询步骤信息列表的类
 */
public class BatchStepPo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String batchId;
    private Integer stepId;
    private Integer parentId;
    private Integer stepNo;
    private String stepType;
    private String stepName;
    private String stepDesc;
    private String procFreq;
    private String procClass;
    private String param1;
    private String param2;
    private String param3;
    private String param4;
    private String param5;
    private Integer compCount;
    private Integer skipStepId;
    private String dependIds;
    private String refTime;
    private String ext1;
    private String ext2;
    private String ext3;
    
    //显示参数
    private String hasChild;
    private List children = new ArrayList();
    private boolean isextend = false;

    /**
     * @return 批处理编号
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * @param batchId 批处理编号
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }
    /**
     * @return 步骤编号
     */
    public Integer getStepId() {
        return stepId;
    }

    /**
     * @param stepId 步骤编号
     */
    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }
    /**
     * @return 步骤名称
     */
    public String getStepName() {
        return stepName;
    }

    /**
     * @param stepName 步骤名称
     */
    public void setStepName(String stepName) {
        this.stepName = stepName;
    }
    /**
     * @return 步骤描述
     */
    public String getStepDesc() {
        return stepDesc;
    }

    /**
     * @param stepDesc 步骤描述
     */
    public void setStepDesc(String stepDesc) {
        this.stepDesc = stepDesc;
    }
    /**
     * @return 执行频率
     */
    public String getProcFreq() {
        return procFreq;
    }

    /**
     * @param procFreq 执行频率
     */
    public void setProcFreq(String procFreq) {
        this.procFreq = procFreq;
    }
    /**
     * @return 执行类名
     */
    public String getProcClass() {
        return procClass;
    }

    /**
     * @param procClass 执行类名
     */
    public void setProcClass(String procClass) {
        this.procClass = procClass;
    }
    /**
     * @return 参数1
     */
    public String getParam1() {
        return param1;
    }

    /**
     * @param param1 参数1
     */
    public void setParam1(String param1) {
        this.param1 = param1;
    }
    /**
     * @return 参数2
     */
    public String getParam2() {
        return param2;
    }

    /**
     * @param param2 参数2
     */
    public void setParam2(String param2) {
        this.param2 = param2;
    }
    /**
     * @return 参数3
     */
    public String getParam3() {
        return param3;
    }

    /**
     * @param param3 参数3
     */
    public void setParam3(String param3) {
        this.param3 = param3;
    }
    /**
     * @return 参数4
     */
    public String getParam4() {
        return param4;
    }

    /**
     * @param param4 参数4
     */
    public void setParam4(String param4) {
        this.param4 = param4;
    }
    /**
     * @return 参数5
     */
    public String getParam5() {
        return param5;
    }

    /**
     * @param param5 参数5
     */
    public void setParam5(String param5) {
        this.param5 = param5;
    }
    /**
     * @return 并发数
     */
    public Integer getCompCount() {
        return compCount;
    }

    /**
     * @param compCount 并发数
     */
    public void setCompCount(Integer compCount) {
        this.compCount = compCount;
    }
    /**
     * @return 跳转步骤编号
     */
    public Integer getSkipStepId() {
        return skipStepId;
    }

    /**
     * @param skipStepId 跳转步骤编号
     */
    public void setSkipStepId(Integer skipStepId) {
        this.skipStepId = skipStepId;
    }
    /**
     * @return 依赖步骤编号
     */
    public String getDependIds() {
        return dependIds;
    }

    /**
     * @param dependIds 依赖步骤编号
     */
    public void setDependIds(String dependIds) {
        this.dependIds = dependIds;
    }
    /**
     * @return 执行时间参考值
     */
    public String getRefTime() {
        return refTime;
    }

    /**
     * @param refTime 执行时间参考值
     */
    public void setRefTime(String refTime) {
        this.refTime = refTime;
    }
    /**
     * @return EXT1
     */
    public String getExt1() {
        return ext1;
    }

    /**
     * @param ext1 EXT1
     */
    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }
    /**
     * @return EXT2
     */
    public String getExt2() {
        return ext2;
    }

    /**
     * @param ext2 EXT2
     */
    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }
    /**
     * @return EXT3
     */
    public String getExt3() {
        return ext3;
    }

    /**
     * @param ext3 EXT3
     */
    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public String getHasChild() {
        return hasChild;
    }

    public void setHasChild(String hasChild) {
        this.hasChild = hasChild;
    }

    public List getChildren() {
        return children;
    }

    public void setChildren(List children) {
        this.children = children;
    }

    public boolean isIsextend() {
        return isextend;
    }

    public void setIsextend(boolean isextend) {
        this.isextend = isextend;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getStepType() {
        return stepType;
    }

    public void setStepType(String stepType) {
        this.stepType = stepType;
    }

    public Integer getStepNo() {
        return stepNo;
    }

    public void setStepNo(Integer stepNo) {
        this.stepNo = stepNo;
    }
    
}