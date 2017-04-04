package com.soule.base.service.auth;

import com.soule.comm.enu.ResourceType;
import com.soule.comm.enu.YesNoFlag;

public class ArcGrantModel {
    private String resCode;
    private ResourceType resType;
    private String channelCode;
    private Object url;
    private Boolean runFlag = Boolean.FALSE;
    private Boolean assFlag = Boolean.FALSE;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public ResourceType getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = ResourceType.valueOf(resType);
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public Boolean getRunFlag() {
        return runFlag;
    }

    public void setRunFlag(String runFlag) {
        this.runFlag = (YesNoFlag.getInstance(runFlag) == YesNoFlag.YES);
    }

    public Boolean getAssFlag() {
        return assFlag;
    }

    public void setAssFlag(String af) {
        this.assFlag = (YesNoFlag.getInstance(af) == YesNoFlag.YES);
    }

}
