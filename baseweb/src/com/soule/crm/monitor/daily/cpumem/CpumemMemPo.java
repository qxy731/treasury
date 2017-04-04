package com.soule.crm.monitor.daily.cpumem;

import java.io.Serializable;

/**
 * 参数传递内存信息的类
 */
public class CpumemMemPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long current;
    private Long totalMem;
    private Long usedMem;
    private Long freeMem;

    /**
     * @return 状态时间
     */
    public Long getCurrent() {
        return current;
    }

    /**
     * @param current 状态时间
     */
    public void setCurrent(Long current) {
        this.current = current;
    }
    /**
     * @return 总内存
     */
    public Long getTotalMem() {
        return totalMem;
    }

    /**
     * @param totalMem 总内存
     */
    public void setTotalMem(Long totalMem) {
        this.totalMem = totalMem;
    }
    /**
     * @return 已使用内存
     */
    public Long getUsedMem() {
        return usedMem;
    }

    /**
     * @param usedMem 已使用内存
     */
    public void setUsedMem(Long usedMem) {
        this.usedMem = usedMem;
    }
    /**
     * @return 空闲内存
     */
    public Long getFreeMem() {
        return freeMem;
    }

    /**
     * @param freeMem 空闲内存
     */
    public void setFreeMem(Long freeMem) {
        this.freeMem = freeMem;
    }
}