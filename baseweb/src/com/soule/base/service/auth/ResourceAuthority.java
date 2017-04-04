package com.soule.base.service.auth;

import org.springframework.security.core.GrantedAuthority;

import com.soule.comm.enu.ResourceType;

public class ResourceAuthority implements GrantedAuthority {
    private static final long serialVersionUID = -1826154383157933147L;
    /**
     * 具体资源值
     */
    private Object value;
    /**
     * 权力标示
     */
    private String authority;
    /**
     * 分配权力标志
     */
    private Boolean assFlag;
    /**
     * 执行权力标志
     */
    private Boolean runFlag;

    private ResourceType resType;

    public ResourceAuthority(ResourceType type,String authority, Object object,Boolean rf,Boolean af) {
        this.resType = type;
        this.authority = authority;
        this.value = object;
        this.runFlag = rf;
        this.assFlag = af;
    }

    public String getAuthority() {
        return authority;
    }

    public Object getResource() {
        return value;
    }

    public ResourceType getResType() {
        return resType;
    }

    public Boolean getAssFlag() {
        return assFlag;
    }

    public Boolean getRunFlag() {
        return runFlag;
    }

}
